package Pharmacie.ui;

import Pharmacie.Pharmacie;
import Pharmacie.composants.Medicament;
import Pharmacie.composants.Ordonnance;
import Pharmacie.composants.Preparation;
import Pharmacie.services.CommandeService;
import Pharmacie.services.GestionStockService;
import Pharmacie.services.OrdonnanceService;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner;
    private Pharmacie pharmacie;
    private GestionStockService gestionStockService;
    private CommandeService commandeService;
    private OrdonnanceService ordonnanceService;

    public ConsoleUI(Pharmacie pharmacie, GestionStockService gestionStockService, CommandeService commandeService, OrdonnanceService ordonnanceService) {
        this.scanner = new Scanner(System.in);
        this.pharmacie = pharmacie;
        this.gestionStockService = gestionStockService;
        this.commandeService = commandeService;
        this.ordonnanceService = ordonnanceService;
    }

    public void demarrer() {
        while (true) {
            System.out.println("\n**Pharmacie " + pharmacie.getNom() + "**");
            System.out.println("1. Gérer les médicaments");
            System.out.println("2. Gérer les commandes");
            System.out.println("3. Gérer les ordonnances");
            System.out.println("4. Quitter");

            try {
                int choix = scanner.nextInt();
                scanner.nextLine(); // Consommer le retour chariot

                switch (choix) {
                    case 1:
                        gererMedicaments();
                        break;
                    case 2:
                        gererCommandes();
                        break;
                    case 3:
                        gererOrdonnances();
                        break;
                    case 4:
                        System.out.println("Au revoir !");
                        System.exit(0);
                    default:
                        System.out.println("Choix invalide. Veuillez saisir un nombre entre 1 et 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur d'entrée. Veuillez saisir un nombre.");
                scanner.nextLine(); // Vider le buffer du scanner
            }
        }
    }

    private void gererMedicaments() {
        while (true) {
            System.out.println("\n**Gestion des médicaments**");
            System.out.println("1. Ajouter un médicament");
            System.out.println("2. Consulter le stock");
            System.out.println("3. Revenir au menu principal");

            try {
                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        ajouterMedicament();
                        break;
                    case 2:
                        consulterStock();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Choix invalide. Veuillez saisir un nombre entre 1 et 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur d'entrée. Veuillez saisir un nombre.");
                scanner.nextLine(); // Vider le buffer du scanner
            }
        }
    }

    private void ajouterMedicament() {
        System.out.print("Nom du médicament : ");
        String nom = scanner.nextLine();

        System.out.print("Marque (ou 'Générique') : ");
        String marque = scanner.nextLine();

        System.out.print("Prix unitaire : ");
        double prixUnitaire;
        try {
            prixUnitaire = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Erreur d'entrée. Veuillez saisir un nombre.");
            scanner.nextLine(); // Vider le buffer du scanner
            return;
        }

        System.out.print("Quantité en stock : ");
        int quantiteStock;
        try {
            quantiteStock = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erreur d'entrée. Veuillez saisir un nombre.");
            scanner.nextLine(); // Vider le buffer du scanner
            return;
        }

        System.out.print("Délivrable librement (true/false) : ");
        boolean delivrableLibrement = scanner.nextBoolean();

        System.out.print("Nécessite une ordonnance (true/false) : ");
        boolean necessitantOrdonnance = scanner.nextBoolean();

        Medicament medicament = new Medicament(1, nom, marque, prixUnitaire, quantiteStock, delivrableLibrement, necessitantOrdonnance);
        gestionStockService.ajouterMedicament(medicament);
        System.out.println("Médicament ajouté avec succès.");
    }

    private void consulterStock() {
        System.out.println("\n**Consultation du stock**");
        List<Medicament> stock = pharmacie.getStockMedicaments();

        if (stock.isEmpty()) {
            System.out.println("Le stock est vide.");
        } else {
            System.out.println("| Nom         | Marque   | Prix Unitaire (€) | Quantité en Stock |");
            System.out.println("|--------------|----------|------------------|-------------------|");
            for (Medicament medicament : stock) {
                System.out.printf("| %-12s | %-8s | %16.2f | %16d |\n",
                        medicament.getNom(), medicament.getMarque(), medicament.getPrixUnitaire(), medicament.getQuantiteStock());
            }
        }
    }

    private void gererCommandes() {
        while (true) {
            System.out.println("\n**Gestion des commandes**");
            System.out.println("1. Passer une commande");
            System.out.println("2. Consulter l'historique des commandes");
            System.out.println("3. Revenir au menu principal");

            try {
                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        passerCommande();
                        break;
                    case 2:
                        consulterHistoriqueCommandes();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Choix invalide. Veuillez saisir un nombre entre 1 et 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur d'entrée. Veuillez saisir un nombre.");
                scanner.nextLine(); // Vider le buffer du scanner
            }
        }
    }

    private void passerCommande() {
        System.out.print("Nom du médicament à commander : ");
        String nomMedicament = scanner.nextLine();

        System.out.print("Quantité à commander : ");
        int quantite;
        try {
            quantite = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erreur d'entrée. Veuillez saisir un nombre.");
            scanner.nextLine(); // Vider le buffer du scanner
            return;
        }

        commandeService.passerCommande(new Medicament(1, nomMedicament, null, 0.0, 0, false, false), quantite);
        System.out.println("Commande passée avec succès.");
    }

    private void consulterHistoriqueCommandes() {
        // TODO : Implémenter la consultation de l'historique des commandes
        System.out.println("Fonctionnalité non implémentée.");
    }

    private void gererOrdonnances() {
        while (true) {
            System.out.println("\n**Gestion des ordonnances**");
            System.out.println("1. Recevoir une ordonnance");
            System.out.println("2. Préparer une ordonnance");
            System.out.println("3. Délivrer une préparation");
            System.out.println("4. Consulter l'historique des ordonnances");
            System.out.println("5. Revenir au menu principal");

            try {
                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        recevoirOrdonnance();
                        break;
                    case 2:
                        preparerOrdonnance();
                        break;
                    case 3:
                        delivrerPreparation();
                        break;
                    case 4:
                        consulterHistoriqueOrdonnances();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Choix invalide. Veuillez saisir un nombre entre 1 et 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur d'entrée. Veuillez saisir un nombre.");
                scanner.nextLine(); // Vider le buffer du scanner
            }
        }
    }

    private void recevoirOrdonnance() {
        // Simuler la réception d'une ordonnance (remplacer par une implémentation plus réaliste)
        System.out.println("Saisir les détails de l'ordonnance (non implémenté)");
    }

    private <MedicamentIndisponibleException extends Throwable> void preparerOrdonnance() {
        System.out.print("ID de l'ordonnance à préparer : ");
        int idOrdonnance;
        try {
            idOrdonnance = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erreur d'entrée. Veuillez saisir un nombre.");
            scanner.nextLine(); // Vider le buffer du scanner
            return;
        }

        try {
            Preparation preparation = ordonnanceService.preparerOrdonnance(new Ordonnance(idOrdonnance, null, null));
            System.out.println("Préparation prête pour le patient " + preparation.getNom() + ".");
        } catch (Error e) {
            System.out.println("Erreur lors de la préparation de l'ordonnance : " + e.getMessage());
        }
    }

    private void delivrerPreparation() {
        System.out.print("Nom du patient pour la délivrance : ");
        String nomPatient = scanner.nextLine();

        // Trouver la préparation en attente pour le patient
        Preparation preparation = null;
        for (Preparation prep : pharmacie.getPreparationsEnCours()) {
            if (prep.getNom().equals(nomPatient)) {
                preparation = prep;
                break;
            }
        }

        if (preparation != null) {
            // Délivrer la préparation au patient
            pharmacie.getPreparationsEnCours().equals(Arrays.asList(preparation));
            ordonnanceService.delivrerPreparation(preparation);
            System.out.println("Préparation délivrée avec succès au patient " + nomPatient + ".");
        } else {
            System.out.println("Aucune préparation en attente pour le patient " + nomPatient + ".");
        }
    }

    private void consulterHistoriqueOrdonnances() {
        // TODO : Implémenter la consultation de l'historique des ordonnances
        System.out.println("Fonctionnalité non implémentée.");
    }
}



