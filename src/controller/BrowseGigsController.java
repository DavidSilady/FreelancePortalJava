package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Gig;
import model.User;

public class BrowseGigsController {

    @FXML
    private TableView<Gig> GigsTableView;

    @FXML
    private TableColumn<Gig, String> GigNameTableColumn;

    @FXML
    private TableColumn<Gig, String> FreelancerTableColumn;

    @FXML
    private TableColumn<Gig, String> CategoryTableColumn;

    @FXML
    private JFXButton PurchaseSelectedButton;

    @FXML
    private ChoiceBox<String> CategoryChoiceBox;

    @FXML
    private Label label1;

    @FXML
    private JFXButton ConfirmButton;

    private User currentUser;

    @FXML
    void purchaseSelected(ActionEvent event) {

    }

    @FXML
    void updateTable(ActionEvent event) {
        if ( CategoryChoiceBox.getSelectionModel().isEmpty()) return;
        String category = CategoryChoiceBox.getValue();
        ObservableList<Gig> gigs = currentUser.findGigByCategory(category);
        GigsTableView.setItems(gigs);
    }

    public void init(User currentUser){
        this.currentUser = currentUser;
        CategoryChoiceBox.getItems().addAll(currentUser.getAllCategories());
        GigNameTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getGigName()));
        FreelancerTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getFreelancerAlias()));
        CategoryTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getCategory()));
        ObservableList<Gig> gigs = currentUser.loadAllGigs();
        GigsTableView.setItems(gigs);
    }
}
