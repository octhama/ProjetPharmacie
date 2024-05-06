package io;

import pharmacie.Medecin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectureMedecinCsv {
private LectureMedecinCsv() {
        // Constructeur privé pour empêcher l'instanciation de la classe
        throw new AssertionError("Cette classe ne peut pas être instanciée");
    }

    public static List<Medecin> chargerMedecinsDepuisCSV() throws IOException {
        List<Medecin> medecins = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/data/authidmedecin.csv"))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] elements = ligne.split(",");
                if (elements.length == 4) {
                    Medecin medecin = new Medecin(elements[0], elements[1], elements[2], elements[3]);
                    medecins.add(medecin);
                } else {
                    System.err.println("Erreur de format dans le fichier CSV pour la ligne : " + ligne);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return medecins;
    }
}
