package test;

import io.LectureDemandeVersionGeneriqueCsv;
import org.junit.Test;
import pharmacie.DemandeVersionGenerique;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LiredemandesversiongeneriquecsvTest {

    @Test
    public void test_read_csv_file_and_return_list() {
        String cheminFichier = "src/data/demandes_version_generique.csv";
        List<DemandeVersionGenerique> expectedList = new ArrayList<>();
        expectedList.add(new DemandeVersionGenerique("Medicament1", true, LocalDate.parse("2022-01-01")));
        expectedList.add(new DemandeVersionGenerique("Medicament2", false, LocalDate.parse("2022-02-02")));

        List<DemandeVersionGenerique> actualList = new ArrayList<>();
        try {
            actualList = LectureDemandeVersionGeneriqueCsv.lireDemandesVersionGeneriqueCsv(cheminFichier);
        } catch (Exception e) {
            fail("Impossible de lire le fichier CSV: " + e.getMessage());
        }

        assertEquals(expectedList, actualList);
    }

}
