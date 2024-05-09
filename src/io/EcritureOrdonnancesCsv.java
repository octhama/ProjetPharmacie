package io;

import pharmacie.Medicament;
import pharmacie.Ordonnance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public class EcritureOrdonnancesCsv {
        public static void ecrireOrdonnanceCsv(String referenceMedecin, String referencePatient, Date datePrescription, Stack<String> medicaments) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(datePrescription);
        final String CSV_FILE_PATH = "src/data/dataordonnances.csv";

        try (FileWriter fw = new FileWriter(CSV_FILE_PATH, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {

            String sb = referenceMedecin + ", " +
                    referencePatient + ", " +
                    formattedDate + ", " +
                    String.join("; ", medicaments) + "\n";

            out.print(sb);

            System.out.println("Ordonnance enregistrée avec succès.");

        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement de l'ordonnance : " + e.getMessage());
        }
    }
}
