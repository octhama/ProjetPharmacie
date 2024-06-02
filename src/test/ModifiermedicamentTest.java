
package test;
import interfaces.IDocuments;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
/**
 * Classe de test pour modifier un médicament.
 * On teste si la modification d'un médicament est bien effectuée.
 * @see IDocuments#modifierMedicament()
 */
public class ModifiermedicamentTest {

    @Test
    public void test_boite_diag_modif_med_ok() {
        try {
            IDocuments.modifierMedicament();
        } catch (IOException e) {
            fail("Erreur lors de la modification du médicament" + e.getMessage());
        }
    }

}