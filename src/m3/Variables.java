package m3;

public interface Variables {
	// Recursos por defecto del ejercito enemigo
	public final int IRON_BASE_ENEMY_ARMY = 15000;
	public final int WOOD_BASE_ENEMY_ARMY = 35000;
	public final int FOOD_BASE_ENEMY_ARMY = 40000;
	
	// % de incremento de los recursos del ejercito enemigo despues de cada batalla
	public final int ENEMY_ARMY_INCREASE = 8;
	
	// Incremento de recursos por segundo
	public final int CIVILIZATION_IRON_GENERATED = 1;
	public final int CIVILIZATION_WOOD_GENERATED = 3;
	public final int CIVILIZATION_FOOD_GENERATED = 5;
	
	public final int CIVILIZATION_IRON_GENERATED_PER_SMITHY = (int) (1.5*CIVILIZATION_IRON_GENERATED);
	public final int CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY = (int) (1.5*CIVILIZATION_WOOD_GENERATED);
	public final int CIVILIZATION_FOOD_GENERATED_PER_FARM = (int) (1.5*CIVILIZATION_FOOD_GENERATED);
	public final int CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER = 5;
	
	// Costes de la tecnologia
	public final int UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST = 1500;
	public final int UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST = 1000;
	public final int UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST = 80;
	public final int UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST = 50;
	
	public final int UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST = 1000;
	public final int UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST = 1500;
	public final int UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST = 50;
	public final int UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST = 80;
	
	// Coste de las unidades ofensivas
	public final int FOOD_COST_SWORDSMAN = 1500;
	public final int FOOD_COST_SPEARMAN = 2000;
	public final int FOOD_COST_CROSSBOW = 800;
	public final int FOOD_COST_CANNON = 500;
	
	public final int WOOD_COST_SWORDSMAN = 500;
	public final int WOOD_COST_SPEARMAN = 1200;
	public final int WOOD_COST_CROSSBOW = 3500;
	public final int WOOD_COST_CANNON = 5000;
	
	public final int IRON_COST_SWORDSMAN = 200;
	public final int IRON_COST_SPEARMAN = 400;
	public final int IRON_COST_CROSSBOW = 1500;
	public final int IRON_COST_CANNON = 8000;
	
	public final int MANA_COST_SWORDSMAN = 0;
	public final int MANA_COST_SPEARMAN = 0;
	public final int MANA_COST_CROSSBOW = 0;
	public final int MANA_COST_CANNON = 0;
	
	// Coste de las unidades defensivas
	public final int IRON_COST_ARROWTOWER = 800;
	public final int IRON_COST_CATAPULT = 2500;
	public final int IRON_COST_ROCKETLAUNCHERTOWER = 10000;
	
	public final int WOOD_COST_ARROWTOWER = 2500;
	public final int WOOD_COST_CATAPULT = 4500;
	public final int WOOD_COST_ROCKETLAUNCHERTOWER = 8000;	
	
	public final int FOOD_COST_ARROWTOWER = 0;
	public final int FOOD_COST_CATAPULT = 0;
	public final int FOOD_COST_ROCKETLAUNCHERTOWER = 0;
	
	public final int MANA_COST_ARROWTOWER = 0;
	public final int MANA_COST_CATAPULT = 0;
	public final int MANA_COST_ROCKETLAUNCHERTOWER = 0;
	
	// Coste de las unidades especiales
	public final int FOOD_COST_MAGICIAN = 3000;
	public final int FOOD_COST_PRIEST = 4000;
	
	public final int WOOD_COST_MAGICIAN = 1500;
	public final int WOOD_COST_PRIEST = 1000;
	
	public final int IRON_COST_MAGICIAN = 800;
	public final int IRON_COST_PRIEST = 500;
	
	public final int MANA_COST_MAGICIAN = 3000;
	public final int MANA_COST_PRIEST = 6000;
	
	// Arrays de los costes de todas las unidades
	public final int[] WOOD_COST_UNITS = {WOOD_COST_SWORDSMAN, WOOD_COST_SPEARMAN, WOOD_COST_CROSSBOW, WOOD_COST_CANNON, WOOD_COST_ARROWTOWER, WOOD_COST_CATAPULT, WOOD_COST_ROCKETLAUNCHERTOWER, WOOD_COST_MAGICIAN, WOOD_COST_PRIEST};
	public final int[] IRON_COST_UNITS = {IRON_COST_SWORDSMAN, IRON_COST_SPEARMAN, IRON_COST_CROSSBOW, IRON_COST_CANNON, IRON_COST_ARROWTOWER, IRON_COST_CATAPULT, IRON_COST_ROCKETLAUNCHERTOWER, IRON_COST_MAGICIAN, IRON_COST_PRIEST};
	public final int[] FOOD_COST_UNITS = {FOOD_COST_SWORDSMAN, FOOD_COST_SPEARMAN, FOOD_COST_CROSSBOW, FOOD_COST_CANNON, FOOD_COST_ARROWTOWER, FOOD_COST_CATAPULT, FOOD_COST_ROCKETLAUNCHERTOWER, FOOD_COST_MAGICIAN, FOOD_COST_PRIEST};
	
	// Coste de las construcciones (Escalonados por tipo)
	public final int FOOD_COST_FARM = 1500;
	public final int WOOD_COST_FARM = 3000;
	public final int IRON_COST_FARM = 500;
	
	public final int FOOD_COST_CARPENTRY = 2000;
	public final int WOOD_COST_CARPENTRY = 2500;
	public final int IRON_COST_CARPENTRY = 1000;
	
	public final int FOOD_COST_SMITHY = 2500;
	public final int WOOD_COST_SMITHY = 4000;
	public final int IRON_COST_SMITHY = 2000;
	
	// Edificios avanzados (Más caros)
	public final int FOOD_COST_CHURCH = 5000;
	public final int WOOD_COST_CHURCH = 8000;
	public final int IRON_COST_CHURCH = 6000;
	
	public final int FOOD_COST_MAGICTOWER = 4000;
	public final int WOOD_COST_MAGICTOWER = 6000;
	public final int IRON_COST_MAGICTOWER = 8000;
	
	// Ataque base de las unidades ofensivas
	public final int BASE_DAMAGE_SWORDSMAN = 120;
	public final int BASE_DAMAGE_SPEARMAN = 180;
	public final int BASE_DAMAGE_CROSSBOW = 450;
	public final int BASE_DAMAGE_CANNON = 900;
	
	// Ataque base de las unidades defensivas
	public final int BASE_DAMAGE_ARROWTOWER = 200;
	public final int BASE_DAMAGE_CATAPULT = 500;
	public final int BASE_DAMAGE_ROCKETLAUNCHERTOWER = 1200;
	
	// Ataque base de las unidades especiales
	public final int BASE_DAMAGE_MAGICIAN = 1500;
	public final int BASE_DAMAGE_PRIEST = 0;
		
	// Armadura base de las unidades ofensivas
	public final int ARMOR_SWORDSMAN = 600;
	public final int ARMOR_SPEARMAN = 900;
	public final int ARMOR_CROSSBOW = 1500;
	public final int ARMOR_CANNON = 3500;
	
	// Armadura base de las unidades defensivas
	public final int ARMOR_ARROWTOWER = 1800;
	public final int ARMOR_CATAPULT = 3000;
	public final int ARMOR_ROCKETLAUNCHERTOWER = 5500;
	
	// Armadura base de las unidades especiales
	public final int ARMOR_MAGICIAN = 800;
	public final int ARMOR_PRIEST = 500;
	
	// % de incremento de armadura por nivel de tecnologia
	public final int PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY = 6;
	public final int PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY = 6;
	public final int PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY = 5;
	public final int PLUS_ARMOR_CANNON_BY_TECHNOLOGY = 4;
	
	public final int PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY = 6;
	public final int PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY = 5;
	public final int PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY = 4;
	
	public final int PLUS_ARMOR_MAGICIAN_BY_TECHNOLOGY = 3;
	public final int PLUS_ARMOR_PRIEST_BY_TECHNOLOGY = 3;
	
	// % de incremento de ataque por nivel de tecnologia
	public final int PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY = 5;
	public final int PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY = 5;
	public final int PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY = 6;
	public final int PLUS_ATTACK_CANNON_BY_TECHNOLOGY = 7;
	
	public final int PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY = 6;
	public final int PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY = 6;
	public final int PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY = 7;
	
	public final int PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY = 8;
	public final int PLUS_ATTACK_PRIEST_BY_TECHNOLOGY = 0;
	
	// % de incremento de stats por nivel de experiencia
	public final int PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT = 3;
	public final int PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT = 4;
	
	// % de incremento de stats por santificacion
	public final int PLUS_ARMOR_UNIT_SANCTIFIED = 8;
	public final int PLUS_ATTACK_UNIT_SANCTIFIED = 10;
	
	// Probabilidad (%) de que una unidad ofensiva genere escombros
	public final int CHANCE_GENERATING_WASTE_SWORDSMAN = 40;
	public final int CHANCE_GENERATING_WASTE_SPEARMAN = 45;
	public final int CHANCE_GENERATING_WASTE_CROSSBOW = 55;
	public final int CHANCE_GENERATING_WASTE_CANNON = 70;
	
	// Probabilidad (%) de que una unidad defensiva genere escombros
	public final int CHANCE_GENERATING_WASTE_ARROWTOWER = 50;
	public final int CHANCE_GENERATING_WASTE_CATAPULT = 60;
	public final int CHANCE_GENERATING_WASTE_ROCKETLAUNCHERTOWER = 75;
	
	// Probabilidad (%) de que una unidad especial genere escombros
	public final int CHANCE_GENERATING_WASTE_MAGICIAN = 0;
	public final int CHANCE_GENERATING_WASTE_PRIEST = 0;
	
	public final int[] CHANCE_GENERATING_WASTE_UNITS = {CHANCE_GENERATING_WASTE_SWORDSMAN, CHANCE_GENERATING_WASTE_SPEARMAN, CHANCE_GENERATING_WASTE_CROSSBOW, CHANCE_GENERATING_WASTE_CANNON, CHANCE_GENERATING_WASTE_ARROWTOWER, CHANCE_GENERATING_WASTE_CATAPULT, CHANCE_GENERATING_WASTE_ROCKETLAUNCHERTOWER, CHANCE_GENERATING_WASTE_MAGICIAN};
	
	// Probabilidad (%) de que una unidad vuelva a atacar (Un poco más ajustado para que no sea fiesta de ataques infinitos)
	public final int CHANCE_ATTACK_AGAIN_SWORDSMAN = 5;
	public final int CHANCE_ATTACK_AGAIN_SPEARMAN = 8;
	public final int CHANCE_ATTACK_AGAIN_CROSSBOW = 20;
	public final int CHANCE_ATTACK_AGAIN_CANNON = 40;
	
	public final int CHANCE_ATTACK_AGAIN_ARROWTOWER = 15;
	public final int CHANCE_ATTACK_AGAIN_CATAPULT = 25;
	public final int CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER = 10;
	
	public final int CHANCE_ATTACK_AGAIN_MAGICIAN = 50;
	public final int CHANCE_ATTACK_AGAIN_PRIEST = 0;
	
	public final int[] CHANCE_ATTACK_AGAIN_UNITS = {CHANCE_ATTACK_AGAIN_SWORDSMAN, CHANCE_ATTACK_AGAIN_SPEARMAN, CHANCE_ATTACK_AGAIN_CROSSBOW, CHANCE_ATTACK_AGAIN_CANNON, CHANCE_ATTACK_AGAIN_ARROWTOWER, CHANCE_ATTACK_AGAIN_CATAPULT, CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER, CHANCE_ATTACK_AGAIN_MAGICIAN};
	
	// Probabilidad de atacar de cada unidad
	public final int[] CHANCE_ATTACK_ARMY_UNITS = {4,9,13,37,4,9,14,10};
	
	// % de perdidas que genera una unidad respecto a su coste
	public final int PERCENTATGE_WASTE = 65;
	
	public final int DEFEAT_PERCENTAGE = 25;
	
	// Mensajes de excepciones
	public final String RESOURCE_EXCEPTION_MESSAGE = "Recursos insuficientes";
	public final String INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE = "Valor inválido de unidades";
	public final String BUILDING_EXCEPTION_MESSAGE = "Estructuras requeridas insuficientes";
	
	// MAX VALUE 
	public final int MAX_VALUE = 9999999;
}