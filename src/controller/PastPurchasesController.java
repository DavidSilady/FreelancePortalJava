package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import model.Freelancer;
import model.PastPurchase;
import model.User;

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
    private JFXButton ViewDetailsButton;

    @FXML
    private JFXButton WriteReviewButton;

    @FXML
    void viewDetailsOfSelected(ActionEvent event) {

    }

    @FXML
    void writeReviewOfSelected(ActionEvent event) {

    }

    private User currentUser;

	public void init (User user) {
	    this.currentUser = user;
        GigTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getGig_name()));
        ServiceTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getService()));
        FreelancerTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getAlias()));
        PriceTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getPriceAsString()));
        DateTableColumn.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getDate()));
        ObservableList<PastPurchase> purchases = currentUser.loadMyPastPurchases();
        if (purchases.isEmpty())
            System.out.println("empty");
        else
            PurchasesTableView.setItems(purchases);
	}
}
