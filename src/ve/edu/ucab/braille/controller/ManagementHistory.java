/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import ve.edu.ucab.braille.model.DocumentsRegister;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class ManagementHistory {
    
    private static ManagementHistory history;
    private final String jsonRute ="src/ve/edu/ucab/braille/model/History.json";
    
    
    private ManagementHistory(){
        
    }
    
    public static ManagementHistory getInstance()
    {
        if(history==null){
            history=new ManagementHistory();
        }
        return history;
    }
    
    
    public void registerHistory(String fileRute) throws FileNotFoundException, IOException{     
           try (Writer writer = new FileWriter(jsonRute)) {
                   Gson gson = new GsonBuilder().create();
                  String json= gson.toJson(new DocumentsRegister());
                   gson.toJson(json, writer);
               }
    
    }
}
