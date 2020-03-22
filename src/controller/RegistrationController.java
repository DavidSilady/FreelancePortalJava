package controller;

import model.DatabaseDriver;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RegistrationController {
	
	@FXML
	private AnchorPane rootAnchorPane;
	
	@FXML
	private JFXButton submitButton;
	
	@FXML
	private JFXTextField mailTextField;
	
	@FXML
	private JFXTextField nameTextField;
	
	@FXML
	private JFXTextField surnameTextField;
	
	@FXML
	private JFXPasswordField passwordTextField;
	
	@FXML
	private JFXPasswordField confirmPasswordTextField;
	
	@FXML
	private Label somethingWrongLabel;
	
	@FXML
	private Label userRegLabel;
	
	@FXML
	void submitForm(ActionEvent event) throws Exception {
		if (!checkAllFilled())
			return;
		
		ArrayList <String> userData = new ArrayList<>();
		String mail = mailTextField.getText();
		String name = nameTextField.getText();
		String surname = surnameTextField.getText();
		String password = passwordTextField.getText();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = dateFormat.format(date);
		if (password.compareTo(confirmPasswordTextField.getText()) == 0) {
			// order of columns in db
			userData.add(name);
			userData.add(surname);
			userData.add(mail);
			userData.add(password);
			userData.add(strDate);
			//System.out.println(userData);
			boolean registrationSuccess = DatabaseDriver.insert_user(userData);
			if (registrationSuccess) {
				SceneManager sceneManager = new SceneManager();
				sceneManager.switchScene(event, "login");
			} else {
				somethingWrongLabel.setOpacity(100);
			}
		}
	}
	
	boolean checkAllFilled () {
		if (isEmpty(mailTextField))
			return false;
		if (isEmpty(nameTextField))
			return false;
		if (isEmpty(surnameTextField))
			return false;
		if (isEmpty(passwordTextField))
			return false;
		return true;
	}
	
	boolean isEmpty(JFXTextField textField) {
		if (textField.getText().equals("")) {
			if (!textField.getPromptText().contains("Must Be Filled")) // negated
				textField.setPromptText(textField.getPromptText() + " Must Be Filled");
			textField.setUnFocusColor(Color.valueOf("#ff3322"));
			return true;
		}
		textField.setUnFocusColor(Color.valueOf("#4da895"));
		return false;
	}

	boolean isEmpty(JFXPasswordField textField) {
		if (textField.getText().equals("")) {
			if (!textField.getPromptText().contains("Must Be Filled")) // negated
				textField.setPromptText(textField.getPromptText() + " Must Be Filled");
			textField.setUnFocusColor(Color.valueOf("#ff3322"));
			return true;
		}
		textField.setUnFocusColor(Color.valueOf("#4da895"));
		return false;
	}
}
