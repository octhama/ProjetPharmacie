package test;
import interfaces.IDocuments;

import org.junit.Test;
import pharmacie.Pharmacie;
import ui.UiGui;


public class AjoutermedicamentTest {
        @Test
        public void test_boite_de_diag_ok() {
            UiGui.pharmacie = new Pharmacie();
            try {
                IDocuments.ajouterMedicament();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}