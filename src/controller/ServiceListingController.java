package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Listable;
import view.ListablePane;

public class ServiceListingController implements ListablePane {
	
	@FXML
	private JFXTextField descriptionTextField;
	
	@FXML
	private JFXButton updateButton;
	
	@FXML
	private JFXButton deleteButton;
	
	@FXML
	private JFXTextField priceTextField;
	
	@FXML
	void delete(ActionEvent event) {
	
	}
	
	@FXML
	void update(ActionEvent event) {
	
	}
	
	@Override
	public void init (Listable listing) {
	
	}
}
