package test;

import interfaces.IDocuments;

import org.junit.Test;
import pharmacie.Medicament;
import ui.UiGui;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AfficherlistemedicamentsTest {

    @Test
    public void test_affiche_med() {
        JPanel panel = new JPanel();

        IDocuments.afficherListeMedicaments(panel);

        List<Medicament> expectedMedicaments = UiGui.pharmacie.getMedicaments();
        JScrollPane scrollPane = (JScrollPane) panel.getComponent(0);
        JPanel medicamentsPanel = (JPanel) scrollPane.getViewport().getView();
        int actualMedicamentsCount = medicamentsPanel.getComponentCount();
        assertEquals(expectedMedicaments.size(), actualMedicamentsCount);
    }

    @Test
    public void test_med_vide() {
        JPanel panel = new JPanel();
        UiGui.pharmacie.setMedicaments(new ArrayList<>());

        IDocuments.afficherListeMedicaments(panel);

        JScrollPane scrollPane = (JScrollPane) panel.getComponent(0);
        JPanel medicamentsPanel = (JPanel) scrollPane.getViewport().getView();
        int actualMedicamentsCount = medicamentsPanel.getComponentCount();
        assertEquals(0, actualMedicamentsCount);
    }

}
