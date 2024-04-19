package pharmacie;

import java.util.List;

public  class Preparation {
    private List<Medicament> medicaments;
    private double coutTotal;

    public void setMedicaments(Medicament[] medicaments) {
        this.medicaments = List.of(medicaments);
    }

    public double calculerCoutTotal() {
        double coutTotal = 0;
        for (Medicament medicament : medicaments) {
            coutTotal += medicament.getPrix();
        }
        setCoutTotal(coutTotal);
        return coutTotal;
    }

    public void setCoutTotal(Object o) {
        this.coutTotal = (double) o;
    }
}
