package pharmacie.entites;

public interface IOrdonnance {
    Ordonnance build();

    Ordonnance self();

    Ordonnance avecNomPatient(String nomPatient);

    Ordonnance avecPrenomPatient(String prenomPatient);

    Ordonnance avecDateNaissance(String dateNaissance);

    Ordonnance avecAdresse(String adresse);

    Ordonnance avecTelephone(String telephone);

    Ordonnance avecEmail(String email);

    Ordonnance avecDateOrdonnance(String dateOrdonnance);

    Ordonnance avecMedecin(String medecin);

    Ordonnance avecNumeroOrdonnance(String numeroOrdonnance);

    Ordonnance avecSpecialite(String specialite);

    Ordonnance avecMedicament(String medicament);

    Ordonnance avecQuantite(String quantite);

    Ordonnance avecPosologie(String posologie);

    Ordonnance avecDuree(String duree);

    Ordonnance avecCommentaire(String commentaire);
}
