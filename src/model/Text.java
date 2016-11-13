/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class Text implements IDocument{

    private List<IDocument> childrenList;
    private String text;
    private int idGeneral;
    private int id;
    
    public Text(){
        childrenList=new ArrayList<>();
        text="";
        idGeneral=0;
        id=0;
    }
    
    @Override
    public String getText() {
        return text;  
    }

    @Override
    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getGeneralId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Metodo para remover un hijo de la lista contenida en el nivel
     * @param _document Hijo que se desea eliminar de la lista
     * @return true: se elimino al hijo , False: no se eliminó al hijo de la lista, no se encontro o sucedio un error
     */
    public boolean removeChild(IDocument _document){
        if(childrenList.contains(_document)){
             childrenList.remove(_document);
             return true;
        }
        
        return false;
    }
     
    /**
     * Metodo para agregar un hijo de la lista contenida en el nivel
     * @param _document Hijo que se desea agregar de la lista
     * @return true: se agregar al hijo , False: no se agregó al hijo de la lista, no se encontro o sucedio un error
     */
    public boolean addChild(IDocument _document){
        try{
            text+=_document.getText();
            childrenList.add(_document);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    /**
     * Metodo que retorna un objeto tipo IDocument de la lista contenida
     * @param _position posición donde se encuentra el hijo 
     * @return hijo encontrado en la posición,Null si ocurrió una excepción
     */
    public IDocument getChild(int _position){
        try{
            IDocument child= childrenList.get(_position);
            return child;
        }
        catch(Exception ex){
            
            return null;
        }
    }
}
