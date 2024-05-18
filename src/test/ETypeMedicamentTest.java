package test;

import enums.ETypeMedicament;

import org.junit.Test;
import static org.junit.Assert.*;

public class ETypeMedicamentTest {

    @Test
    public void test_verif_enum_ok() {
        ETypeMedicament venteLibre = ETypeMedicament.VENTE_LIBRE;
        ETypeMedicament ordonnance = ETypeMedicament.ORDONNANCE;

        assertEquals(ETypeMedicament.VENTE_LIBRE, venteLibre);
        assertEquals(ETypeMedicament.ORDONNANCE, ordonnance);
        assertNotEquals(venteLibre, ordonnance);
    }

}