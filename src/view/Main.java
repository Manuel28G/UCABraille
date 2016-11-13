/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ReadDocument;
import java.io.IOException;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class Main {
       /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       String path="C:\\Users\\manue\\Desktop\\test.txt";
       ReadDocument read=new ReadDocument(path);
       read.getDocument();
      //  launch(args);
    }
}
