package test;

import enums.ETypeMedicament;

import org.junit.Test;
import static org.junit.Assert.*;

public class ETypeMedicamentTest {


    @Test
    public void test_enum_values_accessed_correctly() {
        ETypeMedicament venteLibre = ETypeMedicament.VENTE_LIBRE;
        ETypeMedicament ordonnance = ETypeMedicament.ORDONNANCE;
    
        assertEquals(ETypeMedicament.VENTE_LIBRE, venteLibre);
        assertEquals(ETypeMedicament.ORDONNANCE, ordonnance);
    }

    @Test
    public void test_enum_values_compared_with_operator() {
        ETypeMedicament venteLibre = ETypeMedicament.VENTE_LIBRE;
        ETypeMedicament ordonnance = ETypeMedicament.ORDONNANCE;
    
        assertTrue(venteLibre == ETypeMedicament.VENTE_LIBRE);
        assertTrue(ordonnance == ETypeMedicament.ORDONNANCE);
    }

    @Test
    public void test_enum_values_can_be_null() {
        ETypeMedicament venteLibre = null;
    
        assertNull(venteLibre);
    }

    @Test
    public void test_enum_values_compared_with_non_enum_values() {
        ETypeMedicament venteLibre = ETypeMedicament.VENTE_LIBRE;
    
        assertFalse(venteLibre.equals("VENTE_LIBRE"));
    }

}