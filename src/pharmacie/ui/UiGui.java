package pharmacie.ui;

import pharmacie.entites.Medicament;
import pharmacie.entites.Preparation;
import pharmacie.generiques.MedicamentGenerique;
import pharmacie.nongeneriques.MedicamentNonGenerique;
import pharmacie.personnes.Medecin;
import pharmacie.personnes.Patient;
import pharmacie.entites.Pharmacie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

public class UiGui extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final String TITRE_APPLICATION = "Pharmacie";
    private static final int LARGEUR_APPLICATION = 800;
    private static final int HAUTEUR_APPLICATION = 600;

    private Pharmacie pharmacie;

    public UiGui(Pharmacie pharmacie) {
        super(TITRE_APPLICATION);
        this.pharmacie = pharmacie;
        setSize(LARGEUR_APPLICATION, HAUTEUR_APPLICATION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        // Ajout du menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuActionAdminMenu = new JMenu("Administrateur");
        JMenuItem ajouterMedicamentItem = new JMenuItem("Ajouter un médicament");
        ajouterMedicamentItem.addActionListener(this);
        menuActionAdminMenu.add(ajouterMedicamentItem);
        JMenuItem modifierMedicamentItem = new JMenuItem("Modifier un médicament");
        modifierMedicamentItem.addActionListener(this);
        menuActionAdminMenu.add(modifierMedicamentItem);
        JMenuItem supprimerMedicamentItem = new JMenuItem("Supprimer un médicament");
        supprimerMedicamentItem.addActionListener(this);
        menuActionAdminMenu.add(supprimerMedicamentItem);
        JMenuItem listerMedicamentsItem = new JMenuItem("Lister les médicaments");
        listerMedicamentsItem.addActionListener(this);
        menuActionAdminMenu.add(listerMedicamentsItem);
        JMenuItem listerMedecinsItem = new JMenuItem("Lister les médecins");
        listerMedecinsItem.addActionListener(this);
        menuActionAdminMenu.add(listerMedecinsItem);
        JMenuItem listerPatientsItem = new JMenuItem("Lister les patients");
        listerPatientsItem.addActionListener(this);
        menuActionAdminMenu.add(listerPatientsItem);
        JMenuItem listerPreparationsItem = new JMenuItem("Lister les préparations");
        listerPreparationsItem.addActionListener(this);
        menuActionAdminMenu.add(listerPreparationsItem);
        JMenuItem itemQuitter = new JMenuItem("Quitter");
        itemQuitter.addActionListener(this);
        menuActionAdminMenu.add(itemQuitter);
        menuBar.add(menuActionAdminMenu);

        JMenu menuActionPharmacienMenu = new JMenu("Pharmacien");
        JMenuItem creerPreparationItem = new JMenuItem("Créer une préparation");
        creerPreparationItem.addActionListener(this);
        menuActionPharmacienMenu.add(creerPreparationItem);
        JMenuItem modifierPreparationItem = new JMenuItem("Modifier une préparation");
        modifierPreparationItem.addActionListener(this);
        menuActionPharmacienMenu.add(modifierPreparationItem);
        JMenuItem listerPreparationsPharmacienItem = new JMenuItem("Lister les préparations");
        listerPreparationsPharmacienItem.addActionListener(this);
        menuActionPharmacienMenu.add(listerPreparationsPharmacienItem);
        JMenuItem supprimerPreparationItem = new JMenuItem("Supprimer une préparation");
        supprimerPreparationItem.addActionListener(this);
        menuActionPharmacienMenu.add(supprimerPreparationItem);
        menuBar.add(menuActionPharmacienMenu);

        JMenu menuActionClientMenu = new JMenu("Client");
        JMenuItem rechercheMedicatemtItem = new JMenuItem("Rechercher un médicament");
        rechercheMedicatemtItem.addActionListener(this);
        menuActionClientMenu.add(rechercheMedicatemtItem);
        JMenuItem commandePreparation = new JMenuItem("Commander une préparation");
        commandePreparation.addActionListener(this);
        menuActionClientMenu.add(commandePreparation);
        JMenuItem enregistrerDemandeMedicamentGen = new JMenuItem("Enregistrer une demande de médicament générique");
        enregistrerDemandeMedicamentGen.addActionListener(this);
        menuActionClientMenu.add(enregistrerDemandeMedicamentGen);
        menuBar.add(menuActionClientMenu);

        setJMenuBar(menuBar);

        // Ajout du panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Création des panneaux pour afficher les différentes fonctionnalités
        JPanel medicamentsPanel = new JPanel(new BorderLayout());
        JPanel medecinsPanel = new JPanel(new BorderLayout());
        JPanel patientsPanel = new JPanel(new BorderLayout());
        JPanel medicamentsNonGeneriquesPanel = new JPanel(new BorderLayout());
        JPanel medicamentsGeneriquesPanel = new JPanel(new BorderLayout());
        JPanel preparationsPanel = new JPanel(new BorderLayout());

        // Ajout des panneaux à l'interface
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Médicaments", medicamentsPanel);
        tabbedPane.addTab("Médicaments non génériques", medicamentsNonGeneriquesPanel);
        tabbedPane.addTab("Médicaments génériques", medicamentsGeneriquesPanel);
        tabbedPane.addTab("Médecins", medecinsPanel);
        tabbedPane.addTab("Patients", patientsPanel);
        tabbedPane.addTab("Préparations", preparationsPanel);
        add(tabbedPane, BorderLayout.CENTER);

        // Affichage des listes correspondantes à chaque fonctionnalité
        afficherListeMedicaments(medicamentsPanel);
        afficherListeMedecins(medecinsPanel);
        afficherListePatients(patientsPanel);
        afficherListeMedicamentsNonGeneriques(medicamentsNonGeneriquesPanel);
        afficherListeMedicamentsGeneriques(medicamentsGeneriquesPanel);
        afficherListePreparations(preparationsPanel);

        // Ajout des boutons
        JButton buttonQuitter = new JButton("Quitter");
        JButton buttonTest = new JButton("Test");
        buttonQuitter.addActionListener(this);
        buttonTest.addActionListener(this);

        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoutons.add(buttonTest);
        panelBoutons.add(buttonQuitter);
        add(panelBoutons, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem) e.getSource();
        String texte = item.getText();
        if (texte.equals("Quitter")) {
            System.exit(0);
        } else if (texte.equals("Test")) {
            JOptionPane.showMessageDialog(this, "Test réussi !");
        } else if (texte.equals("Créer une préparation")) {
            creerPreparation();
        } else if (texte.equals("Modifier une préparation")) {
            modifierPreparation();
        } else if (texte.equals("Lister les préparations")) {
            listerPreparations();
        } else if (texte.equals("Supprimer une préparation")) {
            supprimerPreparation();
        } else if (texte.equals("Rechercher un médicament")) {
            rechercherMedicament();
        } else if (texte.equals("Commander une préparation")) {
            commanderPreparation();
        } else if (texte.equals("Enregistrer une demande de médicament générique")) {
            enregistrerDemandeMedicamentGen();
        } else if (texte.equals("Ajouter un médicament")) {
            ajouterMedicament();
        } else if (texte.equals("Modifier un médicament")) {
            modifierMedicament();
        } else if (texte.equals("Supprimer un médicament")) {
            supprimerMedicament();
        } else if (texte.equals("Lister les médicaments")) {
            listerMedicaments();
        } else if (texte.equals("Lister les médicaments non génériques")) {
            listerMedicamentsNonGeneriques();
        } else if (texte.equals("Lister les médicaments génériques")) {
            listerMedicamentsGeneriques();
        } else if (texte.equals("Ajouter un médecin")) {
            ajouterMedecin();
        } else if (texte.equals("Modifier un médecin")) {
            modifierMedecin();
        } else if (texte.equals("Supprimer un médecin")) {
            supprimerMedecin();
        } else if (texte.equals("Lister les médecins")) {
            listerMedecins();
        } else if (texte.equals("Ajouter un patient")) {
            ajouterPatient();
        } else if (texte.equals("Modifier un patient")) {
            modifierPatient();
        } else if (texte.equals("Supprimer un patient")) {
            supprimerPatient();
        } else if (texte.equals("Lister les patients")) {
            listerPatients();
        } else if (texte.equals("Ajouter une préparation")) {
            ajouterPreparation();
        } else if (texte.equals("Modifier une préparation")) {
            modifierPreparation();
        } else if (texte.equals("Supprimer une préparation")) {
            supprimerPreparation();
        } else if (texte.equals("Lister les préparations")) {
            listerPreparations();
        }
    }

    private void listerMedicaments() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Liste des médicaments"), BorderLayout.NORTH);
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        List<Medicament> medicaments = pharmacie.getMedicaments();
        if (medicaments.isEmpty()) {
            textArea.setText("Aucun médicament disponible pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des médicaments :\n");
            for (Medicament medicament : medicaments) {
                sb.append("- ").append(medicament.getNom()).append(" (").append(medicament.getQuantiteEnStock()).append(" en stock)\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        JOptionPane.showMessageDialog(this, panel, "Liste des médicaments", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listerMedicamentsNonGeneriques() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Liste des médicaments non génériques"), BorderLayout.NORTH);
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        List<MedicamentNonGenerique> medicamentsNonGeneriques = pharmacie.getMedicamentsNonGeneriques();
        if (medicamentsNonGeneriques.isEmpty()) {
            textArea.setText("Aucun médicament non générique disponible pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des médicaments non génériques :\n");
            for (MedicamentNonGenerique medicamentNonGenerique : medicamentsNonGeneriques) {
                sb.append("- ").append(medicamentNonGenerique.getNom()).append(" (").append(medicamentNonGenerique.getQuantiteEnStock()).append(" en stock)\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        JOptionPane.showMessageDialog(this, panel, "Liste des médicaments non génériques", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listerMedicamentsGeneriques() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Liste des médicaments génériques"), BorderLayout.NORTH);
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        List<MedicamentGenerique> medicamentsGeneriques = pharmacie.getMedicamentsGeneriques();
        if (medicamentsGeneriques.isEmpty()) {
            textArea.setText("Aucun médicament générique disponible pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des médicaments génériques :\n");
            for (MedicamentGenerique medicamentGenerique : medicamentsGeneriques) {
                sb.append("- ").append(medicamentGenerique.getNom()).append(" (").append(medicamentGenerique.getQuantiteEnStock()).append(" en stock)\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        JOptionPane.showMessageDialog(this, panel, "Liste des médicaments génériques", JOptionPane.INFORMATION_MESSAGE);
    }

    private void ajouterMedecin() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Ajouter un médecin"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom du médecin :"), BorderLayout.WEST);
        JTextField nomMedecinField = new JTextField();
        panel.add(nomMedecinField, BorderLayout.CENTER);
        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(e -> {
            String nomMedecin = nomMedecinField.getText();
            // Créer le médecin
            Medecin medecin = new Medecin(nomMedecin);
            pharmacie.ajouterMedecin(medecin);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Médecin ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void modifierMedecin() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Modifier un médecin"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom du médecin :"), BorderLayout.WEST);
        JTextField nomMedecinField = new JTextField();
        panel.add(nomMedecinField, BorderLayout.CENTER);
        JButton modifierButton = new JButton("Modifier");
        modifierButton.addActionListener(e -> {
            String nomMedecin = nomMedecinField.getText();
            // Modifier le médecin
            pharmacie.modifierMedecin(nomMedecin);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Médecin modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void supprimerMedecin() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Supprimer un médecin"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom du médecin :"), BorderLayout.WEST);
        JTextField nomMedecinField = new JTextField();
        panel.add(nomMedecinField, BorderLayout.CENTER);
        JButton supprimerButton = new JButton("Supprimer");
        supprimerButton.addActionListener(e -> {
            String nomMedecin = nomMedecinField.getText();
            // Supprimer le médecin
            pharmacie.supprimerMedecin(nomMedecin);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Médecin supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void listerMedecins() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Liste des médecins"), BorderLayout.NORTH);
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        List<Medecin> medecins = pharmacie.getMedecins();
        if (medecins.isEmpty()) {
            textArea.setText("Aucun médecin enregistré pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des médecins :\n");
            for (Medecin medecin : medecins) {
                sb.append("- ").append(medecin.getNom()).append("\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        JOptionPane.showMessageDialog(this, panel, "Liste des médecins", JOptionPane.INFORMATION_MESSAGE);
    }

    private void ajouterPatient() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Ajouter un patient"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom du patient :"), BorderLayout.WEST);
        JTextField nomPatientField = new JTextField();
        panel.add(nomPatientField, BorderLayout.CENTER);
        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(e -> {
            String nomPatient = nomPatientField.getText();
            // Créer le patient
            Patient patient = new Patient(nomPatient);
            pharmacie.ajouterPatient(patient);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Patient ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void modifierPatient() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Modifier un patient"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom du patient :"), BorderLayout.WEST);
        JTextField nomPatientField = new JTextField();
        panel.add(nomPatientField, BorderLayout.CENTER);
        JButton modifierButton = new JButton("Modifier");
        modifierButton.addActionListener(e -> {
            String nomPatient = nomPatientField.getText();
            // Modifier le patient
            pharmacie.modifierPatient(nomPatient);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Patient modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void ajouterPreparation() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Ajouter une préparation"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom de la préparation :"), BorderLayout.WEST);
        JTextField nomPreparationField = new JTextField();
        panel.add(nomPreparationField, BorderLayout.CENTER);
        panel.add(new JLabel("Ingrédients :"), BorderLayout.WEST);
        JTextArea ingredientsField = new JTextArea();
        panel.add(ingredientsField, BorderLayout.CENTER);
        panel.add(new JLabel("Quantité :"), BorderLayout.WEST);
        JTextField quantiteField = new JTextField();
        panel.add(quantiteField, BorderLayout.CENTER);
        panel.add(new JLabel("Prix :"), BorderLayout.WEST);
        JTextField prixField = new JTextField();
        panel.add(prixField, BorderLayout.CENTER);
        panel.add(new JLabel("Description :"), BorderLayout.WEST);
        JTextArea descriptionField = new JTextArea();
        panel.add(descriptionField, BorderLayout.CENTER);
        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(e -> {
            String nomPreparation = nomPreparationField.getText();
            String ingredients = ingredientsField.getText();
            String quantite = quantiteField.getText();
            String prix = prixField.getText();
            String description = descriptionField.getText();
            // Créer la préparation
            Preparation preparation = new Preparation(nomPreparation, ingredients, quantite, prix, description);
            pharmacie.ajouterPreparation(preparation);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Préparation ajoutée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void supprimerPatient() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Supprimer un patient"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom du patient :"), BorderLayout.WEST);
        JTextField nomPatientField = new JTextField();
        panel.add(nomPatientField, BorderLayout.CENTER);
        JButton supprimerButton = new JButton("Supprimer");
        supprimerButton.addActionListener(e -> {
            String nomPatient = nomPatientField.getText();
            // Supprimer le patient
            pharmacie.supprimerPatient(nomPatient);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Patient supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void listerPatients() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Liste des patients"), BorderLayout.NORTH);
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        List<Patient> patients = pharmacie.getPatients();
        if (patients.isEmpty()) {
            textArea.setText("Aucun patient enregistré pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des patients :\n");
            for (Patient patient : patients) {
                sb.append("- ").append(patient.getNom()).append("\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        JOptionPane.showMessageDialog(this, panel, "Liste des patients", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listerPreparations() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Liste des préparations"), BorderLayout.NORTH);
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        List<Preparation> preparations = pharmacie.getPreparations();
        if (preparations.isEmpty()) {
            textArea.setText("Aucune préparation disponible pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des préparations :\n");
            for (Preparation preparation : preparations) {
                sb.append("- ").append(preparation.getNom()).append("\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        JOptionPane.showMessageDialog(this, panel, "Liste des préparations", JOptionPane.INFORMATION_MESSAGE);
    }

    private void creerPreparation() {
         JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Créer une préparation"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom de la préparation :"), BorderLayout.WEST);
        JTextField nomPreparationField = new JTextField();
        panel.add(nomPreparationField, BorderLayout.CENTER);
        panel.add(new JLabel("Ingrédients :"), BorderLayout.WEST);
        JTextArea ingredientsField = new JTextArea();
        panel.add(ingredientsField, BorderLayout.CENTER);
        panel.add(new JLabel("Quantité :"), BorderLayout.WEST);
        JTextField quantiteField = new JTextField();
        panel.add(quantiteField, BorderLayout.CENTER);
        panel.add(new JLabel("Prix :"), BorderLayout.WEST);
        JTextField prixField = new JTextField();
        panel.add(prixField, BorderLayout.CENTER);
        panel.add(new JLabel("Description :"), BorderLayout.WEST);
        JTextArea descriptionField = new JTextArea();
        panel.add(descriptionField, BorderLayout.CENTER);
        JButton creerButton = new JButton("Créer");
        creerButton.addActionListener(e -> {
            String nomPreparation = nomPreparationField.getText();
            String ingredients = ingredientsField.getText();
            String quantite = quantiteField.getText();
            String prix = prixField.getText();
            String description = descriptionField.getText();
            // Créer la préparation
            Preparation preparation = new Preparation(nomPreparation, ingredients, quantite, prix, description);
            pharmacie.ajouterPreparation(preparation);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Préparation créée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void modifierPreparation() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Modifier une préparation"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom de la préparation :"), BorderLayout.WEST);
        JTextField nomPreparationField = new JTextField();
        panel.add(nomPreparationField, BorderLayout.CENTER);
        panel.add(new JLabel("Ingrédients :"), BorderLayout.WEST);
        JTextArea ingredientsField = new JTextArea();
        panel.add(ingredientsField, BorderLayout.CENTER);
        panel.add(new JLabel("Quantité :"), BorderLayout.WEST);
        JTextField quantiteField = new JTextField();
        panel.add(quantiteField, BorderLayout.CENTER);
        panel.add(new JLabel("Prix :"), BorderLayout.WEST);
        JTextField prixField = new JTextField();
        panel.add(prixField, BorderLayout.CENTER);
        panel.add(new JLabel("Description :"), BorderLayout.WEST);
        JTextArea descriptionField = new JTextArea();
        panel.add(descriptionField, BorderLayout.CENTER);
        JButton modifierButton = new JButton("Modifier");
        modifierButton.addActionListener(e -> {
            String nomPreparation = nomPreparationField.getText();
            String ingredients = ingredientsField.getText();
            String quantite = quantiteField.getText();
            String prix = prixField.getText();
            String description = descriptionField.getText();
            // Modifier la préparation
            Preparation preparation = new Preparation(nomPreparation, ingredients, quantite, prix, description);
            pharmacie.modifierPreparation(preparation);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Préparation modifiée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void supprimerPreparation() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Supprimer une préparation"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom de la préparation :"), BorderLayout.WEST);
        JTextField nomPreparationField = new JTextField();
        panel.add(nomPreparationField, BorderLayout.CENTER);
        JButton supprimerButton = new JButton("Supprimer");
        supprimerButton.addActionListener(e -> {
            String nomPreparation = nomPreparationField.getText();
            // Supprimer la préparation
            pharmacie.supprimerPreparation(nomPreparation);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Préparation supprimée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        });
        
    }

    private void ajouterMedicament() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Ajouter un médicament"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom du médicament :"), BorderLayout.WEST);
        JTextField nomMedicamentField = new JTextField();
        panel.add(nomMedicamentField, BorderLayout.CENTER);
        panel.add(new JLabel("Prix :"), BorderLayout.WEST);
        JTextField prixField = new JTextField();
        panel.add(prixField, BorderLayout.CENTER);
        panel.add(new JLabel("Quantité en stock :"), BorderLayout.WEST);
        JTextField quantiteEnStockField = new JTextField();
        panel.add(quantiteEnStockField, BorderLayout.CENTER);
        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(e -> {
            String nomMedicament = nomMedicamentField.getText();
            String prix = prixField.getText();
            String quantiteEnStock = quantiteEnStockField.getText();
            // Créer le médicament
            Medicament medicament = new Medicament(nomMedicament, prix, quantiteEnStock);
            // Ajouter le médicament à la pharmacie
            pharmacie.ajouterMedicament(medicament);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Médicament ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            // Rafraîchir la liste des médicaments
            JPanel medicamentsPanel = new JPanel();
            afficherListeMedicaments(medicamentsPanel);
        });
    }

    private void modifierMedicament() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Modifier un médicament"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom du médicament :"), BorderLayout.WEST);
        JTextField nomMedicamentField = new JTextField();
        panel.add(nomMedicamentField, BorderLayout.CENTER);
        panel.add(new JLabel("Prix :"), BorderLayout.WEST);
        JTextField prixField = new JTextField();
        panel.add(prixField, BorderLayout.CENTER);
        panel.add(new JLabel("Quantité en stock :"), BorderLayout.WEST);
        JTextField quantiteEnStockField = new JTextField();
        panel.add(quantiteEnStockField, BorderLayout.CENTER);
        JButton modifierButton = new JButton("Modifier");
        modifierButton.addActionListener(e -> {
            String nomMedicament = nomMedicamentField.getText();
            String prix = prixField.getText();
            String quantiteEnStock = quantiteEnStockField.getText();
            // Modifier le médicament
            Medicament medicament = new Medicament(nomMedicament, prix, quantiteEnStock);
            pharmacie.modifierMedicament(medicament);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Médicament modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            // Rafraîchir la liste des médicaments
            JPanel medicamentsPanel = new JPanel();
            afficherListeMedicaments(medicamentsPanel);
        });
    }

    private void supprimerMedicament() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Supprimer un médicament"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom du médicament :"), BorderLayout.WEST);
        JTextField nomMedicamentField = new JTextField();
        panel.add(nomMedicamentField, BorderLayout.CENTER);
        JButton supprimerButton = new JButton("Supprimer");
        supprimerButton.addActionListener(e -> {
            String nomMedicament = nomMedicamentField.getText();
            // Supprimer le médicament
            pharmacie.supprimerMedicament(nomMedicament);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Médicament supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            // Rafraîchir la liste des médicaments
            JPanel medicamentsPanel = new JPanel();
            afficherListeMedicaments(medicamentsPanel);
        });
    }

    private void rechercherMedicament() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Rechercher un médicament"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom du médicament :"), BorderLayout.WEST);
        JTextField nomMedicamentField = new JTextField();
        panel.add(nomMedicamentField, BorderLayout.CENTER);
        JButton rechercherButton = new JButton("Rechercher");
        rechercherButton.addActionListener(e -> {
            String nomMedicament = nomMedicamentField.getText();
            // Rechercher le médicament
            Medicament medicament = pharmacie.rechercherMedicament(nomMedicament);
            // Afficher le médicament
            if (medicament != null) {
                JOptionPane.showMessageDialog(this, medicament.toString(), "Médicament trouvé", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Médicament non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void commanderPreparation() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Commander une préparation"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom de la préparation :"), BorderLayout.WEST);
        JTextField nomPreparationField = new JTextField();
        panel.add(nomPreparationField, BorderLayout.CENTER);
        JButton commanderButton = new JButton("Commander");
        commanderButton.addActionListener(e -> {
            Preparation nomPreparation = new Preparation(nomPreparationField.getText());
            // Commander la préparation
            Preparation preparation = pharmacie.commanderPreparation(nomPreparation);
            // Afficher un message de succès
            if (preparation != null) {
                JOptionPane.showMessageDialog(this, "Préparation commandée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Préparation non trouvée", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void enregistrerDemandeMedicamentGen() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Enregistrer une demande de médicament générique"), BorderLayout.NORTH);
        panel.add(new JLabel("Nom du médicament générique :"), BorderLayout.WEST);
        JTextField nomMedicamentGenField = new JTextField();
        panel.add(nomMedicamentGenField, BorderLayout.CENTER);
        JButton enregistrerButton = new JButton("Enregistrer");
        enregistrerButton.addActionListener(e -> {
            String nomMedicamentGen = nomMedicamentGenField.getText();
            // Enregistrer la demande de médicament générique
            pharmacie.enregistrerDemandeMedicamentGen(nomMedicamentGen);
            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Demande de médicament générique enregistrée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void afficherMenu() {
        System.out.println("1. Afficher la liste des médicaments");
        System.out.println("2. Afficher la liste des médecins");
        System.out.println("3. Afficher la liste des patients");
        System.out.println("4. Quitter");

        Scanner scanner = null;
        int choix = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la fin de la ligne

        switch (choix) {
            case 1:
                afficherListeMedicaments();
                break;
            case 2:
                afficherListeMedecins();
                break;
            case 3:
                afficherListePatients();
                break;
            case 4:
                System.out.println("Merci de votre visite. À bientôt!");
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
                afficherMenu();
        }
    }

    private void afficherListePatients() {
        List<Patient> patients = pharmacie.getPatients();
        if (patients.isEmpty()) {
            System.out.println("Aucun patient enregistré pour le moment.");
        } else {
            System.out.println("Liste des patients :");
            for (Patient patient : patients) {
                System.out.println("- " + patient.getNom());
            }
        }
        afficherMenu();
    }

    private void afficherListeMedecins() {
        List<Medecin> medecins = pharmacie.getMedecins();
        if (medecins.isEmpty()) {
            System.out.println("Aucun médecin enregistré pour le moment.");
        } else {
            System.out.println("Liste des médecins :");
            for (Medecin medecin : medecins) {
                System.out.println("- " + medecin.getNom());
            }
        }
        afficherMenu();
    }

    private void afficherListeMedicaments() {
        List<Medicament> medicaments = pharmacie.getMedicaments();
        if (medicaments.isEmpty()) {
            System.out.println("Aucun médicament disponible pour le moment.");
        } else {
            System.out.println("Liste des médicaments :");
            for (Medicament medicament : medicaments) {
                System.out.println("- " + medicament.getNom() + " (" + medicament.getQuantiteEnStock() + " en stock)");
            }
        }
        afficherMenu();
    }

    // Méthode pour afficher la liste des médicaments
    private void afficherListeMedicaments(JPanel panel) {
        List<Medicament> medicaments = pharmacie.getMedicaments();
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        if (medicaments.isEmpty()) {
            textArea.setText("Aucun médicament disponible pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des médicaments :\n");
            for (Medicament medicament : medicaments) {
                sb.append("- ").append(medicament.getNom()).append("\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    // Méthode pour afficher la liste des médicaments non génériques
    private void afficherListeMedicamentsNonGeneriques(JPanel panel) {
        List<MedicamentNonGenerique> medicamentsNonGeneriques = pharmacie.getMedicamentsNonGeneriques();
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        if (medicamentsNonGeneriques.isEmpty()) {
            textArea.setText("Aucun médicament non générique disponible pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des médicaments non génériques :\n");
            for (MedicamentNonGenerique medicamentNonGenerique : medicamentsNonGeneriques) {
                sb.append("- ").append(medicamentNonGenerique.getNom()).append("\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    // Méthode pour afficher la liste des médicaments génériques
    private void afficherListeMedicamentsGeneriques(JPanel panel) {
        List<MedicamentGenerique> medicamentsGeneriques = pharmacie.getMedicamentsGeneriques();
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        if (medicamentsGeneriques.isEmpty()) {
            textArea.setText("Aucun médicament générique disponible pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des médicaments génériques :\n");
            for (MedicamentGenerique medicamentGenerique : medicamentsGeneriques) {
                sb.append("- ").append(medicamentGenerique.getNom()).append("\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    // Méthode pour afficher la liste des médecins
    private void afficherListeMedecins(JPanel panel) {
        List<Medecin> medecins = pharmacie.getMedecins();
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        if (medecins.isEmpty()) {
            textArea.setText("Aucun médecin enregistré pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des médecins :\n");
            for (Medecin medecin : medecins) {
                sb.append("- ").append(medecin.getNom()).append("\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    // Méthode pour afficher la liste des patients
    private void afficherListePatients(JPanel panel) {
        List<Patient> patients = pharmacie.getPatients();
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        if (patients.isEmpty()) {
            textArea.setText("Aucun patient enregistré pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des patients :\n");
            for (Patient patient : patients) {
                sb.append("- ").append(patient.getNom()).append("\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    // Méthode pour afficher la liste des préparations
    private void afficherListePreparations(JPanel panel) {
        List<Preparation> preparations = pharmacie.getPreparations();
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        if (preparations.isEmpty()) {
            textArea.setText("Aucune préparation disponible pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des préparations :\n");
            for (Preparation preparation : preparations) {
                sb.append("- ").append(preparation.getDescription()).append("\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Pharmacie pharmacie = new Pharmacie(); // Remplacez par l'instanciation réelle de Pharmacie
        SwingUtilities.invokeLater(() -> new UiGui(pharmacie));
    }

    public void afficher() {
        // TODO Auto-generated method stub
        setVisible(true);
    }

    public void fermer() {
        // TODO Auto-generated method stub
        setVisible(false);
    }
}
