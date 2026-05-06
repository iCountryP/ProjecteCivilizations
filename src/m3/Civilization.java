package m3;

import java.util.ArrayList;

public class Civilization implements Variables {

	private int technologyDefense ;
	private int technologyAtack ;

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
	
	Civilization() {
		for (int i = 0; i < army.length; i++) {
		    army[i] = new ArrayList<>();
		}
	}
	
	public void newChurch() {
		try {
			if (this.food >= FOOD_COST_CHURCH && this.wood >= WOOD_COST_CHURCH && this.iron >= IRON_COST_CHURCH) {
				this.food =- FOOD_COST_CHURCH;
				this.wood =- WOOD_COST_CHURCH;
				this.iron =- IRON_COST_CHURCH;
				this.church++;
			} else {
				throw new ResourceException("No hay suficientes recursos");
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
				throw new ResourceException("No hay suficientes recursos");
			}
		} catch (ResourceException e) {
			System.out.println(e);
		}
	}
	
	public void newFarm() {
		
	}
	
	public void newCarpentry() {
		
	}
	
	public void newSmithy() {
		
	}
}
