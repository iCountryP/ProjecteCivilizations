package m3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class PanelInicio extends JPanel {

	private BufferedImage menuInicial;
	private JButton nuevaPartida, cargarPartida;
	private int buttonFontSize = 18;
	
	public PanelInicio() {
		super();
		setLayout(null);
		
		// Imagen Menú
    	try {
    		menuInicial = ImageIO.read(new File("./src/m3/menu_inicial.jpg"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	// Diseño de los botones
    	ImageIcon buttonDesign = new ImageIcon("./src/m3/boton_inicial.png"); 
    	
    	// Nueva Partida
    	nuevaPartida = new JButton("Nueva Partida");
    	nuevaPartida.setBounds(445,180,200,50);
    	nuevaPartida.setFont(new Font("Arial", Font.BOLD, buttonFontSize));
    	nuevaPartida.setIcon(buttonDesign);
    	nuevaPartida.setHorizontalTextPosition(SwingConstants.CENTER);
    	nuevaPartida.setVerticalTextPosition(SwingConstants.CENTER);
    	nuevaPartida.setContentAreaFilled(false); // poner false
    	nuevaPartida.setFocusPainted(false); // poner false
    	nuevaPartida.setForeground(new Color(0xF5F1EA));
    	// Accion nueva partida
    	nuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Has creado una nueva partida");
				String name = JOptionPane.showInputDialog(null, "Introduce el nombre de tu civilización:", "Nueva partida", JOptionPane.PLAIN_MESSAGE);

				// Si el usuario pulsa Cancelar, nombre será null
				if (name != null && !name.trim().isEmpty()) {
					name = name.trim();
				    if (name.length() > 50) {
				        // Advertencia
				        JOptionPane.showMessageDialog(null, "El nombre no puede superar los 50 caracteres.", "Advertencia", JOptionPane.WARNING_MESSAGE);
				    } else {
						// Cerrar la ventana actual
						Window window = SwingUtilities.getWindowAncestor(nuevaPartida);
						window.dispose();
						
						// Abrir el juego
						new VentanaJuego(name);
				    }
				}
			}
		});
    	add(nuevaPartida);
    	
    	// Cargar partida
    	cargarPartida = new JButton("Cargar Partida");
    	cargarPartida.setBounds(445,250,200,50);
    	cargarPartida.setFont(new Font("Arial", Font.BOLD, buttonFontSize));
    	cargarPartida.setIcon(buttonDesign);
    	cargarPartida.setHorizontalTextPosition(SwingConstants.CENTER);
    	cargarPartida.setVerticalTextPosition(SwingConstants.CENTER);
    	cargarPartida.setContentAreaFilled(false); // poner false
    	cargarPartida.setFocusPainted(false); // poner false
    	cargarPartida.setForeground(new Color(0xF5F1EA));
    	// Accion nueva partida
    	cargarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Has cargado partida");
			}
		});
    	add(cargarPartida);
    	
    	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(menuInicial, 0, 0, 700, 400, this);		
	}
	
}
