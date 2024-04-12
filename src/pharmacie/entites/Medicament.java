package pharmacie.entites;

public class Medicament {
    private String nom;
    private double prix;
    private int quantiteEnStock;

    public Medicament(String nom, double prix, int quantiteEnStock) {
        this.nom = nom;
        this.prix = prix;
        this.quantiteEnStock = quantiteEnStock;
    }

    // Getters et setters pour les attributs

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "nom='" + nom + '\'' +
                ", prix=" + prix +
                ", quantiteEnStock=" + quantiteEnStock +
                '}';
    }

    public void removeFromStock(int quantiteDivisee) {
        this.quantiteEnStock -= quantiteDivisee;
    }

    // Builder statique pour créer des instances de sous-classes de Medicament
    public static abstract class Builder<T extends Medicament> {
        protected String nom;
        protected double prix;
        protected int quantiteEnStock;

        public Builder<T> withNom(String nom) {
            this.nom = nom;
            return this;
        }

        public Builder<T> withPrix(double prix) {
            this.prix = prix;
            return this;
        }

        public Builder<T> withQuantiteEnStock(int quantiteEnStock) {
            this.quantiteEnStock = quantiteEnStock;
            return this;
        }

        public abstract T build();
    }
}

