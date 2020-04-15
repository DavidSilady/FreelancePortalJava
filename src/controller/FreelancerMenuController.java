package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Freelancer;
import model.User;
import view.SceneManager;

public class FreelancerMenuController extends UserMenuController {
	
	@FXML
	private JFXButton PastPurchasesButton;
	
	@FXML
	private JFXButton BrowseGigsButton;
	
	@FXML
	private JFXButton ExitButton;
	
	@FXML
	private Label userNameLabel;
	
	@FXML
	private JFXButton ReviewsButton;
	
	@FXML
	private JFXButton MyGigsButton;
	
	@FXML
	private JFXButton MyProfileButton;
	
	@FXML
	void goToBrowseGigs(ActionEvent event) {
	
	}
	
	@FXML
	void goToMyGigs(ActionEvent event) {
	
	}
	
	@FXML
	void goToMyProfile(ActionEvent event) throws Exception {
		SceneManager sceneManager = new SceneManager();
		FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(getDynamicPane(), "myProfile");
		MyProfileController controller = fxmlLoader.getController();
		controller.init((Freelancer) getCurrentUser());
	}
	
	@FXML
	void goToPastPurchases(ActionEvent event) {
	
	}
	
	@FXML
	void goToReviews(ActionEvent event) {
	
	}

	void exitProgram(ActionEvent event) {
		System.exit(0);
	}

}
