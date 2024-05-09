package pharmacie;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

import interfaces.IDocuments;
import io.EcritureMedicamentsCsv;
import io.EcritureRegistrePreparationCsv;
import io.LectureMedicamentsCsv;
import io.LectureOrdonnanceCsv;
import org.jetbrains.annotations.NotNull;
import ui.UiGui;
import utils.DateUtlis;

public class Preparation {
    private String nom;
    // Ajoutez ce constructeur à la classe Preparation
    public Preparation(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Medicament getMedicament() {
        return null;
    }

    public static void commanderUnePreparation(HashMap<Object, Object> checkBoxSpinnerMap) {

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
                List<Medicament> filteredMedicaments = UiGui.pharmacie.filterMedicaments(search);
                afficherMedicaments(filteredMedicaments);

                // Restaurer les états des éléments après l'affichage des médicaments filtrés
                IDocuments.restoreSelectedMedicamentStates();
                IDocuments.restoreSpinnerStates();
            }

            // Afficher les médicaments filtrés dans le panneau des médicaments
            private void afficherMedicaments(@NotNull List<Medicament> filteredMedicaments) {
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
                    UiGui.medicamentCheckBoxMap.put(medicament, checkBox);
                    UiGui.spinnerMap.put(checkBox, spinner);

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

                    JCheckBox halfDoseCheckBox = UiGui.getjCheckBox(medicament, entryPanel);
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
                    for (int i = 0; i < UiGui.selectedMedicaments.size(); i++) {
                        Medicament medicament = UiGui.selectedMedicaments.get(i);
                        int quantite = UiGui.selectedQuantities.get(i);
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
                        List<Medicament> medicaments = UiGui.pharmacie.getMedicaments();

                        // Afficher les choix de médicaments confirmés dans la boîte de dialogue
                        for (Map.Entry<Medicament, JCheckBox> entry : UiGui.medicamentCheckBoxMap.entrySet()) {
                            // Parcourir les entrées de la map de la boîte de dialogue
                            Medicament medicament = entry.getKey();
                            JCheckBox checkBox = entry.getValue();
                            if (checkBox.isSelected()) {
                                // Récupérer le spinner correspondant à la case à cocher sélectionnée
                                JSpinner spinner = UiGui.spinnerMap.get(checkBox);
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
                        validateButton.addActionListener(_ -> {
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
                        cancelButton.addActionListener(_ -> confirmationDialog.dispose()); // Fermer la boîte de dialogue en cas d'annulation

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

}
