import io.LectureDemandeVersionGeneriqueCsv;
import io.LectureMedicamentsCsv;
import io.LectureRegistrePreparation;
import pharmacie.DemandeVersionGenerique;
import pharmacie.Medicament;
import pharmacie.Pharmacie;
import pharmacie.Preparation;
import ui.UiGui;

import java.io.IOException;
import java.util.List;

/**
 * Classe principale du programme.
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
        // Créer une interface graphique pour la pharmacie
        try {
            UiGui gui = new UiGui(pharmacie);
            gui.afficher();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
