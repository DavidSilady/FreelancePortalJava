package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import model.User;

public class BrowseGigsController {

    @FXML
    private TableView<?> GigsTableView;

    @FXML
    private JFXButton PurchaseButton;
    
    public void init(User user) {
    
    }

    @FXML
    void goBackHome(ActionEvent event) {

    }

    @FXML
    void purchaseSelected(ActionEvent event) {

    }
}