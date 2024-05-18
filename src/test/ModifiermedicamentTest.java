
package test;
import interfaces.IDocuments;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ModifiermedicamentTest {

    @Test
    public void test_boite_diag_modif_med_ok() {
        IDocuments documents = new IDocuments() {};

        try {
            IDocuments.modifierMedicament();
        } catch (IOException e) {
            fail("An exception occurred: " + e.getMessage());
        }
    }

}