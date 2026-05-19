package m3;

public class RocketLauncherTower extends DefenseUnit implements MilitaryUnit, Variables {

	public RocketLauncherTower(int id, int initialArmor, int baseDamage) {
		super(id, initialArmor, baseDamage);
	}
	
	public RocketLauncherTower(int id, int initialArmor, int baseDamage, int experience, boolean sanctified) {
		super(id, initialArmor, baseDamage);
		this.gainExperience(experience);
		if (sanctified) {
			this.sanctify();
		}
	}

	public int getFoodCost() {
		return FOOD_COST_ROCKETLAUNCHERTOWER;
	}

	public int getWoodCost() {
		return WOOD_COST_ROCKETLAUNCHERTOWER;
	}

	public int getIronCost() {
		return IRON_COST_ROCKETLAUNCHERTOWER;
	}

	public int getManaCost() {
		return MANA_COST_ROCKETLAUNCHERTOWER;
	}

	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATING_WASTE_ROCKETLAUNCHERTOWER;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER;
	}

}
