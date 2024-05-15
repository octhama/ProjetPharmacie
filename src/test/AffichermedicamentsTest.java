package test;
import interfaces.IDocuments;

import org.junit.Test;
import pharmacie.Medicament;
import ui.UiGui;
import enums.ETypeMedicament;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;
/**
 * La classe AffichermedicamentsTest permet de tester les méthodes de la classe IDocuments.
 * Cette classe contient 3 méthodes de test :
 * - test_afficher_medicaments_quantite_superieure_20() : permet de tester l'affichage des médicaments avec une quantité en stock supérieure ou égale à 20 unités.
 * - test_afficher_medicaments_quantite_nulle() : permet de tester l'affichage des médicaments avec une quantité en stock égale à 0 unité.
 * - test_afficher_medicaments_noms_vides() : permet de tester l'affichage des médicaments avec des noms vides.
 * <p>
 * Note très importante : Pour que les tests passent, chaque test doit être exécuté individuellement.
 */
public class AffichermedicamentsTest {

    // Afficher les médicaments avec une quantité en stock supérieure ou égale à 20 unités.
    @Test
    public void test_afficher_medicaments_quantite_superieure_20() {
        JPanel panel = new JPanel();
        Medicament medicament1 = new Medicament("Medicament1", 25.0, ETypeMedicament.VENTE_LIBRE, false, 30);
        Medicament medicament2 = new Medicament("Medicament2", 15.0, ETypeMedicament.ORDONNANCE, false, 40);
        UiGui.pharmacie.getMedicaments().add(medicament1);
        UiGui.pharmacie.getMedicaments().add(medicament2);

        IDocuments.afficherMedicaments(panel);

        Component[] components = panel.getComponents();
        JScrollPane scrollPane = (JScrollPane) components[0];
        JPanel medicamentsPanel = (JPanel) scrollPane.getViewport().getView();
        Component[] labels = medicamentsPanel.getComponents();

        assertEquals(2, labels.length);
        assertEquals("Medicament1 - 30 unité(s)", ((JLabel) labels[0]).getText());
        assertEquals("Medicament2 - 40 unité(s)", ((JLabel) labels[1]).getText());
    }

    // Afficher les médicaments avec une quantité en stock égale à 0 unité.
    @Test
    public void test_afficher_medicaments_quantite_nulle() {
        JPanel panel = new JPanel();
        Medicament medicament1 = new Medicament("Medicament1", 25.0, ETypeMedicament.VENTE_LIBRE, false, 0);
        Medicament medicament2 = new Medicament("Medicament2", 15.0, ETypeMedicament.ORDONNANCE, false, 0);
        UiGui.pharmacie.getMedicaments().add(medicament1);
        UiGui.pharmacie.getMedicaments().add(medicament2);

        IDocuments.afficherMedicaments(panel);

        Component[] components = panel.getComponents();
        JScrollPane scrollPane = (JScrollPane) components[0];
        JPanel medicamentsPanel = (JPanel) scrollPane.getViewport().getView();
        Component[] labels = medicamentsPanel.getComponents();

        assertEquals(2, labels.length);
        assertEquals("En rupture de stock - Medicament1 - 0 unité(s)", ((JLabel) labels[0]).getText());
        assertEquals("En rupture de stock - Medicament2 - 0 unité(s)", ((JLabel) labels[1]).getText());
        assertEquals(Color.RED, ((JLabel) labels[0]).getForeground());
        assertEquals(Color.RED, ((JLabel) labels[1]).getForeground());
    }

    // Afficher les médicaments avec des noms vides.
    @Test
    public void test_afficher_medicaments_noms_vides() {
        JPanel panel = new JPanel();
        Medicament medicament1 = new Medicament("", 25.0, ETypeMedicament.VENTE_LIBRE, false, 30);
        Medicament medicament2 = new Medicament("", 15.0, ETypeMedicament.ORDONNANCE, false, 40);
        UiGui.pharmacie.getMedicaments().add(medicament1);
        UiGui.pharmacie.getMedicaments().add(medicament2);

        IDocuments.afficherMedicaments(panel);

        Component[] components = panel.getComponents();
        JScrollPane scrollPane = (JScrollPane) components[0];
        JPanel medicamentsPanel = (JPanel) scrollPane.getViewport().getView();
        Component[] labels = medicamentsPanel.getComponents();

        assertEquals(2, labels.length);
        assertEquals(" - 30 unité(s)", ((JLabel) labels[0]).getText());
        assertEquals(" - 40 unité(s)", ((JLabel) labels[1]).getText());
    }

}