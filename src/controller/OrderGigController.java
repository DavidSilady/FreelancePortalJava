package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
	
	@FXML
	private JFXTextArea billingAddressTextArea;
	
	double xOffset;
	double yOffset;
	
	private User activeUser;
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
		if (billingAddressTextArea.getText().equals("")) {
			billingAddressTextArea.setPromptText("Billing Address Required");
			billingAddressTextArea.requestFocus();
			return;
		}
		
		if (createOrder()) {
			new SceneManager().showWindow(event, "orderedPopUp", 300, 160, true);
		} else {
			new SceneManager().showWindow(event, "orderFailedPopUp", 300, 160, true);
		}
		((Stage) confirmOrderButton.getScene().getWindow()).close();
	}
	
	private boolean createOrder() {
		Order order = new Order(activeUser.getId(), getDate());
		Invoice invoice = new Invoice(getDate(), billingAddressTextArea.getText());
		for(Listable service: this.services) {
			((Service) service).setGigID(gig.getId());
		}
		return DatabaseDriver.newOrder(order, invoice, this.services);
	}
	
	String getDate() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(date);
	}
	
	@FXML
	void addService(ActionEvent event) throws Exception {
		requirementLabel.setOpacity(0.0);
		services.add(new Service());
		servicesContainerController.updateListing(services);
	}
	
	public void init(Gig gig, User activeUser) throws Exception {
		requirementLabel.setOpacity(0.0);
		this.activeUser = activeUser;
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
