package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import model.User;

public class UserHomeController {

    private User currentUser;

    @FXML
    private JFXButton PastPurchasesButton;

    @FXML
    private JFXButton BrowseGigsButton;

    @FXML
    private JFXButton ExitButton;

    @FXML
    private Label label1;

    public void init(User user){
        this.currentUser = user;
        label1.setText("Welcome, " + user.getName() + " " + user.getSurname());
    }

    @FXML
    void exitProgram(ActionEvent event) {

    }

    @FXML
    void goToBrowseGigs(ActionEvent event) {

    }

    @FXML
    void goToPastPurchases(ActionEvent event) {

    }
}