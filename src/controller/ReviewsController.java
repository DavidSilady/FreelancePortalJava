package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import model.Freelancer;
import model.User;

public class ReviewsController {

    @FXML
    private JFXButton ViewReviewButton;

    @FXML
    private JFXTextArea ReviewContentTextArea;

    @FXML
    private TableView<?> ReviewTableView;

    @FXML
    private ScrollBar Scroll1;

    @FXML
    void viewReview(ActionEvent event) {

    }
    
    public void init (Freelancer currentUser) {
    }
}
