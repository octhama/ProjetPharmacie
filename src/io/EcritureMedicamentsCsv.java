package io;

import pharmacie.Medicament;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EcritureMedicamentsCsv {
    public static void ecrireMedicamentsCsv(List<Medicament> medicaments, String fichierCsv) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCsv))) {
            // Écrire l'en-tête du fichier CSV
            //writer.write("nom, prix, type_medicament,generique,quantite_stock\n");
            // Écrire chaque médicament dans une nouvelle ligne
            for (Medicament medicament : medicaments) {
                writer.write(
                        medicament.getNom() + "," +
                                medicament.getPrix() + "," +
                                medicament.getTypeMedicament() + "," +
                                medicament.isGenerique() + "," +
                                medicament.getQuantiteEnStock() + "\n"
                );
            }
        }
    }

    public static List<Medicament> getMedicaments() throws IOException {
        List<Medicament> medicaments = new ArrayList<>();
        try {
            medicaments = LectureMedicamentsCsv.lireMedicamentsCsv("src/data/medicaments.csv");
        } catch (IOException e) {
            // Propager l'exception pour que la méthode appelante puisse la gérer
            throw e;
        }
        return medicaments;
    }
}
