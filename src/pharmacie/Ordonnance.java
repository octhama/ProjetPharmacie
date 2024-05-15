package pharmacie;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import static io.EcritureOrdonnancesCsv.ecrireOrdonnanceCsv;
import static io.LectureMedecinCsv.authentifierMedecin;

public class Ordonnance {
    private final LocalDate datePrescription;
    public static List<Medicament> medicaments;

    public Ordonnance(LocalDate datePrescription, List<Medicament> medicaments) {
        this.datePrescription = datePrescription;
        this.medicaments = medicaments;
    }

    public Ordonnance(String referencesDuMedecin, String referencesDuPatient, String dateDePrescription, String listeDesMedicaments) {
        //TODO Auto-generated constructor stub
        this.datePrescription = LocalDate.parse(dateDePrescription);
        String[] parts = listeDesMedicaments.split(",");
        for (String part : parts) {
            String[] subParts = part.split("\\(");
            String nom = subParts[0];
            int quantite = Integer.parseInt(subParts[1].substring(0, subParts[1].length() - 1));
            for (Medicament medicament : medicaments) {
                if (medicament.getNom().equals(nom)) {
                    medicament.setQuantiteEnStock(medicament.getQuantiteEnStock() - quantite);
                }
            }
        }
    }

    public static void enregistrerOrdonnance() {
        // Demander à l'utilisateur de s'authentifier en tant que médecin
        String idMedecin = JOptionPane.showInputDialog(null, "Veuillez saisir votre identifiant médecin :");
        String passwordMedecin = JOptionPane.showInputDialog(null, "Veuillez saisir votre mot de passe médecin :");

        // Vérifier l'authentification
        if (authentifierMedecin(idMedecin, passwordMedecin)) {
            // Authentification réussie, continuer avec l'enregistrement de l'ordonnance

            // Afficher une boîte de dialogue pour saisir le nom du médecin
            String referenceMedecin = JOptionPane.showInputDialog(null, "Veuillez saisir la référence du médecin :");
            // Afficher une boîte de dialogue pour saisir le nom du patient
            String referencePatient = JOptionPane.showInputDialog(null, "Veuillez saisir la référence du patient :");
            // Afficher une boîte de dialogue pour saisir la date de prescription
            String datePrescriptionString = JOptionPane.showInputDialog(null, "Veuillez saisir la date de prescription (format : yyyy-MM-dd) :");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date datePrescription = null;
            try {
                datePrescription = dateFormat.parse(datePrescriptionString);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Format de date invalide. Veuillez saisir une date au format yyyy-MM-dd.");
                return;
            }

            // Afficher une boîte de dialogue pour saisir le ou les médicament(s) prescrit(s)
            String medicamentsString = JOptionPane.showInputDialog(null, "Veuillez saisir le ou les médicament(s) prescrit(s) (séparés par des virgules) :");
            String[] medicamentsArray = medicamentsString.split(",");
            Stack<String> medicaments = new Stack<>();
            for (String medicament : medicamentsArray) {
                medicaments.push(medicament);
            }
            // Enregistrer l'ordonnance
            ecrireOrdonnanceCsv(referenceMedecin, referencePatient, datePrescription, medicaments);
        } else {
            // Afficher un message d'erreur si l'authentification a échoué
            JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe médecin incorrect. Vous n'êtes pas autorisé à enregistrer une ordonnance.");
        }

    }
}
