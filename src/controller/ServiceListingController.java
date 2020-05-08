package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Listable;
import model.Service;
import view.ListablePane;
import view.ListingContainer;

public class ServiceListingController implements ListablePane {
	
	@FXML
	private JFXTextField descriptionTextField;
	
	@FXML
	private JFXButton updateButton;
	
	@FXML
	private JFXButton deleteButton;
	
	@FXML
	private JFXTextField priceTextField;
	
	private ListingContainer parentContainer;
	private Service service;
	
	@FXML
	void delete(ActionEvent event) throws Exception {
		this.parentContainer.getListableArray().remove(this.service);
		parentContainer.updateListing(parentContainer.getListableArray());
	}
	
	@FXML
	void update(ActionEvent event) {
		service.setBasicInfo(Double.parseDouble(priceTextField.getText()), descriptionTextField.getText());
	}
	
	@Override
	public void init (Listable listing, ListingContainer parentContainer) {
		this.service = (Service) listing;
		this.parentContainer = parentContainer;
	}
	
	public void setParentContainer (ListingContainer controller) {
		this.parentContainer = controller;
	}
}
