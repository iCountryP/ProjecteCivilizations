package m3;

public class Spearman extends AttackUnit {
	
	// Constructor 1 (con parametros)
	public Spearman(int id, int initialArmor, int baseDamage) {
		super(id, initialArmor, baseDamage);
	}
	
	// Constructor 2 (sin parametros)
	public Spearman() {
		super(0, ARMOR_SPEARMAN, BASE_DAMAGE_SPEARMAN);
	}
	
	public Spearman(int id, int initialArmor, int baseDamage, int experience, boolean sanctified) {
		super(id, initialArmor, baseDamage);
		this.gainExperience(experience);
		if (sanctified) {
			this.sanctify();
		}
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
		return MANA_COST_SPEARMAN;
	}
	
	// Devuelve la probabilidad de generar residuos al ser eliminada
	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATING_WASTE_SPEARMAN;
	}

	// Devuelve la probabilidad de volver a atacar
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_SPEARMAN;
	}
		
}
