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

public class OrderGigController {
	
	@FXML
	private AnchorPane servicesPane;
	
	@FXML
	private JFXButton confirmOrderButton;
	
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
		((Stage) exitWindowButton.getScene().getWindow()).close();
	}
	
	@FXML
	void confirmOrder(ActionEvent event) {
		((Stage) confirmOrderButton.getScene().getWindow()).close();
	}
	
	@FXML
	void addService(ActionEvent event) {
	
	}
	
	public void init(Gig gig) throws Exception {
		this.gig = gig;
		gigNameLabel.setText(gig.getGigName());
		categoryLabel.setText(gig.getCategory());
		freelancerAliasLabel.setText(gig.getFreelancerAlias());
		
		ArrayList<Listable> services = new ArrayList<>();
		
		SceneManager sceneManager = new SceneManager();
		FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(servicesPane, "listingContainer");
		ListingContainer container = fxmlLoader.getController();
		container.init(services);
	}
	
	
}
