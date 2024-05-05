package io;

import pharmacie.Medicament;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EcritureMedicamentsCsv {
    public static void ecrireMedicamentsCsv(List<Medicament> medicaments, String fichierCsv) throws IOException {
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
}
