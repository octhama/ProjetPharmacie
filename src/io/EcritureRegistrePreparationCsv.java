package io;

import pharmacie.Medicament;
import pharmacie.Preparation;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EcritureRegistrePreparationCsv {
    private static int idCounter; // Compteur pour les identifiants uniques
    private static List<Preparation> preparations; // Liste des préparations
    private static List<Medicament> medicaments; // Liste des médicaments
    public static final String fichierCsv = "src/data/registrepreparation.csv"; // Chemin du fichier CSV

    static {
        // Initialisation des listes uniquement si elles ne sont pas déjà initialisées
        if (preparations == null) {
            preparations = new ArrayList<>();
        }
        if (medicaments == null) {
            medicaments = new ArrayList<>();
        }

        idCounter = 1;
        // Initialisation du compteur à partir du fichier CSV
        try (BufferedReader reader = new BufferedReader(new FileReader(fichierCsv))) {
            String line;
            boolean headerSkipped = false; // Indicateur pour savoir si l'en-tête a été ignoré
            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Ignorer l'en-tête et passer à la prochaine itération de la boucle
                }
                //System.out.println("Line read from CSV: \n\n" + line); // Ajout d'une instruction d'impression pour le débogage
                // On lit chaque ligne et incrémente l'identifiant s'il est plus grand que le compteur actuel
                String[] parts = line.split(",");
                int currentId = Integer.parseInt(parts[0].substring(7)); // Récupérer l'identifiant numérique à partir de l'identifiant unique
                idCounter = Math.max(idCounter, currentId);
            }
            // Incrémentation pour le prochain identifiant
            idCounter++;
        } catch (IOException e) {
            // Gestion de l'exception en cas de problème lors de la lecture du fichier
            e.printStackTrace();
        }
    }

    public static void ecrirePreparationsCsv(Preparation preparation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCsv, true))) {
            // Vérifier si le fichier est vide ou si l'en-tête est absent
            if (!containsHeader()) {
                // Écrire l'en-tête
                writer.write("idUnique,nom,quantite,date\n");
            }

            // Validation des données de la préparation
            if (preparation == null || preparation.getNom() == null || preparation.getNom().isEmpty() ||
                    preparation.getQuantite() <= 0 || preparation.getDate() == null) {
                System.out.println("Erreur: Données de préparation invalides.");
                return;
            }

            // On ajoute la préparation à la liste
            preparations.add(preparation);

            // Mise à jour du stock de médicaments
            Medicament medicament = medicaments.stream()
                    .filter(m -> m.getNom().equals(preparation.getNom()))
                    .findFirst()
                    .orElse(null);

            if (medicament != null) {
                // Vérification de la quantité en stock
                if (medicament.getQuantiteEnStock() >= preparation.getQuantite()) {
                    // Décrémenter la quantité en stock
                    medicament.setQuantiteEnStock(medicament.getQuantiteEnStock() - preparation.getQuantite());

                    // Écriture de la ligne dans le fichier CSV
                    String idUnique = genererIdUnique();
                    String nom = preparation.getNom();
                    String quantite = String.valueOf(preparation.getQuantite());
                    String date = String.format(String.valueOf(DateTimeFormatter.ISO_DATE)); // Formatage de la date

                    writer.write(
                            idUnique + "," +
                                    nom + "," +
                                    quantite + "," +
                                    date + "\n"
                    );
                } else {
                    System.out.println("Erreur: Stock insuffisant pour le médicament " + preparation.getNom() + ".");
                }
            } else {
                System.out.println("Erreur: Médicament " + preparation.getNom() + " non trouvé dans la liste des médicaments.");
            }

        } catch (IOException e) {
            System.out.println("Erreur d'écriture dans le fichier CSV: " + e.getMessage());
        }
    }

    private static String genererIdUnique() {
        return "CMD-PREP" + idCounter++;
    }

    // Méthodes d'accès aux données

    public static int getIdCounter() {
        return idCounter;
    }

    public static List<Preparation> getPreparations() {
        return preparations;
    }

    public static List<Medicament> getMedicaments() {
        return medicaments;
    }

    // Vérifie si le fichier CSV contient l'en-tête
    private static boolean containsHeader() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichierCsv))) {
            String firstLine = reader.readLine();
            return firstLine != null && firstLine.equals("idUnique,nom,quantite,date");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
