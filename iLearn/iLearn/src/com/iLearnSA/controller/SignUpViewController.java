package com.iLearnSA.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
public class SignUpViewController 
{
	//chwongr4703@ung.edu
	//ahfazzaman@gmail.com
	//Instantiate private main class
	//add here
	
	
	//Setup all FXML content here that will be used in the controller
	@FXML private TextField create_username, create_email;
	@FXML private PasswordField create_password, confirm_password;
	@FXML private Button signUp;
	@FXML private ComboBox<String> securityQuestion1;
	@FXML private ComboBox<String> securityQuestion2;
	@FXML private ComboBox<String> securityQuestion3;
	@FXML private TextField qAnswer1, qAnswer2, qAnswer3;
	
	public void initialize() throws SQLException {
		//We then can set up a DB connection using Chris's connection object
				Connection connection = iLearnDBConfig.getConnection();
				//Create a statement
				Statement st = connection.createStatement();	
				
				ResultSet resultSet = st.executeQuery("SELECT * FROM security_questions");		
				List<String> list = new ArrayList<String>();
				//adds the database values to a list
			    while (resultSet.next()) {
			      String questions = resultSet.getString("security_question");
			      int id = resultSet.getInt("qId");
			    //  System.out.println(id);
			      Object[] itemData = new Object[] {id, questions};
			      list.add(id +")  " + questions);
			    }
			  //  System.out.println(list.toString());
			    //Observable list will detect changes within the list
			    ObservableList obList = FXCollections.observableList(list);
			    //add database value to securityQuestion1
			    if(securityQuestion1 != null)
			    {
			    	securityQuestion1.getItems().clear();
			    	securityQuestion1.setItems(obList);
			    }
			  //add database value to securityQuestion2
			    if(securityQuestion2 != null)
			    {
			    	securityQuestion2.getItems().clear();
			    	securityQuestion2.setItems(obList);
			    }
			  //add database value to securityQuestion3
			    if(securityQuestion3 != null)
			    {
			    	securityQuestion3.getItems().clear();
			    	securityQuestion3.setItems(obList);
			    }
	}
	//The main one we will be looking at would look something like this
	public void ClickSignUp_Button(ActionEvent event) throws SQLException, IOException {
		String userName, passWord, confirmpassWord, firstName, lastName, email, question1, question2, question3, answer1, answer2, answer3;
		userName = create_username.getText();
		passWord = create_password.getText();
		confirmpassWord = confirm_password.getText();
		email = create_email.getText();
		question1 = securityQuestion1.getValue();

		question2 = securityQuestion2.getValue();
		question3 = securityQuestion3.getValue();
		answer1 = qAnswer1.getText();
		answer2 = qAnswer2.getText();
		answer3 = qAnswer3.getText();	
		
		if(userName.isEmpty() || passWord.isEmpty() || email.isEmpty() || answer1.isEmpty() || answer2.isEmpty() || answer3.isEmpty()){
			alert("Input fileds are empty", "Please fill all text fields");
		}else if (!passWord.equals(confirmpassWord)) {
			alert("Passwords are not match", "Please enter two same passwords");
		}else if(question1 == null || question2 == null || question3 == null){
				alert("Security question", "Please select security questions");
		}else if (question1 == question2 || question2 == question3 || question1 == question3) {
			alert("Selected repeated security questions", "Please select three different security questions!");
		}else {
			//After all the validations go through, It will sign them up
			//And send them back to the login page where they can log in			
			//We will then create a User object
			iLearn_User user = new iLearn_User();
			
			//We then use the model to set the values for the User object
			user.setUserName(userName);
			user.setPassWord(passWord);
			user.setEmail(email);		
			
			//We then can set up a DB connection using Chris's connection object
			Connection connection = iLearnDBConfig.getConnection();
			//Create a statement
			Statement st = connection.createStatement();			
			
			// check if username has been user by other people 
			String checkUserName = "SELECT COUNT(1) AS COUNT FROM users WHERE userName = '" + userName + "'";
			ResultSet rs = st.executeQuery(checkUserName);
			rs.first();
			
			// if username is occupied by someone else, the result of statement is 1, otherwise, 0
			int countColumn = rs.getInt("COUNT");
			boolean exist = countColumn >= 1 ? true : false;
			if(exist == false) {
				//Then run the query we set up above
				String query = "INSERT INTO users (userName, password, email) VALUES (" + "'" + userName + "', '" + passWord + "', '" + email + "')";//"userName = ?, passWord = ?, firstName = ?, lastName = ?, email = ?";
				st.executeUpdate(query);
				
				
				String getUserId = "SELECT uersId FROM users WHERE userName = '" + userName + "'";
				rs = st.executeQuery(getUserId);
				String UserId = "";
				while(rs.next())
					UserId = rs.getString("uersId");
				
				//insert the first userId, questionID, and answer to answers
				String query2 ="INSERT INTO answers(userId, qId, answerCol) VALUES (" + "'" + UserId + "', '" + question1.substring(0,1) + "', '" + answer1 + "')";
				st.executeUpdate(query2);
				//insert the second userId, questionID, and answer to answers
				String query3 ="INSERT INTO answers(userId, qId, answerCol) VALUES (" + "'" + UserId + "', '" + question2.substring(0,1) + "', '" + answer2 + "')";
				st.executeUpdate(query3);
				//insert the third userId, questionID, and answer to answers
				String query4 ="INSERT INTO answers(userId, qId, answerCol) VALUES (" + "'" + UserId + "', '" + question3.substring(0,1) + "', '" + answer3 + "')";
				st.executeUpdate(query4);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Successful!");
				alert.setContentText("Your account is created!");
				alert.showAndWait();
				
				Parent view = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
				Scene scene = new Scene(view);
				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
				window.setScene(scene);
				window.show();
			}
			else {
				alert("Occupied username", "Please create another username");
			}
		}
	}
	//jump back to the homepage
	public void BackToHome(ActionEvent event) throws IOException{
		Parent view = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
		Scene scene = new Scene(view, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setTitle("Login Page");
		window.setScene(scene);
		window.show();
	}
	// initialization of alert msg
	public void alert(String alertTile, String alertText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(alertTile);
		alert.setContentText(alertText);
		alert.showAndWait();
	}
}
