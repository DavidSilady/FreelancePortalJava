package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Freelancer;
import model.Gig;
import model.Listable;

public class GigListingController implements ListablePaneController {
	
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
	
	private Freelancer freelancer; // Needed for navigating to freelancer profile
	private Gig gig;
	
	public void init(Listable listing) {
		this.gig = (Gig) listing;
		setLabels();
	}
	
	@FXML
	void showFreelancerProfile(ActionEvent event) {
	
	}
	
	@FXML
	void showGigDetail(ActionEvent event) {
	
	}
	
	private void setLabels() {
		gigNameLabel.setText(gig.getGigName());
		gigDescription.setText(gig.getCategory());
		freelancerAliasLabel.setText(gig.getFreelancerAlias());
		ratingLabel.setText(gig.getAvgRating());
	}
}
