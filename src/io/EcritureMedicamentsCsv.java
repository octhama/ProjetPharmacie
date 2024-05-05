package io;

import pharmacie.Medicament;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EcritureMedicamentsCsv {
    public static void ecrireMajQttStockMedicamentsCsv(List<Medicament> medicaments, String fichierCsv) throws IOException {
        // Lire toutes les lignes du fichier CSV
        List<String> lignes = Files.readAllLines(Paths.get(fichierCsv));

        // Parcourir chaque médicament à mettre à jour
        for (Medicament medicament : medicaments) {
            // Parcourir chaque ligne du fichier CSV
            for (int i = 0; i < lignes.size(); i++) {
                String ligne = lignes.get(i);
                String[] valeurs = ligne.split(",");
                String nomMedicament = valeurs[0];

                // Vérifier si le nom du médicament correspond à celui à mettre à jour
                if (nomMedicament.equals(medicament.getNom())) {
                    // Mettre à jour la quantité en stock dans la ligne correspondante
                    valeurs[4] = Integer.toString(medicament.getQuantiteEnStock());
                    // Reconstruire la ligne mise à jour
                    ligne = String.join(",", valeurs);
                    // Remplacer la ligne dans la liste
                    lignes.set(i, ligne);
                    // Sortir de la boucle interne
                    break;
                }
            }
        }

        // Écrire les lignes mises à jour dans le fichier CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCsv))) {
            for (String ligne : lignes) {
                writer.write(ligne + "\n");
            }
        }
    }

    public static void ecrireAjoutDeMedicamentCsv(String fichierCsv, Medicament medicament) throws IOException {
        // Construire la ligne à écrire dans le fichier CSV
        String ligne = medicament.getNom() + "," + medicament.getPrix() + "," + medicament.getType() + "," + medicament.isGenerique() + "," + medicament.getQuantiteEnStock();

        // Écrire la ligne dans le fichier CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCsv, true))) {
            writer.write(ligne + "\n");
        }
    }

    public static void ecrireSuppressionDeMedicamentCsv(Medicament medicament, String fichierCsv) throws IOException {
        // Lire toutes les lignes du fichier CSV
        List<String> lignes = Files.readAllLines(Paths.get(fichierCsv));

        // Parcourir chaque ligne du fichier CSV
        for (int i = 0; i < lignes.size(); i++) {
            String ligne = lignes.get(i);
            String[] valeurs = ligne.split(",");
            String nomMedicament = valeurs[0];

            // Vérifier si le nom du médicament correspond à celui à supprimer
            if (nomMedicament.equals(medicament.getNom())) {
                // Supprimer la ligne correspondante
                lignes.remove(i);
                // Sortir de la boucle
                break;
            }
        }

        // Écrire les lignes mises à jour dans le fichier CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCsv))) {
            for (String ligne : lignes) {
                writer.write(ligne + "\n");
            }
        }
    }

    public static void ecrireModifierMedicamentCsv(Medicament medicament, String fichierCsv) throws IOException {
        // Lire toutes les lignes du fichier CSV
        List<String> lignes = Files.readAllLines(Paths.get(fichierCsv));

        // Parcourir chaque ligne du fichier CSV
        for (int i = 0; i < lignes.size(); i++) {
            String ligne = lignes.get(i);
            String[] valeurs = ligne.split(",");
            String nomMedicament = valeurs[0];

            // Vérifier si le nom du médicament correspond à celui à modifier
            if (nomMedicament.equals(medicament.getNom())) {
                // Mettre à jour les valeurs du médicament dans la ligne correspondante
                valeurs[1] = Double.toString(medicament.getPrix());
                valeurs[2] = medicament.getType().toString();
                valeurs[3] = Boolean.toString(medicament.isGenerique());
                valeurs[4] = Integer.toString(medicament.getQuantiteEnStock());
                // Reconstruire la ligne mise à jour
                ligne = String.join(",", valeurs);
                // Remplacer la ligne dans la liste
                lignes.set(i, ligne);
                // Sortir de la boucle
                break;
            }
        }

        // Écrire les lignes mises à jour dans le fichier CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCsv))) {
            for (String ligne : lignes) {
                writer.write(ligne + "\n");
            }
        }
    }

}
