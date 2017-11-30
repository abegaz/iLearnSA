package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../com/iLearnSA/view/LoginView.fxml"));
//			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../com/iLearnSA/view/MainPage.fxml"));
//			SplitPane root = (SplitPane)FXMLLoader.load(getClass().getResource("../com/iLearnSA/view/PractiseView.fxml"));

			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
