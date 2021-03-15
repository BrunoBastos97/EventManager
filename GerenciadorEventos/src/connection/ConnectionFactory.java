package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;
import javax.swing.JOptionPane;
		

	/** conexão com o banco de dados 
	 * {@link #DRIVER} drive do mysql 
	 * {@link #URL} link direto com o db local
	 * {@value #USER} usuario do banco
	 * @author mariana*/

public class ConnectionFactory {
	
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/gerenciador_de_pessoas";
	private static String USER = "root";
	private static String PASS = "";
	
	/** constructor para teste a conexão com o banco de dados com
	 * @author mariana
	 */
	
	public static Connection getConnection() {
		
		try {
			Class.forName(DRIVER);
			
			return DriverManager.getConnection(URL, USER, PASS);
			
		} catch (ClassNotFoundException | SQLException ex) {
			throw new RuntimeException("Erro ao conectar: ", ex);
		}
	}
	
	/** constructor para fechar a conexão com o banco de dados 
	 * @author mariana
	 */
	
	public static void closeConnection(Connection connection) {
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (Exception ex) {
			throw new RuntimeException("Erro ao fechar conexão: ", ex);
		}
		
	}
	
	/** constructor para a conexão com o banco de dados passando o Stmt
	 * @author mariana
	 */
	
	public static void closeConnection(Connection connection, PreparedStatement statement) {
		try {
			if(statement != null) {
				statement.close();
			}
		} catch (Exception ex) {
			throw new RuntimeException("Erro ao fechar conexão: ", ex);
		}
		
	}
	
	/** constructor para a conexão com o banco de dados passando o Stmt e um resultSet
	 * @author mariana
	 */
	
	public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
		} catch (Exception ex) {
			throw new RuntimeException("Erro ao fechar conexão: ", ex);
		}
		
	}
	
}
