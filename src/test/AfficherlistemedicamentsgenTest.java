
package test;
import interfaces.IDocuments;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;


public class AfficherlistemedicamentsgenTest {

        @Test
        public void test_afficher_med_gen() {
            JPanel panel = new JPanel();
            IDocuments.afficherListeMedicamentsGen(panel);
            Component[] components = panel.getComponents();
            Assert.assertTrue(components.length > 0);

            for (Component component : components) {
                Assert.assertEquals(false, component instanceof JLabel);
            }
        }

        @Test
        public void test_envoie_null_si_panel_null() {
            Assert.assertThrows(NullPointerException.class, () -> IDocuments.afficherListeMedicamentsGen(null));
        }
}