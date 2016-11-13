/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Braille;
import java.util.Arrays;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class Letter implements IDocument {

    private char text;
    private int id;
    private static int idGeneral=0;
    private static Braille brailleConvert;
    
    public Letter(){
        text=' ';
        id=0;
        idGeneral=0;
        brailleConvert= new Braille();
    }
    public Letter(char _letter,int _id){
        text=_letter;
        id=_id;
        idGeneral++;
        brailleConvert= new Braille();
    }
    
    @Override
    public String getText() {
        return ""+text;  
    }

    @Override
    public int getId() {
        return id;    
    }

    @Override
    public int getGeneralId() {
        return idGeneral;
    }
    
    
    public byte[][] toBraille(){
        byte[][] request=brailleConvert.getBraille(""+text);
        System.out.println("model.Letter.toBraille()-"+Arrays.toString(request[0]));
        System.out.println("model.Letter.toBraille()-"+Arrays.toString(request[1]));
        System.out.println("model.Letter.toBraille()-"+request[0].equals(Braille.emptyArray));
        System.out.println("model.Letter.toBraille()-"+request[1].equals(Braille.emptyArray));
        return request;
    }
    
}
