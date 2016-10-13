package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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


	public static String readDocument(String filePath) throws IOException{

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
			System.out.println("Extension de documento no soportada"); break;
		}



		return textDocument;
	}

	/**
	 * Lectura de documentos word .doc de la version < 2007
	 * @param filePath ruta del archivo word a leer
	 * @throws IOException
	 */
	private static void readWord(String filePath) throws IOException{
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
	private static void readXWord(String filePath) throws IOException{
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
	private static void readPDF(String filePath) throws IOException {
		int pageIni=1; 
		int pageEnd=1;
		PDDocument doc =PDDocument.load(new File(filePath)); // document

		PDFTextStripper reader = new PDFTextStripper();
		reader.setStartPage(pageIni);
		reader.setEndPage(pageEnd);
		textDocument=reader.getText(doc);

	}

	private static void readTxt(String filePath) throws IOException{
		String cadena;
		FileInputStream fis=new FileInputStream( filePath ) ;
		InputStreamReader isr=new InputStreamReader(fis,"UTF8");
		BufferedReader br = new BufferedReader(isr);
		while((cadena=br.readLine())!=null){
			textDocument+="\n"+cadena;
		}
		br.close();
	}
}
