package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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
	void loginUser(ActionEvent event) {
	
	}
	
}
