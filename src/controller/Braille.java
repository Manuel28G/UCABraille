package controller;

import java.util.HashMap;
import java.util.Map;

public class Braille {

	public static final byte[] emptyArray=new byte[]{0,0,0,0,0,0};
        private final int a=1, b=5, c=3,d=11,e=9,f=7,g=15,h=13,i=6,j=14;
	private final int k=17, l=21, m=19,n=11,ñ=-1,o=27,p=23,q=31,r=29,s=22,t=30;
	private final int u=49, v=53,w=-1,x=51,y=59,z=57;
        
        private static final Map<String,byte[][]> stringToBraille=new HashMap<>();
	
        
        public Braille()
        {
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
            stringToBraille.put("z", new byte[][]{emptyArray,{1,0,1,1,1,0}});
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
            stringToBraille.put("Z", new byte[][]{{0,0,1,1,1,0},{1,0,1,1,1,0}});
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
            stringToBraille.put("é", new byte[][]{emptyArray,{0,1,1,1,0,1}});
            stringToBraille.put("í", new byte[][]{emptyArray,{0,0,1,1,0,0}});
            stringToBraille.put("ó", new byte[][]{emptyArray,{0,0,1,1,0,1}});
            stringToBraille.put("ú", new byte[][]{emptyArray,{0,1,1,1,1,1}});
            //vocales con dieresis
            
            stringToBraille.put("ä", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("ë", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("ï", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("ö", new byte[][]{emptyArray,emptyArray});
            stringToBraille.put("ü", new byte[][]{emptyArray,{1,1,0,0,1,1}});
 
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
            stringToBraille.put("@", new byte[][]{emptyArray,{0,0,1,1,1,0}});
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
}
