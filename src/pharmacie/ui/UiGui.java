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
import java.util.List;

public class UiGui {

    private final Pharmacie pharmacie;
    private JFrame frame;

    public UiGui(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Pharmacie");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel medicamentsGeneriquesPanel = new JPanel();
        tabbedPane.addTab("Médicaments Génériques", null, medicamentsGeneriquesPanel, null);
        medicamentsGeneriquesPanel.setLayout(new BorderLayout(0, 0));

        JPanel medicamentsNonGeneriquesPanel = new JPanel();
        tabbedPane.addTab("Médicaments Non Génériques", null, medicamentsNonGeneriquesPanel, null);
        medicamentsNonGeneriquesPanel.setLayout(new BorderLayout(0, 0));

        JPanel medicamentsPanel = new JPanel();
        tabbedPane.addTab("Médicaments", null, medicamentsPanel, null);
        medicamentsPanel.setLayout(new BorderLayout(0, 0));

        JPanel medecinsPanel = new JPanel();
        tabbedPane.addTab("Médecins", null, medecinsPanel, null);
        medecinsPanel.setLayout(new BorderLayout(0, 0));

        JPanel patientsPanel = new JPanel();
        tabbedPane.addTab("Patients", null, patientsPanel, null);
        patientsPanel.setLayout(new BorderLayout(0, 0));

        JPanel preparationsPanel = new JPanel();
        tabbedPane.addTab("Préparations", null, preparationsPanel, null);
        preparationsPanel.setLayout(new BorderLayout(0, 0));


        afficherListeMedicaments(medicamentsPanel);
        afficherListeMedicamentsGeneriques(medicamentsGeneriquesPanel);
        afficherListeMedicamentsNonGeneriques(medicamentsNonGeneriquesPanel);
        afficherListeMedecins(medecinsPanel);
        afficherListePatients(patientsPanel);
        afficherListePreparations(preparationsPanel);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu actionsMenu = new JMenu("Actions");
        menuBar.add(actionsMenu);

        JMenuItem chercherMedicamentItem = new JMenuItem("Cher. mon/mes Medic.");
        chercherMedicamentItem.addActionListener(e -> chercherMedicament());
        actionsMenu.add(chercherMedicamentItem);

        JMenuItem commanderPreparationItem = new JMenuItem("Cmd. ma/mes Prépa.");
        commanderPreparationItem.addActionListener(e -> commanderPreparation());
        actionsMenu.add(commanderPreparationItem);

        JMenuItem afficherPreparationsItem = new JMenuItem("Aff. ma/mes Prépa.");
        afficherPreparationsItem.addActionListener(e -> afficherListePreparations(preparationsPanel));
        actionsMenu.add(afficherPreparationsItem);

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> System.exit(0));
        frame.getContentPane().add(closeButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void supprimerMedicament() {
        String nomMedicament = JOptionPane.showInputDialog(frame, "Entrez le nom du/des médicament(s) à supprimer :");
        Medicament medicament = new Medicament(nomMedicament, 0, 0);
        pharmacie.supprimerMedicament(medicament);
        JOptionPane.showMessageDialog(frame, "Médicament supprimé : " + medicament.getNom());
    }

    private void modifierMedicament() {
        String nomMedicament = JOptionPane.showInputDialog(frame, "Entrez le nom du/des médicament(s) à modifier :");
        int quantite = Integer.parseInt(JOptionPane.showInputDialog(frame, "Entrez la quantité à modifier :"));
        Medicament medicament = new Medicament(nomMedicament, 0, quantite);
        pharmacie.modifierMedicament(medicament);
        JOptionPane.showMessageDialog(frame, "Médicament modifié : " + medicament.getNom());
    }

    private void modifierPreparation() {
        String nomMedicament = JOptionPane.showInputDialog(frame, "Entrez le nom du/des médicament(s) à modifier :");
        int quantite = Integer.parseInt(JOptionPane.showInputDialog(frame, "Entrez la quantité à modifier :"));
        Medicament medicament = new Medicament(nomMedicament, 0, quantite);
        Preparation preparation = new Preparation(medicament, quantite);
        pharmacie.modifierPreparation(preparation);
        JOptionPane.showMessageDialog(frame, "Préparation modifiée : " + preparation.getDescription());
    }

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
                sb.append("- ").append(medicament.getNom()).append(" (").append(medicament.getQuantiteEnStock()).append(" en stock)\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    private void afficherListeMedicamentsGeneriques(JPanel panel) {
        List<MedicamentGenerique> medicaments = pharmacie.getMedicamentsGeneriques();
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        if (medicaments.isEmpty()) {
            textArea.setText("Aucun médicament générique disponible pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des médicaments génériques :\n");
            for (Medicament medicament : medicaments) {
                sb.append("- ").append(medicament.getNom()).append(" (").append(medicament.getQuantiteEnStock()).append(" en stock)\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    private void afficherListeMedicamentsNonGeneriques(JPanel panel) {
        List<MedicamentNonGenerique> medicaments = pharmacie.getMedicamentsNonGeneriques();
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        if (medicaments.isEmpty()) {
            textArea.setText("Aucun médicament non générique disponible pour le moment.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liste des médicaments non génériques :\n");
            for (Medicament medicament : medicaments) {
                sb.append("- ").append(medicament.getNom()).append(" (").append(medicament.getQuantiteEnStock()).append(" en stock)\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

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

    private void chercherMedicament() {
        String nomMedicament = JOptionPane.showInputDialog(frame, "Entrez le nom du médicament à chercher :");
        List<Medicament> medicaments = pharmacie.getMedicaments();
        for (Medicament medicament : medicaments) {
            if (medicament.getNom().equals(nomMedicament)) {
                JOptionPane.showMessageDialog(frame, "Médicament trouvé : " + medicament.getNom());
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Médicament non trouvé.");
    }

    private void commanderPreparation() {
        String nomMedicament = JOptionPane.showInputDialog(frame, "Entrez le nom du/des médicament(s) à commander :");
        int quantite = Integer.parseInt(JOptionPane.showInputDialog(frame, "Entrez la quantité à commander :"));
        Medicament medicament = new Medicament(nomMedicament, 0, quantite);
        Preparation preparation = new Preparation(medicament, quantite);
        pharmacie.commanderPreparation(preparation);
        JOptionPane.showMessageDialog(frame, "Préparation commandée : " + preparation.getDescription());
    }

    private void afficherListePreparations(JPanel preparationsPanel) {
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
        preparationsPanel.removeAll(); // Clear existing content
        preparationsPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        preparationsPanel.revalidate(); // Refresh the panel
        preparationsPanel.repaint(); // Repaint the panel
    }

    public void afficher() {
        EventQueue.invokeLater(() -> {
            try {
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
