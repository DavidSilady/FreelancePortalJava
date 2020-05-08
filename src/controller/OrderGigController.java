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
import model.*;
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
	
	@FXML
	private Label requirementLabel;
	
	private Gig gig;
	
	@FXML
	private JFXButton exitWindowButton;
	
	@FXML
	private Pane dragWindowPane;
	
	double xOffset;
	double yOffset;
	
	private ArrayList<Listable> services;
	private ListingContainer servicesContainerController;
	
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
	void confirmOrder(ActionEvent event) throws Exception {
		if (services.isEmpty()) {
			requirementLabel.setOpacity(1.0);
			return;
		}
		
		new SceneManager().showWindow(event, "orderedPopUp", 300, 160, true);
		((Stage) confirmOrderButton.getScene().getWindow()).close();
	}
	
	@FXML
	void addService(ActionEvent event) throws Exception {
		requirementLabel.setOpacity(0.0);
		services.add(new Service());
		servicesContainerController.updateListing(services);
	}
	
	public void init(Gig gig) throws Exception {
		requirementLabel.setOpacity(0.0);
		this.gig = gig;
		gigNameLabel.setText(gig.getGigName());
		categoryLabel.setText(gig.getCategory());
		freelancerAliasLabel.setText(gig.getFreelancerAlias());
		
		this.services = new ArrayList<>();
		
		SceneManager sceneManager = new SceneManager();
		FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(servicesPane, "listingContainer");
		this.servicesContainerController = fxmlLoader.getController();
		servicesContainerController.init(services);
	}
	
	
}
