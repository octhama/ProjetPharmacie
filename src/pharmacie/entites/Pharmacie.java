package pharmacie.entites;

import pharmacie.generiques.MedicamentGenerique;
import pharmacie.personnes.Medecin;
import pharmacie.personnes.Patient;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Pharmacie {
    private List<Medicament> medicaments;
    private List<Preparation> preparations;

    public Pharmacie() {
        this.medicaments = new ArrayList<>();
        this.preparations = new ArrayList<>();
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

    private void initialiserMedicaments() {
        Medicament.Builder<MedicamentGenerique> builder = new MedicamentGenerique.Builder();
        MedicamentGenerique medicament1 = builder
                .withNom("Paracétamol")
                .withPrix(5.0)
                .withQuantiteEnStock(100)
                .build();
        MedicamentGenerique medicament2 = builder
                .withNom("Ibuprofène")
                .withPrix(6.0)
                .withQuantiteEnStock(150)
                .build();
        MedicamentGenerique medicament3 = builder
                .withNom("Aspirine")
                .withPrix(7.0)
                .withQuantiteEnStock(200)
                .build();
        MedicamentGenerique medicament4 = builder
                .withNom("Doliprane")
                .withPrix(8.0)
                .withQuantiteEnStock(250)
                .build();
        MedicamentGenerique medicament5 = builder
                .withNom("Efferalgan")
                .withPrix(9.0)
                .withQuantiteEnStock(300)
                .build();

        // Ajouter d'autres médicaments selon les besoins

        medicaments.add(medicament1);
        medicaments.add(medicament2);
        medicaments.add(medicament3);
        medicaments.add(medicament4);
        medicaments.add(medicament5);
        // Ajouter les autres médicaments à la liste
    }

    public List<Medicament> getMedicaments() {
        return medicaments;
    }

    public List<Medecin> getMedecins() {
        // Logique pour obtenir les médecins
        return List.of();
    }

    public List<Patient> getPatients() {
        return List.of();
    }

    // Autres méthodes de la classe
}
