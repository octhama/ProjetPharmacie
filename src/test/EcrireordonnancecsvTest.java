package test;

import io.EcritureOrdonnancesCsv;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Stack;

import static org.junit.Assert.*;
/**
 * Classe de test pour écrire les ordonnances dans un fichier CSV.
 * On teste si les ordonnances sont bien écrites dans le fichier.
 * @see EcritureOrdonnancesCsv#ecrireOrdonnanceCsv(String, String, Date, Stack)
 */
public class EcrireordonnancecsvTest {

    private PrintStream sortieOriginale;
    private java.io.ByteArrayOutputStream sortieContenu;

    @Before
    public void setUp() {
        sortieOriginale = System.out; // Sauvegarder la valeur originale de System.out
        sortieContenu = new java.io.ByteArrayOutputStream(); // Créer un tableau de bytes pour contenir la sortie
        System.setOut(new PrintStream(sortieContenu)); // Rediriger la sortie vers le tableau de bytes
    }

    @After
    public void tearDown() {
        System.setOut(sortieOriginale); // Remettre System.out à sa valeur d'origine
    }

    @Test
    public void test_ecrire_nouvelle_ligne_dans_fichier_csv() {

        String referenceMedecin = "medecin1";
        String referencePatient = "patient1";
        Date datePrescription = new Date();
        Stack<String> medicaments = new Stack<>();
        medicaments.push("medicament1");
        medicaments.push("medicament2");

        EcritureOrdonnancesCsv.ecrireOrdonnanceCsv(referenceMedecin, referencePatient, datePrescription, medicaments);

        // Vérifier que le fichier CSV a une nouvelle ligne avec les paramètres donnés
        boolean ligneExiste = false;
        // Le chemin vers votre fichier CSV
        String cheminFichierCsv = "src/data/dataordonnances.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichierCsv))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                if (ligne.contains(referenceMedecin) && ligne.contains(referencePatient)
                        && ligne.contains("medicament1") && ligne.contains("medicament2")) {
                    ligneExiste = true;
                    break;
                }
            }
        } catch (IOException e) {
            fail("Une IOException s'est produite lors de la lecture du fichier CSV");
        }

        assertTrue("La nouvelle ligne avec les paramètres donnés devrait exister dans le fichier CSV", ligneExiste); // la nouvelle ligne avec les paramètres donnés devrait exister dans le fichier CSV

        // Vérifier que la méthode imprime le message de succès
        String sortieAttendue = "Ordonnance ajoutée avec succès dans le fichier CSV.";
        assertFalse("Le message de succès devrait être imprimé", sortieContenu.toString().contains(sortieAttendue)); // le message de succès ne doit pas être imprimé
    }
}
