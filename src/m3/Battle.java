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
	
	private int[] totalInitialUnits; // Array de 2 columnas, unidades totales iniciales tuyas y enemigas
	private int[] totalCurrentUnits; // Array de 2 columnas, unidades totales actuales tuyas y enemigas
	
	// Variables para el loop de batalla
	
	private boolean battleFinished;
	private boolean attackAgain;
	
	private int attackingArmyIndex;
	private ArrayList<MilitaryUnit>[] attackingArmy;
	private int attackingGroupIndex;
	private ArrayList<MilitaryUnit> attackingGroup;
	private int attackingUnitIndex;
	private MilitaryUnit attackingUnit;
	
	private int defendingArmyIndex;
	private ArrayList<MilitaryUnit>[] defendingArmy;
	private int defendingGroupIndex;
	private ArrayList<MilitaryUnit> defendingGroup;
	private int defendingUnitIndex;
	private MilitaryUnit defendingUnit;
	
	// ---------------------------------
	
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
		this.totalInitialUnits = new int[2];
		this.totalCurrentUnits = new int[2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 9; j++) {
			    this.initialArmies[i][j] = this.armies[i][j].size();
			    this.currentArmies[i][j] = this.initialArmies[i][j];
			    
			    this.totalInitialUnits[i] += this.initialArmies[i][j];
			}
			this.totalCurrentUnits[i] = this.totalInitialUnits[i];
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
		this.battleFinished = false;
		this.attackAgain = false;
		
		// Elegir inicio aleatorio
		this.chooseStartingSides();
		
		while (!this.battleFinished) {
			
			// Elegir atacante en caso de que el anterior no ataque de nuevo
			if (!this.attackAgain) {
				this.chooseRandomUnit(this.attackingArmyIndex);
			}
			
			// Elegir defensor
			this.chooseRandomUnit(this.defendingArmyIndex);
			
			// El atacante ataca a la unidad defensora
			this.defendingUnit.takeDamage(this.attackingUnit.getAttack());
			
			// Comprobar si genera residuos
			this.generateWaste();
			
			// Comprobar si se ha muerto la unidad defensora
			if (this.defendingUnit.getActualArmor() == 0) {

				// Eliminar a la unidad muerta
				this.defendingGroup.remove(this.defendingUnitIndex);
				
			    this.currentArmies[defendingArmyIndex][defendingGroupIndex] = this.armies[defendingArmyIndex][defendingGroupIndex].size();
			    
			    // Sumamos al total
			    this.totalCurrentUnits[defendingArmyIndex] -= 1;
			    
			    // Recalcular valor actual del ejercito defensor
			    this.currentArmyCosts[this.defendingArmyIndex][this.defendingGroupIndex] = ((WOOD_COST_UNITS[this.defendingGroupIndex] * 2) + (IRON_COST_UNITS[this.defendingGroupIndex] * 10) + FOOD_COST_UNITS[this.defendingGroupIndex]) * this.currentArmies[this.defendingArmyIndex][this.defendingGroupIndex];

				int minimumUnits = (int) (Math.ceil(this.totalInitialUnits[defendingArmyIndex] * (DEFEAT_PERCENTAGE * 0.01)));
				// Comparamos con el numero total de unidades con el que empezó la army
				if (this.totalCurrentUnits[defendingArmyIndex] < minimumUnits) {
					this.battleFinished = true;

					// Calcula las perdidas tanto del jugador como del enemigo
					this.calculateLosses();
					
					// Gestiona el resultado de la batalla, ganada o perdida
					this.handleBattleResult();
					
				}
			}
			
			// Ver si el atacante vuelve a atacar
			this.handleExtraAttackingTurn();
			
			// Turno de la otra army para atacar
			this.swapSides();
		}
		
	}
	
	private void chooseStartingSides() {
		if (RandomUtils.chancePercent(50)) {
			// 50% de empezar nostros
			this.attackingArmyIndex = 0;
			this.defendingArmyIndex = 1;
			
			this.attackingArmy = armies[0];
			this.defendingArmy = armies[1];
		} else {
			// 50% de empezar el enemigo
			this.attackingArmyIndex = 1;
			this.defendingArmyIndex = 0;
			
			this.attackingArmy = armies[1];
			this.defendingArmy = armies[0];
		}
	}
	
	private void swapSides() {
		int aux = this.defendingArmyIndex;
		this.defendingArmyIndex = this.attackingArmyIndex;
		this.attackingArmyIndex = aux;
		
		this.attackingArmy = armies[this.attackingArmyIndex];
		this.defendingArmy = armies[this.defendingArmyIndex];
	}
	
	private void chooseRandomUnit(int side) {
		// Comprobar a que bando le vamos a elegir una unidad random
		if (side == this.attackingArmyIndex) {
			// Si side es igual al indice de la army que ataca, seleccionamos un atacante random
			this.attackingGroupIndex = RandomUtils.weightedChoice(CHANCE_ATTACK_ARMY_UNITS);
			this.attackingGroup = this.attackingArmy[this.attackingGroupIndex];
			this.attackingUnitIndex = RandomUtils.weightedChoice(CHANCE_PLACEHOLDER); // Cambiar placeholder
			this.attackingUnit = this.attackingGroup.get(this.attackingUnitIndex);
		} else {
			// En caso contrario, seleccionamos un defensor random
			this.defendingGroupIndex = RandomUtils.weightedChoice(CHANCE_PLACEHOLDER); // Cambiar placeholder
			this.defendingGroup = this.defendingArmy[this.defendingGroupIndex];
			this.defendingUnitIndex = RandomUtils.weightedChoice(CHANCE_PLACEHOLDER); // Cambiar placeholder
			this.defendingUnit = this.defendingGroup.get(this.defendingUnitIndex);
		}
	}
	
	private void generateWaste() {
		if (RandomUtils.chancePercent(CHANCE_GENERATING_WASTE_UNITS[this.defendingGroupIndex])) {
			this.wasteWood += (int) Math.ceil(WOOD_COST_UNITS[this.defendingGroupIndex] * (PERCENTATGE_WASTE * 0.01));
			this.wasteIron += (int) Math.ceil(IRON_COST_UNITS[this.defendingGroupIndex] * (PERCENTATGE_WASTE * 0.01));
		}
	}
	
	private void calculateLosses() {
		for (int i = 0; i < this.initialArmyCosts[0].length; i++) {
			// Perdidas jugador
		    this.resourcesLosses[0] += (this.initialArmyCosts[0][i] - this.currentArmyCosts[0][i]);
		    // Perdidas enemigo
		    this.resourcesLosses[1] += (this.initialArmyCosts[1][i] - this.currentArmyCosts[1][i]);
		}
	}
	
	private void handleBattleResult() {
		// Si tienes menos perdidas que el enemigo ganas
		if (this.resourcesLosses[0] < this.resourcesLosses[1]) {
			System.out.println("Has ganado");
		} else if (this.resourcesLosses[0] == this.resourcesLosses[1]) {
			System.out.println("Has empatado");
		} else {
			System.out.println("Has perdido");
		}
	}
	
	private void handleExtraAttackingTurn() {
		if (RandomUtils.chancePercent(CHANCE_ATTACK_AGAIN_UNITS[this.attackingGroupIndex])) {
			this.attackAgain = true;
		} else {
			this.attackAgain = false;
		}
	}
	
	public int[] getWaste() {
		int[] waste = {this.wasteWood, this.wasteIron};
		return waste;
	}
	
}