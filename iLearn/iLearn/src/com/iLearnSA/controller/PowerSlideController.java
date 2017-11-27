package com.iLearnSA.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class PowerSlideController { 

    public Stage primaryStage;
    public BorderPane rootLayout;
    @FXML Button button;
    @FXML Button NextImID;
    @FXML Button BackImID;
    @FXML ImageView ImViewID;
    int imageIndex = 0;
    Image a1 = new Image("com/iLearn/PowerPointImages/2e1374f984eee71dfdc0540a43316e04-0.jpg", true);
    Image a2 = new Image("com/iLearn/PowerPointImages/2e1374f984eee71dfdc0540a43316e04-1.jpg", true);
    Image a3 = new Image("com/iLearn/PowerPointImages/2e1374f984eee71dfdc0540a43316e04-2.jpg", true);
    Image a4 = new Image("com/iLearn/PowerPointImages/2e1374f984eee71dfdc0540a43316e04-3.jpg", true);
    Image a5 = new Image("com/iLearn/PowerPointImages/2e1374f984eee71dfdc0540a43316e04-4.jpg", true);
        
    Image b1 = new Image("com/iLearn/PowerPointImages/d202c73d3a1c7b3b9602729d61a92f94-1.jpg", true);
    Image b2 = new Image("com/iLearn/PowerPointImages/d202c73d3a1c7b3b9602729d61a92f94-2.jpg", true);
    Image b3 = new Image("com/iLearn/PowerPointImages/d202c73d3a1c7b3b9602729d61a92f94-3.jpg", true);
    Image b4 = new Image("com/iLearn/PowerPointImages/d202c73d3a1c7b3b9602729d61a92f94-4.jpg", true);
    Image b5 = new Image("com/iLearn/PowerPointImages/d202c73d3a1c7b3b9602729d61a92f94-5.jpg", true);


    Image[] pp1 = {a1, a2, a3, a4, a5};
    Image[] pp2 = {b1, b2, b3, b4, b5};
    Image[][] PowerPoints = {pp1, pp2};
    int PPT_Index = 0; 

    @FXML
    public void nextImageClick(ActionEvent event) {
        
    		//System.out.println("Hey Charlie!");   
    		if(imageIndex!=PowerPoints[PPT_Index].length-1) {
    			imageIndex++;
    		}
        	ImViewID.setImage(PowerPoints[PPT_Index][imageIndex]);
       
    }
    @FXML
    public void backImageClick(ActionEvent event) {
        
            //System.out.println("Hey Charlie!");   
        	if(imageIndex!=0) {
        		imageIndex--;
        	}
        	ImViewID.setImage(PowerPoints[PPT_Index][imageIndex]);
       
    }
    @FXML
    public void PP1Click(ActionEvent event) {
    	imageIndex = 0;
    	PPT_Index = 0;
          
        ImViewID.setImage(PowerPoints[PPT_Index][imageIndex]);
       
    }
    @FXML
    public void PP2Click(ActionEvent event) {
    	imageIndex = 0;
    	PPT_Index = 1;
        
    	ImViewID.setImage(PowerPoints[PPT_Index][imageIndex]);
       
    }
}

