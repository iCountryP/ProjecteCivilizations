package m3;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

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
		PanelMenu panelMenu = new PanelMenu(panelJuego, miCivilizacion, objetoMain);
		panelMenu.setBounds(600,0, 200,600);
		panelJuego.setMenu(panelMenu);
		
		add(panelJuego);
		add(panelMenu);
		setVisible(true);
		
		// Timer que aumenta y actualiza los recursos (cambiar el primer parametro para aumentar los ms)
		Timer reloj = new Timer(100000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        miCivilizacion.aumentoRecursos();
		        panelMenu.actualizarRecursos();
		    }
		});
		reloj.start();
		objetoMain.createEnemyArmy();
		
		// metodo nuevo que sustituye al reloj antiguo de invasion y que implementa una version con temporizador para que se pueda ver. acciona la batalla y crea.
		Timer relojTiempoRestanteInvasion = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if(panelMenu.getTiempoRestante() > 0) {
		            int minutos = panelMenu.getTiempoRestante() / 60;
		            int segundos = panelMenu.getTiempoRestante() % 60;
		            String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);
		            if(panelMenu.getTiempoRestante() == 60) {
		                objetoMain.viewThreat(tiempoFormateado);
		                JOptionPane.showMessageDialog(null, objetoMain.viewThreat(tiempoFormateado), "¡INVASIÓN INMINENTE!", JOptionPane.WARNING_MESSAGE);
		            }
		            panelMenu.setTiempoRestante(panelMenu.getTiempoRestante() - 1);
		            panelMenu.getProximoAtaque().setText("Próximo Ataque: " + tiempoFormateado);
		            
		        } 
		        // cuando el tiempo se acaba
		        else {
		            panelMenu.setTiempoRestante(180);
		            Battle batalla = new Battle(miCivilizacion.getArmy(), objetoMain.getEnemyArmy());
		            batalla.startBattle();

		            objetoMain.setTopeComida(objetoMain.getTopeComida() + (objetoMain.getTopeComida() * ENEMY_FLEET_INCREASE / 100));
		            objetoMain.setTopeHierro(objetoMain.getTopeHierro() + (objetoMain.getTopeHierro() * ENEMY_FLEET_INCREASE / 100));
		            objetoMain.setTopeMadera(objetoMain.getTopeMadera() + (objetoMain.getTopeMadera() * ENEMY_FLEET_INCREASE / 100));
		            
		            objetoMain.setComidaEnemigo(objetoMain.getTopeComida());
		            objetoMain.setHierroEnemigo(objetoMain.getTopeHierro());
		            objetoMain.setMaderaEnemigo(objetoMain.getTopeMadera());
		            
		            objetoMain.createEnemyArmy();
		        }
		    }
		});
		relojTiempoRestanteInvasion.start(); 
		
	}
	
	
	
}
