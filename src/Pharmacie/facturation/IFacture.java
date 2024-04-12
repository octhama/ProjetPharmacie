package Pharmacie.facturation;

import Pharmacie.preparation.Preparation;

public interface IFacture {
    void ajouterFacture(Facture facture);
    void genererFacture(Preparation preparation);
    void payerFacture(Facture facture);
    void listerFactures();
    void rechercherFacture(String numeroFacture);
    void supprimerFacture(String numeroFacture);
}
