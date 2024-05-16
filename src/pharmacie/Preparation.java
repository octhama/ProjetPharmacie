package pharmacie;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

import interfaces.IDocuments;
import io.EcritureMedicamentsCsv;
import io.EcritureRegistrePreparationCsv;
import io.LectureMedicamentsCsv;
import io.LectureOrdonnanceCsv;
import org.jetbrains.annotations.NotNull;
import ui.UiGui;
import utils.DateUtlis;

public class Preparation {
    private String idUnique;
    private String nom;
    private int quantite;
    private String date;
    private Commande commande; // Attribut pour stocker la commande associée à la préparation


    // Ajoutez ce constructeur à la classe Preparation
    public Preparation() {
        this.nom = nom;
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
    private static List<Preparation> preparations = new ArrayList<>();

    public String getIdUnique() {
        return idUnique;
    }

    public void setIdUnique(String idUnique) {
        this.idUnique = idUnique;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static List<Preparation> getPreparations() {
        return preparations;
    }

    // Méthode pour associer une commande à la préparation
    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public void ajouterPreparations(List<Preparation> preparations) {
        if (preparations != null) {
            // Ajouter les préparations à la liste des préparations
            Preparation.preparations.addAll(preparations);
        }
    }
}
