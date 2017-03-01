/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.presenter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import ve.edu.ucab.braille.controller.Braille;
import ve.edu.ucab.braille.controller.ReadDocument;
import ve.edu.ucab.braille.controller.util;
import ve.edu.ucab.braille.model.Document;

/**
 * FXML Controller class
 *
 * @author Manuel Goncalves Lopez
 */
public class DocumentLoad implements Initializable {

    
    Highlighter highlighter;
    HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
    Document document;
    
    private Button BT_Previous;
    private Button BT_Next;
    @FXML
    private Pane PN_Left;
    @FXML
    private RadioButton RB_L1;
    @FXML
    private RadioButton RB_L4;
    @FXML
    private RadioButton RB_L2;
    @FXML
    private RadioButton RB_L5;
    @FXML
    private RadioButton RB_L3;
    @FXML
    private RadioButton RB_L6;
    @FXML
    private Pane PN_Right;
    @FXML
    private ProgressIndicator PI_ProgressLoad;
    @FXML
    private RadioButton RB_R1;
    @FXML
    private RadioButton RB_R4;
    @FXML
    private RadioButton RB_R2;
    @FXML
    private RadioButton RB_R5;
    @FXML
    private RadioButton RB_R3;
    @FXML
    private RadioButton RB_R6;
    @FXML
    private TextArea TA_Text;
    private Text TB_Prueba;
    @FXML
    private AnchorPane PN_Principal;
    private Button BT_NextWord;
    private Button BT_NextParagraph;
    private Button BT_PreviousParagraph;
    private Button BT_PreviousWord;
    @FXML
    private MenuItem MN_LoadFile;
    @FXML
    private MenuItem MN_PreviousLetter;
    @FXML
    private MenuItem MN_PreviousWord;
    @FXML
    private MenuItem MN_PreviousParagraph;
    @FXML
    private MenuItem MN_NextLetter;
    @FXML
    private MenuItem MN_NextWord;
    @FXML
    private MenuItem MN_NextParagraph;
    @FXML
    private MenuItem MN_Information;
    @FXML
    private MenuItem MN_Contact;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
                 initializeEvents();
         }
                 
    
    /**
     * Método que agrega e inicializa los eventos a los componentes de la interfaz
     * DocumentLoad
     */
   private void initializeEvents(){
          
         TA_Text.setOnDragDetected((MouseEvent)->{
            Dragboard db = TA_Text.startDragAndDrop(TransferMode.ANY);
         });
         
         TA_Text.setOnDragOver(DragEvent->{ 
            DragEvent.acceptTransferModes(TransferMode.ANY);
         });
         
         TA_Text.setOnDragDropped((DragEvent)-> {
            this.OnDragDropped(DragEvent);
         });
          
         
   }
    
   private void pressNextWord(){
                  System.out.println("==Press next word==");
         Document letter=document.getNext(util.Layer.WORD);
              if(letter!=null){
                  representBraille(letter);
              }
               else
              {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información del sistema");
                alert.setHeaderText("Limite de frontera");
                alert.setContentText("ha llegado al comienzo del documento");
                alert.showAndWait();    
              }
   }
   
   private void pressNextParragraph(){
                  System.out.println("==Press next parragraph==");
         Document letter=document.getNext(util.Layer.PARAGRAPH);
              if(letter!=null){
                  
                  representBraille(letter);
              }
               else
              {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información del sistema");
                alert.setHeaderText("Limite de frontera");
                alert.setContentText("ha llegado al comienzo del documento");
                alert.showAndWait();    
              }
   }
   
   private void pressPreviousWord(){
                  System.out.println("==Press previous word==");
         Document letter=document.getPrevious(util.Layer.WORD);
              if(letter!=null){
                  representBraille(letter);
              }
               else
              {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información del sistema");
                alert.setHeaderText("Limite de frontera");
                alert.setContentText("ha llegado al comienzo del documento");
                alert.showAndWait();    
              }
   }
   
   private void pressPreviousParragraph(){
                  System.out.println("==Press previous parragraph==");
         Document letter=document.getPrevious(util.Layer.PARAGRAPH);
              if(letter!=null){
                  representBraille(letter);
              }
               else
              {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información del sistema");
                alert.setHeaderText("Limite de frontera");
                alert.setContentText("ha llegado al comienzo del documento");
                alert.showAndWait();    
              }
   }
   
   private void representBraille(Document letter){
       
                Braille braille=new Braille();
                TA_Text.deselect();
                TA_Text.selectRange(letter.getId(), letter.getId()+1);
                List<RadioButton> left=new ArrayList<>();
                left.add(RB_L1);
                left.add(RB_L2);
                left.add(RB_L3);
                left.add(RB_L4);
                left.add(RB_L5);
                left.add(RB_L6);

                List<RadioButton> right=new ArrayList<>();
                right.add(RB_R1);
                right.add(RB_R2);
                right.add(RB_R3);
                right.add(RB_R4);
                right.add(RB_R5);
                right.add(RB_R6);

                braille.representBraille(left, right,letter);
   }
   
   /**
    * Método que se ejecuta al presionar el boto "Anterior" en la interfaz
    * el cual realiza la lectura del caracter anterior y su represenación braille
    */
    private void pressPreviousButton(){
              Document letter=document.getPrevious(util.Layer.LETTER);
              if(letter!=null){
                  representBraille(letter);
              }
               else
              {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información del sistema");
                alert.setHeaderText("Limite de frontera");
                alert.setContentText("ha llegado al comienzo del documento");
                alert.showAndWait();    
              }
            
    }
    /**
    * Método que se ejecuta al presionar el boto "Siguiente" en la interfaz
    * el cual realiza la lectura del caracter siguiente y su represenación braille
     */
    private void pressNextButton(){
        
              Document letter=document.getNext(util.Layer.LETTER);
              
              if(letter!=null){
                  representBraille(letter);
              }
              else
              {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información del sistema");
                alert.setHeaderText("Limite de frontera");
                alert.setContentText("ha llegado al final del documento");
                alert.showAndWait();    
              }
    }
    
    /**
     * Método que se ejecuta una vez el usuario arrastre y suelte un archivo 
     * sobre el TextArea 
     * @param event 
     */
    private void OnDragDropped(DragEvent event) {
        
                event.acceptTransferModes(TransferMode.ANY);
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()){
                    success = true;
                    List<File> ListArchivo ;
                    ListArchivo = db.getFiles();
                    File archivo;
                    for (int i=0;i<ListArchivo.size();i++){
                        archivo =ListArchivo.get(i);
                        String path=archivo.getAbsolutePath();
                        ReadDocument read=new ReadDocument(path);
                        try {
                            document=read.getDocument(PI_ProgressLoad);
                            
                            TA_Text.setText(document.getText());
                            TA_Text.setEditable(false);

                        } catch (IOException ex) {
                            Logger.getLogger(DocumentLoad.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(DocumentLoad.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(DocumentLoad.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                    }
                    
                    TA_Text.selectRange(1, 0);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DocumentLoad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                event.setDropCompleted(success);
                
                event.consume();
            }
    
    }



    @FXML
    private void loadFileActionMenu(ActionEvent event) {
        
        
        
        try {
            FileDialog fd = new FileDialog(new JFrame(), "Test", FileDialog.LOAD);
            
            fd.setFile("*.txt;*.doc;*.docx;*.pdf");
            fd.setVisible(true);
            String file=fd.getFile();
            String directory=fd.getDirectory();
            String path=directory+file;
            ReadDocument read=new ReadDocument(path);
            document=read.getDocument(PI_ProgressLoad);
            
            TA_Text.setText(document.getText());
            TA_Text.setEditable(false);
            fd.dispose();
            
//                System.out.println(fd.getFile());
//         JFileChooser fileChooser = new JFileChooser();
//            int returnValue = fileChooser.showOpenDialog(null);
//            if (returnValue == JFileChooser.APPROVE_OPTION) {
//              File selectedFile = fileChooser.getSelectedFile();
//              System.out.println(selectedFile.getName());
//            }
          
//        Desktop.getDesktop().open(selectedFile);
        } catch (IOException ex) {
            Logger.getLogger(DocumentLoad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(DocumentLoad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DocumentLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void previousLetterActionMenu(ActionEvent event) {
        this.pressPreviousButton();
    }

    @FXML
    private void previousWordActionMenu(ActionEvent event) {
        this.pressPreviousWord();
    }

    @FXML
    private void previousParagraphActionMenu(ActionEvent event) {
        this.pressPreviousParragraph();
    }

    @FXML
    private void nextLetterActionMenu(ActionEvent event) {
        this.pressNextButton();
    }

    @FXML
    private void nextWordActionMenu(ActionEvent event) {
        this.pressNextWord();
    }

    @FXML
    private void nextParagraphActionMenu(ActionEvent event) {
        this.pressNextParragraph();
    }

    @FXML
    private void informationActionMenu(ActionEvent event) {
        
    }

    @FXML
    private void contactActionMenu(ActionEvent event) {
    }


}

