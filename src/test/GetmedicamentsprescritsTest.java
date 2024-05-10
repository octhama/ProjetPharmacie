package test;

import io.LectureMedicamentsCsv;

// Returns an empty list when the patient reference is not found in the CSV file
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class GetmedicamentsprescritsTest {

        @Test
        public void test_returns_empty_list_when_patient_reference_not_found() {
            // Arrange
            String referencePatient = "nonexistent_reference";
            List<String> expected = new ArrayList<>();

            // Act
            List<String> actual = LectureMedicamentsCsv.getMedicamentsPrescrits(referencePatient);

            // Assert
            assertEquals(expected, actual);
        }


}