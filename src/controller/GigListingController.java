package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Freelancer;
import model.Gig;

public class GigListingController {
	
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
	
	private Freelancer freelancer;
	private Gig gig;
	
	public void init(Freelancer freelancer, Gig gig) {
		this.freelancer = freelancer;
		this.gig = gig;
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
		freelancerAliasLabel.setText(freelancer.getAlias());
		ratingLabel.setText(gig.getAvgRating());
	}
}
