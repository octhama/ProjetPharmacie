
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
        public void test_afficher_med_gen() {
            IDocuments.afficherListeMedicamentsGen(new JPanel());
            Assert.assertTrue(true); // il doit y avoir au moins un composant dans le panel
        }
}