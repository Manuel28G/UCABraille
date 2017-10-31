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

import junit.framework.Assert;

import static org.junit.Assert.*;

/**
 *
 * @author Yadira Brea
 */
public class NumericTest {
    
    public NumericTest() {
    }

    /**
     * Test of round method, of class Numeric.
     */
    @Test
    public void testRound() {
        double value = 3.4576;
        int places = 2;
        double expResult = 3.46;
        double result = Numeric.round(value, places);
        assertEquals(expResult, result,0.001);
        
	    value = 3.4546;
	    expResult = 3.45;
	    result = Numeric.round(value, places);
	    assertEquals(expResult, result,0.001);
        
        
    }
    

    /**
     * Test of round method, of class Numeric.
     */
    
    @Test
    public void negativeValue() {
        double value = -1;
        int places = 2;
        double expResult = 3.46;
        double result = Numeric.round(value, places);
        assertEquals(result, value,0.001);
        
    }
    

    /**
     * Test of round method, of class Numeric.
     */
    
    @Test(expected = IllegalArgumentException.class)
    public void negativePlaces() {
        double value = 2.0;
        int places = -1;
        double expResult = 3.46;
        double result = Numeric.round(value, places);
        
        
    }
}
