package pharmacie.entites;

import pharmacie.generiques.MedicamentGenerique;
import pharmacie.nongeneriques.MedicamentNonGenerique;
import pharmacie.personnes.Medecin;
import pharmacie.personnes.Patient;
import pharmacie.entites.IPreparation;
import pharmacie.entites.Preparation;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Pharmacie {
    private final List<Medicament> medicaments;
    private final List<MedicamentGenerique> medicamentsGeneriques;
    private final List<MedicamentNonGenerique> medicamentsNonGeneriques;
    private final List<Patient> patients;
    private final List<Medecin> medecins;
    private final List<Preparation> preparations;
    private final ArrayList<Object> ordonnance;
    private ArrayList<Object> demandes;

    public Pharmacie() {
        this.medicaments = new ArrayList<>();
        this.medicamentsGeneriques = new ArrayList<>();
        this.medicamentsNonGeneriques = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.medecins = new ArrayList<>();
        this.ordonnance = new ArrayList<>();
        this.preparations = new ArrayList<>();
        this.demandes = new ArrayList<>();
        initialiserMedicaments(); // Initialisation des médicaments
    }

    // Méthode pour vérifier si la pharmacie est ouverte
    private boolean estOuverte() {
        LocalDate dateActuelle = LocalDate.now();
        DayOfWeek jourActuel = dateActuelle.getDayOfWeek();
        return jourActuel != DayOfWeek.SUNDAY; // La pharmacie est fermée le dimanche
    }

    // Méthode pour ajouter des médicaments à la pharmacie
    public void ajouterMedicament(Medicament medicament) {
        if (estOuverte()) {
            medicaments.add(medicament);
        } else {
            System.out.println("La pharmacie est fermée le dimanche.");
        }
    }

    // Méthode pour obtenir les préparations disponibles
    public List<Preparation> getPreparationsDisponibles() {
        // Logique pour obtenir les préparations disponibles
        return List.of();
    }

    // Méthode pour obtenir les médicaments en stock
    public List<Medicament> getMedicamentsEnStock() {
        // Logique pour obtenir les médicaments en stock
        return List.of();
    }

    private void initialiserPreparations() {
        IPreparation.Builder<Preparation> builderPreparation = new IPreparation.Builder() {
            @Override
            protected IPreparation.Builder self() {
                return null;
            }

            @Override
            public Preparation build() {
                return null;
            }
        };

        Preparation preparation1 = builderPreparation
                .lesMedicaments(medicaments)
                .laDatePreparation(LocalDate.now())
                .build();
        Preparation preparation2 = builderPreparation
                .lesMedicaments(medicaments)
                .laDatePreparation(LocalDate.now())
                .build();
        Preparation preparation3 = builderPreparation
                .lesMedicaments(medicaments)
                .laDatePreparation(LocalDate.now())
                .build();
        Preparation preparation4 = builderPreparation
                .lesMedicaments(medicaments)
                .laDatePreparation(LocalDate.now())
                .build();
        Preparation preparation5 = builderPreparation
                .lesMedicaments(medicaments)
                .laDatePreparation(LocalDate.now())
                .build();
        Preparation preparation6 = builderPreparation
                .lesMedicaments(medicaments)
                .laDatePreparation(LocalDate.now())
                .build();
        Preparation preparation7 = builderPreparation
                .lesMedicaments(medicaments)
                .laDatePreparation(LocalDate.now())
                .build();
        Preparation preparation8 = builderPreparation
                .lesMedicaments(medicaments)
                .laDatePreparation(LocalDate.now())
                .build();
        Preparation preparation9 = builderPreparation
                .lesMedicaments(medicaments)
                .laDatePreparation(LocalDate.now())
                .build();
        Preparation preparation10 = builderPreparation
                .lesMedicaments(medicaments)
                .laDatePreparation(LocalDate.now())
                .build();
        preparations.add(preparation1);
        preparations.add(preparation2);
        preparations.add(preparation3);
        preparations.add(preparation4);
        preparations.add(preparation5);
        preparations.add(preparation6);
        preparations.add(preparation7);
        preparations.add(preparation8);
        preparations.add(preparation9);
        preparations.add(preparation10);
    }


    private void initialiserMedicaments() {
        Medicament.Builder builderMedicament = new Medicament.Builder() {
            @Override
            public Medicament build() {
                return null;
            }
        };
        // Builder pour créer des médicaments non génériques
        Medicament.Builder<MedicamentNonGenerique> builderNonGenerique = new MedicamentNonGenerique.Builder();
        // Builder pour créer des médicaments génériques
        Medicament.Builder<MedicamentGenerique> builderGenerique = new MedicamentGenerique.Builder();

        MedicamentNonGenerique Paracetamol = builderNonGenerique
                .leNom("Paracetamol")
                .lePrix(1.0)
                .laQuantiteEnStock(10)
                .build();
        MedicamentNonGenerique Ibuprofene = builderNonGenerique
                .leNom("Ibuprofene")
                .lePrix(2.0)
                .laQuantiteEnStock(20)
                .build();
        MedicamentNonGenerique Aspirine = builderNonGenerique
                .leNom("Aspirine")
                .lePrix(3.0)
                .laQuantiteEnStock(30)
                .build();
        MedicamentNonGenerique Doliprane = builderNonGenerique
                .leNom("Doliprane")
                .lePrix(4.0)
                .laQuantiteEnStock(40)
                .build();
        MedicamentNonGenerique Efferalgan = builderNonGenerique
                .leNom("Efferalgan")
                .lePrix(5.0)
                .laQuantiteEnStock(50)
                .build();
        MedicamentNonGenerique Advil = builderNonGenerique
                .leNom("Advil")
                .lePrix(6.0)
                .laQuantiteEnStock(60)
                .build();
        MedicamentNonGenerique Motrin = builderNonGenerique
                .leNom("Motrin")
                .lePrix(7.0)
                .laQuantiteEnStock(70)
                .build();
        MedicamentNonGenerique Aleve = builderNonGenerique
                .leNom("Aleve")
                .lePrix(8.0)
                .laQuantiteEnStock(80)
                .build();
        MedicamentNonGenerique Celebrex = builderNonGenerique
                .leNom("Celebrex")
                .lePrix(9.0)
                .laQuantiteEnStock(90)
                .build();
        MedicamentNonGenerique Vioxx = builderNonGenerique
                .leNom("Vioxx")
                .lePrix(10.0)
                .laQuantiteEnStock(100)
                .build();
        MedicamentNonGenerique Arthrotec = builderNonGenerique
                .leNom("Arthrotec")
                .lePrix(11.0)
                .laQuantiteEnStock(110)
                .build();
        MedicamentNonGenerique Bextra = builderNonGenerique
                .leNom("Bextra")
                .lePrix(12.0)
                .laQuantiteEnStock(120)
                .build();
        MedicamentNonGenerique Voltaren = builderNonGenerique
                .leNom("Voltaren")
                .lePrix(13.0)
                .laQuantiteEnStock(130)
                .build();
        MedicamentNonGenerique Meloxicam = builderNonGenerique
                .leNom("Meloxicam")
                .lePrix(14.0)
                .laQuantiteEnStock(140)
                .build();
        MedicamentNonGenerique Celecoxib = builderNonGenerique
                .leNom("Celecoxib")
                .lePrix(15.0)
                .laQuantiteEnStock(150)
                .build();
        MedicamentNonGenerique Naproxen = builderNonGenerique
                .leNom("Naproxen")
                .lePrix(16.0)
                .laQuantiteEnStock(160)
                .build();
        MedicamentNonGenerique Diclofenac = builderNonGenerique
                .leNom("Diclofenac")
                .lePrix(17.0)
                .laQuantiteEnStock(170)
                .build();

        // Ajouter d'autres médicaments selon les besoins
        medicamentsNonGeneriques.add(Paracetamol);
        medicamentsNonGeneriques.add(Ibuprofene);
        medicamentsNonGeneriques.add(Aspirine);
        medicamentsNonGeneriques.add(Doliprane);
        medicamentsNonGeneriques.add(Efferalgan);
        medicamentsNonGeneriques.add(Advil);
        medicamentsNonGeneriques.add(Motrin);
        medicamentsNonGeneriques.add(Aleve);
        medicamentsNonGeneriques.add(Celebrex);
        medicamentsNonGeneriques.add(Vioxx);
        medicamentsNonGeneriques.add(Arthrotec);
        medicamentsNonGeneriques.add(Bextra);
        medicamentsNonGeneriques.add(Voltaren);
        medicamentsNonGeneriques.add(Meloxicam);
        medicamentsNonGeneriques.add(Celecoxib);
        medicamentsNonGeneriques.add(Naproxen);
        medicamentsNonGeneriques.add(Diclofenac);

        medicaments.add(Paracetamol);
        medicaments.add(Ibuprofene);
        medicaments.add(Aspirine);
        medicaments.add(Doliprane);
        medicaments.add(Efferalgan);
        medicaments.add(Advil);
        medicaments.add(Motrin);
        medicaments.add(Aleve);
        medicaments.add(Celebrex);
        medicaments.add(Vioxx);
        medicaments.add(Arthrotec);
        medicaments.add(Bextra);
        medicaments.add(Voltaren);
        medicaments.add(Meloxicam);
        medicaments.add(Celecoxib);
        medicaments.add(Naproxen);
        medicaments.add(Diclofenac);

        MedicamentGenerique medicament1 = builderGenerique
                .leNom("Médicament générique 1")
                .lePrix(1.0)
                .laQuantiteEnStock(10)
                .build();
        MedicamentGenerique medicament2 = builderGenerique
                .leNom("Médicament générique 2")
                .lePrix(2.0)
                .laQuantiteEnStock(20)
                .build();
        MedicamentGenerique medicament3 = builderGenerique
                .leNom("Médicament générique 3")
                .lePrix(3.0)
                .laQuantiteEnStock(30)
                .build();
        MedicamentGenerique medicament4 = builderGenerique
                .leNom("Médicament générique 4")
                .lePrix(4.0)
                .laQuantiteEnStock(40)
                .build();
        MedicamentGenerique medicament5 = builderGenerique
                .leNom("Médicament générique 5")
                .lePrix(5.0)
                .laQuantiteEnStock(50)
                .build();
        MedicamentGenerique medicament6 = builderGenerique
                .leNom("Médicament générique 6")
                .lePrix(6.0)
                .laQuantiteEnStock(60)
                .build();
        MedicamentGenerique medicament7 = builderGenerique
                .leNom("Médicament générique 7")
                .lePrix(7.0)
                .laQuantiteEnStock(70)
                .build();
        MedicamentGenerique medicament8 = builderGenerique
                .leNom("Médicament générique 8")
                .lePrix(8.0)
                .laQuantiteEnStock(80)
                .build();
        MedicamentGenerique medicament9 = builderGenerique
                .leNom("Médicament générique 9")
                .lePrix(9.0)
                .laQuantiteEnStock(90)
                .build();
        MedicamentGenerique medicament10 = builderGenerique
                .leNom("Médicament générique 10")
                .lePrix(10.0)
                .laQuantiteEnStock(100)
                .build();
        MedicamentGenerique medicament11 = builderGenerique
                .leNom("Médicament générique 11")
                .lePrix(11.0)
                .laQuantiteEnStock(110)
                .build();
        MedicamentGenerique medicament12 = builderGenerique
                .leNom("Médicament générique 12")
                .lePrix(12.0)
                .laQuantiteEnStock(120)
                .build();

        // Ajouter d'autres médicaments non génériques selon les besoins
        medicamentsGeneriques.add(medicament1);
        medicamentsGeneriques.add(medicament2);
        medicamentsGeneriques.add(medicament3);
        medicamentsGeneriques.add(medicament4);
        medicamentsGeneriques.add(medicament5);
        medicamentsGeneriques.add(medicament6);
        medicamentsGeneriques.add(medicament7);
        medicamentsGeneriques.add(medicament8);
        medicamentsGeneriques.add(medicament9);
        medicamentsGeneriques.add(medicament10);
        medicamentsGeneriques.add(medicament11);
        medicamentsGeneriques.add(medicament12);

        medicaments.add(medicament1);
        medicaments.add(medicament2);
        medicaments.add(medicament3);
        medicaments.add(medicament4);
        medicaments.add(medicament5);
        medicaments.add(medicament6);
        medicaments.add(medicament7);
        medicaments.add(medicament8);
        medicaments.add(medicament9);
        medicaments.add(medicament10);
        medicaments.add(medicament11);
        medicaments.add(medicament12);
    }

    public void ajouterMedicamentGenerique(MedicamentGenerique medicamentGenerique) {
        medicamentsGeneriques.add(medicamentGenerique);
    }

    public void ajouterMedicamentNonGenerique(MedicamentNonGenerique medicamentNonGenerique) {
        medicamentsNonGeneriques.add(medicamentNonGenerique);
    }

    public List<Medicament> getMedicaments() {
        return medicaments;
    }

    public List<MedicamentGenerique> getMedicamentsGeneriques() {
        return medicamentsGeneriques;
    }

    public List<MedicamentNonGenerique> getMedicamentsNonGeneriques() {
        return medicamentsNonGeneriques;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public List<Preparation> getPreparations() {
        return preparations;
    }

    public void ajouterPreparation(Preparation preparation) {
        preparations.add(preparation);
    }

    public Medicament chercherMedicament(String nomMedicament) {
        // Logique pour chercher un médicament
        return medicaments.get(0);
    }

    public void commanderPreparation(Medicament medicament, int quantite) {
        // Logique pour commander une préparation
        // Ajouter la préparation à la liste des préparations
        preparations.add(new Preparation(medicament, quantite));
    }

    public void afficherLaListeDesPreparations() {
        // Logique pour afficher la liste des préparations
        for (Preparation preparation : preparations) {
            System.out.println(preparation.getDescription());
        }
    }

    public void afficherLaListeDesMedicaments() {
        // Logique pour afficher la liste des médicaments
        for (Medicament medicament : medicaments) {
            System.out.println(medicament.getDescription());
        }
    }

    public Preparation commanderPreparation() {
        // Logique pour commander une préparation
        // Ajouter la préparation à la liste des préparations
        preparations.add(new Preparation(medicaments.get(0), 1));
        return preparations.get(0);
    }

    public Preparation commanderPreparation(Preparation preparation) {
        // Logique pour commander une préparation
        // Ajouter la préparation à la liste des préparations
        preparations.add(preparation);
            return preparation;
    }

    public void modifierPreparation(Preparation preparation) {
        // Logique pour modifier une préparation
        // Modifier la préparation dans la liste des préparations
        preparations.set(0, preparation);
    }

    public void modifierMedicament(Medicament medicament) {
        // Logique pour modifier un médicament
        // Modifier le médicament dans la liste des médicaments
        medicaments.set(0, medicament);
    }

    public void supprimerMedicament(String medicament) {
        // Logique pour supprimer un médicament
        // Supprimer le médicament de la liste des médicaments
        medicaments.remove(medicament);
    }

    public void modifierMedicament(String nomMedicament, String nouveauNomMedicament) {
        // Logique pour modifier un médicament
        // Modifier le médicament dans la liste des médicaments
        medicaments.get(0).setNom(nouveauNomMedicament);
    }

    public boolean existeMedicament(String nomMedicament) {
        return false;
    }

    public void supprimerPreparation(String nomPreparation) {
        // Logique pour supprimer une préparation
        // Supprimer la préparation de la liste des préparations
        preparations.remove(0);
    }

    public Medicament rechercherMedicament(String nomMedicament) {
        return null;
    }

    public void enregistrerDemandeMedicamentGen(String nomMedicamentGen) {
        // Logique pour enregistrer une demande de médicament générique
        // Ajouter la demande à la liste des demandes de médicaments génériques
        demandes = new ArrayList<>();
        demandes.add(nomMedicamentGen);

    }

    public void modifierPatient(String nomPatient) {
        // Logique pour modifier un patient
        // Modifier le patient dans la liste des patients
        patients.get(0).setNom(nomPatient);
    }

    public void ajouterPatient(Patient patient) {
        // Logique pour ajouter un patient
        // Ajouter le patient à la liste des patients
        patients.add(patient);
    }

    public void supprimerMedecin(String nomMedecin) {
        // Logique pour supprimer un médecin
        // Supprimer le médecin de la liste des médecins
        medecins.remove(0);
    }

    public void modifierMedecin(String nomMedecin) {
        // Logique pour modifier un médecin
        // Modifier le médecin dans la liste des médecins
        medecins.get(0).setNom(nomMedecin);
    }

    public void ajouterMedecin(Medecin medecin) {
        // Logique pour ajouter un médecin
        // Ajouter le médecin à la liste des médecins
        medecins.add(medecin);
    }

    public void supprimerPatient(String nomPatient) {
        // Logique pour supprimer un patient
        // Supprimer le patient de la liste des patients
        patients.remove(0);
    }
}


