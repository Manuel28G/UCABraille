/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class Configuration {
    private boolean automaticRead;
    private double speedRead;//Minutos,Segundos
    private boolean saveLastFile;//Guardar el ultimo documento leido
    private String terminal;
    private String fileRead;
    private String lastLetterRead;
    private static final String configurationFolder = System.getProperty("user.dir")+"/Config";
    private static final String fileName="/configuration.json";
    private static final String configurationRute = configurationFolder+fileName;
    
    public Configuration(){
         automaticRead = false;
         speedRead = 0.0;
         saveLastFile = false;
         terminal = "";
         fileRead = "";
         lastLetterRead = "";
    }

    public boolean isAutomaticRead() {
        return automaticRead;
    }

    public void setAutomaticRead(boolean automaticRead) {
        this.automaticRead = automaticRead;
    }

    public double getSpeedRead() {
        return speedRead;
    }

    public void setSpeedRead(double speedRead) {
        this.speedRead = speedRead;
    }

    public boolean isSaveLastFile() {
        return saveLastFile;
    }

    public void setSaveLastFile(boolean saveLastFile) {
        this.saveLastFile = saveLastFile;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getFileRead() {
        return fileRead;
    }

    public void setFileRead(String fileRead) {
        this.fileRead = fileRead;
    }

    public String getLastLetterRead() {
        return lastLetterRead;
    }

    public void setLastLetterRead(String lastLetterRead) {
        this.lastLetterRead = lastLetterRead;
    }
 
    /**
     * Metodo que carga la configuración inicial del sistema de un archivo JSON
     */
    public static Configuration loadConfiguration(){
        try {
            return loadConfiguration(configurationRute);
        } catch (FileNotFoundException ex) {
            System.out.println("Mensaje: "+ex.getMessage());
            System.out.println("Causa: "+ex.getCause());
            return null;
        }
    }
    
    /**
     * Metodo que carga la configuración inicial del sistema de un archivo JSON específico
     * @param _path ruta absoluta del archivo JSON a cargar
     * @return Objeto Configuración con toda la configuración del sistema
     * @throws java.io.FileNotFoundException si el archivo destino no es una ruta válida
     */
    public static Configuration loadConfiguration(String _path)throws FileNotFoundException{
        
        Gson gson = new Gson();
        File file = new File(_path);
        Configuration config = new Configuration();
        if(file.exists()){
            FileReader reader = new FileReader(file);
            config = gson.fromJson(reader, Configuration.class);
        }
        return config;
    }
    
    /**
     * Método que guarda la configuración en un archivo JSON 
     */
    public void saveConfiguration() {
        Writer writer = null;
        try {
            
            File file = new File(configurationRute);
            if(!file.exists()){
                new File(configurationFolder).mkdirs();
                file.createNewFile();
                file.setExecutable(false);
                file.setWritable(true);
                file.setReadable(true);
            }
            writer = new FileWriter(configurationRute);
            Gson gson = new GsonBuilder().create();
            gson.toJson(this, writer);
        } catch (IOException ex) {
            System.out.println("Mensaje: "+ex.getMessage());
            System.out.println("Causa: "+ex.getCause());
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
}
