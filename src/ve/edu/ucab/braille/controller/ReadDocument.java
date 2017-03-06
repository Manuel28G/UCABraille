package ve.edu.ucab.braille.controller;

import com.sun.javafx.cursor.CursorFrame;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import ve.edu.ucab.braille.controller.util.Layer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import ve.edu.ucab.braille.model.Document;
import ve.edu.ucab.braille.model.GeneralPropertie;
import ve.edu.ucab.braille.model.Letter;
import ve.edu.ucab.braille.model.Text;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import ve.edu.ucab.braille.model.progress;
import ve.edu.ucab.braille.presenter.DocumentLoad;

public class ReadDocument {

	private static final String PDF="pdf";
	private static final String DOCX="docx";
	private static final String DOC="doc";
	private static final String TXT="txt";
	private static String textDocument="";
        private static String filePath;
        
        public ReadDocument(String _filePath)
        {
            filePath=_filePath;
        }
        
        /**
         * Metodo que realiza la lectura de un documento de texto
         * @return Texto contenido en el documento
         * @throws IOException 
         */
	private  String readDocument() throws IOException{

		//Se crea el objeto File con la ruta del archivo
		File archivodoc = new File(filePath);


		switch(FilenameUtils.getExtension(archivodoc.getPath()))
		{
		case PDF: 
			readPDF(archivodoc.getPath());
			break;

		case DOC:
			readWord(archivodoc.getPath());
			break;
                        
		case DOCX:
			readXWord(archivodoc.getPath());
			break;
                        
		case TXT:
			readTxt(archivodoc.getPath());
			break;
                        
		default:
			textDocument="Extensión no soportada";
			System.out.println("Extensión de documento no soportada"); break;
		}



		return textDocument;
	}

        
       
        
	/**
	 * Lectura de documentos word .doc de la version < 2007
	 * @param filePath ruta del archivo word a leer
	 * @throws IOException
	 */
	private  void readWord(String filePath) throws IOException{
		FileInputStream fis = new FileInputStream(filePath);
		HWPFDocument doc2 = new HWPFDocument(fis);
		WordExtractor extractor = new WordExtractor(doc2);
		textDocument=extractor.getText();

	}

	/**
	 * Lectura de documentos word .doc de la version > 2007
	 * @param filePath ruta del archivo word a leer
	 * @throws IOException
	 */
	private  void readXWord(String filePath) throws IOException{
		FileInputStream fis = new FileInputStream(filePath);
		XWPFDocument doc;
                
		doc = new XWPFDocument(fis);
		XWPFWordExtractor wordxExtractor = new XWPFWordExtractor(doc);
		textDocument=wordxExtractor.getText();


	}
	/**
	 * Lectura de documento PDF
	 * @param filePath ruta del archivo PDF a leer
	 * @throws IOException 
	 */
	private  void readPDF(String filePath) throws IOException {
		int pageIni=1; 
		int pageEnd=1;
		PDDocument doc =PDDocument.load(new File(filePath)); // document

		PDFTextStripper reader = new PDFTextStripper();
		reader.setStartPage(pageIni);
		reader.setEndPage(pageEnd);
		textDocument=reader.getText(doc);

	}
        
         private void readTxt(String filePath) throws IOException{
          
            List<String> lines=new ArrayList<>();
            File file=new File(filePath);
            BufferedReader br = Files.newBufferedReader(file.toPath());
            lines = br.lines().collect(Collectors.toList());
            br.close();
            for(String line: lines){
                textDocument+=line+"\n";
            }
            
        }
         
         public Document getDocument(ProgressBar _progress) throws IOException, InterruptedException, InvocationTargetException{
            String request=this.readDocument();
            boolean lineJump=false;//validar si la ultima linea fue un salto
            char[] letters=request.toCharArray();
            Text document=new Text(Layer.PAGE);
            Text paragraph=new Text(Layer.PARAGRAPH);
            Text word=new Text(Layer.WORD);
            int cont=0;
            progress p;
            double jump=1.0/(double)letters.length;
            double act=0.0;
            boolean lineSeparator=false;
            for(char letter: letters){
                 act+=jump;
                 System.out.println(act);
                 p=new progress(_progress, null, act);
                 p.execute();
                 Document letterDocument;
                 switch(letter){
                     case GeneralPropertie.lineJump:
                            if(!lineSeparator){
                                paragraph.addChild(word);
                             }
                            word=new Text(Layer.WORD);
                            letterDocument=new Letter(letter,cont); 
                            word.addChild(letterDocument);
                            //agregamos la palabra antes del salto de linea
                            paragraph.addChild(word);
                            document.addChild(paragraph);
                            paragraph=new Text(Layer.PARAGRAPH);
                            word=new Text(Layer.WORD); 
                            lineJump=true;
                            break;
                     case GeneralPropertie.carReturn: 
                            //Si es un CarReturn no se toma en cuenta y se omite ya que eso no es visible para el usuario final
                            cont--;
                            break;
                     case GeneralPropertie.lineSeparator :
                            lineSeparator=true;
                            paragraph.addChild(word);
                            word=new Text(Layer.WORD);
                            letterDocument=new Letter(letter,cont); 
                            word.addChild(letterDocument);
                            paragraph.addChild(word);
                            word=new Text(Layer.WORD);
                            break;
                     default:
                            lineSeparator=false;
                            letterDocument=new Letter(letter,cont); 
                            word.addChild(letterDocument);
                            break;
                }
                cont++;
             }

             if(!lineJump){
                 document.addChild(paragraph);
                 
             }
             return document;             
         }
}
