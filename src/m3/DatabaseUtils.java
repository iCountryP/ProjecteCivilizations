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
	                	// magic_tower
	                    break;

	                case 2:
	                	// church
	                    break;
	
	                case 3:
	                	// farm
	                    break;
	
	                case 4:
	                	// smithy
	                    break;
	                    
	                case 5:
	                	// carpentry
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
	
}
