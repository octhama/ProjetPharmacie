package test;

import enums.ETypeMedicament;
import pharmacie.Medicament;
import ui.UiGui;

import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

public class GetjlabelTest {


    // Returns a JLabel with formatted text containing the name, price, type, generique status, and quantity in stock of a given Medicament object.
    @Test
    public void test_jLabel_with_all_fields() {
        Medicament medicament = new Medicament("Med1", 10.0, ETypeMedicament.valueOf("Type1"), true, 5);
        JLabel label = UiGui.getjLabel(medicament);
        String expectedText = "<html><b>Nom:</b> Med1<br><b>Prix:</b> 10.0 €<br><b>Type:</b> Type1<br><b>Générique:</b> Oui<br><b>Quantité en stock:</b> 5</html>";
        assertEquals(expectedText, label.getText());
        assertEquals(BorderFactory.createLineBorder(Color.BLACK), label.getBorder());
        assertEquals(Component.LEFT_ALIGNMENT, label.getAlignmentX());
    }

    // The formatted text is left-aligned and has a black border.
    @Test
    public void test_jLabel_formatting() {
        Medicament medicament = new Medicament("Med2", 20.0, null, null, 10);
        JLabel label = UiGui.getjLabel(medicament);
        assertEquals(BorderFactory.createLineBorder(Color.BLACK), label.getBorder());
        assertEquals(Component.LEFT_ALIGNMENT, label.getAlignmentX());
    }

    // Returns a JLabel with formatted text containing only the name and quantity in stock of a given Medicament object when the price, type, and generique status are null.
    @Test
    public void test_jLabel_with_name_and_quantity() {
        Medicament medicament = new Medicament("Med3", null, null, null, 15);
        JLabel label = UiGui.getjLabel(medicament);
        String expectedText = "<html><b>Nom:</b> Med3<br><b>Quantité en stock:</b> 15</html>";
        assertEquals(expectedText, label.getText());
        assertEquals(BorderFactory.createLineBorder(Color.BLACK), label.getBorder());
        assertEquals(Component.LEFT_ALIGNMENT, label.getAlignmentX());
    }

    // Returns a JLabel with formatted text containing only the name and price of a given Medicament object when the type and generique status are null.
    @Test
    public void test_jLabel_with_name_and_price() {
        Medicament medicament = new Medicament("Med4", 30.0, null, null, null);
        JLabel label = UiGui.getjLabel(medicament);
        String expectedText = "<html><b>Nom:</b> Med4<br><b>Prix:</b> 30.0 €</html>";
        assertEquals(expectedText, label.getText());
        assertEquals(BorderFactory.createLineBorder(Color.BLACK), label.getBorder());
        assertEquals(Component.LEFT_ALIGNMENT, label.getAlignmentX());
    }

}