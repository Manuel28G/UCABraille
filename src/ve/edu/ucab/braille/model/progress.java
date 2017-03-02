/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.braille.model;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javax.swing.SwingWorker;

/**
 *
 * @author Manuel Goncalves Lopez
 */
public class progress extends SwingWorker<Integer, String> {

    private ProgressBar progress;
    private TextArea textArea;
    private double total;
    private double addProgress=0.0;
    
    public progress(ProgressBar _progress, TextArea _textArea,double _total){
        progress=_progress;
        textArea=_textArea;
        total=_total;
    }
    
    @Override
    protected Integer doInBackground() throws Exception {
        progress.setProgress(total);
        return 0;
    }
    
}
