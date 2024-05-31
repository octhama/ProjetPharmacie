package test;
import interfaces.IDocuments;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Classe de test pour vérifier l'extraction de la quantité en mg d'un médicament.
 * On teste si la quantité en mg est bien extraite.
 * @see IDocuments#extractQuantiteEnMg(String)
 */
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