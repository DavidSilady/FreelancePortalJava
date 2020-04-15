package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Freelancer;
import model.User;
import view.SceneManager;

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
	private Label SomethingWrongLabel;

	@FXML
	void loginUser(ActionEvent event) throws Exception {
		String mail = mailTextField.getText();
		String password = passwordTextField.getText();
		User user = new User(mail,password);
//<<<<<<< HEAD
		Freelancer freelancer = new Freelancer(mail,password);
		if (freelancer.verify() == true) {
			System.out.println("freelancer verified");
			SceneManager sceneManager = new SceneManager();
			FXMLLoader tempLoader = sceneManager.switchScene(event, "freelancerMenu");
			FreelancerMenuController controller = tempLoader.getController();
			controller.init(freelancer);
		}
		else if (user.verify() == true) {
				System.out.println("user verified");
//=======*/
		//if (user.verify()) {
				System.out.println("verified");
//>>>>>>> 7af2e4705406ce09f86a784476cb16036eb75f32
				SceneManager sceneManager = new SceneManager();
				FXMLLoader tempLoader = sceneManager.switchScene(event, "mainScreen");
				MainScreenController mainScreenController = tempLoader.getController();
				mainScreenController.init(user);
		}
		
		else{
			SomethingWrongLabel.setVisible(true);
			SomethingWrongLabel.setText("Invalid email or password");
		}
	}
	
}
