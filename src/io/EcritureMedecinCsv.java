package io;

import pharmacie.Medecin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EcritureMedecinCsv {
    public static void ecrireMedecinsCsv(List<Medecin> medecins) {
        // Chemin du fichier CSV
        String filePath = "src/data/authidmedecin.csv";

        // Vérifier si le fichier existe
        File file = new File(filePath);
        boolean fileExists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Écrire l'entête si le fichier n'existe pas
            if (!fileExists) {
                writer.write("Nom, Prénom, Adresse, Téléphone, Email, Spécialité, Code postal, Ville, Numéro national, Date de naissance, Lieu de naissance, Sexe, Numéro carte euro assurance maladie");
                writer.newLine();
            }

            // Écrire chaque médecin dans le fichier CSV
            for (Medecin medecin : medecins) {
                writer.write(medecin.getNom() + ",");
                writer.write(medecin.getPrenom() + ",");
                writer.write(medecin.getAdresse() + ",");
                writer.write(medecin.getTelephone() + ",");
                writer.write(medecin.getEmail() + ",");
                writer.write(medecin.getSpecialite() + ",");
                writer.write(medecin.getCodePostal() + ",");
                writer.write(medecin.getVille() + ",");
                writer.write(medecin.getNumeroNational() + ",");
                writer.write(medecin.getDateDeNaissance() + ",");
                writer.write(medecin.getLieuDeNaissance() + ",");
                writer.write(medecin.getSexe() + ",");
                writer.write(medecin.getNumeroCarteEuroAssuranceMaladie());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
}
}
}
