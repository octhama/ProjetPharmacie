package ui;

import pharmacie.Medicament;
import pharmacie.Pharmacie;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UiGui extends JFrame implements ActionListener {
    private final Pharmacie pharmacie;
    private final JPanel panelAfficherMedicaments;
    private final JPanel panelAfficherMedicamentsGenerique;
    private final JPanel panelAfficherMedicamentsNonGenerique;
    // Déclaration des attributs
    private final HashMap<Medicament, JCheckBox> medicamentCheckBoxMap;
    private final HashMap<JCheckBox, JSpinner> spinnerMap;

    public UiGui(Pharmacie pharmacie, HashMap<Medicament, JCheckBox> medicamentCheckBoxMap, HashMap<JCheckBox, JSpinner> spinnerMap) throws IOException {
        this.pharmacie = pharmacie;
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

        // TabbedPane pour les fonctionnalités
        JTabbedPane tabbedPane = new JTabbedPane();
        mainPanel.add(tabbedPane, BorderLayout.CENTER);


        // Création des panneaux pour afficher les différentes fonctionnalités
        panelAfficherMedicaments = new JPanel(new BorderLayout());
        panelAfficherMedicamentsGenerique = new JPanel(new BorderLayout());
        panelAfficherMedicamentsNonGenerique = new JPanel(new BorderLayout());
        tabbedPane.addTab("Médicaments", panelAfficherMedicaments);
        tabbedPane.addTab("Medicaments Génériques", panelAfficherMedicamentsGenerique);
        tabbedPane.addTab("Medicaments Non Génériques", panelAfficherMedicamentsNonGenerique);

        // Afficher les médicaments Génériques dans le panneau correspondant
        afficherListeMedicamentsGen(panelAfficherMedicamentsGenerique);

        // Afficher les médicaments Non Génériques dans le panneau correspondant
        afficherListeMedicamentsNonGen(panelAfficherMedicamentsNonGenerique);

        // Ajout des boutons
        JButton buttonQuitter = new JButton("Quitter");
        JButton buttonAfficher = new JButton("Afficher les médicaments");

        buttonQuitter.addActionListener(this);
        buttonAfficher.addActionListener(this);

        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoutons.add(buttonAfficher);
        panelBoutons.add(buttonQuitter);
        add(panelBoutons, BorderLayout.SOUTH);
    }

    public UiGui(Pharmacie pharmacie) throws IOException {
        this(pharmacie, new HashMap<>(), new HashMap<>());
    }

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
            Medicament medicament = pharmacie.trouverMedicament(nomMedicament);
            if (medicament != null) {
                JOptionPane.showMessageDialog(frame, "Le médicament " + nomMedicament + " existe dans la pharmacie.");
            } else {
                JOptionPane.showMessageDialog(frame, "Le médicament " + nomMedicament + " n'existe pas dans la pharmacie.");
            }
        });

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }

    private void commanderUnePreparation(HashMap<Object, Object> checkBoxSpinnerMap){
    
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
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
            JLabel nameLabel = new JLabel(medicament.getNom());

            // Ajouter les éléments à medicamentCheckBoxMap et spinnerMap
            medicamentCheckBoxMap.put(medicament, checkBox);
            spinnerMap.put(checkBox, spinner);

            // Ajouter un écouteur d'item pour chaque checkbox
            checkBox.addItemListener(e -> {
                boolean isSelected = checkBox.isSelected();
                spinner.setEnabled(isSelected);
                nameLabel.setEnabled(isSelected);

                if (isSelected) {
                    int quantite = (int) spinner.getValue();
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
            checkBoxSpinnerMap.put(checkBox, spinner);
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
            // Créer une boîte de dialogue pour la commande de médicaments
            JFrame dialogFrame = new JFrame("Commander une préparation");
            dialogFrame.setSize(400, 300);
            dialogFrame.setLocationRelativeTo(frame);
            dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialogFrame.setResizable(false);

            // Créer un panneau pour les éléments de la boîte de dialogue
            JPanel dialogPanel = new JPanel(new BorderLayout());
            dialogFrame.add(dialogPanel);

            // Créer un panneau pour les choix de médicaments
            JPanel choicesPanel = new JPanel(new GridLayout(0, 1));
            dialogPanel.add(new JScrollPane(choicesPanel), BorderLayout.CENTER);

            // Créer une liste pour stocker les médicaments sélectionnés et leurs quantités
            List<Medicament> selectedMedicaments = new ArrayList<>();
            List<Integer> selectedQuantities = new ArrayList<>();

            // Ajouter les choix de médicaments à la boîte de dialogue
            for (Map.Entry<Medicament, JCheckBox> entry : medicamentCheckBoxMap.entrySet()) {
                Medicament medicament = entry.getKey();
                JCheckBox checkBox = entry.getValue();
                if (checkBox.isSelected()) {
                    int quantite = (int) spinnerMap.get(checkBox).getValue();
                    JLabel choiceLabel = new JLabel(medicament.getNom() + " - " + quantite + " unité(s)");
                    choicesPanel.add(choiceLabel);
                    selectedMedicaments.add(medicament);
                    selectedQuantities.add(quantite);
                } else {
                    checkBox.setEnabled(false);
                }
            }

            // Créer un panneau pour les boutons de validation et d'annulation
            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            dialogPanel.add(buttonsPanel, BorderLayout.SOUTH);

            // Ajouter un bouton pour valider la commande
            JButton validateButton = new JButton("Valider");
            buttonsPanel.add(validateButton);

            // Action du bouton de validation
            validateButton.addActionListener(validateEvent -> {
                // Construire le message de confirmation
                StringBuilder confirmationMessage = new StringBuilder("Le(s) médicament(s) suivant(s) ont été commandé(s) :\n");
                // Un indicateur pour savoir si au moins un médicament n'était pas disponible en quantité suffisante
                boolean stockInsuffisant = false;
                // Parcourir chaque médicament sélectionné
                for (int i = 0; i < selectedMedicaments.size(); i++) {
                    Medicament medicament = selectedMedicaments.get(i);
                    int quantite = selectedQuantities.get(i);
                    int quantiteEnStock = medicament.getQuantiteEnStock();
                    // Vérifier si la quantité en stock est suffisante
                    if (quantite <= quantiteEnStock) {
                        int nouvelleQuantite = quantiteEnStock - quantite;
                        if (nouvelleQuantite >= 0) {
                            // Mettre à jour la quantité en stock du médicament
                            medicament.setQuantiteEnStock(nouvelleQuantite);
                            // Gérer le cas où seule une partie du médicament est nécessaire pour la préparation
                            if (medicament.getQuantitePourPreparation() < 1.0) {
                                int reste = (int) (quantite * (1.0 - medicament.getQuantitePourPreparation()));
                                confirmationMessage.append("- ").append(medicament.getNom()).append(" (").append(reste).append(" unité(s))\n");
                            } else {
                                // Ajouter les informations du médicament à la confirmation
                                confirmationMessage.append("- ").append(medicament.getNom()).append(" (").append(quantite).append(" unité(s))\n");
                            }
                        }
                    } else {
                        // Mettre à jour l'indicateur indiquant un stock insuffisant
                        stockInsuffisant = true;
                    }
                }
                // Si au moins un médicament n'était pas disponible en quantité suffisante, afficher un message d'erreur
                if (stockInsuffisant) {
                    JOptionPane.showMessageDialog(dialogFrame, "Stock insuffisant pour certains médicaments.");
                } else {
                    // Calculer la date de livraison
                    Calendar dateLivraison = calculerDateLivraison();
                    // Afficher le message de confirmation avec la date de livraison
                    confirmationMessage.append("\nLa commande sera livrée le ").append(dateLivraison.getTime());
                    // Afficher le message de confirmation
                    JOptionPane.showMessageDialog(dialogFrame, confirmationMessage.toString());
                }
                // Fermer la boîte de dialogue
                dialogFrame.dispose();
            });

            // Ajouter un bouton pour annuler la commande
            JButton cancelButton = new JButton("Annuler");
            buttonsPanel.add(cancelButton);

            // Ajouter un écouteur pour le bouton d'annulation
            cancelButton.addActionListener(cancelEvent -> dialogFrame.dispose()); // Fermer la boîte de dialogue en cas d'annulation

            // Afficher la boîte de dialogue
            dialogFrame.setVisible(true);

            // Fermer la fenêtre de commande de médicaments
            frame.dispose();
        });

        // Afficher la fenêtre
        frame.setVisible(true);
    }
    // Fonction pour vérifier si une ordonnance est disponible
    private boolean ordonnanceDisponible() {
        // Implémente la logique pour vérifier si une ordonnance est disponible
        // Retourne true si une ordonnance est disponible, sinon false
        return true;
    }

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

    public void afficher() {
        // Afficher l'interface graphique
        this.setVisible(true);
    }

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
