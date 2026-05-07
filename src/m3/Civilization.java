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
		this.technologyAttack = 0;
		this.technologyDefense = 0;
		
		this.wood = 0;
		this.iron = 0;
		this.food = 0;
		this.mana = 0;
		
		this.magicTower = 0;
		this.church = 0;
		this.farm = 0;
		this.smithy = 0;
		this.carpentry = 0;
		
		this.battles = 0;
		
		for (int i = 0; i < army.length; i++) {
		    army[i] = new ArrayList<MilitaryUnit>();
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
			int current_iron_cost = (int) Math.ceil(UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST* Math.pow((1+UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST*0.01), technologyDefense));
			int current_wood_cost = (int) Math.ceil(UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST* Math.pow((1+UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST*0.01), technologyDefense));
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
			int current_iron_cost = (int) Math.ceil(UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST* Math.pow((1+UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST*0.01), technologyAttack));
			int current_wood_cost = (int) Math.ceil(UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST* Math.pow((1+UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST*0.01), technologyAttack));
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
				
				int current_armor_plus = (int) Math.ceil(ARMOR_SWORDSMAN* Math.pow((1+PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY*0.01), technologyDefense));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_SWORDSMAN* Math.pow((1+PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY*0.01), technologyAttack));
				
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
				
				int current_armor_plus = (int) Math.ceil(ARMOR_SPEARMAN* Math.pow((1+PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY*0.01), technologyDefense));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_SPEARMAN* Math.pow((1+PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY*0.01), technologyAttack));
				
				for (int i = 0; i < n; i++) {
					this.army[1].add(new Spearman(current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" lanceros al ejercito.");
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
				
				int current_armor_plus = (int) Math.ceil(ARMOR_CROSSBOW* Math.pow((1+PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY*0.01), technologyDefense));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_CROSSBOW* Math.pow((1+PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY*0.01), technologyAttack));
				
				for (int i = 0; i < n; i++) {
					this.army[2].add(new Crossbow(current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" ballestas al ejercito.");
		}
	}
	
	public void newCannon(int n) {
		try {
			if (n <= 0) {
				throw new InvalidUnitAmountException(INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE);
			} else if (this.food < FOOD_COST_CANNON*n || this.wood < WOOD_COST_CANNON*n || this.iron < IRON_COST_CANNON*n || this.mana < MANA_COST_CANNON*n) {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (InvalidUnitAmountException e) {
			System.out.println(e);
			n = 0;
		} catch (ResourceException e) {
			System.out.println(e);
			int maxFood = this.food / FOOD_COST_CANNON;
			int maxWood = this.wood / WOOD_COST_CANNON;
			int maxIron = this.iron / IRON_COST_CANNON;
			int maxMana = this.mana / MANA_COST_CANNON;
			
			int minimum = Math.min(maxFood, Math.min(maxWood, Math.min(maxIron, maxMana)));
			
			if (minimum > 0) {
				n = minimum;
			} else {
				n = 0;
			}
		} finally {
			if (n > 0) {
				this.food -= FOOD_COST_CANNON*n;
				this.wood -= WOOD_COST_CANNON*n;
				this.iron -= IRON_COST_CANNON*n;
				this.mana -= MANA_COST_CANNON*n;
				
				int current_armor_plus = (int) Math.ceil(ARMOR_CANNON* Math.pow((1+PLUS_ARMOR_CANNON_BY_TECHNOLOGY*0.01), technologyDefense));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_CANNON* Math.pow((1+PLUS_ATTACK_CANNON_BY_TECHNOLOGY*0.01), technologyAttack));
				
				for (int i = 0; i < n; i++) {
					this.army[3].add(new Cannon(current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" cañones al ejercito.");
		}
	}
	
	public void newArrowTower(int n) {
		try {
			if (n <= 0) {
				throw new InvalidUnitAmountException(INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE);
			} else if (this.food < FOOD_COST_ARROWTOWER*n || this.wood < WOOD_COST_ARROWTOWER*n || this.iron < IRON_COST_ARROWTOWER*n || this.mana < MANA_COST_ARROWTOWER*n) {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (InvalidUnitAmountException e) {
			System.out.println(e);
			n = 0;
		} catch (ResourceException e) {
			System.out.println(e);
			int maxFood = this.food / FOOD_COST_ARROWTOWER;
			int maxWood = this.wood / WOOD_COST_ARROWTOWER;
			int maxIron = this.iron / IRON_COST_ARROWTOWER;
			int maxMana = this.mana / MANA_COST_ARROWTOWER;
			
			int minimum = Math.min(maxFood, Math.min(maxWood, Math.min(maxIron, maxMana)));
			
			if (minimum > 0) {
				n = minimum;
			} else {
				n = 0;
			}
		} finally {
			if (n > 0) {
				this.food -= FOOD_COST_ARROWTOWER*n;
				this.wood -= WOOD_COST_ARROWTOWER*n;
				this.iron -= IRON_COST_ARROWTOWER*n;
				this.mana -= MANA_COST_ARROWTOWER*n;
				
				int current_armor_plus = (int) Math.ceil(ARMOR_ARROWTOWER* Math.pow((1+PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY*0.01), technologyDefense));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_ARROWTOWER* Math.pow((1+PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY*0.01), technologyAttack));
				
				for (int i = 0; i < n; i++) {
					this.army[4].add(new ArrowTower(current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" torres arqueras al ejercito.");
		}
	}
	
	public void newCatapult(int n) {
		try {
			if (n <= 0) {
				throw new InvalidUnitAmountException(INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE);
			} else if (this.food < FOOD_COST_CATAPULT*n || this.wood < WOOD_COST_CATAPULT*n || this.iron < IRON_COST_CATAPULT*n || this.mana < MANA_COST_CATAPULT*n) {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (InvalidUnitAmountException e) {
			System.out.println(e);
			n = 0;
		} catch (ResourceException e) {
			System.out.println(e);
			int maxFood = this.food / FOOD_COST_CATAPULT;
			int maxWood = this.wood / WOOD_COST_CATAPULT;
			int maxIron = this.iron / IRON_COST_CATAPULT;
			int maxMana = this.mana / MANA_COST_CATAPULT;
			
			int minimum = Math.min(maxFood, Math.min(maxWood, Math.min(maxIron, maxMana)));
			
			if (minimum > 0) {
				n = minimum;
			} else {
				n = 0;
			}
		} finally {
			if (n > 0) {
				this.food -= FOOD_COST_CATAPULT*n;
				this.wood -= WOOD_COST_CATAPULT*n;
				this.iron -= IRON_COST_CATAPULT*n;
				this.mana -= MANA_COST_CATAPULT*n;
				
				int current_armor_plus = (int) Math.ceil(ARMOR_CATAPULT* Math.pow((1+PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY*0.01), technologyDefense));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_CATAPULT* Math.pow((1+PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY*0.01), technologyAttack));
				
				for (int i = 0; i < n; i++) {
					this.army[5].add(new Catapult(current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" catapultas al ejercito.");
		}
	}
	
	public void newRocketLauncher(int n) {
		try {
			if (n <= 0) {
				throw new InvalidUnitAmountException(INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE);
			} else if (this.food < FOOD_COST_ROCKETLAUNCHERTOWER*n || this.wood < WOOD_COST_ROCKETLAUNCHERTOWER*n || this.iron < IRON_COST_ROCKETLAUNCHERTOWER*n || this.mana < MANA_COST_ROCKETLAUNCHERTOWER*n) {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (InvalidUnitAmountException e) {
			System.out.println(e);
			n = 0;
		} catch (ResourceException e) {
			System.out.println(e);
			int maxFood = this.food / FOOD_COST_ROCKETLAUNCHERTOWER;
			int maxWood = this.wood / WOOD_COST_ROCKETLAUNCHERTOWER;
			int maxIron = this.iron / IRON_COST_ROCKETLAUNCHERTOWER;
			int maxMana = this.mana / MANA_COST_ROCKETLAUNCHERTOWER;
			
			int minimum = Math.min(maxFood, Math.min(maxWood, Math.min(maxIron, maxMana)));
			
			if (minimum > 0) {
				n = minimum;
			} else {
				n = 0;
			}
		} finally {
			if (n > 0) {
				this.food -= FOOD_COST_ROCKETLAUNCHERTOWER*n;
				this.wood -= WOOD_COST_ROCKETLAUNCHERTOWER*n;
				this.iron -= IRON_COST_ROCKETLAUNCHERTOWER*n;
				this.mana -= MANA_COST_ROCKETLAUNCHERTOWER*n;
				
				int current_armor_plus = (int) Math.ceil(ARMOR_ROCKETLAUNCHERTOWER* Math.pow((1+PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY*0.01), technologyDefense));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_ROCKETLAUNCHERTOWER* Math.pow((1+PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY*0.01), technologyAttack));
				
				for (int i = 0; i < n; i++) {
					this.army[6].add(new RocketLauncherTower(current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" torres lanzacohetes al ejercito.");
		}
	}
	
	public void newMagician(int n) {
		try {
			if (n <= 0) {
				throw new InvalidUnitAmountException(INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE);
			} else if (this.food < FOOD_COST_MAGICIAN*n || this.wood < WOOD_COST_MAGICIAN*n || this.iron < IRON_COST_MAGICIAN*n || this.mana < MANA_COST_MAGICIAN*n) {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			} else if (this.magicTower == 0) {
				throw new BuildingException(BUILDING_EXCEPTION_MESSAGE);
			}
		} catch (InvalidUnitAmountException e) {
			System.out.println(e);
			n = 0;
		} catch (ResourceException e) {
			System.out.println(e);
			int maxFood = this.food / FOOD_COST_MAGICIAN;
			int maxWood = this.wood / WOOD_COST_MAGICIAN;
			int maxIron = this.iron / IRON_COST_MAGICIAN;
			int maxMana = this.mana / MANA_COST_MAGICIAN;
			
			int minimum = Math.min(maxFood, Math.min(maxWood, Math.min(maxIron, maxMana)));
			
			if (minimum > 0) {
				n = minimum;
			} else {
				n = 0;
			}
		} catch (BuildingException e) {
			System.out.println(e);
			n = 0;
		} finally {
			if (n > 0) {
				this.food -= FOOD_COST_MAGICIAN*n;
				this.wood -= WOOD_COST_MAGICIAN*n;
				this.iron -= IRON_COST_MAGICIAN*n;
				this.mana -= MANA_COST_MAGICIAN*n;
				
				int current_armor_plus = (int) Math.ceil(ARMOR_MAGICIAN* Math.pow((1+PLUS_ARMOR_MAGICIAN_BY_TECHNOLOGY*0.01), technologyDefense));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_MAGICIAN* Math.pow((1+PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY*0.01), technologyAttack));
				
				for (int i = 0; i < n; i++) {
					this.army[7].add(new Magician(current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" magos al ejercito.");
		}
	}
	
	public void newPriest(int n) {
		try {
			if (n <= 0) {
				throw new InvalidUnitAmountException(INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE);
			} else if (this.food < FOOD_COST_PRIEST*n || this.wood < WOOD_COST_PRIEST*n || this.iron < IRON_COST_PRIEST*n || this.mana < MANA_COST_PRIEST*n) {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			} else if (this.church < this.army[8].size() + 1) {
				throw new BuildingException(BUILDING_EXCEPTION_MESSAGE);
			}
		} catch (InvalidUnitAmountException e) {
			System.out.println(e);
			n = 0;
		} catch (ResourceException e) {
			System.out.println(e);
			int maxFood = this.food / FOOD_COST_PRIEST;
			int maxWood = this.wood / WOOD_COST_PRIEST;
			int maxIron = this.iron / IRON_COST_PRIEST;
			int maxMana = this.mana / MANA_COST_PRIEST;
			
			int minimum = Math.min(maxFood, Math.min(maxWood, Math.min(maxIron, maxMana)));
			
			if (minimum > 0) {
				n = minimum;
			} else {
				n = 0;
			}
		} catch (BuildingException e) {
			System.out.println(e);
			n = 0;
		} finally {
			if (n > 0) {
				this.food -= FOOD_COST_PRIEST*n;
				this.wood -= WOOD_COST_PRIEST*n;
				this.iron -= IRON_COST_PRIEST*n;
				this.mana -= MANA_COST_PRIEST*n;
				
				int current_armor_plus = (int) Math.ceil(ARMOR_PRIEST* Math.pow((1+PLUS_ARMOR_PRIEST_BY_TECHNOLOGY*0.01), technologyDefense));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_PRIEST* Math.pow((1+PLUS_ATTACK_PRIEST_BY_TECHNOLOGY*0.01), technologyAttack));
				
				for (int i = 0; i < n; i++) {
					this.army[8].add(new Priest(current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" sacerdotes al ejercito.");
		}
	}
	
}
