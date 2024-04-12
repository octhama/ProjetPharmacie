package pharmacie.entites;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Preparation implements IPreparation {
    private List<Medicament> medicaments;
    private LocalDate datePreparation;

    // Constructeur privé
    Preparation(Builder builder) {
        this.medicaments = builder.medicaments;
        this.datePreparation = builder.datePreparation;
    }

    // Méthodes getters pour les attributs

    @Override
    public List<Medicament> getMedicaments() {
        return medicaments;
    }

    @Override
    public LocalDate getDatePreparation() {
        return datePreparation;
    }

    // Implémentation de la méthode diviserMedicaments de l'interface
    @Override
    public void diviserMedicaments(List<Medicament> medicaments) {
        for (Medicament medicament : medicaments) {
            if (medicament.getQuantiteEnStock() > 1) {
                int quantiteDivisee = medicament.getQuantiteEnStock() / 2;
                medicament.removeFromStock(quantiteDivisee);
                Medicament moitieMedicament = new Medicament(medicament.getNom(), medicament.getPrix(), quantiteDivisee);
                medicaments.add(moitieMedicament);
            }
        }
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean getDateDisponibilite() {
        return false;
    }

    // Autres méthodes et attributs de la classe
}

