package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../com/iLearnSA/view/MyImageViewer.fxml"));
<<<<<<< HEAD
//			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../com/iLearnSA/view/LoginView.fxml"));
//			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("application/TutorialView.fxml/.fxml"));
=======
>>>>>>> bd2542f42c8759cf5d7a645b221cdf0b3855f78f
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../com/iLearnSA/view/LoginView.fxml"));
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
