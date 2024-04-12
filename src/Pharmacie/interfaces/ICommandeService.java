package Pharmacie.interfaces;

import Pharmacie.composants.Commande;
import Pharmacie.composants.Medicament;

import java.util.List;

public interface ICommandeService {

    void passerCommande(Medicament medicament, int quantite);

    List<Commande> getHistoriqueCommandes();
}


