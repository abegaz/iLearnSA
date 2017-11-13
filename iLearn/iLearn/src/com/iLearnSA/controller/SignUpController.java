package com.iLearnSA.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.iLearnDBConfig;
import application.iLearn_User;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
public class SignUpController 
{
	//chwongr4703@ung.edu
	//ahfazzaman@gmail.com
	//Instantiate private main class
	//add here
	
	//Setup all FXML content here that will be used in the controller
	@FXML private TextField create_username, create_password, create_email;
	@FXML private Button signUp;
	@FXML private ComboBox<String> securityQuestion1;
	@FXML private ComboBox<String> securityQuestion2;
	@FXML private ComboBox<String> securityQuestion3;
	@FXML private TextField qAnswer1, qAnswer2, qAnswer3;
	//Those were just some examples
	
	//After we get those set up, we have to use methods to interact
	//with our components from the view as well as the database
	
	//The main one we will be looking at would look something like this
	public void ClickSignUp_Button(ActionEvent event) throws SQLException, IOException {
		System.out.println("Signing Up");
		String userName, passWord, firstName, lastName, email, question1, question2, question3, answer1, answer2, answer3;
		userName = create_username.getText();
		passWord = create_password.getText();
		//firstName = firstName_textField.getText();
		//lastName = lastName_textField.getText();
		email = create_email.getText();
		question1 = securityQuestion1.getValue();
		question2 = securityQuestion1.getValue();
		question3 = securityQuestion1.getValue();
		answer1 = qAnswer1.getText();
		answer2 = qAnswer2.getText();
		answer3 = qAnswer3.getText();

		//This is where we will make all of our validations
		//If it fails even one, we print out an exception object
		//For us as well as an error message or alert for them
		//Such as 'UserName is taken' or 'passWord is not long enough'
		//if (firstName == null || firstName == "" || firstName.trim().isEmpty()) {
			//throw new Exception("invalid First Name.");
			//This throws an exception for us to see, although I have not
			//Tested what the user would see
		//}
		//More validations would go here
		
		//After all the validations go through, It will sign them up
		//And send them back to the login page where they can log in

		//We will then create a User object
		iLearn_User user = new iLearn_User();
		
		//We then use the model to set the values for the User object
		user.setUserName(userName);
		user.setPassWord(passWord);
		//iLearn_User.setFirstName(firstName);
		//iLearn_User.setLastName(lastName);
		user.setEmail(email);

		//We then set up a query statement to run to store it into the database
		//As well as run another validation to make sure the userName
		//is not taken as well as email
		//might need insert instead of update i forgot which one
		String query = "INSERT INTO users (userName, password, email) VALUES (" + "'" + userName + "', '" + passWord + "', '" + email + "')";//"userName = ?, passWord = ?, firstName = ?, lastName = ?, email = ?";
		
		//We then can set up a DB connection using Chris's connection object
		Connection connection = iLearnDBConfig.getConnection();
		//Create a statement
		Statement st = connection.createStatement();
		//Then run the query we set up above
		st.executeUpdate(query);
		
		//I need to ask chris what kind of object gets created
		//When updating or inserting

		//That should be really close to setting up the users account
		//Then chris has the login part down, he just has to check for their password i believe
		Parent view = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
		Scene scene = new Scene(view);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
}
