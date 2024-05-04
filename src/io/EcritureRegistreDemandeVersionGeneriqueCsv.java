package io;

import pharmacie.DemandeVersionGenerique;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EcritureRegistreDemandeVersionGeneriqueCsv {

    public static void ecrireDemandesVersionGeneriqueCsv(DemandeVersionGenerique demande) {
        // Chemin du fichier CSV
        String filePath = "src/data/demandes_version_generique.csv";

        // Vérifier si le fichier existe
        File file = new File(filePath);
        boolean fileExists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Écrire l'entête si le fichier n'existe pas
            if (!fileExists) {
                writer.write("Nom du médicament, Demande, Date de demande");
                writer.newLine();
            }

            // Écrire la demande dans le fichier CSV
            writer.write(demande.getNomMedicament() + ",");
            writer.write(demande.isDemande() ? "1" : "0");
            writer.write("," + demande.getDate()); // Ajout de la date de demande
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}