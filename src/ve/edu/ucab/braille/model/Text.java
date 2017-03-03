/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import ve.edu.ucab.braille.controller.util.Layer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import sun.rmi.server.Util;
import ve.edu.ucab.braille.controller.util;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class Text extends Document{

    public List<Document> childrenList;
    private String text;
    private int idGeneral;
    private int id;
    private boolean searchReverse=false;//variable para complementar la logia de busqueda inversa
   
    
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
    private void getRange(Document _document){
            int min=_document.getId();
            int max=_document.getId();
            if(this.layer!=Layer.WORD){
               min=_document.getMinRangeChild();
               max=_document.getMaxRangeChild();
            }
            
            if(min<getMinRangeChild()){
                setMinRangeChild(min);
            }
            
            if(max>getMaxRangeChild()){
                setMaxRangeChild(max);
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
            getRange(_document);
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
        ListIterator li = childrenList.listIterator();
        while(li.hasNext()) {
            Document document=(Document)li.next();

           if((document.getMinRangeChild()<=(super.getFocusIdChild().getId()+1) &&
              document.getMaxRangeChild()>=(super.getFocusIdChild().getId()+1) )){
               if(document.layer==_layer && (document.getMinRangeChild()!=getFocusIdChild().getId()+1) && document.getMinRangeChild()!= document.getMaxRangeChild()){
                   
                   setFocusIdChild(new Letter(' ',document.getMaxRangeChild()));//se crea una letra vacia con la posición que se esta buscando
               }
               else
               {
                        response=document.getNext(_layer);
                   
                  if(response!=null){
                      
                            if(_layer!=Layer.LETTER &&(response.getText().equals(util.SEPARATOR_JUMP)|| response.getText().equals(util.SEPARATOR_SPACE))){
                             this.setFocusIdChild(new Letter(' ', response.getId()));
                             response=null;
                            }
                            else
                            {
                                 break;
                            }
                        }
                   
               }
           }
           
       } 
       
       return response;
       
         }

    @Override
    public Document getPrevious(Layer _layer) {

    Document response=null;
    ListIterator li = childrenList.listIterator(childrenList.size());

    while(li.hasPrevious()) {
        Document document=(Document)li.previous();

           if(document.getMinRangeChild() <=(super.getFocusIdChild().getId()-1) &&
              document.getMaxRangeChild()>=(super.getFocusIdChild().getId() -1 )){
               if(document.layer==_layer && (document.getMaxRangeChild()!=getFocusIdChild().getId()-1)){

                       setFocusIdChild(new Letter(' ',document.getMinRangeChild()));//se crea una letra vacia con la posición que se esta buscando
                 
               }
               else
               {
                
                   if(document.layer==_layer){
                          setFocusIdChild(new Letter(' ',document.getMinRangeChild()+1));//se crea una letra vacia con la posición que se esta buscando
                 
                   }
                   response=document.getPrevious(_layer);
                   
                      if(response!=null){
                      
                            if(_layer!=Layer.LETTER &&(response.getText().equals(util.SEPARATOR_JUMP)|| response.getText().equals(util.SEPARATOR_SPACE))){
                             this.setFocusIdChild(new Letter(' ', response.getId()));
                             response=null;
                            }
                            else
                            {
                                 break;
                            }
                        }
                   
               }
           
           }
           
           
        }
//        searchReverse=false;
        return response;
     }
}


