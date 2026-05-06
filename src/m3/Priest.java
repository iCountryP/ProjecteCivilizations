package m3;

public class Priest extends DefenseUnit implements MilitaryUnit, Variables {

	public Priest(int initialArmor, int baseDamage) {
		super(initialArmor, baseDamage);
	}

	public int getFoodCost() {
		return FOOD_COST_PRIEST;
	}

	public int getWoodCost() {
		return WOOD_COST_PRIEST;
	}

	public int getIronCost() {
		return IRON_COST_PRIEST;
	}

	public int getManaCost() {
		return MANA_COST_PRIEST;
	}

	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATING_WASTE_PRIEST;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_PRIEST;
	}

}
