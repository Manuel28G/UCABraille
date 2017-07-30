/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;
import ve.edu.ucab.braille.presenter.DocumentLoad;

/**
 * Clase que contendrá los metodos necesarios para realizar la conexión con el dispositivo del arduino
 * @author Manuel Goncalves Lopez
 */
public class ArduinoConnection {
   private String terminal="COM3";//Terminal por el cual se conecta el arduino
   private int frecuency=9600;
   private PanamaHitek_Arduino connection ;
   private static ArduinoConnection arduinoConnection;
   
   
   private static ArduinoConnection getInstance() throws ArduinoException {
	   if(arduinoConnection == null) {
		   Configuration config = Configuration.getInstance();
		   if(config.getArduinoTransmisionRate()>0 && !config.getArduinoTerminal().isEmpty()) {
			   arduinoConnection = new ArduinoConnection(config.getArduinoTerminal(),config.getArduinoTransmisionRate());
		   }
		   else
		   {
			   arduinoConnection = new ArduinoConnection();
		   }
	   }
	   return arduinoConnection;
   }

   public ArduinoConnection() throws ArduinoException {
	       connection=new PanamaHitek_Arduino();
	       setTerminalConnection("");
   }
   private void connectToArduino(String _terminal,int _frecuency) throws ArduinoException {
	   connection.arduinoTX(terminal, frecuency);//declaramos solo para envio
	   connection.setTimeOut(1);
	   DocumentLoad.getInstance().arduinoIsConnect();
   }
   
   
   /**
    * Constructor de la clase Arduino Connection
    * @param _terminal Terminal por el cual esta conectado el Arduino p.e. COM1
    * @param frecuency frecuencia de la conexión 
 * @throws URISyntaxException 
    */
   private ArduinoConnection(String _terminal,int _frecuency) throws ArduinoException{
       terminal=_terminal;
       frecuency=_frecuency;
       connection=new PanamaHitek_Arduino();
       if(connection.getSerialPorts().contains(_terminal)) {
    	   connectToArduino(terminal, frecuency);
       }
   }
   
   /**
    * Constructor de la clase Arduino Connection
    * @param _terminal Terminal por el cual esta conectado el Arduino p.e. COM1
    * @param frecuency frecuencia de la conexión 
    */
   private ArduinoConnection(String _terminal) throws ArduinoException{
       terminal=_terminal;
       connection=new PanamaHitek_Arduino();
       if(connection.getSerialPorts().contains(_terminal)) {
    	   connectToArduino(terminal, frecuency);
       }
   }
   
   
   public List<String> getSerialPort(){
	   return connection.getSerialPorts();
   }
   
   public void setTerminalRate(int _frecency) throws NumberFormatException{
	   if(_frecency>0) {
		   frecuency = _frecency;
	   }
	   else
	   {
		   throw new NumberFormatException("No se admiten números negativos a la frecuencia de transmisión");
	   }
   }
   
   public void setTerminalConnection(String _terminal) throws ArduinoException {

	   if(connection.getSerialPorts().size() == 1) {
		   terminal = connection.getSerialPorts().get(0);
    	   connectToArduino(terminal, frecuency);
	   }
	   else
       if(connection.getSerialPorts().contains(_terminal)) {
    	   connectToArduino(terminal, frecuency);
       }
   }
   
   /**
   * Metodo que envia dos caracteres al ardunio para ser representados
   * @param represent caracteres que serán representados en el arduino
   */
   public void sendData(byte[][] represent){
       String message=Arrays.toString(represent[0]).replace("[", "").replace("]", "").replace(",","").replace(" ", "");
       message+=Arrays.toString(represent[1]).replace("[", "").replace("]", "").replace(",","").replace(" ", "");
       try {
           connection.sendData(message);
           connection.flushSerialPort();
       } catch (ArduinoException ex) {
    	   ex.printStackTrace();
    	   DocumentLoad.getInstance().arduinoIsDisconnect();
       } catch (SerialPortException ex) {
    	   ex.printStackTrace();
    	   DocumentLoad.getInstance().arduinoIsDisconnect();
       }
   }
   
   /**
    * Metodo que realiza la lectura del la accion del boton
    * de siguiente caracter del arudino
    */
   public void nextButtonListener(){
       
   }
    
   /**
    * Metodo que realiza la lectura del la accion del boton
    * de anterior caracter del arudino
    */  
   public void previousButtonListener(){
       
   }
   
   /**
    * Método que envia un pulso al motor de vibración del arduino para notificar
    * limite de frontera
    */
   public void alertNotification(){
	   
   }
   
}
