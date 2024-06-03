
package test;
import interfaces.IDocuments;

import org.junit.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Classe de test pour afficher le registre de préparation.
 * On teste si le registre de préparation est bien affiché.
 * On teste le cas où il y a des préparations et le cas où il n'y en a pas.
 * @see IDocuments#afficherRegistrePreparation(JPanel)
 */
public class AfficherregistrepreparationTest {

        @Test
        public void test_readPreparationDataDuCSV() throws Exception {
            JPanel panelAffichageInfo = new JPanel();
            IDocuments.afficherRegistrePreparation(panelAffichageInfo);
            assertEquals(1, panelAffichageInfo.getComponentCount()); // 1 s'il n'y a pas de préparations
        }

        @Test
        public void test_fichierCSVvide() throws Exception {
            JPanel panelAffichageInfo = new JPanel();
            IDocuments.afficherRegistrePreparation(panelAffichageInfo);
            assertEquals(1, panelAffichageInfo.getComponentCount()); // 1 s'il n'y a pas de préparations
        }
}