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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelMenu extends JPanel {
	private JLabel madera, comida, hierro, mana, nivelTecnologiaAtaque, nivelTecnologiaDefensa, reclutarTitulo;
	private JButton crearGranja, crearCarpinteria, crearHerreria, crearTorreMagica, crearIglesia;
	private JButton mejorarTecnologiaAtaque, mejorarTecnologiaDefensa;
	private BufferedImage menu;
	private int tamanoFuenteBotones = 14;
	private int edificioSeleccionado;
	private Civilization civilizacion;
	private JComboBox<String> selectorTropas;
	private JTextField cajaCantidadTropas;
	private JButton btnReclutar, btnVerTropas;
	
	// Constructor
	public PanelMenu(PanelJuego mapaJuego, Civilization c) {
		super();
		setLayout(null);
		this.civilizacion = c;
		// Imagen Menú
    	try {
    		menu = ImageIO.read(new File("./src/m3/menu.png"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	// Etiqueta Tecnologia Ataque (esto es lo que muestra los stats en el menú. La cantidad de nivelTecnologiaAtaque disponible)
    	nivelTecnologiaAtaque = new JLabel("Nv. Atk: 0");
    	nivelTecnologiaAtaque.setBounds(15,60,150,30);
    	nivelTecnologiaAtaque.setFont(new Font("Arial", Font.BOLD, 16));
    	nivelTecnologiaAtaque.setForeground(Color.WHITE);
    	add(nivelTecnologiaAtaque);
    	
    	// Boton Subir Tecnologia Atk
    	mejorarTecnologiaAtaque = new JButton("+");
    	mejorarTecnologiaAtaque.setBounds(15,85,75,20);
    	mejorarTecnologiaAtaque.setFont(new Font("Arial", Font.BOLD, 14));
    	mejorarTecnologiaAtaque.setForeground(Color.BLACK);
    	mejorarTecnologiaAtaque.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			civilizacion.upgradeTechnologyAttack();
    			actualizarRecursos();
    		}
    	});
    	add(mejorarTecnologiaAtaque);
    	
    	
    	// Etiqueta nivelTecnologiaDefensa (esto es lo que muestra los stats en el menú. La cantidad de nivelTecnologiaDefensa disponible)
    	nivelTecnologiaDefensa = new JLabel("Nv. Def: 0");
    	nivelTecnologiaDefensa.setBounds(110,60,150,30);
    	nivelTecnologiaDefensa.setFont(new Font("Arial", Font.BOLD, 16));
    	nivelTecnologiaDefensa.setForeground(Color.WHITE);
    	add(nivelTecnologiaDefensa);

    	// Boton Subir Tecnologia Def
    	mejorarTecnologiaDefensa = new JButton("+");
    	mejorarTecnologiaDefensa.setBounds(110,85,75,20);
    	mejorarTecnologiaDefensa.setFont(new Font("Arial", Font.BOLD, 14));
    	mejorarTecnologiaDefensa.setForeground(Color.BLACK);
    	mejorarTecnologiaDefensa.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			civilizacion.upgradeTechnologyDefense();
    			actualizarRecursos();
    		}
    	});
    	add(mejorarTecnologiaDefensa);
    	
    	
    	
    	// Etiqueta Madera (esto es lo que muestra los stats en el menú. La cantidad de madera disponible)
    	madera = new JLabel("Madera: "+ civilizacion.getWood());
    	madera.setBounds(15,120,150,30);
    	madera.setFont(new Font("Arial", Font.BOLD, 16));
    	madera.setForeground(Color.WHITE);
    	add(madera);
    	
    	// Etiqueta comida (esto es lo que muestra los stats en el menú. La cantidad de comida disponible)
    	comida = new JLabel("Comida: "+ civilizacion.getFood());
    	comida.setBounds(15,140,150,30);
    	comida.setFont(new Font("Arial", Font.BOLD, 16));
    	comida.setForeground(Color.WHITE);
    	add(comida);
    	
    	// Etiqueta hierro (esto es lo que muestra los stats en el menú. La cantidad de hierro disponible)
    	hierro = new JLabel("Hierro: "+ civilizacion.getIron());
    	hierro.setBounds(15,160,150,30);
    	hierro.setFont(new Font("Arial", Font.BOLD, 16));
    	hierro.setForeground(Color.WHITE);
    	add(hierro);
    	
    	
    	// Etiqueta hierro (esto es lo que muestra los stats en el menú. La cantidad de hierro disponible)
    	mana = new JLabel("Mana: "+ civilizacion.getMana());
    	mana.setBounds(15,180,150,30);
    	mana.setFont(new Font("Arial", Font.BOLD, 16));
    	mana.setForeground(Color.WHITE);
    	add(mana);
    	
    	
    	//
    	
    	// 1. Boton Crear Granja
    	crearGranja = new JButton("Crear Granja");
    	crearGranja.setBounds(15,220,170,30);
    	crearGranja.setFont(new Font("Arial", Font.BOLD, tamanoFuenteBotones));
    	ImageIcon diseñoDorado = new ImageIcon("./src/m3/disenoBotonGranja.png"); 
    	crearGranja.setIcon(diseñoDorado);
    	crearGranja.setHorizontalTextPosition(SwingConstants.CENTER);
    	crearGranja.setVerticalTextPosition(SwingConstants.CENTER);
    	crearGranja.setBorderPainted(false);
    	crearGranja.setContentAreaFilled(false);
    	crearGranja.setFocusPainted(false);
    	crearGranja.setForeground(Color.BLACK);
    		// Establece el edificio a 1 para saber que queremos construir granja.
    	crearGranja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapaJuego.setEdificioSeleccionadoJuego(1);
				System.out.println(mapaJuego.getEdificioSeleccionadoJuego());
			}
		});
    	add(crearGranja);
    	
    	// 2. Boton Crear Carpinteria
    	crearCarpinteria = new JButton("Crear Carpinteria");
    	crearCarpinteria.setBounds(15,260,170,30);
    	crearCarpinteria.setFont(new Font("Arial", Font.BOLD, tamanoFuenteBotones));
    	crearCarpinteria.setBackground(new Color(139, 69, 19)); // Esto es un tono marrón madera
    	crearCarpinteria.setForeground(Color.WHITE);
			// Establece el edificio a 2 para saber que queremos construir carpinteria.
    	crearCarpinteria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapaJuego.setEdificioSeleccionadoJuego(2);
				System.out.println(mapaJuego.getEdificioSeleccionadoJuego());
			}
		});
    	add(crearCarpinteria);
    	
    	// 3. Boton Crear Herreria
    	crearHerreria = new JButton("Crear Herreria");
    	crearHerreria.setBounds(15,300,170,30);
    	crearHerreria.setFont(new Font("Arial", Font.BOLD, tamanoFuenteBotones));
    	crearHerreria.setForeground(Color.BLACK);
			// Establece el edificio a 3 para saber que queremos construir herreria.
    	crearHerreria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapaJuego.setEdificioSeleccionadoJuego(3);
				System.out.println(mapaJuego.getEdificioSeleccionadoJuego());
			}
		});
    	add(crearHerreria);
    	
    	// 4. Boton Crear Torre Magica
    	crearTorreMagica = new JButton("Crear Torre Mágica");
    	crearTorreMagica.setBounds(15,340,170,30);
    	crearTorreMagica.setFont(new Font("Arial", Font.BOLD, tamanoFuenteBotones));
    	crearTorreMagica.setForeground(Color.BLACK);
			// Establece el edificio a 4 para saber que queremos construir torre magica.
    	crearTorreMagica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapaJuego.setEdificioSeleccionadoJuego(4);
				System.out.println(mapaJuego.getEdificioSeleccionadoJuego());
			}
		});
    	add(crearTorreMagica);
    	
    	// 5. Boton Crear Iglesia
    	crearIglesia = new JButton("Crear Iglesia");
    	crearIglesia.setBounds(15,380,170,30);
    	crearIglesia.setFont(new Font("Arial", Font.BOLD, tamanoFuenteBotones));
    	crearIglesia.setForeground(Color.BLACK);
		// Establece el edificio a 5 para saber que queremos construir iglesia.
    	crearIglesia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapaJuego.setEdificioSeleccionadoJuego(5);
				System.out.println(mapaJuego.getEdificioSeleccionadoJuego());
			}
		});
    	add(crearIglesia);
    	
    	
    	// ---------------- ZONA RECLUTAR TROPAS ----------------
    	
    	// Titulo Etiqueta Reclutar
    	reclutarTitulo = new JLabel("-- RECLUTAR --");
    	reclutarTitulo.setBounds(40,415,150,30);
    	reclutarTitulo.setFont(new Font("Arial", Font.BOLD, 16));
    	reclutarTitulo.setForeground(Color.WHITE);
    	add(reclutarTitulo);
    	
    	// Desplegable Reclutar
    	String[] tiposDeTropa = {"Swordsman", "Spearman", "Crossbow", "Cannon", "Arrow Tower", "Catapult", "Rocket Launcher", "Magician", "Priest"};
    	selectorTropas = new JComboBox<String>(tiposDeTropa);
    	selectorTropas.setBounds(15,445,170,30);
    	add(selectorTropas);
    	
    	// Caja cantidad de tropas
    	cajaCantidadTropas = new JTextField("1");
    	cajaCantidadTropas.setBounds(15,485,80,30); 
    	add(cajaCantidadTropas);
    	
    	// Boton Reclutar
    	btnReclutar = new JButton("Reclutar");
    	btnReclutar.setBounds(100,485,85,30); 
    	btnReclutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cantidad = Integer.parseInt(cajaCantidadTropas.getText());
					int indiceSeleccionado = selectorTropas.getSelectedIndex();
					//System.out.println("Cantidad " + cantidad);
					//System.out.println("Indice Seleccionado " + indiceSeleccionado);
					if (indiceSeleccionado == 0) {
					    civilizacion.newSwordsman(cantidad);
					} else if (indiceSeleccionado == 1) {
					    civilizacion.newSpearman(cantidad); 
					} else if (indiceSeleccionado == 2) {
					    civilizacion.newCrossbow(cantidad); 
					} else if (indiceSeleccionado == 3) {
					    civilizacion.newCannon(cantidad); 
					} else if (indiceSeleccionado == 4) {
					    civilizacion.newArrowTower(cantidad); 
					} else if (indiceSeleccionado == 5) {
					    civilizacion.newCatapult(cantidad); 
					} else if (indiceSeleccionado == 6) {
					    civilizacion.newRocketLauncher(cantidad); 
					} else if (indiceSeleccionado == 7) {
					    civilizacion.newMagician(cantidad); 
					} else if (indiceSeleccionado == 8) {
					    civilizacion.newPriest(cantidad); 
					}
				} 
				catch(Exception arg0) {
					System.out.println("Error obteniendo la cantidad.");
				};

			}
		});
    	add(btnReclutar);
    	
    	// ---------------- ZONA VER TROPAS ----------------
    	btnVerTropas = new JButton("Ver Tropas");
    	btnVerTropas.setBounds(15,525,170,30); 
		
    	btnVerTropas.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String informe = "Swordsman: " + civilizacion.getSwordsmanCount() + 
    					"\nSpearman: " + civilizacion.getSpearmanCount() + 
    					"\nCrossbow: " + civilizacion.getCrossbowCount() + 
    					"\nCannon: " + civilizacion.getCannonCount() + 
    					"\nArrow Tower: " + civilizacion.getArrowTowerCount() + 
    					"\nCatapult: " + civilizacion.getCatapultCount() + 
    					"\nRocket Launcher: " + civilizacion.getRocketLauncherCount() + 
    					"\nMagician: " + civilizacion.getMagicianCount() + 
    					"\nPriest: " + civilizacion.getPriestCount();
    			JOptionPane.showMessageDialog(null, informe, "Informe Militar", JOptionPane.INFORMATION_MESSAGE);
    		}
    		
    	});
    	add(btnVerTropas);
	} // Fin del constructor
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(menu, 0, 0, 200, 600, this);		
	}

	
	public void actualizarRecursos() {
	    madera.setText("Madera: " + civilizacion.getWood()); // Actualiza el texto del wood en el menú
	    comida.setText("Comida: " + civilizacion.getFood()); // Actualiza el texto del food en el menú
	    hierro.setText("Hierro: " + civilizacion.getIron()); // Actualiza el texto del iron en el menú
	    mana.setText("Mana: " + civilizacion.getMana()); // Actualiza el texto del mana en el menú


	    nivelTecnologiaAtaque.setText("Nv. Atk: " + civilizacion.getTechnologyAttack());
	    nivelTecnologiaDefensa.setText("Nv. Def: " + civilizacion.getTechnologyDefense());
	}
	
	
	// Getters & Setters
	public int getEdificioSeleccionado() {
		return edificioSeleccionado;
	}

	public void setEdificioSeleccionado(int edificioSeleccionado) {
		this.edificioSeleccionado = edificioSeleccionado;
	}
	
	
	
	
}
