package test;

import interfaces.IDocuments;

import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class AfficherdemandemedicamentsgeneriquesTest {


    // The method should display a window titled 'Demandes de médicaments génériques' with a size of 600x400 pixels.
    @Test
    public void test_display_window() {
        JPanel panel = new JPanel();
        IDocuments.afficherDemandeMedicamentsGeneriques(panel);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
        assertNotNull(frame);
        assertEquals("Demandes de médicaments génériques", frame.getTitle());
        assertEquals(600, frame.getWidth());
        assertEquals(400, frame.getHeight());
    }

    // If there are no demandes of generic versions of medicines, the method should display an empty panel.
    @Test
    public void test_display_empty_panel() {
        JPanel panel = new JPanel();
        IDocuments.afficherDemandeMedicamentsGeneriques(panel);

        JScrollPane scrollPane = (JScrollPane) panel.getComponent(0);
        JPanel demandePanel = (JPanel) scrollPane.getViewport().getView();

        assertEquals(0, demandePanel.getComponentCount());
    }

}