package test;
import interfaces.IDocuments;

import org.junit.Test;

import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour afficher la liste des médicaments non génériques.
 * On teste si la liste des médicaments non génériques est bien affichée.
 * On teste le cas où il y a des médicaments non génériques et le cas où il n'y en a pas.
 * @see IDocuments#afficherListeMedicamentsNonGenOnDemand(JPanel)
 */
public class AfficherlistemedicamentsnongenondemandTest {

        @Test
        public void test_affiche_liste_med_non_gen() {
            JPanel panel = new JPanel();
            IDocuments.afficherListeMedicamentsNonGenOnDemand(panel);
            assertEquals(0, panel.getComponentCount()); // le nombre de médicaments affichés doit être égal au nombre de médicaments non génériques
        }

}