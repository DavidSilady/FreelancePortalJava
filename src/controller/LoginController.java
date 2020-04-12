package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.User;

public class LoginController {
	
	@FXML
	private AnchorPane rootAnchorPane;
	
	@FXML
	private JFXButton submitButton;
	
	@FXML
	private JFXTextField mailTextField;
	
	@FXML
	private JFXPasswordField passwordTextField;
	
	@FXML
	private Label userRegLabel;
	
	@FXML
	void loginUser(ActionEvent event) throws Exception {
		String mail = mailTextField.getText();
		String password = passwordTextField.getText();
		User user = new User(mail,password);
		if (user.verify() == true) {
				System.out.println("verified");
				SceneManager sceneManager = new SceneManager();
				FXMLLoader tempLoader = sceneManager.switchSceneWithReturn(event, "userHome");
				UserHomeController controller = tempLoader.getController();
				controller.init(user);
		}
	}
	
}
