package com.iLearnSA.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.iLearnDBConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;

public class LoginViewController{
	@FXML private TextField userNameField;
	@FXML private TextField passwordField;
	@FXML private ChoiceBox securityQuestion1;
	@FXML private ChoiceBox securityQuestion2;
	@FXML private ChoiceBox securityQuestion3;
	private Connection connection;
	private ResultSet resultSet;
	
	public void loginBtnClicked() throws SQLException {
		connection = iLearnDBConfig.getConnection();
		String usernameInput = userNameField.getText();
		String passwordInput = passwordField.getText();
		
		if((usernameInput == null || usernameInput.isEmpty()) ||
			(passwordInput == null || passwordInput.isEmpty())) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Input fields empty");
			alert.setContentText("Please fill usname and password fields");
			alert.showAndWait();
		}
		else {
			try {
				// try type username with "chwong4703" in userName textField
				String sql_query = "SELECT * FROM USERS WHERE Username = '" + usernameInput + "'"; 
				// create the java statement
				Statement st = connection.createStatement();
			    // execute the query, and get a java resultset
			    ResultSet rs = st.executeQuery(sql_query);
			    // iterate through the java resultset
			    rs.first();
			    String password = rs.getString("Password");
			    if(passwordInput.equals(password)) {
			    	
			    }
			    st.close();
		    }catch(Exception e){
			      System.err.println("Got an exception! ");
			      System.err.println(e.getMessage());
			}
		}
	}

	public void signUpBtnClicked(ActionEvent event) throws IOException {
//		Parent view = FXMLLoader.load(getClass().getResource("../view/signUpView.fxml"));
		Parent view = FXMLLoader.load(getClass().getResource("../view/SignUpView.fxml"));
		Scene scene = new Scene(view);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
		ObservableList<String> questions = FXCollections.observableArrayList(buildQuestions());
		securityQuestion1.setItems(questions);
		securityQuestion2.setItems(questions);
		securityQuestion3.setItems(questions);
	}
	
	public void loginBtnClicked(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource(""));
		Scene scene = new Scene(view);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	private ArrayList<String> buildQuestions()
	{
		ArrayList<String> questions = new ArrayList<String>();
		questions.add("What is your mother's maiden name?");
		questions.add("What is the name of your first pet?");
		questions.add("What is your high school mascot");
		questions.add("What was the name of you elementary school?");
		questions.add("What city does your nearest sibling live in?");
		questions.add("What is your favorite superhero?");
		questions.add("What town was your father born in?");
		return questions;
	}

}
