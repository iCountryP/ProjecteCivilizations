package m3;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class VentanaJuego extends JFrame implements Variables {
	private Main objetoMain = new Main();
	private Civilization civilization;
	private GestorSonido sonido = new GestorSonido();
	private Timer relojTiempoRestanteInvasion;
	
	// Constructor para nuevo juego
	public VentanaJuego(String new_name) {
		super();
		this.civilization = new Civilization(new_name);
		DatabaseUtils.saveNewCivilization(civilization);
		System.out.println("ID de la nueva civilización generada: "+this.civilization.getID());
		this.initializeGame();
	}
	
	public VentanaJuego(int game_id) {
		super();
		this.civilization = DatabaseUtils.loadCivilization(game_id);
		System.out.println("Cargando la civilización con la id: "+this.civilization.getID());
		this.initializeGame();
	}
	
	public void initializeGame() {
		setLayout(null); // No usamos ningun layout. Lo ponemos libre y lo posicionamos todos a posiciones exactas. 
		setBounds(75,100,1414,637);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Dominion");
		ImageIcon img = new ImageIcon("./src/m3/logo.png"); // Imagen logotipo del juego
		setIconImage(img.getImage()); // Establecemos el icono
		setResizable(false); // No se resizea (asi no se nos rompe la estética)
		PanelJuego panelJuego = new PanelJuego(this.civilization); 
		panelJuego.setBounds(0, 0, 600, 600);
		PanelMenu panelMenu = new PanelMenu(panelJuego, this.civilization, objetoMain);
		panelMenu.setBounds(600,0, 800,600);
		panelJuego.setMenu(panelMenu);
		
		add(panelJuego);
		add(panelMenu);
		this.civilization.setPanelMenu(panelMenu);
		sonido.reproducirMusica();
		setVisible(true);
		
		// Timer que aumenta y actualiza los recursos (cambiar el primer parametro para aumentar los ms)
		Timer reloj = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        civilization.aumentoRecursos();
		        panelMenu.actualizarRecursos();
		    }
		});
		reloj.start();
		objetoMain.createEnemyArmy();
		
		// metodo nuevo que sustituye al reloj antiguo de invasion y que implementa una version con temporizador para que se pueda ver. acciona la batalla y crea.
		relojTiempoRestanteInvasion = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if(panelMenu.getTiempoRestante() > 0) {
		            int minutos = panelMenu.getTiempoRestante() / 60;
		            int segundos = panelMenu.getTiempoRestante() % 60;
		            String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);
		            if(panelMenu.getTiempoRestante() == 60) {
		            	sonido.reproducirInvasion();
		            	reloj.stop();
		                JOptionPane.showMessageDialog(null, objetoMain.viewThreat(tiempoFormateado), "¡INVASIÓN INMINENTE!", JOptionPane.INFORMATION_MESSAGE);
						panelMenu.getAreaConsola().append(objetoMain.viewThreat(tiempoFormateado));
						reloj.start();
		            }
		            panelMenu.setTiempoRestante(panelMenu.getTiempoRestante() - 1);
		            panelMenu.getProximoAtaque().setText("Próximo Ataque: " + tiempoFormateado);
		            
		        } 
		        // cuando el tiempo se acaba
		        else {
		            panelMenu.setTiempoRestante(180);
		            Battle batalla = new Battle(civilization.getArmy(), objetoMain.getEnemyArmy());
		            
		            batalla.startBattle();
		            // si perdemos la batalla :
		            if(batalla.isGameOver()) {
		            	// si la batalla se pierde, paramos los relojes
		            	reloj.stop();
		            	relojTiempoRestanteInvasion.stop();

		            	// limpiamos todasl as ventanas
		            	getContentPane().removeAll();

		            	PanelGameOver pantallaFinal = new PanelGameOver();
		            	pantallaFinal.setBounds(0, 0, 1414, 637); // definimos tamaño final game over
		            	add(pantallaFinal);

		            	// hacemos un repaint y un revalidate pra que coja bien la siguiente ventana añadida
		            	revalidate();
		                repaint();
		            } else { 
			            int maderaRecuperada = batalla.getWaste()[0]; 
			            int hierroRecuperado = batalla.getWaste()[1];
			            civilization.addWood(maderaRecuperada);
			            civilization.addIron(hierroRecuperado);
			            
			            panelMenu.getAreaConsola().append("Has recuperado " +maderaRecuperada + " de madera y " + hierroRecuperado + " de hierro de los escombros\n");
			            String reporte = batalla.getBattleReport(1);
			            String battleDevelopment = batalla.getBattleDevelopment();
			            panelMenu.mostrarConsola(battleDevelopment);
			            panelMenu.mostrarConsola(reporte);

			            
			            objetoMain.setTopeComida(objetoMain.getTopeComida() + (objetoMain.getTopeComida() * ENEMY_ARMY_INCREASE / 100));
			            objetoMain.setTopeHierro(objetoMain.getTopeHierro() + (objetoMain.getTopeHierro() * ENEMY_ARMY_INCREASE / 100));
			            objetoMain.setTopeMadera(objetoMain.getTopeMadera() + (objetoMain.getTopeMadera() * ENEMY_ARMY_INCREASE / 100));
			            
			            objetoMain.setComidaEnemigo(objetoMain.getTopeComida());
			            objetoMain.setHierroEnemigo(objetoMain.getTopeHierro());
			            objetoMain.setMaderaEnemigo(objetoMain.getTopeMadera());
			            
			            objetoMain.createEnemyArmy();
		            }

		        }
		    }
		});
		relojTiempoRestanteInvasion.start(); 
		
	}
	
}
