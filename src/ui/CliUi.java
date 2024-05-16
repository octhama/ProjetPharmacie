package ui;

import pharmacie.Commande;
import pharmacie.DemandeVersionGenerique;
import pharmacie.Medicament;
import pharmacie.Pharmacie;
import exceptions.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Interface utilisateur en ligne pour la pharmacie.
 */

public class CliUi {
    private final Pharmacie pharmacie;

    public CliUi(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    public void afficherMenu() {
        System.out.println("1. Afficher la liste des médicaments");
        System.out.println("2. Trouver des médicaments");
        System.out.println("3. Acheter un médicament");
        System.out.println("4. Commander une préparation");
        System.out.println("5. Faire une demande de médicament en version générique");
        System.out.println("6. Enregistrer une ordonnance");
        System.out.println("7. Quitter");
    }

    public void afficherMedicaments() {
        List<Medicament> medicaments = pharmacie.getMedicaments();
        for (Medicament medicament : medicaments) {
            System.out.println(medicament.getNom());
        }
    }

    public void trouverMedicaments() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du médicament : ");
        String nom = scanner.nextLine();
        List<Medicament> suggestions = pharmacie.trouverMedicamentsSuggestions(nom);
        for (Medicament medicament : suggestions) {
            if (medicament.getNom().contains(nom)) {
                System.out.println(medicament.getNom());
            } else {
                System.out.println("Aucun médicament trouvé.");
            }
        }
    }

    public void acheterMedicament() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du médicament : ");
        String nom = scanner.nextLine();
        System.out.println("Entrez la quantité : ");
        int quantite = scanner.nextInt();
        try {
            pharmacie.acheterMedicament(nom, quantite);
        } catch (ExeptionRuptureDeStock e) {
            System.out.println(e.getMessage());
        }
    }

    public void demarrer() {
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        while (choix != 4) {
            afficherMenu();
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    afficherMedicaments();
                    break;
                case 2:
                    trouverMedicaments();
                    break;
                case 3:
                    acheterMedicament();
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

}
