package test;

import io.EcritureRegistreDemandeVersionGeneriqueCsv;

import org.junit.Test;
import pharmacie.DemandeVersionGenerique;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EcriredemandesversiongeneriquecsvTest {


    // The method writes the demandes to a CSV file when the file does not exist.
    @Test
    public void test_writes_to_csv_when_file_does_not_exist() {
        // Arrange
        List<DemandeVersionGenerique> demandes = new ArrayList<>();
        demandes.add(new DemandeVersionGenerique("Medicament A", true));
        demandes.add(new DemandeVersionGenerique("Medicament B", false));

        // Act
        EcritureRegistreDemandeVersionGeneriqueCsv.ecrireDemandesVersionGeneriqueCsv(demandes);

        // Assert
        File file = new File("src/data/demandes_version_generique.csv");
        assertTrue(file.exists());
    }

    // The method throws an IOException when it fails to write to the CSV file.
    @Test
    public void test_throws_io_exception_when_fails_to_write_to_csv() {
        // Arrange
        List<DemandeVersionGenerique> demandes = new ArrayList<>();
        demandes.add(new DemandeVersionGenerique("Medicament A", true));
        demandes.add(new DemandeVersionGenerique("Medicament B", false));

        // Act and Assert
        assertThrows(IOException.class, () -> {
            EcritureRegistreDemandeVersionGeneriqueCsv.ecrireDemandesVersionGeneriqueCsv(demandes);
        });
    }


}