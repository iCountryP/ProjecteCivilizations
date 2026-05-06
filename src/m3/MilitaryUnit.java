package m3;

public interface MilitaryUnit {

	// Devuelve el ataque de la unidad
	abstract int getAttack();
	
	// Resta receivedDamage a la armadura de la unidad
	abstract void takeDamage(int receivedDamage);
	
	// Devuelve la armadura actual de la unidad
	abstract int getActualArmor();
	
	// Devuelve el coste de comida para crear la unidad
	abstract int getFoodCost();
	
	// Devuelve el coste de madera para crear la unidad
	abstract int getWoodCost();
	
	// Devuelve el coste de hierro para crear la unidad
	abstract int getIronCost();
	
	// Devuelve el coste de mana para crear la unidad
	abstract int getManaCost();
	
	// Devuelve la probabilidad de generar residuos al ser eliminada
	abstract int getChanceGeneratingWaste();
	
	// Devuelve la probabilidad de volver a atacar
	abstract int getChanceAttackAgain();
	
	// Reestablece la armadura actual a la armadura inicial
	abstract void resetArmor();
	
	// Suma n a la experiencia actual de la unidad
	abstract void gainExperience(int n);

	// Devuelve la experiencia actual de la unidad
	abstract void getExperience();

}
