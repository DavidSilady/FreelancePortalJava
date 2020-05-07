package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ReviewDetailController {

    @FXML
    private TextArea ReviewTextArea;

    @FXML
    private JFXButton ExitButton;

    private Stage thisStage;

    @FXML
    void exitWindow(ActionEvent event) {
        thisStage.hide();
    }

    public void init(String text, Stage thisWindow){
        this.thisStage = thisWindow;
        ReviewTextArea.setText(text);
    }

}
