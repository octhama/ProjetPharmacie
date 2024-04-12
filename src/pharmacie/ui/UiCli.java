package pharmacie.ui;

import pharmacie.entites.Medicament;
import pharmacie.personnes.Medecin;
import pharmacie.personnes.Patient;
import pharmacie.entites.Pharmacie;

import java.util.List;
import java.util.Scanner;

public class UiCli {
    private Pharmacie pharmacie;
    private Scanner scanner;

    public UiCli(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
        this.scanner = new Scanner(System.in);
    }

    public void afficherMenu() {
        System.out.println("Bienvenue dans la pharmacie!");
        System.out.println("1. Afficher la liste des médicaments");
        System.out.println("2. Afficher la liste des médecins");
        System.out.println("3. Afficher la liste des patients");
        System.out.println("4. Quitter");

        int choix = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la fin de la ligne

        switch (choix) {
            case 1:
                afficherListeMedicaments();
                break;
            case 2:
                afficherListeMedecins();
                break;
            case 3:
                afficherListePatients();
                break;
            case 4:
                System.out.println("Merci de votre visite. À bientôt!");
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
                afficherMenu();
        }
    }

    private void afficherListeMedicaments() {
        List<Medicament> medicaments = pharmacie.getMedicaments();
        if (medicaments.isEmpty()) {
            System.out.println("Aucun médicament disponible pour le moment.");
        } else {
            System.out.println("Liste des médicaments :");
            for (Medicament medicament : medicaments) {
                System.out.println("- " + medicament.getNom() + " (" + medicament.getQuantiteEnStock() + " en stock)");
            }
        }
        afficherMenu();
    }

    private void afficherListeMedecins() {
        List<Medecin> medecins = pharmacie.getMedecins();
        if (medecins.isEmpty()) {
            System.out.println("Aucun médecin enregistré pour le moment.");
        } else {
            System.out.println("Liste des médecins :");
            for (Medecin medecin : medecins) {
                System.out.println("- " + medecin.getNom());
            }
        }
        afficherMenu();
    }

    private void afficherListePatients() {
        List<Patient> patients = pharmacie.getPatients();
        if (patients.isEmpty()) {
            System.out.println("Aucun patient enregistré pour le moment.");
        } else {
            System.out.println("Liste des patients :");
            for (Patient patient : patients) {
                System.out.println("- " + patient.getNom());
            }
        }
        afficherMenu();
    }
}
