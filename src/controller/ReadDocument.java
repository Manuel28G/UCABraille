package controller;

import controller.util.Layer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Document;
import model.GeneralPropertie;
import model.Letter;
import model.Text;
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
			textDocument="Extensi�n no soportada";
			System.out.println("Extension de documento no soportada"); break;
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

	private  void readTxt2(String filePath) throws IOException{
		String cadena;
		FileInputStream fis=new FileInputStream( filePath ) ;
		InputStreamReader isr=new InputStreamReader(fis,"UTF8");
		BufferedReader br = new BufferedReader(isr);
		while((cadena=br.readLine())!=null){
			textDocument+="\n"+cadena;
		}
		br.close();
                
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
         
         public Document getDocument() throws IOException{
             String request=this.readDocument();
             boolean lineJump=false;//validar si la ultima linea fue un salto
             char[] letters=request.toCharArray();
             Text document=new Text(Layer.PAGE);
             Text paragraph=new Text(Layer.PARAGRAPH);
             int cont=0;
             for(char letter: letters){
                 Document letterDocument;
                 if(letter==GeneralPropertie.lineJump){
                    
                     letterDocument=new Letter(letter,cont);
                     paragraph.addChild(letterDocument);
                     document.addChild(paragraph);
                     paragraph=new Text(Layer.PARAGRAPH);
                     lineJump=true;
                  }
                 else
                 {
                     letterDocument=new Letter(letter,cont);
                     paragraph.addChild(letterDocument);
                     lineJump=false;
                 }
                 cont++;
                 System.out.println("Letter load - id: "+letterDocument.getId());
                 System.out.println("Letter load - letter: "+letterDocument.getText());
             }
             if(!lineJump){
                 document.addChild(paragraph);
                 
             }
             return document;
             
         }
}
