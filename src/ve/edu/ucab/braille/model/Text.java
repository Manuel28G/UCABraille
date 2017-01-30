/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import ve.edu.ucab.braille.controller.util;
import ve.edu.ucab.braille.controller.util.Layer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class Text extends Document{

    public List<Document> childrenList;
    private String text;
    private int idGeneral;
    private int id;
   
    
    public Text(Layer _layer ){
        childrenList=new ArrayList<>();
        text="";
        idGeneral=0;
        id=0;
        layer=_layer;
    }
    
    @Override
    public String getText() {
        return text;  
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getGeneralId() {
       return this.id;
    }
    
    /**
     * Metodo para remover un hijo de la lista contenida en el nivel
     * @param _document Hijo que se desea eliminar de la lista
     * @return true: se elimino al hijo , False: no se eliminó al hijo de la lista, no se encontro o sucedio un error
     */
    public boolean removeChild(Document _document){
        if(childrenList.contains(_document)){
             childrenList.remove(_document);
             return true;
        }
        
        return false;
    }
    
    /**
     * Método que establecer el valor(ID) mínimo y máximo de los hijos contenidos en la lista 
     */
    private void getRange(){
        
        super.setMaxRangeChild(0);
        setMinRangeChild(999999999);
        for(Document document: childrenList){
            if(document.getGeneralId()<getMinRangeChild()){
                setMinRangeChild(document.getGeneralId());
            }
            
            if(document.getGeneralId()>getMaxRangeChild()){
                setMaxRangeChild(document.getGeneralId());
            }
        }
    }
    
    /**
     * Metodo para agregar un hijo de la lista contenida en el nivel
     * @param _document Hijo que se desea agregar de la lista
     * @return true: se agregar al hijo , False: no se agregó al hijo de la lista, no se encontro o sucedio un error
     */
    public boolean addChild(Document _document){
        try{
            text+=_document.getText();
            childrenList.add(_document);
            getRange();
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    /**
     * Metodo que retorna un objeto tipo IDocument de la lista contenida
     * @param _position posición donde se encuentra el hijo 
     * @return hijo encontrado en la posición,Null si ocurrió una excepción
     */
    public Document getChild(int _position){
        try{
            Document child= childrenList.get(_position);
            return child;
        }
        catch(Exception ex){
            
            return null;
        }
    }

    @Override
    public void setId(int _id) {
    id=_id;  
    }

    @Override
    public Document getNext(Layer _layer) {
     Document response=null;
       for(Document document: childrenList){
        
           if((document.getMinRangeChild()<=super.getFocusIdChild().getId() &&
              document.getMaxRangeChild()>super.getFocusIdChild().getId() )){
               response=document.getNext(_layer);
               if(response!=null){
                   break;
               }
           }
           
           
       } 
       
       return response;
       
         }

    @Override
    public Document getPrevious(Layer _layer) {
     
    Document response=null;
       for(Document document: childrenList){
           System.out.println("GetPrevious focusId - "+super.getFocusIdChild().getId());
           System.out.println("GetPrevious MinRange - "+document.getMinRangeChild());
           System.out.println("GetPrevious MaxRange - "+document.getMaxRangeChild());
           if((document.getMinRangeChild() <=(super.getFocusIdChild().getId()-1) &&
              document.getMaxRangeChild()>=(super.getFocusIdChild().getId() -1 ))){
               
               System.out.println("Entro Level - "+this.layer);
               response=document.getPrevious(_layer);
               if(response!=null){
                   break;
               }
           }
           
           
        }
        return response;
     }
}


