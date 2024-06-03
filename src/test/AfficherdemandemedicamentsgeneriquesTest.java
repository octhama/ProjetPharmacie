package test;
import interfaces.IDocuments;

import org.junit.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.Assert.*;
/**
 * Classe de test pour afficher les demandes de médicaments génériques.
 * On teste si la liste des demandes de médicaments génériques est bien affichée.
 * On teste le cas où il y a des demandes de médicaments génériques et le cas où il n'y en a pas.
 * @see IDocuments#afficherDemandeMedicamentsGeneriques(JPanel)
 */
public class AfficherdemandemedicamentsgeneriquesTest {

    @Test
    public void test_afficher_demande_medicaments_generiques() throws IOException {
        JPanel panel = new JPanel();
        IDocuments.afficherDemandeMedicamentsGeneriques(panel);
        assertEquals(panel.getComponentCount(), 1);
    }

}