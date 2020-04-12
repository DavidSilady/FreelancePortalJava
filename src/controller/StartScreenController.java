package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartScreenController {

    @FXML
    private JFXButton LoginButton;

    @FXML
    private Label Label1;

    @FXML
    private Label Label3;

    @FXML
    private Label Label2;

    @FXML
    private JFXButton RegisterButton;

    @FXML
    void goToLogin(ActionEvent event) throws Exception {
        SceneManager sceneManager = new SceneManager();
        sceneManager.switchScene(event, "login");
    }

    @FXML
    void goToRegister(ActionEvent event) throws Exception {
        SceneManager sceneManager = new SceneManager();
        sceneManager.switchScene(event, "registration");
    }

}
