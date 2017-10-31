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

/**
 *
 * @author Yadira Brea
 */
public class ArduinoConnectionTest {
    
    public ArduinoConnectionTest() {
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
     * Test of sendData method, of class ArduinoConnection.
     */
    @Test
    public void testSendData() {
        System.out.println("sendData");
        String[][] represent = null;
        ArduinoConnection instance = null;
        instance.sendData(represent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextButtonListener method, of class ArduinoConnection.
     */
    @Test
    public void testNextButtonListener() {
        System.out.println("nextButtonListener");
        ArduinoConnection instance = null;
        instance.nextButtonListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of previousButtonListener method, of class ArduinoConnection.
     */
    @Test
    public void testPreviousButtonListener() {
        System.out.println("previousButtonListener");
        ArduinoConnection instance = null;
        instance.previousButtonListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alertNotification method, of class ArduinoConnection.
     */
    @Test
    public void testAlertNotification() {
        System.out.println("alertNotification");
        ArduinoConnection instance = null;
        instance.alertNotification();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
