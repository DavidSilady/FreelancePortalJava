package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class orderedPopUp {
	
	@FXML
	private JFXButton exitButton;
	
	@FXML
	void exit(ActionEvent event) {
		((Stage) exitButton.getScene().getWindow()).close();
	}
	
}
