package test;

import io.LectureRegistrePreparation;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LireregistrepreparationTest {


    // The method successfully reads the content of the CSV file.
    @Test
    public void test_readContent_success() throws IOException {
        String expected = "line1\nline2\nline3\n";
        String actual = LectureRegistrePreparation.lireRegistrePreparation("src/data/registrepreparation.csv");
        assertEquals(expected, actual);
    }

    // The method returns a non-empty string when the CSV file is not empty.
    @Test
    public void test_nonEmptyString_notEmptyFile() throws IOException {
        String actual = LectureRegistrePreparation.lireRegistrePreparation("src/data/nonemptyfile.csv");
        assertFalse(actual.isEmpty());
    }

    // The method returns an empty string when the CSV file is empty.
    @Test
    public void test_emptyString_emptyFile() throws IOException {
        String actual = LectureRegistrePreparation.lireRegistrePreparation("src/data/emptyfile.csv");
        assertTrue(actual.isEmpty());
    }

    // The method throws an IOException when the CSV file does not exist.
    @Test
    public void test_throwsIOException_nonExistentFile() {
        assertThrows(IOException.class, () -> {
            LectureRegistrePreparation.lireRegistrePreparation("src/data/nonexistentfile.csv");
        });
    }

}