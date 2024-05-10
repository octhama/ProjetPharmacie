package test;

import interfaces.IDocuments;

import org.junit.Test;
import pharmacie.Medicament;
import ui.UiGui;

import static interfaces.IDocuments.saveSelectedMedicamentStates;
import static org.junit.Assert.*;

public class SaveselectedmedicamentstatesTest {

    // The 'medicamentCheckBoxMap' is empty, and the method does not add any entries to the 'selectedMedicamentStates' map.
    @Test
    public void test_empty_medicament_checkbox_map() {
        // Arrange
        UiGui.medicamentCheckBoxMap.clear();
    
        // Act
        saveSelectedMedicamentStates();
    
        // Assert
        assertTrue(UiGui.selectedMedicamentStates.isEmpty());
    }

}