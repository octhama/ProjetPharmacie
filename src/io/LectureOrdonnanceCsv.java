package io;

import java.io.*;


/**
 * Classe pour lire les ordonnances à partir d'un fichier CSV.
 */
public class LectureOrdonnanceCsv {
    public static boolean ordonnanceDisponible(String referencePatient) {
            String csvFilePath = "src/data/dataordonnances.csv";
    
            try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                // Lire chaque ligne du fichier CSV
                while ((line = br.readLine()) != null) {
                    // Diviser la ligne en utilisant le délimiteur approprié (virgule dans ce cas)
                    String[] parts = line.split(",");
                    // Vérifier si la ligne contient la référence du patient donnée
                    if (parts.length >= 2 && parts[1].trim().equals(referencePatient.trim())) {
                        return true; // Ordonnance trouvée
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            return false; // Aucune correspondance trouvée
        }
}
