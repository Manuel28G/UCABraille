/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ve.edu.ucab.braille.controller.util;

/**
 *
 * @author Yadira Brea
 */
public class DocumentTest {
    
    public DocumentTest() {
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
     * Test of setFocusIdChild method, of class Document.
     */
    @Test
    public void testSetFocusIdChild() {
        System.out.println("setFocusIdChild");
        Document _childInFocus = null;
        Document instance = new DocumentImpl();
        instance.setFocusIdChild(_childInFocus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFocusIdChild method, of class Document.
     */
    @Test
    public void testGetFocusIdChild() {
        System.out.println("getFocusIdChild");
        Document instance = new DocumentImpl();
        Document expResult = null;
        Document result = instance.getFocusIdChild();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxRangeChild method, of class Document.
     */
    @Test
    public void testGetMaxRangeChild() {
        System.out.println("getMaxRangeChild");
        Document instance = new DocumentImpl();
        int expResult = 0;
        int result = instance.getMaxRangeChild();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaxRangeChild method, of class Document.
     */
    @Test
    public void testSetMaxRangeChild() {
        System.out.println("setMaxRangeChild");
        int _id = 0;
        Document instance = new DocumentImpl();
        instance.setMaxRangeChild(_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMinRangeChild method, of class Document.
     */
    @Test
    public void testSetMinRangeChild() {
        System.out.println("setMinRangeChild");
        int _id = 0;
        Document instance = new DocumentImpl();
        instance.setMinRangeChild(_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinRangeChild method, of class Document.
     */
    @Test
    public void testGetMinRangeChild() {
        System.out.println("getMinRangeChild");
        Document instance = new DocumentImpl();
        int expResult = 0;
        int result = instance.getMinRangeChild();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getText method, of class Document.
     */
    @Test
    public void testGetText() {
        System.out.println("getText");
        Document instance = new DocumentImpl();
        String expResult = "";
        String result = instance.getText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Document.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Document instance = new DocumentImpl();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGeneralId method, of class Document.
     */
    @Test
    public void testGetGeneralId() {
        System.out.println("getGeneralId");
        Document instance = new DocumentImpl();
        int expResult = 0;
        int result = instance.getGeneralId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Document.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int _id = 0;
        Document instance = new DocumentImpl();
        instance.setId(_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNext method, of class Document.
     */
    @Test
    public void testGetNext() {
        System.out.println("getNext");
        util.Layer _layer = null;
        Document instance = new DocumentImpl();
        Document expResult = null;
        Document result = instance.getNext(_layer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrevious method, of class Document.
     */
    @Test
    public void testGetPrevious() {
        System.out.println("getPrevious");
        util.Layer _layer = null;
        Document instance = new DocumentImpl();
        Document expResult = null;
        Document result = instance.getPrevious(_layer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DocumentImpl extends Document {

        public String getText() {
            return "";
        }

        public int getId() {
            return 0;
        }

        public int getGeneralId() {
            return 0;
        }

        public void setId(int _id) {
        }

        public Document getNext(util.Layer _layer) {
            return null;
        }

        public Document getPrevious(util.Layer _layer) {
            return null;
        }
    }
    
}
