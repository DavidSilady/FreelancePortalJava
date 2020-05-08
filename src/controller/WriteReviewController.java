package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import model.PastPurchase;
import model.User;

public class WriteReviewController {

    @FXML
    private Label reviewNameLabel;

    @FXML
    private Label Label1;

    @FXML
    private JFXTextArea reviewTextArea;

    @FXML
    private Spinner<Integer> ratingSpinner;

    @FXML
    private JFXButton saveAndExitButton;

    private PastPurchase currentPurchase;
    private User currentUser;
    private Stage thisStage;

    @FXML
    void saveAndExit(ActionEvent event) {
        int gigID = currentPurchase.getGigID();
        int rating = ratingSpinner.getValue();
        String reviewText = reviewTextArea.getText();
        if (reviewText.isEmpty() || reviewText.equals("Write review here"))
            return;

        currentUser.addReview(gigID,rating,reviewText);
        thisStage.close();
    }

    public void init(User user, PastPurchase purchase){
        this.currentUser = user;
        this.currentPurchase = purchase;
        this.thisStage =  (Stage) reviewNameLabel.getScene().getWindow();
        reviewNameLabel.setText("Review of gig "+ purchase.getGigName() + " from freelancer " + purchase.getAlias());
        SpinnerValueFactory<Integer> possibleRatings = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5,3);
        ratingSpinner.setValueFactory(possibleRatings);
    }

}
