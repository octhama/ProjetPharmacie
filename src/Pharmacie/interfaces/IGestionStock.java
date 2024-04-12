package Pharmacie.interfaces;

import Pharmacie.composants.Medicament;

public interface IGestionStock {

    void ajouterMedicament(Medicament medicament);

    void retirerMedicament(Medicament medicament, int quantite);

    int getQuantiteStock(Medicament medicament);

    boolean isMedicamentDisponible(Medicament medicament, int quantite);
}


