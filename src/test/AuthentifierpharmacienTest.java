package test;

import pharmacie.Pharmacien;

import org.junit.Test;
import static org.junit.Assert.*;

public class AuthentifierpharmacienTest {


    // Returns true when the provided id and password match a line in the CSV file.
    @Test
    public void test_returns_true_when_id_and_password_match() {
        boolean result = Pharmacien.authentifierPharmacien("id1", "password1");
        assertTrue(result);
    }

    // Returns false when the provided id and password do not match any line in the CSV file.
    @Test
    public void test_returns_false_when_id_and_password_do_not_match() {
        boolean result = Pharmacien.authentifierPharmacien("id3", "password3");
        assertFalse(result);
    }

    // Returns false when the CSV file is empty.
    @Test
    public void test_returns_false_when_csv_file_is_empty() {
        boolean result = Pharmacien.authentifierPharmacien("id1", "password1");
        assertFalse(result);
    }

    // Returns false when the CSV file has only one column.
    @Test
    public void test_returns_false_when_csv_file_has_only_one_column() {
        boolean result = Pharmacien.authentifierPharmacien("id1", "password1");
        assertFalse(result);
    }

}