package m3;

public class Swordsman extends AttackUnit {
	
	// Constructor 1 (con parametros)
	public Swordsman(int id, int initialArmor, int baseDamage) {
		super(id, initialArmor, baseDamage);
	}
	
	// Constructor 2 (sin parametros)
	public Swordsman() {
		super(0, ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN);
	}
	
	// Devuelve el coste de comida para crear la unidad
	public int getFoodCost() {
		return FOOD_COST_SWORDSMAN;
	}
	
	// Devuelve el coste de madera para crear la unidad
	public int getWoodCost() {
		return WOOD_COST_SWORDSMAN;
	}

	// Devuelve el coste de hierro para crear la unidad
	public int getIronCost() {
		return IRON_COST_SWORDSMAN;
	}
	
	// Devuelve 0 porque Swordsman no gasta mana.
	public int getManaCost() {
		return MANA_COST_SWORDSMAN;
	}
	
	// Devuelve la probabilidad de generar residuos al ser eliminada
	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATING_WASTE_SWORDSMAN;
	}

	// Devuelve la probabilidad de volver a atacar
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_SWORDSMAN;
	}
	
}
