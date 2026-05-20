package m3;

public class Crossbow extends AttackUnit {
	
	// Constructor 1 (con parametros)
	public Crossbow(int id, int initialArmor, int baseDamage) {
		super(id, initialArmor, baseDamage);
	}
	
	// Constructor 2 (sin parametros)
	public Crossbow() {
		super(0, ARMOR_CROSSBOW, BASE_DAMAGE_CROSSBOW);
	}
	
	public Crossbow(int id, int initialArmor, int baseDamage, int experience, boolean sanctified) {
		super(id, initialArmor, baseDamage);
		this.gainExperience(experience);
		if (sanctified) {
			this.sanctify();
		}
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
		return MANA_COST_CROSSBOW;
	}
	
	// Devuelve la probabilidad de generar residuos al ser eliminada
	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATING_WASTE_CROSSBOW;
	}

	// Devuelve la probabilidad de volver a atacar
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_CROSSBOW;
	}
}
