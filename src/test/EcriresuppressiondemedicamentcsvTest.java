package test;

import io.EcritureMedicamentsCsv;

import org.junit.Test;
import pharmacie.Medicament;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class EcriresuppressiondemedicamentcsvTest {

    // Given a null Medicament object and a valid CSV file path, when the method is called, then an IOException is thrown.
    @Test
    public void test_removeMedicamentLine_nullMedicamentAndValidCsvPath() {
        // Arrange
        Medicament medicament = null;
        String fichierCsv = "test.csv";
    
        // Act and Assert
        assertThrows(IOException.class, () -> {
            EcritureMedicamentsCsv.ecrireSuppressionDeMedicamentCsv(medicament, fichierCsv);
        });
    }

}