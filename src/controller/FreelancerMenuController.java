package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import model.Freelancer;
import view.SceneManager;

public class FreelancerMenuController extends UserMenuController {
	
	@FXML
	private JFXButton PastPurchasesButton;
	
	@FXML
	private JFXButton BrowseGigsButton;
	
	@FXML
	private Label userNameLabel;
	
	@FXML
	private JFXButton ReviewsOfMeButton;
	
	@FXML
	private JFXButton MyGigsButton;
	
	@FXML
	private JFXButton MyProfileButton;

	@FXML
	private JFXButton BrowseFreelancersButton;

	@FXML
	void goToMyGigs(ActionEvent event) throws Exception {
		SceneManager sceneManager = new SceneManager();
		FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(getDynamicPane(), "myGigs");
		((MyGigsController) fxmlLoader.getController()).init((Freelancer) getCurrentUser());
	}
	
	@FXML
	void goToMyProfile(ActionEvent event) throws Exception {
		SceneManager sceneManager = new SceneManager();
		FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(getDynamicPane(), "myProfile");
		((MyProfileController) fxmlLoader.getController()).init((Freelancer) getCurrentUser());
	}
	
	@FXML
	void goToReviewsOfMe(ActionEvent event) throws Exception {
		SceneManager sceneManager = new SceneManager();
		FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(getDynamicPane(), "reviewsOfMe");
		((ReviewsOfMeController) fxmlLoader.getController()).init((Freelancer) getCurrentUser());
	}
}
