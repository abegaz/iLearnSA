package com.iLearnSA.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.iLearnDBConfig;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class loginViewController {
	@FXML private TextField userNameField;
	@FXML private TextField passwordField;
	 
	private Connection connection;
	private ResultSet resultSet;
	
	public void loginBtnClicked() throws SQLException {
		connection = iLearnDBConfig.getConnection();
		String userName = userNameField.getText();
//		if (!(userName.length() > 0))	
//			System.out.println("userName Field Required");
//		else
			try {
				// try type username with "chwong4703" in userName textField
				//String sql_query = "SELECT * FROM USER WHERE Username = '" + userName + "'"; 
				String sql_query = "SELECT * FROM USER"; 
				// create the java statement
				Statement st = connection.createStatement();
			    // execute the query, and get a java resultset
			    ResultSet rs = st.executeQuery(sql_query);
			    // iterate through the java resultset
			      while (rs.next())
			      {
			    	int id = rs.getInt("id");
			        String firstName = rs.getString("FirstName");
			        String lastName = rs.getString("LastName");
			        String email = rs.getString("Email");
			        String username = rs.getString("Username");
			        String password = rs.getString("Password");
			        System.out.println("First Name: " + firstName + "\tLastName: " + lastName + 
			        					"\tEmail: " + email + "\tUsername: " + username + "\tpassword: " + password);
			      }
			      st.close();
			    }
			    catch (Exception e)
			    {
			      System.err.println("Got an exception! ");
			      System.err.println(e.getMessage());
			    }
	}
	public void signupBtnClicked() throws SQLException 
	{
		
	}
}
