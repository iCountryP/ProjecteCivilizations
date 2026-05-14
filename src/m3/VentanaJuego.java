package m3;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaJuego extends JFrame implements Variables {
	private Main objetoMain = new Main();
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
		Timer reloj = new Timer(10, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        miCivilizacion.aumentoRecursos();
		        panelMenu.actualizarRecursos();
		    }
		});
		reloj.start();
		objetoMain.createEnemyArmy();
		
		Timer relojInvasion = new Timer(30000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Battle batalla = new Battle(miCivilizacion.getArmy(), objetoMain.getEnemyArmy());
				batalla.startBattle();
				objetoMain.setComidaEnemigo(FOOD_BASE_ENEMY_ARMY + (FOOD_BASE_ENEMY_ARMY * ENEMY_FLEET_INCREASE / 100));
				objetoMain.setHierroEnemigo(IRON_BASE_ENEMY_ARMY + (IRON_BASE_ENEMY_ARMY * ENEMY_FLEET_INCREASE / 100));
				objetoMain.setMaderaEnemigo(WOOD_BASE_ENEMY_ARMY + (WOOD_BASE_ENEMY_ARMY * ENEMY_FLEET_INCREASE / 100));
				objetoMain.createEnemyArmy();
			}
		});
		relojInvasion.start();
		
		
	}
	
	
	
}
