
package test;
import enums.ETypeMedicament;
import io.EcritureMedicamentsCsv;

import org.junit.Test;
import pharmacie.Medicament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        Path path = Paths.get("src/data/medicaments.csv");
        List<String> lignes = Files.readAllLines(path);
        EcritureMedicamentsCsv.ecrireModificationDeMedicamentCsv(new Medicament("Medicament1", 10.0, ETypeMedicament.ORDONNANCE, true, 5), "src/data/medicaments.csv");
        List<String> lignesApresModification = Files.readAllLines(path);
        assertFalse(lignes.equals(lignesApresModification));
    }

}