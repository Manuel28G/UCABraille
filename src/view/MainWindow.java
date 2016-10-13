package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class MainWindow {
    
	static Highlighter hilit;
    static Highlighter.HighlightPainter painter;

	public static void main(String [] args) throws BadLocationException {
		
		
        hilit = new DefaultHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
        
		
		
		JFrame window=new JFrame();
		window.getContentPane().setLayout(new FlowLayout());
		System.out.println(Arrays.toString("d".getBytes()));
		JTextArea ta=new JTextArea();
		ta.setText("Hello world!");
		ta.setColumns(50);
        ta.setLineWrap(true);
        ta.setRows(5);
        ta.setWrapStyleWord(true);
        ta.setEditable(false); 
        ta.setHighlighter(hilit);
        
        JScrollPane scroll;
        scroll = new JScrollPane(ta);
		window.getContentPane().add(scroll);
		window.setSize(10000, 10000);
		window.setVisible(true);
		}

	
	
}
