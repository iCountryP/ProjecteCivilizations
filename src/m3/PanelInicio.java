package m3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelInicio extends JPanel {

	private BufferedImage menuInicial;
	private JButton nuevaPartida, continuar;
	private int buttonFontSize = 14;
	
	public PanelInicio() {
		super();
		setLayout(null);
		
		// Imagen Menú
    	try {
    		menuInicial = ImageIO.read(new File("./src/m3/menu_inicial.jpg"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	// Nueva Partida
    	nuevaPartida = new JButton("Nueva Partida");
    	nuevaPartida.setBounds(445,180,200,50);
    	nuevaPartida.setFont(new Font("Arial", Font.BOLD, buttonFontSize));
    	ImageIcon disenoDorado = new ImageIcon("./src/m3/disenoBoton.png"); 
    	nuevaPartida.setIcon(disenoDorado);
    	nuevaPartida.setHorizontalTextPosition(SwingConstants.CENTER);
    	nuevaPartida.setVerticalTextPosition(SwingConstants.CENTER);
    	nuevaPartida.setContentAreaFilled(true); // poner false
    	nuevaPartida.setFocusPainted(true); // poner false
    	nuevaPartida.setForeground(Color.BLACK);
    	// Accion nueva partida
    	nuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Has creado una nueva partida");
			}
		});
    	add(nuevaPartida);
    	
    	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(menuInicial, 0, 0, 700, 400, this);		
	}
	
}
