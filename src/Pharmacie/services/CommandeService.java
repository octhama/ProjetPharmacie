package Pharmacie.services;

import Pharmacie.Pharmacie;
import Pharmacie.composants.Commande;
import Pharmacie.composants.Medicament;
import Pharmacie.interfaces.ICommandeService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommandeService implements ICommandeService {

    private GestionStockService gestionStockService;
    private Pharmacie pharmacie;
    private List<Commande> historiqueCommandes;

    public CommandeService(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
        this.gestionStockService = gestionStockService;
        this.historiqueCommandes = new ArrayList<>();
    }

    @Override
    public void passerCommande(Medicament medicament, int quantite) {
        if (gestionStockService.isMedicamentDisponible(medicament, quantite)) {
            gestionStockService.retirerMedicament(medicament, quantite);
            Commande commande = new Commande(historiqueCommandes.size() + 1, medicament, quantite, new Date(), false);
            historiqueCommandes.add(commande);
            System.out.println("Commande passée avec succès pour le médicament " + medicament.getNom() + " (quantité : " + quantite + ").");
        } else {
            System.err.println("Erreur lors de la commande de " + medicament.getNom() + " : " + gestionStockService.getQuantiteStock(medicament) + " disponible(s), " + quantite + " demandé(s).");
        }
    }

    @Override
    public List<Commande> getHistoriqueCommandes() {
        return historiqueCommandes;
    }
    // Additional methods (optional)
    public void marquerCommandeLivree(int idCommande) {
        for (Commande commande : historiqueCommandes) {
            if (commande.getId() == idCommande) {
                commande.setLivree(true);
                System.out.println("Commande " + idCommande + " marquée comme livrée.");
                return;
            }
        }
        System.out.println("Aucune commande trouvée avec l'ID " + idCommande);
    }

    public void receptionnerLivraison(List<Medicament> medicaments) {
        for (Medicament medicament : medicaments) {
            gestionStockService.ajouterMedicament(medicament);
        }
        System.out.println("Livraison réceptionnée avec succès.");
    }
}


