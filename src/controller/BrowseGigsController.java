package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Listable;
import model.User;
import view.ListingContainer;
import view.SceneManager;

import java.util.ArrayList;

public class BrowseGigsController {
    
    @FXML
    private JFXComboBox<String> sortComboBox;
    
    @FXML
    private JFXButton PurchaseSelectedButton;

    @FXML
    private JFXComboBox<String> categoryComboBox;

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
    private AnchorPane listingPane;
    
    private ListingContainer listingController;

    @FXML
    void purchaseSelected(ActionEvent event) {

    }
    
    private void updateGigTable() throws Exception {
        if ( categoryComboBox.getSelectionModel().isEmpty()) {
            ArrayList<Listable> gigs = currentUser.loadAllGigs(pageNum);
            listingController.updateListing(gigs);
        } else {
            String category = categoryComboBox.getValue();
            ArrayList<Listable> gigs = currentUser.findGigByCategory(category, pageNum);
            listingController.updateListing(gigs);
        }
        
    }
    
    @FXML
    void updateNext(ActionEvent event) throws Exception {
        pageNum += 1;
        pageNumLabel.setText(String.valueOf(pageNum));
        updateGigTable();
    }
    
    @FXML
    void updatePrevious(ActionEvent event) throws Exception {
        pageNum -= 1;
        if (pageNum < 0) pageNum = 0;
        pageNumLabel.setText(String.valueOf(pageNum));
        updateGigTable();
    }
    
    

    @FXML
    void updateByCategory (ActionEvent event) throws Exception {
        if ( categoryComboBox.getSelectionModel().isEmpty()) return;
        pageNum = 0;
        pageNumLabel.setText(String.valueOf(pageNum));
        String category = categoryComboBox.getValue();
        ArrayList<Listable> gigs = currentUser.findGigByCategory(category, pageNum);
        listingController.updateListing(gigs);
    }

    public void init(User currentUser) throws Exception {
        pageNum = 0;
        pageNumLabel.setText(String.valueOf(pageNum));
        this.currentUser = currentUser;
        categoryComboBox.getItems().addAll(currentUser.getAllCategories());
        ArrayList<Listable> gigs = currentUser.loadAllGigs(pageNum);
        SceneManager sceneManager = new SceneManager();
        FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(listingPane, "listingContainer");
        ListingContainer controller = fxmlLoader.getController();
        controller.init(gigs);
        listingController = controller;
    }
}
