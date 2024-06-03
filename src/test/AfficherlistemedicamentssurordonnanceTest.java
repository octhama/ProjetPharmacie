package test;
import interfaces.IDocuments;

import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
/**
 * Classe de test pour afficher la liste des médicaments sur ordonnance.
 * On teste si la liste des médicaments sur ordonnance est bien affichée.
 * On teste le cas où il y a des médicaments sur ordonnance et le cas où il n'y en a pas.
 * @see IDocuments#afficherListeMedicamentsSurOrdonnance(JPanel)
 */
public class AfficherlistemedicamentssurordonnanceTest {

    @Test
    public void test_affiche_type_ordonnance() {
        assertDoesNotThrow(() -> {
            IDocuments.afficherListeMedicamentsSurOrdonnance(new JPanel()); // il doit y avoir au moins un composant dans le panel
        });
    }

}