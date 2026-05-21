package m3;

public interface Variables {
	// Recursos por defecto del ejercito enemigo
	public final int IRON_BASE_ENEMY_ARMY = 3000;
	public final int WOOD_BASE_ENEMY_ARMY = 6000;
	public final int FOOD_BASE_ENEMY_ARMY = 9000;
	
	// % de incremento de los recursos del ejercito enemigo despues de cada batalla
	public final int ENEMY_ARMY_INCREASE = 6;
	
	// Incremento de recursos por segundo
	public final int CIVILIZATION_IRON_GENERATED = 15;
	public final int CIVILIZATION_WOOD_GENERATED = 30;
	public final int CIVILIZATION_FOOD_GENERATED = 45;
	
	public final int CIVILIZATION_IRON_GENERATED_PER_SMITHY = (int) (2*CIVILIZATION_IRON_GENERATED);
	public final int CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY = (int) (2*CIVILIZATION_WOOD_GENERATED);
	public final int CIVILIZATION_FOOD_GENERATED_PER_FARM = (int) (2*CIVILIZATION_FOOD_GENERATED);
	public final int CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER = 8;
	
	// Costes de la tecnologia
	public final int UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST = 2000;
	public final int UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST = 1500;
	public final int UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST = 250;
	public final int UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST = 150;
	
	public final int UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST = 1500;
	public final int UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST = 2000;
	public final int UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST = 150;
	public final int UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST = 250;
	
	// Coste de las unidades ofensivas
	public final int FOOD_COST_SWORDSMAN = 250;
	public final int FOOD_COST_SPEARMAN = 400;
	public final int FOOD_COST_CROSSBOW = 600;
	public final int FOOD_COST_CANNON = 1500;
	
	public final int WOOD_COST_SWORDSMAN = 100;
	public final int WOOD_COST_SPEARMAN = 200;
	public final int WOOD_COST_CROSSBOW = 500;
	public final int WOOD_COST_CANNON = 1200;
	
	public final int IRON_COST_SWORDSMAN = 30;
	public final int IRON_COST_SPEARMAN = 70;
	public final int IRON_COST_CROSSBOW = 250;
	public final int IRON_COST_CANNON = 900;
	
	public final int MANA_COST_SWORDSMAN = 0;
	public final int MANA_COST_SPEARMAN = 0;
	public final int MANA_COST_CROSSBOW = 0;
	public final int MANA_COST_CANNON = 0;
	
	// Coste de las unidades defensivas
	public final int IRON_COST_ARROWTOWER = 400;
	public final int IRON_COST_CATAPULT = 1200;
	public final int IRON_COST_ROCKETLAUNCHERTOWER = 4000;
	
	public final int WOOD_COST_ARROWTOWER = 800;
	public final int WOOD_COST_CATAPULT = 2000;
	public final int WOOD_COST_ROCKETLAUNCHERTOWER = 5000;	
	
	public final int FOOD_COST_ARROWTOWER = 0;
	public final int FOOD_COST_CATAPULT = 0;
	public final int FOOD_COST_ROCKETLAUNCHERTOWER = 0;
	
	public final int MANA_COST_ARROWTOWER = 0;
	public final int MANA_COST_CATAPULT = 0;
	public final int MANA_COST_ROCKETLAUNCHERTOWER = 0;
	
	// Coste de las unidades especiales
	public final int FOOD_COST_MAGICIAN = 2000;
	public final int FOOD_COST_PRIEST = 2500;
	
	public final int WOOD_COST_MAGICIAN = 1200;
	public final int WOOD_COST_PRIEST = 1000;
	
	public final int IRON_COST_MAGICIAN = 600;
	public final int IRON_COST_PRIEST = 400;
	
	public final int MANA_COST_MAGICIAN = 1500;
	public final int MANA_COST_PRIEST = 2500;
	
	// Arrays de los costes de todas las unidades
	public final int[] WOOD_COST_UNITS = {WOOD_COST_SWORDSMAN, WOOD_COST_SPEARMAN, WOOD_COST_CROSSBOW, WOOD_COST_CANNON, WOOD_COST_ARROWTOWER, WOOD_COST_CATAPULT, WOOD_COST_ROCKETLAUNCHERTOWER, WOOD_COST_MAGICIAN, WOOD_COST_PRIEST};
	public final int[] IRON_COST_UNITS = {IRON_COST_SWORDSMAN, IRON_COST_SPEARMAN, IRON_COST_CROSSBOW, IRON_COST_CANNON, IRON_COST_ARROWTOWER, IRON_COST_CATAPULT, IRON_COST_ROCKETLAUNCHERTOWER, IRON_COST_MAGICIAN, IRON_COST_PRIEST};
	public final int[] FOOD_COST_UNITS = {FOOD_COST_SWORDSMAN, FOOD_COST_SPEARMAN, FOOD_COST_CROSSBOW, FOOD_COST_CANNON, FOOD_COST_ARROWTOWER, FOOD_COST_CATAPULT, FOOD_COST_ROCKETLAUNCHERTOWER, FOOD_COST_MAGICIAN, FOOD_COST_PRIEST};
	
	// Coste de las construcciones (Escalonados por tipo)
	public final int FOOD_COST_FARM = 1000;
	public final int WOOD_COST_FARM = 1500;
	public final int IRON_COST_FARM = 300;
	
	public final int FOOD_COST_CARPENTRY = 1200;
	public final int WOOD_COST_CARPENTRY = 1200;
	public final int IRON_COST_CARPENTRY = 600;
	
	public final int FOOD_COST_SMITHY = 1500;
	public final int WOOD_COST_SMITHY = 2000;
	public final int IRON_COST_SMITHY = 1200;
	
	// Edificios avanzados (Más caros)
	public final int FOOD_COST_CHURCH = 3000;
	public final int WOOD_COST_CHURCH = 4500;
	public final int IRON_COST_CHURCH = 3500;
	
	public final int FOOD_COST_MAGICTOWER = 2500;
	public final int WOOD_COST_MAGICTOWER = 3500;
	public final int IRON_COST_MAGICTOWER = 4500;
	
	// Ataque base de las unidades ofensivas
	public final int BASE_DAMAGE_SWORDSMAN = 40;
	public final int BASE_DAMAGE_SPEARMAN = 65;
	public final int BASE_DAMAGE_CROSSBOW = 110;
	public final int BASE_DAMAGE_CANNON = 260;
	
	// Ataque base de las unidades defensivas
	public final int BASE_DAMAGE_ARROWTOWER = 70;
	public final int BASE_DAMAGE_CATAPULT = 160;
	public final int BASE_DAMAGE_ROCKETLAUNCHERTOWER = 380;
	
	// Ataque base de las unidades especiales
	public final int BASE_DAMAGE_MAGICIAN = 210;
	public final int BASE_DAMAGE_PRIEST = 0;
		
	// Armadura base de las unidades ofensivas
	public final int ARMOR_SWORDSMAN = 200;
	public final int ARMOR_SPEARMAN = 350;
	public final int ARMOR_CROSSBOW = 500;
	public final int ARMOR_CANNON = 1200;
	
	// Armadura base de las unidades defensivas
	public final int ARMOR_ARROWTOWER = 600;
	public final int ARMOR_CATAPULT = 1400;
	public final int ARMOR_ROCKETLAUNCHERTOWER = 3200;
	
	// Armadura base de las unidades especiales
	public final int ARMOR_MAGICIAN = 300;
	public final int ARMOR_PRIEST = 250;
	
	// % de incremento de armadura por nivel de tecnologia
	public final int PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY = 8;
	public final int PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY = 8;
	public final int PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY = 7;
	public final int PLUS_ARMOR_CANNON_BY_TECHNOLOGY = 6;
	
	public final int PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY = 9;
	public final int PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY = 8;
	public final int PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY = 6;
	
	public final int PLUS_ARMOR_MAGICIAN_BY_TECHNOLOGY = 5;
	public final int PLUS_ARMOR_PRIEST_BY_TECHNOLOGY = 5;
	
	// % de incremento de ataque por nivel de tecnologia
	public final int PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY = 7;
	public final int PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY = 7;
	public final int PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY = 8;
	public final int PLUS_ATTACK_CANNON_BY_TECHNOLOGY = 9;
	
	public final int PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY = 8;
	public final int PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY = 8;
	public final int PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY = 10;
	
	public final int PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY = 10;
	public final int PLUS_ATTACK_PRIEST_BY_TECHNOLOGY = 0;
	
	// % de incremento de stats por nivel de experiencia
	public final int PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT = 4;
	public final int PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT = 5;
	
	// % de incremento de stats por santificacion
	public final int PLUS_ARMOR_UNIT_SANCTIFIED = 10;
	public final int PLUS_ATTACK_UNIT_SANCTIFIED = 15;
	
	// Probabilidad (%) de que una unidad ofensiva genere escombros
	public final int CHANCE_GENERATING_WASTE_SWORDSMAN = 30;
	public final int CHANCE_GENERATING_WASTE_SPEARMAN = 35;
	public final int CHANCE_GENERATING_WASTE_CROSSBOW = 40;
	public final int CHANCE_GENERATING_WASTE_CANNON = 55;
	
	// Probabilidad (%) de que una unidad defensiva genere escombros
	public final int CHANCE_GENERATING_WASTE_ARROWTOWER = 40;
	public final int CHANCE_GENERATING_WASTE_CATAPULT = 50;
	public final int CHANCE_GENERATING_WASTE_ROCKETLAUNCHERTOWER = 60;
	
	// Probabilidad (%) de que una unidad especial genere escombros
	public final int CHANCE_GENERATING_WASTE_MAGICIAN = 0;
	public final int CHANCE_GENERATING_WASTE_PRIEST = 0;
	
	public final int[] CHANCE_GENERATING_WASTE_UNITS = {CHANCE_GENERATING_WASTE_SWORDSMAN, CHANCE_GENERATING_WASTE_SPEARMAN, CHANCE_GENERATING_WASTE_CROSSBOW, CHANCE_GENERATING_WASTE_CANNON, CHANCE_GENERATING_WASTE_ARROWTOWER, CHANCE_GENERATING_WASTE_CATAPULT, CHANCE_GENERATING_WASTE_ROCKETLAUNCHERTOWER, CHANCE_GENERATING_WASTE_MAGICIAN};
	
	// Probabilidad (%) de que una unidad vuelva a atacar (Un poco más ajustado para que no sea fiesta de ataques infinitos)
	public final int CHANCE_ATTACK_AGAIN_SWORDSMAN = 10;
	public final int CHANCE_ATTACK_AGAIN_SPEARMAN = 12;
	public final int CHANCE_ATTACK_AGAIN_CROSSBOW = 25;
	public final int CHANCE_ATTACK_AGAIN_CANNON = 15;
	
	public final int CHANCE_ATTACK_AGAIN_ARROWTOWER = 15;
	public final int CHANCE_ATTACK_AGAIN_CATAPULT = 10;
	public final int CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER = 5;
	
	public final int CHANCE_ATTACK_AGAIN_MAGICIAN = 35;
	public final int CHANCE_ATTACK_AGAIN_PRIEST = 0;
	
	public final int[] CHANCE_ATTACK_AGAIN_UNITS = {CHANCE_ATTACK_AGAIN_SWORDSMAN, CHANCE_ATTACK_AGAIN_SPEARMAN, CHANCE_ATTACK_AGAIN_CROSSBOW, CHANCE_ATTACK_AGAIN_CANNON, CHANCE_ATTACK_AGAIN_ARROWTOWER, CHANCE_ATTACK_AGAIN_CATAPULT, CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER, CHANCE_ATTACK_AGAIN_MAGICIAN};
	
	// Probabilidad de atacar de cada unidad
	public final int[] CHANCE_ATTACK_ARMY_UNITS = {20,20,20,15,10,10,3,2};
	
	// % de perdidas que genera una unidad respecto a su coste
	public final int PERCENTATGE_WASTE = 45;
	
	public final int DEFEAT_PERCENTAGE = 30;
	
	// Mensajes de excepciones
	public final String RESOURCE_EXCEPTION_MESSAGE = "Recursos insuficientes";
	public final String INVALID_UNIT_AMOUNT_EXCEPTION_MESSAGE = "Valor inválido de unidades";
	public final String BUILDING_EXCEPTION_MESSAGE = "Estructuras requeridas insuficientes";
	
	// MAX VALUE 
	public final int MAX_VALUE = 9999999;
}