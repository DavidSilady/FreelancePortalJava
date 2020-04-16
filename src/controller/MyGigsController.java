package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Freelancer;
import model.Gig;
import model.User;

public class MyGigsController {

    @FXML
    private TableView<Gig> GigTableView;

    @FXML
    private TableColumn<Gig, String> GigNameTableColumn;

    @FXML
    private TableColumn<Gig, String> CategoryTableColumn;

    @FXML
    private Label Label1;

    @FXML
    private JFXTextField GigNameTextField;

    @FXML
    private JFXButton RemoveGigButton;

    @FXML
    private ChoiceBox<String> GigCategoryChoiceBox;

    @FXML
    private JFXButton AddGigButtton;

    Freelancer currentFreelancer;

    @FXML
    void addGig(ActionEvent event) {
        String gigName = GigNameTextField.getText();
        if (gigName.isEmpty()) return;
        if ( GigCategoryChoiceBox.getSelectionModel().isEmpty()) return;
        String category = GigCategoryChoiceBox.getValue();
        currentFreelancer.addGig(gigName,category);
        GigTableView.getItems().add(new Gig(gigName,category));
    }

    @FXML
    void removeGig(ActionEvent event) {
        if (GigTableView.getSelectionModel().isEmpty()) return;
        Gig selectedGig = GigTableView.getSelectionModel().getSelectedItem();
        currentFreelancer.deleteMyGig(selectedGig.getGigName(),selectedGig.getCategory());
        GigTableView.getItems().remove(selectedGig);
    }

    public void init (Freelancer currentUser) {
        currentFreelancer = currentUser;
        GigCategoryChoiceBox.getItems().addAll(currentUser.getAllCategories());
        GigNameTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getGigName()));
        CategoryTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getCategory()));
        ObservableList<Gig> gigs = currentUser.loadMyGigs();
        GigTableView.getItems().addAll(gigs);
    }
}
