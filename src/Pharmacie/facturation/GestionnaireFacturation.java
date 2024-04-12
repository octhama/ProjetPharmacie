package Pharmacie.facturation;

import Pharmacie.preparation.Preparation;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireFacturation {
    private List<Facture> factures;

    public GestionnaireFacturation() {
        this.factures = new ArrayList<>();
    }

    // Constructeur
    public GestionnaireFacturation(List<Facture> factures) {
        this.factures = factures;
    }

    public void ajouterFacture(Facture facture) {
        // Ajouter la facture à la liste
        factures.add(facture);
    }

    public void genererFacture(Preparation preparation) {
        // Générer une facture pour la préparation
        Facture facture = new Facture(preparation);
        factures.add(facture);
    }

    public void payerFacture(Facture facture) {
        // Payer la facture
        facture.setPaiement(true);
    }

    public void listerFactures() {
        // Lister toutes les factures
        for (Facture facture : factures) {
            System.out.println(facture);
        }
    }

    public void rechercherFacture(String numeroFacture) {
        // Rechercher une facture par son numéro
        for (Facture facture : factures) {
            if (facture.getNumero().equals(numeroFacture)) {
                System.out.println(facture);
                break;
            }
        }
    }

    public void supprimerFacture(String numeroFacture) {
        // Supprimer une facture par son numéro
        for (Facture facture : factures) {
            if (facture.getNumero().equals(numeroFacture)) {
                factures.remove(facture);
                break;
            }
        }
    }

    // Getters et setters
    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }


    // toString
    @Override
    public String toString() {
        return "GestionnaireFacturation{" +
                "factures=" + factures +
                '}';
    }

    // Autres méthodes pour la gestion des factures
    public void envoyerFacture(Facture facture) {
        // Envoyer la facture par courrier
        facture.setEnvoyee(true);
    }

    public void archiverFacture(Facture facture) {
        // Archiver la facture dans une base de données
        facture.setArchivee(true);
    }

    public void imprimerFacture(Facture facture) {
        // Imprimer la facture
        facture.setImprimee(true);
    }

    public void envoyerFactureParEmail(Facture facture) {
        // Envoyer la facture par email
        facture.setEnvoyeeParEmail(true);
    }

    public void envoyerFactureParFax(Facture facture) {
        // Envoyer la facture par fax
        facture.setEnvoyeeParFax(true);
    }

    public void envoyerFactureParCourrierPostal(Facture facture) {
        // Envoyer la facture par courrier postal
        facture.setEnvoyeeParCourrierPostal(true);
    }

    public void envoyerFactureParMessagerie(Facture facture) {
        // Envoyer la facture par messagerie
        facture.setEnvoyeeParMessagerie(true);
    }
}

