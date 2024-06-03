package test;

import io.LectureDemandeVersionGeneriqueCsv;
import org.junit.Test;
import pharmacie.DemandeVersionGenerique;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Classe de test pour lire les demandes de version générique dans un fichier CSV.
 * On teste si les demandes de version générique sont bien lues dans le fichier CSV.
 */
public class LiredemandesversiongeneriquecsvTest {

    @Test
    public void test_lire_fichier_csv_and_retoune_liste() {
        List<DemandeVersionGenerique> liste = LectureDemandeVersionGeneriqueCsv.lireDemandesVersionGeneriqueCsv("src/data/demandes_version_generique.csv");
        assertEquals(39, liste.size());
    }

}
