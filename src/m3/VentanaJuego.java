package m3;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaJuego extends JFrame {
	// Constructor
	public VentanaJuego() {
		super();
		setLayout(null);
		setBounds(300,100,814,637);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Dominion");
		setResizable(false);
		Civilization miCivilizacion = new Civilization();
		PanelJuego panelJuego = new PanelJuego(miCivilizacion);
		panelJuego.setBounds(0, 0, 600, 600);
		PanelMenu panelMenu = new PanelMenu(panelJuego, miCivilizacion);
		panelMenu.setBounds(600,0, 200,600);
		panelJuego.setMenu(panelMenu);
		
		add(panelJuego);
		add(panelMenu);
		setVisible(true);
		
		// Timer que aumenta y actualiza los recursos (cambiar el primer parametro para aumentar los ms)
		Timer reloj = new Timer(5000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        miCivilizacion.aumentoRecursos();
		        panelMenu.actualizarRecursos();
		    }
		});
		reloj.start();
	}
	
	
	
}
