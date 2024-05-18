package test;
import interfaces.IDocuments;

import org.junit.Test;

import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class AfficherlistemedicamentsnongenondemandTest {

        @Test
        public void test_affiche_liste_med_non_gen() {
            JPanel panel = new JPanel();
            IDocuments.afficherListeMedicamentsNonGenOnDemand(panel);
            assertEquals(0, panel.getComponentCount());
        }

        @Test
        public void test_input_panel_vide() {
            IDocuments.afficherListeMedicamentsNonGenOnDemand(null);
            JPanel emptyPanel = new JPanel();
            IDocuments.afficherListeMedicamentsNonGenOnDemand(emptyPanel);
        }


}