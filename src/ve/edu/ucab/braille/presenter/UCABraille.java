/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.presenter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ve.edu.ucab.braille.controller.ManagementDocument;
import ve.edu.ucab.braille.controller.ManagementNotification;
import ve.edu.ucab.braille.model.Configuration;
import ve.edu.ucab.braille.model.Document;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class UCABraille extends Application {
    
    public static String ruteMainFXML="/ve/edu/ucab/braille/view/DocumentLoad.fxml";
    public static String ruteOptionsFXML="/ve/edu/ucab/braille/view/Options.fxml";
    String aplicationName="UCABraille";
    
    
    /**
     * Método que se inicia al levantar la interface Aplication desde el metodo Launch
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
      	FXMLLoader fxmlLoader;         
      	Parent root1 = null;
     	ve.edu.ucab.braille.model.Configuration config = Configuration.getInstance();
        try {
            fxmlLoader = new FXMLLoader(UCABraille.class.getResource(ruteMainFXML));
            root1 = (Parent) fxmlLoader.load();
            
        } catch (IOException ex) {
            String error="Mensaje: "+ex.getMessage();
            error +="Causa: "+ex.getCause();
            DocumentLoad.logger.error(error);
            Logger.getLogger(UCABraille.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setTitle(aplicationName);
        stage.setScene(new Scene(root1));  
        stage.setOnCloseRequest(t -> {
		    t.consume();
		    Document _document = ManagementDocument.getInstance().getDocument();
		    config.addDocument(_document);
		    config.getListDocument();
	       config.saveConfiguration();
		   stage.close();
		   System.exit(0);
		});
        
        
        ManagementNotification.playWelcomeVoice();
        
        stage.show();
    }

        /**
     * Método inicial con el cual arranca la aplicación
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
