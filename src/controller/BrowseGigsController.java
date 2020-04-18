package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.Gig;
import model.Listable;
import model.User;
import view.SceneManager;

import java.util.ArrayList;

public class BrowseGigsController {

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
    private AnchorPane listingPane;

    @FXML
    void purchaseSelected(ActionEvent event) {

    }
    
    private void updateGigTable() {
    
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
        
        ArrayList<Gig> gigs = currentUser.findGigByCategory(category, pageNum);
    }

    public void init(User currentUser) throws Exception {
        pageNum = 0;
        pageNumLabel.setText(String.valueOf(pageNum));
        this.currentUser = currentUser;
        CategoryChoiceBox.getItems().addAll(currentUser.getAllCategories());
        ArrayList<Listable> gigs = currentUser.loadAllGigs(pageNum);
        SceneManager sceneManager = new SceneManager();
        FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(listingPane, "listingContainer");
        ListingContainerController controller = fxmlLoader.getController();
        controller.init(gigs);
    }
}
