package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import enums.ETypeMedicament;

import io.LectureMedicamentsCsv;
import org.junit.Test;
import pharmacie.Medicament;
import pharmacie.Pharmacie;
import ui.UiGui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    // Loads a list of Medicament objects from a CSV file and adds them to a Pharmacie object
    @Test
    public void test_load_medicaments_from_csv() throws IOException {
        Pharmacie pharmacie = new Pharmacie();
        List<Medicament> expectedMedicaments = new ArrayList<>();
        expectedMedicaments.add(new Medicament("Medicament1", 10.0, ETypeMedicament.VENTE_LIBRE, true, 5));
        expectedMedicaments.add(new Medicament("Medicament2", 20.0, ETypeMedicament.ORDONNANCE, false, 10));
    
        List<Medicament> actualMedicaments = LectureMedicamentsCsv.lireMedicamentsCsv("src/data/medicaments.csv");
    
        assertEquals(expectedMedicaments, actualMedicaments);
        assertEquals(expectedMedicaments.size(), pharmacie.getMedicaments().size());
        assertTrue(pharmacie.getMedicaments().containsAll(expectedMedicaments));
    }

    // Creates a UiGui object with the Pharmacie object and displays it
    @Test
    public void test_create_and_display_uigui() throws IOException {
        Pharmacie pharmacie = new Pharmacie();
        UiGui gui = new UiGui(pharmacie);
    
        gui.afficher();
    
        // Verify that the GUI is displayed (cannot be tested programmatically)
    }

    // Throws an IOException if the CSV file cannot be read
    @Test
    public void test_throw_ioexception_on_csv_read_error() {
        assertThrows(IOException.class, () -> {
            LectureMedicamentsCsv.lireMedicamentsCsv("invalid_file.csv");
        });
    }

    // Throws an IOException if the UiGui cannot be created
    @Test
    public void test_throw_ioexception_on_uigui_creation_error() {
        assertThrows(IOException.class, () -> {
            UiGui gui = new UiGui(null);
        });
    }

}