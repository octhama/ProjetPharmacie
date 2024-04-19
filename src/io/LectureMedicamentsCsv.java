package io;

import enums.ETypeMedicament;
import exceptions.ExeptionRuptureDeStock;
import pharmacie.Medicament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectureMedicamentsCsv {
    private LectureMedicamentsCsv() {
        // Constructeur privé pour empêcher l'instanciation de la classe
        throw new AssertionError("Cette classe ne peut pas être instanciée");
    }

    public static List<Medicament> lireMedicamentsCsv(String cheminFichier) throws IOException {
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
                          public void acheter(int quantite) {
                            quantiteStock[0] += quantite;
                          }
                };

                medicaments.add(medicament);
            }
        }

        return medicaments;
    }
}
