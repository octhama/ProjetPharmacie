package Pharmacie.facturation;

import Pharmacie.preparation.Preparation;

public class Facture {
    private String referenceFacture;
    private double montantTotal;

    public Facture(String referenceFacture, double montantTotal) {
        this.referenceFacture = referenceFacture;
        this.montantTotal = montantTotal;
    }

    public Facture(Preparation preparation) {
        this.referenceFacture = preparation.getNumero();
        this.montantTotal = preparation.calculerMontantTotal();
    }

    // Getters and setters
    public String getReferenceFacture() {
        return referenceFacture;
    }

    public void setReferenceFacture(String referenceFacture) {
        this.referenceFacture = referenceFacture;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "referenceFacture='" + referenceFacture + '\'' +
                ", montantTotal=" + montantTotal +
                '}';
    }

    public void setPaiement(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getNumero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public <LigneFacture> LigneFacture[] getLignesFacture() {
        return null;
    }

    public void setEnvoyee(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setArchivee(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setImprimee(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEnvoyeeParEmail(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEnvoyeeParFax(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEnvoyeeParCourrierPostal(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEnvoyeeParMessagerie(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

