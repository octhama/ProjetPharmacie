
package test;
import utils.DateUtlis;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class CalculerdatelivraisonTest {

    @Test
    public void test_retour_obj_datecalend_ok() {
        Calendar result = DateUtlis.calculerDateLivraison();
        assertNotNull(result);
        assertTrue(result instanceof Calendar);
    }

}