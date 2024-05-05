package pharmacie;
import java.util.regex.*;

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

    public List<Medicament> filterMedicaments(String search) {
        // Créer une liste pour stocker les médicaments filtrés
        List<Medicament> filteredMedicaments = new ArrayList<>();

        // Échapper les caractères spéciaux dans la recherche pour éviter les erreurs dans l'expression régulière
        String escapedSearch = Pattern.quote(search);

        // Créer un pattern regex avec une correspondance partielle du terme de recherche dans le nom du médicament
        Pattern pattern = Pattern.compile("\\b" + escapedSearch, Pattern.CASE_INSENSITIVE);

        // Parcourir la liste des médicaments
        for (Medicament medicament : medicaments) {
            // Vérifier si le nom du médicament correspond au pattern regex
            Matcher matcher = pattern.matcher(medicament.getNom());
            if (matcher.find()) {
                // Ajouter le médicament à la liste des médicaments filtrés
                filteredMedicaments.add(medicament);
            }
        }

        // Retourner la liste des médicaments filtrés
        return filteredMedicaments;
    }

    public List<Medicament> getMedicamentsNonGeneriques() {
        for (Medicament medicament : medicaments) {
            if (!medicament.isGenerique()) {
                return medicaments;
            }
        }
        return List.of();
    }

    public List<DemandeVersionGenerique> getDemandesVersionGenerique() {
        List<DemandeVersionGenerique> demandes = new ArrayList<>();
        for (Medicament medicament : medicaments) {
            if (!medicament.isGenerique()) {
                demandes.add(new DemandeVersionGenerique(medicament.getNom()));
            }
        }
        return demandes;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Medecin> getMedecins() {
        return List.of();
    }

    public void ajouterMedicament(Medicament medicament) {
        medicaments.add(medicament);
    }
}