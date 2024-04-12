package Pharmacie;

import Pharmacie.services.CommandeService;
import Pharmacie.services.GestionStockService;
import Pharmacie.services.OrdonnanceService;
import Pharmacie.ui.ConsoleUI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nom de la pharmacie : ");
        String nomPharmacie = scanner.nextLine();

        Pharmacie pharmacie = new Pharmacie(nomPharmacie);
        GestionStockService gestionStockService = new GestionStockService(pharmacie);
        CommandeService commandeService = new CommandeService(pharmacie);
        OrdonnanceService ordonnanceService = new OrdonnanceService(pharmacie);

        ConsoleUI ui = new ConsoleUI(pharmacie, gestionStockService, commandeService, ordonnanceService);
        ui.demarrer();
    }
}



