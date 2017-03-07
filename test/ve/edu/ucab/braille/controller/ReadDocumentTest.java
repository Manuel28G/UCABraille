/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.controller;

import javafx.scene.control.ProgressBar;
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
public class ReadDocumentTest {
    
    public ReadDocumentTest() {
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
     * Test of getDocument method, of class ReadDocument.
     */
    @Test
    public void testGetDocument() throws Exception {
        System.out.println("getDocument");
        ProgressBar _progress = null;
        ReadDocument instance = null;
        Document expResult = null;
        Document result = instance.getDocument(_progress);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
