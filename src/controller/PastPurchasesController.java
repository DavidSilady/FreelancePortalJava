package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import model.User;

public class PastPurchasesController {

    @FXML
    private TableView<?> PurchasesTableView;

    @FXML
    private JFXButton ViewDetailsButton;

    @FXML
    private JFXButton WriteReviewButton;

    @FXML
    private JFXButton BackButton;

    @FXML
    void goBackHome(ActionEvent event) {

    }

    @FXML
    void viewDetailsOfSelected(ActionEvent event) {

    }

    @FXML
    void writeReviewOfSelected(ActionEvent event) {

    }
	
	public void init (User currentUser) {
	}
}
