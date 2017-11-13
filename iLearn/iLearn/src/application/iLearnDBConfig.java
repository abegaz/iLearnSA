package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class iLearnDBConfig {
	private static final String CONN_STRING = "jdbc:mysql://50.62.209.113:3306/iLearnSA";
	private static final String USERNAME = "iLearnAdmin";
	private static final String PASSWORD = "123456789";

	public static Connection getConnection(){
		Connection connection = null;
	    try {
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			System.out.println("Connected to database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return connection;
	}
}
