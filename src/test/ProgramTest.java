package test;

import enums.ETypeMedicament;
import io.LectureMedicamentsCsv;
import org.junit.Test;
import pharmacie.Medicament;
import pharmacie.Pharmacie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Classe de test pour le programme principal.
 * On teste le chargement et l'ajout des médicaments depuis un fichier CSV.
 * @see Pharmacie
 */
public class ProgramTest {
    @Test
    public void test_loading_and_ajout_medicaments_depuis_csv() throws IOException {
        Pharmacie pharmacie = new Pharmacie();
        List<Medicament> medicaments = new ArrayList<>();
        LectureMedicamentsCsv.loadMedicaments(medicaments, "src/data/medicaments.csv"); // Charger les médicaments depuis un fichier CSV
        pharmacie.setMedicaments(medicaments);
        assertEquals(1, pharmacie.getMedicaments().size()); // le nombre de médicaments dans la pharmacie doit être égal au nombre de médicaments dans le fichier CSV
    }

}