package Pharmacie;

public interface IBoiteMail {
    public void envoyerMail(String email, String Objet, String message);
    public void recevoirMail(String email, String Objet, String message);
    public void recevoirToutMail(String Objet, String message);
    public void envoyerToutMail(String Objet, String message);
    public void supprimerMail(String Objet, String message);
    public void supprimerToutMail();public void afficherMail(String Objet, String message);
    public void afficherToutMail(String Objet, String message);
}
