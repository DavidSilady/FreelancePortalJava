package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.DatabaseDriver;
import model.Gig;
import model.Listable;
import model.User;
import view.ListingContainer;
import view.SceneManager;

import java.util.ArrayList;
import java.util.Objects;

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
            ArrayList<Listable> gigs = fetchMostPopularGigs();
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
        sortComboBox.getItems().add("Most Popular");
        ArrayList<Listable> gigs = fetchMostPopularGigs();
        SceneManager sceneManager = new SceneManager();
        FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(listingPane, "listingContainer");
        ListingContainer controller = fxmlLoader.getController();
        controller.init(gigs);
        listingController = controller;
    }
    
    private ArrayList<Listable> fetchMostPopularGigs() {
        String query = "select g.id, f.freelance_id, c.category_name, g.gig_name, f.alias, " +
                "count(s.id) as num_sold, avg(r.rating) as avg_rating, avg(r.rating) * count(s.id) as ratio from gigs g\n" +
                "inner join categories c on g.category_id = c.id\n" +
                "inner join freelancers f on g.freelancer_id = f.freelance_id\n" +
                "inner join reviews r on g.id = r.gig_id\n" +
                "inner join services s on g.id = s.gig_id\n" +
                "group by g.id, g.gig_name, c.category_name, f.freelance_id, f.alias\n" +
                "order by ratio desc\n" +
                "limit 10 offset " + pageNum * 10;
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery(query);
        ArrayList<Listable> gigs = new ArrayList<>();
        for (ArrayList<String> row: Objects.requireNonNull(result)) {
            gigs.add(new Gig(
                    Integer.parseInt(row.get(0)),
                    Integer.parseInt(row.get(1)),
                    row.get(2),
                    row.get(3),
                    row.get(4),
                    Integer.parseInt(row.get(5)),
                    Double.parseDouble(row.get(6))
                    ));
        }
        return gigs;
    }
}
