package test;

import interfaces.IDocuments;

import org.junit.Test;
import pharmacie.Medicament;
import ui.UiGui;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Classe de test pour la méthode afficherListeMedicamentsNonGen.
 * On teste si la liste des médicaments non génériques est bien affichée.
 * On teste si la liste des médicaments génériques est bien affichée.
 * @see IDocuments#afficherListeMedicamentsNonGen(JPanel)
 */
public class AfficherlistemedicamentsnongenTest {

    @Test
    public void test_non_generique_med() {
        JPanel panel = new JPanel();
        IDocuments.afficherListeMedicamentsNonGen(panel);
        assertEquals(1, panel.getComponentCount());
    }

    @Test
    public void test_generique_med() {
        JPanel panel = new JPanel();
        List<Medicament> medicaments = new ArrayList<>();
        UiGui.pharmacie.setMedicaments(medicaments);
        IDocuments.afficherListeMedicamentsNonGen(panel);
        assertEquals(1, panel.getComponentCount());
    }

}