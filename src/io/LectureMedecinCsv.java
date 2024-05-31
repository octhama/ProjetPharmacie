package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Classe pour lire les identifiants des médecins dans un fichier CSV.
 * On peut lire les identifiants des médecins dans un fichier CSV.
 * @see pharmacie.Medecin
 * @see pharmacie.Medecin#Medecin(String, String, String, String)
 * @see pharmacie.Medecin#getId()
 * @see pharmacie.Medecin#getMotDePasse()
 * @see pharmacie.Medecin#getNom()
 * @see pharmacie.Medecin#getPrenom()
 * @see pharmacie.Medecin#Medecin(String, String, String, String)
 */
public class LectureMedecinCsv {
private LectureMedecinCsv() {
        // Constructeur privé pour empêcher l'instanciation de la classe
        throw new AssertionError("Cette classe ne peut pas être instanciée");
    }
    // Méthode pour authentifier le médecin
    public static boolean authentifierMedecin(String id, String password) {
        String csvFilePath = "src/data/authidmedecin.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Lire chaque ligne du fichier CSV
            while ((line = br.readLine()) != null) {
                // Diviser la ligne en utilisant le délimiteur approprié (virgule dans ce cas)
                String[] parts = line.split(",");
                // Vérifier si la ligne contient l'identifiant et le mot de passe fournis
                if (parts.length >= 2 && parts[0].equals(id) && parts[1].equals(password)) {
                    return true; // Authentification réussie
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Authentification échouée
    }
}
