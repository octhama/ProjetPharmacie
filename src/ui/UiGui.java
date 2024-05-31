package ui;

import interfaces.IDocuments;
import io.LectureOrdonnanceCsv;
import pharmacie.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.io.*;
import java.util.*;
import java.util.List;
/**
 * Interface graphique pour une pharmacie (version graphique utilisée dans le projet final).
 * @see pharmacie.Pharmacie
 * @see pharmacie.Medicament
 * @see pharmacie.Pharmacien
 * @see pharmacie.Ordonnance
 * @see pharmacie.Preparation
 * @see pharmacie.DemandeVersionGenerique
 * @see pharmacie.Commande
 * @see pharmacie.Medicament#isGenerique()
 * @see pharmacie.Medicament#getNom()
 * @see pharmacie.Medicament#getPrix()
 * @see pharmacie.Medicament#getType()
 * @see pharmacie.Medicament#getQuantiteEnStock()
 * @see pharmacie.Pharmacie#trouverMedicamentsSuggestions(String)
 * @see pharmacie.Pharmacie#acheterMedicament(String, int)
 * @see pharmacie.Pharmacien#accesMenuPharmacien()
 * @see pharmacie.Ordonnance#enregistrerOrdonnance()
 * @see pharmacie.DemandeVersionGenerique#commanderMedicamentVersionGenerique()
 * @see pharmacie.Commande#commanderUnePreparation(HashMap)
 * @see pharmacie.Preparation#Preparation(String, String, int, String)
 * @see pharmacie.Preparation#getIdUnique()
 * @see pharmacie.Preparation#getNom()
 * @see pharmacie.Preparation#getQuantite()
 * @see pharmacie.Preparation#getDate()
 * @see pharmacie.Medicament#Medicament(String, double, enums.ETypeMedicament, boolean, int)
 * @see pharmacie.Medicament#Medicament(String, int)
 * @see pharmacie.Medicament#acheter(int)
 * @see pharmacie.Medicament#getType()
 * @see pharmacie.Medicament#isGenerique()
 * @see pharmacie.Medicament#getNom()
 * @see pharmacie.Medicament#getPrix()
 * @see pharmacie.Medicament#getQuantiteEnStock()
 * @see pharmacie.Medicament#getType()
 * @see pharmacie.Medicament#isGenerique()
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
    public static List<Medicament> selectedMedicaments = new ArrayList<>();
    public static List<Integer> selectedQuantities = new ArrayList<>();
    /**
     * Constructeur de l'interface graphique
     * @param pharmacie             La pharmacie à gérer dans l'interface graphique (initialisée à vide)
     * @param medicamentCheckBoxMap La map pour stocker les médicaments et les cases à cocher correspondantes (initialisée à vide)
     * @param spinnerMap            La map pour stocker les cases à cocher et les spinners correspondants (initialisée à vide)
     * @param medicamentGenCheckBoxMap La map pour stocker les médicaments génériques et les cases à cocher correspondantes (initialisée à vide)
     * @param selectedMedicaments   La liste des médicaments sélectionnés par l'utilisateur (initialisée à vide)
     * @param selectedQuantities    La liste des quantités sélectionnées par l'utilisateur (initialisée à vide)
     * @throws IOException          En cas d'erreur d'entrée/sortie
     */

    public UiGui(Pharmacie pharmacie, HashMap<Medicament, JCheckBox> medicamentCheckBoxMap, HashMap<JCheckBox, JSpinner> spinnerMap, HashMap<Medicament, JCheckBox> medicamentGenCheckBoxMap, List<Medicament> selectedMedicaments, List<Integer> selectedQuantities) throws IOException {
        UiGui.pharmacie = pharmacie;
        UiGui.medicamentGenCheckBoxMap = medicamentGenCheckBoxMap;
        UiGui.medicamentCheckBoxMap = medicamentCheckBoxMap;
        UiGui.spinnerMap = spinnerMap;
        UiGui.selectedMedicaments = selectedMedicaments;
        UiGui.selectedQuantities = selectedQuantities;
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
        JMenu menuActionsPatient = new JMenu("Menu Patient");
        menuBar.add(menuActionsPatient);

        // Déclaration du menu d'enregistrement d'ordonnance en dehors de la méthode
        JMenuItem menuEnregistrerOrdonnance = getjMenuItem();
        // Ajouter le menu au menuActionsPatient
        menuActionsPatient.add(menuEnregistrerOrdonnance);

        JMenuItem menuCommanderUnePreparation = new JMenuItem("Commander préparation(s)");
        menuCommanderUnePreparation.addActionListener(e -> {
            HashMap<Object, Object> checkBoxSpinnerMap = new HashMap<>();
            Commande.commanderUnePreparation(checkBoxSpinnerMap);
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

        // Champ de recherche
        JTextField searchField = new JTextField(20);
        searchField.setForeground(Color.GRAY); // Couleur de texte grise
        searchField.setText("Rechercher un médicament..."); // Texte placeholder

        // Ajout d'un listener pour le champ de recherche
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

    /**
     * Constructeur de l'interface graphique
     * @param pharmacie             La pharmacie à gérer dans l'interface graphique (initialisée à vide)
     * @param selectedMedicaments   La liste des médicaments sélectionnés par l'utilisateur (initialisée à vide)
     * @param selectedQuantities    La liste des quantités sélectionnées par l'utilisateur (initialisée à vide)
     * @throws IOException          En cas d'erreur d'entrée/sortie
     */

    public UiGui(Pharmacie pharmacie, List<Medicament> selectedMedicaments, List<Integer> selectedQuantities) throws IOException {
        this(pharmacie, new HashMap<>(), new HashMap<>(), new HashMap<>(), selectedMedicaments, selectedQuantities);
    }

    /**
     * Constructeur de l'interface graphique
     * @param pharmacie             La pharmacie à gérer dans l'interface graphique (initialisée à vide)
     * @throws IOException          En cas d'erreur d'entrée/sortie
     */

    public UiGui(Pharmacie pharmacie) throws IOException {
        this(pharmacie, new ArrayList<>(), new ArrayList<>());
    }

    // Méthode pour obtenir le menu d'enregistrement d'ordonnance
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

    // Méthode pour obtenir un label pour un médicament donné (avec ses informations)
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

    public static JPanel createOrdonnancePanel(Ordonnance ordonnance) throws IOException {
        JPanel ordonnancePanel = new JPanel();
        ordonnancePanel.setLayout(new GridLayout(0, 1));

        // Ajouter les détails de l'ordonnance
        JLabel referenceMedecinLabel = new JLabel("Références du médecin: " + ordonnance.getReferencesDuMedecin());
        JLabel referencePatientLabel = new JLabel("Références du patient: " + ordonnance.getReferencesDuPatient());
        JLabel datePrescriptionLabel = new JLabel("Date de prescription: " + ordonnance.getDatePrescription());
        JLabel listeMedicamentsLabel = new JLabel("Liste des médicaments:");

        // Créer un panneau pour afficher la liste des médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));
        // Ajouter chaque médicament de l'ordonnance à ce panneau
        for (String listeDesMedicamentsOrdonnance : ordonnance.getListeMedicamentsOrdonnance()) {
            JLabel medicamentLabel = new JLabel("- " + listeDesMedicamentsOrdonnance);
            medicamentsPanel.add(medicamentLabel);
        }

        // Ajouter les détails au panneau principal de l'ordonnance
        ordonnancePanel.add(referenceMedecinLabel);
        ordonnancePanel.add(referencePatientLabel);
        ordonnancePanel.add(datePrescriptionLabel);
        ordonnancePanel.add(listeMedicamentsLabel);
        ordonnancePanel.add(medicamentsPanel);

        // Styling
        ordonnancePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return ordonnancePanel;
    }

    public static JPanel createDemandeVersionGeneriquePanel(DemandeVersionGenerique demande) throws IOException {
        JPanel demandePanel = new JPanel();
        demandePanel.setLayout(new GridLayout(0, 1));

        // Ajouter les détails de la demande
        JLabel nomMedicamentLabel = new JLabel("Nom du médicament: " + demande.getNomMedicament());
        JLabel demandeVersionGeneriqueLabel = new JLabel("Demande de version générique: " + (demande.isDemande() ? "Oui" : "Non"));

        // Ajouter les détails au panneau principal de la demande
        demandePanel.add(nomMedicamentLabel);
        demandePanel.add(demandeVersionGeneriqueLabel);

        // Styling
        demandePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return demandePanel;
    }

    public static JPanel createPreparationPanel(Preparation preparation) {
        JPanel preparationPanel = new JPanel();
        preparationPanel.setLayout(new GridLayout(0, 1));

        // Ajouter les détails de la préparation
        JLabel idPreparationLabel = new JLabel("Identifiant de la préparation: " + preparation.getIdUnique());
        JLabel nomPreparationLabel = new JLabel("Nom de la préparation: " + preparation.getNom());
        JLabel quantitePreparationLabel = new JLabel("Quantité de la préparation: " + preparation.getQuantite());
        JLabel datePreparationLabel = new JLabel("Date de la préparation: " + preparation.getDate());

        // Ajouter les détails au panneau principal de la préparation
        preparationPanel.add(idPreparationLabel);
        preparationPanel.add(nomPreparationLabel);
        preparationPanel.add(quantitePreparationLabel);
        preparationPanel.add(datePreparationLabel);

        // Styling
        preparationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return preparationPanel;
    }

    // Méthode pour obtenir une case à cocher pour un médicament donné (avec ses informations)
    public static JCheckBox getjCheckBox(Medicament medicament, JPanel entryPanel) {
        JCheckBox halfDoseCheckBox = new JCheckBox("50% du mg");
        halfDoseCheckBox.addItemListener(e -> {
            if (halfDoseCheckBox.isSelected()) {
                halfDoseCheckBox.setText("mg à 50%");
                // Calculer la quantité de médicament nécessaire à 50% en mg
                String nomMedicament = medicament.getNom();
                String quantiteEnMg = IDocuments.extractQuantiteEnMg(nomMedicament);
                if (!quantiteEnMg.isEmpty()) {
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
