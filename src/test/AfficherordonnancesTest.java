package test;
import interfaces.IDocuments;

import org.junit.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.Assert.*;
/**
 * Classe de test pour afficher les ordonnances.
 * On teste si la liste des ordonnances est bien affichée.
 * On teste le cas où il y a des ordonnances et le cas où il n'y en a pas.
 * @see IDocuments#afficherOrdonnances(JPanel)
 */
public class AfficherordonnancesTest {


    @Test
    public void test_affiche_prescrip() throws IOException {
        JPanel panel = new JPanel();
        IDocuments.afficherOrdonnances(panel);
        assertNotNull(panel.getComponent(0));
    }

    @Test
    public void test_prescrip_liste_vide() throws IOException {
        JPanel panel = new JPanel();
        IDocuments.afficherOrdonnances(panel);
        assertEquals(1, panel.getComponentCount());
    }

}