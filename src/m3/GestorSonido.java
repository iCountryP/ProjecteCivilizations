package m3;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException; 

public class GestorSonido  {
	// Sonido Click
	public void reproducirClick() {
	    File sonidoBoton = new File("./src/m3/sonidoBoton.wav"); // hacemos un objeto tipo file para el sonido
	    try { // obligatorio un try catch
	        AudioInputStream stream = AudioSystem.getAudioInputStream(sonidoBoton); // le pasamos a un objeto de tipo AudioInputStream el sonido
	        Clip clip = AudioSystem.getClip(); // hacemos un objeto tipo Clip que coja el audio
	        clip.open(stream); // le pasamos el objeto AudioInputStream que tiene el sonido
	        clip.start(); // lo iniciamos
	        
	    } catch (UnsupportedAudioFileException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (LineUnavailableException e) {
	        e.printStackTrace();
	    }
	}
	
	// Sonido Construcción
	public void reproducirConstruccion() {
	    File sonidoConstruccion = new File("./src/m3/sonidoConstruccion.wav"); // hacemos un objeto tipo file para el sonido
	    try { // obligatorio un try catch
	        AudioInputStream stream = AudioSystem.getAudioInputStream(sonidoConstruccion); // le pasamos a un objeto de tipo AudioInputStream el sonido
	        Clip clip = AudioSystem.getClip(); // hacemos un objeto tipo Clip que coja el audio
	        clip.open(stream); // le pasamos el objeto AudioInputStream que tiene el sonido
	        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); // Cogemos el valor del volumen
	        gainControl.setValue(-10.0f); // le bajamos 10 decibelios en formato float
	        clip.start(); // lo iniciamos
	        
	    } catch (UnsupportedAudioFileException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (LineUnavailableException e) {
	        e.printStackTrace();
	    }
	}
	
	// BSO de fondo
	public void reproducirMusica() {
	    File musicaFondo = new File("./src/m3/dominionMainTheme.wav"); // hacemos un objeto tipo file para el sonido
	    try { // obligatorio un try catch
	        AudioInputStream stream = AudioSystem.getAudioInputStream(musicaFondo); // le pasamos a un objeto de tipo AudioInputStream el sonido
	        Clip clip = AudioSystem.getClip(); // hacemos un objeto tipo Clip que coja el audio
	        clip.open(stream); // le pasamos el objeto AudioInputStream que tiene el sonido
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        clip.start(); // lo iniciamos
	        
	    } catch (UnsupportedAudioFileException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (LineUnavailableException e) {
	        e.printStackTrace();
	    }
	}

	// Sonido al santificarse
	public void reproducirSantificado() {
	    File sonidoSantificado = new File("./src/m3/santificado.wav"); // hacemos un objeto tipo file para el sonido
	    try { // obligatorio un try catch
	        AudioInputStream stream = AudioSystem.getAudioInputStream(sonidoSantificado); // le pasamos a un objeto de tipo AudioInputStream el sonido
	        Clip clip = AudioSystem.getClip(); // hacemos un objeto tipo Clip que coja el audio
	        clip.open(stream); // le pasamos el objeto AudioInputStream que tiene el sonido
	        clip.start(); // lo iniciamos
	        
	    } catch (UnsupportedAudioFileException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (LineUnavailableException e) {
	        e.printStackTrace();
	    }
	}
}
