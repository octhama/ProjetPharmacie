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
        menuBar = new JMenuBar();
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
        if (e.getActionCommand().equals("Quitter")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Ajouter un médicament")) {
            // Ajouter le code pour l'ajout d'un médicament
        }
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
            textArea.setText("Aucune préparation enregistrée pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des préparations :\n");
            for (Preparation preparation : preparations) {
                sb.append("- ").append(preparation.getMedicament().getNom()).append(" pour ").append(preparation.getPatient().getNom()).append(" par ").append(preparation.getMedecin().getNom()).append("\n");
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
