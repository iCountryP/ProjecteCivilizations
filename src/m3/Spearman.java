package m3;

public class Spearman extends AttackUnit {
	
	// Constructor 1 (con parametros)
	public Spearman(int initialArmor, int baseDamage) {
		super(initialArmor, baseDamage);
	}
	
	// Constructor 2 (sin parametros)
	public Spearman() {
		super(ARMOR_SPEARMAN, BASE_DAMAGE_SPEARMAN);
	}
	
	// Devuelve el coste de comida para crear la unidad
	public int getFoodCost() {
		return FOOD_COST_SPEARMAN;
	}
	
	// Devuelve el coste de madera para crear la unidad
	public int getWoodCost() {
		return WOOD_COST_SPEARMAN;
	}

	// Devuelve el coste de hierro para crear la unidad
	public int getIronCost() {
		return IRON_COST_SPEARMAN;
	}
	
	// Devuelve 0 porque Spearman no gasta mana.
	public int getManaCost() {
		return 0;
	}
	
	// Devuelve la probabilidad de generar residuos al ser eliminada
	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATNG_WASTE_SPEARMAN;
	}

	// Devuelve la probabilidad de volver a atacar
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_SPEARMAN;
	}
		
}
