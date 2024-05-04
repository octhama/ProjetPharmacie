package io;

import pharmacie.Medicament;
import pharmacie.Ordonnance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour lire les ordonnances à partir d'un fichier CSV.
 */
public class LectureOrdonnanceCsv {

    /**
     * Méthode pour lire les ordonnances à partir d'un fichier CSV.
     *
     * @param filename Nom du fichier CSV.
     * @return Une liste d'ordonnances lues à partir du fichier.
     */
    public static List<Ordonnance> lireOrdonnancesCsv(String filename) {
        List<Ordonnance> ordonnances = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Ordonnance ordonnance = parseLine(line);
                if (ordonnance != null) {
                    ordonnances.add(ordonnance);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ordonnances;
    }

    /**
     * Méthode pour parser une ligne du fichier CSV et créer une ordonnance.
     *
     * @param line Ligne du fichier CSV à parser.
     * @return Une instance d'Ordonnance créée à partir de la ligne, ou null si la ligne est mal formée.
     */
    private static Ordonnance parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 4) { // Assurez-vous que la ligne a au moins 4 parties
            // Analyser les parties pour créer une ordonnance
            String referencesMedecin = parts[0];
            String referencesPatient = parts[1];
            LocalDate datePrescription = LocalDate.parse(parts[2]); // Suppose que la date est stockée au format ISO yyyy-MM-dd
            List<Medicament> medicaments = parseMedicaments(parts[3]);
            return new Ordonnance(datePrescription, medicaments);
        } else {
            System.err.println("Ligne mal formée dans le fichier CSV : " + line);
            return null;
        }
    }

    /**
     * Méthode pour parser la liste de médicaments à partir de la chaîne de données du fichier CSV.
     * @param medicamentsString Chaîne de données représentant la liste de médicaments.
     * @return Une liste de médicaments.
     */
    private static List<Medicament> parseMedicaments(String medicamentsString) {
        List<Medicament> medicaments = new ArrayList<>();
        String[] medicamentStrings = medicamentsString.split(",");
        for (String medicamentString : medicamentStrings) {
            // Analyser chaque partie pour extraire le nom du médicament et la quantité prescrite
            // La logique dépendra du format dans lequel les médicaments sont stockés dans le fichier CSV
            // Par exemple, le format pourrait être "NomMedicament(QuantitePrescrite)"
            // Vous devrez ajuster cette logique en fonction de votre format réel
            // Ajouter le médicament à la liste

            // Split the string to get the name and quantity
            String[] parts = medicamentString.split("\\(");
            String nom = parts[0];
            int quantitePrescrite = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
            medicaments.add(new Medicament(nom, quantitePrescrite));    // Ajouter le médicament à la liste
            medicaments.add(new Medicament(medicamentString));    // Ajouter le médicament à la liste
            medicaments.add(new Medicament(medicamentString.substring(0, medicamentString.indexOf('('))));    // Ajouter le médicament à la liste
            String substring = medicamentString.substring(medicamentString.indexOf('(') + 1, medicamentString.length() - 1);
            medicaments.add(new Medicament(substring));    // Ajouter le médicament à la liste
            medicaments.add(new Medicament(medicamentString.substring(0, medicamentString.indexOf('(')), Integer.parseInt(substring)));    // Ajouter le médicament à la liste
        }
        return medicaments;
    }
}
