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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;


public class PasswordRecoveryController 
{
	@FXML private TextField userName, emailAddress;
	@FXML private Button resetBtn;
	@FXML private Button backBtn;
	
	public void clickResetBtn(ActionEvent event) throws SQLException, IOException{
		String userNameEntry, emailAddressEntry, userId;
		ResultSet rs;
		userNameEntry = userName.getText();
		emailAddressEntry = emailAddress.getText();
		if(userNameEntry.isEmpty() || emailAddressEntry.isEmpty())
			alert("Input fileds are empty empty", "Please fill first name and last name fields");
		Connection connection = iLearnDBConfig.getConnection();
		Statement st = connection.createStatement();
		String getUserId = "SELECT userID FROM users WHERE userName = '" + userNameEntry + "'"  + "AND email = '" + emailAddressEntry +"'";
		rs = st.executeQuery(getUserId);
		userId = rs.getString("userID");
		
		
	}
	public void alert(String alertTile, String alertText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(alertTile);
		alert.setContentText(alertText);
		alert.showAndWait();
	}
}
