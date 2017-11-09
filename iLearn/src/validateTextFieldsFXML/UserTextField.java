package validateTextFieldsFXML;

import javafx.scene.control.TextField;

public class UserTextField extends TextField{
	public UserTextField() {
		this.setPromptText("Username/Email");
	}

	@Override
	public void replaceText(int start, int end, String text) {
		// TODO Auto-generated method stub
		if(text.matches("[a-zA-Z0-9]") || text.isEmpty()) {
			super.replaceText(start, end, text);
		}
	}
	
	@Override
	public void replaceSelection(String replacement) {
		// TODO Auto-generated method stub
		super.replaceSelection(replacement);
	}


	
}
