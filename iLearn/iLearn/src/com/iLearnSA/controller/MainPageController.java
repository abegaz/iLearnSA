package com.iLearnSA.controller;

import java.io.File;
import java.io.IOException;

import com.qoppa.pdf.PDFException;
import com.qoppa.pdfViewerFX.PDFViewer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPageController {
	@FXML ComboBox<String> Chapters = new ComboBox<>();
	public void initialize() {
		Chapters.getItems().addAll("Chapter 1", "Chapter 2", "Chapter 3", "Chapter 4", "Chapter 5", "Chapter 6", 
									"Chapter 7", "Chapter 8", "Chapter 9", "Chapter 10", "Chapter 11", "Chapter 12", "Chapter 13");
		Chapters.setPromptText("\t\t    Notes");
	}
	public void noteBtnClicked() throws PDFException{
		Parent root;
		try {
			PDFViewer m_PDFViewer;
			m_PDFViewer = new PDFViewer();
//			File file = new File("iLearn/src/com/iLearn/ChaptersPDF/Chapter1.pdf"); //set your location path 
			String selectedChapter = Chapters.getValue();
			File file = new File("iLearn/src/com/iLearn/ChaptersPDF/" + selectedChapter.replace(" ", "") + ".pdf"); //set your location path 
			String path = file.getAbsolutePath();
			path.replaceAll("\\\\", "/");
			m_PDFViewer.loadPDF(path);
			BorderPane borderPane = new BorderPane(m_PDFViewer);
			Scene scene = new Scene(borderPane, 800, 800);
			Stage stage = new Stage();
			stage.setTitle(selectedChapter);
			stage.setScene(scene);
			stage.centerOnScreen();
		    stage.show();
    	}catch (PDFException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  }
	}
	    
	public void tutorialBtnClicked(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/PractiseView.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Practise your code! ");
		stage.setScene(new Scene(root, 800, 800));
		stage.show();
	}
	
	public void quizBtnClicked(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/QuizView.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Test your understanding! ");
		stage.setScene(new Scene(root, 800, 800));
		stage.show();
	}
	
	public void logoutBtnClicked(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Log out");
		alert.setContentText("See you!");
		alert.showAndWait();
		
	    Parent view = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
		Scene scene = new Scene(view, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setTitle("Sign Up!");
		window.setScene(scene);
		window.show();
	}
}
