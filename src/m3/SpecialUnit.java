package m3;

public abstract class SpecialUnit implements MilitaryUnit, Variables {
	private int armor, initialArmor, baseDamage, experience;
	
	public SpecialUnit(int initialArmor, int baseDamage) {
		this.initialArmor = initialArmor;
		this.baseDamage = baseDamage;
		this.armor = initialArmor;
		this.experience = 0;
	}
	
	// Devuelve el ataque de la unidad
	public int getAttack() {
		return baseDamage;
	};
	
	// Resta receivedDamage a la armadura de la unidad
	public void takeDamage(int receivedDamage) {
		this.armor = this.armor - receivedDamage;
	};
	
	// Devuelve la armadura actual de la unidad
	public int getActualArmor() {
		return this.armor;
	};
	
	// Reestablece la armadura actual a la armadura inicial
	public void resetArmor() {
		this.armor = initialArmor;
	};
	
	// Suma n a la experiencia actual de la unidad
	public void gainExperience(int n) {
		this.experience = this.experience + n;
	};

	// Devuelve la experiencia actual de la unidad
	public int getExperience() {
		return this.experience;
	};
	
}
