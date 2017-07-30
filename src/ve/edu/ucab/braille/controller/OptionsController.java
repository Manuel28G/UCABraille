/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.panamahitek.ArduinoException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ve.edu.ucab.braille.model.ArduinoConnection;
import ve.edu.ucab.braille.model.Configuration;
import ve.edu.ucab.braille.presenter.DocumentLoad;
import ve.edu.ucab.braille.presenter.UCABraille;

/**
 * FXML Controller class
 *
 * @author Manuel Goncalves Lopez
 */
public class OptionsController implements Initializable {

	
	@FXML
    private ComboBox<String> CB_Terminal;

    @FXML
    private Slider SL_ReadTime;

    @FXML
    private ToggleButton TBT_Automatic;

    @FXML
    private Button BT_Save;

    @FXML
    private Button BT_Cancel;

    @FXML
    private Label LB_ReadTime;

    @FXML
    private TextField TF_TransmitionRate;
	    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	boolean terminalConfig = false;
    	Configuration config = Configuration.getInstance();
    	if(config.getArduinoTransmisionRate() > 0){
    		TF_TransmitionRate.setText(Integer.toString(config.getArduinoTransmisionRate()));
    	}
    	
    	if(config.getReadSpeed()>0) {
    		TBT_Automatic.setSelected(true);
    		SL_ReadTime.setValue(config.getReadSpeed());
    		setTextTime(config.getReadSpeed());
    	}
    	
    	ArduinoConnection arduino;
		try {
			arduino = ArduinoConnection.getInstance();
			List<String> terminalList = new ArrayList<>();

	    	if(!config.getArduinoTerminal().isEmpty()) {
	    		terminalList.add(config.getArduinoTerminal());
	    		terminalConfig = true;
	    	}
	    	
			terminalList.addAll(arduino.getSerialPort());
			
			CB_Terminal.setItems(FXCollections.observableList(terminalList));
			
			if(terminalConfig) {
				CB_Terminal.getSelectionModel().select(0);
			}
			
			
		} catch (ArduinoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }   
    
    @FXML
    void OnActionAutomticRead(ActionEvent event) {

    }

    @FXML
    void OnActionCancel(ActionEvent event) {
    	Stage stage = (Stage)BT_Cancel.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void OnActionSave(ActionEvent event) {
    	Configuration  config = Configuration.getInstance();
    	if(TF_TransmitionRate.getText().isEmpty()){
    		TF_TransmitionRate.setStyle("-fx-background-color: #ff6666");
    		return;
    	}
    	else
    	{
    		TF_TransmitionRate.setStyle("-fx-background-color: #ffffff");
    	}
    		
		if(CB_Terminal.getSelectionModel().getSelectedItem() == null) {
			CB_Terminal.setStyle("-fx-background-color: #ff6666");
			return;
		}
		else
		{
			CB_Terminal.setStyle("-fx-background-color: #ffffff");
		}
		
		try {
    		int transmition = Integer.parseInt(TF_TransmitionRate.getText());
        	config.setArduinoTransmisionRate(transmition);
        	config.setArduinoTerminal(CB_Terminal.getSelectionModel().getSelectedItem());
        	if(TBT_Automatic.isSelected()) {
        		config.setReadSpeed((int)SL_ReadTime.getValue());
        	}
        	else
        	{
        		config.setReadSpeed(0);//indica que la lectura será manual
        	}
        	config.saveConfiguration();
        	
        	ArduinoConnection.getInstance().setTerminalRate(config.getArduinoTransmisionRate());
        	ArduinoConnection.getInstance().setTerminalConnection(config.getArduinoTerminal());
        	Stage stage = (Stage) BT_Save.getScene().getWindow();
        	Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Configuración");
            alert.setHeaderText("Configuración guardada");
            alert.setContentText("Los valores modificados han sido guardados con éxtio.");
            alert.showAndWait(); 
        	stage.close();
		}
		catch(NumberFormatException nfex) {
			Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de formato");
            alert.setHeaderText("Error 100");
            alert.setContentText("El valor introducido en 'Transmision rate' no corresponde a un número positivo");
            alert.showAndWait(); 
            return;
		} catch (ArduinoException e) {
			Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Arduino Error");
            alert.setHeaderText("Error 101");
            alert.setContentText(e.getMessage());
            alert.showAndWait(); 
		}
    		
    	
    }

    @FXML
    void OnKeyTypedTransmitionRate(KeyEvent event) {

    }

   private void setTextTime(int seconds) {
	   String text ="Tiempo por palabra:";
	   	text += seconds + "seg";
	   	LB_ReadTime.setText(text);
   }

    @FXML
    void dragMouseReleased(MouseEvent event) {
    	setTextTime((int)SL_ReadTime.getValue());
    }

}
