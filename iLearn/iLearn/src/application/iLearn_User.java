package application;

public class iLearn_User 
{
	//Instances for each variable describing our user
	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;
	private String Email;
	private String securityQuestion1;
	private String securityQuestion2;
	private String securityQuestion3;
	
	//Standard getters and setters used for controller
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
}
