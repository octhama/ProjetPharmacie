
package test;
import interfaces.IDocuments;

import org.junit.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class AfficherregistrepreparationTest {

        @Test
        public void test_readPreparationDataDuCSV() throws Exception {
            JPanel panelAffichageInfo = new JPanel();
            IDocuments.afficherRegistrePreparation(panelAffichageInfo);
            assertEquals(1, panelAffichageInfo.getComponentCount());
        }

        @Test
        public void test_fichierCSVvide() throws Exception {
            JPanel panelAffichageInfo = new JPanel();
            IDocuments.afficherRegistrePreparation(panelAffichageInfo);
            assertEquals(1, panelAffichageInfo.getComponentCount());
        }
}