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
	private int[] resourcesLosses; // Array de 2 columnas del valor total perdido de cada army
	
	private String battleDevelopment; // Desarrollo de la batalla que tendra que mostrarse despues en algun sitio
	
	private int wasteWood, wasteIron; // Residuos generados en la batalla
	private int enemyDrops, civilizationDrops; // Bajas de los ejercitos
	
	public Battle(ArrayList<MilitaryUnit>[] civilizationArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
		// Valores por defecto antes de la batalla
		this.wasteWood = 0;
		this.wasteIron = 0;
		this.enemyDrops = 0;
		this.civilizationDrops = 0;
		this.battleDevelopment = "";
		
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
			    this.initialArmyCosts[i][j] = ((WOOD_COST_UNITS[j] * 2) + (IRON_COST_UNITS[j] * 10) + FOOD_COST_UNITS[j]) * this.initialArmies[i][j];
			    this.currentArmyCosts[i][j] = this.initialArmyCosts[i][j];
			}
		}
		
		this.resourcesLosses = new int[2];
	}
	
	public void startBattle() {
		boolean battleFinished = false;
		
		// Se elige un ejercito al azar atacante (Falta implementar)
		int attackingArmyIndex = 0;
		ArrayList<MilitaryUnit>[] attackingArmy = armies[attackingArmyIndex];
		int defendingArmyIndex = 1;
		ArrayList<MilitaryUnit>[] defendingArmy = armies[defendingArmyIndex];
		
		while (!battleFinished) {
			// Se elige un grupo al azar (Falta implementar)
			ArrayList<MilitaryUnit> attackingGroup = attackingArmy[0];
			
			// Se elige una unidad al azar del grupo (Falta implementar)
			MilitaryUnit attackingUnit = attackingGroup.get(0);
			
			// Se elige un grupo defensor al azar (Falta implementar)
			int defendingGroupIndex = 0;
			ArrayList<MilitaryUnit> defendingGroup = defendingArmy[defendingGroupIndex];
			
			// Se elige una unidad defensora al azar (Falta implementar)
			int defendingUnitIndex = 0;
			MilitaryUnit defendingUnit = defendingGroup.get(defendingUnitIndex);
			
			// El atacante ataca a la unidad defensora
			defendingUnit.takeDamage(attackingUnit.getAttack());
			
			// Comprobar si genera residuos
			if (RandomUtils.chancePercent(CHANCE_GENERATING_WASTE_UNITS[defendingGroupIndex])) {
				this.wasteWood += (int) Math.ceil(WOOD_COST_UNITS[defendingGroupIndex] * (PERCENTATGE_WASTE * 0.01));
				this.wasteIron += (int) Math.ceil(IRON_COST_UNITS[defendingGroupIndex] * (PERCENTATGE_WASTE * 0.01));
			}
			
			// Comprobar si se ha muerto la unidad defensora
			if (defendingUnit.getActualArmor() == 0) {
				// Eliminar a la unidad muerta
				defendingGroup.remove(defendingUnitIndex);
				
				// Actualizar el numero de unidades de cada grupo actualmente
				// Total de unidades
				int totalInitialUnits = 0;
				int totalCurrentUnits = 0;
				for (int i = 0; i < 9; i++) {
				    this.currentArmies[defendingArmyIndex][i] = this.armies[defendingArmyIndex][i].size();
				    // Sumamos al total
				    totalInitialUnits += this.initialArmies[defendingArmyIndex][i];
				    totalCurrentUnits += this.armies[defendingArmyIndex][i].size();
				    // Recalcular valor actual del ejercito defensor
				    this.currentArmyCosts[defendingArmyIndex][i] = ((WOOD_COST_UNITS[i] * 2) + (IRON_COST_UNITS[i] * 10) + FOOD_COST_UNITS[i]) * this.initialArmies[defendingArmyIndex][i];
				}
				
				// Comparamos con el numero total de unidades con el que empezó la army
				if ((Math.ceil(totalInitialUnits * (DEFEAT_PERCENTAGE * 0.01))) > totalCurrentUnits) {
					battleFinished = true;
					// Calcular el valor perdido de cada ejercito
					for (int i = 0; i < 9; i++) {
					    // Recalcular valor actual del ejercito defensor
					    this.resourcesLosses[0] += this.currentArmyCosts[0][i];
					    this.resourcesLosses[1] += this.currentArmyCosts[1][i];
					}
					
					if (this.resourcesLosses[0] > this.resourcesLosses[1]) {
						System.out.println("Has ganado");
					} else if (this.resourcesLosses[0] == this.resourcesLosses[1]) {
						System.out.println("Has empatado");
					} else {
						System.out.println("Has perdido");
					}
					
				}
			}
		}
		
	}
	
	public int[] getWaste() {
		int[] waste = {this.wasteWood, this.wasteIron};
		return waste;
	}
	
}