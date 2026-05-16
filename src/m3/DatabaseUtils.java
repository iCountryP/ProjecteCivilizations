package m3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                        magic_tower_amount,
                        church_amount,
                        farm_amount,
                        smithy_amount,
                        carpentry_amount,
                        technology_defense_level,
                        technology_attack_level
                    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1, civilization.getName());
                stmt.setInt(2, civilization.getWood());
                stmt.setInt(3, civilization.getIron());
                stmt.setInt(4, civilization.getFood());
                stmt.setInt(5, civilization.getMana());

                stmt.setInt(6, civilization.getMagicTower());
                stmt.setInt(7, civilization.getChurch());
                stmt.setInt(8, civilization.getFarm());
                stmt.setInt(9, civilization.getSmithy());
                stmt.setInt(10, civilization.getCarpentry());

                stmt.setInt(11, civilization.getTechnologyDefense());
                stmt.setInt(12, civilization.getTechnologyAttack());

                int rowsInserted = stmt.executeUpdate();
                System.out.println("Filas insertadas: " + rowsInserted);

                stmt.close();
                connection.close();
            
        } catch(ClassNotFoundException ex) {
            System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
        } catch (SQLException e) {
            System.out.println("Excepción del tipo SQL");
            e.printStackTrace();
        }
	}
	
}
