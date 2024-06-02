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
        String quantite = IDocuments.extractQuantiteEnMg(medicationName);
        assertEquals("500mg", quantite); // la quantité en mg doit être bien extraite
    }

    @Test
    public void test_regex_sans_espace_ok_med() {
        String medicationName = "Medication500mg";
        String quantite = IDocuments.extractQuantiteEnMg(medicationName);
        assertEquals("500mg", quantite); // la quantité en mg
    }

}