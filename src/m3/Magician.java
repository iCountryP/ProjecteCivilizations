package m3;

public class Magician extends DefenseUnit implements MilitaryUnit, Variables {

	public Magician(int initialArmor, int baseDamage) {
		super(initialArmor, baseDamage);
	}

	public int getFoodCost() {
		return FOOD_COST_MAGICIAN;
	}

	public int getWoodCost() {
		return WOOD_COST_MAGICIAN;
	}

	public int getIronCost() {
		return IRON_COST_MAGICIAN;
	}

	public int getManaCost() {
		return MANA_COST_MAGICIAN;
	}

	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATING_WASTE_MAGICIAN;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_MAGICIAN;
	}

}
