package io;

import pharmacie.Medecin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EcritureMedecinCsv {
    public static void ecrireMedecinsCsv(List<Medecin> medecins, String fichierCsv) throws IOException {
        // Créer le fichier CSV s'il n'existe pas
        File file = new File(fichierCsv);
        if (!file.exists()) {
            file.createNewFile();
        }

        // Écrire les médecins dans le fichier CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCsv))) {
            for (Medecin medecin : medecins) {
                writer.write(medecin.getNom() + "," + medecin.getPrenom() + "," + medecin.getId() + "," + medecin.getMotDePasse() + "\n");
            }
        }
    }
}
