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

    public void demarrer() {
        int choix;
        Scanner scanner = new Scanner(System.in);

        do {
            afficherMenu();
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    listerMedicaments();
                    break;
                case 2:
                    acheterMedicament();
                    break;
                case 3:
                    // ... (autres fonctionnalités à implémenter)
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void afficherMenu() {
        System.out.println("\n***** Pharmacie *****");
        System.out.println("1. Lister les médicaments");
        System.out.println("2. Acheter un médicament");
        System.out.println("3. ... (autres options)");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private void listerMedicaments() {
        List<Medicament> medicaments = pharmacie.getMedicaments();
        if (medicaments.isEmpty()) {
            System.out.println("Aucun médicament disponible.");
        } else {
            for (Medicament medicament : medicaments) {
                System.out.println(medicament);
            }
        }
    }

    private void acheterMedicament() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nom du médicament : ");
        String nomMedicament = scanner.nextLine();

        Medicament medicament = pharmacie.trouverMedicament(nomMedicament);
        if (medicament == null) {
            System.out.println("Médicament introuvable.");
        } else {
            System.out.print("Quantité désirée : ");
            int quantite = scanner.nextInt();

            try {
                pharmacie.dispense(medicament, quantite);
                System.out.println("Médicament dispensé avec succès.");
            } catch (ExeptionRuptureDeStock e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
