package io;

import pharmacie.Medicament;
import pharmacie.Preparation;

import java.io.*;
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
                System.out.println("Line read from CSV: \n\n" + line); // Ajout d'une instruction d'impression pour le débogage
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

    public static void ecrirePreparationsCsv(Preparation preparation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCsv, true))) {
            // Vérifier si le fichier est vide ou si l'en-tête est absent
            if (!containsHeader()) {
                // Écrire l'en-tête
                writer.write("idUnique,nom,quantite,date\n");
            }
            // On ajoute la préparation à la liste
            preparations.add(preparation);
            // On met à jour la quantité de médicament en stock dans la liste des médicaments
            Medicament medicament = medicaments.stream()
                    .filter(m -> m.getNom().equals(preparation.getNom()))
                    .findFirst()
                    .orElse(null);

            if (medicament != null && medicament.getQuantiteEnStock() >= preparation.getQuantite()) {
                // Si le médicament est trouvé et la quantité est suffisante, décrémentez la quantité en stock
                medicament.setQuantiteEnStock(medicament.getQuantiteEnStock() - preparation.getQuantite());

                // Ecriture de la ligne dans le fichier CSV
                String idUnique = "CMD-PRP" + idCounter; // Identifiant unique auto-généré
                String nom = preparation.getNom() != null ? preparation.getNom() : "null";
                String quantite = String.valueOf(preparation.getQuantite());
                String date = preparation.getDate() != null ? preparation.getDate() : "null";

                // Ecriture de la ligne dans le fichier CSV
                writer.write(
                        idUnique + "," +
                                nom + "," +
                                quantite + "," +
                                date + "\n"
                );
                idCounter++; // Incrémenter le compteur pour le prochain identifiant
            } else {
                // Gérez le cas où le médicament n'est pas trouvé ou la quantité en stock est insuffisante
                System.out.println("Le médicament " + preparation.getNom() + " n'a pas été trouvé dans la liste des médicaments ou la quantité en stock est insuffisante.");
                // Ajoutez ici la logique supplémentaire que vous souhaitez exécuter lorsque le médicament n'est pas trouvé ou la quantité en stock est insuffisante
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
