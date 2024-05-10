package test;

import io.EcritureOrdonnancesCsv;

import org.junit.Test;

import java.util.Date;
import java.util.Stack;

import static org.junit.Assert.*;

public class EcrireordonnancecsvTest {


    // Successfully writes a new line to the CSV file with the given parameters.
    @Test
    public void test_writes_new_line_to_CSV_file() {
        // Arrange
        String referenceMedecin = "medecin1";
        String referencePatient = "patient1";
        Date datePrescription = new Date();
        Stack<String> medicaments = new Stack<>();
        medicaments.push("medicament1");
        medicaments.push("medicament2");

        // Act
        EcritureOrdonnancesCsv.ecrireOrdonnanceCsv(referenceMedecin, referencePatient, datePrescription, medicaments);

        // Assert
        // Verify that the line is written to the CSV file
        // and contains the correct data
    }

    // Handles empty stack of medicaments.
    @Test
    public void test_handles_empty_stack_of_medicaments() {
        // Arrange
        String referenceMedecin = "medecin1";
        String referencePatient = "patient1";
        Date datePrescription = new Date();
        Stack<String> medicaments = new Stack<>();

        // Act
        EcritureOrdonnancesCsv.ecrireOrdonnanceCsv(referenceMedecin, referencePatient, datePrescription, medicaments);

        // Assert
        // Verify that the line is written to the CSV file
        // and contains the correct data (empty medicaments)
    }

}