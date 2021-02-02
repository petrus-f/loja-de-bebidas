package projeto.homologacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
	
	private static final String URLCON = "jdbc:mariadb://localhost:3306/lojadb";
	private static final String USER = "root";
	private static final String PASS = "";
	
	private static ConnectionSingleton instancia=null;
	private Connection connection = null;
	public static ConnectionSingleton instancia() {
		if(instancia==null) {
			instancia = new ConnectionSingleton();
		}
		return instancia;
	}
	
	private ConnectionSingleton() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection connection() {
		try {
			if(connection == null || connection.isValid(10)) {
				connection = DriverManager.getConnection(URLCON, USER,PASS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
