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
    public void test_affiche_med() {
        JPanel panel = new JPanel();
        UiGui.pharmacie.setMedicaments(new ArrayList<>());
        UiGui.pharmacie.getMedicaments().add(new Medicament("med1", 1.0, "desc1", "gen1", 1));
        UiGui.pharmacie.getMedicaments().add(new Medicament("med2", 2.0, "desc2", "gen2", 2));
        UiGui.pharmacie.getMedicaments().add(new Medicament("med3", 3.0, "desc3", "gen3", 3));
        IDocuments.afficherListeMedicaments(panel);
        JScrollPane scrollPane = (JScrollPane) panel.getComponent(0);
        JPanel medicamentsPanel = (JPanel) scrollPane.getViewport().getView();
        int actualMedicamentsCount = medicamentsPanel.getComponentCount();
        assertEquals(3, actualMedicamentsCount); // le nombre de médicaments affichés doit être égal au nombre de médicaments dans la pharmacie
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
