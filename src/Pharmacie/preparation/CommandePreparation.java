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
}

