package io;

import pharmacie.Ordonnance;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


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

    public static String getOrdonnance(String referencePatient) {
            String csvFilePath = "src/data/dataordonnances.csv";
            String ordonnance = "";

            try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                // Lire chaque ligne du fichier CSV
                while ((line = br.readLine()) != null) {
                    // Diviser la ligne en utilisant le délimiteur approprié (virgule dans ce cas)
                    String[] parts = line.split(",");
                    // Vérifier si la ligne contient la référence du patient donnée
                    if (parts.length >= 2 && parts[1].trim().equals(referencePatient.trim())) {
                        // Récupérer l'ordonnance du patient
                        if (parts.length >= 4) {
                            ordonnance = parts[3];
                        }
                        break; // Arrêter la recherche après avoir trouvé la référence du patient
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return ordonnance;
        }

    public static List<Ordonnance> lireOrdonnances(String cheminFichier) throws IOException {
        List<Ordonnance> ordonnances = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            reader.readLine(); // Lire la première ligne (entêtes) et l'ignorer
            while ((ligne = reader.readLine()) != null) {
                String[] champs = ligne.split(",");
                String ReferencesDuMedecin = champs[0];
                String ReferencesDuPatient = champs[1];
                String DateDePrescription = champs[2];
                List <String> ListeDesMedicaments = new ArrayList<>();
                if (champs.length >= 4) {
                    String[] medicaments = champs[3].split(";");
                    for (String medicament : medicaments) {
                        ListeDesMedicaments.add(medicament.trim());
                    }
                }
                Ordonnance ordonnance = new Ordonnance(ReferencesDuMedecin, ReferencesDuPatient, DateDePrescription, ListeDesMedicaments.toArray(new String[0]));
                ordonnances.add(ordonnance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ordonnances;
    }

}
