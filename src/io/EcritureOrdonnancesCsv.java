package io;

import pharmacie.Medicament;
import pharmacie.Ordonnance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EcritureOrdonnancesCsv {
    /**
     * Ecrit une ordonnance dans un fichier CSV.
     *
     * @param filePath Le chemin du fichier CSV.
     * @param ordonnance L'ordonnance à écrire.
     */
    public static void ecrireOrdinanceCsv(String filePath, Ordonnance ordonnance) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            List<Medicament> medicaments = List.of(ordonnance.getMedicaments());
            for (Medicament medicament : medicaments) {
                writer.write(medicament.getNom() + "," +
                        medicament.getPrix() + "," +
                        medicament.getTypeMedicament() + "," +
                        medicament.isGenerique() + "," +
                        medicament.getQuantiteEnStock());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
