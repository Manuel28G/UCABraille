package ve.edu.ucab.braille.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javafx.scene.control.RadioButton;
import ve.edu.ucab.braille.model.Document;
import ve.edu.ucab.braille.model.Letter;

public class Braille {

	public static final byte[] emptyArray=new byte[]{0,0,0,0,0,0};
        
        private static final Map<String,byte[][]> stringToBraille=new HashMap<>();
	
        //Revisar: http://www.ite.educacion.es/formacion/materiales/129/cd/unidad_5/m5_estructura_sistema.htm
        public Braille()
        {
            //Valores sin adignación
            stringToBraille.put(" ",new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("\n",new byte[][]{emptyArray,{0,0,0,0,0,0}});
            stringToBraille.put("\r",new byte[][]{emptyArray,{0,0,0,0,0,0}});
            stringToBraille.put("",new byte[][]{emptyArray,{0,0,0,0,0,0}});
            //Letras miúsculas
            stringToBraille.put("a", new byte[][]{emptyArray,{1,0,0,0,0,0}});
            stringToBraille.put("b", new byte[][]{emptyArray,{1,1,0,0,0,0}});
            stringToBraille.put("c", new byte[][]{emptyArray,{1,0,0,1,0,0}});
            stringToBraille.put("d", new byte[][]{emptyArray,{1,0,0,1,1,0}});
            stringToBraille.put("e", new byte[][]{emptyArray,{1,0,0,0,1,0}});
            stringToBraille.put("f", new byte[][]{emptyArray,{1,1,0,1,0,0}});
            stringToBraille.put("g", new byte[][]{emptyArray,{1,1,0,1,1,0}});
            stringToBraille.put("h", new byte[][]{emptyArray,{1,1,0,0,1,0}});
            stringToBraille.put("i", new byte[][]{emptyArray,{0,1,0,1,0,0}});
            stringToBraille.put("j", new byte[][]{emptyArray,{0,1,0,1,1,0}});
            stringToBraille.put("k", new byte[][]{emptyArray,{1,0,1,0,0,0}});
            stringToBraille.put("l", new byte[][]{emptyArray,{1,1,1,0,0,0}});
            stringToBraille.put("m", new byte[][]{emptyArray,{1,0,1,1,0,0}});
            stringToBraille.put("n", new byte[][]{emptyArray,{1,0,1,1,1,0}});
            stringToBraille.put("ñ", new byte[][]{emptyArray,{1,1,0,1,1,1}});
            stringToBraille.put("o", new byte[][]{emptyArray,{1,0,1,0,1,0}});
            stringToBraille.put("p", new byte[][]{emptyArray,{1,1,1,1,0,0}});
            stringToBraille.put("q", new byte[][]{emptyArray,{1,1,1,1,1,0}});
            stringToBraille.put("r", new byte[][]{emptyArray,{1,1,1,0,1,0}});
            stringToBraille.put("s", new byte[][]{emptyArray,{0,1,1,1,0,0}});
            stringToBraille.put("t", new byte[][]{emptyArray,{0,1,1,1,1,0}});
            stringToBraille.put("u", new byte[][]{emptyArray,{1,0,1,0,0,1}});
            stringToBraille.put("v", new byte[][]{emptyArray,{1,1,1,0,0,1}});
            stringToBraille.put("w", new byte[][]{emptyArray,{0,1,0,1,1,1}});
            stringToBraille.put("x", new byte[][]{emptyArray,{1,0,1,1,0,1}});
            stringToBraille.put("y", new byte[][]{emptyArray,{1,0,1,1,1,1}});
            stringToBraille.put("z", new byte[][]{emptyArray,{1,0,1,0,1,1}});
            //Letras Mayúsculas
            stringToBraille.put("A", new byte[][]{{0,0,1,1,1,0},{1,0,0,0,0,0}});
            stringToBraille.put("B", new byte[][]{{0,0,1,1,1,0},{1,1,0,0,0,0}});
            stringToBraille.put("C", new byte[][]{{0,0,1,1,1,0},{1,0,0,1,0,0}});
            stringToBraille.put("D", new byte[][]{{0,0,1,1,1,0},{1,0,0,1,1,0}});
            stringToBraille.put("E", new byte[][]{{0,0,1,1,1,0},{1,0,0,0,1,0}});
            stringToBraille.put("F", new byte[][]{{0,0,1,1,1,0},{1,1,0,1,0,0}});
            stringToBraille.put("G", new byte[][]{{0,0,1,1,1,0},{1,1,0,1,1,0}});
            stringToBraille.put("H", new byte[][]{{0,0,1,1,1,0},{1,1,0,0,1,0}});
            stringToBraille.put("I", new byte[][]{{0,0,1,1,1,0},{0,1,0,1,0,0}});
            stringToBraille.put("J", new byte[][]{{0,0,1,1,1,0},{0,1,0,1,1,0}});
            stringToBraille.put("K", new byte[][]{{0,0,1,1,1,0},{1,0,1,0,0,0}});
            stringToBraille.put("L", new byte[][]{{0,0,1,1,1,0},{1,1,1,0,0,0}});
            stringToBraille.put("M", new byte[][]{{0,0,1,1,1,0},{1,0,1,1,0,0}});
            stringToBraille.put("N", new byte[][]{{0,0,1,1,1,0},{1,0,1,1,1,0}});
            stringToBraille.put("Ñ", new byte[][]{{0,0,1,1,1,0},{1,1,0,1,1,1}});
            stringToBraille.put("O", new byte[][]{{0,0,1,1,1,0},{1,0,1,0,1,0}});
            stringToBraille.put("P", new byte[][]{{0,0,1,1,1,0},{1,1,1,1,0,0}});
            stringToBraille.put("Q", new byte[][]{{0,0,1,1,1,0},{1,1,1,1,1,0}});
            stringToBraille.put("R", new byte[][]{{0,0,1,1,1,0},{1,1,1,0,1,0}});
            stringToBraille.put("S", new byte[][]{{0,0,1,1,1,0},{0,1,1,1,0,0}});
            stringToBraille.put("T", new byte[][]{{0,0,1,1,1,0},{0,1,1,1,1,0}});
            stringToBraille.put("U", new byte[][]{{0,0,1,1,1,0},{1,0,1,0,0,1}});
            stringToBraille.put("V", new byte[][]{{0,0,1,1,1,0},{1,1,1,0,0,1}});
            stringToBraille.put("W", new byte[][]{{0,0,1,1,1,0},{0,1,0,1,1,1}});
            stringToBraille.put("X", new byte[][]{{0,0,1,1,1,0},{1,0,1,1,0,1}});
            stringToBraille.put("Y", new byte[][]{{0,0,1,1,1,0},{1,0,1,1,1,1}});
            stringToBraille.put("Z", new byte[][]{{0,0,1,1,1,0},{1,0,1,0,1,1}});
            //Numeros
            stringToBraille.put("0", new byte[][]{{0,0,1,1,1,1},{0,1,0,1,1,0}});
            stringToBraille.put("1", new byte[][]{{0,0,1,1,1,1},{1,0,0,0,0,0}});
            stringToBraille.put("2", new byte[][]{{0,0,1,1,1,1},{1,1,0,0,0,0}});
            stringToBraille.put("3", new byte[][]{{0,0,1,1,1,1},{1,0,0,1,0,0}});
            stringToBraille.put("4", new byte[][]{{0,0,1,1,1,1},{1,0,0,1,1,0}});
            stringToBraille.put("5", new byte[][]{{0,0,1,1,1,1},{1,0,0,0,1,0}});
            stringToBraille.put("6", new byte[][]{{0,0,1,1,1,1},{1,1,0,1,0,0}});
            stringToBraille.put("7", new byte[][]{{0,0,1,1,1,1},{1,1,0,1,1,0}});
            stringToBraille.put("8", new byte[][]{{0,0,1,1,1,1},{1,1,0,0,1,0}});
            stringToBraille.put("9", new byte[][]{{0,0,1,1,1,1},{0,1,0,1,0,0}});
            //vocales asentuadas
            stringToBraille.put("á", new byte[][]{emptyArray,{1,1,1,0,1,1}});
            stringToBraille.put("é", new byte[][]{emptyArray,{0,1,1,1,0,1}});//
            stringToBraille.put("í", new byte[][]{emptyArray,{0,0,1,1,0,0}});
            stringToBraille.put("ó", new byte[][]{emptyArray,{0,0,1,1,0,1}});
            stringToBraille.put("ú", new byte[][]{emptyArray,{0,1,1,1,1,1}});
            //vocales con dieresis
            stringToBraille.put("ä", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("ë", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("ï", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("ö", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("ü", new byte[][]{emptyArray,{1,1,0,0,1,1}});
            stringToBraille.put("à", new byte[][]{emptyArray,{1,1,1,0,1,1}});
            stringToBraille.put("è", new byte[][]{emptyArray,{1,1,1,0,1,1}});
            stringToBraille.put("ì", new byte[][]{emptyArray,{1,1,1,0,1,1}});
            stringToBraille.put("ò", new byte[][]{emptyArray,{1,1,1,0,1,1}});
            stringToBraille.put("ù", new byte[][]{emptyArray,{1,1,1,0,1,1}});
            //signos de puntuacion
            stringToBraille.put(".", new byte[][]{emptyArray,{0,0,1,0,0,0}});
            stringToBraille.put(",", new byte[][]{emptyArray,{0,1,0,0,0,0}});
            stringToBraille.put(":", new byte[][]{emptyArray,{0,1,0,0,1,0}});
            stringToBraille.put(";", new byte[][]{emptyArray,{0,1,1,0,0,0}});
            //interrogacion y exclamacion
            stringToBraille.put("?", new byte[][]{emptyArray,{0,1,0,0,0,1}});
            stringToBraille.put("¿", new byte[][]{emptyArray,{0,1,0,0,0,1}});
            stringToBraille.put("!", new byte[][]{emptyArray,{0,1,1,0,1,0}});
            stringToBraille.put("¡", new byte[][]{emptyArray,{0,1,1,0,1,0}});
            //Signos matematicos
            stringToBraille.put("+", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("-", new byte[][]{emptyArray,{0,1,0,0,1,0}});
            stringToBraille.put("*", new byte[][]{emptyArray,{0,0,1,0,1,0}});
            stringToBraille.put("/", new byte[][]{emptyArray,{0,0,1,1,0,0}});
            stringToBraille.put("=", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("%", new byte[][]{emptyArray,emptyArray});
            //Comilla doble y simple
            stringToBraille.put("\"", new byte[][]{emptyArray,{0,1,1,0,0,1}});
            stringToBraille.put("\'", new byte[][]{emptyArray,{0,0,1,0,0,0}});
            //Caracteres especiales
            stringToBraille.put("@", new byte[][]{emptyArray,{0,0,1,1,1,0}});//
            stringToBraille.put("#", new byte[][]{emptyArray,{0,0,1,1,1,1}});
            stringToBraille.put("$", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("&", new byte[][]{emptyArray,{1,1,1,1,0,1}});
            stringToBraille.put("(", new byte[][]{emptyArray,{1,1,0,0,0,1}});
            stringToBraille.put(")", new byte[][]{emptyArray,{0,0,1,1,1,0}});
            stringToBraille.put("´", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("}", new byte[][]{{0,0,0,0,1,0},{1,1,1,0,0,0}});
            stringToBraille.put("{", new byte[][]{{0,0,0,0,1,0},{0,0,0,1,1,1}});
            stringToBraille.put("<", new byte[][]{emptyArray,{0,1,0,1,0,1}});
            stringToBraille.put(">", new byte[][]{emptyArray,{1,0,1,0,1,0}});
            stringToBraille.put("|", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("°", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("¬", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("_", new byte[][]{emptyArray,{0,0,1,0,0,1}});
            stringToBraille.put("[", new byte[][]{emptyArray,{1,1,1,0,1,1}});
            stringToBraille.put("]", new byte[][]{emptyArray,{0,1,1,1,1,1}});
            stringToBraille.put("ç", new byte[][]{emptyArray,{1,1,1,1,0,1}});//
            stringToBraille.put("€", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("£", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("§", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("\\", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put(Character.toString ((char) 9),new byte[][]{emptyArray,emptyArray});//Tabulador 
            
        }  
        
        /**
         * 
         * @param letter
         * @return 
         */
        public byte[][] getBraille(String letter){
        if(stringToBraille.containsKey(letter)){
         byte[][] request= stringToBraille.get(letter);
         return request;
        }
	return null;
	}
        
        public void representBraille(List<RadioButton> left, List<RadioButton> right,Document letter){
            byte[][]brailleMatrix=((Letter)letter).toBraille();
            boolean first=true;
            for (byte[] brailleArray: brailleMatrix)
            {
                Iterator<RadioButton> radioBTList=left.iterator();
                if(!first){
                        radioBTList=right.iterator();
                }
                
                for(byte braille: brailleArray ){
                    RadioButton radioBT=radioBTList.next();
                    if(braille==1){
                        radioBT.setSelected(true);
                    }else
                    {
                        radioBT.setSelected(false);
                    }
                }
                first=false;
            }
            
        }
}
