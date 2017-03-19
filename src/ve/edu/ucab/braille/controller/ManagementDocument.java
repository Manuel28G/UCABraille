/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.controller;

import com.panamahitek.ArduinoException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.Dragboard;
import ve.edu.ucab.braille.model.ArduinoConnection;
import ve.edu.ucab.braille.model.Document;
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
    
    private Document document;
    private String documentRute;
    private int caracterOnFocus;
    private Document letterOnFocus;
    private final Braille braille;
    
    private ManagementDocument(){
//        document
        braille=new Braille();
        try {
            arduino=new ArduinoConnection("COM3");
        } catch (ArduinoException ex) {
            Logger.getLogger(ManagementDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    
    public String getNextLetter(){
        letterOnFocus=document.getNext(util.Layer.LETTER);
        String response=(letterOnFocus!=null)?letterOnFocus.getText():null;
        validateEdge(response);
        return response;
    }
    
    public String getPreviousLetter(){
        
        letterOnFocus=document.getPrevious(util.Layer.LETTER);
        String response=(letterOnFocus!=null)?letterOnFocus.getText():null;
        validateEdge(response);
        return response;
    }
    
    public String getNextWord(){
        letterOnFocus=document.getNext(util.Layer.WORD);
        String response=(letterOnFocus!=null)?letterOnFocus.getText():null;
        validateEdge(response);
        return response;
    }
    
    public String getPreviousWord(){
        letterOnFocus=document.getPrevious(util.Layer.WORD);
        String response=(letterOnFocus!=null)?letterOnFocus.getText():null;
        validateEdge(response);
        return response;
    }
    
    public String getNextParagraph(){
        letterOnFocus=document.getNext(util.Layer.PARAGRAPH);
        String response=(letterOnFocus!=null)?letterOnFocus.getText():null;
        validateEdge(response);
        return response;
    }
    
    public String getPreviousParagraph(){
        letterOnFocus=document.getPrevious(util.Layer.PARAGRAPH);
        String response=(letterOnFocus!=null)?letterOnFocus.getText():null;
        validateEdge(response);
        return response;
    }
    
    private void validateEdge(String response){
        new Thread(){
            public void run(){
                if(response==null){
                    arduino.alertNotification();
                }
            }
        }.start();
    }
    
    public void refreshBrailleRepresent(List<RadioButton> _left,List<RadioButton> _right,TextArea _textArea){
        
                new Thread(){ 
                    public void run(){
                    arduino.sendData(((Letter)letterOnFocus).toBraille());
                    }}.start();        
                braille.representBraille(_left, _right,letterOnFocus);
                _textArea.selectRange(letterOnFocus.getId(),letterOnFocus.getId()+1);
                
    }
    
    
    public void loadDocumentText(TextArea _textArea,String _path){
                        ReadDocument read=new ReadDocument(_path);
                        try {
                            document=read.getDocument();
                            
                            _textArea.setText(document.getText());
                            _textArea.setEditable(false);
                            _textArea.selectRange(1, 0);

                        } catch (IOException | InterruptedException | InvocationTargetException ex) {
                            Logger.getLogger(DocumentLoad.class.getName()).log(Level.SEVERE, null, ex);
                        }    
                        
    }
    
    public boolean dragAndDrop(Dragboard _dragBoard,TextArea _textArea){
                boolean success = false;
                if (_dragBoard.hasFiles()){
                    success = true;
                    List<File> ListArchivo ;
                    ListArchivo = _dragBoard.getFiles();
                    File archivo;
                    for (int i=0;i<ListArchivo.size();i++){
                        archivo =ListArchivo.get(i);
                        String path=archivo.getAbsolutePath();
                        this.loadDocumentText(_textArea, path);
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
