package io;

import enums.ETypeMedicament;
import exceptions.ExeptionRuptureDeStock;
import org.jetbrains.annotations.NotNull;
import pharmacie.Medicament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaire pour lire les médicaments d'un fichier CSV.
 */

public class LectureMedicamentsCsv {
    private LectureMedicamentsCsv() {
        // Constructeur privé pour empêcher l'instanciation de la classe
        throw new AssertionError("Cette classe ne peut pas être instanciée");
    }

    public static @NotNull List<Medicament> lireMedicamentsCsv(String cheminFichier) throws IOException {
        List<Medicament> medicaments = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            reader.readLine(); // Ignorer l'en-tête du fichier CSV

            while ((ligne = reader.readLine()) != null) {
                String[] champs = ligne.split(",");

                String nom = champs[0];
                double prix = Double.parseDouble(champs[1]);
                String type = champs[2];
                boolean generique = Boolean.parseBoolean(champs[3]);
                final int[] quantiteStock = {Integer.parseInt(champs[4])};

                Medicament medicament = new Medicament(nom, prix, ETypeMedicament.valueOf(type), generique, quantiteStock[0]) {
                    @Override
                    public void acheter(int quantite) throws ExeptionRuptureDeStock {
                        if (quantite > quantiteStock[0]) {
                            throw new ExeptionRuptureDeStock("Rupture de stock pour le médicament " + nom);
                        }

                        quantiteStock[0] -= quantite;
                    }
                };

                medicaments.add(medicament);
            }
        }

        return medicaments;
    }

    public static @NotNull List<String> getMedicamentsPrescrits(String referencePatient) {
        String csvFilePath = "src/data/dataordonnances.csv";
        List<String> medicamentsPrescrits = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Lire chaque ligne du fichier CSV
            while ((line = br.readLine()) != null) {
                // Diviser la ligne en utilisant le délimiteur approprié (virgule dans ce cas)
                String[] parts = line.split(",");
                // Vérifier si la ligne contient la référence du patient donnée
                if (parts.length >= 2 && parts[1].trim().equals(referencePatient.trim())) {
                    // Ajouter les médicaments prescrits à la liste
                    if (parts.length >= 4) {
                        String[] medicaments = parts[3].split(";");
                        for (String medicament : medicaments) {
                            medicamentsPrescrits.add(medicament.trim());
                        }
                    }
                    break; // Arrêter la recherche après avoir trouvé la référence du patient
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return medicamentsPrescrits;
    }
}

