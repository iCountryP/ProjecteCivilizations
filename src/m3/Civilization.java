package m3;

import java.util.ArrayList;

public class Civilization implements Variables {

	private int id;
	private String name;
	
	private int technologyDefense;
	private int technologyAttack;

	private int wood;
	private int iron;
	private int food;
	private int mana;
	
	private ArrayList<int[]> magicTower;
	private ArrayList<int[]> church;
	private ArrayList<int[]> farm;
	private ArrayList<int[]> smithy;
	private ArrayList<int[]> carpentry;

	private int battles;
	private boolean gameOver;
	
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
	
	// Constructor para nuevas civilizaciones
	public Civilization(String name) {
		// Ver que onda con esto, instanciacion de las unidades
		this.name = name;
		System.out.println("Nueva civilizacion: " + this.name);
		
		this.technologyAttack = 0;
		this.technologyDefense = 0;
		
		this.wood = 50000;
		this.iron = 50000;
		this.food = 50000;
		this.mana = 0;
		
		this.magicTower = new ArrayList<int[]>();
		this.church = new ArrayList<int[]>();
		this.farm = new ArrayList<int[]>();
		this.smithy = new ArrayList<int[]>();
		this.carpentry = new ArrayList<int[]>();
		
		this.battles = 0;
		this.gameOver = false;
		
		for (int i = 0; i < this.army.length; i++) {
		    this.army[i] = new ArrayList<MilitaryUnit>();
		}
		
	}
	
	// Constructor para cargar civilizaciones de la base de datos mediante su id
	public Civilization(int id) {
		this.id = id;
		this.gameOver = false;
		
		this.magicTower = new ArrayList<int[]>();
		this.church = new ArrayList<int[]>();
		this.farm = new ArrayList<int[]>();
		this.smithy = new ArrayList<int[]>();
		this.carpentry = new ArrayList<int[]>();
		
		for (int i = 0; i < this.army.length; i++) {
		    this.army[i] = new ArrayList<MilitaryUnit>();
		}
	}
	
	public void addWood(int quantity) {
		this.wood += quantity;
	}
	
	public void addIron(int quantity) {
		this.iron += quantity;
	}
	
	// Metodos para añadir estructuras a la civilizacion
	
	public void newChurch(int x, int y) {
		try {
			if (this.food >= FOOD_COST_CHURCH && this.wood >= WOOD_COST_CHURCH && this.iron >= IRON_COST_CHURCH) {
				this.food -= FOOD_COST_CHURCH;
				this.wood -= WOOD_COST_CHURCH;
				this.iron -= IRON_COST_CHURCH;
				this.church.add(new int[]{0, x, y});
			} else {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (ResourceException e) {
			System.out.println(e);
		}
	}
	
	public void newMagicTower(int x, int y) {
		try {
			if (this.food >= FOOD_COST_MAGICTOWER && this.wood >= WOOD_COST_MAGICTOWER && this.iron >= IRON_COST_MAGICTOWER) {
				this.food -= FOOD_COST_MAGICTOWER;
				this.wood -= WOOD_COST_MAGICTOWER;
				this.iron -= IRON_COST_MAGICTOWER;
				this.magicTower.add(new int[]{0, x, y});
			} else {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (ResourceException e) {
			System.out.println(e);
		}
	}
	
	public void newFarm(int x, int y) {
		try {
			if (this.food >= FOOD_COST_FARM && this.wood >= WOOD_COST_FARM && this.iron >= IRON_COST_FARM) {
				this.food -= FOOD_COST_FARM;
				this.wood -= WOOD_COST_FARM;
				this.iron -= IRON_COST_FARM;
				this.farm.add(new int[]{0, x, y});
			} else {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (ResourceException e) {
			System.out.println(e);
		}
	}
	
	public void newCarpentry(int x, int y) {
		try {
			if (this.food >= FOOD_COST_CARPENTRY && this.wood >= WOOD_COST_CARPENTRY && this.iron >= IRON_COST_CARPENTRY) {
				this.food -= FOOD_COST_CARPENTRY;
				this.wood -= WOOD_COST_CARPENTRY;
				this.iron -= IRON_COST_CARPENTRY;
				this.carpentry.add(new int[]{0, x, y});
			} else {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			}
		} catch (ResourceException e) {
			System.out.println(e);
		}
	}
	
	public void newSmithy(int x, int y) {
		try {
			if (this.food >= FOOD_COST_SMITHY && this.wood >= WOOD_COST_SMITHY && this.iron >= IRON_COST_SMITHY) {
				this.food -= FOOD_COST_SMITHY;
				this.wood -= WOOD_COST_SMITHY;
				this.iron -= IRON_COST_SMITHY;
				this.smithy.add(new int[]{0, x, y});
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
				this.iron -= current_iron_cost;
			    this.wood -= current_wood_cost;
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
				this.iron -= current_iron_cost;
			    this.wood -= current_wood_cost;
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
			//System.out.println("MINIMUM VALE ESTO ");
			int maxMana = MAX_VALUE;


			int minimum = Math.min(maxFood, Math.min(maxWood, Math.min(maxIron, maxMana)));
			if (minimum > 0) {
				n = minimum;
			} else {
				n = 0;
			}
		} finally {
			System.out.println("N VALE ESTO "+n);
			if (n > 0) {
				this.food -= FOOD_COST_SWORDSMAN*n;
				this.wood -= WOOD_COST_SWORDSMAN*n;
				this.iron -= IRON_COST_SWORDSMAN*n;
				this.mana -= MANA_COST_SWORDSMAN*n;
				
				int current_armor_plus = (int) Math.ceil(ARMOR_SWORDSMAN* Math.pow((1+PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY*0.01), technologyDefense));
				int current_attack_plus = (int) Math.ceil(BASE_DAMAGE_SWORDSMAN* Math.pow((1+PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY*0.01), technologyAttack));
				
				for (int i = 0; i < n; i++) {
					this.army[0].add(new Swordsman(0, current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" espadachines al ejercito.");
		}
		
	}
	
	public void loadSwordsman(int unit_id, int initialArmor, int baseDamage, int experience, String sanctified) {
		boolean sanctifiedBool = false;
		if (sanctified.equals("TRUE")) {
			sanctifiedBool = true;
		}
		this.army[0].add(new Swordsman(unit_id, initialArmor, baseDamage, experience, sanctifiedBool));
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
			int maxMana = MAX_VALUE;
			
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
					this.army[1].add(new Spearman(0, current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" lanceros al ejercito.");
		}
	}
	
	public void loadSpearman(int unit_id, int initialArmor, int baseDamage, int experience, String sanctified) {
		boolean sanctifiedBool = false;
		if (sanctified.equals("TRUE")) {
			sanctifiedBool = true;
		}
		this.army[1].add(new Spearman(unit_id, initialArmor, baseDamage, experience, sanctifiedBool));
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
			int maxFood = MAX_VALUE;
			int maxWood = this.wood / WOOD_COST_CROSSBOW;
			int maxIron = this.iron / IRON_COST_CROSSBOW;
			int maxMana = MAX_VALUE;
			
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
					this.army[2].add(new Crossbow(0, current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" ballestas al ejercito.");
		}
	}
	
	public void loadCrossbow(int unit_id, int initialArmor, int baseDamage, int experience, String sanctified) {
		boolean sanctifiedBool = false;
		if (sanctified.equals("TRUE")) {
			sanctifiedBool = true;
		}
		this.army[2].add(new Crossbow(unit_id, initialArmor, baseDamage, experience, sanctifiedBool));
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
			int maxFood = MAX_VALUE;
			int maxWood = this.wood / WOOD_COST_CANNON;
			int maxIron = this.iron / IRON_COST_CANNON;
			int maxMana = MAX_VALUE;
			
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
					this.army[3].add(new Cannon(0, current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" cañones al ejercito.");
		}
	}
	
	public void loadCannon(int unit_id, int initialArmor, int baseDamage, int experience, String sanctified) {
		boolean sanctifiedBool = false;
		if (sanctified.equals("TRUE")) {
			sanctifiedBool = true;
		}
		this.army[3].add(new Cannon(unit_id, initialArmor, baseDamage, experience, sanctifiedBool));
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
			int maxFood = MAX_VALUE;
			int maxWood = this.wood / WOOD_COST_ARROWTOWER;
			int maxIron = MAX_VALUE;
			int maxMana = MAX_VALUE;
			
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
					this.army[4].add(new ArrowTower(0, current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" torres arqueras al ejercito.");
		}
	}
	
	public void loadArrowTower(int unit_id, int initialArmor, int baseDamage, int experience, String sanctified) {
		boolean sanctifiedBool = false;
		if (sanctified.equals("TRUE")) {
			sanctifiedBool = true;
		}
		this.army[4].add(new ArrowTower(unit_id, initialArmor, baseDamage, experience, sanctifiedBool));
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
			int maxFood = MAX_VALUE;
			int maxWood = this.wood / WOOD_COST_CATAPULT;
			int maxIron = this.iron / IRON_COST_CATAPULT;
			int maxMana = MAX_VALUE;
			
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
					this.army[5].add(new Catapult(0, current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" catapultas al ejercito.");
		}
	}
	
	public void loadCatapult(int unit_id, int initialArmor, int baseDamage, int experience, String sanctified) {
		boolean sanctifiedBool = false;
		if (sanctified.equals("TRUE")) {
			sanctifiedBool = true;
		}
		this.army[5].add(new Catapult(unit_id, initialArmor, baseDamage, experience, sanctifiedBool));
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
			int maxFood = MAX_VALUE;
			int maxWood = this.wood / WOOD_COST_ROCKETLAUNCHERTOWER;
			int maxIron = this.iron / IRON_COST_ROCKETLAUNCHERTOWER;
			int maxMana = MAX_VALUE;
			
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
					this.army[6].add(new RocketLauncherTower(0, current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" torres lanzacohetes al ejercito.");
		}
	}
	
	public void loadRocketLauncher(int unit_id, int initialArmor, int baseDamage, int experience, String sanctified) {
		boolean sanctifiedBool = false;
		if (sanctified.equals("TRUE")) {
			sanctifiedBool = true;
		}
		this.army[6].add(new RocketLauncherTower(unit_id, initialArmor, baseDamage, experience, sanctifiedBool));
	}
	
	public void newMagician(int n) {
		try {
			if (n <= 0) {
				throw new InvalidUnitAmountException(INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE);
			} else if (this.food < FOOD_COST_MAGICIAN*n || this.wood < WOOD_COST_MAGICIAN*n || this.iron < IRON_COST_MAGICIAN*n || this.mana < MANA_COST_MAGICIAN*n) {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			} else if (this.magicTower.size() == 0) {
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
					this.army[7].add(new Magician(0, current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" magos al ejercito.");
		}
	}
	
	public void loadMagician(int unit_id, int initialArmor, int baseDamage, int experience) {
		this.army[7].add(new Magician(unit_id, initialArmor, baseDamage, experience));
	}
	
	public void newPriest(int n) {
		try {
			if (n <= 0) {
				throw new InvalidUnitAmountException(INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE);
			} else if (this.food < FOOD_COST_PRIEST*n || this.wood < WOOD_COST_PRIEST*n || this.iron < IRON_COST_PRIEST*n || this.mana < MANA_COST_PRIEST*n) {
				throw new ResourceException(RESOURCE_EXCEPTION_MESSAGE);
			} else if (this.church.size() < this.army[8].size() + 1) {
				throw new BuildingException(BUILDING_EXCEPTION_MESSAGE);
			}
		} catch (InvalidUnitAmountException e) {
			System.out.println(e);
			n = 0;
		} catch (ResourceException e) {
			System.out.println(e);
			int maxFood = this.food / FOOD_COST_PRIEST;
			int maxWood = MAX_VALUE;
			int maxIron = MAX_VALUE;
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
					this.army[8].add(new Priest(0, current_armor_plus, current_attack_plus));
				}
			}
			System.out.println("Se agregaron "+n+" sacerdotes al ejercito.");
		}
	}
	
	public void loadPriest(int unit_id, int initialArmor, int baseDamage, int experience) {
		this.army[8].add(new Priest(unit_id, initialArmor, baseDamage, experience));
	}
	
	// método que controla el aumento de recuroos y teniendo en cuenta la cantidad de edificios que hay
	public void aumentoRecursos() {
	    this.food = this.food + CIVILIZATION_FOOD_GENERATED + (this.farm.size() * CIVILIZATION_FOOD_GENERATED_PER_FARM);
	    this.wood = this.wood + CIVILIZATION_WOOD_GENERATED + (this.carpentry.size() * CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY);
	    this.iron = this.iron + CIVILIZATION_IRON_GENERATED + (this.smithy.size() * CIVILIZATION_IRON_GENERATED_PER_SMITHY);
	    this.mana = this.mana + (this.magicTower.size() * CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER);
	    //System.out.println(this.magicTower);
	}
	
	// Getters & Setters

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getIron() {
		return iron;
	}

	public void setIron(int iron) {
		this.iron = iron;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getTechnologyDefense() {
		return technologyDefense;
	}

	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}

	public int getTechnologyAttack() {
		return technologyAttack;
	}

	public void setTechnologyAttack(int technologyAttack) {
		this.technologyAttack = technologyAttack;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getMagicTower() {
		return this.magicTower.size();
	}
	
	public void loadMagicTower(int x, int y) {
		this.magicTower.add(new int[]{1, x, y});
	}

	public int getChurch() {
		return this.church.size();
	}
	
	public void loadChurch(int x, int y) {
		this.church.add(new int[]{1, x, y});
	}

	public int getFarm() {
		return this.farm.size();
	}
	
	public void loadFarm(int x, int y) {
		this.farm.add(new int[]{1, x, y});
	}

	public int getSmithy() {
		return this.smithy.size();
	}
	
	public void loadSmithy(int x, int y) {
		this.smithy.add(new int[]{1, x, y});
	}

	public int getCarpentry() {
		return this.carpentry.size();
	}
	
	public void loadCarpentry(int x, int y) {
		this.carpentry.add(new int[]{1, x, y});
	}

	public int getBattles() {
		return battles;
	}

	public void setBattles(int battles) {
		this.battles = battles;
	}

	public ArrayList<MilitaryUnit>[] getArmy() {
		return army;
	}

	public void setArmy(ArrayList<MilitaryUnit>[] army) {
		this.army = army;
	}
	
	public int getSwordsmanCount() {
		return this.army[0].size();
	}
	
	public int getSpearmanCount() {
		return this.army[1].size();
	}
	
	public int getCrossbowCount() {
		return this.army[2].size();
	}
	
	public int getCannonCount() {
		return this.army[3].size();
	}
	
	public int getArrowTowerCount() {
		return this.army[4].size();
	}
	
	public int getCatapultCount() {
		return this.army[5].size();
	}
	
	public int getRocketLauncherCount() {
		return this.army[6].size();
	}
	
	public int getMagicianCount() {
		return this.army[7].size();
	}
	
	public int getPriestCount() {
		return this.army[8].size();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public boolean getGameOver() {
		return this.gameOver;
	}
	
	public ArrayList<int[]> getMagicTowerPositions() {
		return this.magicTower;
	}
	
	public ArrayList<int[]> getChurchPositions() {
		return this.church;
	}
	
	public ArrayList<int[]> getFarmPositions() {
		return this.farm;
	}
	
	public ArrayList<int[]> getSmithyPositions() {
		return this.smithy;
	}
	
	public ArrayList<int[]> getCarpentryPositions() {
		return this.carpentry;
	}
}
