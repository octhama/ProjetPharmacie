package Pharmacie.ui;

import Pharmacie.Medicament;

import java.util.List;


public class InterfaceUtilisateur {
    // Méthodes pour afficher des menus et gérer les interactions utilisateur
    public void afficherMenuPrincipal() {
        System.out.println("Menu principal");
        System.out.println("1. Lister les médicaments");
        System.out.println("2. Commander une préparation");
        System.out.println("3. Quitter");
        System.out.println("Veuillez choisir une option : ");
    }
    public void afficherMessageErreur(String message) {
        System.out.println(message);
        System.out.println("Veuillez choisir une option : ");
    }
    public void afficherMessage(String message) {
        System.out.println(message);
    }
    public void afficherMedicaments(List<Medicament> medicaments) {
        for (Medicament medicament : medicaments) {
            System.out.println(medicament);
        }
    }

    public int lireChoixUtilisateur() {
        return 0;
    }

    public void chercherMedicament() {

    }

    public void commanderPreparation() {
    }

    public void quitter() {
    }

    public void afficherErreurSaisie() {
    }

    public void demarrer() {
    }

    public void fermer() {
    }


}
