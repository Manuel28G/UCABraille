/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.controller;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class Util {
    
    public static enum Layer{
        LETTER,
        WORD,
        PARAGRAPH,
        PAGE
    }      
    
        public static String SEPARATOR_JUMP="\n";
        public static String SEPARATOR_SPACE=" ";
        public static int charPerPage=800;
        public static String tipography="";
        public static int fontSize=0;
        public static final char lineJump='\n';
        public static final char carReturn='\r';
        public static String colorOfHighlight="Yellow";
        public static final char lineSeparator=' ';
        public static final SimpleDateFormat DATA_FORMAT=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        public static File configurationRute = new File("src/resource/config.json");

        public static String PLAY_AUTOMATIC_READ = "Iniciar lectura";
        public static String PAUSE_AUTOMATIC_READ = "Detener lectura";
}
