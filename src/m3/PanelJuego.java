package m3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelJuego extends JPanel {
	private BufferedImage texturaHierba;
	private BufferedImage farm, carpinteria, herreria, torreMagica, church;
	private int[][] mapa;
	private int edificioSeleccionadoJuego;
	private Civilization civilizacion;
	private PanelMenu menu;
	private GestorSonido sonido = new GestorSonido();
	
	public PanelJuego(Civilization c) {
		super();
		mapa = new int[10][10];	
		this.civilizacion = c;
		
		// Metodo para poder encontrar la posicion de la cuadricula
		addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        int columna = e.getX() / 60;
		        int fila = e.getY() / 60;
		        //System.out.println(columna +"     "+ fila);
		        if (mapa[columna][fila] == 0 && edificioSeleccionadoJuego > 0) {
		        	
		        	// Construimos granja
		        	if(edificioSeleccionadoJuego == 1) {
		        		int granjasAntesDeConstruir = civilizacion.getFarm();
		        		
		        		civilizacion.newFarm();
		        		
		        		if(civilizacion.getFarm() > granjasAntesDeConstruir) {
		        			mapa[columna][fila] = 1;
		        			System.out.println("Se ha construido una granja.");
				        	sonido.reproducirConstruccion();
		        			menu.getAreaConsola().append("Granja construida en " + "[" + columna + "]" + "[" + fila + "]. Tienes un total de: " + civilizacion.getFarm() + " granjas.\n");
		        			menu.getAreaConsola().append("*********************************************************\n");
		        		}
		        	}
		        	
		        	// Construimos Carpinteria
		        	else if(edificioSeleccionadoJuego == 2) {
		        		int carpinteriasAntesDeConstruir = civilizacion.getCarpentry();
		        		
		        		civilizacion.newCarpentry();
		        		
		        		if(civilizacion.getCarpentry() > carpinteriasAntesDeConstruir) {
		        			mapa[columna][fila] = 2;
		        			//System.out.println("Se ha construido una carpinteria.");
				        	sonido.reproducirConstruccion();
		        			menu.getAreaConsola().append("Carpinteria construida en " + "[" + columna + "]" + "[" + fila + "]. Tienes un total de: " + civilizacion.getCarpentry() + " carpinterías.\n");
		        			menu.getAreaConsola().append("*********************************************************\n");


		        		}
		        	}
		        	
		        	// Construimos Herreria
		        	else if(edificioSeleccionadoJuego == 3) {
		        		int herreriasAntesDeConstruir = civilizacion.getSmithy();
		        		
		        		civilizacion.newSmithy();
		        		
		        		if(civilizacion.getSmithy() > herreriasAntesDeConstruir) {
		        			mapa[columna][fila] = 3;
		        			//System.out.println("Se ha construido una herreria.");
				        	sonido.reproducirConstruccion();
		        			menu.getAreaConsola().append("Herrería construida en " + "[" + columna + "]" + "[" + fila + "]. Tienes un total de: " + civilizacion.getSmithy() + " herrerías.\n");
		        			menu.getAreaConsola().append("*********************************************************\n");

		        		}
		        	}
		        	
		        	// Construimos Magic Tower
		        	else if(edificioSeleccionadoJuego == 4) {
		        		int magicTowerAntesDeConstruir = civilizacion.getMagicTower();
		        		
		        		civilizacion.newMagicTower();
		        		
		        		if(civilizacion.getMagicTower() > magicTowerAntesDeConstruir) {
		        			mapa[columna][fila] = 4;
		        			//System.out.println("Se ha construido una torre mágica.");
				        	sonido.reproducirConstruccion();
		        			menu.getAreaConsola().append("Torre mágica construida en " + "[" + columna + "]" + "[" + fila + "]. Tienes un total de: " + civilizacion.getMagicTower() + " torres mágicas.\n");
		        			menu.getAreaConsola().append("*********************************************************\n");

		        		}
		        	}
		        	
		        	// Construimos Church
		        	else if(edificioSeleccionadoJuego == 5) {
		        		int churchAntesDeConstruir = civilizacion.getChurch();
		        		
		        		civilizacion.newChurch();
		        		
		        		if(civilizacion.getChurch() > churchAntesDeConstruir) {
		        			mapa[columna][fila] = 5;
		        			System.out.println("Se ha construido una iglesia (Amén).");
				        	sonido.reproducirConstruccion();
		        			menu.getAreaConsola().append("Iglesia construida en " + "[" + columna + "]" + "[" + fila + "]. Tienes un total de: " + civilizacion.getChurch() + " iglesias.\n");
		        			menu.getAreaConsola().append("*********************************************************\n");

		        		}
		        	}
		        } 
		        else if (mapa[columna][fila] > 0 && edificioSeleccionadoJuego > 0) {
		        	System.out.println("Ya existe un edificio en esa cuadricula.");
		        	menu.getAreaConsola().append("Ya existe un edificio en esa cuadrícula.\n");
        			menu.getAreaConsola().append("*********************************************************\n");

		        }

		        edificioSeleccionadoJuego = 0;
		        repaint();
		        
		        if (menu != null) {
		        	menu.actualizarRecursos();
		        }
		    }
		    
		});
		
		// Metodo para que cuando pasas el ratón por encima, sale un botón HTML con la info de la construccion
		addMouseMotionListener(new MouseAdapter() {
		    public void mouseMoved(MouseEvent e) {
		        int columna = e.getX() / 60;
		        int fila = e.getY() / 60;
		        
		       // Si dejas el ratón encima de una granja, sale el siguiente mensaje:
		        if (mapa[columna][fila] == 1) {
		        	setToolTipText("<html>"+"["+columna+"]  ["+fila+"]<br>"+"<b>Granja</b><br>Cantidad: "+civilizacion.getFarm()+"</html>");
		        }
		        else if (mapa[columna][fila] == 2) {
		        	setToolTipText("<html>"+"["+columna+"]  ["+fila+"]<br>"+"<b>Carpinteria</b><br>Cantidad: "+civilizacion.getCarpentry()+"</html>");
		        }
		        else if (mapa[columna][fila] == 3) {
		        	setToolTipText("<html>"+"["+columna+"]  ["+fila+"]<br>"+"<b>Herreria</b><br>Cantidad: "+civilizacion.getSmithy()+"</html>");
		        }
		        else if (mapa[columna][fila] == 4) {
		        	setToolTipText("<html>"+"["+columna+"]  ["+fila+"]<br>"+"<b>Torre Magica</b><br>Cantidad: "+civilizacion.getMagicTower()+"</html>");
		        }
		        else if (mapa[columna][fila] == 5) {
		        	setToolTipText("<html>"+"["+columna+"]  ["+fila+"]<br>"+"<b>Iglesia</b><br>Cantidad: "+civilizacion.getChurch()+"</html>");
		        }
		        
		        if (mapa[columna][fila] == 0) {
		            setToolTipText(null); 
		        }
		    }
		});
		
		
		// Textura Hierba
    	try {
    		texturaHierba = ImageIO.read(new File("./src/m3/texturaHierba.jpeg"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    	// EDIFICIO FARM
    	try {
    		farm = ImageIO.read(new File("./src/m3/farm.png"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    	// EDIFICIO CARPINTERIA
    	try {
    		carpinteria = ImageIO.read(new File("./src/m3/carpinteria.png"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	// EDIFICIO HERRERIA
    	try {
    		herreria = ImageIO.read(new File("./src/m3/herreria.png"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    	// EDIFICIO TORRE MAGICA
    	try {
    		torreMagica = ImageIO.read(new File("./src/m3/torreMagica.png"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    	// EDIFICIO CHURCH
    	try {
    		church = ImageIO.read(new File("./src/m3/church3.png"));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    	
	} // Fin del Constructor
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(new Color(0,0,0));
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(i <= 4 && j <= 6) {
					g2d.drawImage(texturaHierba, i*60, j*60, 60, 60, this);

				}
				/*else if(j >= 8 || i <= 2) {
					g2d.drawImage(texturaDesierto, i*60, j*60, 60, 60, this);
				}*/
				else {
					g2d.drawImage(texturaHierba, i*60, j*60, 60, 60, this);
				}
		

				g2d.drawRect(i*60, j*60, 60, 60);
			}
		}
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if (mapa[i][j] == 1) {
					g2d.drawImage(farm, i*60, j*60, 60, 60, this);
				}
				else if (mapa[i][j] == 2) {
					g2d.drawImage(carpinteria, i*60, j*60, 60, 60, this);
				}
				else if (mapa[i][j] == 3) {
					g2d.drawImage(herreria, i*60, j*60, 60, 60, this);
				}
				else if (mapa[i][j] == 4) {
					g2d.drawImage(torreMagica, i*60, j*60, 60, 60, this);
				}
				else if (mapa[i][j] == 5) {
					g2d.drawImage(church, i*60, j*60, 60, 60, this);
				}
			}
		}
	}

	public int getEdificioSeleccionadoJuego() {
		return edificioSeleccionadoJuego;
	}

	public void setEdificioSeleccionadoJuego(int edificioSeleccionadoJuego) {
		this.edificioSeleccionadoJuego = edificioSeleccionadoJuego;
	}

	public void setMenu(PanelMenu menu) {
		this.menu = menu;
	}
	
	
	
}
