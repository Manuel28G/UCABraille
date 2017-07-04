/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ve.edu.ucab.braille.controller.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class Configuration {
	
	private String lastView;
	private int readSpeed;
	private String arduinoTerminal;
	private int arduinoTransmisionRate;
	private List<DocumentRead> document;
	private static Configuration configuration;
 
	private Configuration() {
		lastView = "";
		readSpeed = 0;
		arduinoTerminal = "COM3";
		arduinoTransmisionRate = 9600;
		document = new ArrayList<>();
	}
	
    public String getLastView() {
		return lastView;
	}

	public void setLastView(String lastView) {
		this.lastView = lastView;
	}

	public int getReadSpeed() {
		return readSpeed;
	}

	public void setReadSpeed(int readSpeed) {
		this.readSpeed = readSpeed;
	}

	public String getArduinoTerminal() {
		return arduinoTerminal;
	}

	public void setArduinoTerminal(String arduinoTerminal) {
		this.arduinoTerminal = arduinoTerminal;
	}

	public int getArduinoTransmisionRate() {
		return arduinoTransmisionRate;
	}

	public void setArduinoTransmisionRate(int arduinoTransmisionRate) {
		this.arduinoTransmisionRate = arduinoTransmisionRate;
	}

	public List<DocumentRead> getDocument() {
		return document;
	}

	public void setDocument(List<DocumentRead> document) {
		this.document = document;
	}

	public  String getConfigurationRute() {
		return Util.configurationRute.getAbsolutePath();
	}

	public void addDocument(DocumentRead _document) {
		if(!document.isEmpty()) {
			for(DocumentRead doc: document) {
				if(doc.getTitle().equals(_document.getTitle()) && (doc.getSize().equals(_document.getSize()))) {
					if((_document.getActualLetter()>doc.getActualLetter()) || (doc.getActualLetter() == doc.getTotalLetter())) {
						doc = _document;
					}
					else
					{
						_document = doc;
					}
				}
			}
		}
		else
		{
			document.add(_document);
		}
	}
	
	
	/**
     * Metodo que carga la configuración inicial del sistema de un archivo JSON
     */
    public static Configuration getInstance(){
        try {
            return loadConfiguration(Util.configurationRute.getAbsolutePath());
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
        if(configuration == null) {
        	if(file.exists()){
	            FileReader reader = new FileReader(file);
	            configuration = gson.fromJson(reader, Configuration.class);
	        }
        	else {
        		configuration = new Configuration();
        	}
        }
        return configuration;
    }
    
    /**
     * Método que guarda la configuración en un archivo JSON 
     */
    public void saveConfiguration() {
        Writer writer = null;
        try {
            File file = Util.configurationRute;
            if(!file.exists()){
                String directory = Util.configurationRute.getAbsolutePath().replace(Util.configurationRute.getName(), "");
                new File(directory).mkdirs();
                file.createNewFile();
                file.setExecutable(false);
                file.setWritable(true);
                file.setReadable(true);
            }
            writer = new FileWriter(Util.configurationRute);
            Gson gson = new GsonBuilder().create();
            gson.toJson(configuration, writer);
        } catch (IOException ex) {
            System.out.println("Mensaje: "+ex.getMessage());
            System.out.println("Causa: "+ex.getCause());
        } finally {
            try {
            	if(writer != null) {
            		writer.close();
            	}
            } catch (IOException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
}
