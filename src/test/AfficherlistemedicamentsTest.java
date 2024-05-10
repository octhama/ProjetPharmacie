package test;

import interfaces.IDocuments;

import org.junit.Test;
import pharmacie.Medicament;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AfficherlistemedicamentsTest {


    // The method correctly creates a JPanel to display the suggested medications.
    @Test
    public void test_create_jpanel() {
        JPanel panel = new JPanel();
        List<Medicament> suggestions = new ArrayList<>();

        IDocuments.afficherListeMedicaments(panel, suggestions);

        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JScrollPane);
        JScrollPane scrollPane = (JScrollPane) components[0];
        Component view = scrollPane.getViewport().getView();
        assertTrue(view instanceof JPanel);
    }

    // The method correctly handles an empty list of suggested medications.
    @Test
    public void test_empty_list() {
        JPanel panel = new JPanel();
        List<Medicament> suggestions = new ArrayList<>();

        IDocuments.afficherListeMedicaments(panel, suggestions);

        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JScrollPane);
        JScrollPane scrollPane = (JScrollPane) components[0];
        Component view = scrollPane.getViewport().getView();
        assertTrue(view instanceof JPanel);
        JPanel medicamentsPanel = (JPanel) view;
        assertEquals(0, medicamentsPanel.getComponentCount());
    }

}