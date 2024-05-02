package pharmacie;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;
import enums.ETypeMedicament;
import exceptions.ExeptionRuptureDeStock;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Pharmacie {
    private final List<Medicament> medicaments;
    private final List<Ordonnance> ordonnances;
    private final List<Patient> patients;

    public Pharmacie() {
        this.medicaments = new ArrayList<>();
        this.ordonnances = new ArrayList<>();
        this.patients = new ArrayList<>();
    }

    public void ajouterMedicament(String nom, double prix, ETypeMedicament type, boolean generique, int quantiteStock) {
        // Ajouter un médicament à la liste
        medicaments.add(new Medicament(nom, prix, type, generique, quantiteStock));
    }

    public List<Medicament> getMedicaments() {
        // Retourner la liste des médicaments
        return medicaments;
    }

    public List<Medicament> trouverMedicamentsSuggestions(String prefixe) {
        // Créer un pattern regex avec le préfixe du nom du médicament
        Pattern pattern = Pattern.compile("^" + prefixe, Pattern.CASE_INSENSITIVE);
    
        // Liste pour stocker les suggestions
        List<Medicament> suggestions = new ArrayList<>();
    
        // Parcourir la liste des médicaments
        for (Medicament medicament : medicaments) {
            // Vérifier si le nom du médicament correspond au pattern regex
            Matcher matcher = pattern.matcher(medicament.getNom());
            if (matcher.find()) {
                // Ajouter le médicament à la liste de suggestions
                suggestions.add(medicament);
            }
        }
    
        // Filtrer et trier la liste des suggestions
        suggestions = filtrerEtTrierSuggestions(suggestions);
    
        // Retourner la liste des suggestions
        return suggestions;
    }
    
    // Fonction de filtrage et de tri des suggestions
    private List<Medicament> filtrerEtTrierSuggestions(List<Medicament> suggestions) {
        return suggestions;
    }
    
    public void acheterMedicament(String nomMedicament, int quantite) throws ExeptionRuptureDeStock {
        // Implémentez la logique pour acheter un médicament
        Medicament medicament = (Medicament) trouverMedicamentsSuggestions(nomMedicament);
        if (medicament != null) {
            medicament.acheter(quantite);
        } else {
            JOptionPane.showMessageDialog(null, "Le médicament " + nomMedicament + " n'existe pas dans la pharmacie.");
        }
    }

    public void ajouterMedicaments(List<Medicament> medicaments) {
        // Ajouter des médicaments à la pharmacie
        this.medicaments.addAll(medicaments);
    }

    public Preparation commanderPreparation(Ordonnance ordonnance) throws Exception {
        // Vérifier si tous les médicaments de l'ordonnance sont disponibles en quantité suffisante
        for (Medicament medicament : ordonnance.getMedicaments()) {
            if (!medicaments.contains(medicament) || medicament.getQuantiteEnStock() <= 0) {
                throw new Exception("Médicament " + medicament.getNom() + " non disponible en quantité suffisante.");
            }
        }

        // Créer une nouvelle préparation avec les médicaments de l'ordonnance
        Preparation preparation = new Preparation();
        preparation.setMedicaments(ordonnance.getMedicaments());

        // Calculer le coût total de la préparation
        preparation.setCoutTotal(preparation.calculerCoutTotal());

        return preparation;
    }

    public void dispense(Medicament medicament, int quantite) throws ExeptionRuptureDeStock {
        // Vérifier si la quantité demandée est disponible en stock
        if (medicament.getQuantiteEnStock() < quantite) {
            // Lever une exception si la quantité demandée dépasse le stock disponible
            throw new ExeptionRuptureDeStock("Rupture de stock pour le médicament " + medicament.getNom());
        } else {
            // Déduire la quantité dispensée du stock du médicament
            medicament.setQuantiteEnStock(medicament.getQuantiteEnStock() - quantite);
            // Autres actions éventuelles liées à la dispensation du médicament
        }
    }

}