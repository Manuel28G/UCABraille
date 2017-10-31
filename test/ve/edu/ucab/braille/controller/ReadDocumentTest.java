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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import ve.edu.ucab.braille.model.Document;
import ve.edu.ucab.braille.model.DocumentRead;

/**
 *
 * @author Yadira Brea
 */
public class ReadDocumentTest {
    
    public ReadDocumentTest() {
    }
    

    
    @Test(expected = NullPointerException.class)
    public void NullDocument() throws InvocationTargetException, IOException, InterruptedException {
    	ReadDocument document = new ReadDocument(null);
    		document.getDocument(null);
    }
    
    @Test(expected = IOException.class)
    public void documentUnexist() throws InvocationTargetException, IOException, InterruptedException {
    	ReadDocument document = new ReadDocument("c:/prueba.txt");
    		document.getDocument(new DocumentRead());
    }
    
}
