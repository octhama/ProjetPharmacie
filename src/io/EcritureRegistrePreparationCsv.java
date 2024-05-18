package io;

import org.jetbrains.annotations.NotNull;
import pharmacie.Medicament;
import pharmacie.Preparation;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EcritureRegistrePreparationCsv {
    private static int idCounter; // Compteur pour les identifiants uniques
    public static List<Preparation> preparations; // Liste des préparations
    public static List<Medicament> medicaments; // Liste des médicaments
    public static final String fichierCsv = "src/data/registrepreparation.csv"; // Chemin du fichier CSV

    static {
        // Initialisation des listes uniquement si elles ne sont pas déjà initialisées
        preparations = new ArrayList<>();
        medicaments = new ArrayList<>();

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
                // On lit chaque ligne et incrémente l'identifiant s'il est plus grand que le compteur actuel
                String[] parts = line.split(",");
                if (parts.length >= 4) { // Assurez-vous que la ligne contient au moins quatre éléments (idUnique, nom, quantite, date)
                    String idPart = parts[0]; // Récupérer la première partie correspondant à l'ID unique
                    if (idPart.startsWith("CMD-PREP")) { // Vérifier si l'ID unique commence par "CMD-PREP"
                        int currentId = Integer.parseInt(idPart.substring(8)); // Récupérer l'identifiant numérique à partir de la position 8
                        idCounter = Math.max(idCounter, currentId);
                    }
                }
            }
            // Incrémentation pour le prochain identifiant
            idCounter++;
        } catch (IOException e) {
            // Gestion de l'exception en cas de problème lors de la lecture du fichier
            e.printStackTrace();
        }
    }

    private static @NotNull String genererIdUnique() {
        return "CMD-PREP" + idCounter++;
    }

    public static void ecrireRegistrePreparationCsv(@NotNull List<Medicament> selectedMedicaments, List<Integer> selectedQuantities, String csvFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            // Écrire chaque médicament dans une nouvelle ligne
            for (int i = 0; i < selectedMedicaments.size(); i++) {
                Medicament medicament = selectedMedicaments.get(i);
                int quantite = selectedQuantities.get(i);
                String idUnique = genererIdUnique();
                String nom = medicament.getNom();
                String date = LocalDate.now().format(DateTimeFormatter.ISO_DATE); // Formatage de la date

                writer.write(
                        idUnique + "," +
                                nom + "," +
                                quantite + "," +
                                date + "\n"
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
