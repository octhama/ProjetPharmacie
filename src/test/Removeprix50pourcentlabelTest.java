
package test;
import interfaces.IDocuments;

import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;
/**
 * Classe de test pour 50% de réduction.
 * On teste si le label du prix 50% est bien supprimé.
 * @see IDocuments#removePrix50PourcentLabel(JPanel)
 */
public class Removeprix50pourcentlabelTest {

    @Test
    public void test_suppPrix50PourcentLabel_labelOK() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("| Prix mg 50%");
        panel.add(label);

        IDocuments.removePrix50PourcentLabel(panel);
        assertEquals(0, panel.getComponentCount()); // le label doit être supprimé
    }

    @Test
    public void test_suppPrix50PourcentLabel_panelVide() {
        JPanel panel = new JPanel();
        IDocuments.removePrix50PourcentLabel(panel);
        assertEquals(0, panel.getComponentCount()); // le panel doit être vide
    }

}