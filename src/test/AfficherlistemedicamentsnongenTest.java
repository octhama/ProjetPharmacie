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
    public void test_non_gen() {
        JPanel panel = new JPanel();
        List<Medicament> medicaments = new ArrayList<>();
        medicaments.add(new Medicament("Med1", "Labo1", "Mol1", "Ref1", false));
        medicaments.add(new Medicament("Med2", "Labo2", "Mol2", "Ref2", false));
        medicaments.add(new Medicament("Med3", "Labo3", "Mol3", "Ref3", false));

        UiGui.pharmacie.setMedicaments(medicaments);
        IDocuments.afficherListeMedicamentsNonGen(panel);
        assertEquals(1, panel.getComponentCount()); // le nombre de médicaments affichés doit être égal au nombre de médicaments non génériques
    }

}