package Pharmacie;

public interface IMedicament {
    void ajouterMedicament(Medicament medicament);
    void supprimerMedicament(Medicament medicament);
    void commanderMedicament(String nom, int quantite);
    void vendreMedicament(String nom, int quantite);
    void afficherMedicaments();
    Medicament getMedicament(String nom);
    void modifierMedicament(Medicament medicament);
    int getQuantiteMedicament(String nom);
    void setQuantiteMedicament(String nom, int quantite);
    void afficherMedicamentsEnRupture();
    void afficherMedicamentsEnSurplus();
    void afficherMedicamentsParCategorie(String categorie);
}
