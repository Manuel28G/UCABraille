/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import ve.edu.ucab.braille.controller.Braille;
import ve.edu.ucab.braille.controller.util.Layer;
import java.util.Arrays;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class Letter extends Document {

    private char text;
    private int id;
    private static int idGeneral=0;
    private static Braille brailleConvert;
    
    public Letter(){
        text=' ';
        id=0;
        idGeneral=0;
        brailleConvert= new Braille();
        layer=Layer.LETTER;
        this.setMinRangeChild(id);
        this.setMaxRangeChild(id);
    }
    public Letter(char _letter,int _id){
        text=_letter;
        id=_id;
        idGeneral++;
        brailleConvert= new Braille();
        this.setMinRangeChild(id);
        this.setMaxRangeChild(id);
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

    @Override
    public void setId(int _id) {
         id=_id; 
    }

    @Override
    public Document getNext(Layer layer) {
        System.out.println("Letter: "+this.getText());
        System.out.println("Id from Letter: "+id);
        System.out.println("Id search: "+(this.getFocusIdChild().getId()+1));
        if(this.id==this.getFocusIdChild().getId()+1){
            this.setFocusIdChild(this);
            return this;}
        else{
            return null;}
    }

    @Override
    public Document getPrevious(Layer layer) {
        System.out.println("Letter: "+this.getText());
        System.out.println("Id from Letter: "+id);
        System.out.println("Id search: "+(this.getFocusIdChild().getId()-1));
    if(this.id==(this.getFocusIdChild().getId()-1)){
            this.setFocusIdChild(this);
            return this;}
        else{
            return null;}
    }
    
}
