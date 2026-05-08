package m3;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelMenu extends JPanel {
	private JButton boton;
	private BufferedImage menu;

	public PanelMenu() {
		super();

		// Menu
    	try {
    		menu = ImageIO.read(new File("./src/m3/menu.png"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(menu, 0, 0, 200, 600, this);		
	}
}
