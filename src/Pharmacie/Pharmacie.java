package Pharmacie;
import Pharmacie.composants.Medicament;
import Pharmacie.composants.Ordonnance;
import Pharmacie.composants.Preparation;
import Pharmacie.services.CommandeService;
import Pharmacie.services.GestionStockService;
import Pharmacie.services.OrdonnanceService;

import java.util.ArrayList;
import java.util.List;

// Classe Pharmacie

public class Pharmacie {

    private String nom; // Nom de la pharmacie
    private List<Medicament> stockMedicaments; // Liste des médicaments en stock
    private List<Preparation> preparationsEnCours; // Liste des préparations en cours
    private GestionStockService gestionStockService; // Service de gestion des stocks
    private CommandeService commandeService; // Service de commande de médicaments
    private OrdonnanceService ordonnanceService; // Service de gestion des ordonnances

    // Constructeurs
    public Pharmacie(String nom) {
        this.nom = nom;
        this.stockMedicaments = new ArrayList<>();
        this.preparationsEnCours = new ArrayList<>();
    }

    // Getters et setters
    // ... (ajoutez les getters et setters pour chaque attribut)

    public void ajouterMedicament(Medicament medicament) {
        gestionStockService.ajouterMedicament(medicament);
    }

    public void retirerMedicament(Medicament medicament, int quantite) throws UnsupportedOperationException {
        gestionStockService.retirerMedicament(medicament, quantite);
    }

    public int getQuantiteStock(Medicament medicament) {
        return gestionStockService.getQuantiteStock(medicament);
    }

    public void passerCommande(Medicament medicament, int quantite) {
        commandeService.passerCommande(medicament, quantite);
    }

    public void receptionnerLivraison(List<Medicament> medicaments) {
        commandeService.receptionnerLivraison(medicaments);
    }

    public void recevoirOrdonnance(Ordonnance ordonnance) {
        ordonnanceService.recevoirOrdonnance(ordonnance);
    }

    public Preparation preparerOrdonnance(Ordonnance ordonnance) {
        return ordonnanceService.preparerOrdonnance(ordonnance);
    }

    public void delivrerPreparation(Preparation preparation) {
        ordonnanceService.delivrerPreparation(preparation);
    }

    @Override
    public String toString() {
        return "Pharmacie{" +
                "nom='" + nom + '\'' +
                ", stockMedicaments=" + stockMedicaments +
                ", preparationsEnCours=" + preparationsEnCours +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public List<Medicament> getStockMedicaments() {
        return stockMedicaments;
    }

    public Preparation[] getPreparationsEnCours() {
        return preparationsEnCours.toArray(new Preparation[0]);
    }

    public Medicament[] getMedicaments() {
        return stockMedicaments.toArray(new Medicament[0]);
    }
}



