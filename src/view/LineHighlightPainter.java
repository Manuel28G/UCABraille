/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author luisj.gonzalez
 */
public class LineHighlightPainter {
     String revisedText = "La intefaz grafica del usuario "
            + "tambien conocida como GUI (del ingles graphical user interface)"
            + "es un programa informatico que actua de interfaz de usuario "
            + "utilizando un conjunto de imagenes y obejtos graficos para"
            + " representar la informacion y acciones disponibles en la interfaz... ";
            
    
     String token = "GUI";
    
   

    private void createAndShowGUI() {
        // título del cuadro...
        JFrame frame = new JFrame("LineHighlightPainter demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //creamos el cuadro y le damos una dimensión...
        JTextArea area = new JTextArea(9, 45);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setText(revisedText);
        
        // Resaltamos la parte del texto dentro del cuadro basado en el token...        
        highlight(area, token);

        frame.getContentPane().add(new JScrollPane(area), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private void highlight(JTextComponent textComp, String pattern) {
        // primero quitamos los resaltados anteriores...
        removeHighlights(textComp);

        try {
            Highlighter hilite = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());

            int pos = 0;
            
            while ((pos = text.indexOf(pattern, pos)) >= 0) {
                
                hilite.addHighlight(pos, pos + pattern.length(), myHighlightPainter);
                pos += pattern.length();
            }

        } catch (BadLocationException e) {
        }
    }
    
    public void removeHighlights(JTextComponent textComp) {
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();

         for (Highlighter.Highlight hilite1 : hilites) {
             if (hilite1.getPainter() instanceof MyHighlightPainter) {
                 hilite.removeHighlight(hilite1);
             }
         }
    }
    // An instance of the private subclass of the default highlight painter
    Highlighter.HighlightPainter myHighlightPainter = (Highlighter.HighlightPainter) new MyHighlightPainter(Color.red);

    // A private subclass of the default highlight painter
    class MyHighlightPainter
            extends DefaultHighlighter.DefaultHighlightPainter {

        public MyHighlightPainter(Color color) {
            super(color);
        }
    }
}
