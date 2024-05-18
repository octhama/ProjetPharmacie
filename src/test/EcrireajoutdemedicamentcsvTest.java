package test;
import enums.ETypeMedicament;
import io.EcritureMedicamentsCsv;

import org.junit.Test;
import pharmacie.Medicament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class EcrireajoutdemedicamentcsvTest {

    @Test
    public void test_ecrire_med_dans_le_bon_format() throws IOException {

        String fichierCsv = "test.csv";
        Medicament medicament = new Medicament("Medicament1", 10.0, ETypeMedicament.VENTE_LIBRE, true, 5);

        EcritureMedicamentsCsv.ecrireAjoutDeMedicamentCsv(fichierCsv, medicament);

        List<String> lines = Files.readAllLines(Paths.get(fichierCsv));
        String lastLine = lines.get(lines.size() - 1);
        String[] values = lastLine.split(",");
        assertEquals(medicament.getNom(), values[0]);
        assertEquals(Double.toString(medicament.getPrix()), values[1]);
        assertEquals(medicament.getType().toString(), values[2]);
        assertEquals(Boolean.toString(medicament.isGenerique()), values[3]);
        assertEquals(Integer.toString(medicament.getQuantiteEnStock()), values[4]);
    }

}