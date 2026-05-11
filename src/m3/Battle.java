package m3;

import java.util.ArrayList;

public class Battle implements Variables {

	private ArrayList<MilitaryUnit>[] civilizationArmy; // Tu army
	private ArrayList<MilitaryUnit>[] enemyArmy; // Army enemiga
	private ArrayList<MilitaryUnit>[][] armies; 
	
	private int[][] initialArmies; // Matriz de 2 filas 9 columnas del numero de tropas por grupo inicial
	private int[][] currentArmies; // Matriz de 2 filas 9 columnas del numero de tropas por grupo actual
	private int[][] initialArmyCosts; // Matriz de 2 filas 9 columnas del valor total de cada grupo de tropas inicial
	private int[][] currentArmyCosts; // Matriz de 2 filas 9 columnas del valor total de cada grupo de tropas actual
	private int[][] resourcesLosses; // Matriz de 2 filas 9 columnas del valor total perdido de cada grupo
	
	private String battleDevelopment; // Desarrollo de la batalla que tendra que mostrarse despues en algun sitio
	
	private int wasteWood, wasteIron; // Residuos generados en la batalla
	private int enemyDrops, civilizationDrops; // Bajas de los ejercitos
	
	public Battle(ArrayList<MilitaryUnit>[] civilizationArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
		// Instanciación de las armys
		this.civilizationArmy = civilizationArmy;
		this.enemyArmy = enemyArmy;
		
		this.armies = (ArrayList<MilitaryUnit>[][]) new ArrayList[2][9];
	    
	    this.armies[0] = civilizationArmy;
	    this.armies[1] = enemyArmy;
		
		// Instanciación de los datos de las armys
		this.initialArmies = new int[2][9];
		this.currentArmies = new int[2][9];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 9; j++) {
			    this.initialArmies[i][j] = this.armies[i][j].size();
			    this.currentArmies[i][j] = this.initialArmies[i][j];
			}
		}
		
		this.initialArmyCosts = new int[2][9];
		this.currentArmyCosts = new int[2][9];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 9; j++) {
			    this.initialArmyCosts[i][j] = (WOOD_COST_UNITS[j] * 2) + (IRON_COST_UNITS[j] * 10) + FOOD_COST_UNITS[j];
			    this.currentArmyCosts[i][j] = this.initialArmyCosts[i][j];
			}
		}
		
		this.resourcesLosses = new int[2][9];
	}
	
}