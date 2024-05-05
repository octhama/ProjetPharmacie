package ui;

import enums.ETypeMedicament;
import io.EcritureMedicamentsCsv;
import io.EcritureRegistreDemandeVersionGeneriqueCsv;
import io.EcritureRegistrePreparationCsv;
import io.LectureRegistrePreparation;
import pharmacie.*;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Interface graphique pour une pharmacie
 */

public class UiGui extends JFrame implements ActionListener {
    private final Pharmacie pharmacie;
    private final JPanel panelAfficherMedicaments;
    private final HashMap<Medicament, JCheckBox> medicamentCheckBoxMap;
    private final HashMap<Medicament, JCheckBox> medicamentGenCheckBoxMap;
    private final HashMap<JCheckBox, JSpinner> spinnerMap;
    private final Map<Medicament, Boolean> selectedMedicamentStates = new HashMap<>();
    private final Map<JCheckBox, Object> spinnerStates = new HashMap<>();
    private final Map<Medicament, Integer> spinnerValues = new HashMap<>();
    private final List<Medicament> selectedMedicaments;
    private final List<Integer> selectedQuantities;
    private static final String CSV_FILE_PATH = "src/data/dataordonnances.csv";

    /**
     * Constructeur de l'interface graphique
     *
     * @param pharmacie             La pharmacie à gérer
     * @param medicamentCheckBoxMap La map pour stocker les médicaments et les cases à cocher
     * @param spinnerMap            La map pour stocker les cases à cocher et les spinners
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    public UiGui(Pharmacie pharmacie, HashMap<Medicament, JCheckBox> medicamentCheckBoxMap, HashMap<JCheckBox, JSpinner> spinnerMap, HashMap<Medicament, JCheckBox> medicamentGenCheckBoxMap, List<Medicament> selectedMedicaments, List<Integer> selectedQuantities) throws IOException {
        this.pharmacie = pharmacie;
        this.medicamentGenCheckBoxMap = medicamentGenCheckBoxMap;
        this.medicamentCheckBoxMap = medicamentCheckBoxMap;
        this.spinnerMap = spinnerMap;
        this.selectedMedicaments = selectedMedicaments;
        this.selectedQuantities = selectedQuantities;
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

        // Déclaration du menu d'enregistrement d'ordonnance en dehors de la méthode
        JMenuItem menuEnregistrerOrdonnance = getjMenuItem();
        // Ajouter le menu au menuActionsPatient
        menuActionsPatient.add(menuEnregistrerOrdonnance);

        JMenuItem menuCommanderUnePreparation = new JMenuItem("Commander préparation(s)");
        menuCommanderUnePreparation.addActionListener(e -> {
            HashMap<Object, Object> checkBoxSpinnerMap = new HashMap<>();
            commanderUnePreparation(checkBoxSpinnerMap);
        });
        menuActionsPatient.add(menuCommanderUnePreparation);

        JMenuItem menuCommanderMedicamentVersionGenerique = new JMenuItem("Commander médicament(s) en version générique");
        menuCommanderMedicamentVersionGenerique.addActionListener(e -> {
            commanderMedicamentVersionGenerique();
        });
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
        JPanel panelMedSurOrdonnance = new JPanel(new BorderLayout());
        JPanel panelMedVenteLibre = new JPanel(new BorderLayout());

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
                    JLabel label = getjLabel(medicament);
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

    public UiGui(Pharmacie pharmacie, List<Medicament> selectedMedicaments, List<Integer> selectedQuantities) throws IOException {
        this(pharmacie, new HashMap<>(), new HashMap<>(), new HashMap<>(), selectedMedicaments, selectedQuantities);
    }

    public UiGui(Pharmacie pharmacie) throws IOException {
        this(pharmacie, new ArrayList<>(), new ArrayList<>());
    }

    private JMenuItem getjMenuItem() {
        JMenuItem menuEnregistrerOrdonnance = new JMenuItem("Enregistrer une ordonnance (Authentification médecin requise)");
        menuEnregistrerOrdonnance.addActionListener(e -> {
            String referenceOrdonnance = JOptionPane.showInputDialog(this, "Veuillez saisir la référence de l'ordonnance :");
            if (ordonnanceDisponible(referenceOrdonnance)) {
                JOptionPane.showMessageDialog(this, "L'ordonnance existe déjà.");
            } else {
                enregistrerOrdonnance();
            }
        });
        return menuEnregistrerOrdonnance;
    }

    private void enregistrerOrdonnance() {
        // Demander à l'utilisateur de s'authentifier en tant que médecin
        String idMedecin = JOptionPane.showInputDialog(this, "Veuillez saisir votre identifiant médecin :");
        String passwordMedecin = JOptionPane.showInputDialog(this, "Veuillez saisir votre mot de passe médecin :");

        // Vérifier l'authentification
        if (authentifierMedecin(idMedecin, passwordMedecin)) {
            // Authentification réussie, continuer avec l'enregistrement de l'ordonnance

            // Afficher une boîte de dialogue pour saisir le nom du médecin
            String referenceMedecin = JOptionPane.showInputDialog(this, "Veuillez saisir la référence du médecin :");
            // Afficher une boîte de dialogue pour saisir le nom du patient
            String referencePatient = JOptionPane.showInputDialog(this, "Veuillez saisir la référence du patient :");
            // Afficher une boîte de dialogue pour saisir la date de prescription
            String datePrescriptionString = JOptionPane.showInputDialog(this, "Veuillez saisir la date de prescription (format : yyyy-MM-dd) :");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date datePrescription = null;
            try {
                datePrescription = dateFormat.parse(datePrescriptionString);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Format de date invalide. Veuillez saisir une date au format yyyy-MM-dd.");
                return;
            }

            // Afficher une boîte de dialogue pour saisir le ou les médicament(s) prescrit(s)
            String medicamentsString = JOptionPane.showInputDialog(this, "Veuillez saisir le ou les médicament(s) prescrit(s) (séparés par des virgules) :");
            String[] medicamentsArray = medicamentsString.split(",");
            Stack<String> medicaments = new Stack<>();
            for (String medicament : medicamentsArray) {
                medicaments.push(medicament);
            }
            // Enregistrer l'ordonnance
            ecrireOrdonnanceCsv(referenceMedecin, referencePatient, datePrescription, medicaments);
        } else {
            // Afficher un message d'erreur si l'authentification a échoué
            JOptionPane.showMessageDialog(this, "Identifiant ou mot de passe médecin incorrect. Vous n'êtes pas autorisé à enregistrer une ordonnance.");
        }
    }

    public void ecrireOrdonnanceCsv(String referenceMedecin, String referencePatient, Date datePrescription, Stack<String> medicaments) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(datePrescription);

        try (FileWriter fw = new FileWriter(CSV_FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            String sb = referenceMedecin + ", " +
                    referencePatient + ", " +
                    formattedDate + ", " +
                    String.join("; ", medicaments) + "\n";

            out.print(sb);

            System.out.println("Ordonnance enregistrée avec succès.");

        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement de l'ordonnance : " + e.getMessage());
        }
    }

    private static JLabel getjLabel(Medicament medicament) {
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
        return label;
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
                // Sauvegarder les états des éléments avant la recherche
                saveSelectedMedicamentStates();
                saveSpinnerStates();

                // Effectuer la recherche et afficher les médicaments filtrés
                String search = searchField.getText();
                List<Medicament> filteredMedicaments = pharmacie.filterMedicaments(search);
                afficherMedicaments(filteredMedicaments);

                // Restaurer les états des éléments après l'affichage des médicaments filtrés
                restoreSelectedMedicamentStates();
                restoreSpinnerStates();
            }

            // Afficher les médicaments filtrés dans le panneau des médicaments
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

                    // Vérifier si le médicament est générique ou non
                    if (medicament.isGenerique()) {
                        // Colorer le texte en bleu pour les médicaments génériques
                        nameLabel.setForeground(Color.BLUE);
                    } else {
                        // Colorer le texte en rouge pour les médicaments non génériques
                        nameLabel.setForeground(Color.RED);
                    }

                    // Ajouter les composants au panneau d'entrée
                    entryPanel.add(checkBox);
                    entryPanel.add(nameLabel);
                    entryPanel.add(spinner);

                    // Ajouter un séparateur pour le checkbox supplémentaire et le label dynamique
                    entryPanel.add(new JLabel("|"));

                    JCheckBox halfDoseCheckBox = getjCheckBox(medicament, entryPanel);
                    entryPanel.add(halfDoseCheckBox);

                    // Ajouter un séparateur pour le checkbox supplémentaire et le label dynamique
                    entryPanel.add(new JLabel("|"));

                    // Si la case à cocher est non sélectionnée afficher le prix du médicament
                    if (!checkBox.isSelected()) {
                        JLabel prixLabel = new JLabel("Prix : " + medicament.getPrix() + " €");
                        entryPanel.add(prixLabel);
                    }

                    // Par défaut, les spinners et les labels sont désactivés
                    spinner.setEnabled(false);
                    nameLabel.setEnabled(false);

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
                    JOptionPane.showMessageDialog(frame, "Le(s) médicament(s) sélectionné(s) ne correspond(ent) pas à ce(ux) prescrit(s).");
                    // Afficher une boîte de dialogue avec la liste des médicaments prescrits
                    StringBuilder message = new StringBuilder("Le(s) médicament(s) suivant(s) sont prescrit(s) dans l'ordonnance :\n\n");
                    for (String medicamentPrescrit : medicamentsPrescrits) {
                        message.append("- ").append(medicamentPrescrit).append("\n");
                    }
                    message.append("\nVeuillez sélectionner le(s) médicament(s) prescrit(s).");
                    JOptionPane.showMessageDialog(frame, message.toString());
                } else {

                    // Vérifier si le stock est suffisant pour tous les médicaments sélectionnés dans le fichier CSV src/data/medicaments.csv
                    final boolean[] stockInsuffisant = {false};
                    for (int i = 0; i < selectedMedicaments.size(); i++) {
                        Medicament medicament = selectedMedicaments.get(i);
                        int quantite = selectedQuantities.get(i);
                        if (quantite > medicament.getQuantiteEnStock()) {
                            stockInsuffisant[0] = true;
                            break;
                        }
                    }

                    if (stockInsuffisant[0]) {
                        JOptionPane.showMessageDialog(frame, "Stock insuffisant pour ce(s) médicament(s).");
                    } else {

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
                        List<Integer> nouvellesQuantites = new ArrayList<>();
                        List<Medicament> medicaments = pharmacie.getMedicaments();

                        // Afficher les choix de médicaments confirmés dans la boîte de dialogue
                        for (Map.Entry<Medicament, JCheckBox> entry : medicamentCheckBoxMap.entrySet()) {
                            // Parcourir les entrées de la map de la boîte de dialogue
                            Medicament medicament = entry.getKey();
                            JCheckBox checkBox = entry.getValue();
                            if (checkBox.isSelected()) {
                                // Récupérer le spinner correspondant à la case à cocher sélectionnée
                                JSpinner spinner = spinnerMap.get(checkBox);
                                // Ajouter le spinner à la map avec la case à cocher correspondante
                                checkBoxSpinnerMap.put(checkBox, spinner);
                                // Ajouter les informations sur le médicament sélectionné à la boîte de dialogue
                                int quantite = (int) spinner.getValue();
                                JLabel choiceLabel = new JLabel(medicament.getNom() + " - " + quantite + " unité(s)");
                                choicesPanel.add(choiceLabel);
                                // Ajouter le médicament et sa quantité aux listes sélectionnées
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
                            // Vérifier si le stock est suffisant pour tous les médicaments sélectionnés dans le fichier CSV src/data/medicaments.csv
                            for (int i = 0; i < selectedMedicaments.size(); i++) {
                                Medicament medicament = selectedMedicaments.get(i);
                                int quantite = selectedQuantities.get(i);
                                if (quantite > medicament.getQuantiteEnStock()) {
                                    stockInsuffisant[0] = true;
                                    break;
                                }
                            }
                            if (stockInsuffisant[0]) {
                                JOptionPane.showMessageDialog(confirmationDialog, "Stock insuffisant pour ce(s) médicament(s).");
                            } else {
                                // Calculer la date de livraison estimée
                                Calendar dateLivraison = calculerDateLivraison();

                                // Construire le message de confirmation
                                StringBuilder confirmationMessage = new StringBuilder("Les médicament(s) suivant(s) ont été commandé(s) :\n\n");
                                double montantTotal = 0.0;
                                boolean isHalfDoseSelected = false;
                                for (int i = 0; i < selectedMedicaments.size(); i++) {
                                    Medicament medicament = selectedMedicaments.get(i);
                                    int quantite = selectedQuantities.get(i);
                                    double prixUnitaire = medicament.getPrix();
                                    double prixTotal = quantite * prixUnitaire;
                                    montantTotal += prixTotal;
                                    confirmationMessage.append("- ").append(medicament.getNom()).append(" (").append(quantite).append(" unité(s))\n • Prix unitaire : ").append(prixUnitaire).append(" €\n • Prix total : ").append(prixTotal).append(" €\n");

                                    // Vérifier si le médicament a été commandé à 50% du prix
                                    if (medicament.estCommandeA50Pourcent()) {
                                        isHalfDoseSelected = true; // Mettre isHalfDoseSelected à true si au moins un médicament est commandé à 50%
                                        double prix50Pourcent = medicament.getPrix() / 2;
                                        confirmationMessage.append("    - Prix 50% : ").append(prix50Pourcent * quantite).append(" €\n");
                                    }
                                }
                                confirmationMessage.append("\nMontant total à payer : ").append(montantTotal).append(" €");

                                // Vérifier si la case 50% a été cochée pour au moins un médicament
                                if (isHalfDoseSelected) {
                                    // Calculer le montant restant à payer (payer le prix complet)
                                    double montantRestant = montantTotal - (montantTotal / 2);
                                    confirmationMessage.append("\nMontant restant à payer après avoir payé le prix complet : ").append(montantRestant).append(" €");
                                } else {
                                    confirmationMessage.append("\nAucun médicament n'a été commandé à 50% du prix.");
                                }

                                confirmationMessage.append("\nLa commande sera livrée le ").append(dateLivraison.getTime());

                                // Afficher le message de confirmation
                                JOptionPane.showMessageDialog(confirmationDialog, confirmationMessage.toString());

                                // Mettre à jour les quantités en stock des médicaments
                                for (int i = 0; i < selectedMedicaments.size(); i++) {
                                    Medicament medicament = selectedMedicaments.get(i);
                                    int nouvelleQuantite = medicament.getQuantiteEnStock() - selectedQuantities.get(i);
                                    medicament.setQuantiteEnStock(nouvelleQuantite);
                                }

                                // Réécrire le fichier CSV des médicaments avec les nouvelles quantités
                                try {
                                    EcritureMedicamentsCsv.ecrireMajQttStockMedicamentsCsv(selectedMedicaments, "src/data/medicaments.csv");
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }

                                // Ecrire les informations de la commande dans le fichier CSV (à implémenter)
                                EcritureRegistrePreparationCsv.ecrireRegistrePreparationCsv(selectedMedicaments, selectedQuantities, "src/data/registrepreparation.csv");
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
            }
        });

        // Afficher la fenêtre
        frame.setVisible(true);
    }

    private void commanderMedicamentVersionGenerique() {
        // Créer une fenêtre pour la commande de médicaments en version générique
        JFrame frame = new JFrame("Demande de médicament(s) en version générique");
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

        // Créer un conteneur pour le champ de recherche
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(searchPanel, BorderLayout.NORTH);

        // Créer le label "Rechercher un médicament"
        JLabel searchLabel = new JLabel("Rechercher un médicament : ");
        searchPanel.add(searchLabel, BorderLayout.WEST);

        // Créer le champ de recherche
        JTextField searchField = new JTextField();
        searchField.setColumns(20);
        searchPanel.add(searchField, BorderLayout.CENTER);

        // Afficher les médicaments non génériques
        afficherListeMedicamentsNonGenOnDemand(medicamentPanel);

        // Créer un panneau pour le bouton enregistrer la demande, retourner à la fenêtre principale et réinitialiser les champs de recherche
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Ajouter un bouton pour lancer la commande
        JButton buttonCommander = new JButton("Enregistrer demande(s)");
        bottomPanel.add(buttonCommander);

        JButton buttonReinitialiser = new JButton("Réinitialiser");
        bottomPanel.add(buttonReinitialiser);

        JButton buttonRetour = new JButton("Retour");
        bottomPanel.add(buttonRetour);

        // Action du bouton de commande
        buttonCommander.addActionListener(e -> {
            // Créer une boîte de dialogue pour la confirmation de la commande
            JFrame confirmationDialog = new JFrame("Confirmation de demande");
            confirmationDialog.setSize(400, 300);
            confirmationDialog.setLocationRelativeTo(frame);
            confirmationDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            confirmationDialog.setResizable(false);

            // Créer un panneau pour les éléments de la boîte de dialogue
            JPanel confirmationPanel = new JPanel(new BorderLayout());
            confirmationDialog.add(confirmationPanel);

            // Créer un panneau pour les choix de médicaments confirmés
            JPanel choicesPanel = new JPanel(new GridLayout(0, 1)); // Utilisation d'une disposition en grille
            confirmationPanel.add(new JScrollPane(choicesPanel), BorderLayout.CENTER);

            // Créer une liste pour stocker les demandes de médicaments sélectionnés
            List<DemandeVersionGenerique> selectedDemandes = new ArrayList<>();

            // Ajouter les demandes de médicaments sélectionnés à la liste
            for (Map.Entry<Medicament, JCheckBox> entry : medicamentGenCheckBoxMap.entrySet()) {
                Medicament medicament = entry.getKey();
                JCheckBox checkBox = entry.getValue();
                if (checkBox.isSelected()) {
                    // Créer une demande pour le médicament sélectionné
                    DemandeVersionGenerique demande = new DemandeVersionGenerique(medicament.getNom(), checkBox.isSelected());
                    selectedDemandes.add(demande);

                    // Ajouter une étiquette pour le médicament sélectionné
                    JLabel choiceLabel = new JLabel(medicament.getNom());
                    choicesPanel.add(choiceLabel);
                }
            }

            // Écrire les demandes dans le fichier CSV
            EcritureRegistreDemandeVersionGeneriqueCsv.ecrireDemandesVersionGeneriqueCsv(selectedDemandes);

            // Créer un panneau pour les boutons de validation et d'annulation
            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            confirmationPanel.add(buttonsPanel, BorderLayout.SOUTH);

            // Ajouter un bouton pour valider la commande
            JButton validateButton = new JButton("Valider");
            buttonsPanel.add(validateButton);

            // Action du bouton de validation
            validateButton.addActionListener(validateEvent -> {
                // Construire le message de confirmation
                StringBuilder confirmationMessage = new StringBuilder("Les médicament(s) suivant(s) ont été demandé(s) en version générique :\n\n");

                // Ajouter les demandes de médicaments sélectionnés au message de confirmation
                for (DemandeVersionGenerique demande : selectedDemandes) {
                    confirmationMessage.append("- ").append(demande.getNomMedicament()).append("\n");
                }
                confirmationMessage.append("\nLa demande a été enregistrée avec succès.");

                // Afficher le message de confirmation
                JOptionPane.showMessageDialog(confirmationDialog, confirmationMessage.toString());

                // Fermer la boîte de dialogue de confirmation
                confirmationDialog.dispose();
            });

            // Ajouter un bouton pour annuler la commande
            JButton cancelButton = new JButton("Annuler");
            buttonsPanel.add(cancelButton);

            // Ajouter un écouteur pour le bouton d'annulation
            cancelButton.addActionListener(cancelEvent -> confirmationDialog.dispose()); // Fermer la boîte de dialogue en cas d'annulation

            // Afficher la boîte de dialogue de confirmation
            confirmationDialog.setVisible(true);
        });

        // Action du bouton de réinitialisation
        buttonReinitialiser.addActionListener(e -> {
            // Réinitialiser les champs de recherche
            searchField.setText("");
            // Réinitialiser les médicaments affichés
            afficherListeMedicamentsNonGenOnDemand(medicamentPanel);
        });

        // Action du bouton de retour
        buttonRetour.addActionListener(e -> {
            // Fermer la fenêtre de commande de médicaments
            frame.dispose();
        });

        // Afficher la fenêtre
        frame.setVisible(true);
    }

    private void afficherListeMedicamentsNonGenOnDemand(JPanel medicamentPanel) {
        // Nettoyer le panneau des médicaments avant d'ajouter de nouveaux éléments
        medicamentPanel.removeAll();

        // Récupérer la liste des médicaments non génériques
        List<Medicament> medicamentsNonGen = pharmacie.getMedicamentsNonGeneriques();

        // Parcourir la liste des médicaments non génériques
        for (Medicament medicament : medicamentsNonGen) {
            // Créer les composants pour afficher le médicament
            JPanel entryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JCheckBox checkBox = new JCheckBox();
            JLabel nameLabel = new JLabel(medicament.getNom());
            JLabel genLabel = new JLabel("Version générique demandée");
            genLabel.setForeground(Color.BLUE); // Mettre la couleur du label en bleu

            // Ajouter les éléments à medicamentGenCheckBoxMap
            medicamentGenCheckBoxMap.put(medicament, checkBox);

            // Ajouter le label une seule fois au panel
            entryPanel.add(checkBox);
            entryPanel.add(nameLabel);
            entryPanel.add(genLabel);

            // Ajouter un écouteur d'item pour chaque case à cocher pour activer/désactiver les labels
            checkBox.addItemListener(e -> {
                boolean isSelected = checkBox.isSelected();
                nameLabel.setEnabled(isSelected);
                genLabel.setVisible(isSelected); // Afficher ou cacher le label en fonction de l'état du checkbox
            });

            // Par défaut, les labels sont désactivés
            nameLabel.setEnabled(false);
            genLabel.setVisible(false); // Cacher le label par défaut

            // Encapsuler chaque médicament dans un panneau individuel
            JPanel medicineEntryPanel = new JPanel(new BorderLayout());
            medicineEntryPanel.add(entryPanel, BorderLayout.CENTER);
            medicamentPanel.add(medicineEntryPanel);
        }

        // Rafraîchir l'interface utilisateur pour refléter les changements
        medicamentPanel.revalidate();
        medicamentPanel.repaint();
    }

    private JCheckBox getjCheckBox(Medicament medicament, JPanel entryPanel) {
        JCheckBox halfDoseCheckBox = new JCheckBox("50% du mg");
        halfDoseCheckBox.addItemListener(e -> {
            if (halfDoseCheckBox.isSelected()) {
                halfDoseCheckBox.setText("mg à 50%");
                // Calculer la quantité de médicament nécessaire à 50% en mg
                String nomMedicament = medicament.getNom();
                String quantiteEnMg = extractQuantiteEnMg(nomMedicament);
                if (!quantiteEnMg.isEmpty()) {
                    int quantiteComplete = Integer.parseInt(quantiteEnMg.replace("mg", ""));
                    double prixUnitaireComplete = medicament.getPrix();
                    double prixUnitaire50Pourcent = prixUnitaireComplete / 2;
                    // Afficher les prix
                    System.out.println("Prix unitaire du médicament en mg complet : " + prixUnitaireComplete);
                    System.out.println("Prix unitaire du médicament en mg à 50% : " + prixUnitaire50Pourcent);
                    JLabel prixUnitaire50PourcentLabel = new JLabel("| Prix mg 50% : " + prixUnitaire50Pourcent + " €");
                    entryPanel.add(prixUnitaire50PourcentLabel);
                }
            } else {
                halfDoseCheckBox.setText("Qtt mg complète");
                // Supprimer le label du prix à 50% s'il existe
                removePrix50PourcentLabel(entryPanel);
            }
        });
        return halfDoseCheckBox;
    }

    private void removePrix50PourcentLabel(JPanel entryPanel) {
        Component[] components = entryPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel label) {
                if (label.getText().contains("| Prix mg 50%")) {
                    entryPanel.remove(label);
                    entryPanel.revalidate();
                    entryPanel.repaint();
                    break;
                }
            }
        }
    }

    private String extractQuantiteEnMg(String nomMedicament) {
        // Extraire la quantité en mg du nom du médicament
        String[] parts = nomMedicament.split(" ");
        for (String part : parts) {
            if (part.contains("mg")) {
                return part;
            }
        }
        return "";
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

    private void saveSpinnerStates() {
        spinnerValues.clear();
        for (Map.Entry<Medicament, JCheckBox> entry : medicamentCheckBoxMap.entrySet()) {
            Medicament medicament = entry.getKey();
            JCheckBox checkBox = entry.getValue();
            if (checkBox.isSelected()) {
                JSpinner spinner = spinnerMap.get(checkBox);
                spinnerValues.put(medicament, (int) spinner.getValue());
            }
        }
    }

    private void restoreSpinnerStates() {
        for (Map.Entry<Medicament, Integer> entry : spinnerValues.entrySet()) {
            Medicament medicament = entry.getKey();
            Integer value = entry.getValue();
            JCheckBox checkBox = medicamentCheckBoxMap.get(medicament);
            JSpinner spinner = spinnerMap.get(checkBox);
            if (checkBox.isSelected()) {
                spinner.setValue(value);
            }
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

    // Méthode pour authentifier le médecin
    private boolean authentifierMedecin(String id, String password) {
        String csvFilePath = "src/data/authidmedecin.csv";

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
        return false; // Authentification échouée
    }

    private void afficherMenuPharmacien() {
        SwingUtilities.invokeLater(() -> {
        // Créer une nouvelle fenêtre pour le menu du pharmacien
        JFrame menuPharmacienFrame = new JFrame("Menu Pharmacien");
        menuPharmacienFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuPharmacienFrame.setSize(600, 600);
        menuPharmacienFrame.setLocationRelativeTo(null);
        menuPharmacienFrame.setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        menuPharmacienFrame.setJMenuBar(menuBar);

        // Créer un conteneur pour les panneaux de contenu
        JPanel contentPanel = new JPanel(new CardLayout());
        menuPharmacienFrame.add(contentPanel);

        // Création des panneaux pour afficher les informations
        JPanel panelAffichageMedicaments = new JPanel(new BorderLayout());
        contentPanel.add(panelAffichageMedicaments, "Medicaments");
        JPanel panelAffichageDemandeMedicamentsGeneriques = new JPanel(new BorderLayout());
        contentPanel.add(panelAffichageDemandeMedicamentsGeneriques, "Demandes");
        JPanel panelAffichageRegistrePreparation = new JPanel(new BorderLayout());
        contentPanel.add(panelAffichageRegistrePreparation, "Registre");
        JPanel panelAffichageListePatients = new JPanel(new BorderLayout());
        contentPanel.add(panelAffichageListePatients, "Patients");
        JPanel panelAffichageListeMedecins = new JPanel(new BorderLayout());
        contentPanel.add(panelAffichageListeMedecins, "Medecins");

        // Créer un menu pour les actions d'affichage de données
        JMenu menuActionsAffichage = new JMenu("Affichage de données");
        menuBar.add(menuActionsAffichage);
        JMenuItem menuItemAfficherMedicaments = new JMenuItem("Afficher les médicaments");
        menuActionsAffichage.add(menuItemAfficherMedicaments);
        JMenuItem menuItemAfficherDemandeMedicamentsGeneriques = new JMenuItem("Aff. demandes médicaments gen");
        menuActionsAffichage.add(menuItemAfficherDemandeMedicamentsGeneriques);
        JMenuItem menuItemAfficherRegistrePreparation = new JMenuItem("Aff. registre prep. médicaments (ordonnances)");
        menuActionsAffichage.add(menuItemAfficherRegistrePreparation);
        JMenuItem menuItemAfficherListePatients = new JMenuItem("Aff. patients");
        menuActionsAffichage.add(menuItemAfficherListePatients);
        JMenuItem menuItemAfficherListeMedecins = new JMenuItem("Aff. médecins");
        menuActionsAffichage.add(menuItemAfficherListeMedecins);

        // Ajouter les actions correspondantes aux éléments de menu

        // Action pour afficher les médicaments
        menuItemAfficherMedicaments.addActionListener(e -> {
            // Appeler la méthode pour afficher les médicaments dans le panneau de la fenêtre
            afficherMedicaments(panelAffichageMedicaments);
        });

        // Action pour afficher les demandes de médicaments génériques
        menuItemAfficherDemandeMedicamentsGeneriques.addActionListener(e -> {
            // Appeler la méthode pour afficher les demandes de médicaments génériques dans le panneau de la fenêtre
            afficherDemandeMedicamentsGeneriques(panelAffichageDemandeMedicamentsGeneriques);
        });

        // Action pour afficher le registre de préparation
        menuItemAfficherRegistrePreparation.addActionListener(e -> {
            // Appeler la méthode pour afficher le registre de préparation dans le panneau de la fenêtre
            try {
                afficherRegistrePreparation(panelAffichageRegistrePreparation);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Action pour afficher la liste des patients
        menuItemAfficherListePatients.addActionListener(e -> {
            // Appeler la méthode pour afficher la liste des patients dans le panneau de la fenêtre
            afficherListePatients(panelAffichageListePatients);
        });

        // Action pour afficher la liste des médecins
        menuItemAfficherListeMedecins.addActionListener(e -> {
            // Appeler la méthode pour afficher la liste des médecins dans le panneau de la fenêtre
            afficherListeMedecins(panelAffichageListeMedecins);
        });

        // Créer un menu pour les actions du pharmacien
        JMenu menuActions = new JMenu("Gest. Stock Médicaments");
        menuBar.add(menuActions);
        JMenuItem menuItemAjouterMedicament = new JMenuItem("Ajouter un médicament");
        menuActions.add(menuItemAjouterMedicament);
        menuItemAjouterMedicament.addActionListener(e -> {
            // Appeler la méthode pour ajouter un médicament
            try {
                ajouterMedicament();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JMenuItem menuItemModifMed = new JMenuItem("Modifier un médicament");
        menuActions.add(menuItemModifMed);
        JMenuItem menuItemRetirerMedicament = new JMenuItem("Retirer un médicament");
        menuActions.add(menuItemRetirerMedicament);
        JMenuItem menuItemCommanderPreparationGeneriqueDemandeVersionGenerique = new JMenuItem("Ajouter un médicament de marque en version générique");
        menuActions.add(menuItemCommanderPreparationGeneriqueDemandeVersionGenerique);

        // Créer un menu pour les actions du pharmacien
        JMenu menuActionsGestionDesPersonnes = new JMenu("Gest. Personnes");
        menuBar.add(menuActionsGestionDesPersonnes);
        JMenuItem menuItemAjouterPatient = new JMenuItem("Ajouter un patient");
        menuActionsGestionDesPersonnes.add(menuItemAjouterPatient);
        JMenuItem menuItemSuppPatient = new JMenuItem("Supprimer un patient");
        menuActionsGestionDesPersonnes.add(menuItemSuppPatient);
        JMenuItem menuItemAjouterMedecin = new JMenuItem("Ajouter un médecin");
        menuActionsGestionDesPersonnes.add(menuItemAjouterMedecin);
        JMenuItem menuItemSuppMedecin = new JMenuItem("Supprimer un médecin");
        menuActionsGestionDesPersonnes.add(menuItemSuppMedecin);
        JMenuItem menuItemAjouterPharmacien = new JMenuItem("Ajouter un pharmacien");
        menuActionsGestionDesPersonnes.add(menuItemAjouterPharmacien);
        JMenuItem menuItemSuppPharmacien = new JMenuItem("Supprimer un pharmacien");
        menuActionsGestionDesPersonnes.add(menuItemSuppPharmacien);

        // Ajoutez d'autres boutons au panneau du menu si nécessaire

        // Afficher la fenêtre du menu du pharmacien
        menuPharmacienFrame.setVisible(true);
        });
    }

    // Afficher les medicament de src/data/medicaments.csv
    private void afficherMedicaments(JPanel panel) {
        // Créer une fenêtre pour afficher les médicaments
        JFrame frame = new JFrame("Liste des médicaments");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Création d'un panneau pour afficher les médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médicament au panneau (ajouté un label "en rupture de stock" en rouge devant les médicaments avec une quantité en stock inférieure à 20 unités)
        for (Medicament medicament : pharmacie.getMedicaments()) {
            JLabel label = new JLabel(medicament.getNom() + " - " + medicament.getQuantiteEnStock() + " unité(s)");
            if (medicament.getQuantiteEnStock() < 20) {
                label.setForeground(Color.RED); // Mettre la couleur du label en rouge
                label.setText("En rupture de stock - " + medicament.getNom() + " - " + medicament.getQuantiteEnStock() + " unité(s)");
            }
            medicamentsPanel.add(label);
        }

        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    // Afficher les medicament de src/data/listeMedecins.csv
    private void afficherListeMedecins(JPanel panel) {
        // Créer une fenêtre pour afficher la liste des médecins
        JFrame frame = new JFrame("Liste des médecins");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Création d'un panneau pour afficher la liste des médecins
        JPanel medecinPanel = new JPanel();
        medecinPanel.setLayout(new BoxLayout(medecinPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médecin au panneau
        for (Medecin medecin : pharmacie.getMedecins()) {
            JLabel label = new JLabel(medecin.getPrenom() + " " + medecin.getNom() + " - " + medecin.getReference());
            medecinPanel.add(label);
        }

        // Ajouter le panneau des médecins au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medecinPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    // Afficher les medicaments dans src/data/listePatients.csv
    private void afficherListePatients(JPanel panel) {
        // Créer une fenêtre pour afficher la liste des patients
        JFrame frame = new JFrame("Liste des patients");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Création d'un panneau pour afficher la liste des patients
        JPanel patientPanel = new JPanel();
        patientPanel.setLayout(new BoxLayout(patientPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque patient au panneau
        for (Patient patient : pharmacie.getPatients()) {
            JLabel label = new JLabel(patient.getPrenom() + " " + patient.getNom() + " - " + patient.getReference());
            patientPanel.add(label);
        }

        // Ajouter le panneau des patients au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(patientPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    // Afficher le registre de préparation en utilisant la classe LectureRegistrePreparation
    private void afficherRegistrePreparation(JPanel panel) throws IOException {
        // Créer une fenêtre pour afficher le registre de préparation
        JFrame frame = new JFrame("Registre de préparation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Création d'un panneau pour afficher les médicaments préparés
        JPanel preparationPanel = new JPanel();
        preparationPanel.setLayout(new BoxLayout(preparationPanel, BoxLayout.Y_AXIS));

        // Lire le registre de préparation
        String preparations = LectureRegistrePreparation.lireRegistrePreparation("src/data/registrepreparation.csv");

        // Ajouter les détails de chaque préparation commandée dans le panneau
        for (String preparation : preparations.split("\n")) {
            JLabel label = new JLabel(preparation);
            preparationPanel.add(label);
        }

        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(preparationPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    // Afficher les medicament de src/data/demandes_version_generique.csv
    private void afficherDemandeMedicamentsGeneriques(JPanel panel) {
        // Créer une fenêtre pour afficher les demandes de médicaments génériques
        JFrame frame = new JFrame("Demandes de médicaments génériques");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Création d'un panneau pour afficher les médicaments en demande de version générique
        JPanel demandePanel = new JPanel();
        demandePanel.setLayout(new BoxLayout(demandePanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque demande au panneau
        for (DemandeVersionGenerique demande : pharmacie.getDemandesVersionGenerique()) {
            JLabel label = new JLabel(demande.getNomMedicament() + " - Version générique demandée : " + demande.isVersionGeneriqueDemandee());
            demandePanel.add(label);
        }

        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(demandePanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
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
                        String[] medicaments = parts[3].split(";");
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
            if (checkBox.isSelected()) {
                boolean correspondant = false;
                for (String medicamentPrescrit : medicamentsPrescrits) {
                    // Vérifier si le nom du médicament sélectionné correspond à un médicament prescrit
                    if (medicamentPrescrit.equalsIgnoreCase(medicament.getNom())) {
                        correspondant = true;
                        break;
                    }
                }
                // Si aucun médicament prescrit ne correspond, retourner false
                if (!correspondant) {
                    return false;
                }
            }
        }
        // Si tous les médicaments sélectionnés correspondent à ceux prescrits, retourner true
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
            JLabel label = getjLabel(medicament);
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
                JLabel label = getjLabel(medicament);
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

        // Ajouter les détails de chaque médicament non générique au panneau
        for (Medicament medicament : medicaments) {
            if (!medicament.isGenerique()) {
                JLabel label = getjLabel(medicament);
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
                JLabel label = getjLabel(medicament);
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
                JLabel label = getjLabel(medicament);
                medicamentsPanel.add(label);
            }
        }
        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    // Ajouter médicament dans le src/data/medicaments.csv
    private void ajouterMedicament() throws IOException {
        // Créer une boîte de dialogue pour saisir les détails du médicament
        JDialog dialog = new JDialog();
        dialog.setTitle("Ajouter un médicament");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        // Créer un panneau pour les champs de saisie
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        dialog.add(inputPanel, BorderLayout.CENTER);

        // Ajouter des champs de saisie pour le nom, le prix, la quantité en stock et le type du médicament
        JTextField nomField = new JTextField();
        JTextField prixField = new JTextField();
        JComboBox<ETypeMedicament> typeComboBox = new JComboBox<>(ETypeMedicament.values());
        Checkbox generiqueCheckBox = new Checkbox("Générique");
        JTextField quantiteField = new JTextField();


        inputPanel.add(new JLabel("Nom :"));
        inputPanel.add(nomField);

        inputPanel.add(new JLabel("Prix :"));
        inputPanel.add(prixField);

        inputPanel.add(new JLabel("Type :"));
        inputPanel.add(typeComboBox);

        inputPanel.add(new JLabel("Générique :"));
        inputPanel.add(generiqueCheckBox);

        inputPanel.add(new JLabel("Quantité en stock :"));
        inputPanel.add(quantiteField);

        //Créer un conteneur pour les boutons Ajouter, Reinitaliser et Quitter
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        dialog.add(buttonsPanel, BorderLayout.SOUTH);

        // Créer un bouton pour ajouter le médicament
        JButton addButton = new JButton("Ajouter");
        buttonsPanel.add(addButton);

        // Créer un bouton pour réinitialiser les champs
        JButton resetButton = new JButton("Réinitialiser");
        buttonsPanel.add(resetButton);

        // Créer un bouton pour fermer la boîte de dialogue
        JButton closeButton = new JButton("Fermer");
        buttonsPanel.add(closeButton);

        // Action du bouton Ajouter
        addButton.addActionListener(e -> {
            // Récupérer les valeurs saisies par l'utilisateur
            String nom = nomField.getText();
            double prix = Double.parseDouble(prixField.getText());
            ETypeMedicament type = (ETypeMedicament) typeComboBox.getSelectedItem();
            boolean generique = generiqueCheckBox.getState();
            int quantite = Integer.parseInt(quantiteField.getText());


            // Créer un nouveau médicament avec les valeurs saisies
            Medicament medicament = new Medicament(nom, prix, type, generique, quantite);

            // Ajouter le médicament à la liste des médicaments de la pharmacie
            pharmacie.ajouterMedicament(medicament);

            // Mettre à jour le fichier CSV des médicaments
            try {
                EcritureMedicamentsCsv.ecrireAjoutDeMedicamentCsv("src/data/medicaments.csv", medicament);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Afficher un message de confirmation
            JOptionPane.showMessageDialog(dialog, "Le médicament a été ajouté avec succès.");

            // Fermer la boîte de dialogue
            dialog.dispose();
        });

        // Action du bouton Réinitialiser
        resetButton.addActionListener(e -> {
            // Réinitialiser les champs de saisie
            nomField.setText("");
            prixField.setText("");
            typeComboBox.setSelectedIndex(0);
            generiqueCheckBox.setState(false);
            quantiteField.setText("");

        });

        // Action du bouton Fermer
        closeButton.addActionListener(e -> {
            // Fermer la boîte de dialogue
            dialog.dispose();
        });

        // Afficher la boîte de dialogue
        dialog.setVisible(true);
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
