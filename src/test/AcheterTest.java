package test;

import exceptions.ExeptionRuptureDeStock;
import pharmacie.Medicament;

import org.junit.Test;
import static org.junit.Assert.*;

public class AcheterTest {


    // The method should increase the quantity in stock of the medication by the given quantity.
    @Test
    public void test_increase_quantity_in_stock() throws ExeptionRuptureDeStock {
        Medicament medicament = new Medicament("Medicament", 10);
        int initialQuantity = medicament.getQuantiteEnStock();
        int quantityToAdd = 5;

        medicament.acheter(quantityToAdd);

        assertEquals(initialQuantity + quantityToAdd, medicament.getQuantiteEnStock());
    }

    // If the given quantity is zero, the method should not change the quantity in stock of the medication.
    @Test
    public void test_zero_quantity() throws ExeptionRuptureDeStock {
        Medicament medicament = new Medicament("Medicament", 10);
        int initialQuantity = medicament.getQuantiteEnStock();
        int quantityToAdd = 0;

        medicament.acheter(quantityToAdd);

        assertEquals(initialQuantity, medicament.getQuantiteEnStock());
    }

    // If the given quantity is negative, the method should throw an ExeptionRuptureDeStock with a message indicating that the quantity must be positive.
    @Test
    public void test_negative_quantity() {
        Medicament medicament = new Medicament("Medicament", 10);
        int initialQuantity = medicament.getQuantiteEnStock();
        int quantityToAdd = -5;

        try {
            medicament.acheter(quantityToAdd);
            fail("Expected ExeptionRuptureDeStock to be thrown");
        } catch (ExeptionRuptureDeStock e) {
            assertEquals("Quantity must be positive", e.getMessage());
            assertEquals(initialQuantity, medicament.getQuantiteEnStock());
        }
    }

    // If the sum of the current quantity in stock and the given quantity is greater than the maximum possible integer value, the method should throw an ExeptionRuptureDeStock with a message indicating that the quantity in stock would overflow.
    @Test
    public void test_quantity_overflow() {
        Medicament medicament = new Medicament("Medicament", Integer.MAX_VALUE);
        int initialQuantity = medicament.getQuantiteEnStock();
        int quantityToAdd = 1;

        try {
            medicament.acheter(quantityToAdd);
            fail("Expected ExeptionRuptureDeStock to be thrown");
        } catch (ExeptionRuptureDeStock e) {
            assertEquals("Quantity in stock would overflow", e.getMessage());
            assertEquals(initialQuantity, medicament.getQuantiteEnStock());
        }
    }

}