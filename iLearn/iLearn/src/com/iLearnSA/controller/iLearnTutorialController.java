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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class iLearnTutorialController{

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


    //questions in a multi-dimensional array
    public static String[][] questions = {
    {"which is the correct way to print?","Print()","System.Print()","System.out.print()","System,out,print()","System.out.print()"},
    {"What color is best?","red","blue","green","yellow","red"},
    {"How many hands do people have?","1","two","3","four","two"}
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
        qLabel.setText(questions[qIndex][0]);
        r1.setText(questions[qIndex][1]);
        r2.setText(questions[qIndex][2]);
        r3.setText(questions[qIndex][3]);
        r4.setText(questions[qIndex][4]);
    }

    @FXML
void ClickButton(ActionEvent event) throws Exception {
    try{

    String chosen = ((Labeled) group.getSelectedToggle()).getText();

    //is question right?
    if(chosen.equals(questions[qIndex][5])){
    outL.setText("you got it right!");
    }else{
    outL.setText("Incorrect Answer");
    }

    qIndex++;

    if(qIndex!=questions.length){
        qLabel.setText(questions[qIndex][0]);
        r1.setText(questions[qIndex][1]);
        r2.setText(questions[qIndex][2]);
        r3.setText(questions[qIndex][3]);
        r4.setText(questions[qIndex][4]);
    }else{
    qLabel.setText("No more questions!");
    }

    }catch(Exception e){
    outL.setText("please make a selection");
    }
    }
    public void BackBtnClicked(ActionEvent event) throws IOException, SQLException {
	    
  	  //load the next page
    	//change to go back to wherever "home" is
  	    Parent view = FXMLLoader.load(getClass().getResource("TutorialView.fxml"));
  		Scene scene = new Scene(view);
  		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
  		window.setScene(scene);
  		window.show();
  	}
}
	


