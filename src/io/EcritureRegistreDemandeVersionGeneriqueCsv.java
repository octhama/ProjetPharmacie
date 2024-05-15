package io;

import pharmacie.DemandeVersionGenerique;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EcritureRegistreDemandeVersionGeneriqueCsv {
    public static void ecrireDemandesVersionGeneriqueCsv(List<DemandeVersionGenerique> demandes) {
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

            // Formatter pour la date
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = LocalDate.now().format(dateFormatter);

            // Écrire chaque demande dans le fichier CSV
            for (DemandeVersionGenerique demande : demandes) {
                writer.write(demande.getNomMedicament() + ",");
                writer.write(demande.isDemande() ? "1" : "0");
                writer.write("," + formattedDate); // Ajout de la date de demande formatée
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
