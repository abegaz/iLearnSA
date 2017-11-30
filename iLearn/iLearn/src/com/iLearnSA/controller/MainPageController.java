package com.iLearnSA.controller;

import java.io.File;
import java.io.IOException;

import com.qoppa.pdf.PDFException;
import com.qoppa.pdfViewerFX.PDFViewer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPageController {
	public void noteBtnClicked(ActionEvent event) throws PDFException{
		Parent root;
		try {
			PDFViewer m_PDFViewer;
			m_PDFViewer = new PDFViewer();
			File file = new File("iLearn/src/com/iLearn/ChaptersPDF/Chapter1.pdf"); //set your location path 
			String path = file.getAbsolutePath();
			path.replaceAll("\\\\", "/");
			m_PDFViewer.loadPDF(path);
			BorderPane borderPane = new BorderPane(m_PDFViewer);
			Scene scene = new Scene(borderPane, 800, 800);
			Stage stage = new Stage();
			stage.setTitle("Chapter 1");
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
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/TutorialView.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Test your understanding! ");
		stage.setScene(new Scene(root, 800, 800));
		stage.show();
	}
}
