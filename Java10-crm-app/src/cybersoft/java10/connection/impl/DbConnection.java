package cybersoft.java10.connection.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cybersoft.java10.connection.IDbConnection;

public class DbConnection implements IDbConnection{
	private static String url = "jdbc:mysql://localhost:3306/crm_app_myclass";
	private static String username = "root";
	private static String password = "zZ01283352741@";
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
