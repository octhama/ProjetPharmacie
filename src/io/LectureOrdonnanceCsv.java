package io;

import pharmacie.DemandeVersionGenerique;
import pharmacie.Medicament;
import pharmacie.Ordonnance;
import ui.UiGui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import interfaces.IDocuments;

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

    // Méthode pour authentifier le médecin
    public static boolean authentifierMedecin(String id, String password) {
            String csvFilePath = "src/data/authidmedecin.csv";
        
            try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                // Lire chaque ligne du fichier CSV
                while ((line = br.readLine()) != null) {
                    // Diviser la ligne en utilisant le délimiteur approprié (virgule dans ce cas)
                    String[] parts = line.split(",");
                    // Vérifier si la ligne contient l'identifiant et le mot de passe fournis
                    if (parts.length >= 2 && parts[0].equals(id) && parts[1].equals(password)) {
                        return true; // Authentification réussie
                    }
                }
            } catch (IOException e) {
                    e.printStackTrace();
            }
            return false; // Authentification échouée
        }

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
