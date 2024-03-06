package Pharmacie;

public class BoiteMail implements IBoiteMail{
    private String email;
    private String Objet;
    private String message;

    public BoiteMail(String email, String Objet, String message) {
        this.email = email;
        this.Objet = Objet;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getObjet() {
        return Objet;
    }

    public String getMessage() {
        return message;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setObjet(String Objet) {
        this.Objet = Objet;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BoiteMail [email=" + email + ", Objet=" + Objet + ", message=" + message + "]";
    }

    @Override
    public void envoyerMail(String email, String Objet, String message) {
        System.out.println("Email envoyé à " + email + " avec l'objet " + Objet + " et le message " + message);
    }

    @Override
    public void recevoirMail(String email, String Objet, String message) {
        System.out.println("Email reçu de " + email + " avec l'objet " + Objet + " et le message " + message);
    }

    @Override
    public void recevoirToutMail(String Objet, String message) {
    }

    @Override
    public void envoyerToutMail(String Objet, String message) {

    }

    @Override
    public void supprimerMail(String Objet, String message) {

    }

    @Override
    public void supprimerToutMail() {

    }

    @Override
    public void afficherMail(String Objet, String message) {

    }

    @Override
    public void afficherToutMail(String Objet, String message) {

    }
}
