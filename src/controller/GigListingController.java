package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import model.Freelancer;
import model.Gig;
import model.Listable;
import model.User;
import view.ListablePane;
import view.ListingContainer;
import view.SceneManager;

public class GigListingController implements ListablePane {
	
	@FXML
	private JFXButton detailButton;
	
	@FXML
	private JFXButton freelancerProfileButton;
	
	@FXML
	private Label gigNameLabel;
	
	@FXML
	private Label gigDescription;
	
	@FXML
	private Label ratingLabel;
	
	@FXML
	private Label freelancerAliasLabel;
	
	@FXML
	private Label soldLabel;
	
	private Freelancer freelancer; // Needed for navigating to freelancer profile
	private Gig gig;
	private User activeUser;
	

	@Override
	public void init (Listable listing, ListingContainer parentContainer) {
		this.activeUser = parentContainer.getActiveUser();
		this.gig = (Gig) listing;
		setLabels();
	}
	
	@FXML
	void showFreelancerProfile(ActionEvent event) {
	
	}
	
	@FXML
	void showGigDetail(ActionEvent event) throws Exception {
		SceneManager sceneManager = new SceneManager();
		FXMLLoader fxmlLoader = sceneManager.showWindow(event, "gigDetail", 600, 520, true);
		GigDetailController gigDetailController = fxmlLoader.getController();
		gigDetailController.init(gig, this.activeUser);
	}
	
	private void setLabels() {
		soldLabel.setText(gig.getNumSold());
		gigNameLabel.setText(gig.getGigName());
		gigDescription.setText(gig.getCategory());
		freelancerAliasLabel.setText(gig.getFreelancerAlias());
		ratingLabel.setText(gig.getAvgRating());
	}
}
