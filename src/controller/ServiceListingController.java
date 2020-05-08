package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Listable;
import model.Service;
import view.ListablePane;
import view.ListingContainer;

import java.text.ParseException;

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
		double price = service.getPrice();
		
		try {
			price = Double.parseDouble(priceTextField.getText());
		} catch (Exception e) {
			priceTextField.setText("");
			this.priceTextField.setPromptText(service.getPrice() + " $");
			return;
		}
		service.setPrice(price);
		priceTextField.setText("");
		priceTextField.setPromptText(price + " $");
		
		if (descriptionTextField.getText().equals("")) {
			descriptionTextField.setPromptText("You must describe your request to the freelancer");
			descriptionTextField.requestFocus();
			return;
		}
		service.setDescription(descriptionTextField.getText());
	}
	
	@Override
	public void init (Listable listing, ListingContainer parentContainer) {
		this.service = (Service) listing;
		this.parentContainer = parentContainer;
		
		this.priceTextField.setPromptText(service.getPrice() + " $");
		this.descriptionTextField.setPromptText(service.getDescription());
	}
	
	public void setParentContainer (ListingContainer controller) {
		this.parentContainer = controller;
	}
}
