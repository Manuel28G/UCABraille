/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class DocumentsRegister {
    
    private List<DocumentHistory> documentsRead;
    private String lastReadDate;
    private int totalDocumentsRead;
    
    public DocumentsRegister(){
        documentsRead=new ArrayList<>();
        lastReadDate="";
        totalDocumentsRead=0;
    }

	public List<DocumentHistory> getDocumentsRead() {
		return documentsRead;
	}

	public void setDocumentsRead(List<DocumentHistory> documentsRead) {
		this.documentsRead = documentsRead;
	}

	public String getLastReadDate() {
		return lastReadDate;
	}

	public void setLastReadDate(String lastReadDate) {
		this.lastReadDate = lastReadDate;
	}

	public int getTotalDocumentsRead() {
		return totalDocumentsRead;
	}

	public void setTotalDocumentsRead(int totalDocumentsRead) {
		this.totalDocumentsRead = totalDocumentsRead;
	}
    
    
}
