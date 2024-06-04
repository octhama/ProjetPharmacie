package io;

import pharmacie.DemandeVersionGenerique;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static pharmacie.DemandeVersionGenerique.demandes;
/**
 * Classe pour lire les demandes de version générique dans un fichier CSV.
 * On peut lire les demandes de version générique dans un fichier CSV.
 * @see pharmacie.DemandeVersionGenerique
 * @see pharmacie.DemandeVersionGenerique#getNomMedicament()
 * @see pharmacie.DemandeVersionGenerique#isDemande()
 * @see pharmacie.DemandeVersionGenerique#DemandeVersionGenerique(String, boolean)
 * @see pharmacie.DemandeVersionGenerique#demandes
 * @see pharmacie.DemandeVersionGenerique#toString()
 * @see pharmacie.DemandeVersionGenerique#equals(Object)
 * @see pharmacie.DemandeVersionGenerique#hashCode()
 */
public class LectureDemandeVersionGeneriqueCsv {
    /**
     * Méthode pour lire les demandes de version générique dans un fichier CSV.
     * @param cheminFichier Chemin du fichier CSV
     * @return Liste des demandes de version générique
     */
    public static List<DemandeVersionGenerique> lireDemandesVersionGeneriqueCsv(String cheminFichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            reader.readLine(); // Ignorer l'en-tête du fichier CSV

            while ((ligne = reader.readLine()) != null) {
                String[] champs = ligne.split(",");

                // Vérifiez que le tableau champs contient les éléments attendus
                if (champs.length == 3) {
                    String nomMedicament = champs[0].trim();
                    boolean demande = champs[1].trim().equals("1");
                    LocalDate dateDemande = LocalDate.parse(champs[2].trim());

                    // Créez l'objet DemandeVersionGenerique
                    DemandeVersionGenerique demandeVersionGenerique = new DemandeVersionGenerique(nomMedicament, demande);

                    // Assurez-vous que la liste demandes est initialisée
                    if (demandes == null) {
                        demandes = new ArrayList<>();
                    }

                    // Ajoutez la demande à la liste demandes
                    demandes.add(demandeVersionGenerique);
                } else {
                    // Gérez le cas où une ligne du fichier CSV ne correspond pas au format attendu
                    System.err.println("La ligne du fichier CSV ne correspond pas au format attendu : " + ligne);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return demandes;
    }
}
