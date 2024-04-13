package pharmacie.entites;

import java.time.LocalDate;
import java.util.List;

public interface IPreparation {
    List<Medicament> getMedicaments();

    LocalDate getDatePreparation();

    // Implémentation de la méthode diviserMedicaments de l'interface
    void diviserMedicaments(List<Medicament> medicaments);

    String getDescription();

    boolean getDateDisponibilite();

    IPreparation.Builder<Preparation> lesMedicaments(List<Medicament> medicaments);

    Preparation.Builder<Medicament> laDatePreparation(LocalDate now);

    // Builder pour créer des instances de Preparation
    public static abstract class Builder<T extends Builder<T>> {
        List<Medicament> medicaments;
        LocalDate datePreparation;

        public T withMedicaments(List<Medicament> medicaments) {
            this.medicaments = medicaments;
            return self();
        }

        public T withDatePreparation(LocalDate datePreparation) {
            this.datePreparation = datePreparation;
            return self();
        }

        protected abstract T self();

        public abstract Preparation build();

        public T lesMedicaments(List<Medicament> medicaments) {
            return null;
        }
    }

    // Classe Builder concret pour Preparation
    public static class PreparationBuilder extends Builder<Preparation.PreparationBuilder> {
        @Override
        protected PreparationBuilder self() {
            return this;
        }

        @Override
        public Preparation build() {
            return new Preparation(this);
        }
    }
}
