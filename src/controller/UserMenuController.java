package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.User;
import view.SceneManager;

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
    
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public AnchorPane getDynamicPane() {
        return dynamicPane;
    }
    
    public void setDynamicPane(AnchorPane dynamicPane) {
        this.dynamicPane = dynamicPane;
    }

    @FXML
    void exitProgram(ActionEvent event) {
        userNameLabel.setText(currentUser.getName() + ", there is not exit.");
    }

    @FXML
    void goToBrowseGigs(ActionEvent event) throws Exception {
        SceneManager sceneManager = new SceneManager();
        FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(dynamicPane, "browseGigs");
        ((BrowseGigsController) fxmlLoader.getController()).init(getCurrentUser());
    }

    @FXML
    void goToPastPurchases(ActionEvent event) throws Exception {
        SceneManager sceneManager = new SceneManager();
        FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(dynamicPane, "pastPurchases");
        ((PastPurchasesController) fxmlLoader.getController()).init(getCurrentUser());
    }
}