package com.iLearnSA.controller;

import java.io.IOException;
import java.sql.SQLException;

import application.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class QuizViewController{

//always reference main method, and build constructor
private Main main;
public static int qIndex=0;
public void setMain(Main mainIn)
{
initialize();
main=mainIn;
}

//declare all the stuff in the fxml
    @FXML private RadioButton r1;
    @FXML private RadioButton r2;
    @FXML private RadioButton r3;
    @FXML private RadioButton r4;
    @FXML private Button btn;
    @FXML private Button BackBtn; 
    @FXML private Label outL;
    @FXML private Label qLabel;
    int correctResponse = 0;

    //questions in a multi-dimensional array
    public static String[][] questions = {
	    {"Which is the correct way to print?","Print()","System.Print()","System.out.print()","System,out,print()","System.out.print()"},
	    {"Java is an _____________ language","Object","Subject","Magic","Logic","Object"},
	    {"A Java program is best classified as", "input","storage","processor","hardware","software"},
	    {"All Java statements must end with a:___ ", "#",";","!",",",";"},
	    {"The memory or primary storage of a computer is called?","ROM","floppy disk","RAM","hard drive","RAM"}
    };

    //make a ToggleGroup
    ToggleGroup group = new ToggleGroup();

    //this just means at start up
    @FXML
    public void initialize(){
    //add the radio buttons to the toggle group
    	r1.setToggleGroup(group);
        r2.setToggleGroup(group);
        r3.setToggleGroup(group);
        r4.setToggleGroup(group);
        qLabel.setText("Question " + (qIndex + 1) + " : " + questions[qIndex][0]);
        r1.setText("A: " + questions[qIndex][1]);
        r2.setText("B: " + questions[qIndex][2]);
        r3.setText("C: " + questions[qIndex][3]);
        r4.setText("D: " + questions[qIndex][4]);
    }

    @FXML
	public void ClickButton(ActionEvent event) throws Exception {
	    try{
		    String chosen = ((Labeled) group.getSelectedToggle()).getText();
		
		    //is question right?
		    if(chosen.equals(questions[qIndex][5])){
		    	outL.setText("You got it right!");
		    	correctResponse++;
		    }else{
		    outL.setText("Incorrect Answer");
	    }
	
		    qIndex++;
	
		    if(qIndex!=questions.length){
		        qLabel.setText("Question " + (qIndex + 1) + " : " + questions[qIndex][0]);
		        r1.setText(questions[qIndex][1]);
		        r1.setSelected(false);
		        r2.setText(questions[qIndex][2]);
		        r2.setSelected(false);
		        r3.setText(questions[qIndex][3]);
		        r3.setSelected(false);
		        r4.setText(questions[qIndex][4]);
		        r4.setSelected(false);
		
		    }else{
		    	qLabel.setText("No more questions!");
		    	grade();
		    	qIndex = 0;
		    }
		
		    }catch(Exception e){
		    	outL.setText("Please make a selection");
		    	}
		    }
	public void grade() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Your grade");
		alert.setContentText("Your grade is " + (correctResponse * 100 / 5) + "%");
		alert.showAndWait();
	}
  	
}
	


