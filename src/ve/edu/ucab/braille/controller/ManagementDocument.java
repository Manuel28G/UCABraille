/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.controller;

import com.panamahitek.ArduinoException;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Timer;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.Dragboard;
import ve.edu.ucab.braille.model.ArduinoConnection;
import ve.edu.ucab.braille.model.Configuration;
import ve.edu.ucab.braille.model.Document;
import ve.edu.ucab.braille.model.DocumentRead;
import ve.edu.ucab.braille.model.Letter;
import ve.edu.ucab.braille.presenter.DocumentLoad;

/**
 *Clase que realiza el manejo del documento de manera logistica y contiene metodos
 * para la obtención del caracter que se requiera (siguiente/anterior [parrafo,palabra,letra])
 * @author Manuel Goncalves Lopez
 */
public class ManagementDocument {
    
    private static ManagementDocument managementDocument=null;
    private ArduinoConnection arduino;
    
    private static Document document;
    private Document letterOnFocus;
    private final Braille braille;
    private Date lastChange = new Date();
    private Timer timerChange ;
    
    
    private ManagementDocument(){
    	timerChange = new Timer (2000, new ActionListener () 
    	{ 
    	   
			@Override
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				Date now = Calendar.getInstance().getTime();
				long seconds = (now.getTime() - lastChange.getTime())/1000;
				if(seconds > 10 && seconds < 13) {
					ManagementDocument.getInstance().neutralRepresentation();
				}
				
				
			} 
    	}); 
    	timerChange.start();
        braille=new Braille();    
        try { 
            arduino=ArduinoConnection.getInstance(); 
        } catch (ArduinoException ex) { 
           	Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Arduino Conexión");
            alert.setHeaderText("Error al intentar conectar con el arduino");
            alert.setContentText("Error al intentar conectar con el arduino, por favor verifique que este bien conectado y tenga configurado los parámetros correctos.");
            alert.showAndWait(); 
        } 
    }
    
    public Document getDocument() {
    		return document;
    }
    
    public String getDocumentRute(){
        return managementDocument.getDocumentRute();
    }
    
    public static ManagementDocument getInstance(){
        if(managementDocument==null ){
            managementDocument=new ManagementDocument();
        }
        return managementDocument;
    }
    
    public void neutralRepresentation() {
    	arduino.sendData(braille.getBraille(" "));
    }
    
    
    public String getNextLetter(){
    	lastChange = Calendar.getInstance().getTime();
        letterOnFocus=document.getNext(Util.Layer.LETTER);
        String response = null;
        if(letterOnFocus != null) {
	        document.getDocumentRead().setActualLetter(letterOnFocus.getId());
	        response=(letterOnFocus!=null)?letterOnFocus.getText():null;
        }

        validateEdge(response);
        return response;
    }
    
    public String getPreviousLetter(){

    	lastChange = Calendar.getInstance().getTime();
        letterOnFocus=document.getPrevious(Util.Layer.LETTER);
        String response = null;
        if(letterOnFocus != null) {
	        document.getDocumentRead().setActualLetter(letterOnFocus.getId());
	        response=(letterOnFocus!=null)?letterOnFocus.getText():null;
        }
        validateEdge(response);
        return response;
    }
    
    public String getNextWord(){
    	lastChange = Calendar.getInstance().getTime();
        letterOnFocus=document.getNext(Util.Layer.WORD);
        String response = null;
        if(letterOnFocus != null) {
	        document.getDocumentRead().setActualLetter(letterOnFocus.getId());
	        response=(letterOnFocus!=null)?letterOnFocus.getText():null;
        }
        validateEdge(response);
        return response;
    }
    
    public String getPreviousWord(){
    	lastChange = Calendar.getInstance().getTime();
        letterOnFocus=document.getPrevious(Util.Layer.WORD);
        String response = null;
        if(letterOnFocus != null) {
	        document.getDocumentRead().setActualLetter(letterOnFocus.getId());
	        response=(letterOnFocus!=null)?letterOnFocus.getText():null;
        }
        validateEdge(response);
        return response;
    }
    
    public String getNextParagraph(){
    	lastChange = Calendar.getInstance().getTime();
        letterOnFocus=document.getNext(Util.Layer.PARAGRAPH);
        String response = null;
        if(letterOnFocus != null) {
	        document.getDocumentRead().setActualLetter(letterOnFocus.getId());
	        response=(letterOnFocus!=null)?letterOnFocus.getText():null;
        }
        validateEdge(response);
        return response;
    }
    
    public String getPreviousParagraph(){
    	lastChange = Calendar.getInstance().getTime();
        letterOnFocus=document.getPrevious(Util.Layer.PARAGRAPH);
        String response = null;
        if(letterOnFocus != null) {
		    document.getDocumentRead().setActualLetter(letterOnFocus.getId());
		    response=(letterOnFocus!=null)?letterOnFocus.getText():null;
        }
        validateEdge(response);
        return response;
    }
    
    private void validateEdge(String response){
        new Thread(){
            public void run(){
                if(response==null){
                	//si se llego al limite de frontera notificar al motor que vibrará
                    arduino.alertNotification();
                }
            }
        }.start();
    }
    
    public void refreshBrailleRepresent(List<RadioButton> _left,List<RadioButton> _right,TextArea _textArea){  
                braille.representBraille(_left, _right,letterOnFocus);
                _textArea.selectRange(letterOnFocus.getId(),letterOnFocus.getId()+1);   
                if(DocumentLoad.getInstance().isArduinConnect()) {
	                new Thread(){  
	                    public void run(){ 
	                    
	                    arduino.sendData(((Letter)letterOnFocus).toBraille()); 
	                    }}.start();  
                }
    }
    
    
    public void loadDocumentText(TextArea _textArea,String _path,Configuration _configuration){
                        ReadDocument read=new ReadDocument(_path);
                        try {
                        	DocumentRead _documentRead = new DocumentRead();
                            document=read.getDocument(_documentRead);
                            if(document != null) {
	                    		ManagementDocument.getInstance().getNextLetter();//actualizamos a la letra actual obteniendo el texto y todos sus atributos
	                            letterOnFocus = document.getFocusIdChild();
	                            _textArea.setText(document.getText());
	                            _textArea.setEditable(false);
	                            _textArea.selectRange(1, 0);
	                            ManagementNotification.playDocumentLoadVoice();
                            }

                        } catch (IOException | InterruptedException | InvocationTargetException ex) {
                            Logger.getLogger(DocumentLoad.class.getName()).log(Level.SEVERE, null, ex);
                        }    
                        
    }
    
    public boolean dragAndDrop(Dragboard _dragBoard,TextArea _textArea){
                boolean success = false;
                if (_dragBoard.hasFiles()){
                    success = true;
                    List<File> listArchivo ;
                    listArchivo = _dragBoard.getFiles();
                    Configuration config = Configuration.getInstance();
                    for (File archivo : listArchivo){
                        String path=archivo.getAbsolutePath();
                        this.loadDocumentText(_textArea, path,config);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DocumentLoad.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            return success;
    }
}
