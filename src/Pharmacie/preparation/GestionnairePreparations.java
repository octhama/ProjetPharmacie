package Pharmacie.preparation;

import java.util.List;

public class GestionnairePreparations {
    private List<Preparation> preparations;

    public void creerPreparation(Preparation preparation) {
        // Ajouter la préparation à la liste
        preparations.add(preparation);
    }

    public void ordonnancerPreparation(Preparation preparation) {
        // Ordonnancer la préparation pour la délivrance
        preparation.ordonnancer();
    }

    public void delivrerPreparation(Preparation preparation) {
        // Délivrer la préparation au patient
        preparation.delivrer();
    }

    public void archiverPreparation(Preparation preparation) {
        // Archiver la préparation dans les dossiers
        preparation.archiver();
    }

    // Getters et setters
    public List<Preparation> getPreparations() {
        return preparations;
    }

    public void setPreparations(List<Preparation> preparations) {
        this.preparations = preparations;
    }

    // Méthode pour trouver une préparation par son nom
    public Preparation trouverPreparationParNom(String nom) {
        for (Preparation preparation : preparations) {
            if (preparation.getNom().equals(nom)) {
                return preparation;
            }
        }
        return null;
    }

    // Autres méthodes pour la gestion des préparations
    public void supprimerPreparation(Preparation preparation) {
        preparations.remove(preparation);
    }

    public void modifierPreparation(Preparation preparation) {
        // Modifier les informations de la préparation
        preparations.set(preparations.indexOf(preparation), preparation);
    }

    // Méthode pour afficher la liste des préparations
    public void afficherPreparations() {
        for (Preparation preparation : preparations) {
            System.out.println(preparation);
        }
    }
}

