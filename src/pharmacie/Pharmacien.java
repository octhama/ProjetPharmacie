package pharmacie;

import java.awt.*;
import java.io.*;

import javax.swing.*;

import interfaces.IDocuments;
/**
 * Classe pour les pharmaciens.
 * On peut authentifier un pharmacien, accéder au menu du pharmacien, afficher les médicaments, les ordonnances, les demandes de médicaments génériques, le registre de préparation, etc.
 * @see Pharmacien#authentifierPharmacien(String, String)
 * @see Pharmacien#accesMenuPharmacien()
 * @see Pharmacien#afficherMenuPharmacien()
 */
public class Pharmacien extends Personne {

    public Pharmacien(String Id, String MotDePasse, String nom, String prenom) {
        super(Id, MotDePasse, nom, prenom);
    }

    // Méthode pour authentifier un pharmacien en utilisant un identifiant et un mot de passe)
    public static boolean authentifierPharmacien(String id, String password) {
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

    // Méthode pour accéder au menu du pharmacien
    public static void accesMenuPharmacien() {
        // Demander à l'utilisateur de saisir l'identifiant et le mot de passe
        String id = JOptionPane.showInputDialog(null, "Identifiant pharmacien :");
        String password = JOptionPane.showInputDialog(null, "Mot de passe :");
    
        // Vérifier l'authentification
        if (Pharmacien.authentifierPharmacien(id, password)) {
            // Authentification réussie, afficher le menu du pharmacien
            afficherMenuPharmacien();
        } else {
            // Afficher un message d'erreur si l'authentification a échoué
            JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect.");
        }
    }

    // Méthode pour afficher le menu du pharmacien
    private static void afficherMenuPharmacien() {
        SwingUtilities.invokeLater(() -> { // Exécuter l'interface graphique dans le thread de l'EDT (Event Dispatch Thread)
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
        JPanel panelAffichageInfo = new JPanel(new BorderLayout());
        contentPanel.add(panelAffichageInfo, "Affichage des informations");

        // Créer un menu pour les actions d'affichage de données
        JMenu menuActionsAffichage = new JMenu("Affichage de données");
        menuBar.add(menuActionsAffichage);

        JMenuItem menuItemAfficherMedicaments = new JMenuItem("Afficher les médicaments");
        menuActionsAffichage.add(menuItemAfficherMedicaments);

        JMenuItem menuItemAfficherOrdonnances = new JMenuItem("Afficher les ordonnances");
        menuActionsAffichage.add(menuItemAfficherOrdonnances);

        JMenuItem menuItemAfficherDemandeMedicamentsGeneriques = new JMenuItem("Aff. demandes med. gen.");
        menuActionsAffichage.add(menuItemAfficherDemandeMedicamentsGeneriques);

        JMenuItem menuItemAfficherRegistrePreparation = new JMenuItem("Aff. prep. médicaments");
        menuActionsAffichage.add(menuItemAfficherRegistrePreparation);

        // Menu pour afficher la liste des patients et des médecins (griser et désactiver)
        JMenuItem menuItemAfficherListePatients = new JMenuItem("Aff. patients (désactivé)");
        menuActionsAffichage.add(menuItemAfficherListePatients);

        JMenuItem menuItemAfficherListeMedecins = new JMenuItem("Aff. médecins (désactivé)");
        menuActionsAffichage.add(menuItemAfficherListeMedecins);

            // Désactiver et griser les éléments de menu
            menuItemAfficherListePatients.setEnabled(false);
            menuItemAfficherListeMedecins.setEnabled(false);

        // Action pour afficher les médicaments
        menuItemAfficherMedicaments.addActionListener(e -> {
            // Appeler la méthode pour afficher les médicaments dans le panneau de la fenêtre
            IDocuments.afficherMedicaments(panelAffichageInfo);
        });

        // Action pour afficher les ordonnances
        menuItemAfficherOrdonnances.addActionListener(e -> {
            // Appeler la méthode pour afficher les ordonnances dans le panneau de la fenêtre
            try {
                IDocuments.afficherOrdonnances(panelAffichageInfo);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Action pour afficher les demandes de médicaments génériques
        menuItemAfficherDemandeMedicamentsGeneriques.addActionListener(e -> {
            // Appeler la méthode pour afficher les demandes de médicaments génériques dans le panneau de la fenêtre
            try {
                IDocuments.afficherDemandeMedicamentsGeneriques(panelAffichageInfo);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Action pour afficher le registre de préparation
        menuItemAfficherRegistrePreparation.addActionListener(e -> {
            // Appeler la méthode pour afficher le registre de préparation dans le panneau de la fenêtre
            try {
                IDocuments.afficherRegistrePreparation(panelAffichageInfo);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Action pour afficher la liste des patients
        menuItemAfficherListePatients.addActionListener(e -> {
            // Appeler la méthode pour afficher la liste des patients dans le panneau de la fenêtre
            IDocuments.afficherListePatients(panelAffichageInfo);
        });

        // Action pour afficher la liste des médecins
        menuItemAfficherListeMedecins.addActionListener(e -> {
            // Appeler la méthode pour afficher la liste des médecins dans le panneau de la fenêtre
            IDocuments.afficherListeMedecins(panelAffichageInfo);
        });

        // Créer un menu pour les actions du pharmacien
        JMenu menuActions = new JMenu("Gest. Stock Médicaments");
        menuBar.add(menuActions);
        JMenuItem menuItemAjouterMedicament = new JMenuItem("Ajouter un médicament");
        menuActions.add(menuItemAjouterMedicament);
        menuItemAjouterMedicament.addActionListener(e -> {
            // Appeler la méthode pour ajouter un médicament
            try {
                IDocuments.ajouterMedicament();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JMenuItem menuItemModifMed = new JMenuItem("Modifier un médicament");
        menuActions.add(menuItemModifMed);
        menuItemModifMed.addActionListener(e -> {
            // Appeler la méthode pour modifier un médicament
            try {
                IDocuments.modifierMedicament();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JMenuItem menuItemRetirerMedicament = new JMenuItem("Retirer un médicament");
        menuActions.add(menuItemRetirerMedicament);
        menuItemRetirerMedicament.addActionListener(e -> {
            // Appeler la méthode pour retirer un médicament
            try {
                IDocuments.retirerMedicament();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Créer un menu pour les actions du pharmacien (grisé et désactivé)
        JMenu menuActionsGestionDesPersonnes = new JMenu("Gest. Personnes (désactivé)");
        menuBar.add(menuActionsGestionDesPersonnes);

        JMenuItem menuItemAjouterPatient = new JMenuItem("Ajouter un patient (désactivé)");
        menuActionsGestionDesPersonnes.add(menuItemAjouterPatient);

        JMenuItem menuItemSuppPatient = new JMenuItem("Supprimer un patient (désactivé)");
        menuActionsGestionDesPersonnes.add(menuItemSuppPatient);

        JMenuItem menuItemAjouterMedecin = new JMenuItem("Ajouter un médecin (désactivé)");
        menuActionsGestionDesPersonnes.add(menuItemAjouterMedecin);

        JMenuItem menuItemSuppMedecin = new JMenuItem("Supprimer un médecin (désactivé)");
        menuActionsGestionDesPersonnes.add(menuItemSuppMedecin);

        JMenuItem menuItemAjouterPharmacien = new JMenuItem("Ajouter un pharmacien (désactivé)");
        menuActionsGestionDesPersonnes.add(menuItemAjouterPharmacien);

        JMenuItem menuItemSuppPharmacien = new JMenuItem("Supprimer un pharmacien (désactivé)");
        menuActionsGestionDesPersonnes.add(menuItemSuppPharmacien);

        // Désactiver et griser les éléments de menu
        menuItemAjouterPatient.setEnabled(false);
        menuItemSuppPatient.setEnabled(false);
        menuItemAjouterMedecin.setEnabled(false);
        menuItemSuppMedecin.setEnabled(false);
        menuItemAjouterPharmacien.setEnabled(false);
        menuItemSuppPharmacien.setEnabled(false);

        // Afficher la fenêtre du menu du pharmacien
        menuPharmacienFrame.setVisible(true);
        });
    }

}
