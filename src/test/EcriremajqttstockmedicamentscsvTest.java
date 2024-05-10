package test;

import io.EcritureMedicamentsCsv;

import org.junit.Test;
import pharmacie.Medicament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class EcriremajqttstockmedicamentscsvTest {


    // Given a valid list of Medicament objects and a valid CSV file path, the method should successfully update the quantity in stock of each corresponding Medicament in the CSV file and save the changes to the file.
    @Test
    public void test_validListAndFilePath() throws IOException {
        // Arrange
        List<Medicament> medicaments = new ArrayList<>();
        medicaments.add(new Medicament("Med1", 10));
        medicaments.add(new Medicament("Med2", 20));
        String fichierCsv = "path/to/csv/file.csv";
    
        // Act
        EcritureMedicamentsCsv.ecrireMajQttStockMedicamentsCsv(medicaments, fichierCsv);
    
        // Assert
        List<String> expectedLines = Arrays.asList("Med1,0,null,false,10", "Med2,0,null,false,20");
        List<String> actualLines = Files.readAllLines(Paths.get(fichierCsv));
        assertEquals(expectedLines, actualLines);
    }

    // If the CSV file is empty, the method should not throw an exception and should not update any Medicament quantities.
    @Test
    public void test_emptyCsvFile() throws IOException {
        // Arrange
        List<Medicament> medicaments = new ArrayList<>();
        medicaments.add(new Medicament("Med1", 10));
        String fichierCsv = "path/to/empty/csv/file.csv";
        Files.createFile(Paths.get(fichierCsv));
    
        // Act
        EcritureMedicamentsCsv.ecrireMajQttStockMedicamentsCsv(medicaments, fichierCsv);
    
        // Assert
        List<String> expectedLines = Collections.emptyList();
        List<String> actualLines = Files.readAllLines(Paths.get(fichierCsv));
        assertEquals(expectedLines, actualLines);
    }

    // If the CSV file path is null, the method should throw a NullPointerException.
    @Test
    public void test_nullFilePath() throws IOException {
        // Arrange
        List<Medicament> medicaments = new ArrayList<>();
        medicaments.add(new Medicament("Med1", 10));
        String fichierCsv = null;
    
        // Act and Assert
        assertThrows(NullPointerException.class, () -> {
            EcritureMedicamentsCsv.ecrireMajQttStockMedicamentsCsv(medicaments, fichierCsv);
        });
    }

    // If the list of Medicament objects is null, the method should throw a NullPointerException.
    @Test
    public void test_nullMedicamentsList() throws IOException {
        // Arrange
        List<Medicament> medicaments = null;
        String fichierCsv = "path/to/csv/file.csv";
    
        // Act and Assert
        assertThrows(NullPointerException.class, () -> {
            EcritureMedicamentsCsv.ecrireMajQttStockMedicamentsCsv(medicaments, fichierCsv);
        });
    }

}