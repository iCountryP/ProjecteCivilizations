package m3;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Main implements Variables {
	 private int hierroEnemigo = IRON_BASE_ENEMY_ARMY;
	 private int maderaEnemigo = WOOD_BASE_ENEMY_ARMY;
	 private int comidaEnemigo = FOOD_BASE_ENEMY_ARMY;
	 private ArrayList<MilitaryUnit>[] enemyArmy = new ArrayList[9];

	public static void main(String[] args) {
		new VentanaJuego();
	}
	
	
	
	
	
	
	
	// Método para crear el ejército enemigo
	public void createEnemyArmy() {
		// aqui rellenamos el array de militaryunti
		for(int i = 0; i < enemyArmy.length; i++) {
			enemyArmy[i] = new ArrayList<MilitaryUnit>();
		}
		// mientras que el enemigo pueda pagarse un swordsman (q es el mas barato) entonces se ejecuta el while para rellenar .
		while(comidaEnemigo >= FOOD_COST_SWORDSMAN && maderaEnemigo >= WOOD_COST_SWORDSMAN && hierroEnemigo >= IRON_COST_SWORDSMAN) {
			int probabilidad = (int)(Math.random() * 100);
			
			// 1. SWORDSMAN
			if(probabilidad < 35) {
				comidaEnemigo -= FOOD_COST_SWORDSMAN;
				maderaEnemigo -= WOOD_COST_SWORDSMAN;
				hierroEnemigo -= IRON_COST_SWORDSMAN;
				enemyArmy[0].add(new Swordsman());
			}
			
			// 2. SPEARMAN
			else if(probabilidad < 60) {
				if(comidaEnemigo >= FOOD_COST_SPEARMAN && maderaEnemigo >= WOOD_COST_SPEARMAN && hierroEnemigo >= IRON_COST_SPEARMAN) {
					comidaEnemigo -= FOOD_COST_SPEARMAN;
					maderaEnemigo -= WOOD_COST_SPEARMAN;
					hierroEnemigo -= IRON_COST_SPEARMAN;
					enemyArmy[1].add(new Spearman());
				}
			}
			
			// 3. CROSSBOW
			else if (probabilidad < 80) {
				if(comidaEnemigo >= FOOD_COST_CROSSBOW && maderaEnemigo >= WOOD_COST_CROSSBOW && hierroEnemigo >= IRON_COST_CROSSBOW) {
					comidaEnemigo -= FOOD_COST_CROSSBOW;
					maderaEnemigo -= WOOD_COST_CROSSBOW;
					hierroEnemigo -= IRON_COST_CROSSBOW;
					enemyArmy[2].add(new Crossbow());
				}
			}
			
			// 4. CANNON
			else {
				if(comidaEnemigo >= FOOD_COST_CANNON && maderaEnemigo >= WOOD_COST_CANNON && hierroEnemigo >= IRON_COST_CANNON) {
					comidaEnemigo -= FOOD_COST_CANNON;
					maderaEnemigo -= WOOD_COST_CANNON;
					hierroEnemigo -= IRON_COST_CANNON;
					enemyArmy[3].add(new Cannon());
				}
			}
			
		}
	} // Fin createEnemyArmy


	public ArrayList<MilitaryUnit>[] getEnemyArmy() {
		return enemyArmy;
	}







	public int getHierroEnemigo() {
		return hierroEnemigo;
	}







	public void setHierroEnemigo(int hierroEnemigo) {
		this.hierroEnemigo = hierroEnemigo;
	}







	public int getMaderaEnemigo() {
		return maderaEnemigo;
	}







	public void setMaderaEnemigo(int maderaEnemigo) {
		this.maderaEnemigo = maderaEnemigo;
	}







	public int getComidaEnemigo() {
		return comidaEnemigo;
	}







	public void setComidaEnemigo(int comidaEnemigo) {
		this.comidaEnemigo = comidaEnemigo;
	}
	
	

	
	
	
}




