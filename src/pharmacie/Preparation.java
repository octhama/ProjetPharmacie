package pharmacie;

import exceptions.ExceptionCommandeInvalide;
import exceptions.ExeptionRuptureDeStock;

import java.util.Date;
import java.util.List;

public  class Preparation {
    private String nom;
    private List<Ingredient> ingredients;
    private Date datePreparation;

    // ... (autres attributs et méthodes)

    public void dispense() throws ExeptionRuptureDeStock {
    }

    public void ajouteIngredient(Medicament medicament, int quantite) throws ExceptionCommandeInvalide {
        // ... (Vérification du médicament et ajout à la liste des ingrédients)
    }


    public void close() {
        // ... (Fermeture de la préparation)
        System.out.println("La préparation a été fermée.");
    }
}
