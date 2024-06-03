package test;
import enums.ETypeMedicament;
import io.EcritureMedicamentsCsv;

import org.junit.Test;
import pharmacie.Medicament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Classe de test pour écrire l'ajout de médicament dans un fichier CSV.
 * On teste si le médicament est bien écrit dans le fichier CSV.
 * @see EcritureMedicamentsCsv#ecrireAjoutDeMedicamentCsv(String, Medicament)
 */
public class EcrireajoutdemedicamentcsvTest {

    @Test
    public void test_ecrire_med_dans_le_bon_format() throws IOException {

        String fichierCsv = "test.csv";
        Medicament medicament = new Medicament("Medicament1", 10.0, ETypeMedicament.VENTE_LIBRE, true, 5);

        EcritureMedicamentsCsv.ecrireAjoutDeMedicamentCsv(fichierCsv, medicament);

        List<String> lines = Files.readAllLines(Paths.get(fichierCsv));
        String lastLine = lines.getLast();
        String[] values = lastLine.split(",");
        assertEquals(medicament.getNom(), values[0]); // le nom du médicament doit être écrit dans le bon format
        assertEquals(Double.toString(medicament.getPrix()), values[1]); // le prix du médicament doit être écrit dans le bon format
        assertEquals(medicament.getType().toString(), values[2]); // le type du médicament doit être écrit dans le bon format
        assertEquals(Boolean.toString(medicament.isGenerique()), values[3]); // le médicament générique doit être écrit dans le bon format
        assertEquals(Integer.toString(medicament.getQuantiteEnStock()), values[4]); // la quantité en stock du médicament doit être écrite dans le bon format
    }

}