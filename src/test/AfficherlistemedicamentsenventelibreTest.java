package test;
import enums.ETypeMedicament;
import interfaces.IDocuments;

import org.junit.Test;
import pharmacie.Medicament;
import ui.UiGui;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Classe de test pour la méthode afficherListeMedicamentsEnVenteLibre.
 * On teste si la liste des médicaments en vente libre est bien affichée.
 * On teste le cas où il y a des médicaments en vente libre et le cas où il n'y en a pas.
 * @see IDocuments#afficherListeMedicamentsEnVenteLibre(JPanel)
 */

public class AfficherlistemedicamentsenventelibreTest {

    @Test
    public void test_tout_medicaments() {
        JPanel panel = new JPanel();
        List<Medicament> medicaments = new ArrayList<>();
        medicaments.add(new Medicament("Medicine 1", 10.0, ETypeMedicament.VENTE_LIBRE, false, 20));
        medicaments.add(new Medicament("Medicine 2", 15.0, ETypeMedicament.ORDONNANCE, false, 10));
        UiGui.pharmacie.setMedicaments(medicaments);

        IDocuments.afficherListeMedicamentsEnVenteLibre(panel);

        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JScrollPane);
        JScrollPane scrollPane = (JScrollPane) components[0];
        Component view = scrollPane.getViewport().getView();
        assertTrue(view instanceof JPanel);
        JPanel medicamentsPanel = (JPanel) view;
        Component[] labels = medicamentsPanel.getComponents();
        assertEquals(1, labels.length);
        assertTrue(labels[0] instanceof JLabel);
        JLabel label = (JLabel) labels[0];
        assertEquals("<html><b>Nom:</b> Medicine 1<br><b>Prix:</b> 10.0 €<br><b>Type:</b> VENTE_LIBRE<br><b>Générique:</b> Non<br><b>Quantité en stock:</b> 20</html>", label.getText());
    }

    @Test
    public void test_pas_de_medicaments() {
        JPanel panel = new JPanel();
        UiGui.pharmacie.setMedicaments(new ArrayList<>());

        IDocuments.afficherListeMedicamentsEnVenteLibre(panel);

        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JScrollPane);
        JScrollPane scrollPane = (JScrollPane) components[0];
        Component view = scrollPane.getViewport().getView();
        assertTrue(view instanceof JPanel);
        JPanel medicamentsPanel = (JPanel) view;
        Component[] labels = medicamentsPanel.getComponents();
        assertEquals(0, labels.length);
    }

}