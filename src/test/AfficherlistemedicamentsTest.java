package test;

import interfaces.IDocuments;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import pharmacie.Medicament;
import enums.ETypeMedicament;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class AfficherlistemedicamentsTest {

        @Test
        public void test_display_suggested_medications() {
            JPanel panel = new JPanel();

            List<Medicament> suggestions = new ArrayList<>();
            suggestions.add(new Medicament("Med1", 10.0, ETypeMedicament.VENTE_LIBRE, false, 20));
            suggestions.add(new Medicament("Med2", 15.0, ETypeMedicament.ORDONNANCE, true, 10));

            IDocuments.afficherListeMedicaments(panel, suggestions);

            assertEquals(2, panel.getComponentCount());

            JLabel label1 = (JLabel) panel.getComponent(0);
            assertEquals("Med1 - 20 unité(s)", label1.getText());

            JLabel label2 = (JLabel) panel.getComponent(1);
            assertEquals("Med2 - 10 unité(s)", label2.getText());
        }


}