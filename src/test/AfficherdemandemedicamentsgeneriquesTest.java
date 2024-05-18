package test;
import interfaces.IDocuments;

import org.junit.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.Assert.*;

public class AfficherdemandemedicamentsgeneriquesTest {

    // Afficher les demandes de médicaments génériques depuis le fichier CSV.
    @Test
    public void test_afficher_demande_medicaments_generiques() throws IOException {
        JPanel panel = new JPanel();
        IDocuments.afficherDemandeMedicamentsGeneriques(panel);

        assertEquals(1, panel.getComponentCount());

        assertTrue(panel.getComponent(0) instanceof JScrollPane);

        JScrollPane scrollPane = (JScrollPane) panel.getComponent(0);
        assertTrue(scrollPane.getViewport().getView() instanceof JPanel);

        JPanel demandePanel = (JPanel) scrollPane.getViewport().getView();
        assertEquals(21, demandePanel.getComponentCount());
    }

}