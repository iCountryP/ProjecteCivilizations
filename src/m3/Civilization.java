package m3;

import java.util.ArrayList;

public class Civilization implements Variables {

	private int technologyDefense;
	private int technologyAttack;

	private int wood;
	private int iron;
	private int food;
	private int mana;
	
	private int magicTower;
	private int church;
	private int farm;
	private int smithy;
	private int carpentry;

	private int battles;
	private ArrayList<MilitaryUnit>[] army = new ArrayList[9];
	
	public Civilization() {
		// Ver que onda con esto, instanciacion de las unidades
		for (int i = 0; i < army.length; i++) {
		    army[i] = new ArrayList<>();
		}
	}
	
	// Metodos para añadir estructuras a la civilizacion
	
	public void newChurch() {
		try {
			if (this.food >= FOOD_COST_CHURCH && this.wood >= WOOD_COST_CHURCH && this.iron >= IRON_COST_CHURCH) {
				this.food =- FOOD_COST_CHURCH;
				this.wood =- WOOD_COST_CHURCH;
				this.iron =- IRON_COST_CHURCH;
				this.church++;
			} else {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (ResourceException e) {
			System.out.println(e);
		}
	}
	
	public void newMagicTower() {
		try {
			if (this.food >= FOOD_COST_MAGICTOWER && this.wood >= WOOD_COST_MAGICTOWER && this.iron >= IRON_COST_MAGICTOWER) {
				this.food =- FOOD_COST_MAGICTOWER;
				this.wood =- WOOD_COST_MAGICTOWER;
				this.iron =- IRON_COST_MAGICTOWER;
				this.magicTower++;
			} else {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (ResourceException e) {
			System.out.println(e);
		}
	}
	
	public void newFarm() {
		try {
			if (this.food >= FOOD_COST_FARM && this.wood >= WOOD_COST_FARM && this.iron >= IRON_COST_FARM) {
				this.food =- FOOD_COST_FARM;
				this.wood =- WOOD_COST_FARM;
				this.iron =- IRON_COST_FARM;
				this.farm++;
			} else {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (ResourceException e) {
			System.out.println(e);
		}
	}
	
	public void newCarpentry() {
		try {
			if (this.food >= FOOD_COST_CARPENTRY && this.wood >= WOOD_COST_CARPENTRY && this.iron >= IRON_COST_CARPENTRY) {
				this.food =- FOOD_COST_CARPENTRY;
				this.wood =- WOOD_COST_CARPENTRY;
				this.iron =- IRON_COST_CARPENTRY;
				this.carpentry++;
			} else {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (ResourceException e) {
			System.out.println(e);
		}
	}
	
	public void newSmithy() {
		try {
			if (this.food >= FOOD_COST_SMITHY && this.wood >= WOOD_COST_SMITHY && this.iron >= IRON_COST_SMITHY) {
				this.food =- FOOD_COST_SMITHY;
				this.wood =- WOOD_COST_SMITHY;
				this.iron =- IRON_COST_SMITHY;
				this.smithy++;
			} else {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (ResourceException e) {
			System.out.println(e);
		}
	}
	
	// Metodos para añadir mejoras a la civilizacion
	
	public void upgradeTechnologyDefense() {
		try {
			// Si el nivel por defecto es 0, quitar el -1
			int current_iron_cost = (int) Math.ceil(UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST* Math.pow((1+UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST*0.01), (technologyDefense-1)));
			int current_wood_cost = (int) Math.ceil(UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST* Math.pow((1+UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST*0.01), (technologyDefense-1)));
			if (this.iron >= current_iron_cost && this.wood >= current_wood_cost) {
				this.technologyDefense++;
			} else {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (ResourceException e) {
			System.out.println(e);
		}
	}
	
	public void upgradeTechnologyAttack() {
		try {
			int current_iron_cost = (int) Math.ceil(UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST* Math.pow((1+UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST*0.01), (technologyAttack-1)));
			int current_wood_cost = (int) Math.ceil(UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST* Math.pow((1+UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST*0.01), (technologyAttack-1)));
			if (this.iron >= current_iron_cost && this.wood >= current_wood_cost) {
				this.technologyAttack++;
			} else {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (ResourceException e) {
			System.out.println(e);
		}
	}
	
}
