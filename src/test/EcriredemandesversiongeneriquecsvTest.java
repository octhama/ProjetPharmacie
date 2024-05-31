package test;
import io.EcritureRegistreDemandeVersionGeneriqueCsv;
import pharmacie.DemandeVersionGenerique;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Classe de test pour écrire les demandes de version générique dans un fichier CSV.
 * On teste si les demandes sont bien écrites dans le fichier.
 * @see EcritureRegistreDemandeVersionGeneriqueCsv#ecrireDemandesVersionGeneriqueCsv(List)
 */
public class EcriredemandesversiongeneriquecsvTest {

    @Test
    public void test_ecrire_demande_pour_fichier_ok() {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp", ".csv");
            tempFile.deleteOnExit();

            // Écrire du contenu initial dans le fichier temporaire
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            writer.write("Nom du médicament, Demande, Date de demande");
            writer.newLine();
            writer.write("Medicament1,1,2022-01-01");
            writer.newLine();
            writer.close();

            // Créer une liste de demandes
            List<DemandeVersionGenerique> demandes = new ArrayList<>();
            demandes.add(new DemandeVersionGenerique("Medicament2", true));
            demandes.add(new DemandeVersionGenerique("Medicament3", false));

            // Appeler la méthode de test en utilisant le fichier temporaire
            EcritureRegistreDemandeVersionGeneriqueCsv.ecrireDemandesVersionGeneriqueCsv(demandes);

            // Lire le contenu du fichier temporaire
            BufferedReader reader = new BufferedReader(new FileReader(tempFile));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            // Vérifier que le contenu est correct
            assertEquals("Nom du médicament, Demande, Date de demande", lines.get(0));
            assertEquals("Medicament1,1,2022-01-01", lines.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
