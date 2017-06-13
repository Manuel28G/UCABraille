/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.presenter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import java.awt.FileDialog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFrame;
import ve.edu.ucab.braille.controller.ManagementDocument;
import ve.edu.ucab.braille.controller.ManagementHistory;

/**
 * FXML Controller class
 *
 * @author Manuel Goncalves Lopez
 */
public class DocumentLoad implements Initializable {

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
    public static  ProgressIndicator PI_ProgressLoad;
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
    @FXML
    private AnchorPane PN_Principal;
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
    @FXML
    public ProgressBar PB_Progress;
    
    private List<RadioButton> leftRepresentation;
    private List<RadioButton> rightRepresentation;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        try {
            initializeEvents();
            ManagementHistory history=ManagementHistory.getInstance();
                    history.registerHistory("");
                    //Cargando en la lista los RadioButtons para la representación
                    //visual del codigo braille
                    leftRepresentation=new ArrayList<>();
                    leftRepresentation.add(RB_L1);
                    leftRepresentation.add(RB_L2);
                    leftRepresentation.add(RB_L3);
                    leftRepresentation.add(RB_L4);
                    leftRepresentation.add(RB_L5);
                    leftRepresentation.add(RB_L6);
                    
                    rightRepresentation=new ArrayList<>();
                    rightRepresentation.add(RB_R1);
                    rightRepresentation.add(RB_R2);
                    rightRepresentation.add(RB_R3);
                    rightRepresentation.add(RB_R4);
                    rightRepresentation.add(RB_R5);
                    rightRepresentation.add(RB_R6);
        } catch (IOException ex) {
            Logger.getLogger(DocumentLoad.class.getName()).log(Level.SEVERE, null, ex);
        }

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
       String response=ManagementDocument.getInstance().getNextWord();
       validateEdge(response,true);
   }
   
   /**
    * Metodo que valida si se ha llegado al limite de frontera
    * @param response respuesta obtenida al realizar la accion (siguiente/anterior)
    * @param isNext variable que determina si la accion fue siguiente o anterior para genera un mensaje acorde a la misma
    */
   private void validateEdge(String response,boolean isNext){
       if(response!=null){
        ManagementDocument.getInstance().refreshBrailleRepresent(leftRepresentation, rightRepresentation,TA_Text);
       }
       else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Información del sistema");
            alert.setHeaderText("Limite de frontera");
            String edge=(isNext)?"fianal":"comienzo";
            alert.setContentText("ha llegado al "+edge+" del documento");
            alert.showAndWait();     
       }
   }
   private void pressNextParragraph(){
       String response=ManagementDocument.getInstance().getNextParagraph();
       validateEdge(response,true);
   }
   
   private void pressPreviousWord(){
       
       String response=ManagementDocument.getInstance().getPreviousWord();    
       validateEdge(response, false);
   }
   
   private void pressPreviousParragraph(){
       String response=ManagementDocument.getInstance().getPreviousParagraph();  
       validateEdge(response, false);
   }
   
  
   
   /**
    * Método que se ejecuta al presionar el boto "Anterior" en la interfaz
    * el cual realiza la lectura del caracter anterior y su represenación braille
    */
    private void pressPreviousButton(){
        
       String response=ManagementDocument.getInstance().getPreviousLetter();
       validateEdge(response, false);    
    }
    /**
    * Método que se ejecuta al presionar el boto "Siguiente" en la interfaz
    * el cual realiza la lectura del caracter siguiente y su represenación braille
     */
    private void pressNextButton(){
        
        String response=ManagementDocument.getInstance().getNextLetter();
        validateEdge(response, true);
    }
    
    /**
     * Método que se ejecuta una vez el usuario arrastre y suelte un archivo 
     * sobre el TextArea 
     * @param event 
     */
    private void OnDragDropped(DragEvent event) {
               event.acceptTransferModes(TransferMode.ANY);
                Dragboard db = event.getDragboard();
                boolean success=ManagementDocument.getInstance().dragAndDrop(db, TA_Text);
                event.setDropCompleted(success);
                event.consume();
    }



    @FXML
    private void loadFileActionMenu(ActionEvent event) {

            FileDialog fd = new FileDialog(new JFrame(), "Test", FileDialog.LOAD);
            fd.setFile("*.txt;*.doc;*.docx;*.pdf");
            fd.setVisible(true);
            String file=fd.getFile();
            String directory=fd.getDirectory();
            String path=directory+file;
            ManagementDocument.getInstance().loadDocumentText(TA_Text, path);
            fd.dispose();
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

