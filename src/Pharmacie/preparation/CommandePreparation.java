package Pharmacie.preparation;

import java.time.LocalDate;

public class CommandePreparation {
    private Preparation preparation;
    private LocalDate dateCommande;

    public CommandePreparation(Preparation preparation, LocalDate dateCommande) {
        this.preparation = preparation;
        this.dateCommande = dateCommande;
    }

    // Getters and setters
    public Preparation getPreparation() {
        return preparation;
    }
    public void setPreparation(Preparation preparation) {
        this.preparation = preparation;
    }
    public LocalDate getDateCommande() {
        return dateCommande;
    }
    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }
    @Override
    public String toString() {
        return "CommandePreparation{" +
                "preparation=" + preparation +
                ", dateCommande=" + dateCommande +
                '}';
    }
}

