package ui;

import exceptions.ExeptionRuptureDeStock;
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
    private static final String CSV_FILE_PATH = "src/data/medicaments.csv";
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
        tabbedPane.addTab("Médicaments", panelAfficherMedicaments);

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

        // Créer un panneau pour le bouton de recherche
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

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

            // Ajouter un écouteur pour le bouton de validation
            validateButton.addActionListener(validateEvent -> {
                // Traiter la commande ici
                for (Map.Entry<Object, Object> entry : checkBoxSpinnerMap.entrySet()) {
                    JCheckBox checkBox = (JCheckBox) entry.getKey();
                    int quantite = (int) ((JSpinner) entry.getValue()).getValue();
                    String nomMedicament = checkBox.getText();
                    Medicament medicament = pharmacie.trouverMedicament(nomMedicament);
                    if (checkBox.isSelected()) {
                        if (medicament != null) {
                            try {
                                pharmacie.dispense(medicament, quantite);
                            } catch (ExeptionRuptureDeStock ex) {
                                // Gérer l'exception de rupture de stock ici
                                ex.printStackTrace();
                            }
                        } else {
                            // Afficher un message si le médicament n'est pas trouvé
                            JOptionPane.showMessageDialog(dialogFrame, "Le médicament " + nomMedicament + " n'existe pas dans la pharmacie.");
                        }
                    }
                }
                JOptionPane.showMessageDialog(dialogFrame, "La commande a été passée avec succès !");
                dialogFrame.dispose(); // Fermer la boîte de dialogue après la validation de la commande
            });

            // Ajouter un bouton pour annuler la commande
            JButton cancelButton = new JButton("Annuler");
            buttonsPanel.add(cancelButton);

            // Ajouter un écouteur pour le bouton d'annulation
            cancelButton.addActionListener(cancelEvent -> dialogFrame.dispose()); // Fermer la boîte de dialogue en cas d'annulation

            // Afficher la boîte de dialogue
            dialogFrame.setVisible(true);
        });

        // Afficher la fenêtre
        frame.setVisible(true);
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
