package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ReviewDetailController {

    @FXML
    private TextArea reviewTextArea;

    @FXML
    private JFXButton exitButton;

    private Stage thisStage;

    @FXML
    void exitWindow(ActionEvent event) {
        thisStage.close();
    }

    public void init(String text){
        this.thisStage =  (Stage) exitButton.getScene().getWindow();
        reviewTextArea.setText(text);
    }

}
