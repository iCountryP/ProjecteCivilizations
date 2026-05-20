package m3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


// ESTA CLASE GESTIONA EL PANEL COMPLETO DE MENÚ, INCLUIDA LA PARTE DE CREAR, RECLUTAR, CONSOLA, ETC.

public class PanelMenu extends JPanel implements Variables {
	// Iconos
	private ImageIcon iconoMadera = new ImageIcon(new ImageIcon("./src/m3/iconoMadera.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));    	
	private ImageIcon iconoComida = new ImageIcon(new ImageIcon("./src/m3/iconoComida.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));    	
	private ImageIcon iconoHierro = new ImageIcon(new ImageIcon("./src/m3/iconoHierro.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));    	

	private JLabel madera, comida, hierro, mana, nivelTecnologiaAtaque, nivelTecnologiaDefensa, reclutarTitulo;
	private JButton crearGranja, crearCarpinteria, crearHerreria, crearTorreMagica, crearIglesia;
	private JButton mejorarTecnologiaAtaque, mejorarTecnologiaDefensa;
	// Etiquetas Coste Tropa
			// Labels Coste (irán en el apartado de reclutar)
	private JLabel costeTropaMadera, costeTropaComida, costeTropaHierro, costeTropaMana;
	private BufferedImage menu, emperador; //Imagen del fondo del menú + emperador
	private int tamanoFuenteBotones = 14; // Controla el tamaño de la fuente de los botones
	private int edificioSeleccionado; // Controla el edificio que se selecciona cuando se clica un botón de crear
	private Civilization civilizacion; // Clase civilizacion
	private Main objetoMain; // Instanciamos el objeto MAIN para poder coger algunos métodos
	private JComboBox<String> selectorTropas; // Para controlar los indices en el desplegable de RECLUTAR
	private JTextField cajaCantidadTropas; // TextField donde se pondrá la cantidad de tropas
	private JButton btnReclutar, btnMiImperio, btnVerTropasEnemigas, btnGuardar, btnAyuda; // Botones utiles
	private int tiempoRestante = 180; // Para que sean 3 minutos.
	private JLabel proximoAtaque = new JLabel("Próximo Ataque: 03:00"); // Label que indica cuanto falta para el próximo ataque	
	// Rutas para los iconos de los materiales. el toUri().toString() lo que hace es añadir el file:// para q la etiqueta <img> lo entienda en Java
	private String rutaMadera = new File("./src/m3/iconoMadera.png").toURI().toString(); // Ruta imagen madera par ahacerla compatible con HTML
	private String rutaHierro = new File("./src/m3/iconoHierro.png").toURI().toString(); // Ruta imagen madera par ahacerla compatible con HTML
	private String rutaComida = new File("./src/m3/iconoComida.png").toURI().toString(); // Ruta imagen madera par ahacerla compatible con HTML
	private JTextArea areaConsola = new JTextArea(); // Consola
	// Labels de información
	private JLabel civilizacionName, swordsmanCantidad, spearmanCantidad, crossbowCantidad, cannonCantidad, arrowTowerCantidad, catapultCantidad, rocketLauncherCantidad, magicianCantidad, priestCantidad;
	// Gestor de sonido para controlar los sonidos
	private GestorSonido sonido = new GestorSonido();
	// Label de timer de invasion
	private JLabel relojInvasion;


	// Constructor
	public PanelMenu(PanelJuego mapaJuego, Civilization c, Main m) {
		super();
		setLayout(null);
		this.civilizacion = c;
		this.objetoMain = m;
		// Imagen Menú
    	try {
    		menu = ImageIO.read(new File("./src/m3/menu.png"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
		// Imagen Emperador
    	try {
    		emperador = ImageIO.read(new File("./src/m3/imagenEmperador.jpeg"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    	// ---------------- ZONA CONSOLA ----------------
    	areaConsola.setEditable(false); 
    	areaConsola.setBackground(Color.BLACK); 
    	areaConsola.setFont(new Font("Consolas", 0, 14));
    	areaConsola.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    	areaConsola.setForeground(Color.WHITE); 
    	JScrollPane scrollConsola = new JScrollPane(areaConsola);
		scrollConsola.setBounds(200, 0, 600, 450);
		add(scrollConsola);
    	
    	
    	
    	// usamos el imageIcon para cambiar los diseños de los botones y que no se vean tan antiguos
    	ImageIcon disenoDorado = new ImageIcon("./src/m3/disenoBoton.png"); 

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
    	mejorarTecnologiaAtaque.setIcon(disenoDorado);
    	mejorarTecnologiaAtaque.setHorizontalTextPosition(SwingConstants.CENTER);
    	mejorarTecnologiaAtaque.setVerticalTextPosition(SwingConstants.CENTER);
    	mejorarTecnologiaAtaque.setContentAreaFilled(false);
    	mejorarTecnologiaAtaque.setFocusPainted(false);
    	mejorarTecnologiaAtaque.setForeground(Color.BLACK);
    	mejorarTecnologiaAtaque.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
				sonido.reproducirClick();
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
    	mejorarTecnologiaDefensa.setIcon(disenoDorado);
    	mejorarTecnologiaDefensa.setHorizontalTextPosition(SwingConstants.CENTER);
    	mejorarTecnologiaDefensa.setVerticalTextPosition(SwingConstants.CENTER);
    	mejorarTecnologiaDefensa.setContentAreaFilled(false);
    	mejorarTecnologiaDefensa.setFocusPainted(false);
    	mejorarTecnologiaDefensa.setForeground(Color.BLACK);
    	mejorarTecnologiaDefensa.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
				sonido.reproducirClick();
    			civilizacion.upgradeTechnologyDefense();
    			actualizarRecursos();
    		}
    	});
    	add(mejorarTecnologiaDefensa);
    	
    	
    	
    	// Etiqueta Madera (esto es lo que muestra los stats en el menú. La cantidad de madera disponible)
    	madera = new JLabel("Madera: "+ civilizacion.getWood());
    	madera.setBounds(10,110,180,30);
    	madera.setFont(new Font("Arial", Font.BOLD, 15));
    	madera.setForeground(Color.WHITE);
    	madera.setIcon(iconoMadera);
    	add(madera);
    	
    	// Etiqueta comida (esto es lo que muestra los stats en el menú. La cantidad de comida disponible)
    	comida = new JLabel("Comida: "+ civilizacion.getFood());
    	comida.setBounds(10,135,180,30);
    	comida.setFont(new Font("Arial", Font.BOLD, 15));
    	comida.setForeground(Color.WHITE);
    	comida.setIcon(iconoComida);
    	add(comida);
    	
    	// Etiqueta hierro (esto es lo que muestra los stats en el menú. La cantidad de hierro disponible)
    	hierro = new JLabel("Hierro: "+ civilizacion.getIron());
    	hierro.setBounds(10,160,180,30);
    	hierro.setFont(new Font("Arial", Font.BOLD, 15));
    	hierro.setForeground(Color.WHITE);
    	hierro.setIcon(iconoHierro);
    	add(hierro);
    	
    	
    	// Etiqueta hierro (esto es lo que muestra los stats en el menú. La cantidad de hierro disponible)
    	ImageIcon iconoMana = new ImageIcon(new ImageIcon("./src/m3/iconoMana.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));    	
    	mana = new JLabel("Mana: "+ civilizacion.getMana());
    	mana.setBounds(10,185,180,30);
    	mana.setFont(new Font("Arial", Font.BOLD, 15));
    	mana.setForeground(Color.WHITE);
    	mana.setIcon(iconoMana);
    	add(mana);
    	
    	
    	//
    	
    	// 1. Boton Crear Granja
    	crearGranja = new JButton("Crear Granja");
    	crearGranja.setBounds(15,220,170,30);
    	crearGranja.setFont(new Font("Arial", Font.BOLD, tamanoFuenteBotones));
    	crearGranja.setIcon(disenoDorado);
    	crearGranja.setHorizontalTextPosition(SwingConstants.CENTER);
    	crearGranja.setVerticalTextPosition(SwingConstants.CENTER);
    	crearGranja.setContentAreaFilled(false);
    	crearGranja.setFocusPainted(false);
    	crearGranja.setToolTipText("<html>"
    		    + "<div style='margin: 5px; font-family: Arial;'>"
    		    + "<b>Coste de Granja:</b><br><br>"
    		    + "<img src='" + rutaMadera + "' width='25' height='25'> Madera: " + WOOD_COST_FARM + "<br>"
    		    + "<img src='" + rutaHierro + "' width='25' height='25'> Hierro: " + IRON_COST_FARM + "<br>"
    		    + "<img src='" + rutaComida + "' width='25' height='25'> Comida: " + FOOD_COST_FARM + "<br>"
    		    + "</div>"
    		    + "</html>");
    	crearGranja.setForeground(Color.BLACK);
    		// Establece el edificio a 1 para saber que queremos construir granja.
    	crearGranja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.reproducirClick();
				mapaJuego.setEdificioSeleccionadoJuego(1);
				areaConsola.append("Has seleccionado construir: Granja\n");
				areaConsola.append("Coste {Madera: "+ WOOD_COST_FARM + ", Comida: " + FOOD_COST_FARM + ", Hierro: " + IRON_COST_FARM + "}\n");
				areaConsola.append("*********************************************************\n");

			}
		});
    	add(crearGranja);
    	
    	// 2. Boton Crear Carpinteria
    	crearCarpinteria = new JButton("Crear Carpinteria");
    	crearCarpinteria.setBounds(15,260,170,30);
    	crearCarpinteria.setFont(new Font("Arial", Font.BOLD, tamanoFuenteBotones));
    	crearCarpinteria.setIcon(disenoDorado);
    	crearCarpinteria.setHorizontalTextPosition(SwingConstants.CENTER);
    	crearCarpinteria.setVerticalTextPosition(SwingConstants.CENTER);
    	crearCarpinteria.setContentAreaFilled(false);
    	crearCarpinteria.setFocusPainted(false);
    		// Añade que salgan los recursos si pones el raton encima
    	crearCarpinteria.setToolTipText("<html>"
    		    + "<div style='margin: 5px; font-family: Arial;'>"
    		    + "<b>Coste de Carpintería:</b><br><br>"
    		    + "<img src='" + rutaMadera + "' width='25' height='25'> Madera: " + WOOD_COST_CARPENTRY + "<br>"
    		    + "<img src='" + rutaHierro + "' width='25' height='25'> Hierro: " + IRON_COST_CARPENTRY + "<br>"
    		    + "<img src='" + rutaComida + "' width='25' height='25'> Comida: " + FOOD_COST_CARPENTRY + "<br>"
    		    + "</div>"
    		    + "</html>");
    	crearCarpinteria.setForeground(Color.BLACK);
			// Establece el edificio a 2 para saber que queremos construir carpinteria.
    	crearCarpinteria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.reproducirClick();
				mapaJuego.setEdificioSeleccionadoJuego(2);
				areaConsola.append("Has seleccionado construir: Carpinteria\n");
				areaConsola.append("Coste {Madera: "+ WOOD_COST_CARPENTRY + ", Comida: " + FOOD_COST_CARPENTRY + ", Hierro: " + IRON_COST_CARPENTRY + "}\n");
				areaConsola.append("*********************************************************\n");

			}
		});
    	add(crearCarpinteria);
    	
    	// 3. Boton Crear Herreria
    	crearHerreria = new JButton("Crear Herreria");
    	crearHerreria.setBounds(15,300,170,30);
    	crearHerreria.setFont(new Font("Arial", Font.BOLD, tamanoFuenteBotones));
    	crearHerreria.setIcon(disenoDorado);
    	crearHerreria.setHorizontalTextPosition(SwingConstants.CENTER);
    	crearHerreria.setVerticalTextPosition(SwingConstants.CENTER);
    	crearHerreria.setContentAreaFilled(false);
    	crearHerreria.setFocusPainted(false);
			// Añade que salgan los recursos si pones el raton encima
    	crearHerreria.setToolTipText("<html>"
    		    + "<div style='margin: 5px; font-family: Arial;'>"
    		    + "<b>Coste de Herrería:</b><br><br>"
    		    + "<img src='" + rutaMadera + "' width='25' height='25'> Madera: " + WOOD_COST_SMITHY + "<br>"
    		    + "<img src='" + rutaHierro + "' width='25' height='25'> Hierro: " + IRON_COST_SMITHY + "<br>"
    		    + "<img src='" + rutaComida + "' width='25' height='25'> Comida: " + FOOD_COST_SMITHY + "<br>"
    		    + "</div>"
    		    + "</html>");
    	crearHerreria.setForeground(Color.BLACK);
			// Establece el edificio a 3 para saber que queremos construir herreria.
    	crearHerreria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.reproducirClick();
				mapaJuego.setEdificioSeleccionadoJuego(3);
				areaConsola.append("Has seleccionado construir: Herreria\n");
				areaConsola.append("Coste {Madera: "+ WOOD_COST_SMITHY + ", Comida: " + FOOD_COST_SMITHY + ", Hierro: " + IRON_COST_SMITHY + "}\n");
				areaConsola.append("*********************************************************\n");


			}
		});
    	add(crearHerreria);
    	
    	// 4. Boton Crear Torre Magica
    	crearTorreMagica = new JButton("Crear Torre Mágica");
    	crearTorreMagica.setBounds(15,340,170,30);
    	crearTorreMagica.setFont(new Font("Arial", Font.BOLD, tamanoFuenteBotones));
    	crearTorreMagica.setIcon(disenoDorado);
    	crearTorreMagica.setHorizontalTextPosition(SwingConstants.CENTER);
    	crearTorreMagica.setVerticalTextPosition(SwingConstants.CENTER);
    	crearTorreMagica.setContentAreaFilled(false);
    	crearTorreMagica.setFocusPainted(false);
			// Añade que salgan los recursos si pones el raton encima
    	crearTorreMagica.setToolTipText("<html>"
		    + "<div style='margin: 5px; font-family: Arial;'>"
		    + "<b>Coste de Torre Mágica:</b><br><br>"
		    + "<img src='" + rutaMadera + "' width='25' height='25'> Madera: " + WOOD_COST_MAGICTOWER + "<br>"
		    + "<img src='" + rutaHierro + "' width='25' height='25'> Hierro: " + IRON_COST_MAGICTOWER + "<br>"
		    + "<img src='" + rutaComida + "' width='25' height='25'> Comida: " + FOOD_COST_MAGICTOWER + "<br>"
		    + "</div>"
		    + "</html>");
    	crearTorreMagica.setForeground(Color.BLACK);
			// Establece el edificio a 4 para saber que queremos construir torre magica.
    	crearTorreMagica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.reproducirClick();
				mapaJuego.setEdificioSeleccionadoJuego(4);
				areaConsola.append("Has seleccionado construir: Torre Mágica\n");
				areaConsola.append("Coste {Madera: "+ WOOD_COST_MAGICTOWER + ", Comida: " + FOOD_COST_MAGICTOWER + ", Hierro: " + IRON_COST_MAGICTOWER + "}\n");
				areaConsola.append("*********************************************************\n");


			}
		});
    	add(crearTorreMagica);
    	
    	// 5. Boton Crear Iglesia
    	crearIglesia = new JButton("Crear Iglesia");
    	crearIglesia.setBounds(15,380,170,30);
    	crearIglesia.setFont(new Font("Arial", Font.BOLD, tamanoFuenteBotones));
    	crearIglesia.setIcon(disenoDorado);
    	crearIglesia.setHorizontalTextPosition(SwingConstants.CENTER);
    	crearIglesia.setVerticalTextPosition(SwingConstants.CENTER);
    	crearIglesia.setContentAreaFilled(false);
    	crearIglesia.setFocusPainted(false);
			// Añade que salgan los recursos si pones el raton encima
    	crearIglesia.setToolTipText("<html>"
    		    + "<div style='margin: 5px; font-family: Arial;'>"
    		    + "<b>Coste de Iglesia:</b><br><br>"
    		    + "<img src='" + rutaMadera + "' width='25' height='25'> Madera: " + WOOD_COST_CHURCH + "<br>"
    		    + "<img src='" + rutaHierro + "' width='25' height='25'> Hierro: " + IRON_COST_CHURCH + "<br>"
    		    + "<img src='" + rutaComida + "' width='25' height='25'> Comida: " + FOOD_COST_CHURCH + "<br>"
    		    + "</div>"
    		    + "</html>");
    	crearIglesia.setForeground(Color.BLACK);
		// Establece el edificio a 5 para saber que queremos construir iglesia.
    	crearIglesia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sonido.reproducirClick();
				mapaJuego.setEdificioSeleccionadoJuego(5);
				areaConsola.append("Has seleccionado construir: Iglesia (Amén)\n");
				areaConsola.append("Coste {Madera: "+ WOOD_COST_CHURCH + ", Comida: " + FOOD_COST_CHURCH + ", Hierro: " + IRON_COST_CHURCH + "}\n");
				areaConsola.append("*********************************************************\n");


			}
		});
    	add(crearIglesia);
    	
    	// ---------------- ZONA RECLUTAR TROPAS ----------------
    	
    	// Titulo Etiqueta Reclutar
    	reclutarTitulo = new JLabel("-- RECLUTAR --");
    	reclutarTitulo.setBounds(40,420,150,30);
    	reclutarTitulo.setFont(new Font("Arial", Font.BOLD, 16));
    	reclutarTitulo.setForeground(Color.WHITE);
    	add(reclutarTitulo);
    	
    	// Desplegable Reclutar
    	String[] tiposDeTropa = {"Swordsman", "Spearman", "Crossbow", "Cannon", "Arrow Tower", "Catapult", "Rocket Launcher", "Magician", "Priest"};
    	selectorTropas = new JComboBox<String>(tiposDeTropa);
    	selectorTropas.setBounds(15,515,170,30);
    	// Listener que cambia los costes en funcion del desplegable
    	selectorTropas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indiceSeleccionado = selectorTropas.getSelectedIndex();
		        // IFS PARA QUE LOS COSTES SE TENGAN EN CUENTA SEGUN TU ELECCIÓN
		        if (indiceSeleccionado == 0) { // Swordsman
		            costeTropaMadera.setText(String.valueOf(WOOD_COST_SWORDSMAN));
		            costeTropaComida.setText(String.valueOf(FOOD_COST_SWORDSMAN));
		            costeTropaHierro.setText(String.valueOf(IRON_COST_SWORDSMAN));
		            costeTropaMana.setText(String.valueOf(MANA_COST_SWORDSMAN));

		            
		        } else if (indiceSeleccionado == 1) { // Spearman
		            costeTropaMadera.setText(String.valueOf(WOOD_COST_SPEARMAN));
		            costeTropaComida.setText(String.valueOf(FOOD_COST_SPEARMAN));
		            costeTropaHierro.setText(String.valueOf(IRON_COST_SPEARMAN));
		            costeTropaMana.setText(String.valueOf(MANA_COST_SPEARMAN));

		            
		        } else if (indiceSeleccionado == 2) { // Crossbow
		            costeTropaMadera.setText(String.valueOf(WOOD_COST_CROSSBOW));
		            costeTropaComida.setText(String.valueOf(FOOD_COST_CROSSBOW));
		            costeTropaHierro.setText(String.valueOf(IRON_COST_CROSSBOW));
		            costeTropaMana.setText(String.valueOf(MANA_COST_CROSSBOW));

		        } else if (indiceSeleccionado == 3) { // Cannon
		            costeTropaMadera.setText(String.valueOf(WOOD_COST_CANNON));
		            costeTropaComida.setText(String.valueOf(FOOD_COST_CANNON));
		            costeTropaHierro.setText(String.valueOf(IRON_COST_CANNON));
		            costeTropaMana.setText(String.valueOf(MANA_COST_CANNON));

		        } else if (indiceSeleccionado == 4) { // Arrow Tower
		            costeTropaMadera.setText(String.valueOf(WOOD_COST_ARROWTOWER));
		            costeTropaComida.setText(String.valueOf(FOOD_COST_ARROWTOWER));
		            costeTropaHierro.setText(String.valueOf(IRON_COST_ARROWTOWER));
		            costeTropaMana.setText(String.valueOf(MANA_COST_ARROWTOWER));

		        } else if (indiceSeleccionado == 5) { // Catapult
		            costeTropaMadera.setText(String.valueOf(WOOD_COST_CATAPULT));
		            costeTropaComida.setText(String.valueOf(FOOD_COST_CATAPULT));
		            costeTropaHierro.setText(String.valueOf(IRON_COST_CATAPULT));
		            costeTropaMana.setText(String.valueOf(MANA_COST_CATAPULT));

		        } else if (indiceSeleccionado == 6) { // Rocket Launcher
		            costeTropaMadera.setText(String.valueOf(WOOD_COST_ROCKETLAUNCHERTOWER));
		            costeTropaComida.setText(String.valueOf(FOOD_COST_ROCKETLAUNCHERTOWER));
		            costeTropaHierro.setText(String.valueOf(IRON_COST_ROCKETLAUNCHERTOWER));
		            costeTropaMana.setText(String.valueOf(MANA_COST_ROCKETLAUNCHERTOWER));

		        } else if (indiceSeleccionado == 7) { // Magician
		            costeTropaMadera.setText(String.valueOf(WOOD_COST_MAGICIAN));
		            costeTropaComida.setText(String.valueOf(FOOD_COST_MAGICIAN));
		            costeTropaHierro.setText(String.valueOf(IRON_COST_MAGICIAN));
		            costeTropaMana.setText(String.valueOf(MANA_COST_MAGICIAN));

		        } else if (indiceSeleccionado == 8) { // Priest
		            costeTropaMadera.setText(String.valueOf(WOOD_COST_PRIEST));
		            costeTropaComida.setText(String.valueOf(FOOD_COST_PRIEST));
		            costeTropaHierro.setText(String.valueOf(IRON_COST_PRIEST));
		            costeTropaMana.setText(String.valueOf(MANA_COST_PRIEST));

		        } 
		        
			}
    		
    	});
    	add(selectorTropas);
    	
    	// Caja cantidad de tropas
    	cajaCantidadTropas = new JTextField("1");
    	cajaCantidadTropas.setBounds(15,557,80,30); 
    	add(cajaCantidadTropas);
    	
    	// COSTE TROPAS
		// Coste Madera
    	costeTropaMadera = new JLabel(String.valueOf(WOOD_COST_SWORDSMAN));
    	costeTropaMadera.setBounds(25,445,150,30);
    	costeTropaMadera.setFont(new Font("Arial", Font.BOLD, 12));
    	costeTropaMadera.setForeground(Color.WHITE);
    	costeTropaMadera.setIcon(iconoMadera);
    	add(costeTropaMadera);
		// Coste Comida
		costeTropaComida = new JLabel(String.valueOf(FOOD_COST_SWORDSMAN));
		costeTropaComida.setBounds(95,445,150,30);
		costeTropaComida.setFont(new Font("Arial", Font.BOLD, 12));
		costeTropaComida.setForeground(Color.WHITE);
		costeTropaComida.setIcon(iconoComida);
		add(costeTropaComida);
		// Coste Hierro
		costeTropaHierro = new JLabel(String.valueOf(IRON_COST_SWORDSMAN));
		costeTropaHierro.setBounds(25,475,150,30);
		costeTropaHierro.setFont(new Font("Arial", Font.BOLD, 12));
		costeTropaHierro.setForeground(Color.WHITE);
		costeTropaHierro.setIcon(iconoHierro);
		add(costeTropaHierro);
		// Coste Mana
		costeTropaMana = new JLabel(String.valueOf(MANA_COST_SWORDSMAN));
		costeTropaMana.setBounds(95,475,150,30);
		costeTropaMana.setFont(new Font("Arial", Font.BOLD, 12));
		costeTropaMana.setForeground(Color.WHITE);
		costeTropaMana.setIcon(iconoMana);
		add(costeTropaMana);
		
		
    	// Boton Reclutar
    	btnReclutar = new JButton("Reclutar");
    	btnReclutar.setBounds(100,557,85,30);
    	btnReclutar.setIcon(disenoDorado);
    	btnReclutar.setHorizontalTextPosition(SwingConstants.CENTER);
    	btnReclutar.setVerticalTextPosition(SwingConstants.CENTER);
    	btnReclutar.setContentAreaFilled(false);
    	btnReclutar.setFocusPainted(false);
    	btnReclutar.setForeground(Color.BLACK);

    	btnReclutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sonido.reproducirClick();
					int cantidad = Integer.parseInt(cajaCantidadTropas.getText());
					int indiceSeleccionado = selectorTropas.getSelectedIndex();
					//areaConsola.append("Cantidad " + cantidad);
					//areaConsola.append("Indice Seleccionado " + indiceSeleccionado);
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
					//areaConsola.append("Error obteniendo la cantidad.\n");
				};

			}
		});
    	add(btnReclutar);
    	


    	// ---------------- ZONA VER TROPAS / EDIFICIOS ----------------
    	btnMiImperio = new JButton("Mi Imperio");
    	btnMiImperio.setBounds(590,465,100,30); 
    	ImageIcon disenoAzul = new ImageIcon("./src/m3/disenoBotonAzul.png"); 
    	btnMiImperio.setIcon(disenoAzul);
    	btnMiImperio.setHorizontalTextPosition(SwingConstants.CENTER);
    	btnMiImperio.setVerticalTextPosition(SwingConstants.CENTER);
    	btnMiImperio.setContentAreaFilled(false);
    	btnMiImperio.setFocusPainted(false);
    	btnMiImperio.setForeground(Color.BLACK);
		
    	btnMiImperio.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
				sonido.reproducirClick();
    			String informe = "========== TROPAS ACTUALES ==========" + 
    					"\n--- Attack Units ---\n*******************************************************" +
    					"\nSwordsman: " + civilizacion.getSwordsmanCount() + 
    					"\nSpearman: " + civilizacion.getSpearmanCount() + 
    					"\nCrossbow: " + civilizacion.getCrossbowCount() + 
    					"\nCannon: " + civilizacion.getCannonCount() + 
    					"\n--- Defense Units ---\n*******************************************************" +
    					"\nArrow Tower: " + civilizacion.getArrowTowerCount() + 
    					"\nCatapult: " + civilizacion.getCatapultCount() + 
    					"\nRocket Launcher: " + civilizacion.getRocketLauncherCount() + 
    					"\n--- Special Units ---\n*******************************************************" +
    					"\nMagician: " + civilizacion.getMagicianCount() + 
    					"\nPriest: " + civilizacion.getPriestCount() + 
    					"\n========== EDIFICIOS ACTUALES ==========" + 
    					"\nGranjas: " + civilizacion.getFarm() +
    					"\nCarpinterias: " + civilizacion.getCarpentry() + 
    					"\nHerrerías: " + civilizacion.getSmithy() + 
    					"\nTorres Mágicas: " + civilizacion.getMagicTower() +
    					"\nIglesias: " + civilizacion.getChurch();
    			JOptionPane.showMessageDialog(null, informe, "Informe Militar", JOptionPane.INFORMATION_MESSAGE);
    		}
    		
    	});
    	add(btnMiImperio);
    	
    	// ---------------- ZONA VER TROPAS ENEMIGAS ----------------
    	btnVerTropasEnemigas = new JButton("Enemigos");
    	btnVerTropasEnemigas.setBounds(590,505,100,30); 
    	btnVerTropasEnemigas.setIcon(disenoAzul);
    	btnVerTropasEnemigas.setHorizontalTextPosition(SwingConstants.CENTER);
    	btnVerTropasEnemigas.setVerticalTextPosition(SwingConstants.CENTER);
    	btnVerTropasEnemigas.setContentAreaFilled(false);
    	btnVerTropasEnemigas.setFocusPainted(false);
    	btnVerTropasEnemigas.setForeground(Color.BLACK);
    	btnVerTropasEnemigas.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
				sonido.reproducirClick();
    			int min = tiempoRestante / 60;
    			int seg = tiempoRestante % 60;
    			String textoTiempo = String.format("%02d:%02d", min, seg);
    			String informeEnemigo = m.viewThreat(textoTiempo);
    			JOptionPane.showMessageDialog(null, informeEnemigo, "Informe Militar Enemigo", JOptionPane.INFORMATION_MESSAGE);
    		}
    	});
    	add(btnVerTropasEnemigas);
    	
    	
    	// ---------------- PANEL INFERIOR CONSOLA ----------------
    	
    	//{"Swordsman", "Spearman", "Crossbow", "Cannon", "Arrow Tower", "Catapult", "Rocket Launcher", "Magician", "Priest"};
    	civilizacionName = new JLabel("CIVILIZACION: " + civilizacion.getName());
    	civilizacionName.setBounds(335,455,250,30);
    	civilizacionName.setFont(new Font("Arial", Font.BOLD, 16));
    	civilizacionName.setForeground(Color.WHITE);
    	add(civilizacionName);
    	
    		// ATTACK UNITS
    	swordsmanCantidad = new JLabel("Swordsman: " + civilizacion.getSwordsmanCount());
    	swordsmanCantidad.setBounds(335,480,180,30);
    	swordsmanCantidad.setFont(new Font("Arial", Font.BOLD, 13));
    	swordsmanCantidad.setForeground(Color.WHITE);
    	add(swordsmanCantidad);
    	
    	spearmanCantidad = new JLabel("Spearman: " + civilizacion.getSpearmanCount());
    	spearmanCantidad.setBounds(335,500,180,30);
    	spearmanCantidad.setFont(new Font("Arial", Font.BOLD, 13));
    	spearmanCantidad.setForeground(Color.WHITE);
    	add(spearmanCantidad);
    	
    	crossbowCantidad = new JLabel("Crossbow: " + civilizacion.getCrossbowCount());
    	crossbowCantidad.setBounds(335,520,180,30);
    	crossbowCantidad.setFont(new Font("Arial", Font.BOLD, 13));
    	crossbowCantidad.setForeground(Color.WHITE);
    	add(crossbowCantidad);
    	
    	cannonCantidad = new JLabel("Cannon: " + civilizacion.getCannonCount());
    	cannonCantidad.setBounds(335,540,180,30);
    	cannonCantidad.setFont(new Font("Arial", Font.BOLD, 13));
    	cannonCantidad.setForeground(Color.WHITE);
    	add(cannonCantidad);
    	
    		// DEFENSE UNITS 
    	arrowTowerCantidad = new JLabel("Arrow Tower: " + civilizacion.getArrowTowerCount());
    	arrowTowerCantidad.setBounds(335,560,180,30);
    	arrowTowerCantidad.setFont(new Font("Arial", Font.BOLD, 13));
    	arrowTowerCantidad.setForeground(Color.WHITE);
    	add(arrowTowerCantidad);
    	
    	catapultCantidad = new JLabel("Catapult: " + civilizacion.getCatapultCount());
    	catapultCantidad.setBounds(450,480,180,30);
    	catapultCantidad.setFont(new Font("Arial", Font.BOLD, 13));
    	catapultCantidad.setForeground(Color.WHITE);
    	add(catapultCantidad);
    	
    	rocketLauncherCantidad = new JLabel("Rocket Launch: " + civilizacion.getRocketLauncherCount());
    	rocketLauncherCantidad.setBounds(450,500,180,30);
    	rocketLauncherCantidad.setFont(new Font("Arial", Font.BOLD, 13));
    	rocketLauncherCantidad.setForeground(Color.WHITE);
    	add(rocketLauncherCantidad);
    	
    		// SPECIAL UNITS
    	magicianCantidad = new JLabel("Magician: " + civilizacion.getMagicianCount());
    	magicianCantidad.setBounds(450,520,180,30);
    	magicianCantidad.setFont(new Font("Arial", Font.BOLD, 13));
    	magicianCantidad.setForeground(Color.WHITE);
    	add(magicianCantidad);
    	
    	priestCantidad = new JLabel("Priest: " + civilizacion.getPriestCount());
    	priestCantidad.setBounds(450,540,180,30);
    	priestCantidad.setFont(new Font("Arial", Font.BOLD, 13));
    	priestCantidad.setForeground(Color.WHITE);
    	add(priestCantidad);
    	// ---------------- BOTON GUARDAR PARTIDA ----------------
    	btnGuardar = new JButton("Guardar");
    	btnGuardar.setBounds(700,465,80,30); 
    	btnGuardar.setIcon(disenoAzul);
    	btnGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
    	btnGuardar.setVerticalTextPosition(SwingConstants.CENTER);
    	btnGuardar.setContentAreaFilled(false);
    	btnGuardar.setFocusPainted(false);
    	btnGuardar.setForeground(Color.BLACK);
    	btnGuardar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			DatabaseUtils.saveCivilization(civilizacion);
    		}
    	});
    	add(btnGuardar);
    	
    	// ---------------- BOTON AYUDA ----------------
    	btnAyuda = new JButton("Ayuda");
    	btnAyuda.setBounds(700,505,80,30); 
    	btnAyuda.setIcon(disenoAzul);
    	btnAyuda.setHorizontalTextPosition(SwingConstants.CENTER);
    	btnAyuda.setVerticalTextPosition(SwingConstants.CENTER);
    	btnAyuda.setContentAreaFilled(false);
    	btnAyuda.setFocusPainted(false);
    	btnAyuda.setForeground(Color.BLACK);
    	btnAyuda.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			areaConsola.append("Bienvenido a Dominion:"
    					+"\n- El objetivo de este juego es sobrevivir las máximas oleadas posibles "
    					+"\nque irán atacando tu civilizacion cada cierto tiempo." 
    					+ "\n\n - Puedes construir edifcios para mejorar tu producción de recursos."
    					+ "\n Para ello, haz click en uno de los botones de creación y posteriormente"
    					+ "\n en una casilla vacía para construir tu primer edificio."
    					+ "\n\n - Recluta tropas desde el menú desplegable RECLUTAR para poder hacer"
    					+ "\nfrente a todas las amenazas que ataquen tu civilización."
    					+ "\nPuedes ver el coste por unidad de cada tropa justo encima del menú \ndesplegable"
    					+ "\n\n- Pierde la batalla la civilización que haya tenido más perdidas."
    					+ "\n ¡OJO! Si pierdes una batalla tu civilización será destruida y tendrás\nque volver a comenzar desde cero."
    					+ "\n\nBuena suerte. La estrategia que sigas a la hora de reclutar y de \nconstruir tus edificios serán clave para poder sobrevivir a los ataques \nenemigos.\n\n\n");
    			
    		}
    	});
    	add(btnAyuda);
    	
    	// Ponemos le label
    	proximoAtaque.setBounds(590,550,200,30); 
    	proximoAtaque.setFont(new Font("Consolas", Font.BOLD, 16));
    	proximoAtaque.setForeground(Color.RED); 
    	add(proximoAtaque);
    	
    	
	} // Fin del constructor
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(menu, 0, 0, 200, 600, this); // Imagen menú vertical
		g2d.drawImage(menu, 200, 0, 600, 600, this); // Imagen menú horizontal
		g2d.drawImage(emperador, 200, 460, 130, 130, this);
	}

	
	public void actualizarRecursos() {
	    madera.setText("Madera: " + civilizacion.getWood()); // Actualiza el texto del wood en el menú
	    comida.setText("Comida: " + civilizacion.getFood()); // Actualiza el texto del food en el menú
	    hierro.setText("Hierro: " + civilizacion.getIron()); // Actualiza el texto del iron en el menú
	    mana.setText("Mana: " + civilizacion.getMana()); // Actualiza el texto del mana en el menú
	    
	    
	    swordsmanCantidad.setText("Swordsman: " + civilizacion.getSwordsmanCount());
	    spearmanCantidad.setText("Spearman: " + civilizacion.getSpearmanCount());
	    crossbowCantidad.setText("Crossbow: " + civilizacion.getCrossbowCount());
	    cannonCantidad.setText("Cannon: " + civilizacion.getCannonCount());
	    arrowTowerCantidad.setText("Arrow Tower: " + civilizacion.getArrowTowerCount());
	    catapultCantidad.setText("Catapult: " + civilizacion.getCatapultCount());
	    rocketLauncherCantidad.setText("Rocket Launch: " + civilizacion.getRocketLauncherCount());
	    magicianCantidad.setText("Magician: " + civilizacion.getMagicianCount());
	    priestCantidad.setText("Priest: " + civilizacion.getPriestCount());


	    
	    nivelTecnologiaAtaque.setText("Nv. Atk: " + civilizacion.getTechnologyAttack());
	    nivelTecnologiaDefensa.setText("Nv. Def: " + civilizacion.getTechnologyDefense());
	}
	
	public void mostrarConsola(String mensaje) {
		areaConsola.append(mensaje);
	}
	
	// Getters & Setters
	public int getEdificioSeleccionado() {
		return edificioSeleccionado;
	}

	public void setEdificioSeleccionado(int edificioSeleccionado) {
		this.edificioSeleccionado = edificioSeleccionado;
	}

	public int getTiempoRestante() {
		return tiempoRestante;
	}

	public void setTiempoRestante(int tiempoRestante) {
		this.tiempoRestante = tiempoRestante;
	}

	public JLabel getProximoAtaque() {
		return proximoAtaque;
	}

	public void setProximoAtaque(JLabel proximoAtaque) {
		this.proximoAtaque = proximoAtaque;
	}

	public JTextArea getAreaConsola() {
		return areaConsola;
	}

	public void setAreaConsola(JTextArea areaConsola) {
		this.areaConsola = areaConsola;
	}
	

	
	
}
