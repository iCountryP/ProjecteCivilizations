package m3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelJuego extends JPanel {
	private BufferedImage texturaHierba;
	private BufferedImage texturaDesierto;
	private BufferedImage church;
	
	public PanelJuego() {
		super();
		
		// Textura Hierba
    	try {
    		texturaHierba = ImageIO.read(new File("./src/m3/texturaHierba.jpeg"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	// Textura Desierto
    	try {
    		texturaDesierto = ImageIO.read(new File("./src/m3/texturaDesierto.jpeg"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	// Textura Agua
    	try {
    		church = ImageIO.read(new File("./src/m3/church.png"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(new Color(0,0,0));
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(i <= 4 && j <= 6) {
					g2d.drawImage(texturaHierba, i*60, j*60, 60, 60, this);

				}
				else if(j >= 8 || i <= 2) {
					g2d.drawImage(texturaDesierto, i*60, j*60, 60, 60, this);
				}
				else {
					g2d.drawImage(texturaHierba, i*60, j*60, 60, 60, this);
					g2d.drawImage(church, i*60, j*60, 60, 50, this);

				}
				
				
				
				g2d.drawRect(i*60, j*60, 60, 60);
			}
		}
	}
}
