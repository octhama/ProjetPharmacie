package test;
import enums.ETypeMedicament;
import io.EcritureMedicamentsCsv;

import org.junit.Test;
import pharmacie.Medicament;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class EcriresuppressiondemedicamentcsvTest {


    @Test
    public void test_suppMed() throws IOException {
        String tempFile = "temp.csv";
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write("Medicament1,10.0,Type1,true,5\n");
        writer.write("Medicament2,20.0,Type2,false,10\n");
        writer.write("Medicament3,30.0,Type3,true,15\n");
        writer.close();

        Medicament medicamentToRemove = new Medicament("Medicament2", 20.0, ETypeMedicament.ORDONNANCE, false, 10);

        EcritureMedicamentsCsv.ecrireSuppressionDeMedicamentCsv(medicamentToRemove, tempFile);

        List<String> lines = Files.readAllLines(Paths.get(tempFile));

        assertFalse(lines.contains("Medicament2,20.0,Type2,false,10"));

        Files.deleteIfExists(Paths.get(tempFile));
    }

}