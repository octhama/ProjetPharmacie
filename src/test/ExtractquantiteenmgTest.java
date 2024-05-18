package test;
import interfaces.IDocuments;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExtractquantiteenmgTest {

    @Test
    public void test_regex_ok_med() {
        String medicationName = "Medication 500mg";
        String quantity = IDocuments.extractQuantiteEnMg(medicationName);
        assertEquals("500mg", quantity);
    }

    @Test
    public void test_regex_sans_espace_ok_med() {
        String medicationName = "Medication500mg";
        String quantity = IDocuments.extractQuantiteEnMg(medicationName);
        assertEquals("500mg", quantity);
    }

}