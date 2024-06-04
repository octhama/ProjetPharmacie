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
 * Classe de test pour afficher la liste des médicaments.
 * On teste si la liste des médicaments est bien affichée.
 * On teste le cas où il y a des médicaments et le cas où il n'y en a pas.
 * @see IDocuments#afficherListeMedicaments(JPanel)
 */
public class AfficherlistemedicamentsTest {

    @Test
    public void test_affiche_liste_med() {
        JPanel panel = new JPanel();
        UiGui.pharmacie.setMedicaments(List.of(new Medicament("Doliprane", "Paracétamol", "500mg", "Comprimé", false)));
        IDocuments.afficherListeMedicaments(panel);
        JScrollPane scrollPane = (JScrollPane) panel.getComponent(0);
        JPanel medicamentsPanel = (JPanel) scrollPane.getViewport().getView();
        int actualMedicamentsCount = medicamentsPanel.getComponentCount();
        assertEquals(1, actualMedicamentsCount); // le nombre de médicaments affichés doit être égal au nombre de médicaments dans la pharmacie
    }

    @Test
    public void test_med_vide() {
        JPanel panel = new JPanel();
        UiGui.pharmacie.setMedicaments(new ArrayList<>());
        IDocuments.afficherListeMedicaments(panel);
        JScrollPane scrollPane = (JScrollPane) panel.getComponent(0);
        JPanel medicamentsPanel = (JPanel) scrollPane.getViewport().getView();
        int actualMedicamentsCount = medicamentsPanel.getComponentCount();
        assertEquals(0, actualMedicamentsCount); // le nombre de médicaments affichés doit être égal au nombre de médicaments dans la pharmacie
    }

}
