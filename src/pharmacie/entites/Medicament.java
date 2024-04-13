package pharmacie.entites;

public class Medicament extends IPreparation.Builder<Medicament> {
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

    public void supprimerDuStock(int quantiteDivisee) {
        this.quantiteEnStock -= quantiteDivisee;
    }

    @Override
    protected Medicament self() {
        return null;
    }

    @Override
    public Preparation build() {
        return null;
    }

    public String getDescription() {
        // TODO: Implémenter la méthode
        return "";
    }

    // Builder statique pour créer des instances de sous-classes de Medicament
    public static abstract class Builder<T extends Medicament> {
        protected String nom;
        protected double prix;
        protected int quantiteEnStock;

        public Builder<T> leNom(String nom) {
            this.nom = nom;
            return this;
        }

        public Builder<T> lePrix(double prix) {
            this.prix = prix;
            return this;
        }

        public Builder<T> laQuantiteEnStock(int quantiteEnStock) {
            this.quantiteEnStock = quantiteEnStock;
            return this;
        }

        public abstract T build();
    }
}

