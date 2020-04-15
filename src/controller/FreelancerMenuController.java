package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
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

	private Freelancer currentFreelancer;

	public void init(Freelancer freelancer){
		this.currentFreelancer = freelancer;
		userNameLabel.setText("Welcome, " + freelancer.getName() + " " + freelancer.getSurname());
	}
	
	@FXML
	void goToBrowseGigs(ActionEvent event) {
	
	}
	
	@FXML
	void goToMyGigs(ActionEvent event) {
	
	}
	
	@FXML
	void goToMyProfile(ActionEvent event) throws Exception {
		SceneManager sceneManager = new SceneManager();
		//FXMLLoader tempLoader = sceneManager.switchSceneWithReturn(event, "myProfile");
		FXMLLoader tempLoader = sceneManager.switchScene(event, "myProfile");
		MyProfileController controller = tempLoader.getController();
		controller.init(currentFreelancer);
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
