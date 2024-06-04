package pharmacie;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.swing.JCheckBox;
import enums.ETypeMedicament;
import exceptions.ExeptionRuptureDeStock;
import ui.UiGui;

import static io.EcritureRegistrePreparationCsv.medicaments;
/**
 * Classe pour les médicaments.
 * On peut ajouter un médicament, retirer un médicament, acheter un médicament, etc.
 * @see Medicament#acheter(int)
 * @see Medicament#setMedicaments(Medicament[])
 * @see Medicament#setQuantiteEnStock(int)
 * @see Medicament#getMedicaments()
 * @see Medicament#setMedicaments(List)
 */
public class Medicament {
    private String nom;
    private double prix;
    private ETypeMedicament type;
    private boolean generique;
    private int quantiteEnStock;
    private boolean commandeA50Pourcent;

    public Medicament(String nom, double prix2, ETypeMedicament eTypeMedicament, boolean generique2, int quantiteStock, boolean commandeA50Pourcent, boolean prescriptionRequise) {
        this.nom = nom;
        this.prix = prix2;
        this.type = ETypeMedicament.valueOf(eTypeMedicament.toString());
        this.generique = generique2;
        this.quantiteEnStock = quantiteStock;
        this.commandeA50Pourcent = commandeA50Pourcent;
        // Indique si une ordonnance est requise pour acheter le médicament
    }

    public Medicament(boolean commandeA50Pourcent) {
        //TODO Auto-generated constructor stub
        this.commandeA50Pourcent = commandeA50Pourcent;
    }

    public Medicament(String nom2, String nom3, String nom4, String nom5, String nom6, boolean commandeA50Pourcent) {
        //TODO Auto-generated constructor stub
        this.nom = nom2;
        this.prix = Double.parseDouble(nom3);
        this.type = ETypeMedicament.valueOf(nom4);
        this.generique = Boolean.parseBoolean(nom5);
        this.quantiteEnStock = Integer.parseInt(nom6);
        this.commandeA50Pourcent = commandeA50Pourcent;
    }

    public Medicament(String nom, int quantitePrescrite, boolean commandeA50Pourcent) {
        //TODO Auto-generated constructor stub
        this.nom = nom;
        this.quantiteEnStock = quantitePrescrite;
        this.commandeA50Pourcent = commandeA50Pourcent;
        this.prix = 0;
        this.type = null;
        this.generique = false;
    }

    public Medicament(String medicamentString, boolean commandeA50Pourcent) {
        this.commandeA50Pourcent = commandeA50Pourcent;
        //TODO Auto-generated constructor stub
        String[] parts = medicamentString.split("\\(");
        this.nom = parts[0];
        this.quantiteEnStock = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
    }

    public Medicament(String nom, int quantitePrescrite) {
        //TODO Auto-generated constructor stub
        this.nom = nom;
        this.quantiteEnStock = quantitePrescrite;
    }

    public Medicament(String medicamentString) {
        // Trouver l'index du dernier espace dans la chaîne
        int lastSpaceIndex = medicamentString.lastIndexOf(" ");

        // Extraire le nom du médicament (tout avant le dernier espace)
        this.nom = medicamentString.substring(0, lastSpaceIndex);

        // Extraire la quantité en stock (tout après le dernier espace)
        this.quantiteEnStock = Integer.parseInt(medicamentString.substring(lastSpaceIndex + 1, medicamentString.length() - 2));
    }

    public Medicament(String nom, double prix, ETypeMedicament type, boolean generique, int quantiteStock) {
        this.nom = nom;
        this.prix = prix;
        this.type = type;
        this.generique = generique;
        this.quantiteEnStock = quantiteStock;
    }

    public Medicament(String nom, double prix, int quantite, ETypeMedicament type) {
        this.nom = nom;
        this.prix = prix;
        this.quantiteEnStock = quantite;
        this.type = type;
    }

    public Medicament(String nom, int prix, ETypeMedicament type, boolean generique, Integer quantiteStock) {
        //TODO Auto-generated constructor stub
        this.nom = nom;
        this.prix = prix;
        this.type = type;
        this.generique = generique;
        this.quantiteEnStock = quantiteStock;
    }

    public Medicament(String med2, double v, ETypeMedicament o, Boolean o1, int i) {
        //TODO Auto-generated constructor stub
        this.nom = med2;
        this.prix = v;
        this.type = o;
        this.generique = o1;
        this.quantiteEnStock = i;
    }

    public Medicament(String med3, Double o, Object o1, Object o2, int i) {
        //TODO Auto-generated constructor stub
        this.nom = med3;
        this.prix = o;
        this.type = (ETypeMedicament) o1;
        this.generique = (Boolean) o2;
        this.quantiteEnStock = i;
    }

    public Medicament(String med4, double v, ETypeMedicament o, Boolean o1, Integer o2) {
        //TODO Auto-generated constructor stub
        this.nom = med4;
        this.prix = v;
        this.type = o;
        this.generique = o1;
        this.quantiteEnStock = o2;
    }

    // Constructeur pour les médicaments prescrits sur une ordonnance (Test AfficherlistemedicamentssurordonnanceTest)
    public Medicament(String med1, String labo1, String mol1, String ref1, boolean b) {
        //TODO Auto-generated constructor stub
        this.nom = med1;
        this.prix = 0;
        this.type = null;
        this.generique = false;
        this.quantiteEnStock = 0;
        this.commandeA50Pourcent = b;
    }

    public static void supprimerMedicament(List<Medicament> liste, Medicament med) {
        // Supprimer le médicament de la liste
        liste.remove(med);
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public ETypeMedicament getType() {
        return type;
    }

    public boolean isGenerique() {
        return generique;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void acheter(int quantite) throws ExeptionRuptureDeStock {
        // Augmenter la quantité en stock du médicament
        this.quantiteEnStock += quantite;
    }

    public void setQuantiteEnStock(int quantite) {
        this.quantiteEnStock = quantite;
    }

    public boolean estCommandeA50Pourcent() {
        return commandeA50Pourcent;
    }

    // Méthode pour vérifier si les médicaments sélectionnés correspondent à ceux prescrits
    public static boolean medicamentsCorrespondants(List<String> medicamentsPrescrits) {
        for (Map.Entry<Medicament, JCheckBox> entry : UiGui.medicamentCheckBoxMap.entrySet()) {
            Medicament medicament = entry.getKey();
            JCheckBox checkBox = entry.getValue();
            if (checkBox.isSelected()) {
                boolean correspondant = false;
                for (String medicamentPrescrit : medicamentsPrescrits) {
                    // Vérifier si le nom du médicament sélectionné correspond à un médicament prescrit
                    if (medicamentPrescrit.equalsIgnoreCase(medicament.getNom())) {
                        correspondant = true;
                        break;
                    }
                }
                // Si aucun médicament prescrit ne correspond, retourner false
                if (!correspondant) {
                    return false;
                }
            }
        }
        // Si tous les médicaments sélectionnés correspondent à ceux prescrits, retourner true
        return true;
    }

    // Méthode pour ajouter un médicament à la liste des médicaments
    public void setMedicaments(List<Medicament> medicaments) {
        Ordonnance.setMedicaments(medicaments);
    }

    public Medicament[] getMedicaments() {
        return medicaments.toArray(new Medicament[0]);
    }

    public void setMedicaments(Medicament[] medicaments) {
        Ordonnance.setMedicaments(List.of(medicaments));
    }
}
