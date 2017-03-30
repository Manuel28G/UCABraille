/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        File f = new File(jsonRute);
        System.out.println("file:"+f.exists());
        if(f.exists()){
            FileReader fr=new FileReader(f);
            System.out.println("fr:"+fr);
               System.out.println("bf:"+new BufferedReader(fr));    
            
        }
    
        
           try (Writer writer = new FileWriter(jsonRute)) {

                   Gson gson = new GsonBuilder().create();
                  String json= gson.toJson(new DocumentsRegister());
                  System.out.println("JSON:"+json);
                   gson.toJson(json, writer);
               }
    
    }
}
