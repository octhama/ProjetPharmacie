package test;
import interfaces.IDocuments;

import org.junit.Test;
import pharmacie.Pharmacie;
import ui.UiGui;
/**
 * Classe de test pour ajouter un médicament.
 * On teste si l'ajout d'un médicament est bien effectué.
 * @see IDocuments#ajouterMedicament()
 */

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