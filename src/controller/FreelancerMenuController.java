package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.User;

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
	void goToMyProfile(ActionEvent event) {
	
	}
	
	@FXML
	void goToPastPurchases(ActionEvent event) {
	
	}
	
	@FXML
	void goToReviews(ActionEvent event) {
	
	}
	
}
