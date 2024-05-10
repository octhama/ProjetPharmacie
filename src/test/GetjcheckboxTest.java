package test;

import pharmacie.Medicament;
import ui.UiGui;

import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class GetjcheckboxTest {


    // Returns a JCheckBox with the label "50% du mg" when called with a Medicament and a JPanel
    @Test
    public void test_returns_jcheckbox_with_label_50_percent_du_mg() {
        // Arrange
        Medicament medicament = new Medicament("Medicament A", (int) 10.0);
        JPanel panel = new JPanel();
    
        // Act
        JCheckBox checkBox = UiGui.getjCheckBox(medicament, panel);
    
        // Assert
        assertEquals("50% du mg", checkBox.getText());
    }

    // When the returned JCheckBox is selected, changes its label to "mg à 50%"
    @Test
    public void test_changes_label_to_mg_a_50_percent_when_selected() {
        // Arrange
        Medicament medicament = new Medicament("Medicament A", (int) 10.0);
        JPanel panel = new JPanel();
        JCheckBox checkBox = UiGui.getjCheckBox(medicament, panel);
    
        // Act
        checkBox.setSelected(true);
    
        // Assert
        assertEquals("mg à 50%", checkBox.getText());
    }

    // When called with a Medicament with an empty name, returns a JCheckBox with the label "50% du mg"
    @Test
    public void test_returns_jcheckbox_with_label_50_percent_du_mg_when_medicament_has_empty_name() {
        // Arrange
        Medicament medicament = new Medicament("", (int) 10.0);
        JPanel panel = new JPanel();
    
        // Act
        JCheckBox checkBox = UiGui.getjCheckBox(medicament, panel);
    
        // Assert
        assertEquals("50% du mg", checkBox.getText());
    }

    // When called with a Medicament with a name that does not contain a quantity in mg, returns a JCheckBox with the label "50% du mg"
    @Test
    public void test_returns_jcheckbox_with_label_50_percent_du_mg_when_medicament_name_does_not_contain_quantity_in_mg() {
        // Arrange
        Medicament medicament = new Medicament("Medicament A", (int) 10.0);
        JPanel panel = new JPanel();
    
        // Act
        JCheckBox checkBox = UiGui.getjCheckBox(medicament, panel);
    
        // Assert
        assertEquals("50% du mg", checkBox.getText());
    }

}