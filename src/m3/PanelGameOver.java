package m3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class PanelGameOver extends JPanel {
	private JLabel textoDerrota;
	private JButton btnVolverMenu;
	private JButton btnSalir;
	private int fuenteBoton = 18;

	public PanelGameOver() {
		super();
		setLayout(null);
		setBackground(Color.BLACK); 

		// label game over en rojo bien grande
		textoDerrota = new JLabel("GAME OVER");
		textoDerrota.setBounds(0, 150, 800, 100); 
		textoDerrota.setFont(new Font("Arial", Font.BOLD, 80));
		textoDerrota.setForeground(Color.RED);
		textoDerrota.setHorizontalAlignment(SwingConstants.CENTER);
		add(textoDerrota);
		
		ImageIcon buttonDesign = new ImageIcon("./src/m3/boton_inicial.png"); // diseño boton hector

		btnVolverMenu = new JButton("Volver al Menú");
		btnVolverMenu.setBounds(300, 300, 200, 50);
		btnVolverMenu.setFont(new Font("Arial", Font.BOLD, fuenteBoton));
		btnVolverMenu.setIcon(buttonDesign);
		btnVolverMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVolverMenu.setVerticalTextPosition(SwingConstants.CENTER);
		btnVolverMenu.setContentAreaFilled(false);
		btnVolverMenu.setFocusPainted(false);
		btnVolverMenu.setForeground(Color.WHITE);
		
		// SI LE DA A VOLVER AL MENU VUELVE A LA VENTANA INICIO DONDE SE CARGA PARTIDA O SE HACE NUEVA PARTIDA
		btnVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window window = SwingUtilities.getWindowAncestor(btnVolverMenu);
				if (window != null) {
					window.dispose();
				}
				new VentanaInicio();
			}
		});
		add(btnVolverMenu);
		
		
		// SI LE DA AL BOTON DE SLAIR DEL JUEGO SE CIERRA EL JUEGO
		btnSalir = new JButton("Salir del Juego");
		btnSalir.setBounds(300, 380, 200, 50); 
		btnSalir.setFont(new Font("Arial", Font.BOLD, fuenteBoton));
		btnSalir.setIcon(buttonDesign);
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setVerticalTextPosition(SwingConstants.CENTER);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setFocusPainted(false);
		btnSalir.setForeground(Color.WHITE);

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); 
			}
		});
		add(btnSalir);
	}
}