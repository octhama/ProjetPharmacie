package Pharmacie.composants;

import java.util.Date;

public class Commande {

    private int id;
    private Medicament medicament;
    private int quantiteCommandee;
    private Date dateCommande;
    private boolean livree;

    public Commande(int id, Medicament medicament, int quantiteCommandee, Date dateCommande, boolean livree) {
        this.id = id;
        this.medicament = medicament;
        this.quantiteCommandee = quantiteCommandee;
        this.dateCommande = dateCommande;
        this.livree = livree;
    }

    // Getters and setters for all fields

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", medicament=" + medicament +
                ", quantiteCommandee=" + quantiteCommandee +
                ", dateCommande=" + dateCommande +
                ", livree=" + livree +
                '}';
    }

    public void setLivree(boolean b) {
        this.livree = b;
    }

    public int getId() {
        return 0;
    }
}


