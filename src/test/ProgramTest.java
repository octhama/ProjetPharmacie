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
 * Classe de test pour lire les médicaments dans un fichier CSV.
 */
public class ProgramTest {
    @Test
    public void test_loading_and_ajout_medicaments_depuis_csv() throws IOException {
        Pharmacie pharmacie = new Pharmacie();
        List<Medicament> medicaments = new ArrayList<>();
        LectureMedicamentsCsv.loadMedicaments(medicaments, "src/data/medicaments.csv");
        pharmacie.setMedicaments(medicaments);
        assertEquals(1, pharmacie.getMedicaments().size());
    }

}