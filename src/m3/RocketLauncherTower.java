package m3;

public class RocketLauncherTower extends DefenseUnit implements MilitaryUnit, Variables {

	public RocketLauncherTower(int initialArmor, int baseDamage) {
		super(initialArmor, baseDamage);
	}

	public int getFoodCost() {
		return FOOD_COST_ARROWTOWER;
	}

	public int getWoodCost() {
		return WOOD_COST_ARROWTOWER;
	}

	public int getIronCost() {
		return IRON_COST_ARROWTOWER;
	}

	public int getManaCost() {
		return MANA_COST_ARROWTOWER;
	}

	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATING_WASTE_ARROWTOWER;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_ARROWTOWER;
	}

}
