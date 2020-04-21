package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Listable;
import model.Review;
import view.ListablePane;

public class ReviewListingController implements ListablePane {
	
	@FXML
	private Label customerNameLabel;
	
	@FXML
	private Label reviewText;
	
	@FXML
	private Label ratingLabel;
	
	private Review review;
	
	@Override
	public void init (Listable listing) {
		review = (Review) listing;
		ratingLabel.setText(review.getRatingAsString());
		reviewText.setText(review.getContent());
		customerNameLabel.setText(review.getAuthorName());
	}
}
