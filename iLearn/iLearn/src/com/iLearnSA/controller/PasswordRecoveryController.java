package com.iLearnSA.controller;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.iLearnDBConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PasswordRecoveryController 
{
	@FXML private TextField userName, emailAddress, answer1, answer2, answer3, newPassword;
	@FXML private Button fetchQuestions;
	@FXML private Button backBtn;
	@FXML private Text question1;
	@FXML private Text question2;
	@FXML private Text question3;
	private String userNameEntry, emailAddressEntry, userId, secQuestion1, secQuestion2, secQuestion3;
	
	public void clickResetBtn(ActionEvent event) throws SQLException, IOException{
		userId = "";
		secQuestion1 = "";
		secQuestion2 = "";
		secQuestion3 = "";
		String[] questionIds = new String[3];
		ResultSet rs;
		userNameEntry = userName.getText();
		emailAddressEntry = emailAddress.getText();
		if(userNameEntry.isEmpty() || emailAddressEntry.isEmpty())
			alert("Input fileds are empty", "Please fill username and email fields");
		Connection connection = iLearnDBConfig.getConnection();
		Statement st = connection.createStatement();
		String getUserId = "SELECT uersId FROM users WHERE userName = '" + userNameEntry + "'"  + " AND email = '" + emailAddressEntry +"'";
		rs = st.executeQuery(getUserId);
		while(rs.next())
		userId = rs.getString("uersId");
		String getQuestionIds = "SELECT qId FROM answers WHERE userId = '" + userId + "'";
		rs = st.executeQuery(getQuestionIds);
		int count = 0;
		while(rs.next()) 
		{
			questionIds[count] = rs.getString("qId");
			count++;
		}
		String getQuestion1 = "SELECT security_question FROM security_questions WHERE qId = '" + questionIds[0] + "'";
		rs = st.executeQuery(getQuestion1);
		while(rs.next())
			secQuestion1 = rs.getString("security_question");
		String getQuestion2 = "SELECT security_question FROM security_questions WHERE qId = '" + questionIds[1] + "'";
		rs = st.executeQuery(getQuestion2);
		while(rs.next())
			secQuestion2 = rs.getString("security_question");
		String getQuestion3 = "SELECT security_question FROM security_questions WHERE qId = '" + questionIds[2] + "'";
		rs = st.executeQuery(getQuestion3);
		while(rs.next())
			secQuestion3 = rs.getString("security_question");
		question1.setText(secQuestion1);
		question2.setText(secQuestion2);
		question3.setText(secQuestion3);
	}
	
	public void clickChangePasswordBtn(ActionEvent event) throws SQLException, IOException
	{
		Connection connection = iLearnDBConfig.getConnection();
		Statement st = connection.createStatement();
		ResultSet rs;
		String[] answers = new String[3];
		String getAnswers = "SELECT answerCol FROM answers WHERE userId = '" + userId + "'";
		rs = st.executeQuery(getAnswers);
		int count = 0;
		while(rs.next()) 
		{
			answers[count] = rs.getString("answerCol");
			count++;
		}
		if(answer1.getText().equals(answers[0]) && answer2.getText().equals(answers[1]) && answer3.getText().equals(answers[2])) 
		{
		String newPassQuery = "UPDATE users SET password = '" + newPassword.getText() + "' WHERE uersId = '" + userId + "'";
		st.executeUpdate(newPassQuery);
		Parent view = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
		Scene scene = new Scene(view);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
		}
		else
			alert("Security Question answers incorrect", "Please try again");
	}
	public void clickBackBtn(ActionEvent event) throws IOException
	{
		Parent view = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
		Scene scene = new Scene(view, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setTitle("Login Page");
		window.setScene(scene);
		window.show();
	}
	public void alert(String alertTile, String alertText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(alertTile);
		alert.setContentText(alertText);
		alert.showAndWait();
	}
}
