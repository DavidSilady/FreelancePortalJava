package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Freelancer;
import model.PastPurchase;
import model.Review;
import model.User;
import view.SceneManager;

public class PastPurchasesController {

    @FXML
    private TableView<PastPurchase> PurchasesTableView;

    @FXML
    private TableColumn<PastPurchase , String>  GigTableColumn;

    @FXML
    private TableColumn<PastPurchase , String>  ServiceTableColumn;

    @FXML
    private TableColumn<PastPurchase , String>  FreelancerTableColumn;

    @FXML
    private TableColumn<PastPurchase , String>  PriceTableColumn;

    @FXML
    private TableColumn<PastPurchase , String>  DateTableColumn;

    @FXML
    private Label ErrorLabel;

    @FXML
    private JFXButton ViewDetailsButton;

    @FXML
    private JFXButton WriteReviewButton;

    @FXML
    void viewDetailsOfSelected(ActionEvent event) {

    }

    @FXML
    void writeReviewOfSelected(ActionEvent event) {
        if( PurchasesTableView.getSelectionModel().isEmpty())
            ErrorLabel.setVisible(true);
        else {
            ErrorLabel.setVisible(false);
            PastPurchase selectedPurchase = PurchasesTableView.getSelectionModel().getSelectedItem();

            try {
                SceneManager sceneManager = new SceneManager();
                FXMLLoader tempLoader = sceneManager.showWindow(event, "writeReview",800,600,true);
                WriteReviewController writeReviewController = tempLoader.getController();
                writeReviewController.init(currentUser, selectedPurchase);
            } catch (Exception ex){;}
        }
    }

    private User currentUser;

	public void init (User user) {
	    this.currentUser = user;
        GigTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getGigName()));
        ServiceTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getService()));
        FreelancerTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getAlias()));
        PriceTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getPriceAsString()));
        DateTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getDate()));
        ObservableList<PastPurchase> purchases = currentUser.loadMyPastPurchases();
        PurchasesTableView.setItems(purchases);
	}
}
