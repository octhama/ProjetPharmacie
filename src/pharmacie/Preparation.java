package pharmacie;

import java.time.LocalDate;
import java.util.*;
import java.util.List;
/**
 * Classe pour les préparations.
 * On peut ajouter une préparation, retirer une préparation, etc.
 * @see Preparation#setDate(String)
 * @see Preparation#setNom(String)
 * @see Preparation#setQuantite(int)
 * @see Preparation#toString()
 */
public class Preparation {
    private String idUnique;
    private String nom;
    private int quantite;
    private String date;
    private Commande commande; // Attribut pour stocker la commande associée à la préparation

    // Ajoutez ce constructeur à la classe Preparation
    public Preparation() {
    }

    public Preparation(String idUnique, String nom, int quantite, String date) {
        this.idUnique = idUnique;
        this.nom = nom;
        this.quantite = quantite;
        this.date = date;

    }

    @Override
    public String toString() {
        return "Preparation{" +
                "idUnique='" + idUnique + '\'' +
                ", nom='" + nom + '\'' +
                ", quantite=" + quantite +
                ", date='" + date + '\'' +
                '}';
    }

    // Déclaration d'une liste statique pour stocker les préparations
    private static final List<Preparation> preparations = new ArrayList<>();

    public String getIdUnique() {
        return idUnique;
    }

    // Méthode pour récupérer le nom de la préparation
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Méthode pour récupérer la commande associée à la préparation
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public LocalDate getDate() {
        return LocalDate.parse(date);
    }

    // Méthode pour récupérer la date de la préparation
    public void setDate(String date) {
        this.date = date;
    }

    // Méthode pour récupérer la liste des préparations
    public static List<Preparation> getPreparations() {
        return preparations;
    }

}
