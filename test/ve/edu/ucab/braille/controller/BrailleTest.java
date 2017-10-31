/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.controller;

import java.util.List;
import javafx.scene.control.RadioButton;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ve.edu.ucab.braille.model.Document;

/**
 *
 * @author Yadira Brea
 */
public class BrailleTest {
    
    public BrailleTest() {
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
     * Test of getBraille method, of class Braille.
     */
    @Test
//    public void testGetBraille() {
//        System.out.println("getBraille");
//        String letter = "´";
//        Braille instance = new Braille();
//        byte[][] expResult = {{0,0,0,0,0,0},{1,0,0,0,0,0}};
//        byte[][] result = instance.getBraille(letter);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }

    //metodo sin erorres si se introduce la letra deseada se obtiene el array
//    esperado faltan buscar los caracteres restantes para tener la tabla mas
//    completa
    
    /**
     * Test of representBraille method, of class Braille.
     */
//    @Test
    public void testRepresentBraille() {
        System.out.println("representBraille");
        List<RadioButton> left = null;
        List<RadioButton> right = null;
        Document letter = null;
        Braille instance = new Braille();
        instance.representBraille(left, right, letter);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
