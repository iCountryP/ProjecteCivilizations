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
	// army[0] --> Swordsman
	// army[1] --> Spearman
	// army[2] --> Crossbow
	// army[3] --> Cannon
	// army[4] --> ArrowTower
	// army[5] --> Catapult
	// army[6] --> RocketLauncher
	// army[7] --> Magician
	// army[8] --> Priest
	
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
				this.food -= FOOD_COST_CHURCH;
				this.wood -= WOOD_COST_CHURCH;
				this.iron -= IRON_COST_CHURCH;
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
				this.food -= FOOD_COST_MAGICTOWER;
				this.wood -= WOOD_COST_MAGICTOWER;
				this.iron -= IRON_COST_MAGICTOWER;
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
				this.food -= FOOD_COST_FARM;
				this.wood -= WOOD_COST_FARM;
				this.iron -= IRON_COST_FARM;
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
				this.food -= FOOD_COST_CARPENTRY;
				this.wood -= WOOD_COST_CARPENTRY;
				this.iron -= IRON_COST_CARPENTRY;
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
				this.food -= FOOD_COST_SMITHY;
				this.wood -= WOOD_COST_SMITHY;
				this.iron -= IRON_COST_SMITHY;
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
	
	// Metodos para añadir unidades a la army
	
	public void newSwordsman(int n) {
		
		try {
			if (n <= 0) {
				throw new InvalidUnitAmountException(INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE);
			} else if (this.food < FOOD_COST_SWORDSMAN*n || this.wood < WOOD_COST_SWORDSMAN*n || this.iron < IRON_COST_SWORDSMAN*n || this.mana < MANA_COST_SWORDSMAN*n) {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (InvalidUnitAmountException e) {
			System.out.println(e);
			n = 0;
		} catch (ResourceException e) {
			System.out.println(e);
			int maxFood = this.food / FOOD_COST_SWORDSMAN;
			int maxWood = this.wood / WOOD_COST_SWORDSMAN;
			int maxIron = this.iron / IRON_COST_SWORDSMAN;
			int maxMana = this.mana / MANA_COST_SWORDSMAN;
			
			int minimum = Math.min(maxFood, Math.min(maxWood, Math.min(maxIron, maxMana)));
			
			if (minimum > 0) {
				n = minimum;
			} else {
				n = 0;
			}
		} finally {
			if (n > 0) {
				this.food -= FOOD_COST_SWORDSMAN*n;
				this.wood -= WOOD_COST_SWORDSMAN*n;
				this.iron -= IRON_COST_SWORDSMAN*n;
				this.mana -= MANA_COST_SWORDSMAN*n;
				
				int current_armor_plus = (int) Math.ceil(ARMOR_SWORDSMAN* Math.pow((1+PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY*0.01), (technologyDefense-1)));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_SWORDSMAN* Math.pow((1+PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY*0.01), (technologyAttack-1)));
				
				for (int i = 0; i < n; i++) {
					this.army[0].add(new Swordsman(current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" espadachines al ejercito.");
		}
		
	}
	
	public void newSpearman(int n) {
		try {
			if (n <= 0) {
				throw new InvalidUnitAmountException(INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE);
			} else if (this.food < FOOD_COST_SPEARMAN*n || this.wood < WOOD_COST_SPEARMAN*n || this.iron < IRON_COST_SPEARMAN*n || this.mana < MANA_COST_SPEARMAN*n) {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (InvalidUnitAmountException e) {
			System.out.println(e);
			n = 0;
		} catch (ResourceException e) {
			System.out.println(e);
			int maxFood = this.food / FOOD_COST_SPEARMAN;
			int maxWood = this.wood / WOOD_COST_SPEARMAN;
			int maxIron = this.iron / IRON_COST_SPEARMAN;
			int maxMana = this.mana / MANA_COST_SPEARMAN;
			
			int minimum = Math.min(maxFood, Math.min(maxWood, Math.min(maxIron, maxMana)));
			
			if (minimum > 0) {
				n = minimum;
			} else {
				n = 0;
			}
		} finally {
			if (n > 0) {
				this.food -= FOOD_COST_SPEARMAN*n;
				this.wood -= WOOD_COST_SPEARMAN*n;
				this.iron -= IRON_COST_SPEARMAN*n;
				this.mana -= MANA_COST_SPEARMAN*n;
				
				int current_armor_plus = (int) Math.ceil(ARMOR_SPEARMAN* Math.pow((1+PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY*0.01), (technologyDefense-1)));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_SPEARMAN* Math.pow((1+PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY*0.01), (technologyAttack-1)));
				
				for (int i = 0; i < n; i++) {
					this.army[1].add(new Spearman(current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" espadachines al ejercito.");
		}
	}
	
	public void newCrossbow(int n) {
		try {
			if (n <= 0) {
				throw new InvalidUnitAmountException(INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE);
			} else if (this.food < FOOD_COST_CROSSBOW*n || this.wood < WOOD_COST_CROSSBOW*n || this.iron < IRON_COST_CROSSBOW*n || this.mana < MANA_COST_CROSSBOW*n) {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (InvalidUnitAmountException e) {
			System.out.println(e);
			n = 0;
		} catch (ResourceException e) {
			System.out.println(e);
			int maxFood = this.food / FOOD_COST_CROSSBOW;
			int maxWood = this.wood / WOOD_COST_CROSSBOW;
			int maxIron = this.iron / IRON_COST_CROSSBOW;
			int maxMana = this.mana / MANA_COST_CROSSBOW;
			
			int minimum = Math.min(maxFood, Math.min(maxWood, Math.min(maxIron, maxMana)));
			
			if (minimum > 0) {
				n = minimum;
			} else {
				n = 0;
			}
		} finally {
			if (n > 0) {
				this.food -= FOOD_COST_CROSSBOW*n;
				this.wood -= WOOD_COST_CROSSBOW*n;
				this.iron -= IRON_COST_CROSSBOW*n;
				this.mana -= MANA_COST_CROSSBOW*n;
				
				int current_armor_plus = (int) Math.ceil(ARMOR_CROSSBOW* Math.pow((1+PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY*0.01), (technologyDefense-1)));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_CROSSBOW* Math.pow((1+PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY*0.01), (technologyAttack-1)));
				
				for (int i = 0; i < n; i++) {
					this.army[2].add(new Crossbow(current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" espadachines al ejercito.");
		}
	}
	
	public void newCannon(int n) {
		this.army[3].add(new Cannon(ARMOR_CANNON, BASE_DAMAGE_CANNON));
	}
	
	public void newArrowTower(int n) {
		this.army[4].add(new ArrowTower(ARMOR_ARROWTOWER, BASE_DAMAGE_ARROWTOWER));
	}
	
	public void newCatapult(int n) {
		this.army[5].add(new Catapult(ARMOR_CATAPULT, BASE_DAMAGE_CATAPULT));
	}
	
	public void newRocketLauncher(int n) {
		this.army[6].add(new RocketLauncherTower(ARMOR_ROCKETLAUNCHERTOWER, BASE_DAMAGE_ROCKETLAUNCHERTOWER));
	}
	
	public void newMagician(int n) {
		this.army[7].add(new Magician(ARMOR_MAGICIAN, BASE_DAMAGE_MAGICIAN));
	}
	
	public void newPriest(int n) {
		this.army[8].add(new Priest(ARMOR_PRIEST, BASE_DAMAGE_PRIEST));
	}
	
}
