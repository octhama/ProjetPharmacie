
package test;
import utils.DateUtlis;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;
/**
 * Classe de test pour calculer la date de livraison.
 * On teste si la date de livraison est bien calculée.
 * @see DateUtlis#calculerDateLivraison()
 */
public class CalculerdatelivraisonTest {

    @Test
    public void test_retour_obj_datecalend_ok() {
        Calendar result = DateUtlis.calculerDateLivraison();
        assertNotNull(result);
        assertTrue(result instanceof Calendar);
    }

}