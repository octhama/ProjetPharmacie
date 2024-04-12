package Pharmacie.ordonnance;

public interface IOrdonnance {
    void ajouterOrdonnance(Ordonnance ordonnance);
    void supprimerOrdonnance(Ordonnance ordonnance);
    void modifierOrdonnance(Ordonnance ordonnance);
}
