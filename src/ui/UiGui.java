package ui;

import enums.ETypeMedicament;
import interfaces.IDocuments;
import io.EcritureMedicamentsCsv;
import io.EcritureOrdonnancesCsv;
import io.EcritureRegistreDemandeVersionGeneriqueCsv;
import io.EcritureRegistrePreparationCsv;
import io.LectureMedecinCsv;
import io.LectureMedicamentsCsv;
import io.LectureOrdonnanceCsv;
import io.LectureRegistrePreparation;
import pharmacie.*;
import utils.DateUtlis;

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
    public static Pharmacie pharmacie = new Pharmacie();
    private final JPanel panelAfficherMedicaments;
    public static HashMap<Medicament, JCheckBox> medicamentCheckBoxMap = new HashMap<Medicament, JCheckBox>();
    public static HashMap<Medicament, JCheckBox> medicamentGenCheckBoxMap = new HashMap<>();
    public static HashMap<JCheckBox, JSpinner> spinnerMap = new HashMap<>();
    public static Map<Medicament, Boolean> selectedMedicamentStates = new HashMap<>();
    private final Map<JCheckBox, Object> spinnerStates = new HashMap<>();
    public static Map<Medicament, Integer> spinnerValues = new HashMap<>();
    private final List<Medicament> selectedMedicaments;
    private final List<Integer> selectedQuantities;
    /**
     * Constructeur de l'interface graphique
     *
     * @param pharmacie             La pharmacie à gérer
     * @param medicamentCheckBoxMap La map pour stocker les médicaments et les cases à cocher
     * @param spinnerMap            La map pour stocker les cases à cocher et les spinners
     * @throws IOException Si une erreur d'entrée/sortie se produit
     */

    public UiGui(Pharmacie pharmacie, HashMap<Medicament, JCheckBox> medicamentCheckBoxMap, HashMap<JCheckBox, JSpinner> spinnerMap, HashMap<Medicament, JCheckBox> medicamentGenCheckBoxMap, List<Medicament> selectedMedicaments, List<Integer> selectedQuantities) throws IOException {
        UiGui.pharmacie = pharmacie;
        UiGui.medicamentGenCheckBoxMap = medicamentGenCheckBoxMap;
        UiGui.medicamentCheckBoxMap = medicamentCheckBoxMap;
        UiGui.spinnerMap = spinnerMap;
        this.selectedMedicaments = selectedMedicaments;
        this.selectedQuantities = selectedQuantities;
        this.setTitle("Pharmacie Version Alpha 0.0");
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
            DemandeVersionGenerique.commanderMedicamentVersionGenerique();
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
        IDocuments.afficherListeMedicamentsGen(panelAfficherMedicamentsGenerique);

        // Afficher les médicaments Non Génériques dans le panneau correspondant
        IDocuments.afficherListeMedicamentsNonGen(panelAfficherMedicamentsNonGenerique);

        // Afficher les médicaments sur ordonnance dans le panneau correspondant
        IDocuments.afficherListeMedicamentsSurOrdonnance(panelMedSurOrdonnance);

        // Afficher les médicaments en vente libre dans le panneau correspondant
        IDocuments.afficherListeMedicamentsEnVenteLibre(panelMedVenteLibre);

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
            Pharmacien.accesMenuPharmacien();
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
                IDocuments.afficherListeMedicaments(panelAfficherMedicaments, suggestions);
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
            if (LectureOrdonnanceCsv.ordonnanceDisponible(referenceOrdonnance)) {
                JOptionPane.showMessageDialog(this, "L'ordonnance existe déjà.");
            } else {
                Ordonnance.enregistrerOrdonnance();
            }
        });
        return menuEnregistrerOrdonnance;
    }

    public static JLabel getjLabel(Medicament medicament) {
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
                IDocuments.saveSelectedMedicamentStates();
                IDocuments.saveSpinnerStates();

                // Effectuer la recherche et afficher les médicaments filtrés
                String search = searchField.getText();
                List<Medicament> filteredMedicaments = pharmacie.filterMedicaments(search);
                afficherMedicaments(filteredMedicaments);

                // Restaurer les états des éléments après l'affichage des médicaments filtrés
                IDocuments.restoreSelectedMedicamentStates();
                IDocuments.restoreSpinnerStates();
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
            if (!LectureOrdonnanceCsv.ordonnanceDisponible(referenceOrdonnance)) {
                JOptionPane.showMessageDialog(frame, "Ordonnance introuvable. Veuillez vérifier la référence de l'ordonnance.");
            } else {
                // L'ordonnance est disponible, procéder à la validation de la commande
                // Récupérer la liste des médicaments prescrits dans l'ordonnance
                List<String> medicamentsPrescrits = LectureMedicamentsCsv.getMedicamentsPrescrits(referenceOrdonnance);

                // Vérifier si les médicaments sélectionnés correspondent à ceux prescrits
                if (!Medicament.medicamentsCorrespondants(medicamentsPrescrits)) {
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
                                Calendar dateLivraison = DateUtlis.calculerDateLivraison();

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

    private JCheckBox getjCheckBox(Medicament medicament, JPanel entryPanel) {
        JCheckBox halfDoseCheckBox = new JCheckBox("50% du mg");
        halfDoseCheckBox.addItemListener(e -> {
            if (halfDoseCheckBox.isSelected()) {
                halfDoseCheckBox.setText("mg à 50%");
                // Calculer la quantité de médicament nécessaire à 50% en mg
                String nomMedicament = medicament.getNom();
                String quantiteEnMg = IDocuments.extractQuantiteEnMg(nomMedicament);
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
                IDocuments.removePrix50PourcentLabel(entryPanel);
            }
        });
        return halfDoseCheckBox;
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
                IDocuments.afficherListeMedicaments(panelAfficherMedicaments);
                break;
        }
    }
}
