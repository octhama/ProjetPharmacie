package Pharmacie.ordonnance;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireOrdonnances {
    private List<Ordonnance> ordonnances;
    // Constructeur par défaut
    public GestionnaireOrdonnances() {
        this.ordonnances = new ArrayList<>();
    }
    // Constructeur avec liste d'ordonnances
    public GestionnaireOrdonnances(List<Ordonnance> ordonnances) {
        this.ordonnances = ordonnances;
    }
    // Méthodes pour la gestion des ordonnances

    public void ajouterOrdonnance(Ordonnance ordonnance) {
        // Ajouter l'ordonnance à la liste
        ordonnances.add(ordonnance);
    }

    public void supprimerOrdonnance(Ordonnance ordonnance) {
        // Supprimer l'ordonnance de la liste
        ordonnances.remove(ordonnance);
    }

    public List<Ordonnance> getOrdonnances() {
        // Retourner la liste des ordonnances
        return ordonnances;
    }

    // Autres méthodes pour la gestion des ordonnances
    public void modifierOrdonnance(Ordonnance ordonnance) {
        // Modifier l'ordonnance dans la liste
        ordonnances.set(ordonnances.indexOf(ordonnance), ordonnance);
    }
}

