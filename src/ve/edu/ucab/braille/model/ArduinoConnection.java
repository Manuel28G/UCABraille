/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import com.panamahitek.PanamaHitek_Arduino;

/**
 * Clase que contendrá los metodos necesarios para realizar la conexión con el dispositivo del arduino
 * @author Manuel Goncalves Lopez
 */
public class ArduinoConnection {
   private String terminal="";//Terminal por el cual se conecta el arduino
   private int frecuency=9600;
   private PanamaHitek_Arduino connection ;
   
   /**
    * Constructor de la clase Arduino Connection
    * @param _terminal Terminal por el cual esta conectado el Arduino p.e. COM1
    * @param frecuency frecuencia de la conexión 
    */
   public ArduinoConnection(String _terminal,int _frecuency){
       terminal=_terminal;
       frecuency=_frecuency;
       connection=new PanamaHitek_Arduino();
   }
    
   /**
   * Metodo que envia dos caracteres al ardunio para ser representados
   * @param represent caracteres que serán representados en el arduino
   */
   public void sendData(String[][] represent){
       
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
