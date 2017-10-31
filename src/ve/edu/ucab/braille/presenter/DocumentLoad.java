/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.presenter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimerTask;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.panamahitek.ArduinoException;
import ve.edu.ucab.braille.controller.ManagementDocument;
import ve.edu.ucab.braille.controller.ManagementHistory;
import ve.edu.ucab.braille.controller.ManagementNotification;
import ve.edu.ucab.braille.controller.ReadDocument;
import ve.edu.ucab.braille.controller.Util;
import ve.edu.ucab.braille.model.ArduinoConnection;
import ve.edu.ucab.braille.model.Configuration;

/**
 * FXML Controller class
 *
 * @author Manuel Goncalves Lopez
 */
public class DocumentLoad implements Initializable {

	private boolean isPressAutomaticRead = false; //Variable para determinar si se encuentra en lectura automática 

    @FXML
    private AnchorPane PN_Principal;

    @FXML
    private TextArea TA_Text;

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
    private MenuItem MN_AutomaticRead;

    @FXML
    private MenuItem MN_Information;

    @FXML
    private MenuItem MN_Contact;

    @FXML
    private ProgressIndicator PI_ProgressLoad;

    @FXML
    private ImageView IV_Connection;

    
    private List<RadioButton> leftRepresentation;
    private List<RadioButton> rightRepresentation;
    public static final Logger logger = LogManager.getLogger(DocumentLoad.class.getName());
    private static DocumentLoad documentLoad;
    private final ArduinoConnection arduino ;

    public DocumentLoad() {
    	documentLoad = this;
    	arduino = new ArduinoConnection();
    }
    
    public static DocumentLoad getInstance() {
    	if(documentLoad == null) {
    		documentLoad = new DocumentLoad();
    	}
    	return documentLoad;
    }
    
    public ImageView getIV_Connection() {
		return IV_Connection;
	}
    
    public boolean isArduinConnect() {
    	return arduino.isArduinoConnect();
    }
    
    public void arduinoIsConnect() {
        File file = new File("src/resource/Image/connect.png");
        Image image = new Image(file.toURI().toString());
    	IV_Connection.setImage(image);
    	arduino.setArduinoConnect(true);
    }
    
    public void arduinoIsDisconnect() {
        File file = new File("src/resource/Image/disconnect.png");
        Image image = new Image(file.toURI().toString());
    	IV_Connection.setImage(image);
    	arduino.setArduinoConnect(false); 
    }

	/**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        	if(arduino.isArduinoConnect()) {
        		this.arduinoIsConnect();
        	}
        	else
        	{
        		this.arduinoIsDisconnect();
        	}
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
        	ex.printStackTrace();
        	
        } catch (ArduinoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

         }
                 
    
    /**
     * Método que agrega e inicializa los eventos a los componentes de la interfaz
     * DocumentLoad
     * @throws ArduinoException 
     */
   private void initializeEvents() throws ArduinoException{
          
         TA_Text.setOnDragDetected((MouseEvent)->{
             TA_Text.startDragAndDrop(TransferMode.ANY);
         });
         
         TA_Text.setOnDragOver(DragEvent->{ 
            DragEvent.acceptTransferModes(TransferMode.ANY);
         });
         
         TA_Text.setOnDragDropped((DragEvent)-> {
            this.OnDragDropped(DragEvent);
         });
          

     	Configuration config = Configuration.getInstance();
        ArduinoConnection arduino =  ArduinoConnection.getInstance();
        if(!arduino.isArduinoConnect()) {
	     	if(!config.getArduinoTerminal().isEmpty() && config.getArduinoTransmisionRate()>0 && arduino.getSerialPort().contains(config.getArduinoTerminal())) {
	     		arduino.setTerminalConnection(config.getArduinoTerminal());
	     		arduino.setTerminalRate(config.getArduinoTransmisionRate());
	     		arduino.connectToArduino();
	     	}
	         
        }
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
            String edge;
            if(isNext) {
            	edge = "fianal";
            	ManagementNotification.playReadEndVoice();
            }
            else
            {
            	edge = "comienzo";
            	ManagementNotification.playInitialLetterVoice();
            }
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
    public void pressPreviousButton(){
        try {
       String response=ManagementDocument.getInstance().getPreviousLetter();
       validateEdge(response, false);
	    }
	    catch(NullPointerException ex) {
	    	System.err.println("No hay palabra anterior");
	    }
    }
    /**
    * Método que se ejecuta al presionar el boto "Siguiente" en la interfaz
    * el cual realiza la lectura del caracter siguiente y su represenación braille
     */
    public void pressNextButton(){
        try {
        String response=ManagementDocument.getInstance().getNextLetter();
        validateEdge(response, true);
        }
        catch(NullPointerException ex) {
        	System.err.println("No hay palabra siguiente");
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
                boolean success=ManagementDocument.getInstance().dragAndDrop(db, TA_Text);
                event.setDropCompleted(success);
                //refrescamos la selección
                ManagementDocument.getInstance().refreshBrailleRepresent(leftRepresentation, rightRepresentation,TA_Text);
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
            Configuration config = Configuration.getInstance();
            ManagementDocument.getInstance().loadDocumentText(TA_Text, path,config);
            //refrescamos la selección
            ManagementDocument.getInstance().refreshBrailleRepresent(leftRepresentation, rightRepresentation,TA_Text);
            fd.dispose();
    }

    @FXML
    void configActionMenu(ActionEvent event) {

        FXMLLoader fxmlLoader;         
        Parent root1 = null;
    	fxmlLoader = new FXMLLoader(UCABraille.class.getResource(UCABraille.ruteOptionsFXML));
         try {
			root1 = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

         Stage stage = new Stage();
         stage.initModality(Modality.APPLICATION_MODAL);
         stage.initStyle(StageStyle.DECORATED);
         stage.setMaximized(false);
         stage.setResizable(false);
         stage.setTitle("Option");
         stage.setScene(new Scene(root1));  
         stage.show();
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


    @FXML
    void onActionAutomaticRead(ActionEvent event) {
    	if(!isPressAutomaticRead) {
    		ManagementNotification.playAutomaticReadStartVoice();
    		try {
    		ReadDocument.getTimer().schedule(new TimerTask() {

                @Override
                public void run() {
                	try {
                		DocumentLoad.getInstance().pressNextButton();
                	}
                	catch (IllegalStateException ex) {
                		MN_AutomaticRead.setText(Util.PLAY_AUTOMATIC_READ);
                    	isPressAutomaticRead = !isPressAutomaticRead;
                		ManagementNotification.playAutomaticReadEndVoice();
                		this.cancel();
                	}
                }
            }, 0, Configuration.getInstance().getReadSpeed()*1000);

    		MN_AutomaticRead.setText(Util.PAUSE_AUTOMATIC_READ);
    		}
    		catch(java.lang.IllegalStateException ex) {
    			ex.printStackTrace();
    		}
    	}
    	else
    	{
    		MN_AutomaticRead.setText(Util.PLAY_AUTOMATIC_READ);
    		ReadDocument.getTimer().cancel();
    		ManagementNotification.playAutomaticReadEndVoice();
    	}
    	isPressAutomaticRead = !isPressAutomaticRead;
    	
    }

}

