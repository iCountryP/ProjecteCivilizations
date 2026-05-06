package m3;

public abstract class AttackUnit implements MilitaryUnit, Variables {
	private int armor, initialArmor, baseDamage, experience;
	private boolean sanctified;
	
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
