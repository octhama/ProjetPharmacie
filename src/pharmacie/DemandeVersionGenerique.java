package pharmacie;

import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

import javax.swing.*;

import interfaces.IDocuments;
import io.EcritureRegistreDemandeVersionGeneriqueCsv;
import ui.UiGui;

public class DemandeVersionGenerique {
    private String nomMedicament;
    private boolean etatDeLaDemande;
    public static List<DemandeVersionGenerique> demandes = new ArrayList<>();

    public DemandeVersionGenerique(String nom, boolean b) {
        this.nomMedicament = nom;
        this.etatDeLaDemande = b;
    }

    public DemandeVersionGenerique(String nom) {
        this.nomMedicament = nom;
    }

    public DemandeVersionGenerique(String nomMedicament, boolean demande, LocalDate dateDemande) {
        this.nomMedicament = nomMedicament;
        this.etatDeLaDemande = demande;
    }

    public DemandeVersionGenerique() {

    }

    public static void demandes(String nom, String date) {
        DemandeVersionGenerique demande = new DemandeVersionGenerique(nom);
        demandes.add(demande);
    }

    public String getNomMedicament() {
        return nomMedicament;
    }

    public boolean isDemande() {
        return etatDeLaDemande;
    }

    public String isVersionGeneriqueDemandee() {
        return null;
    }

    public static void commanderMedicamentVersionGenerique() {
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
        IDocuments.afficherListeMedicamentsNonGenOnDemand(medicamentPanel);

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
            for (Map.Entry<Medicament, JCheckBox> entry : UiGui.medicamentGenCheckBoxMap.entrySet()) {
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
            IDocuments.afficherListeMedicamentsNonGenOnDemand(medicamentPanel);
        });

        // Action du bouton de retour
        buttonRetour.addActionListener(e -> {
            // Fermer la fenêtre de commande de médicaments
            frame.dispose();
        });

        // Afficher la fenêtre
        frame.setVisible(true);
    }

}
