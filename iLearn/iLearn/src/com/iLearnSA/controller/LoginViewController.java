package com.iLearnSA.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.iLearnDBConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController {
	@FXML private TextField userNameField;
	@FXML private TextField passwordField;
	@FXML private ComboBox<String> securityQuestion1;
	@FXML private ComboBox<String> securityQuestion2;
	@FXML private ComboBox<String> securityQuestion3;
	private Connection connection;
	private ResultSet resultSet;
	
	public void loginBtnClicked(ActionEvent event) throws SQLException, IOException {
		// Create SQL connection
		connection = iLearnDBConfig.getConnection();
		String usernameInput = userNameField.getText();
		String passwordInput = passwordField.getText();
		
		// Check if username and password are filled
		if(usernameInput.isEmpty() || passwordInput.isEmpty()) {
			alert("Input fileds empty", "Please fill in username and password fields");
		}
		else {
			try {
				// try type username with "chwong4703" in userName textField and password "123456789"
				String sql_query = "SELECT Password FROM users WHERE Username = '" + usernameInput + "'"; 
				// create the sql statement
				Statement st = connection.createStatement();
			    // execute the query, and get a java resultset
			    ResultSet rs = st.executeQuery(sql_query);
			    // Get the password with username
			    // If the username is not existed in the database, it will be handled by catch statement
			    rs.first();
			    String password = rs.getString("Password");
			    if(password == "")
			    	System.out.println("hahaha");
			    // Jump to mainview if the username and the password are correct and match the vaule in db
			    if(passwordInput.equals(password)) {
					Parent view = FXMLLoader.load(getClass().getResource("../view/MainPage.fxml"));
					Scene scene = new Scene(view, 800, 600);
					Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
					window.setTitle("Main Page");
					window.setScene(scene);
					window.show();
			    }else{
			    	// if the password is not correct, pops up an error message 
			    	alert("Incorrect password", "Password is not correct!");
			    }
			    st.close();
		    }catch(Exception e){
		    	// unknown user exception
		    	alert("Unknown user", "Username is not existed in the database!");
				System.out.println("Got an exception!");
				System.err.println(e.getMessage());
			}
		}
	}
	
	// initialization of alert msg
	public void alert(String alertTile, String alertText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(alertTile);
		alert.setContentText(alertText);
		alert.showAndWait();
	}
	
	// jump to signupview
	public void signUpBtnClicked(ActionEvent event) throws IOException, SQLException {
		    
	  //load the next page
	    Parent view = FXMLLoader.load(getClass().getResource("../view/SignUpView.fxml"));
		Scene scene = new Scene(view);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setTitle("Sign Up!");
		window.setScene(scene);
		window.show();
	}
	
	public void forgotBtnClicked(ActionEvent event) throws IOException, SQLException {
		Parent view = FXMLLoader.load(getClass().getResource("../view/PasswordRecoveryView.fxml"));
		Scene scene = new Scene(view);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setTitle("Forgot password");
		window.setScene(scene);
		window.show(); 
	}
}

