package m3;

public class Priest extends SpecialUnit implements MilitaryUnit, Variables {

	public Priest(int id, int initialArmor, int baseDamage) {
		super(id, initialArmor, baseDamage);
	}
	
	public Priest(int id, int initialArmor, int baseDamage, int experience) {
		super(id, initialArmor, baseDamage);
		this.gainExperience(experience);
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
