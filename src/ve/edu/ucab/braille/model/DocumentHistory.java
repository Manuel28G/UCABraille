/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class DocumentHistory {
    
    private String lasRead;
    private int finalCharacter;
    private String nameOfFile;
    private String extensionFile;
  
    public DocumentHistory(){
        
    }

    public String getLasRead() {
        return lasRead;
    }

    public void setLasRead(String lasRead) {
        this.lasRead = lasRead;
    }

    public int getFinalCharacter() {
        return finalCharacter;
    }

    public void setFinalCharacter(int finalCharacter) {
        this.finalCharacter = finalCharacter;
    }

    public String getNameOfFile() {
        return nameOfFile;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }

    public String getExtensionFile() {
        return extensionFile;
    }

    public void setExtensionFile(String extensionFile) {
        this.extensionFile = extensionFile;
    }
    
    
    
}
