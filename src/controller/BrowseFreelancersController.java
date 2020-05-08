package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Freelancer;
import model.Gig;
import model.User;

import java.util.ArrayList;

public class BrowseFreelancersController {

    @FXML
    private TableView<Freelancer> FreelancersTableView;

    @FXML
    private TableColumn<Freelancer, String> FreelancerTableColumn;

    @FXML
    private TableColumn<Freelancer, String> AdditionalTableColumn;

    @FXML
    private Label Label1;

    @FXML
    private JFXButton BestRatingButton;

    @FXML
    private JFXButton MostLanguagesButton;

    private User currentUser;

    @FXML
    void showBestRated(ActionEvent event) {
        FreelancerTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getAlias()));
        AdditionalTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getRatingAsString()));
        ObservableList<Freelancer> freelancers = currentUser.loadBestReviewedFreelancers(10);
        FreelancersTableView.setItems(freelancers);
        AdditionalTableColumn.setText("Rating");
        Label1.setText("Table shows freelancers whose gigs are best reviewed");
        Label1.setVisible(true);
    }

    @FXML
    void showMostLanguages(ActionEvent event) {
        FreelancerTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getAlias()));
        AdditionalTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getLanguagesKnownAsString()));
        ObservableList<Freelancer> freelancers = currentUser.loadFreelancersWithMostLanguages(10);
        FreelancersTableView.setItems(freelancers);
        AdditionalTableColumn.setText("Known languages");
        Label1.setText("Table shows freelancers who know the most languages");
        Label1.setVisible(true);
    }

    public void init(User user){
        this.currentUser = user;
    }
}
