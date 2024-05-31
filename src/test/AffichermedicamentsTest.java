
package test;
import interfaces.IDocuments;

import org.junit.Test;
import pharmacie.Medicament;
import ui.UiGui;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;
/**
 * Classe de test pour afficher les médicaments.
 * On teste si les médicaments sont bien affichés.
 * On teste le cas où il y a des médicaments et le cas où il n'y en a pas.
 * @see IDocuments#afficherMedicaments(JPanel)
 */
public class AffichermedicamentsTest {

    // Afficher les médicaments avec une quantité en stock supérieure ou égale à 20 unités.
    @Test
    public void test_afficher_medicaments_quantite_superieure_ou_egale_20() {
        JPanel panel = new JPanel();
        IDocuments.afficherMedicaments(panel);

        Component[] components = panel.getComponents();
        JScrollPane scrollPane = (JScrollPane) components[0];
        JPanel medicamentsPanel = (JPanel) scrollPane.getViewport().getView();

        int expectedCount = 0;
        for (Medicament medicament : UiGui.pharmacie.getMedicaments()) {
            if (medicament.getQuantiteEnStock() >= 20) {
                expectedCount++;
            }
        }

        assertEquals(expectedCount, medicamentsPanel.getComponentCount());
    }

}