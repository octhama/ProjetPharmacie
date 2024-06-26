import io.LectureMedicamentsCsv;
import io.LectureRegistrePreparation;
import pharmacie.Medicament;
import pharmacie.Pharmacie;
import pharmacie.Preparation;
import ui.UiGui;

import java.io.IOException;
import java.util.List;

/**
 * Classe principale du programme.
 * On peut charger les médicaments depuis un fichier CSV, charger les préparations depuis un fichier CSV et afficher une interface graphique pour la pharmacie.
 * @see pharmacie.Pharmacie
 * @see pharmacie.Medicament
 * @see pharmacie.Preparation
 * @see io.LectureMedicamentsCsv
 * @see io.LectureRegistrePreparation
 * @see ui.UiGui
 * @see ui.CliUi
 * @see exceptions.ExeptionRuptureDeStock
 * @see enums.ETypeMedicament
 */

public class Program {
    public static void main(String[] args) throws IOException {
        // Chargement des médicaments depuis un fichier CSV
        Pharmacie pharmacie = new Pharmacie();
        try {
            List<Medicament> medicaments = LectureMedicamentsCsv.lireMedicamentsCsv("src/data/medicaments.csv");
            pharmacie.ajouterMedicaments(medicaments); // Ajouter les médicaments à la pharmacie
        } catch (IOException e) {
            e.printStackTrace();
        }
        String cheminFichier = "src/data/registrepreparation.csv";
        try {
            List<Preparation> preparations = LectureRegistrePreparation.lireRegistrePreparation(cheminFichier);
            for (Preparation preparation : preparations) {
                System.out.println("ID: " + preparation.getIdUnique());
                System.out.println("Nom: " + preparation.getNom());
                System.out.println("Quantité: " + preparation.getQuantite());
                System.out.println("Date: " + preparation.getDate());
                System.out.println("-----------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Créer une interface graphique pour la pharmacie et l'afficher (Version graphique utilisée)
        try {
            UiGui gui = new UiGui(pharmacie);
            gui.afficher();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // version console (non utilisée)
        /*Pharmacie pharmacie = new Pharmacie();
        CliUi cli = new CliUi(pharmacie);
        while (true) {
            cli.afficherMenu();*/
    }
}
