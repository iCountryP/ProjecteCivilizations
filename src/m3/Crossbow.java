package m3;

public class Crossbow extends AttackUnit {
	
	// Constructor 1 (con parametros)
	public Crossbow(int initialArmor, int baseDamage) {
		super(initialArmor, baseDamage);
	}
	
	// Constructor 2 (sin parametros)
	public Crossbow() {
		super(ARMOR_CROSSBOW, BASE_DAMAGE_CROSSBOW);
	}
	
	// Devuelve el coste de comida para crear la unidad
	public int getFoodCost() {
		return FOOD_COST_CROSSBOW;
	}
	
	// Devuelve el coste de madera para crear la unidad
	public int getWoodCost() {
		return WOOD_COST_CROSSBOW;
	}

	// Devuelve el coste de hierro para crear la unidad
	public int getIronCost() {
		return IRON_COST_CROSSBOW;
	}
	
	// Devuelve 0 porque Crossbow no gasta mana.
	public int getManaCost() {
		return 0;
	}
	
	// Devuelve la probabilidad de generar residuos al ser eliminada
	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATNG_WASTE_CROSSBOW;
	}

	// Devuelve la probabilidad de volver a atacar
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_CROSSBOW;
	}
}
