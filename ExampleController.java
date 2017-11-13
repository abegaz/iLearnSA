package iLearnSA_Junk;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import application.iLearnDBConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExampleController 
{
	//chwongr4703@ung.edu
	//ahfazzaman@gmail.com
	//Instantiate private main class
	//add here
	
	//Setup all FXML content here that will be used in the controller
	@FMXL private TextBox userName_textField, passWord_textField, email_textField;
	@FMXL Button signUp;
	//Those were just some examples
	
	//After we get those set up, we have to use methods to interact
	//with our components from the view as well as the database
	
	//The main one we will be looking at would look something like this
	public void ClickSignUp_Button(ActionEvent event) throws Exception {
		System.out.println("Signing Up");

		String userName, passWord, firstName, lastName, email, sq1, sq2, sq3;

		userName = userName_textField.getText();
		passWord = passWord_textField.getText();
		//firstName = firstName_textField.getText();
		//lastName = lastName_textField.getText();
		email = email_textField.getText();

		//This is where we will make all of our validations
		//If it fails even one, we print out an exception object
		//For us as well as an error message or alert for them
		//Such as 'UserName is taken' or 'passWord is not long enough'
		//if (firstName == null || firstName == "" || firstName.trim().isEmpty()) {
			//throw new Exception("invalid First Name.");
			//This throws an exception for us to see, although I have not
			//Tested what the user would see
		//}
		//More validations would go here
		
		//After all the validations go through, It will sign them up
		//And send them back to the login page where they can log in

		//We will then create a User object
		iLearn_User user = new iLearn_User();
		
		//We then use the model to set the values for the User object
		iLearn_User.setUserName(userName);
		iLearn_User.setPassWord(passWord);
		//iLearn_User.setFirstName(firstName);
		//iLearn_User.setLastName(lastName);
		iLearn_User.setEmail(email);

		//We then set up a query statement to run to store it into the database
		//As well as run another validation to make sure the userName
		//is not taken as well as email
		//might need insert instead of update i forgot which one
		String query = "INSERT iLearnJava (username, password, email) VALUES (" + userName + ", " + passWord + ", " + email + ")";//"userName = ?, passWord = ?, firstName = ?, lastName = ?, email = ?";
		
		//We then can set up a DB connection using Chris's connection object
		connection = iLearnDBConfig.getConnection();
		//Create a statement
		Statement st = connection.createStatement();
		//Then run the query we set up above
		st.executeQuery(query);
		
		//I need to ask chris what kind of object gets created
		//When updating or inserting

		//That should be really close to setting up the users account
		//Then chris has the login part down, he just has to check for their password i believe
		Parent view = FXMLLoader.load(getClass().getResource("../view/loginView.fxml"));
		Scene scene = new Scene(view);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	
}
