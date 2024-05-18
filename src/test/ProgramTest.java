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

public class ProgramTest {

    @Test
    public void test_loads_and_adds_medicaments_from_csv() throws IOException {
        Pharmacie pharmacie = new Pharmacie();
        List<Medicament> expectedMedicaments = new ArrayList<>();
        expectedMedicaments.add(new Medicament("Medicament1", 10.0, ETypeMedicament.VENTE_LIBRE, true, 100));
        expectedMedicaments.add(new Medicament("Medicament2", 20.0, ETypeMedicament.ORDONNANCE, false, 200));
        expectedMedicaments.add(new Medicament("Medicament3", 30.0, ETypeMedicament.VENTE_LIBRE, true, 300));

        LectureMedicamentsCsv.lireMedicamentsCsv("src/data/medicaments.csv");

        assertEquals(expectedMedicaments, pharmacie.getMedicaments());
    }

}