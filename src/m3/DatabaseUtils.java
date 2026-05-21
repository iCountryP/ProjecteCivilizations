package m3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class DatabaseUtils {

    private static final String USER = "game";
    private static final String PASSWORD = "lancero777";
    private static final String DB_URL = "jdbc:mysql://localhost/dominion?serverTimezone=UTC";
	
	private DatabaseUtils() {

	}
	
	// Funcion puramente para testing de que la conexion se este realizando correctamente
	public static void connectionTest() {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente");
            
            // Crear conexion con la base de datos
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexión creada correctamente");
            
        } catch(ClassNotFoundException ex) {
            System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
        } catch (SQLException e) {
            System.out.println("Excepción del tipo SQL");
            e.printStackTrace();
        }
	}
	
	public static void saveNewCivilization(Civilization civilization) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente");
            
            // Crear conexion con la base de datos
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexión creada correctamente");
            
            String sql = """
                    INSERT INTO CIVILIZATION (
                        name,
                        wood_amount,
                        iron_amount,
                        food_amount,
                        mana_amount,
                        technology_defense_level,
                        technology_attack_level
                    ) VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, civilization.getName());
            stmt.setInt(2, civilization.getWood());
            stmt.setInt(3, civilization.getIron());
            stmt.setInt(4, civilization.getFood());
            stmt.setInt(5, civilization.getMana());

            stmt.setInt(6, civilization.getTechnologyDefense());
            stmt.setInt(7, civilization.getTechnologyAttack());

            stmt.executeUpdate();
            
            // Obtener la ID generada
            ResultSet generatedKeys = stmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);

                // Settear la id al objeto
                civilization.setID(generatedId);

                System.out.println("ID generada: " + generatedId);
            }

            generatedKeys.close();
            stmt.close();
            connection.close();
            
        } catch(ClassNotFoundException ex) {
            System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
        } catch (SQLException e) {
            System.out.println("Excepción del tipo SQL");
            e.printStackTrace();
        }
	}
	
	public static int checkCivilization(int id) {
		int valid = 0;
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente");
            
            // Crear conexion con la base de datos
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexión creada correctamente");
            
            String sql = """
					SELECT civilization_id, game_over
					FROM CIVILIZATION
					WHERE civilization_id = ?
                """;

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            // Comprobar si la query ha dado resultados
            if (rs.next()) {
            	valid = 1;
                boolean gameOver = rs.getBoolean("game_over");
                if (gameOver) {
                    valid = -2;
                }
            } else {
            	valid = -1;
            }
            
            rs.close();
            stmt.close();
            connection.close();
            
        } catch(ClassNotFoundException ex) {
            System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
        } catch (SQLException e) {
            System.out.println("Excepción del tipo SQL");
            e.printStackTrace();
        }
		return valid;
	}
	
	public static Civilization loadCivilization(int id) {
		Civilization loaded_civilization = new Civilization(id);
		
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente");
            
            // Crear conexion con la base de datos
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexión creada correctamente");
            
            String sql_stats = """
					SELECT c.name, c.technology_defense_level, c.technology_attack_level, c.wood_amount, c.iron_amount, c.food_amount, c.mana_amount,
						   (SELECT COUNT(*) FROM BATTLE b WHERE b.civilization_id = ?) AS battle_count
					FROM CIVILIZATION c
					WHERE c.civilization_id = ?
                """;

            PreparedStatement stmt_stats = connection.prepareStatement(sql_stats);
            stmt_stats.setInt(1, id);
            stmt_stats.setInt(2, id);
            ResultSet rs_stats = stmt_stats.executeQuery();
            
            // Cargamos datos basicos de la tabla de civilization
			if (rs_stats.next()) {
			    loaded_civilization.setName(rs_stats.getString("name"));
			    
			    loaded_civilization.setTechnologyDefense(rs_stats.getInt("technology_defense_level"));
			    loaded_civilization.setTechnologyAttack(rs_stats.getInt("technology_attack_level"));
			    
			    loaded_civilization.setWood(rs_stats.getInt("wood_amount"));
			    loaded_civilization.setIron(rs_stats.getInt("iron_amount"));
			    loaded_civilization.setFood(rs_stats.getInt("food_amount"));
			    loaded_civilization.setMana(rs_stats.getInt("mana_amount"));
			    
			    loaded_civilization.setBattles(rs_stats.getInt("battle_count"));
			} else {
				System.out.println("Algo ha salido mal...");
			}
			
			// Cerramos recursos
			rs_stats.close();
			rs_stats.close();
			
			// Cargar las building de la civilizacion
            String sql_buildings = """
					SELECT building_type_id, pos_x, pos_y
					FROM CIVILIZATION_BUILDING
					WHERE civilization_id = ?
                """;

            PreparedStatement stmt_buildings = connection.prepareStatement(sql_buildings);
            stmt_buildings.setInt(1, id);
            ResultSet rs_buildings = stmt_buildings.executeQuery();
            
            // Recorrer el rs y lo vamos insertando en la civilizacion
            while (rs_buildings.next()) {
                int buildingTypeId = rs_buildings.getInt("building_type_id");
                int posX = rs_buildings.getInt("pos_x");
                int posY = rs_buildings.getInt("pos_y");

                switch (buildingTypeId) {
	                case 1:
	                	// magic_tower
	                    loaded_civilization.loadMagicTower(posX, posY);
	                    break;

	                case 2:
	                	// church
	                	loaded_civilization.loadChurch(posX, posY);
	                    break;
	
	                case 3:
	                	// farm
	                	loaded_civilization.loadFarm(posX, posY);
	                    break;
	
	                case 4:
	                	// smithy
	                	loaded_civilization.loadSmithy(posX, posY);
	                    break;
	                    
	                case 5:
	                	// carpentry
	                	loaded_civilization.loadCarpentry(posX, posY);
	                    break;
	
	                default:
	                	// hubo un error
	                    System.out.println("Edificio desconocido");
	                    break;
                }
            }

            // Cerramos recursos
            rs_buildings.close();
            stmt_buildings.close();
            
			// Cargar las unidades de la army de la civilizacion
            String sql_army = """
					SELECT unit_id, unit_type_id, initialArmor, armor, baseDamage, experience, sanctified
					FROM UNIT
					WHERE civilization_id = ?
                """;

            PreparedStatement stmt_army = connection.prepareStatement(sql_army);
            stmt_army.setInt(1, id);
            ResultSet rs_army = stmt_army.executeQuery();
            
            // Recorrer el rs y lo vamos insertando en la army de la civilizacion
            while (rs_army.next()) {
                int unit_id = rs_army.getInt("unit_id");
                int unit_type_id = rs_army.getInt("unit_type_id");
                int initialArmor = rs_army.getInt("initialArmor");
                int armor = rs_army.getInt("armor");
                int baseDamage = rs_army.getInt("baseDamage");
                int experience = rs_army.getInt("experience");
                String sanctified = rs_army.getString("sanctified");

                switch (unit_type_id) {
	                case 1:
	                	// swordsman (1)
	                	loaded_civilization.loadSwordsman(unit_id, initialArmor, baseDamage, experience, sanctified);
	                    break;

	                case 2:
	                	// spearman (2)
	                	loaded_civilization.loadSpearman(unit_id, initialArmor, baseDamage, experience, sanctified);
	                    break;
	
	                case 3:
	                	// crossbow (3)
	                	loaded_civilization.loadCrossbow(unit_id, initialArmor, baseDamage, experience, sanctified);
	                    break;
	
	                case 4:
	                	// cannon (4)
	                	loaded_civilization.loadCannon(unit_id, initialArmor, baseDamage, experience, sanctified);
	                    break;
	                    
	                case 5:
	                	// arrow_tower (5)
	                	loaded_civilization.loadArrowTower(unit_id, initialArmor, baseDamage, experience, sanctified);
	                    break;
	                    
	                case 6:
	                	// catapult (6)
	                	loaded_civilization.loadCatapult(unit_id, initialArmor, baseDamage, experience, sanctified);
	                    break;
	                    
	                case 7:
	                	// rocket_launcher (7)
	                	loaded_civilization.loadRocketLauncher(unit_id, initialArmor, baseDamage, experience, sanctified);
	                    break;
	                    
	                case 8:
	                	// magician (8)
	                	loaded_civilization.loadMagician(unit_id, initialArmor, baseDamage, experience);
	                    break;
	                    
	                case 9:
	                	// priest (9)
	                	loaded_civilization.loadPriest(unit_id, initialArmor, baseDamage, experience);
	                    break;
	
	                default:
	                	// hubo un error
	                    System.out.println("Unidad desconocida");
	                    break;
                }
            }
            
            // Cerramos recursos
            rs_army.close();
            stmt_army.close();
            
            connection.close();
            
        } catch(ClassNotFoundException ex) {
            System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
        } catch (SQLException e) {
            System.out.println("Excepción del tipo SQL");
            e.printStackTrace();
        }
		
		return loaded_civilization;
	}
	
	public static void saveCivilization(Civilization civilization) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente");
            
            // Crear conexion con la base de datos
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexión creada correctamente");
            
            // Guardar stats base
            String sql_stats = """
					UPDATE CIVILIZATION
					SET
					    wood_amount = ?,
					    iron_amount = ?,
					    food_amount = ?,
					    mana_amount = ?,
					    technology_defense_level = ?,
					    technology_attack_level = ?,
					    game_over = ?,
					    updated_at = CURRENT_TIMESTAMP,
					    updated_by = CURRENT_USER()
					WHERE civilization_id = ?
                """;

            PreparedStatement stmt_stats = connection.prepareStatement(sql_stats);
            
            System.out.println("wood: "+civilization.getWood());
            System.out.println("iron: "+civilization.getIron());
            System.out.println("food: "+civilization.getFood());
            System.out.println("mana: "+civilization.getMana());
            
            stmt_stats.setInt(1, civilization.getWood());
            stmt_stats.setInt(2, civilization.getIron());
            stmt_stats.setInt(3, civilization.getFood());
            stmt_stats.setInt(4, civilization.getMana());

            stmt_stats.setInt(5, civilization.getTechnologyDefense());
            stmt_stats.setInt(6, civilization.getTechnologyAttack());
            stmt_stats.setBoolean(7, civilization.getGameOver());
            
            stmt_stats.setInt(8, civilization.getID());
            
            stmt_stats.executeUpdate();
            
            stmt_stats.close();
            
            // Guardar edificios
            String sql_buildings = """
					INSERT INTO CIVILIZATION_BUILDING (civilization_id, building_type_id, pos_x, pos_y)
					VALUES (?, ?, ?, ?);
                """;
            
            PreparedStatement stmt_buildings = connection.prepareStatement(sql_buildings);
            stmt_buildings.setInt(1, civilization.getID());
            
            stmt_buildings.setInt(2, 1);
        	ArrayList<int[]> magicTower = civilization.getMagicTowerPositions();
        	for (int[] position : magicTower) {
        		if (position[0] == 0) {
            	    stmt_buildings.setInt(3, position[1]);
            	    stmt_buildings.setInt(4, position[2]);

            	    stmt_buildings.executeUpdate();
        		}
        	}
        	
        	stmt_buildings.setInt(2, 2);
        	ArrayList<int[]> church = civilization.getChurchPositions();
        	for (int[] position : church) {
        		if (position[0] == 0) {
            	    stmt_buildings.setInt(3, position[1]);
            	    stmt_buildings.setInt(4, position[2]);

            	    stmt_buildings.executeUpdate();
        		}
        	}
        	
        	stmt_buildings.setInt(2, 3);
        	ArrayList<int[]> farm = civilization.getFarmPositions();
        	for (int[] position : farm) {
        		if (position[0] == 0) {
            	    stmt_buildings.setInt(3, position[1]);
            	    stmt_buildings.setInt(4, position[2]);

            	    stmt_buildings.executeUpdate();
        		}
        	}
        	
        	stmt_buildings.setInt(2, 4);
        	ArrayList<int[]> smithy = civilization.getSmithyPositions();
        	for (int[] position : smithy) {
        		if (position[0] == 0) {
            	    stmt_buildings.setInt(3, position[1]);
            	    stmt_buildings.setInt(4, position[2]);

            	    stmt_buildings.executeUpdate();
        		}
        	}
        	
        	stmt_buildings.setInt(2, 5);
        	ArrayList<int[]> carpentry = civilization.getCarpentryPositions();
        	for (int[] position : carpentry) {
        		if (position[0] == 0) {
            	    stmt_buildings.setInt(3, position[1]);
            	    stmt_buildings.setInt(4, position[2]);

            	    stmt_buildings.executeUpdate();
        		}
        	}
            
            stmt_buildings.close();
            
         // Guardar unidades
            String sql_unit_insert = """
                    INSERT INTO UNIT (
                        civilization_id,
                        unit_type_id,
                        initialArmor,
                        armor,
                        baseDamage,
                        experience,
                        sanctified
                    ) VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

            String sql_unit_update = """
                    UPDATE UNIT
                    SET
                        initialArmor = ?,
                        armor = ?,
                        baseDamage = ?,
                        experience = ?,
                        sanctified = ?,
                        updated_at = CURRENT_TIMESTAMP,
                        updated_by = CURRENT_USER()
                    WHERE unit_id = ?;
                """;

            String sql_unit_delete = """
                    DELETE FROM UNIT
                    WHERE unit_id = ?;
                """;

            String sql_unit_select = """
                    SELECT unit_id
                    FROM UNIT
                    WHERE civilization_id = ?;
                """;

            PreparedStatement stmt_unit_insert = connection.prepareStatement(sql_unit_insert,Statement.RETURN_GENERATED_KEYS);

            stmt_unit_insert.setInt(1, civilization.getID());

            PreparedStatement stmt_unit_update = connection.prepareStatement(sql_unit_update);
            PreparedStatement stmt_unit_delete = connection.prepareStatement(sql_unit_delete);
            PreparedStatement stmt_unit_select = connection.prepareStatement(sql_unit_select);

            ArrayList<MilitaryUnit>[] civilization_army = civilization.getArmy();
            ArrayList<Integer> alive_units = new ArrayList<>();

            for (int i = 0; i < civilization_army.length; i++) {
                for (int j = 0; j < civilization_army[i].size(); j++) {
                    MilitaryUnit unit = civilization_army[i].get(j);

                    // INSERT
                    if (unit.getID() == 0) {

                        stmt_unit_insert.setInt(2, i + 1);
                        stmt_unit_insert.setInt(3, unit.getInitialArmor());
                        stmt_unit_insert.setInt(4, unit.getActualArmor());
                        stmt_unit_insert.setInt(5, unit.getAttack());
                        stmt_unit_insert.setInt(6, unit.getExperience());

                        if (i < 7) {
                        	if (unit.isSanctified()) {
                        	    stmt_unit_insert.setString(7, "TRUE");
                        	} else {
                        	    stmt_unit_insert.setString(7, "FALSE");
                        	}
                        } else {
                            stmt_unit_insert.setString(7, "NOT_APPLICABLE");
                        }

                        stmt_unit_insert.executeUpdate();

                        ResultSet generatedKeys = stmt_unit_insert.getGeneratedKeys();

                        if (generatedKeys.next()) {
                            int generatedID = generatedKeys.getInt(1);
                            unit.setID(generatedID);
                            alive_units.add(generatedID);
                        }

                        generatedKeys.close();

                    // UPDATE
                    } else {

                        stmt_unit_update.setInt(1, unit.getInitialArmor());
                        stmt_unit_update.setInt(2, unit.getActualArmor());
                        stmt_unit_update.setInt(3, unit.getAttack());
                        stmt_unit_update.setInt(4, unit.getExperience());

                        if (i < 7) {
                        	if (unit.isSanctified()) {
                        	    stmt_unit_update.setString(5, "TRUE");
                        	} else {
                        	    stmt_unit_update.setString(5, "FALSE");
                        	}
                        } else {
                            stmt_unit_update.setString(5, "NOT_APPLICABLE");
                        }

                        stmt_unit_update.setInt(6, unit.getID());
                        stmt_unit_update.executeUpdate();
                        alive_units.add(unit.getID());
                    }
                }
            }

            // Buscar unidades muertas
            stmt_unit_select.setInt(1, civilization.getID());
            ResultSet rs_units = stmt_unit_select.executeQuery();

            while (rs_units.next()) {
                int db_unit_id = rs_units.getInt("unit_id");
                if (!alive_units.contains(db_unit_id)) {
                    stmt_unit_delete.setInt(1, db_unit_id);
                    stmt_unit_delete.executeUpdate();
                }
            }

            rs_units.close();

            stmt_unit_insert.close();
            stmt_unit_update.close();
            stmt_unit_delete.close();
            stmt_unit_select.close();
            
            connection.close();
            
            System.out.println("Guardado con exito");
            
        } catch(ClassNotFoundException ex) {
            System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
        } catch (SQLException e) {
            System.out.println("Excepción del tipo SQL");
            e.printStackTrace();
        }
	}
	
	public static void battleAutoSave(Battle battle, Civilization civilization) {
		DatabaseUtils.saveCivilization(civilization);
		
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente");
            
            // Crear conexion con la base de datos
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexión creada correctamente");
            
            // Battle stats
            String sql_battle_stats = """
                    INSERT INTO BATTLE (civilization_id, num_battle, winner, wood_waste, iron_waste, log_text
            		) VALUES (?, ?, ?, ?, ?, ?)
                """;

            PreparedStatement stmt_battle_stats = connection.prepareStatement(sql_battle_stats, Statement.RETURN_GENERATED_KEYS);

            stmt_battle_stats.setInt(1, civilization.getID());
            stmt_battle_stats.setInt(2, civilization.getBattles());
            
            if (battle.getBattleResult()) {
            	// Ha perdido
            	stmt_battle_stats.setString(3, "enemy");
            } else {
            	// Ha ganado
            	stmt_battle_stats.setString(3, "civilization");
            }
            
            stmt_battle_stats.setInt(4, battle.getWaste()[0]);
            stmt_battle_stats.setInt(5, battle.getWaste()[1]);
            
            stmt_battle_stats.setString(6, battle.getBattleDevelopment()); // Log paso a paso
            
            // Ejecutar INSERT
            stmt_battle_stats.executeUpdate();

            // Obtener battle_id generado
            ResultSet generatedKeys = stmt_battle_stats.getGeneratedKeys();
            
            int battleId = -1;

            if (generatedKeys.next()) {
                battleId = generatedKeys.getInt(1);
            }

            generatedKeys.close();
            stmt_battle_stats.close();
            
            // Battle drops
            String sql_battle_losses = """
                    INSERT INTO BATTLE_RESOURCE_LOSSES (battle_id, side,
									initial_iron_amount, initial_wood_amount, initial_food_amount,
									iron_losses, wood_losses, food_losses
					) VALUES (?, ?, ?, ?, ?, ?, ?, ?),
							 (?, ?, ?, ?, ?, ?, ?, ?)
                """;
            
            int[][] resource_losses = battle.getResourceLosses();

            PreparedStatement stmt_battle_losses = connection.prepareStatement(sql_battle_losses);
            
            stmt_battle_losses.setInt(1, battleId);
            stmt_battle_losses.setString(2, "civilization");
            
            stmt_battle_losses.setInt(3, resource_losses[0][0]);
            stmt_battle_losses.setInt(4, resource_losses[0][1]);
            stmt_battle_losses.setInt(5, resource_losses[0][2]);
            
            stmt_battle_losses.setInt(6, resource_losses[0][3]);
            stmt_battle_losses.setInt(7, resource_losses[0][4]);
            stmt_battle_losses.setInt(8, resource_losses[0][5]);
            
            // ---
            
            stmt_battle_losses.setInt(9, battleId);
            stmt_battle_losses.setString(10, "enemy");
            
            stmt_battle_losses.setInt(11, resource_losses[1][0]);
            stmt_battle_losses.setInt(12, resource_losses[1][1]);
            stmt_battle_losses.setInt(13, resource_losses[1][2]);
            
            stmt_battle_losses.setInt(14, resource_losses[1][3]);
            stmt_battle_losses.setInt(15, resource_losses[1][4]);
            stmt_battle_losses.setInt(16, resource_losses[1][5]);
            
            stmt_battle_losses.executeUpdate();
            stmt_battle_losses.close();
            
            // Battle drops by groups
            String sql_battle_drops = """
                    INSERT INTO UNIT_GROUP (battle_id, side, unit_type_id, initial_amount, drops
            		) VALUES (?, ?, ?, ?, ?)
                """;
            
            int[][] initial_armies = battle.getInitialArmies();
            int[][] current_armies = battle.getCurrentArmies();
            
            PreparedStatement stmt_battle_drops = connection.prepareStatement(sql_battle_drops);
            stmt_battle_drops.setInt(1, battleId);
            
            for (int i = 0; i < 2; i++) {
            	
            	if (i == 0) {
            		stmt_battle_drops.setString(2, "civilization");
            	} else {
            		stmt_battle_drops.setString(2, "enemy");
            	}
            	
                for (int j = 0; j < 9; j++) {

                	stmt_battle_drops.setInt(3, j+1);
                	stmt_battle_drops.setInt(4, initial_armies[i][j]);
                	stmt_battle_drops.setInt(5, initial_armies[i][j] - current_armies[i][j]);
                	
                	stmt_battle_drops.executeUpdate();
                }
            }
            
            stmt_battle_drops.close();
            
            connection.close();
            
        } catch(ClassNotFoundException ex) {
            System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
        } catch (SQLException e) {
            System.out.println("Excepción del tipo SQL");
            e.printStackTrace();
        }
	}
	
	public static String reconstruirReporteBatalla(int numBattleLocal, int civId) {
		String reporte = "";
		
		int[][] initialUnits = new int[2][9];
		int[][] dropsUnits = new int[2][9];

		int[] costFood = new int[2];
		int[] costWood = new int[2];
		int[] costIron = new int[2];

		int[] lossFood = new int[2];
		int[] lossWood = new int[2];
		int[] lossIron = new int[2];

		int battleNum = 0;
		int wasteWood = 0;
		int wasteIron = 0;
		String winner = "";
		boolean encontrada = false;

		String[] troopsNames = {"Swordsman", "Spearman", "Crossbow", "Cannon   ", "Arrow Tower", "Catapult", "Rocket Launcher", "Magician", "Priest   "};

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			int battleIdGlobal = -1; 
			String queryId = "SELECT battle_id FROM BATTLE WHERE num_battle = ? AND civilization_id = ?";
			PreparedStatement pstmt0 = connection.prepareStatement(queryId);
			pstmt0.setInt(1, numBattleLocal);
			pstmt0.setInt(2, civId);
			
			ResultSet rs0 = pstmt0.executeQuery();
			if(rs0.next()) {
				battleIdGlobal = rs0.getInt("battle_id");
			}
			rs0.close();
			pstmt0.close();

			if (battleIdGlobal == -1) {
				connection.close();
				return null; 
			}

			String queryBattle = "SELECT num_battle, winner, wood_waste, iron_waste FROM BATTLE WHERE battle_id = ?";
			PreparedStatement pstmt1 = connection.prepareStatement(queryBattle);
			pstmt1.setInt(1, battleIdGlobal);
			ResultSet rs1 = pstmt1.executeQuery();
			
			if(rs1.next()) {
				encontrada = true;
				battleNum = rs1.getInt("num_battle");
				winner = rs1.getString("winner");
				wasteWood = rs1.getInt("wood_waste");
				wasteIron = rs1.getInt("iron_waste");
			}
			rs1.close();
			pstmt1.close();

			if (!encontrada) {
				connection.close();
				return null; 
			}

			String queryUnits = "SELECT side, unit_type_id, initial_amount, drops FROM UNIT_GROUP WHERE battle_id = ?";
			PreparedStatement pstmt2 = connection.prepareStatement(queryUnits);
			pstmt2.setInt(1, battleIdGlobal);
			ResultSet rs2 = pstmt2.executeQuery();
			
			while(rs2.next()) {
				String side = rs2.getString("side");
				int typeIndex = rs2.getInt("unit_type_id") - 1; 
				int sideIndex = 0; 
				
				if(side.equals("enemy")) {
					sideIndex = 1;
				}
				
				initialUnits[sideIndex][typeIndex] = rs2.getInt("initial_amount");
				dropsUnits[sideIndex][typeIndex] = rs2.getInt("drops");
			}
			rs2.close();
			pstmt2.close();

			String queryLosses = "SELECT side, initial_food_amount, initial_wood_amount, initial_iron_amount, food_losses, wood_losses, iron_losses FROM BATTLE_RESOURCE_LOSSES WHERE battle_id = ?";
			PreparedStatement pstmt3 = connection.prepareStatement(queryLosses);
			pstmt3.setInt(1, battleIdGlobal);
			ResultSet rs3 = pstmt3.executeQuery();
			
			while(rs3.next()) {
				String side = rs3.getString("side");
				int sideIndex = 0;
				if(side.equals("enemy")) {
					sideIndex = 1;
				}
				
				costFood[sideIndex] = rs3.getInt("initial_food_amount");
				costWood[sideIndex] = rs3.getInt("initial_wood_amount");
				costIron[sideIndex] = rs3.getInt("initial_iron_amount");

				lossFood[sideIndex] = rs3.getInt("food_losses");
				lossWood[sideIndex] = rs3.getInt("wood_losses");
				lossIron[sideIndex] = rs3.getInt("iron_losses");
			}
			rs3.close();
			pstmt3.close();
			
			connection.close();
			
		} catch (ClassNotFoundException ex) {
			System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
		} catch (SQLException e) {
			System.out.println("Excepción del tipo SQL");
			e.printStackTrace();
		}

		reporte += "\nBATTLE NUMBER: " + battleNum + "\nBATTLE STATISTICS\n\n";
		reporte += "Civ. Army\tUnits\tDrops\tEnemy Army\tUnits\tDrops\n";

		for (int i = 0; i < 9; i++) {
			reporte += troopsNames[i] + "\t" + initialUnits[0][i] + "\t" + dropsUnits[0][i] + "\t" + troopsNames[i] + "\t" + initialUnits[1][i] + "\t" + dropsUnits[1][i] + "\n";
		}

		reporte += "\n*********************************************************\n";
		reporte += "Cost Army Civilization\t\tCost Army Enemy\n\n";
		reporte += "Food:\t" + costFood[0] + "\t\t\tFood:\t" + costFood[1] + "\n";
		reporte += "Wood:\t" + costWood[0] + "\t\t\tWood:\t" + costWood[1] + "\n";
		reporte += "Iron:\t" + costIron[0] + "\t\t\tIron:\t" + costIron[1] + "\n";

		reporte += "\n*********************************************************\n";
		reporte += "Losses Army Civilization\t\tLosses Army Enemy\n\n";
		reporte += "Food:\t" + lossFood[0] + "\t\t\tFood:\t" + lossFood[1] + "\n";
		reporte += "Wood:\t" + lossWood[0] + "\t\t\tWood:\t" + lossWood[1] + "\n";
		reporte += "Iron:\t" + lossIron[0] + "\t\t\tIron:\t" + lossIron[1] + "\n";

		reporte += "\n*********************************************************\n";
		reporte += "Waste Generated:\nWood\t" + wasteWood + "\nIron\t" + wasteIron + "\n\n";

		if (winner.equals("civilization")) {
			reporte += "Battle Won by Civilization, We Collect Rubble\n";
		} else if (winner.equals("enemy")) {
			reporte += "Battle Lost by Civilization\n";
		} else {
			reporte += "Battle ended in a Tie\n";
		}

		return reporte;
	}
	
}
