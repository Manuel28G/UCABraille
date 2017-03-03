/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Yadira Brea
 */
public class Source extends Application {
    //invocamos el desktop para realizar la busqueda
    private Desktop desktop = Desktop.getDesktop();
    @Override
    public void start(final Stage primaryStage) {
        //llamamos al metodo FileChooser y lo instanciamos para obtener el archivo.
        final FileChooser fileChooser = new FileChooser();
        //se crea un botón para con el que se se eligirá el archivo.
        Button btn = new Button();
        btn.setText("Elige tu Archivo");
        // escogemos el archivo.
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {

                File file = fileChooser.showOpenDialog(primaryStage);
                //si el archivo no es nulo se abre.
                if (file != null) {
                    //se llama al método open file para abrir el archivo.
                    openFile(file);
                }
            }

            private void openFile(File file) {
                try{
                    desktop.open(file);
                    // aqui obtenemos el archivo e imprimimos la ruta donde se encunetra el archivo.
                    System.out.println(file.getAbsolutePath());
                }
                catch (IOException ex) {
                Logger.getLogger(
                    Source.class.getName()).log(
                        Level.SEVERE, null, ex
                    );
            }
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Elige tu Archivo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
