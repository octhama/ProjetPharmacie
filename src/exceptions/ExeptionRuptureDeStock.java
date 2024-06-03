package exceptions;
/**
 * Exception levée lorsqu'un médicament n'est pas trouvé.
 * @see pharmacie.Pharmacie#acheterMedicament(String, int)
 */
public class ExeptionRuptureDeStock extends Throwable {
    public ExeptionRuptureDeStock(String message) {
        super(message);
    }
}
