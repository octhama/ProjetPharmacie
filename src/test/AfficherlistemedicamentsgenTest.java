
package test;
import interfaces.IDocuments;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

/**
 * Classe de test pour afficher la liste des médicaments génériques.
 * On teste si la liste des médicaments génériques est bien affichée.
 * On teste le cas où il y a des médicaments génériques et le cas où il n'y en a pas.
 * @see IDocuments#afficherListeMedicamentsGen(JPanel)
 */
public class AfficherlistemedicamentsgenTest {

        @Test
        public void test_affiche_liste_med_gen() {
            JPanel panel = new JPanel();
            IDocuments.afficherListeMedicamentsGen(panel);
            Assert.assertEquals(0, panel.getComponentCount()); // le nombre de médicaments affichés doit être égal au nombre de médicaments génériques
        }
}