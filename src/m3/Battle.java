package m3;

import java.util.ArrayList;

public class Battle implements Variables {

	private ArrayList<MilitaryUnit> civilizationArmy;
	private ArrayList<MilitaryUnit> enemyArmy;
	private ArrayList<MilitaryUnit>[] armies;
	
	private int[][] initialArmies;
	private int[][] actualNumberUnitsCivilization, actualNumberUnitsEnemy;
	
	private String battleDevelopment;
	
	private int initialCostFleet;
	private int initialNumberUnitsCivilization, initialNumberUnitsEnemy;
	private int wasteWoodIron;
	private int enemyDrops, civilizationDrops;
	private int resourcesLooses;
	
}