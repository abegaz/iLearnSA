package application;

import java.sql.*;
import java.util.*;

public class dbConfig {
	public static void main(String[] args)
	{
		Connection conn = getConnection();

		 Statement stmt = null;
		 String query = "select firstName,lastName from USER = chowng4703";
		 try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			 while (rs.next()) {
		            String firstName = rs.getString("firstName");
		            String lastName =  rs.getString("lastName");
		            System.out.println("First Name: " + firstName + "\tLast Name: " + lastName);
		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection(){
		Connection conn = null;
	    try {
			conn = DriverManager.getConnection("jdbc:mysql://50.62.209.113:3306/iLearnSA","iLearnAdmin","123456789");
			 System.out.println("Connected to database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return conn;
	}
}
