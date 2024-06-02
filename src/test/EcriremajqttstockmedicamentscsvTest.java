package test;
import io.EcritureMedicamentsCsv;

import org.junit.Test;
import pharmacie.Medicament;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Classe de test pour écrire la mise à jour de la quantité en stock des médicaments dans un fichier CSV.
 * On teste si la quantité en stock des médicaments est bien mise à jour dans le fichier.
 * @see EcritureMedicamentsCsv#ecrireMajQttStockMedicamentsCsv(List, String)
 */
public class EcriremajqttstockmedicamentscsvTest {

    @Test
    public void test_maj_qtt_stck() throws IOException {
        List<Medicament> medicaments = new ArrayList<>();
        medicaments.add(new Medicament("Med1", 10));
        medicaments.add(new Medicament("Med2", 20));
        String fichierCsv = "test.csv";
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCsv))) {
            writer.write("Med1,10,Type1,true,5\n");
            writer.write("Med2,20,Type2,false,8\n");
            writer.write("Med3,30,Type3,true,15\n");
        }
    
        EcritureMedicamentsCsv.ecrireMajQttStockMedicamentsCsv(medicaments, fichierCsv);

        List<String> lines = Files.readAllLines(Paths.get(fichierCsv));
        assertEquals("Med1,10,Type1,true,10", lines.get(0)); // la quantité en stock du médicament doit être mise à jour
        assertEquals("Med2,20,Type2,false,20", lines.get(1)); // la quantité en stock du médicament doit être mise à jour
        assertEquals("Med3,30,Type3,true,15", lines.get(2)); // la quantité en stock du médicament ne doit pas être mise à jour
    
        Files.deleteIfExists(Paths.get(fichierCsv));
    }

}