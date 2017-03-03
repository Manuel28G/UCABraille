/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import ve.edu.ucab.braille.controller.util.Layer;


/**
 *
 * @author Manuel Goncalves Lopez
 */
public abstract class Document {
   
    
     public Layer layer;//
     private int childRangeMin;//Limite minimo del ID que cntiene entre los hijos
     private int childRangeMax;//Limite maximo del ID que cntiene entre los hijos
     private static Document childInFocus;//indice del hijo que se contiene como seleccionado para saltar al siguiente o anterior
   
     public Document(){
         childRangeMin=999999;
         childRangeMax=0;
         
         if(childInFocus==null){
            childInFocus=this;
         }
         
     }
     
     public void setFocusIdChild(Document _childInFocus){
         childInFocus=_childInFocus;
     }
     
     public Document getFocusIdChild(){
         return childInFocus;
     }
     
     public int getMaxRangeChild(){
         return childRangeMax;
     }
     
     public void setMaxRangeChild(int _id){
         childRangeMax=_id;
     }
     public void setMinRangeChild(int _id){
         childRangeMin=_id;
     }
     public int getMinRangeChild(){
         return childRangeMin;
     }
     
    /**
     * Metodo que retorna el texto contenido
     * @return texto contenido en el objeto segun el nivel
     */
    public abstract String getText();
    
    /**
     * Metodo que retorna la posición del caracter en su nivel
     * @return posición general del caracter en todo el texto 
     */
    public abstract int getId();
    
    /**
     * Metodo que retorna la posición general del caracter en todo el texto
     * @return posición general del caracter en todo el texto
     */
    public abstract int getGeneralId();
    
    /**
     * Metodo que modifica el Id correspondiente al objeto
     */
    public abstract void setId( int _id);

    /**
     * Metodo que retorna el siguiente objeto en la lista
     * @param layer el nivel que se quiere requerir el próximo objeto donde cero
     * (0) es el nivel actual
     * @return Objeto siguiente en el nivel solicitado
     */
    public abstract Document getNext(Layer _layer);
    
      /**
     * Metodo que retorna el anterior objeto en la lista
     * @param layer el nivel que se quiere requerir el próximo objeto donde cero
     * (0) es el nivel actual
     * @return Objeto anterior en el nivel solicitado
     */
    public abstract Document getPrevious(Layer _layer);
    
}
