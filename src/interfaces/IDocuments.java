package interfaces;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import enums.ETypeMedicament;
import io.*;
import org.jetbrains.annotations.NotNull;
import pharmacie.*;
import ui.UiGui;

import static ui.UiGui.*;

public interface IDocuments {
    
    // Affiche les suggestions de médicaments dans le même format que la liste complète
    static void afficherListeMedicaments(JPanel panel, @NotNull List<Medicament> suggestions) {
        // Création d'un panneau pour afficher les suggestions de médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médicament suggéré au panneau
        for (Medicament medicament : suggestions) {
            JLabel label = UiGui.getjLabel(medicament);
            medicamentsPanel.add(label);
        }

        // Retirer les anciens éléments du panneau
        panel.removeAll();

        // Ajouter le panneau des suggestions de médicaments au panneau principal
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);

        // Mettre à jour l'affichage du panneau
        panel.revalidate();
    }
    
    // Afficher les medicament de src/data/medicaments.csv
    static void afficherMedicaments(JPanel panel) {
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
        for (Medicament medicament : UiGui.pharmacie.getMedicaments()) {
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

    // Afficher les médecins de src/data/listeMedecins.csv (non implémenté)
    static void afficherListeMedecins(JPanel panel) {
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
        for (Medecin medecin : UiGui.pharmacie.getMedecins()) {
            JLabel label = new JLabel(medecin.getPrenom() + " " + medecin.getNom() + " - " + medecin.getReference());
            medecinPanel.add(label);
        }

        // Ajouter le panneau des médecins au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medecinPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    // Afficher les patients dans src/data/listePatients.csv (non implémenté)
    static void afficherListePatients(JPanel panel) {
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
        for (Patient patient : UiGui.pharmacie.getPatients()) {
            JLabel label = new JLabel(patient.getPrenom() + " " + patient.getNom() + " - " + patient.getReference());
            patientPanel.add(label);
        }

        // Ajouter le panneau des patients au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(patientPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    // Afficher les medicament de src/data/demandes_version_generique.csv
    static void afficherDemandeMedicamentsGeneriques(JPanel panel) throws IOException {
        // Créer une fenêtre pour afficher les demandes de médicaments génériques
        JFrame frame = new JFrame("Demandes de médicaments génériques");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Création d'un panneau pour afficher les médicaments en demande de version générique
        JPanel demandePanel = new JPanel();
        demandePanel.setLayout(new BoxLayout(demandePanel, BoxLayout.Y_AXIS));

        // Lire les demandes de médicaments génériques depuis le fichier CSV
        List<DemandeVersionGenerique> demandes = LectureDemandeVersionGeneriqueCsv.lireDemandesVersionGeneriqueCsv("src/data/demandes_version_generique.csv");

        // Ajouter les détails de chaque demande au panneau
        for (DemandeVersionGenerique demande : demandes) {
            JPanel demandesPanel = createDemandeVersionGeneriquePanel(demande);
            demandePanel.add(demandesPanel);
        }

        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(demandePanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    // Ajouter médicament dans le src/data/medicaments.csv
    static void ajouterMedicament() throws IOException {
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
                UiGui.pharmacie.ajouterMedicament(medicament);
                JOptionPane.showMessageDialog(dialog, "Le médicament a été ajouté avec succès.");

            // Mettre à jour le fichier CSV des médicaments
            try {
                EcritureMedicamentsCsv.ecrireAjoutDeMedicamentCsv("src/data/medicaments.csv", medicament);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Afficher un message de confirmation
            JOptionPane.showMessageDialog(dialog, "Le médicament a été ajouté avec succès dans la base de données.");

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

    // Supprimer un médicament de src/data/medicaments.csv
    static void retirerMedicament() throws IOException {
        // Créer une boîte de dialogue pour saisir les détails du médicament
        JDialog dialog = new JDialog();
        dialog.setTitle("Supprimer un médicament");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        // Créer un panneau pour les champs de saisie
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        dialog.add(inputPanel, BorderLayout.CENTER);

        // Ajouter des champs de saisie pour le nom et la quantité en stock du médicament
        JTextField nomField = new JTextField();

        inputPanel.add(new JLabel("Nom :"));
        inputPanel.add(nomField);

        //Créer un conteneur pour les boutons Supprimer, Reinitaliser et Quitter
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        dialog.add(buttonsPanel, BorderLayout.SOUTH);

        // Créer un bouton pour supprimer le médicament
        JButton supprimerButton = new JButton("Supprimer");
        buttonsPanel.add(supprimerButton);

        // Créer un bouton pour réinitialiser les champs
        JButton resetButton = new JButton("Réinitialiser");
        buttonsPanel.add(resetButton);

        // Créer un bouton pour fermer la boîte de dialogue
        JButton closeButton = new JButton("Fermer");
        buttonsPanel.add(closeButton);

        // Action du bouton Supprimer
        supprimerButton.addActionListener(e -> {
            // Récupérer les valeurs saisies par l'utilisateur
            String nom = nomField.getText();

            // Créer un nouveau médicament avec les valeurs saisies
            Medicament medicament = new Medicament(nom, 0, null, false, 0);

            // Supprimer le médicament de la liste des médicaments de la pharmacie
                UiGui.pharmacie.retirerMedicament(medicament);
                JOptionPane.showMessageDialog(dialog, "Le médicament a été retiré avec succès.");

            // Mettre à jour le fichier CSV des médicaments
            try {
                EcritureMedicamentsCsv.ecrireSuppressionDeMedicamentCsv(medicament, "src/data/medicaments.csv");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Afficher un message de confirmation
            JOptionPane.showMessageDialog(dialog, "Le médicament a été retiré avec succès de la base de données.");

            // Fermer la boîte de dialogue
            dialog.dispose();
        });

        // Action du bouton Réinitialiser

        resetButton.addActionListener(e -> {
            // Réinitialiser les champs de saisie
            nomField.setText("");
        });

        // Action du bouton Fermer
        closeButton.addActionListener(e -> {
            // Fermer la boîte de dialogue
            dialog.dispose();
        });

        // Afficher la boîte de dialogue
        dialog.setVisible(true);
    }

    // Modifier un médicament de src/data/medicaments.csv
    static void modifierMedicament() throws IOException {
        // Créer une boîte de dialogue pour saisir les détails du médicament
        JDialog dialog = new JDialog();
        dialog.setTitle("Modifier un médicament");
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

        //Créer un conteneur pour les boutons Modifier, Reinitaliser et Quitter
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        dialog.add(buttonsPanel, BorderLayout.SOUTH);

        // Créer un bouton pour modifier le médicament
        JButton modifierButton = new JButton("Modifier");
        buttonsPanel.add(modifierButton);

        // Créer un bouton pour réinitialiser les champs
        JButton resetButton = new JButton("Réinitialiser");
        buttonsPanel.add(resetButton);

        // Créer un bouton pour fermer la boîte de dialogue
        JButton closeButton = new JButton("Fermer");

        // Action du bouton Modifier

        modifierButton.addActionListener(e -> {
            // Récupérer les valeurs saisies par l'utilisateur
            String nom = nomField.getText();
            double prix = Double.parseDouble(prixField.getText());
            ETypeMedicament type = (ETypeMedicament) typeComboBox.getSelectedItem();
            boolean generique = generiqueCheckBox.getState();
            int quantite = Integer.parseInt(quantiteField.getText());

            // Créer un nouveau médicament avec les valeurs saisies
            Medicament medicament = new Medicament(nom, prix, type, generique, quantite);

            // Modifier le médicament dans la liste des médicaments de la pharmacie
                UiGui.pharmacie.modifierMedicament(medicament);
                JOptionPane.showMessageDialog(dialog, "Le médicament a été modifié avec succès.");

            // Mettre à jour le fichier CSV des médicaments
            try {
                EcritureMedicamentsCsv.ecrireModificationDeMedicamentCsv(medicament, "src/data/medicaments.csv");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Afficher un message de confirmation
            JOptionPane.showMessageDialog(dialog, "Le médicament a été modifié avec succès dans la base de données.");

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

    // Afficher les medicament de src/data/medicaments.csv
    static void afficherListeMedicaments(JPanel panel) {
        // Récupérer la liste des médicaments de la pharmacie
        List<Medicament> medicaments = UiGui.pharmacie.getMedicaments();

        // Création d'un panneau pour afficher les médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médicament au panneau
        for (Medicament medicament : medicaments) {
            JLabel label = UiGui.getjLabel(medicament);
            medicamentsPanel.add(label);
        }
        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

     // Afficher la liste des médicaments générique
    static void afficherListeMedicamentsGen(JPanel panel) {
        // Récupérer la liste des médicaments de la pharmacie
        List<Medicament> medicaments = UiGui.pharmacie.getMedicaments();

        // Création d'un panneau pour afficher les médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médicament au panneau
        for (Medicament medicament : medicaments) {
            if (medicament.isGenerique()) {
                JLabel label = UiGui.getjLabel(medicament);
                medicamentsPanel.add(label);
            }
        }
        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    // Afficher la liste des médicaments non générique
    static void afficherListeMedicamentsNonGen(JPanel panel) {
        // Récupérer la liste des médicaments de la pharmacie
        List<Medicament> medicaments = UiGui.pharmacie.getMedicaments();

        // Création d'un panneau pour afficher les médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médicament non générique au panneau
        for (Medicament medicament : medicaments) {
            if (!medicament.isGenerique()) {
                JLabel label = UiGui.getjLabel(medicament);
                medicamentsPanel.add(label);
            }
        }
        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    // Afficher la liste des médicaments sur ordonnance
    static  void afficherListeMedicamentsSurOrdonnance(JPanel panel) {
        // Récupérer la liste des médicaments de la pharmacie
        List<Medicament> medicaments = UiGui.pharmacie.getMedicaments();

        // Création d'un panneau pour afficher les médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médicament au panneau
        for (Medicament medicament : medicaments) {
            if (medicament.getType() == ETypeMedicament.ORDONNANCE) {
                JLabel label = UiGui.getjLabel(medicament);
                medicamentsPanel.add(label);
            }
        }
        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    // Afficher la liste des médicaments en vente libre
    static void afficherListeMedicamentsEnVenteLibre(JPanel panel) {
        // Récupérer la liste des médicaments de la pharmacie
        List<Medicament> medicaments = UiGui.pharmacie.getMedicaments();

        // Création d'un panneau pour afficher les médicaments
        JPanel medicamentsPanel = new JPanel();
        medicamentsPanel.setLayout(new BoxLayout(medicamentsPanel, BoxLayout.Y_AXIS));

        // Ajouter les détails de chaque médicament au panneau
        for (Medicament medicament : medicaments) {
            if (medicament.getType() == ETypeMedicament.VENTE_LIBRE) {
                JLabel label = UiGui.getjLabel(medicament);
                medicamentsPanel.add(label);
            }
        }
        // Ajouter le panneau des médicaments au panneau principal
        panel.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panel.add(new JScrollPane(medicamentsPanel), BorderLayout.CENTER);
        panel.revalidate(); // Mettre à jour l'affichage du panneau
    }

    static void afficherListeMedicamentsNonGenOnDemand(@NotNull JPanel medicamentPanel) {
        // Nettoyer le panneau des médicaments avant d'ajouter de nouveaux éléments
        medicamentPanel.removeAll();

        // Récupérer la liste des médicaments non génériques
        List<Medicament> medicamentsNonGen = UiGui.pharmacie.getMedicamentsNonGeneriques();

        // Parcourir la liste des médicaments non génériques
        for (Medicament medicament : medicamentsNonGen) {
            // Créer les composants pour afficher le médicament
            JPanel entryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JCheckBox checkBox = new JCheckBox();
            JLabel nameLabel = new JLabel(medicament.getNom());
            JLabel genLabel = new JLabel("Version générique demandée");
            genLabel.setForeground(Color.BLUE); // Mettre la couleur du label en bleu

            // Ajouter les éléments à medicamentGenCheckBoxMap
            UiGui.medicamentGenCheckBoxMap.put(medicament, checkBox);

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

    static void removePrix50PourcentLabel(@NotNull JPanel entryPanel) {
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

    static @NotNull String extractQuantiteEnMg(@NotNull String nomMedicament) {
        // Extraire la quantité en mg du nom du médicament
        String[] parts = nomMedicament.split(" ");
        for (String part : parts) {
            if (part.contains("mg")) {
                return part;
            }
        }
        return "";
    }

    static void saveSelectedMedicamentStates() {
        UiGui.selectedMedicamentStates.clear();
        for (Map.Entry<Medicament, JCheckBox> entry : UiGui.medicamentCheckBoxMap.entrySet()) {
            Medicament medicament = entry.getKey();
            JCheckBox checkBox = entry.getValue();
            UiGui.selectedMedicamentStates.put(medicament, checkBox.isSelected());
        }
    }

    static void restoreSelectedMedicamentStates() {
        for (Map.Entry<Medicament, Boolean> entry : UiGui.selectedMedicamentStates.entrySet()) {
            Medicament medicament = entry.getKey();
            boolean isSelected = entry.getValue();
            JCheckBox checkBox = UiGui.medicamentCheckBoxMap.get(medicament);
            checkBox.setSelected(isSelected);
        }
    }

    static void saveSpinnerStates() {
        UiGui.spinnerValues.clear();
        for (Map.Entry<Medicament, JCheckBox> entry : UiGui.medicamentCheckBoxMap.entrySet()) {
            Medicament medicament = entry.getKey();
            JCheckBox checkBox = entry.getValue();
            if (checkBox.isSelected()) {
                JSpinner spinner = UiGui.spinnerMap.get(checkBox);
                UiGui.spinnerValues.put(medicament, (int) spinner.getValue());
            }
        }
    }

    static void restoreSpinnerStates() {
        for (Map.Entry<Medicament, Integer> entry : UiGui.spinnerValues.entrySet()) {
            Medicament medicament = entry.getKey();
            Integer value = entry.getValue();
            JCheckBox checkBox = UiGui.medicamentCheckBoxMap.get(medicament);
            JSpinner spinner = UiGui.spinnerMap.get(checkBox);
            if (checkBox.isSelected()) {
                spinner.setValue(value);
            }
        }
    }

    static void afficherOrdonnances(JPanel panelAffichageInfo) throws IOException {
        // Créer un panneau pour afficher les ordonnances
        JPanel ordonnancesPanel = new JPanel();
        ordonnancesPanel.setLayout(new BoxLayout(ordonnancesPanel, BoxLayout.Y_AXIS));

        // Lire les ordonnances depuis le fichier CSV
        List<Ordonnance> ordonnances = LectureOrdonnanceCsv.lireOrdonnances("src/data/dataordonnances.csv");

        // Ajouter les détails de chaque ordonnance au panneau
        for (Ordonnance ordonnance : ordonnances) {
            JPanel ordonnancePanel = createOrdonnancePanel(ordonnance); // Créer le panneau pour chaque ordonnance
            ordonnancesPanel.add(ordonnancePanel); // Ajouter le panneau de l'ordonnance au panneau principal
        }

        // Ajouter le panneau des ordonnances au panneau principal
        panelAffichageInfo.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panelAffichageInfo.add(new JScrollPane(ordonnancesPanel), BorderLayout.CENTER);
        panelAffichageInfo.revalidate(); // Mettre à jour l'affichage du panneau
    }

    static void afficherRegistrePreparation(JPanel panelAffichageInfo) throws Exception {
        // Créer un panneau pour afficher le registre de préparation
        JPanel preparationPanel = new JPanel();
        preparationPanel.setLayout(new BoxLayout(preparationPanel, BoxLayout.Y_AXIS));

        // Lire les préparations depuis le fichier CSV
        List<Preparation> preparations = LectureRegistrePreparation.lireRegistrePreparation("src/data/registrepreparation.csv");

        // Ajouter les détails de chaque préparation au panneau
        for (Preparation preparation : preparations) {
            JPanel preparationsPanel = createPreparationPanel(preparation); // Créer le panneau pour chaque préparation
            preparationPanel.add(preparationsPanel); // Ajouter le panneau de la préparation au panneau principal
        }

        // Ajouter le panneau des préparations au panneau principal
        panelAffichageInfo.removeAll(); // Retirer les éventuels anciens éléments du panneau
        panelAffichageInfo.add(new JScrollPane(preparationPanel), BorderLayout.CENTER);
        panelAffichageInfo.revalidate(); // Mettre à jour l'affichage du panneau
    }

}
