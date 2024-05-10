package test;

import io.LectureMedecinCsv;

import org.junit.Test;
import static org.junit.Assert.*;

public class AuthentifiermedecinTest {


    // Authenticates a valid medecin ID and password
    @Test
    public void test_authenticate_valid_medecin() {
        boolean result = LectureMedecinCsv.authentifierMedecin("valid_id", "valid_password");
        assertTrue(result);
    }

    // Returns false if the CSV file is not readable
    @Test
    public void test_csv_file_not_readable() {
        boolean result = LectureMedecinCsv.authentifierMedecin("id", "password");
        assertFalse(result);
    }

}