package controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
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
    private TableColumn<Freelancer, String> AverageRatingTableColumn;

    @FXML
    private Label label1;

    private User currentUser;

    public void init(User user){
        this.currentUser = user;
        FreelancerTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getAlias()));
        AverageRatingTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getRatingAsString()));
        ArrayList<Freelancer> freelancers = currentUser.loadBestReviewedFreelancers(10);
        // FreelancersTableView.setItems(freelancers);
    }
}
