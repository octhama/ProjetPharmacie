package io;

import pharmacie.Medicament;
import pharmacie.Preparation;

import java.io.*;
import java.util.List;

public class EcritureRegistrePreparationCsv {
    private static int idCounter; // Compteur pour les identifiants uniques
    private static List<Preparation> preparations; // Liste des préparations
    private static Medicament medicament; // Médicament
    private static String nom; // Nom de la préparation
    private static int quantite; // Quantité de la préparation
    private static String date; // Date de la préparation
    public static final String fichierCsv = "src/data/registrepreparation.csv"; // Chemin du fichier CSV
    private static List<Medicament> medicaments; // Liste des médicaments
    public static Preparation preparation; // Préparation

    static {
        // Initialisation du compteur à partir du fichier CSV
        try (BufferedReader reader = new BufferedReader(new FileReader(fichierCsv))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // On lit chaque ligne et incrémente l'identifiant s'il est plus grand que le compteur actuel
                String[] parts = line.split(",");
                int currentId = Integer.parseInt(parts[0].substring(7)); // Récupération de l'identifiant numérique
                idCounter = Math.max(idCounter, currentId);
            }
            // Incrémentation pour le prochain identifiant
            idCounter++;
        } catch (IOException e) {
            // Gestion de l'exception en cas de problème lors de la lecture du fichier
            e.printStackTrace();
        }
    }

    public static void ecrirePreparationsCsv(String s, Preparation preparation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCsv, true))) {
            String idUnique = "CMD-PRP" + idCounter; // Identifiant unique auto-généré
            writer.write(
                    idUnique + "," +
                            preparation.getNom() + "," +
                            preparation.getQuantite() + "," +
                            preparation.getDate() + "\n"
            );
            idCounter++; // Incrémenter le compteur pour le prochain identifiant
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setMedicaments(List<Medicament> medicaments) {
        EcritureRegistrePreparationCsv.medicaments = medicaments;
    }

    public static void setPreparation(Preparation preparation) {
        EcritureRegistrePreparationCsv.preparation = preparation;
    }

    public static void setPreparations(List<Preparation> preparations) {
        EcritureRegistrePreparationCsv.preparations = preparations;
    }

    public static void setNom(String nom) {
        EcritureRegistrePreparationCsv.nom = nom;
    }

    public static void setQuantite(int quantite) {
        EcritureRegistrePreparationCsv.quantite = quantite;
    }

    public static void setDate(String date) {
        EcritureRegistrePreparationCsv.date = date;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static List<Preparation> getPreparations() {
        return preparations;
    }

    public static Medicament getMedicament() {
        return medicament;
    }

    public static String getNom() {
        return nom;
    }

    public static int getQuantite() {
        return quantite;
    }

    public static String getDate() {
        return date;
    }

    public static String getFichierCsv() {
        return fichierCsv;
    }

    public static List<Medicament> getMedicaments() {
        return medicaments;
    }

    public static Preparation getPreparation() {
        return preparation;
    }
}
