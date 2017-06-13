/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.presenter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class UCABraille extends Application {
    
    String ruteFXML="/ve/edu/ucab/braille/view/DocumentLoad.fxml";
    String aplicationName="UCABraille";
    
    
    /**
     * Método que se inicia al levantar la interface Aplication desde el metodo Launch
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
         FXMLLoader fxmlLoader;         
         Parent root1 = null;
         ve.edu.ucab.braille.model.Configuration config = new ve.edu.ucab.braille.model.Configuration();
         config = ve.edu.ucab.braille.model.Configuration.loadConfiguration();
         config.setSaveLastFile(!config.isSaveLastFile());
         config.saveConfiguration();
        try {
            fxmlLoader = new FXMLLoader(UCABraille.class.getResource(ruteFXML));
            System.out.println(fxmlLoader.getLocation());
            root1 = (Parent) fxmlLoader.load();
            
        } catch (IOException ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            Logger.getLogger(UCABraille.class.getName()).log(Level.SEVERE, null, ex);
        }
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.setTitle(aplicationName);
            stage.setScene(new Scene(root1));  
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
