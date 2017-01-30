/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import java.util.List;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public interface IDocument {
   
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
    

}
