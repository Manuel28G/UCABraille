/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.controller;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Yadira Brea
 */
public class BrailleTest {
    
    public BrailleTest() {
    }
    
    /**
     * Test of getBraille method, of class Braille.
     */
    @Test
    public void testRepresentBraille() {
    	Braille braille = new Braille();
    	byte[][] tmp = braille.getBraille(" ");
    	assertEquals(tmp, new byte[][] {{0,0,0,0,0,0},{0,0,0,0,0,0}});
    	
    	tmp = braille.getBraille("A");
    	assertEquals(tmp, new byte[][] {{0,0,1,1,1,0},{1,0,0,0,0,0}});
    	
    }
    
}
