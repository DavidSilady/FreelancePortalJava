package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Freelancer;
import model.PastPurchase;
import model.Review;
import view.SceneManager;

public class ReviewsOfMeController {

    @FXML
    private JFXButton ViewReviewButton;

    @FXML
    private TableView<Review> ReviewTableView;

    @FXML
    private TableColumn<Review, String> UserEmailTableColumn;

    @FXML
    private TableColumn<Review, String> GigNameTableColumn;

    @FXML
    private TableColumn<Review, String> RatingTableColumn;

    @FXML
    private Label ErrorLabel;

    private Freelancer currentFreelancer;

    @FXML
    void viewReview(ActionEvent event) {                // trebalo by aby ukazovalo po riadkoch
        if( ReviewTableView.getSelectionModel().isEmpty())
            ErrorLabel.setVisible(true);
        else {
            ErrorLabel.setVisible(false);
            Review selectedReview = ReviewTableView.getSelectionModel().getSelectedItem();
            try {
                SceneManager sceneManager = new SceneManager();
                Stage newStage = new Stage();
                FXMLLoader tempLoader = sceneManager.showWindow(event, "reviewDetail",800,600,true);
                ReviewDetailController reviewDetailController = tempLoader.getController();
                reviewDetailController.init(selectedReview.getContent());
            } catch (Exception ex){;}
        }
    }
    
    public void init (Freelancer currentUser) {
        currentFreelancer = currentUser;
        UserEmailTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getAuthorName()));
        GigNameTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getGigName()));
        RatingTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getRatingAsString()));
        ObservableList<Review> reviews = currentFreelancer.loadMyReviews();
        ReviewTableView.setItems(reviews);
    }
}
