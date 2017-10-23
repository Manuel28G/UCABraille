package ve.edu.ucab.braille.controller;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ManagementNotification {

	private final static String  path ="src/resource/audio/";
	private final static String fileType = ".mp3";
	
	public ManagementNotification() {
	}
	
	private static void playSound(String _path) {

        Media media = new Media(new File(_path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
	}
	
	public static void playWelcomeVoice() {
		playSound(path+"Bienvenido"+fileType);
	}
	
	public static void playReadEndVoice() {
		playSound(path+"LecturaFinalizada"+fileType);
	}
	
	public static void playInitialLetterVoice() {
		playSound(path+"InicioDelArchivo"+fileType);
	}
	
	public static void playAutomaticReadStartVoice() {
		playSound(path+"LecturaAutomaticaIniciada"+fileType);	
	}
	
	public static void playAutomaticReadEndVoice() {
		playSound(path+"LecturaAutomaticaFinalizada"+fileType);	
	}
	
	public static void playDocumentLoadVoice() {
		playSound(path+"DocumentoCargado"+fileType);
	}
	
	public static void playReadContinueVoice() {
		playSound(path+"LecturaContinuada"+fileType);
	}
	
}
