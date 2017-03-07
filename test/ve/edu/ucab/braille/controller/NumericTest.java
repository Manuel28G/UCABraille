/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yadira Brea
 */
public class NumericTest {
    
    public NumericTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of round method, of class Numeric.
     */
    @Test
    public void testRound() {
        System.out.println("round");
        double value = 3.4543;
        int places = 2;
        double expResult = 3.45;
        double result = Numeric.round(value, places);
        assertEquals(expResult, result, 0.0);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
/*
    3.4578 el valor del doble
    luegares 2 
    resultado esperado 3.46
    resultado 3,46
    resultado de la prueba: falla entra en el metodo fail 
    

    -3.4578 el valor del doble
    luegares 2 
    resultado esperado -3.44
    resultado -3,46
    resultado de la prueba: falla esperaba 3,46

*/ 