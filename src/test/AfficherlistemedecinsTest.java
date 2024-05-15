package test;
import interfaces.IDocuments;

import org.junit.Test;
import pharmacie.Medecin;
import ui.UiGui;

import javax.swing.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AfficherlistemedecinsTest {

    @Test
    public void test_displayWindow() {
        JPanel panel = new JPanel();
        IDocuments.afficherListeMedecins(panel);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
        assertNotNull(frame);   // Vérifiez si le frame n'est pas null
        assertEquals("Liste des médecins", frame.getTitle());
        assertEquals(600, frame.getWidth());
        assertEquals(400, frame.getHeight());
    }

    @Test
    public void test_retrieveMedecinList() {
        JPanel panel = new JPanel();
        IDocuments.afficherListeMedecins(panel);
        Component[] components = panel.getComponents();
        JScrollPane scrollPane = (JScrollPane) components[0];
        JPanel medecinPanel = (JPanel) scrollPane.getViewport().getView();

        // Spécifiez le type de données contenu dans la liste medecins
        List<Medecin> medecins = UiGui.pharmacie.getMedecins();

        assertEquals(medecins.size(), medecinPanel.getComponentCount());
        int componentCount = medecinPanel.getComponentCount();
        for (int i = 0; i < componentCount; i++) {
            JLabel label = (JLabel) medecinPanel.getComponent(i);
            Medecin medecin = (Medecin) ((java.util.List<?>) medecins).get(i); // Castez le type de données contenu dans la liste medecins
            assertEquals(medecin.getPrenom() + " " + medecin.getNom() + " - " + medecin.getReference(), label.getText());
        }
    }

    @Test
    public void test_displayEmptyList() {
        JPanel panel = new JPanel();
        UiGui.pharmacie.setMedecins(new ArrayList<>());
        IDocuments.afficherListeMedecins(panel);
        Component[] components = panel.getComponents();
        JScrollPane scrollPane = (JScrollPane) components[0];
        JPanel medecinPanel = (JPanel) scrollPane.getViewport().getView();
        assertEquals(0, medecinPanel.getComponentCount());
    }

    @Test
    public void test_nullPanelParameter() {
        assertThrows(NullPointerException.class, () -> {
            IDocuments.afficherListeMedecins(null);
        });
    }

}