package test;

import pharmacie.Medicament;

import org.junit.Test;
import static org.junit.Assert.*;

public class GetquantiteenstockTest {


    // Should return the current quantity in stock when called
    @Test
    public void test_return_current_quantity() {
        Medicament medicament = new Medicament("Medicament", 10);
        int quantity = medicament.getQuantiteEnStock();
    
        assertEquals(10, quantity);
    }

    // Should return 0 if the quantity in stock is initially set to 0
    @Test
    public void test_return_zero_quantity() {
        Medicament medicament = new Medicament("Medicament", 0);
        int quantity = medicament.getQuantiteEnStock();
    
        assertEquals(0, quantity);
    }

    // Should return the maximum integer value if the quantity in stock is initially set to Integer.MAX_VALUE
    @Test
    public void test_return_max_integer_value() {
        Medicament medicament = new Medicament("Medicament", Integer.MAX_VALUE);
        int quantity = medicament.getQuantiteEnStock();
    
        assertEquals(Integer.MAX_VALUE, quantity);
    }

    // Should return the minimum integer value if the quantity in stock is initially set to Integer.MIN_VALUE
    @Test
    public void test_return_min_integer_value() {
        Medicament medicament = new Medicament("Medicament", Integer.MIN_VALUE);
        int quantity = medicament.getQuantiteEnStock();
    
        assertEquals(Integer.MIN_VALUE, quantity);
    }

}