package pharmacie;

import java.time.LocalDate;

public class Commande {
    private String idUnique;
    private final int numeroCommande;
    private String commentaires;
    private int quantiteTotalCommande;
    private LocalDate dateCommande;

    public Commande(String idUnique, int numeroCommande, String commentaires, int quantiteTotalCommande, String date) {
        this.idUnique = idUnique;
        this.numeroCommande = numeroCommande;
        this.commentaires = commentaires;
        this.quantiteTotalCommande = quantiteTotalCommande;
        this.dateCommande = LocalDate.parse(date);
    }

    public String getIdUnique() {
        return idUnique;
    }

    public int getNumeroCommande() {
        return numeroCommande;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public int getQuantiteTotalCommande() {
        return quantiteTotalCommande;
    }

    public LocalDate getDate() {
        return dateCommande;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "idUnique='" + idUnique + '\'' +
                ", numeroCommande=" + numeroCommande +
                ", commentaires='" + commentaires + '\'' +
                ", quantiteTotalCommande=" + quantiteTotalCommande +
                ", dateCommande=" + dateCommande +
                '}';
    }

}

