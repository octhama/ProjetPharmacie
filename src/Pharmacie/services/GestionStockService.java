package Pharmacie.services;

import Pharmacie.Pharmacie;
import Pharmacie.composants.Medicament;
import Pharmacie.interfaces.IGestionStock;

import java.util.HashMap;
import java.util.List;

public class GestionStockService implements IGestionStock {

    private HashMap<Medicament, Integer> stock;

    public GestionStockService(Pharmacie pharmacie) {
        this.stock = new HashMap<>();
        for (Medicament medicament : pharmacie.getMedicaments()) {
            stock.put(medicament, medicament.getQuantiteStock());
        }
    }

    @Override
    public void ajouterMedicament(Medicament medicament) {
        if (stock.containsKey(medicament)) {
            stock.put(medicament, stock.get(medicament) + medicament.getQuantiteStock());
        } else {
            stock.put(medicament, medicament.getQuantiteStock());
        }
    }

    @Override
    public void retirerMedicament(Medicament medicament, int quantite) {
        if (stock.containsKey(medicament)) {
            int currentQuantity = stock.get(medicament);
            if (currentQuantity < quantite) {
                throw new Error("Stock insuffisant pour le médicament " + medicament.getNom() + ". Quantité disponible : " + currentQuantity + ", Quantité demandée : " + quantite);
            }
            stock.put(medicament, currentQuantity - quantite);
        } else {
            throw new Error("Médicament inconnu : " + medicament.getNom());
        }
    }

    @Override
    public int getQuantiteStock(Medicament medicament) {
        if (stock.containsKey(medicament)) {
            return stock.get(medicament);
        } else {
            return 0;
        }
    }

    @Override
    public boolean isMedicamentDisponible(Medicament medicament, int quantite) {
        return stock.containsKey(medicament) && stock.get(medicament) >= quantite;
    }

}
