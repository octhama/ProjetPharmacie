package test;
import io.EcritureRegistrePreparationCsv;
import pharmacie.Medicament;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EcrireregistrepreparationcsvTest {

    @Test
    public void test_ecrire_med_in_csv() {
        List<Medicament> selectedMedicaments = new ArrayList<>();
        selectedMedicaments.add(new Medicament("Med1", 10));
        selectedMedicaments.add(new Medicament("Med2", 5));

        List<Integer> selectedQuantities = new ArrayList<>();
        selectedQuantities.add(2);
        selectedQuantities.add(3);

        String csvFilePath = "test.csv";

        EcritureRegistrePreparationCsv.ecrireRegistrePreparationCsv(selectedMedicaments, selectedQuantities, csvFilePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Adaptez ce nombre selon le nombre de colonnes attendues
                assertEquals(4, parts.length);
                assertNotNull(parts[0]);
                assertNotNull(parts[1]);
                assertNotNull(parts[2]);
                assertNotNull(parts[3]);
                lineCount++;
            }
            assertEquals(selectedMedicaments.size(), lineCount);
        } catch (IOException e) {
            fail("Impossible de lire le fichier CSV");
        }
    }
}
