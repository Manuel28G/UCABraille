package ve.edu.ucab.braille.controller;

import ve.edu.ucab.braille.controller.Util.Layer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;
import ve.edu.ucab.braille.model.Configuration;
import ve.edu.ucab.braille.model.Document;
import ve.edu.ucab.braille.model.DocumentRead;
import ve.edu.ucab.braille.model.GeneralPropertie;
import ve.edu.ucab.braille.model.Letter;
import ve.edu.ucab.braille.model.Text;
import ve.edu.ucab.braille.presenter.DocumentLoad;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ReadDocument {

	private static final String PDF="pdf";
	private static final String DOCX="docx";
	private static final String DOC="doc";
	private static final String TXT="txt";
	private static String textDocument="";
	private DocumentRead document;
    private static String filePath;
    public static String documentExtesion;
    public static Timer timer = new Timer();
	private WordExtractor extractor;
	private XWPFWordExtractor file;
   
        
        public ReadDocument(String _filePath)
        {
            filePath=_filePath;
        }
        
        /**
         * Metodo que realiza la lectura de un documento de texto
         * @return Texto contenido en el documento
         * @throws IOException 
         */
	private  String readDocument(DocumentRead _documentRead) throws IOException{

		//Se crea el objeto File con la ruta del archivo
		File archivodoc = new File(filePath);

                documentExtesion=FilenameUtils.getExtension(archivodoc.getPath());
                _documentRead.setExtension(documentExtesion.toString());
                _documentRead.setTitle(archivodoc.getName());
                _documentRead.setPath(archivodoc.getPath());
                _documentRead.setSize(archivodoc.getTotalSpace());
		switch(documentExtesion)
		{
		case PDF: 
			readPDF(archivodoc.getPath(),1,1000);
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
			textDocument="";
			DocumentLoad.logger.debug(textDocument);
			break;
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
		extractor = new WordExtractor(doc2);
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
		file = new XWPFWordExtractor(doc);
		document = new DocumentRead();
//        document.setTitle(file.getExtendedProperties().getName());
//        document.setSize(Long.toString(file.length()));
        document.setExtension("WORD");
		textDocument=file.getText();


	}
	/**
	 * Lectura de documento PDF
	 * @param filePath ruta del archivo PDF a leer
	 * @throws IOException 
	 */
	private  void readPDF(String filePath,int pageIni,int pageEnd) throws IOException {
		File file = new File(filePath);
		PDDocument doc =PDDocument.load(file); // document

		PDFTextStripper reader = new PDFTextStripper();
    	document = new DocumentRead();
        document.setTitle(file.getName());
        document.setSize(Long.toString(file.length()));
        document.setExtension("PDF");
        document.setTotalLetter(pageEnd);
		reader.setStartPage(pageIni);
		reader.setEndPage(pageEnd);
		textDocument=reader.getText(doc);

	}
        
         private void readTxt(String filePath) throws IOException{
            textDocument="";
            List<String> lines=new ArrayList<>();
            File file=new File(filePath);
        	document = new DocumentRead();
            document.setTitle(file.getName());
            document.setSize(Long.toString(file.length()));
            document.setExtension("TXT");
            BufferedReader br = Files.newBufferedReader(file.toPath());
            lines = br.lines().collect(Collectors.toList());
            br.close();
            for(String line: lines){
                textDocument+=line+"\n";
            }
            
        }
         
         public Document getDocument(DocumentRead _documentRead) throws IOException, InterruptedException, InvocationTargetException{
            String request=this.readDocument(_documentRead);
            boolean lineJump=false;//validar si la ultima linea fue un salto
            char[] letters=request.toCharArray();
            Text document = null;
            if(!request.isEmpty()) {
	            document = new Text(Layer.PAGE);
	            Text paragraph=new Text(Layer.PARAGRAPH);
	            Text word=new Text(Layer.WORD);
	            int cont=0;
	            boolean lineSeparator=false;
	            for(char letter: letters){
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
	             _documentRead.setTotalLetter(cont);
	             this.document.setTotalLetter(document.getMaxRangeChild());
	             document.setDocumentRead(_documentRead);
	             Configuration.getInstance().getDocument(document);
	             Configuration.getInstance().saveConfiguration();
            }
             return document;             
         }

}
