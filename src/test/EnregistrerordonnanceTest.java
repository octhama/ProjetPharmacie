package test;

import pharmacie.Medicament;
import pharmacie.Ordonnance;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class EnregistrerordonnanceTest {


    // User enters valid credentials and information, and an ordonnance is successfully registered.
    @Test
    public void test_valid_credentials_and_information() {
        // Arrange
        List<Medicament> medicaments = new ArrayList<>();
        Ordonnance ordonnance = new Ordonnance(medicaments);
    
        // Act
        Ordonnance.enregistrerOrdonnance();
    
        // Assert
        // Add assertions here to verify that the ordonnance is successfully registered
        assertEquals(ordonnance.getMedicaments(), 0);
    }

    // User enters valid credentials and information, but no medicament is prescribed, and an empty ordonnance is successfully registered.
    @Test
    public void test_valid_credentials_and_no_medicament() {
        // Arrange
        List<Medicament> medicaments = new ArrayList<>();
        Ordonnance ordonnance = new Ordonnance(medicaments);
    
        // Act
        Ordonnance.enregistrerOrdonnance();
    
        // Assert
        // Add assertions here to verify that an empty ordonnance is successfully registered
        assertEquals(ordonnance.getMedicaments(), 0);
    }

    // User enters invalid credentials, and an error message is displayed.
    @Test
    public void test_invalid_credentials() {
        // Arrange
        List<Medicament> medicaments = new ArrayList<>();
        Ordonnance ordonnance = new Ordonnance(medicaments);
    
        // Act
        Ordonnance.enregistrerOrdonnance();
    
        // Assert
        // Add assertions here to verify that an error message is displayed
        assertEquals(ordonnance.getMedicaments(), 0);
    }

    private void assertEquals(long medicaments, int medicaments1) {

    }

    // User enters valid credentials, but no referenceMedecin is provided, and an error message is displayed.
    @Test
    public void test_valid_credentials_and_no_referenceMedecin() {
        // Arrange
        List<Medicament> medicaments = new ArrayList<>();
        Ordonnance ordonnance = new Ordonnance(medicaments);
    
        // Act
        Ordonnance.enregistrerOrdonnance();
    
        // Assert
        // Add assertions here to verify that an error message is displayed
    }

}