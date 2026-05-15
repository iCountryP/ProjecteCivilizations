package m3;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelInicio extends JPanel {

	private BufferedImage menuInicial;
	
	public PanelInicio() {
		super();
		setLayout(null);
		
		// Imagen Menú
    	try {
    		menuInicial = ImageIO.read(new File("./src/m3/menu_inicial.jpg"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(menuInicial, 0, 0, 700, 400, this);		
	}
	
}
