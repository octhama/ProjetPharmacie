package io;

import pharmacie.Commande;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe pour lire les commandes dans un fichier CSV.
 * On peut lire les commandes dans un fichier CSV.
 * @see pharmacie.Commande
 * @see pharmacie.Commande#Commande(String, int, String, int, String)
 * @see pharmacie.Commande#getIdUnique()
 * @see pharmacie.Commande#getNom()
 * @see pharmacie.Commande#getNumeroCommande()
 * @see pharmacie.Commande#getQuantite()
 * @see pharmacie.Commande#getDate()
 */
public class LectureCommande {
    public static List<Commande> lireCommandes(String cheminFichier) throws Exception {
        List<Commande> commandes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            reader.readLine(); // Lire la première ligne (entêtes) et l'ignorer
            while ((ligne = reader.readLine()) != null) {
                String[] champs = ligne.split(",");
                String idUnique = champs[0];
                int numeroCommande = Integer.parseInt(champs[1]);
                String nom = champs[2];
                int quantite = Integer.parseInt(champs[3]);
                String date = champs[4];

                Commande commande = new Commande(idUnique, numeroCommande, nom, quantite, date);
                commandes.add(commande);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commandes;
    }
}
