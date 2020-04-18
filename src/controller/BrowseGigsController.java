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
    
    private int pageNum;
    
    @FXML
    private Label pageNumLabel;
    
    @FXML
    private JFXButton rightButton;
    
    @FXML
    private JFXButton leftButton;

    @FXML
    void purchaseSelected(ActionEvent event) {

    }
    
    private void updateGigTable() {
        if ( CategoryChoiceBox.getSelectionModel().isEmpty()) {
            ObservableList<Gig> gigs = currentUser.loadAllGigs(pageNum);
            GigsTableView.setItems(gigs);
        } else {
            String category = CategoryChoiceBox.getValue();
            ObservableList<Gig> gigs = currentUser.findGigByCategory(category, pageNum);
            GigsTableView.setItems(gigs);
        }
    }
    
    @FXML
    void updateNext(ActionEvent event) {
        pageNum += 1;
        pageNumLabel.setText(String.valueOf(pageNum));
        updateGigTable();
    }
    
    @FXML
    void updatePrevious(ActionEvent event) {
        pageNum -= 1;
        if (pageNum < 0) pageNum = 0;
        pageNumLabel.setText(String.valueOf(pageNum));
        updateGigTable();
    }
    
    

    @FXML
    void updateByCategory (ActionEvent event) {
        if ( CategoryChoiceBox.getSelectionModel().isEmpty()) return;
        pageNum = 0;
        pageNumLabel.setText(String.valueOf(pageNum));
        String category = CategoryChoiceBox.getValue();
        
        ObservableList<Gig> gigs = currentUser.findGigByCategory(category, pageNum);
        GigsTableView.setItems(gigs);
    }

    public void init(User currentUser){
        pageNum = 0;
        pageNumLabel.setText(String.valueOf(pageNum));
        this.currentUser = currentUser;
        CategoryChoiceBox.getItems().addAll(currentUser.getAllCategories());
        GigNameTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getGigName()));
        FreelancerTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getFreelancerAlias()));
        CategoryTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getCategory()));
        ObservableList<Gig> gigs = currentUser.loadAllGigs(pageNum);
        GigsTableView.setItems(gigs);
    }
}
