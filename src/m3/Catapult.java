package m3;

public class Catapult extends DefenseUnit implements MilitaryUnit, Variables {

	public Catapult(int id, int initialArmor, int baseDamage) {
		super(id, initialArmor, baseDamage);
	}
	
	public Catapult(int id, int initialArmor, int baseDamage, int experience, boolean sanctified) {
		super(id, initialArmor, baseDamage);
		this.gainExperience(experience);
		if (sanctified) {
			this.sanctify();
		}
	}

	public int getFoodCost() {
		return FOOD_COST_CATAPULT;
	}

	public int getWoodCost() {
		return WOOD_COST_CATAPULT;
	}

	public int getIronCost() {
		return IRON_COST_CATAPULT;
	}

	public int getManaCost() {
		return MANA_COST_CATAPULT;
	}

	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATING_WASTE_CATAPULT;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_CATAPULT;
	}

}
