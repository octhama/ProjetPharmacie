package io;

import pharmacie.Commande;
import pharmacie.Preparation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectureRegistrePreparation {
    public static List<Preparation> lireRegistrePreparation(String cheminFichier) throws IOException {
        List<Preparation> preparations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            reader.readLine(); // Lire la première ligne (entêtes) et l'ignorer
            while ((ligne = reader.readLine()) != null) {
                String[] champs = ligne.split(",");
                String idUnique = champs[0];
                int numeroCommande = Integer.parseInt(champs[1]);
                String nom = champs[2];
                String commentaires = champs[3];
                int quantite = Integer.parseInt(champs[4]);
                String date = champs[5];

                Preparation preparation = new Preparation(idUnique, nom, quantite, date);
                preparations.add(preparation);
                Commande commande = new Commande(idUnique, numeroCommande, commentaires, quantite, date);
                preparation.setCommande(commande);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return preparations;
    }
}


