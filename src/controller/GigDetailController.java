package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.DatabaseDriver;
import model.Gig;
import model.Listable;
import model.Review;
import view.ListingContainer;
import view.SceneManager;

import java.util.ArrayList;

public class GigDetailController {
	
	@FXML
	private AnchorPane reviewPane;
	
	@FXML
	private JFXButton orderButton;
	
	@FXML
	private Label gigNameLabel;
	
	@FXML
	private Label categoryLabel;
	
	@FXML
	private Label freelancerAliasLabel;
	
	@FXML
	private AnchorPane shadowPane;
	
	private Gig gig;
	
	@FXML
	private JFXButton exitWindowButton;
	
	@FXML
	private Pane dragWindowPane;
	
	double xOffset;
	double yOffset;
	
	@FXML
	void mousePressed (MouseEvent event) {
		Stage primaryStage = (Stage) dragWindowPane.getScene().getWindow();
		xOffset = primaryStage.getX() - event.getScreenX();
		yOffset = primaryStage.getY() - event.getScreenY();
	}
	
	@FXML
	void mouseDragged (MouseEvent event) {
		Stage primaryStage = (Stage) dragWindowPane.getScene().getWindow();
		primaryStage.setX(event.getScreenX() + xOffset);
		primaryStage.setY(event.getScreenY() + yOffset);
	}
	
	@FXML
	void exitWindow(ActionEvent event) {
		Stage stage = (Stage) exitWindowButton.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	void order(ActionEvent event) {
	
	}
	
	public void init(Gig gig) throws Exception {
		this.gig = gig;
		gigNameLabel.setText(gig.getGigName());
		categoryLabel.setText(gig.getCategory());
		freelancerAliasLabel.setText(gig.getFreelancerAlias());
		
		ArrayList<Listable> reviews = fetchReviews();
		
		SceneManager sceneManager = new SceneManager();
		FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(reviewPane, "listingContainer");
		ListingContainer container = fxmlLoader.getController();
		container.init(reviews);
	}
	
	ArrayList<Listable> fetchReviews() {
		ArrayList<Listable> reviews = new ArrayList<>();
		String query = "select u.name, u.surname, g.gig_name, r.content, r.rating from reviews r\n" +
				"inner join gigs g on r.gig_id = g.id\n" +
				"inner join users u on r.customer_id = u.id\n" +
				"where r.gig_id = " + gig.getId();
		ArrayList<ArrayList<String>> results = DatabaseDriver.executeQuery(query);
		if (results == null || results.isEmpty()) {
			return reviews;
		}
		for (ArrayList<String> row : results) {
			reviews.add(new Review(
					row.get(0) + " " + row.get(1),
					row.get(2),
					Integer.parseInt(row.get(4)),
					row.get(3)));
		}
		return reviews;
	}
	
}
