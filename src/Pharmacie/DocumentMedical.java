package Pharmacie;

public class DocumentMedical {
    private Medecin medecin;
    private Patient patient;
    private Pharmacien pharmacien;
    private Medicament  medicament;
    private MedicamentGeneric medicamentGeneric;
    private MedicamentNonGeneric medicamentNonGeneric;
    private MedicamentSansOrdonnance medicamentSansOrdonnance;
    private MedicamentAvecOrdonnance medicamentAvecOrdonnance;
    private Pharmacie pharmacie;
    private String nomHopital;
    private String emailHopital;
    private String adresseHopital;
    private String dateConsulation;
    private String datePrescription;
    private String dateFacturation;
    private double prixConsulation;
    private String numSecu;
    private String mutuelle;
    private String allergene;
    private String antecedent;

    public DocumentMedical(Medecin medecin, Patient patient, Pharmacien pharmacien, Medicament medicament, MedicamentGeneric medicamentGeneric, MedicamentNonGeneric medicamentNonGeneric, MedicamentSansOrdonnance medicamentSansOrdonnance, MedicamentAvecOrdonnance medicamentAvecOrdonnance, Pharmacie pharmacie, String nomHopital, String emailHopital, String adresseHopital, String dateConsulation, String datePrescription, String dateFacturation, double prixConsulation, String numSecu, String mutuelle, String allergene, String antecedent) {
        this.medecin = medecin;
        this.patient = patient;
        this.pharmacien = pharmacien;
        this.medicament = medicament;
        this.medicamentGeneric = medicamentGeneric;
        this.medicamentNonGeneric = medicamentNonGeneric;
        this.medicamentSansOrdonnance = medicamentSansOrdonnance;
        this.medicamentAvecOrdonnance = medicamentAvecOrdonnance;
        this.pharmacie = pharmacie;
        this.nomHopital = nomHopital;
        this.emailHopital = emailHopital;
        this.adresseHopital = adresseHopital;
        this.dateConsulation = dateConsulation;
        this.datePrescription = datePrescription;
        this.dateFacturation = dateFacturation;
        this.prixConsulation = prixConsulation;
        this.numSecu = numSecu;
        this.mutuelle = mutuelle;
        this.allergene = allergene;
        this.antecedent = antecedent;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Pharmacien getPharmacien() {
        return pharmacien;
    }

    public void setPharmacien(Pharmacien pharmacien) {
        this.pharmacien = pharmacien;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament, String nomMedicament) {
        this.medicament = medicament;
    }

    public MedicamentGeneric getMedicamentGeneric() {
        return medicamentGeneric;
    }

    public void setMedicamentGeneric(MedicamentGeneric medicamentGeneric) {
        this.medicamentGeneric = medicamentGeneric;
    }

    public MedicamentNonGeneric getMedicamentNonGeneric() {
        return medicamentNonGeneric;
    }

    public void setMedicamentNonGeneric(MedicamentNonGeneric medicamentNonGeneric) {
        this.medicamentNonGeneric = medicamentNonGeneric;
    }

    public MedicamentSansOrdonnance getMedicamentSansOrdonnance() {
        return medicamentSansOrdonnance;
    }

    public void setMedicamentSansOrdonnance(MedicamentSansOrdonnance medicamentSansOrdonnance) {
        this.medicamentSansOrdonnance = medicamentSansOrdonnance;
    }

    public MedicamentAvecOrdonnance getMedicamentAvecOrdonnance() {
        return medicamentAvecOrdonnance;
    }

    public void setMedicamentAvecOrdonnance(MedicamentAvecOrdonnance medicamentAvecOrdonnance) {
        this.medicamentAvecOrdonnance = medicamentAvecOrdonnance;
    }

    public Pharmacie getPharmacie() {
        return pharmacie;
    }

    public void setPharmacie(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    public String getNomHopital() {
        return nomHopital;
    }

    public void setNomHopital(String nomHopital) {
        this.nomHopital = nomHopital;
    }

    public String getEmailHopital() {
        return emailHopital;
    }

    public void setEmailHopital(String emailHopital) {
        this.emailHopital = emailHopital;
    }

    public String getAdresseHopital() {
        return adresseHopital;
    }

    public void setAdresseHopital(String adresseHopital) {
        this.adresseHopital = adresseHopital;
    }

    public String getDateConsulation() {
        return dateConsulation;
    }

    public void setDateConsulation(String dateConsulation) {
        this.dateConsulation = dateConsulation;
    }

    public String getDatePrescription() {
        return datePrescription;
    }

    public void setDatePrescription(String datePrescription) {
        this.datePrescription = datePrescription;
    }

    public String getDateFacturation() {
        return dateFacturation;
    }

    public void setDateFacturation(String dateFacturation) {
        this.dateFacturation = dateFacturation;
    }

    public Medicament getMedicament(String nomMedicament) {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public double getPrixConsulation() {
        return prixConsulation;
    }

    public void setPrixConsulation(double prixConsulation) {
        this.prixConsulation = prixConsulation;
    }

    public String getNumSecu() {
        return numSecu;
    }

    public void setNumSecu(String numSecu) {
        this.numSecu = numSecu;
    }

    public String getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(String mutuelle) {
        this.mutuelle = mutuelle;
    }

    public String getAllergene() {
        return allergene;
    }

    public void setAllergene(String allergene) {
        this.allergene = allergene;
    }

    public String getAntecedent() {
        return antecedent;
    }

    public void setAntecedent(String antecedent) {
        this.antecedent = antecedent;
    }

    public void consulterDossierMedical(){
        System.out.println("Consulter le dossier médical du patient");
    }
    public void imprimerDossierMedical(){
        System.out.println("Imprimer le dossier médical du patient");
    }
    public void enregistrerDossierMedical(){
        System.out.println("Enregistrer le dossier médical du patient");
    }
    public void archiverDossierMedical(){
        System.out.println("Archiver le dossier médical du patient");
    }
    public void transfererDossierMedical(){
        System.out.println("Transferer le dossier médical du patient");
    }
    public void validerDossierMedical(){
        System.out.println("Valider le dossier médical du patient");
    }
    public void envoyerDossierMedical(){
        System.out.println("Envoyer le dossier médical du patient");
    }
    public void supprimerDossierMedical(){
        System.out.println("Supprimer le dossier médical du patient");
    }
}

