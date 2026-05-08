package m3;

import javax.swing.JFrame;


public class VentanaJuego extends JFrame {
	// Constructor
	public VentanaJuego() {
		super();
		setLayout(null);
		setBounds(300,100,814,637);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Dominion");
		setResizable(false);
		PanelJuego panelJuego = new PanelJuego();
		panelJuego.setBounds(0, 0, 600, 600);
		PanelMenu panelMenu = new PanelMenu();
		panelMenu.setBounds(600,0, 200,600);
		add(panelJuego);
		add(panelMenu);
		setVisible(true);
	}
	
	
	
}
