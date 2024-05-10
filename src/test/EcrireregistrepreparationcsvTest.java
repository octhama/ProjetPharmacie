package test;

import io.EcritureRegistrePreparationCsv;

import org.junit.Test;
import pharmacie.Medicament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EcrireregistrepreparationcsvTest {


    // writes each selected medicament to a new line in the CSV file with a unique ID, name, quantity, and current date
    @Test
    public void test_writes_selected_medicaments_to_csv_file() {
        // Arrange
        List<Medicament> selectedMedicaments = new ArrayList<>();
        selectedMedicaments.add(new Medicament("Medicament 1"));
        selectedMedicaments.add(new Medicament("Medicament 2"));
        List<Integer> selectedQuantities = new ArrayList<>();
        selectedQuantities.add(10);
        selectedQuantities.add(5);
        String csvFilePath = "test.csv";

        // Act
        EcritureRegistrePreparationCsv.ecrireRegistrePreparationCsv(selectedMedicaments, selectedQuantities, csvFilePath);

        // Assert
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                String[] parts = line.split(",");
                assertEquals(4, parts.length); // Ensure that each line has four elements (idUnique, nom, quantite, date)
                assertTrue(parts[0].startsWith("CMD-PREP")); // Ensure that the idUnique starts with "CMD-PREP"
                assertNotNull(parts[1]); // Ensure that the nom is not null
                assertNotNull(parts[2]); // Ensure that the quantite is not null
                assertNotNull(parts[3]); // Ensure that the date is not null
            }
            assertEquals(2, lineCount); // Ensure that there are two lines in the CSV file
        } catch (IOException e) {
            fail("An exception occurred while reading the CSV file");
        }
    }

    // throws an IOException if there is an error while writing to the CSV file
    @Test
    public void test_throws_io_exception_when_writing_to_csv_file() {
        // Arrange
        List<Medicament> selectedMedicaments = new ArrayList<>();
        selectedMedicaments.add(new Medicament("Medicament 1"));
        List<Integer> selectedQuantities = new ArrayList<>();
        selectedQuantities.add(10);
        String csvFilePath = "nonexistent_directory/test.csv";

        // Act and Assert
        assertThrows(IOException.class, () -> {
            EcritureRegistrePreparationCsv.ecrireRegistrePreparationCsv(selectedMedicaments, selectedQuantities, csvFilePath);
        });
    }

}