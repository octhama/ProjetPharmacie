package test;

import pharmacie.Preparation;

import org.junit.Test;

import javax.swing.*;
import java.util.HashMap;

public class CommanderUnepreparationTest {

    @Test
    public void test_display_GUI_correctly() {
        SwingUtilities.invokeLater(() -> {
            Preparation.commanderUnePreparation(new HashMap<>());
        });
    }

    @Test
    public void testCommanderUnePreparation() {
        Preparation.commanderUnePreparation(new HashMap<>());
    }
}






