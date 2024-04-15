package pharmacie.entites;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Preparation extends IPreparation.Builder<Preparation> implements IPreparation {
    private List<Medicament> medicaments;
    private LocalDate datePreparation;

    // Constructeur privé
    public Preparation(Builder builder) {
        this.medicaments = builder.medicaments;
        this.datePreparation = builder.datePreparation;
    }

    public Preparation(Medicament medicament, int quantite) {
        this.medicaments = new ArrayList<>();
        this.medicaments.add(medicament);
        this.datePreparation = LocalDate.now();
    }

    public Preparation(String nomPreparation, String ingredients, String quantite, String prix, String description) {
        // TODO Auto-generated constructor stub
        this.medicaments = new ArrayList<>();
        this.datePreparation = LocalDate.now();
    }

    // Constructeur public
    public Preparation(List<Medicament> medicaments, LocalDate datePreparation) {
        this.medicaments = medicaments;
        this.datePreparation = datePreparation;
    }

    public Preparation(String text) {
        // TODO Auto-generated constructor stub

    }

    // Méthodes setters pour les attributs
    public void setMedicaments(List<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    public void setDatePreparation(LocalDate datePreparation) {
        this.datePreparation = datePreparation;
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
                Medicament moitieMedicament = new Medicament(Arrays.toString(medicament.getNom()), medicament.getPrix(), quantiteDivisee);
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

    public Medicament getMedicament() {
        // TODO Auto-generated method stub
        return null;
    }

    public Medicament getPatient() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getQuantite() {
        // TODO Auto-generated method stub
        return 0;
    }
        @Override
    public String toString() {
        return "Preparation [medicaments=" + medicaments + ", datePreparation=" + datePreparation + "]";
    }

        public Medicament getMedecin() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getMedecin'");
        }

}

