
package test;
import enums.ETypeMedicament;
import io.EcritureMedicamentsCsv;

import org.junit.Test;
import pharmacie.Medicament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Classe de test pour écrire la modification de médicament dans un fichier CSV.
 * On teste si le médicament est bien modifié dans le fichier CSV.
 * @see EcritureMedicamentsCsv#ecrireModificationDeMedicamentCsv(Medicament, String)
 */
public class EcriremodificationdemedicamentcsvTest {

    @Test
    public void test_lireTouteLesLignesDuFichierCsv() throws IOException {
        // Arrange
        List<String> expectedLines = List.of("med1,10.0,VENTE_LIBRE,true,5, med2,20.0,Type2,false,8");
        String fichierCsv = "test.csv";
        Files.write(Paths.get(fichierCsv), expectedLines);

        EcritureMedicamentsCsv.ecrireModificationDeMedicamentCsv(new Medicament("med1", 10.0, ETypeMedicament.VENTE_LIBRE, true, 5), fichierCsv);
        List<String> actualLines = Files.readAllLines(Paths.get(fichierCsv));

        assertEquals(expectedLines, actualLines);
    }

}