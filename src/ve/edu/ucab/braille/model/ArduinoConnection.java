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
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
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
   private boolean arduinoIsConnect = false;
   private SerialPortEventListener listener = new SerialPortEventListener() {
		
		@Override
		public void serialEvent(SerialPortEvent arg0) {
			try {
				if(connection.isMessageAvailable()) {
					final String next = "Letra siguiente";
					final String previous = "Letra anterior";

					String message = connection.printMessage();
					System.out.println("Mensaje recibido:"+message);
					switch(message) {
						case next:
							nextButtonListener();
							break;
						
						case previous: 
							previousButtonListener();
							break;
						
						default:
							System.out.println("ERROR COMANDO NO RECONOCIDO"); 
							break;
					}
				}
				
			} catch (SerialPortException | ArduinoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	};
   
   public static ArduinoConnection getInstance() throws ArduinoException {
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
   
   public ArduinoConnection() {
	       connection=new PanamaHitek_Arduino();
	       setTerminalConnection("");
   }
   
   private void connectToArduino(String _terminal,int _frecuency) throws ArduinoException {
	   if(!arduinoIsConnect) {
		   connection.arduinoRXTX(terminal, frecuency,listener);//declaramos solo para envio
		   connection.setTimeOut(1);
		   DocumentLoad.getInstance().arduinoIsConnect();
		   arduinoIsConnect = true;
	   }
	   else
	   {
		   System.out.println("ERROR YA EL ARDUINO ESTA CONECTADO");
	   }
   }
   
   public boolean isArduinoConnect() {
	   return arduinoIsConnect;
   }
   
   public void setArduinoConnect(boolean _arduinoIsConnect) {
	   arduinoIsConnect = _arduinoIsConnect;
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
   
   public void connectToArduino() throws ArduinoException {
	   connectToArduino(terminal, frecuency);
   }
   
   public void setTerminalConnection(String _terminal) throws NullPointerException {
	   if(_terminal != null || !_terminal.isEmpty()) {
		   terminal = _terminal;
	    }
	   else
	   {
		   throw new NullPointerException("El terminal asignado esta vacio o nulo.");
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
    	   System.out.println("Intentando enviar");
    	   System.out.println("ports:"+connection.getPortsAvailable());
    	   System.out.println("portsSerial:"+connection.getSerialPorts());
    	   PanamaHitek_Arduino connectTmp = new PanamaHitek_Arduino();
    	   System.out.println("ports:"+connectTmp.getPortsAvailable());
    	   System.out.println("portsSerial:"+connectTmp.getSerialPorts());
    	   connectTmp = null;
 
//    	   this.connectToArduino(terminal, frecuency);
           connection.sendData(message);
    	   System.out.println("Enviado..");
//           connection.flushSerialPort();
//    	   System.out.println("Enviado..");
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
       DocumentLoad.getInstance().pressNextButton();
   }
    
   /**
    * Metodo que realiza la lectura del la accion del boton
    * de anterior caracter del arudino
    */  
   public void previousButtonListener(){
       DocumentLoad.getInstance().pressPreviousButton();
   }
   
   /**
    *   Método que envia un pulso al motor de vibración del arduino para notificar
    * limite de frontera
    */
   public void alertNotification(){
	   
   }
   
}
