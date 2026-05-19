package m3;

public class ArrowTower extends DefenseUnit implements MilitaryUnit, Variables {

	public ArrowTower(int id, int initialArmor, int baseDamage) {
		super(id, initialArmor, baseDamage);
	}
	
	public ArrowTower(int id, int initialArmor, int baseDamage, int experience, boolean sanctified) {
		super(id, initialArmor, baseDamage);
		this.gainExperience(experience);
		if (sanctified) {
			this.sanctify();
		}
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
