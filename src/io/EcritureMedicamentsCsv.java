package io;

import org.jetbrains.annotations.NotNull;
import pharmacie.Medicament;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
/**
 * Classe pour écrire les médicaments dans un fichier CSV.
 * On peut écrire les médicaments dans un fichier CSV.
 * @see Medicament
 * @see pharmacie.Medicament
 * @see pharmacie.Medicament#Medicament(String, double, enums.ETypeMedicament, boolean, int, boolean, boolean)
 * @see pharmacie.Medicament#getNom()
 * @see pharmacie.Medicament#getPrix()
 * @see pharmacie.Medicament#getType()
 * @see pharmacie.Medicament#isGenerique()
 * @see pharmacie.Medicament#getQuantiteEnStock()
 * @see pharmacie.Medicament#Medicament(String, int, boolean)
 */
public class EcritureMedicamentsCsv {
    /**
     * Méthode pour écrire les médicaments dans un fichier CSV.
     * @param medicaments Liste des médicaments
     * @param fichierCsv Chemin du fichier CSV
     * @throws IOException Si une erreur d'entrée/sortie s'est produite
     */
    public static void ecrireMajQttStockMedicamentsCsv(@NotNull List<Medicament> medicaments, String fichierCsv) throws IOException {
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

    /**
     * Méthode pour écrire l'ajout d'un médicament dans un fichier CSV.
     * @param fichierCsv Chemin du fichier CSV
     * @param medicament Médicament à ajouter
     * @throws IOException Si une erreur d'entrée/sortie s'est produite
     * @see Medicament
     * @see pharmacie.Medicament#Medicament(String, double, enums.ETypeMedicament, boolean, int, boolean, boolean)
     * @see pharmacie.Medicament#getNom()
     * @see pharmacie.Medicament#getPrix()
     * @see pharmacie.Medicament#getType()
     * @see pharmacie.Medicament#isGenerique()
     * @see pharmacie.Medicament#getQuantiteEnStock()
     */
    public static void ecrireAjoutDeMedicamentCsv(String fichierCsv, @NotNull Medicament medicament) throws IOException {
        // Construire la ligne à écrire dans le fichier CSV
        String ligne = medicament.getNom() + "," + medicament.getPrix() + "," + medicament.getType() + "," + medicament.isGenerique() + "," + medicament.getQuantiteEnStock();

        // Écrire la ligne dans le fichier CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCsv, true))) {
            writer.write(ligne + "\n");
        }
    }

    /**
     * Méthode pour écrire la suppression d'un médicament dans un fichier CSV.
     * @param medicament Médicament à supprimer
     * @param fichierCsv Chemin du fichier CSV
     * @throws IOException Si une erreur d'entrée/sortie s'est produite
     */
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

    /**
     * Méthode pour écrire la modification d'un médicament dans un fichier CSV.
     * @param medicament Médicament à modifier
     * @param fichierCsv Chemin du fichier CSV
     * @throws IOException Si une erreur d'entrée/sortie s'est produite
     */
    public static void ecrireModificationDeMedicamentCsv(Medicament medicament, String fichierCsv) throws IOException {
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
