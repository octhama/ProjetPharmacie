package pharmacie.entites;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Preparation extends IPreparation.Builder<Preparation> implements IPreparation {
    private List<Medicament> medicaments;
    private LocalDate datePreparation;

    // Constructeur privé
    Preparation(Builder builder) {
        this.medicaments = builder.medicaments;
        this.datePreparation = builder.datePreparation;
    }

    public Preparation(Medicament medicament, int quantite) {
        this.medicaments = new ArrayList<>();
        this.medicaments.add(medicament);
        this.datePreparation = LocalDate.now();
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
                medicament.supprimerDuStock(quantiteDivisee);
                Medicament moitieMedicament = new Medicament(medicament.getNom(), medicament.getPrix(), quantiteDivisee);
                medicaments.add(moitieMedicament);
            }
        }
    }

    // Méthodes getters pour les attributs

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean getDateDisponibilite() {
        return false;
    }

    @Override
    public Builder<Medicament> laDatePreparation(LocalDate now) {
        return null;
    }

    @Override
    protected Preparation self() {
        return null;
    }

    @Override
    public Preparation build() {
        return null;
    }
    // Autres méthodes et attributs de la classe
}

