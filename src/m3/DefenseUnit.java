package m3;

public abstract class DefenseUnit implements MilitaryUnit, Variables {
	private int id, armor, initialArmor, baseDamage, experience;
	private boolean sanctified;
	private GestorSonido sonido = new GestorSonido(); // Para manejar los sonidos.

	public DefenseUnit(int id, int initialArmor, int baseDamage) {
		this.id = id;
		this.initialArmor = initialArmor;
		this.baseDamage = baseDamage;
		this.armor = initialArmor;
		this.experience = 0;
		this.sanctified = false;
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
	
	// Metodo que santifica y aumenta en % las stats de los pjs
	public void sanctify() {
		sonido.reproducirSantificado();
	    if (!this.sanctified) {       
	        this.sanctified = true;
	        this.armor += (int) Math.ceil(this.armor * (PLUS_ARMOR_UNIT_SANCTIFIED / 100.0));
	        this.initialArmor += (int) Math.ceil(this.initialArmor * (PLUS_ARMOR_UNIT_SANCTIFIED / 100.0));
	        this.baseDamage += (int) Math.ceil(this.baseDamage * (PLUS_ATTACK_UNIT_SANCTIFIED / 100.0));
	    }
	}
	
	public int getID() {
		return this.id;
	}
	
	public int getInitialArmor() {
		return this.initialArmor;
	}
	
	public Boolean isSanctified() {
		return this.sanctified;
	}
	
	public void setID(int generatedID) {
		this.id = generatedID;
	}
	
}
