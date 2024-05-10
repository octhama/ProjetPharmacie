package test;

import interfaces.IDocuments;

import org.junit.Test;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class AfficherregistrepreparationTest {


    // The method should read the preparation registry file and display its content on a panel.
    @Test
    public void test_readPreparationRegistryFile() throws IOException {
        // Create a mock panel
        JPanel panel = new JPanel();

        // Call the method under test
        IDocuments.afficherRegistrePreparation(panel);

        // Verify that the panel contains the expected number of labels
        int expectedLabelCount = 3; // Assuming the preparation registry file contains 3 entries
        int actualLabelCount = panel.getComponentCount();
        assertEquals(expectedLabelCount, actualLabelCount);
    }

    // The method should properly handle registry files with non-standard formats.
    @Test
    public void test_handleNonStandardRegistryFormat() throws IOException {
        // Create a mock panel
        JPanel panel = new JPanel();

        // Create a non-standard preparation registry file
        String preparations = "Preparation 1\nPreparation 2\nPreparation 3";
        String nonStandardRegistryFilePath = "src/data/nonstandardregistry.csv";
        Files.write(Paths.get(nonStandardRegistryFilePath), preparations.getBytes());

        // Call the method under test
        IDocuments.afficherRegistrePreparation(panel);

        // Verify that the panel contains the expected number of labels
        int expectedLabelCount = 3; // Assuming the non-standard preparation registry file contains 3 entries
        int actualLabelCount = panel.getComponentCount();
        assertEquals(expectedLabelCount, actualLabelCount);

        // Clean up the non-standard preparation registry file
        Files.deleteIfExists(Paths.get(nonStandardRegistryFilePath));
    }

}