/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

/**
 * Clase que contendrá los metodos necesarios para realizar la conexión con el dispositivo del arduino
 * @author Manuel Goncalves Lopez
 */
public class ArduinoConnection {
   private String terminal="COM3";//Terminal por el cual se conecta el arduino
   private int frecuency=9600;
   private PanamaHitek_Arduino connection ;
   
   /**
    * Constructor de la clase Arduino Connection
    * @param _terminal Terminal por el cual esta conectado el Arduino p.e. COM1
    * @param frecuency frecuencia de la conexión 
    */
   public ArduinoConnection(String _terminal,int _frecuency) throws ArduinoException{
       terminal=_terminal;
       System.out.println("TERMINAL:"+terminal);
       System.out.println("Frecuencia:"+frecuency);
       frecuency=_frecuency;
       connection=new PanamaHitek_Arduino();
       connection.arduinoTX(terminal, frecuency);//declaramos solo para envio
   }
   
   /**
    * Constructor de la clase Arduino Connection
    * @param _terminal Terminal por el cual esta conectado el Arduino p.e. COM1
    * @param frecuency frecuencia de la conexión 
    */
   public ArduinoConnection(String _terminal) throws ArduinoException{
       terminal=_terminal;
       
       System.out.println("TERMINAL:"+terminal);
       System.out.println("Frecuencia:"+frecuency);
       connection=new PanamaHitek_Arduino();
       connection.arduinoTX(terminal, frecuency);//declaramos solo para envio
   }
   /**
   * Metodo que envia dos caracteres al ardunio para ser representados
   * @param represent caracteres que serán representados en el arduino
   */
   public void sendData(byte[][] represent){
       System.out.println("Imprimiendo por el arduino");
       String message=Arrays.toString(represent[0]).replace("[", "").replace("]", "").replace(",","").replace(" ", "");
       message+=Arrays.toString(represent[1]).replace("[", "").replace("]", "").replace(",","").replace(" ", "");
       System.out.println("Message:"+message);
       try {
           connection.sendData(message);
       } catch (ArduinoException ex) {
           Logger.getLogger(ArduinoConnection.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SerialPortException ex) {
           Logger.getLogger(ArduinoConnection.class.getName()).log(Level.SEVERE, null, ex);
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
       System.out.println("Enviando señal de notificación al arduino");
   }
   
}
