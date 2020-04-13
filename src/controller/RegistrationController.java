package controller;

import javafx.scene.image.ImageView;

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
import java.util.Calendar;
import java.util.Date;

import com.jfoenix.controls.JFXCheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import model.Freelancer;
import model.User;
import view.SceneManager;

import javax.management.InstanceAlreadyExistsException;

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
	private Label userRegLabel;

	@FXML
	private Label somethingWrongLabel;

	@FXML
	private ImageView logoImage;

	@FXML
	private JFXCheckBox FreelancerCheckBox;

	@FXML
	private JFXTextField AliasTextField;

	private boolean UserIsFreelancer = false;

	@FXML
	void setFreelancer(ActionEvent event) {
			UserIsFreelancer = true;
			AliasTextField.setDisable(false);
	}

	@FXML
	void submitForm(ActionEvent event) throws Exception {
		if (! isAllFilled())
			return;
		String mail = mailTextField.getText();
		String name = nameTextField.getText();
		String surname = surnameTextField.getText();
		String password = passwordTextField.getText();
		String alias = "";
		if (UserIsFreelancer == true)
			alias = AliasTextField.getText();
		if (password.compareTo(confirmPasswordTextField.getText()) == 0) {
			parseToDatabase(event, name, surname, mail, password, alias);
		}
	}

	String getDate() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(date);
	}

	void parseToDatabase(ActionEvent event, String name, String surname, String email, String password, String alias) throws Exception {
		try {
			boolean registrationSuccess = false;
			if (UserIsFreelancer == false) {
				User user = new User(name, surname, email, password, getDate());
				registrationSuccess = user.register();
			}
			else if (UserIsFreelancer == true){
				Freelancer freelancer = new Freelancer(name, surname, email, password, getDate(),alias);
				registrationSuccess = freelancer.register();
			}
			if (registrationSuccess) {
				SceneManager sceneManager = new SceneManager();
				sceneManager.switchScene(event, "login");
			} else {
				somethingWrongLabel.setVisible(true);
			}
		}
		catch (InstanceAlreadyExistsException ex){
			somethingWrongLabel.setVisible(true);
			somethingWrongLabel.setText("Email already registered");
		}
	}

	boolean isAllFilled () {
		if (isEmpty(mailTextField))
			return false;
		if (isEmpty(nameTextField))
			return false;
		if (isEmpty(surnameTextField))
			return false;
		if (isEmpty(passwordTextField))
			return false;
		if ((UserIsFreelancer == true) && (isEmpty(AliasTextField)))
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