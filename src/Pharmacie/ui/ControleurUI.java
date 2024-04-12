package Pharmacie.ui;

import Pharmacie.Medicament;

public class ControleurUI {
    // Méthodes pour la logique de contrôle entre l'interface utilisateur et les modules métier
    public void afficherMenuPrincipal() {
        // Afficher le menu principal
        System.out.println("Menu principal");
        System.out.println("1. Ajouter un médicament");
        System.out.println("2. Modifier un médicament");
        System.out.println("3. Supprimer un médicament");
        System.out.println("4. Lister les médicaments");
        System.out.println("5. Rechercher un médicament");
        System.out.println("6. Quitter");
        System.out.println("Veuillez choisir une option : ");
        int choix = Integer.parseInt(System.console().readLine());
        switch (choix) {
            case 1:
                ajouterMedicament();
                break;
            case 2:
                modifierMedicament();
                break;
            case 3:
                supprimerMedicament();
                break;
            case 4:
                listerMedicaments();
                break;
            case 5:
                rechercherMedicament();
                break;
            case 6:
                quitter();
                break;
            default:
                afficherMessageErreur("Option invalide");
                break;
        }
        afficherMenuPrincipal();
    }
    public void afficherMessageErreur(String message) {
        // Afficher un message d'erreur
        System.out.println(message);
    }
    public void afficherMessage(String message) {
        // Afficher un message
        System.out.println(message);
    }
    public void ajouterMedicament() {
        // Ajouter un médicament
        System.out.println("Ajouter un médicament");
        // ...
    }
    public void modifierMedicament() {
        // Modifier un médicament
        System.out.println("Modifier un médicament");
        // ...
    }
    public void supprimerMedicament() {
        // Supprimer un médicament
        System.out.println("Supprimer un médicament");
        // ...
    }
    public void listerMedicaments() {
        // Lister les médicaments
        System.out.println("Lister les médicaments");
        // ...
    }
    public void rechercherMedicament() {
        // Rechercher un médicament
        System.out.println("Rechercher un médicament");
        // ...
    }
    public void quitter() {
        // Quitter
        System.out.println("Quitter");
        // ...
    }
    public static void main(String[] args) {
        // Créer un objet ControleurUI
        ControleurUI controleurUI = new ControleurUI();
        // Afficher le menu principal
        controleurUI.afficherMenuPrincipal();
    }
}
