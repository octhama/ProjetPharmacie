import io.LectureMedicamentsCsv;
import pharmacie.Medicament;
import pharmacie.Pharmacie;
import ui.UiGui;

import java.io.IOException;
import java.util.List;

/**
 * Classe principale du programme.
 */

public class Program {
    public static void main(String[] args) throws IOException {
        // version graphique
        // Créer une pharmacie
        Pharmacie pharmacie = new Pharmacie();

        // Chargement des médicaments depuis un fichier CSV
        try {
            List<Medicament> medicaments = LectureMedicamentsCsv.lireMedicamentsCsv("src/data/medicaments.csv");
            pharmacie.ajouterMedicaments(medicaments); // Ajouter les médicaments à la pharmacie
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Créer une interface graphique pour la pharmacie
        try {
            UiGui gui = new UiGui(pharmacie);
            gui.afficher();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // version console
        /*Pharmacie pharmacie = new Pharmacie();
        CliUi cli = new CliUi(pharmacie);
        while (true) {
            cli.afficherMenu();*/
    }
}
