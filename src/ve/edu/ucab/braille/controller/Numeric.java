/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * clase que contiene metodos numéricos generales para la transformación e interpretación de caracteres
 * numéricos
 * @author Manuel Goncalves Lopez
 */
public class Numeric {
    
  /**
     * Método que redondea los decimales de una expresion no entera
     * @param value Valor decimal
     * @param places cantidad de decimales a conservar
     * @return valor decimal con la cantidad de decimales fijados 
     */
    public static double round(double value, int places){
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
