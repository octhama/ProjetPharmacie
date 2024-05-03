package ui;

import enums.ETypeMedicament;
import io.EcritureRegistrePreparationCsv;
import pharmacie.Medicament;
import pharmacie.Pharmacie;
import pharmacie.Preparation;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static io.EcritureRegistrePreparationCsv.*;

/**
 * Interface graphique pour une pharmacie
 */

public class UiGui extends JFrame implements ActionListener {
    private final Pharmacie pharmacie;
    private final JPanel panelAfficherMedicaments;
    private final HashMap<Medicament, JCheckBox> medicamentCheckBoxMap;
    private final HashMap<JCheckBox, JSpinner> spinnerMap;
    private final Map<Medicament, Boolean> selectedMedicamentStates = new HashMap<>();
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
        JPanel panelMedSurOrdonnance1 = panelMedSurOrdonnance;
        JPanel panelMedVenteLibre1 = panelMedVenteLibre;
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

        JMenuItem menuEnregistrerOrdonnance = new JMenuItem("Enregistrer une ordonnance");
        menuEnregistrerOrdonnance.addActionListener(this);
        menuActionsPatient.add(menuEnregistrerOrdonnance);

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
        JPanel panelAfficherMedicamentsGenerique = new JPanel(new BorderLayout());
        JPanel panelAfficherMedicamentsNonGenerique = new JPanel(new BorderLayout());
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
        
        // Ajout des boutons et moteur de recherche
        JButton buttonQuitter = new JButton("Quitter");
        JButton buttonAfficher = new JButton("Afficher les médicaments");
        JButton buttonJeSuisPharmacien = new JButton("Je suis pharmacien");

        JTextField searchField = new JTextField(20);
        searchField.setForeground(Color.GRAY); // Couleur de texte grise
        searchField.setText("Rechercher un médicament..."); // Texte placeholder

        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Rechercher un médicament...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK); // Changer la couleur du texte à noir lorsque le champ obtient le focus
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Rechercher un médicament...");
                    searchField.setForeground(Color.GRAY); // Changer la couleur du texte à gris lorsque le champ perd le focus
                }
            }
        });

        buttonQuitter.addActionListener(this);
        buttonAfficher.addActionListener(this);
        buttonJeSuisPharmacien.addActionListener(e -> {
            accesMenuPharmacien();
        });
        

        JPanel panelBoutonsEtRecherche = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoutonsEtRecherche.add(buttonJeSuisPharmacien);
        panelBoutonsEtRecherche.add(searchField);
        panelBoutonsEtRecherche.add(buttonAfficher);
        panelBoutonsEtRecherche.add(buttonQuitter);
        add(panelBoutonsEtRecherche, BorderLayout.SOUTH);

        // Ajout moteur de recherche dynamique de médicament (les informations s'affichent en temps réel dans le panneau de médicaments)
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
        JScrollPane scrollPane = new JScrollPane(medicamentPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Créér un conteneur pour le champ de recherche
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(searchPanel, BorderLayout.NORTH);

        // Créer le label "Rechercher un médicament"
        JLabel searchLabel = new JLabel("Rechercher un médicament : ");
        searchPanel.add(searchLabel, BorderLayout.WEST);

        // Créer le champ de recherche
        JTextField searchField = new JTextField();
        searchField.setColumns(20);
        searchPanel.add(searchField, BorderLayout.CENTER);

        // Ajouter un écouteur au champ de recherche pour la recherche dynamique
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
                // Sauvegarder l'état de sélection des médicaments
                saveSelectedMedicamentStates();
                // Filtrer les médicaments en fonction de la recherche
                List<Medicament> filteredMedicaments = pharmacie.filterMedicaments(search);
                // Mettre à jour l'affichage des médicaments
                afficherMedicaments(filteredMedicaments);
                // Restaurer l'état de sélection des médicaments
                restoreSelectedMedicamentStates();
            }

            private void afficherMedicaments(List<Medicament> filteredMedicaments) {
                // Nettoyer le panneau des médicaments avant d'ajouter de nouveaux éléments
                medicamentPanel.removeAll();
            
                // Parcourir la liste des médicaments filtrés
                for (Medicament medicament : filteredMedicaments) {
                    // Créer les composants pour afficher le médicament
                    JPanel entryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JCheckBox checkBox = new JCheckBox();
                    JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, medicament.getQuantiteEnStock(), 1));
                    JLabel nameLabel = new JLabel(medicament.getNom());
            
                    // Ajouter les éléments à medicamentCheckBoxMap et spinnerMap
                    medicamentCheckBoxMap.put(medicament, checkBox);
                    spinnerMap.put(checkBox, spinner);

                    final int quantiteEnStock = medicament.getQuantiteEnStock();
                    // Ajouter un écouteur d'item pour chaque case à cocher pour activer/désactiver les spinners
                    checkBox.addItemListener(e -> {
                        boolean isSelected = checkBox.isSelected();
                        spinner.setEnabled(isSelected);
                        nameLabel.setEnabled(isSelected);
            
                        if (isSelected) {
                            int quantite = (int) spinner.getValue();
                            if (quantite > quantiteEnStock) {
                                spinner.setValue(quantiteEnStock);
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
            
                    // Ajouter les composants au panneau d'entrée
                    entryPanel.add(checkBox);
                    entryPanel.add(nameLabel);
                    entryPanel.add(spinner);
            
                    // Encapsuler chaque médicament dans un panneau individuel
                    JPanel medicineEntryPanel = new JPanel(new BorderLayout());
                    medicineEntryPanel.add(entryPanel, BorderLayout.CENTER);
                    medicamentPanel.add(medicineEntryPanel);
                }
            
                // Rafraîchir l'interface utilisateur pour refléter les changements
                medicamentPanel.revalidate();
                medicamentPanel.repaint();
            }
            
        });

        // Créer un panneau pour le bouton de commande
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Ajouter un bouton pour lancer la commande
        JButton buttonCommander = new JButton("Commander");
        bottomPanel.add(buttonCommander);

        // Action du bouton de commande
        buttonCommander.addActionListener(e -> {
            // Afficher une boîte de dialogue pour saisir l'identifiant de l'ordonnance
            String referenceOrdonnance = JOptionPane.showInputDialog(frame, "Veuillez saisir la référence de l'ordonnance:");

            // Vérifier si l'ordonnance est disponible
            if (!ordonnanceDisponible(referenceOrdonnance)) {
                JOptionPane.showMessageDialog(frame, "Ordonnance introuvable. Veuillez vérifier la référence de l'ordonnance.");
            } else {
                // L'ordonnance est disponible, procéder à la validation de la commande
                // Récupérer la liste des médicaments prescrits dans l'ordonnance
                List<String> medicamentsPrescrits = getMedicamentsPrescrits(referenceOrdonnance);

                // Vérifier si les médicaments sélectionnés correspondent à ceux prescrits
                if (!medicamentsCorrespondants(medicamentsPrescrits)) {
                    JOptionPane.showMessageDialog(frame, "Certains médicaments sélectionnés ne correspondent pas à ceux prescrits.");
                    // Afficher une boîte de dialogue avec la liste des médicaments prescrits
                    StringBuilder message = new StringBuilder("Les médicaments suivants sont prescrits dans l'ordonnance :\n\n");
                    for (String medicamentPrescrit : medicamentsPrescrits) {
                        message.append("- ").append(medicamentPrescrit).append("\n");
                    }
                    message.append("\nVeuillez sélectionner les médicaments prescrits.");
                    JOptionPane.showMessageDialog(frame, message.toString());
                }else{

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
            }
        }
        });

        // Afficher la fenêtre
        frame.setVisible(true);
    }

    private void saveSelectedMedicamentStates() {
        selectedMedicamentStates.clear();
        for (Map.Entry<Medicament, JCheckBox> entry : medicamentCheckBoxMap.entrySet()) {
            Medicament medicament = entry.getKey();
            JCheckBox checkBox = entry.getValue();
            selectedMedicamentStates.put(medicament, checkBox.isSelected());
        }
    }

    private void restoreSelectedMedicamentStates() {
        for (Map.Entry<Medicament, Boolean> entry : selectedMedicamentStates.entrySet()) {
            Medicament medicament = entry.getKey();
            boolean isSelected = entry.getValue();
            JCheckBox checkBox = medicamentCheckBoxMap.get(medicament);
            checkBox.setSelected(isSelected);
        }
    }

    private void accesMenuPharmacien() {
        // Demander à l'utilisateur de saisir l'identifiant et le mot de passe
        String id = JOptionPane.showInputDialog(null, "Identifiant pharmacien :");
        String password = JOptionPane.showInputDialog(null, "Mot de passe :");
    
        // Vérifier l'authentification
        if (authentifierPharmacien(id, password)) {
            // Authentification réussie, afficher le menu du pharmacien
            afficherMenuPharmacien();
        } else {
            // Afficher un message d'erreur si l'authentification a échoué
            JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect.");
        }
    }
    
    private boolean authentifierPharmacien(String id, String password) {
    String csvFilePath = "src/data/authidpharmacien.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
        String line;
        // Lire chaque ligne du fichier CSV
        while ((line = br.readLine()) != null) {
            // Diviser la ligne en utilisant le délimiteur approprié (virgule dans ce cas)
            String[] parts = line.split(",");
            // Vérifier si la ligne contient l'identifiant et le mot de passe fournis
            if (parts.length >= 2 && parts[0].equals(id) && parts[1].equals(password)) {
                return true; // Authentification réussie
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return false; // Aucune correspondance trouvée
}

    private void afficherMenuPharmacien() {
        // Créer une nouvelle fenêtre pour le menu du pharmacien
        JFrame menuPharmacienFrame = new JFrame("Menu Pharmacien");
        menuPharmacienFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuPharmacienFrame.setSize(400, 300);
        menuPharmacienFrame.setLocationRelativeTo(null);
        menuPharmacienFrame.setResizable(false);
    
        // Créer un panneau pour les boutons du menu
        JPanel menuPanel = new JPanel(new GridLayout(0, 1));
        menuPharmacienFrame.add(menuPanel);
    
        // Ajouter des boutons pour les fonctionnalités du menu
        JButton ajouterMedicamentButton = new JButton("Ajouter un médicament");
        JButton ajouterPharmacienButton = new JButton("Ajouter un pharmacien");
        JButton afficherLeStockMedicamentButton = new JButton("Afficher le stock des médicaments");
        JButton afficherLesPreparationsButton = new JButton("Afficher les préparations");
        JButton afficherLesOrdonnancesButton = new JButton("Afficher les ordonnances");
        JButton afficherLesPatientsButton = new JButton("Afficher les patients");
        JButton afficherLesPharmaciensButton = new JButton("Afficher les pharmaciens");
        JButton afficherLesDemandesDeMedGen = new JButton("Afficher les demandes de médicaments génériques");

        // Ajoutez d'autres boutons pour d'autres fonctionnalités si nécessaire
    
        // Ajouter des actions aux boutons
        ajouterMedicamentButton.addActionListener(e -> {
            // Ajouter la logique pour ajouter un médicament
        });
    
        ajouterPharmacienButton.addActionListener(e -> {
            // Ajouter la logique pour ajouter un pharmacien
        });
    
        // Ajouter les boutons au panneau du menu
        menuPanel.add(ajouterMedicamentButton);
        menuPanel.add(ajouterPharmacienButton);
        menuPanel.add(afficherLeStockMedicamentButton);
        menuPanel.add(afficherLesPreparationsButton);
        menuPanel.add(afficherLesOrdonnancesButton);
        menuPanel.add(afficherLesPatientsButton);
        menuPanel.add(afficherLesPharmaciensButton);
        menuPanel.add(afficherLesDemandesDeMedGen);
        
        // Ajoutez d'autres boutons au panneau du menu si nécessaire
    
        // Afficher la fenêtre du menu du pharmacien
        menuPharmacienFrame.setVisible(true);
    }

    private boolean ordonnanceDisponible(String referencePatient) {
        String csvFilePath = "src/data/dataordonnances.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Lire chaque ligne du fichier CSV
            while ((line = br.readLine()) != null) {
                // Diviser la ligne en utilisant le délimiteur approprié (virgule dans ce cas)
                String[] parts = line.split(",");
                // Vérifier si la ligne contient la référence du patient donnée
                if (parts.length >= 2 && parts[1].trim().equals(referencePatient.trim())) {
                    return true; // Ordonnance trouvée
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Aucune correspondance trouvée
    }

    private List<String> getMedicamentsPrescrits(String referencePatient) {
        String csvFilePath = "src/data/dataordonnances.csv";
        List<String> medicamentsPrescrits = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Lire chaque ligne du fichier CSV
            while ((line = br.readLine()) != null) {
                // Diviser la ligne en utilisant le délimiteur approprié (virgule dans ce cas)
                String[] parts = line.split(",");
                // Vérifier si la ligne contient la référence du patient donnée
                if (parts.length >= 2 && parts[1].trim().equals(referencePatient.trim())) {
                    // Ajouter les médicaments prescrits à la liste
                    if (parts.length >= 4) {
                        String[] medicaments = parts[3].split(",");
                        for (String medicament : medicaments) {
                            medicamentsPrescrits.add(medicament.trim());
                        }
                    }
                    break; // Arrêter la recherche après avoir trouvé la référence du patient
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return medicamentsPrescrits;
    }

    private boolean medicamentsCorrespondants(List<String> medicamentsPrescrits) {
        for (Map.Entry<Medicament, JCheckBox> entry : medicamentCheckBoxMap.entrySet()) {
            Medicament medicament = entry.getKey();
            JCheckBox checkBox = entry.getValue();
            if (checkBox.isSelected() && !medicamentsPrescrits.contains(medicament.getNom())) {
                return false; // Un médicament sélectionné ne correspond pas à ceux prescrits
            }
        }
        return true; // Tous les médicaments sélectionnés correspondent à ceux prescrits
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
