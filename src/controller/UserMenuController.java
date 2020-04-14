package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.User;

public class UserMenuController {

    private User currentUser;

    @FXML
    private JFXButton PastPurchasesButton;

    @FXML
    private JFXButton BrowseGigsButton;

    @FXML
    private JFXButton ExitButton;

    @FXML
    private Label userNameLabel;
    
    @FXML
    private AnchorPane dynamicPane;

    public void init(User user, AnchorPane dynamicPane){
        this.currentUser = user;
        this.dynamicPane = dynamicPane;
        userNameLabel.setText("Signed in as " + user.getName() + " " + user.getSurname());
    }

    @FXML
    void exitProgram(ActionEvent event) {
        userNameLabel.setText(currentUser.getName() + ", there is not exit.");
    }

    @FXML
    void goToBrowseGigs(ActionEvent event) {

    }

    @FXML
    void goToPastPurchases(ActionEvent event) {

    }
}