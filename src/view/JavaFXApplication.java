/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ReadDocument;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import model.Letter;

/**
 *
 * @author LuisJose
 */
public class JavaFXApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        //para colocar el titulo al cuadro dialogo
        primaryStage.setTitle("Test drag and drop");
        //se inicializa el escenario
        Group root = new Group();
        //ahora se le asigna un tamano...
        Scene scene = new Scene(root, 400, 200);
        //se le pone un color
        scene.setFill(Color.GRAY);
        // creamos un texto dentro del cuadro y lo posicionamos 
        final Text target = new Text(150, 100, "DROP HERE");
        target.setScaleX(2.0);
        target.setScaleY(2.0);
        
        target.setOnDragDetected((MouseEvent event) -> {
            /* drag was detected, start a drag-and-drop gesture*/
            /* allow any transfer mode */
            Dragboard db = target.startDragAndDrop(TransferMode.ANY);
            
            event.consume();
        });
        
        target.setOnDragOver((DragEvent event) -> {
            /* data is dragged over the target */
            System.out.println("onDragOver");
            event.acceptTransferModes(TransferMode.ANY);
            event.consume();
        });
        
        target.setOnDragEntered((DragEvent event) -> {
            /* the drag-and-drop gesture entered the target */
            System.out.println("onDragEntered");
            /* show to the user that it is an actual gesture target */
            
            scene.setFill(Color.YELLOW);
            
            
//                    File origen = new File("C:/Users/LuisJose/Documents/test.txt");
//                    File destino = new File("C:/Users/LuisJose/Pictures/test.txt");
//                    try {
//                        Files.copy(origen.toPath(), destino.toPath());
//                       
//                    } catch (FileAlreadyExistsException ex) {
//                        Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
//                        
//                    } catch (IOException ex) {
//                        Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
//                        
//                    }


event.consume();
        });
        
        target.setOnDragDropped((DragEvent event) -> {
            /* data dropped */
            System.out.println("onDragDropped");
            // aqui hay que averiuar como validar si es un .docx .doc .pdf
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()){
                success = true;
                scene.setFill(Color.RED); 
                List<File> ListArchivo = new ArrayList<>();
                ListArchivo = db.getFiles();
                File archivo=null;
                for (int i=0;i<ListArchivo.size();i++){
                    archivo =ListArchivo.get(i);
                    System.out.println("path: "+archivo.getAbsolutePath());
                    System.out.println("name: "+archivo.getName());
                }
                String ext = getFileExtension(archivo);
                System.out.println("File extension is: "+getFileExtension(archivo));
                if((!"txt".equals(ext))&&(!"docx".equals(ext))&&(!"doc".equals(ext))&&(!"pdf".equals(ext))){
                    //aqui tiene que abrirte una ventana y no imprimirlo por pantalla
                    System.out.println("documento no permitido");
                }
                else{
                    File origen = new File(archivo.getAbsolutePath());
                    File destino = new File("C:\\Users\\LuisJose\\Documents\\NetBeansProjects\\JavaFXApplication\\"+archivo.getName());
                    try {
                        Files.copy(origen.toPath(), destino.toPath());
                       
                    } catch (FileAlreadyExistsException ex) {
                        System.out.println("El Archivo ya existe "+ex.getMessage());
                        Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
                        
                    } catch (IOException ex) {
                        System.out.println("Error por: "+ex.getMessage());
                        Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
                        
                    }
                }
            }
            event.setDropCompleted(success);
            
            event.consume();
        });
        
        target.setOnDragDone((DragEvent event) -> {
            /* the drag-and-drop gesture ended */
            System.out.println("onDragDone");
            /* if the data was successfully moved, clear it */
            if (event.getTransferMode() == TransferMode.COPY) {
                target.setText("DragDone");
            }
            
            event.consume();
        });
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
        

        //ahora lo adjuntamos al cuadro...
        root.getChildren().add(target);
        // y lo mostramos..
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
 
    
}
