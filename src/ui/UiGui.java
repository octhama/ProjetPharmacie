package ui;

import enums.ETypeMedicament;
import io.EcritureRegistrePreparationCsv;
import pharmacie.Medicament;
import pharmacie.Ordonnance;
import pharmacie.Pharmacie;
import pharmacie.Preparation;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import java.io.IOException;
import java.util.List;

import static io.EcritureRegistrePreparationCsv.*;

/**
 * Interface graphique pour une pharmacie
 */

public class UiGui extends JFrame implements ActionListener {
    private final Pharmacie pharmacie;
    private final JPanel panelAfficherMedicaments;
    private final JPanel panelAfficherMedicamentsGenerique;
    private final JPanel panelAfficherMedicamentsNonGenerique;
    private final JPanel panelMedSurOrdonnance;
    private final JPanel panelMedVenteLibre;
    private final HashMap<Medicament, JCheckBox> medicamentCheckBoxMap;
    private final HashMap<JCheckBox, JSpinner> spinnerMap;

    /**
     * Constructeur de l'interface graphique
     *
     * @param pharmacie             La pharmacie à gérer
     * @param panelMedSurOrdonnance Le panel pour afficher les médicaments sur ordonnance
     * @param panelMedVenteLibre    Le panel pour afficher les médicaments en vente libre
     * @param medicamentCheckBoxMap La map pour stocker les médicaments et les cases à cocher
     * @param spinnerMap            La map pour stocker les cases à cocher et les spinners
     * @param fichierCsv            Le chemin du fichier CSV
     * @throws IOException          Si une erreur d'entrée/sortie se produit
     */

    public UiGui(Pharmacie pharmacie, JPanel panelMedSurOrdonnance, JPanel panelMedVenteLibre, HashMap<Medicament, JCheckBox> medicamentCheckBoxMap, HashMap<JCheckBox, JSpinner> spinnerMap, String fichierCsv) throws IOException {
        this.pharmacie = pharmacie;
        this.panelMedSurOrdonnance = panelMedSurOrdonnance;
        this.panelMedVenteLibre = panelMedVenteLibre;
        this.medicamentCheckBoxMap = medicamentCheckBoxMap;
        this.spinnerMap = spinnerMap;
        this.setTitle("Pharmacie");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        this.setVisible(true);

        // Ajout du menu
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu menuFichier = new JMenu("Fichier");
        menuBar.add(menuFichier);
        JMenuItem menuItemQuitter = new JMenuItem("Quitter");
        menuItemQuitter.addActionListener(this);
        menuFichier.add(menuItemQuitter);

        JMenu menuActionsPatient = new JMenu("Menu Patient");
        menuBar.add(menuActionsPatient);
        JMenuItem menuChercherMedicament = new JMenuItem("Chercher médicament");
        menuChercherMedicament.addActionListener(e -> chercherMedicament());
        menuActionsPatient.add(menuChercherMedicament);

        JMenuItem menuCommanderUnePreparation = new JMenuItem("Commander préparation(s)");
        menuCommanderUnePreparation.addActionListener(e -> {
            HashMap<Object, Object> checkBoxSpinnerMap = new HashMap<>();
            commanderUnePreparation(checkBoxSpinnerMap);
        });
        menuActionsPatient.add(menuCommanderUnePreparation);

        JMenuItem menuCommanderMedicamentVersionGenerique = new JMenuItem ("Commander médicament(s) en version générique");
        menuCommanderMedicamentVersionGenerique.addActionListener(this);
        menuActionsPatient.add(menuCommanderMedicamentVersionGenerique);


        // Initialisation des éléments d'interface
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Initialisation des éléments d'interface
        JLabel titreLabel = new JLabel("Bienvenue à la pharmacie !");
        titreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titreLabel, BorderLayout.NORTH);

        // TabbedPane pour afficher les médicaments
        JTabbedPane tabbedPane = new JTabbedPane();
        mainPanel.add(tabbedPane, BorderLayout.CENTER);


        // Création des panneaux pour afficher les médicaments
        panelAfficherMedicaments = new JPanel(new BorderLayout());
        panelAfficherMedicamentsGenerique = new JPanel(new BorderLayout());
        panelAfficherMedicamentsNonGenerique = new JPanel(new BorderLayout());
        panelMedSurOrdonnance = new JPanel(new BorderLayout());
        panelMedVenteLibre = new JPanel(new BorderLayout());

        tabbedPane.addTab("Médicaments", panelAfficherMedicaments);
        tabbedPane.addTab("Medicaments Génériques", panelAfficherMedicamentsGenerique);
        tabbedPane.addTab("Medicaments Non Génériques", panelAfficherMedicamentsNonGenerique);
        tabbedPane.addTab("Médicaments sur ordonnance", panelMedSurOrdonnance);
        tabbedPane.addTab("Médicaments en vente libre", panelMedVenteLibre);

        // Afficher les médicaments Génériques dans le panneau correspondant
        afficherListeMedicamentsGen(panelAfficherMedicamentsGenerique);

        // Afficher les médicaments Non Génériques dans le panneau correspondant
        afficherListeMedicamentsNonGen(panelAfficherMedicamentsNonGenerique);

        // Afficher les médicaments sur ordonnance dans le panneau correspondant
        afficherListeMedicamentsSurOrdonnance(panelMedSurOrdonnance);

        // Afficher les médicaments en vente libre dans le panneau correspondant
        afficherListeMedicamentsEnVenteLibre(panelMedVenteLibre);


        

        // Ajout moteur de recherche dynamique de médicament (les informations s'affichent en temps réel dans le panneau de médicaments)
        JTextField searchField = new JTextField(20);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            private void updateList() {
                String search = searchField.getText();
    List<Medicament> suggestions = pharmacie.trouverMedicamentsSuggestions(search);
    afficherListeMedicaments(panelAfficherMedicaments, suggestions);
            }

            // Affiche les suggestions de médicaments dans le même format que la liste complète
private void afficherListeMedicaments(JPanel panel, List<Medicament> suggestions) {
    // Création d'un panneau pour afficher les suggestions de médicaments
    JPanel medicamentsPanel = new JPanel();
    medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

    // Ajouter les détails de chaque médicament suggéré au panneau
    for (Medicament medicament : suggestions) {
        JLabel label = new JLabel(
                "<html><b>Nom:</b> " + medicament.getNom() +
                        "<br><b>Prix:</b> " + medicament.getPrix() + " €" +
                        "<br><b>Type:</b> " + medicament.getType() +
                        "<br><b>Générique:</b> " + (medicament.isGenerique() ? "Oui" : "Non") +
                        "<br><b>Quantité en stock:</b> " + medicament.getQuantiteEnStock() +
                        "</html>"
        );
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        medicamentsPanel.add(label);
    }

    // Retirer les anciens éléments du panneau
    panel.removeAll();

    // Ajouter le panneau des suggestions de médicaments au panneau principal
    panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);

    // Mettre à jour l'affichage du panneau
    panel.revalidate();
}
        });
        
        // Ajout des boutons
        JButton buttonQuitter = new JButton("Quitter");
        JButton buttonAfficher = new JButton("Afficher les médicaments");

        buttonQuitter.addActionListener(this);
        buttonAfficher.addActionListener(this);

        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoutons.add(buttonAfficher);
        panelBoutons.add(buttonQuitter);
        panelBoutons.add(searchField);
        add(panelBoutons, BorderLayout.SOUTH);

    }

    /**
     * Constructeur de l'interface graphique
     * @param pharmacie La pharmacie à gérer
     * @param panelMedSurOrdonnance Le panel pour afficher les médicaments sur ordonnance
     * @param panelMedVenteLibre Le panel pour afficher les médicaments en vente libre
     */

    public UiGui(Pharmacie pharmacie, JPanel panelMedSurOrdonnance, JPanel panelMedVenteLibre) throws IOException {
        this(pharmacie, panelMedVenteLibre, panelMedSurOrdonnance, new HashMap<>(), new HashMap<>(), fichierCsv);
    }

    /**
     * Constructeur de l'interface graphique
     * @param pharmacie La pharmacie à gérer
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    public UiGui(Pharmacie pharmacie) throws IOException {
        this(pharmacie, new JPanel(), new JPanel());
    }

    /**
     * Constructeur de l'interface graphique
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    // Méthode pour vérifier si un médicament existe dans src/data/medicaments.csv
    private void chercherMedicament() {
        // Créer une fenêtre pour la recherche de médicament
        JFrame frame = new JFrame("Chercher un médicament");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 100);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Créer un panneau pour les éléments de la fenêtre
        JPanel panel = new JPanel(new FlowLayout());
        frame.add(panel);

        // Créer un champ de texte pour la recherche
        JTextField searchField = new JTextField(20);
        panel.add(searchField);

        // Créer un bouton pour lancer la recherche
        JButton searchButton = new JButton("Rechercher");
        panel.add(searchButton);

        // Ajouter un écouteur pour le bouton de recherche
        searchButton.addActionListener(e -> {
            String nomMedicament = searchField.getText();
            Medicament medicament = (Medicament) pharmacie.trouverMedicamentsSuggestions(nomMedicament);
            if (medicament != null) {
                JOptionPane.showMessageDialog(frame, "Le médicament " + nomMedicament + " existe dans la pharmacie.");
            } else {
                JOptionPane.showMessageDialog(frame, "Le médicament " + nomMedicament + " n'existe pas dans la pharmacie.");
            }
        });

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }

    /**
     * Méthode pour afficher la liste des médicaments génériques dans un JPanel
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    private void commanderUnePreparation(HashMap<Object, Object> checkBoxSpinnerMap) {

        // Créer une fenêtre pour la commande de préparation
        JFrame frame = new JFrame("Commander une préparation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Créer un panneau pour les éléments de la fenêtre
        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        // Créer un panneau pour les médicaments
        JPanel medicamentPanel = new JPanel(new GridLayout(0, 1));
        panel.add(new JScrollPane(medicamentPanel), BorderLayout.CENTER);

        // Remplir le panneau des médicaments avec des cases à cocher et des spinners pour la sélection
        List<Medicament> medicaments = pharmacie.getMedicaments();
        for (Medicament medicament : medicaments) {
            JPanel entryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JCheckBox checkBox = new JCheckBox();
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, medicament.getQuantiteEnStock(), 1));
            JLabel nameLabel = new JLabel(medicament.getNom());

            // Ajouter les éléments à medicamentCheckBoxMap et spinnerMap
            medicamentCheckBoxMap.put(medicament, checkBox);
            spinnerMap.put(checkBox, spinner);

            // Ajouter un écouteur d'item pour chaque checkbox pour activer/désactiver les spinners
            checkBox.addItemListener(e -> {
                boolean isSelected = checkBox.isSelected();
                spinner.setEnabled(isSelected);
                nameLabel.setEnabled(isSelected);

                if (isSelected) {
                    int quantite = (int) spinner.getValue();
                    if (quantite > medicament.getQuantiteEnStock()) {
                        spinner.setValue(medicament.getQuantiteEnStock());
                        JOptionPane.showMessageDialog(null, "Stock insuffisant pour ce médicament. Quantité maximum disponible : " + medicament.getQuantiteEnStock());
                    }
                    checkBoxSpinnerMap.put(checkBox, spinner);
                } else {
                    checkBoxSpinnerMap.remove(checkBox);
                }
            });

            // Par défaut, les spinners et les labels sont désactivés
            spinner.setEnabled(false);
            nameLabel.setEnabled(false);

            entryPanel.add(checkBox);
            entryPanel.add(nameLabel);
            entryPanel.add(spinner);

            // Encapsuler chaque médicament dans un panneau individuel
            JPanel medicineEntryPanel = new JPanel(new BorderLayout());
            medicineEntryPanel.add(entryPanel, BorderLayout.CENTER);
            medicamentPanel.add(medicineEntryPanel);
        }

        // Créer un panneau pour le bouton de commande
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Ajouter un bouton pour lancer la commande
        JButton buttonCommander = new JButton("Commander");
        bottomPanel.add(buttonCommander);
        JButton searchButton = new JButton("Rechercher");
        searchButton.addActionListener(e ->  chercherMedicament());
        bottomPanel.add(searchButton);

        // Action du bouton de commande
        buttonCommander.addActionListener(e -> {
            // Vérifier si une ordonnance est disponible
            if (!ordonnanceDisponible()) {
                JOptionPane.showMessageDialog(frame, "Une ordonnance est nécessaire pour commander une préparation.");
                return; // Arrêter le traitement si l'ordonnance est manquante
            }

            // Créer une boîte de dialogue pour la confirmation de la commande
            JFrame confirmationDialog = new JFrame("Confirmation de commande");
            confirmationDialog.setSize(400, 300);
            confirmationDialog.setLocationRelativeTo(frame);
            confirmationDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            confirmationDialog.setResizable(false);

            // Créer un panneau pour les éléments de la boîte de dialogue
            JPanel confirmationPanel = new JPanel(new BorderLayout());
            confirmationDialog.add(confirmationPanel);

            // Créer un panneau pour les choix de médicaments confirmés
            JPanel choicesPanel = new JPanel(new GridLayout(0, 1));
            confirmationPanel.add(new JScrollPane(choicesPanel), BorderLayout.CENTER);

            // Créer une liste pour stocker les médicaments sélectionnés et leurs quantités
            List<Medicament> selectedMedicaments = new ArrayList<>();
            List<Integer> selectedQuantities = new ArrayList<>();

            // Afficher les choix de médicaments confirmés dans la boîte de dialogue
            for (Map.Entry<Medicament, JCheckBox> entry : medicamentCheckBoxMap.entrySet()) { // Parcourir les entrées de la map de la boîte de la boîte de dialogue
                Medicament medicament = entry.getKey();
                JCheckBox checkBox = entry.getValue();
                if (checkBox.isSelected()) {
                    int quantite = (int) spinnerMap.get(checkBox).getValue();
                    JLabel choiceLabel = new JLabel(medicament.getNom() + " - " + quantite + " unité(s)");
                    choicesPanel.add(choiceLabel);
                    selectedMedicaments.add(medicament);
                    selectedQuantities.add(quantite);
                }
            }

            // Créer un panneau pour les boutons de validation et d'annulation
            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            confirmationPanel.add(buttonsPanel, BorderLayout.SOUTH);

            // Ajouter un bouton pour valider la commande
            JButton validateButton = new JButton("Valider");
            buttonsPanel.add(validateButton);

            // Action du bouton de validation
            validateButton.addActionListener(validateEvent -> {
                // Vérifier si le stock est suffisant pour tous les médicaments sélectionnés
                boolean stockInsuffisant = false;
                for (int i = 0; i < selectedMedicaments.size(); i++) {
                    Medicament medicament = selectedMedicaments.get(i);
                    int quantite = selectedQuantities.get(i);
                    if (quantite > medicament.getQuantiteEnStock()) {
                        stockInsuffisant = true;
                        break;
                    }
                }

                if (stockInsuffisant) {
                    JOptionPane.showMessageDialog(confirmationDialog, "Stock insuffisant pour certains médicaments.");
                } else {
                    // Calculer la date de livraison estimée
                    Calendar dateLivraison = calculerDateLivraison();

                    // Construire le message de confirmation
                    StringBuilder confirmationMessage = new StringBuilder("Les médicaments suivants ont été commandés :\n\n");
                    for (int i = 0; i < selectedMedicaments.size(); i++) {
                        Medicament medicament = selectedMedicaments.get(i);
                        int quantite = selectedQuantities.get(i);
                        confirmationMessage.append("- ").append(medicament.getNom()).append(" (").append(quantite).append(" unité(s))\n");
                    }
                    confirmationMessage.append("\nLa commande sera livrée le ").append(dateLivraison.getTime());

                    // Afficher le message de confirmation
                    JOptionPane.showMessageDialog(confirmationDialog, confirmationMessage.toString());

                    // Mettre à jour les quantités en stock des médicaments
                    for (int i = 0; i < selectedMedicaments.size(); i++) {
                        Medicament medicament = selectedMedicaments.get(i);
                        int quantite = selectedQuantities.get(i);
                        int nouvelleQuantite = medicament.getQuantiteEnStock() - quantite;
                        medicament.setQuantiteEnStock(nouvelleQuantite);
                    }

                    // Ecrire les informations de la commande dans le fichier CSV (à implémenter)
                    Preparation preparation = new Preparation(); // Créer un objet Preparation avec les données nécessaires
                    EcritureRegistrePreparationCsv.ecrirePreparationsCsv(preparation); // Appel de la méthode pour écrire dans le fichier CSV

                    confirmationDialog.dispose();
                }
            });

            // Ajouter un bouton pour annuler la commande
            JButton cancelButton = new JButton("Annuler");
            buttonsPanel.add(cancelButton);

            // Ajouter un écouteur pour le bouton d'annulation
            cancelButton.addActionListener(cancelEvent -> confirmationDialog.dispose()); // Fermer la boîte de dialogue en cas d'annulation

            // Afficher la boîte de dialogue de confirmation
            confirmationDialog.setVisible(true);

            // Fermer la fenêtre de commande de médicaments
            frame.dispose();
        });

        // Afficher la fenêtre
        frame.setVisible(true);
    }


    /**
     * Méthode pour afficher la liste des médicaments dans un JPanel
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    // Fonction pour vérifier si une ordonnance est disponible
    private boolean ordonnanceDisponible() {
        // Implémente la logique pour vérifier si une ordonnance est disponible
        // Retourne true si une ordonnance est disponible, sinon false
        return true;
    }

    /**
     * Méthode pour afficher la liste des médicaments dans un JPanel
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    // Fonction pour calculer la date de livraison en ajoutant un jour à la date actuelle
    private Calendar calculerDateLivraison() {
        Calendar dateLivraison = Calendar.getInstance();
        dateLivraison.add(Calendar.DAY_OF_MONTH, 1); // Ajouter un jour
        // Vérifier si la date de livraison est un dimanche
        if (dateLivraison.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            dateLivraison.add(Calendar.DAY_OF_MONTH, 1); // Ajouter un jour supplémentaire
        }
        return dateLivraison;
    }

    /**
     * Méthode pour gérer les événements des boutons
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    // Afficher les medicament de src/data/medicaments.csv
    private void afficherListeMedicaments(JPanel panel) {
        // Récupérer la liste des médicaments de la pharmacie
        List<Medicament> medicaments = pharmacie.getMedicaments();

        // Création d'un panneau pour afficher les médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médicament au panneau
        for (Medicament medicament : medicaments) {
            JLabel label = new JLabel(
                    "<html><b>Nom:</b> " + medicament.getNom() +
                            "<br><b>Prix:</b> " + medicament.getPrix() + " €" +
                            "<br><b>Type:</b> " + medicament.getType() +
                            "<br><b>Générique:</b> " + (medicament.isGenerique() ? "Oui" : "Non") +
                            "<br><b>Quantité en stock:</b> " + medicament.getQuantiteEnStock() +
                            "</html>"
            );
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            medicamentsPanel.add(label);
        }
        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    /**
     *  Méthode pour gérer les événements des boutons
     * @param panel
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

     // Afficher la liste des médicaments générique
    private void afficherListeMedicamentsGen(JPanel panel) {
        // Récupérer la liste des médicaments de la pharmacie
        List<Medicament> medicaments = pharmacie.getMedicaments();

        // Création d'un panneau pour afficher les médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médicament au panneau
        for (Medicament medicament : medicaments) {
            if (medicament.isGenerique()) {
                JLabel label = new JLabel(
                        "<html><b>Nom:</b> " + medicament.getNom() +
                                "<br><b>Prix:</b> " + medicament.getPrix() + " €" +
                                "<br><b>Type:</b> " + medicament.getType() +
                                "<br><b>Générique:</b> " + (medicament.isGenerique() ? "Oui" : "Non") +
                                "<br><b>Quantité en stock:</b> " + medicament.getQuantiteEnStock() +
                                "</html>"
                );
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                medicamentsPanel.add(label);
            }
        }
        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    /**
     * Méthode pour gérer les événements des boutons
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    // Afficher la liste des médicaments non générique
    private void afficherListeMedicamentsNonGen(JPanel panel) {
        // Récupérer la liste des médicaments de la pharmacie
        List<Medicament> medicaments = pharmacie.getMedicaments();

        // Création d'un panneau pour afficher les médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médicament au panneau
        for (Medicament medicament : medicaments) {
            if (!medicament.isGenerique()) {
                JLabel label = new JLabel(
                        "<html><b>Nom:</b> " + medicament.getNom() +
                                "<br><b>Prix:</b> " + medicament.getPrix() + " €" +
                                "<br><b>Type:</b> " + medicament.getType() +
                                "<br><b>Générique:</b> " + (medicament.isGenerique() ? "Oui" : "Non") +
                                "<br><b>Quantité en stock:</b> " + medicament.getQuantiteEnStock() +
                                "</html>"
                );
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                medicamentsPanel.add(label);
            }
        }
        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    /**
     * Méthode pour gérer les événements des boutons
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    // Afficher la liste des médicaments sur ordonnance
    private  void afficherListeMedicamentsSurOrdonnance(JPanel panel) {
        // Récupérer la liste des médicaments de la pharmacie
        List<Medicament> medicaments = pharmacie.getMedicaments();

        // Création d'un panneau pour afficher les médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médicament au panneau
        for (Medicament medicament : medicaments) {
            if (medicament.getType() == ETypeMedicament.ORDONNANCE) {
                JLabel label = new JLabel(
                        "<html><b>Nom:</b> " + medicament.getNom() +
                                "<br><b>Prix:</b> " + medicament.getPrix() + " €" +
                                "<br><b>Type:</b> " + medicament.getType() +
                                "<br><b>Générique:</b> " + (medicament.isGenerique() ? "Oui" : "Non") +
                                "<br><b>Quantité en stock:</b> " + medicament.getQuantiteEnStock() +
                                "</html>"
                );
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                medicamentsPanel.add(label);
            }
        }
        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    /**
     * Méthode pour gérer les événements des boutons
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    // Afficher la liste des médicaments en vente libre
    private void afficherListeMedicamentsEnVenteLibre(JPanel panel) {
        // Récupérer la liste des médicaments de la pharmacie
        List<Medicament> medicaments = pharmacie.getMedicaments();

        // Création d'un panneau pour afficher les médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médicament au panneau
        for (Medicament medicament : medicaments) {
            if (medicament.getType() == ETypeMedicament.VENTE_LIBRE) {
                JLabel label = new JLabel(
                        "<html><b>Nom:</b> " + medicament.getNom() +
                                "<br><b>Prix:</b> " + medicament.getPrix() + " €" +
                                "<br><b>Type:</b> " + medicament.getType() +
                                "<br><b>Générique:</b> " + (medicament.isGenerique() ? "Oui" : "Non") +
                                "<br><b>Quantité en stock:</b> " + medicament.getQuantiteEnStock() +
                                "</html>"
                );
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                medicamentsPanel.add(label);
            }
        }
        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    /**
     * Méthode pour afficher l'interface graphique
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    public void afficher() {
        // Afficher l'interface graphique
        this.setVisible(true);
    }

    /**
     * Méthode pour gérer les événements des boutons
     * @param e L'événement déclenché
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Quitter":
                System.exit(0);
                break;
            case "Afficher les médicaments":
                afficherListeMedicaments(panelAfficherMedicaments);
                break;
        }
    }
}
