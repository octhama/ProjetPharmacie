package ui;

import pharmacie.Medicament;
import pharmacie.Pharmacie;
import exceptions.*;
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
        System.out.println("2. Chercher un medicament");
        System.out.println("3. Commander une preparation");
        System.out.println("4. Acheter un médicament");
        System.out.println("5. Quitter");
    }

    public void afficherMedicaments() {
        List<Medicament> medicaments = pharmacie.getMedicaments();
        for (Medicament medicament : medicaments) {
            System.out.println(medicament);
        }
    }

    public void chercherMedicament() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du médicament à chercher:");
        String nomMedicament = scanner.nextLine();

        Medicament medicament = (Medicament) pharmacie.trouverMedicamentsSuggestions(nomMedicament);
        if (medicament != null) {
            System.out.println(medicament);
        } else {
            System.out.println("Le médicament " + nomMedicament + " n'existe pas dans la pharmacie.");
        }
    }

    public void commanderPreparation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du médicament à commander:");
        String nomMedicament = scanner.nextLine();

        Medicament medicament = (Medicament) pharmacie.trouverMedicamentsSuggestions(nomMedicament);
        if (medicament != null) {
            System.out.println("Entrez la quantité à commander:");
            int quantite = scanner.nextInt();
            try {
                medicament.commander(quantite);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Le médicament " + nomMedicament + " n'existe pas dans la pharmacie.");
        }
    }

    public void acheterMedicament() throws ExeptionRuptureDeStock {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du médicament à acheter:");
        String nomMedicament = scanner.nextLine();

        Medicament medicament = (Medicament) pharmacie.trouverMedicamentsSuggestions(nomMedicament);
        if (medicament != null) {
            System.out.println("Entrez la quantité à acheter:");
            int quantite = scanner.nextInt();
            try {
                medicament.acheter(quantite);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Le médicament " + nomMedicament + " n'existe pas dans la pharmacie.");
        }
    }

    public void demarrer() throws ExeptionRuptureDeStock {
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        do {
            afficherMenu();
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    afficherMedicaments();
                    break;
                case 2:
                    chercherMedicament();
                    break;
                case 3:
                    commanderPreparation();
                    break;
                case 4:
                    acheterMedicament();
                    break;
                case 5:
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while (choix != 5);
    }
}
