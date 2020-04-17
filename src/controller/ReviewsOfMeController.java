package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import model.Freelancer;
import model.Gig;
import model.Review;
import model.User;

public class ReviewsOfMeController {

    @FXML
    private JFXButton ViewReviewButton;

    @FXML
    private JFXTextArea ReviewContentTextArea;

    @FXML
    private TableView<Review> ReviewTableView;

    @FXML
    private TableColumn<Review, String> UserEmailTableColumn;

    @FXML
    private TableColumn<Review, String> GigNameTableColumn;

    @FXML
    private TableColumn<Review, String> RatingTableColumn;

    @FXML
    private ScrollBar Scroll1;

    private Freelancer currentFreelancer;

    @FXML
    void viewReview(ActionEvent event) {                // trebalo by aby ukazovalo po riadkoch
        if( ReviewTableView.getSelectionModel().isEmpty())  return;
        Review selectedReview = ReviewTableView.getSelectionModel().getSelectedItem();
        ReviewContentTextArea.setText(selectedReview.getContent());
    }
    
    public void init (Freelancer currentUser) {
        currentFreelancer = currentUser;
        UserEmailTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getUserEmail()));
        GigNameTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getGigName()));
        RatingTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getRatingAsString()));
        ObservableList<Review> reviews = currentFreelancer.loadMyReviews();
        ReviewTableView.getItems().addAll(reviews);
    }
}
