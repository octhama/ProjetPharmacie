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



    public Medicament trouverMedicament(String nomMedicament) {
        // Créer un pattern regex avec le nom du médicament
        Pattern pattern = Pattern.compile(nomMedicament, Pattern.CASE_INSENSITIVE);

        // Parcourir la liste des médicaments
        for (Medicament medicament : medicaments) {
            // Vérifier si le nom du médicament correspond au pattern regex
            Matcher matcher = pattern.matcher(medicament.getNom());
            if (matcher.find()) {
                // Retourner le médicament trouvé
                return medicament;
            }
        }
        // Retourner null si le médicament n'est pas trouvé
        return null;
    }


    public void acheterMedicament(String nomMedicament, int quantite) throws ExeptionRuptureDeStock {
        // Implémentez la logique pour acheter un médicament
        Medicament medicament = trouverMedicament(nomMedicament);
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

    // Méthode commanderPreparation
public String commanderPreparation(HashMap<JCheckBox, JSpinner> checkBoxSpinnerMap) throws ExeptionRuptureDeStock {
    // Construire le message de confirmation
    StringBuilder confirmationMessage = new StringBuilder("Le(s) médicament(s) suivant(s) ont été commandé(s) :\n");

    // Parcourir chaque entrée dans la map
    for (Map.Entry<JCheckBox, JSpinner> entry : checkBoxSpinnerMap.entrySet()) {
        JCheckBox checkBox = entry.getKey();
        JSpinner spinner = entry.getValue();
        if (checkBox.isSelected()) {
            Medicament medicament = (Medicament) checkBox.getClientProperty("medicament");
            int quantite = (int) spinner.getValue();
            int quantiteEnStock = medicament.getQuantiteEnStock();

            // Vérifier si la quantité en stock est suffisante
            if (quantite <= quantiteEnStock) {
                int nouvelleQuantite = quantiteEnStock - quantite;
                if (nouvelleQuantite >= 0) {
                    // Mettre à jour la quantité en stock du médicament
                    medicament.setQuantiteEnStock(nouvelleQuantite);
                    // Ajouter les informations du médicament à la confirmation
                    confirmationMessage.append("- ").append(medicament.getNom()).append(" (").append(quantite).append(" unité(s))\n");
                } else {
                    throw new ExeptionRuptureDeStock("Rupture de stock pour le médicament " + medicament.getNom());
                }
            } else {
                throw new ExeptionRuptureDeStock("Stock insuffisant pour le médicament " + medicament.getNom());
            }
        }
    }

    // Retourner le message de confirmation
    return confirmationMessage.toString();
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

    // Autres méthodes...
}