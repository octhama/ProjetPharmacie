package test;
import enums.ETypeMedicament;
import interfaces.IDocuments;

import org.junit.Test;
import pharmacie.Medicament;
import ui.UiGui;

import java.io.IOException;

import static org.junit.Assert.*;
/**
 * Classe de test pour retirer un médicament.
 * On teste si le médicament est bien retiré.
 * @see IDocuments#retirerMedicament()
 */
public class RetirermedicamentTest {

    @Test
    public void test_sup_med_existant_ok() throws IOException {
        IDocuments documents = new IDocuments() {};

        Medicament medication = new Medicament("Medication A", 10.0, ETypeMedicament.VENTE_LIBRE, false, 100);
        UiGui.pharmacie.ajouterMedicament(medication);

        IDocuments.retirerMedicament();

        assertTrue(UiGui.pharmacie.getMedicaments().contains(medication));
    }

}