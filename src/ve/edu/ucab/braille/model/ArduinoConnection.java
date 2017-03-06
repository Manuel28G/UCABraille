/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

/**
 * Clase que contendrá los metodos necesarios para realizar la conexión con el dispositivo del arduino
 * @author Manuel Goncalves Lopez
 */
public class ArduinoConnection {
   private String terminal="";//Terminal por el cual se conecta el arduino
   
   public ArduinoConnection(String _terminal){
       terminal=_terminal;
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
