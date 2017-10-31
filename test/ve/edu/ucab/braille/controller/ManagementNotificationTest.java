package ve.edu.ucab.braille.controller;

import org.junit.Test;


public class ManagementNotificationTest {


	@Test (expected = java.lang.IllegalStateException.class)
	public void automaticReadEnd() {
		ManagementNotification.playAutomaticReadEndVoice();
	}
	

	@Test (expected = java.lang.IllegalStateException.class)
	public void automaticReadStart() {
		ManagementNotification.playAutomaticReadStartVoice();
	}

	@Test (expected = java.lang.IllegalStateException.class)
	public void documentLoad() {
		ManagementNotification.playDocumentLoadVoice();
	}

	@Test (expected = java.lang.IllegalStateException.class)
	public void initialLetter() {
		ManagementNotification.playInitialLetterVoice();
	}

	@Test (expected = java.lang.IllegalStateException.class)
	public void readContinue() {
		ManagementNotification.playReadContinueVoice();
	}

	@Test (expected = java.lang.IllegalStateException.class)
	public void readEnd() {
		ManagementNotification.playReadEndVoice();
	}
	
	@Test (expected = java.lang.IllegalStateException.class)
	public void welcome() {
		ManagementNotification.playWelcomeVoice();
	}
	
}
