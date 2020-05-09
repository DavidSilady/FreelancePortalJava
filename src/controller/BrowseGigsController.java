package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
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
    private JFXComboBox<String> categoryComboBox;

    @FXML
    private Label label1;

    @FXML
    private JFXButton filterButton;

    private User currentUser;
    
    private int pageNum;
    
    @FXML
    private JFXTextField pageNumField;
    
    @FXML
    private JFXButton rightButton;
    
    @FXML
    private JFXButton leftButton;
    
    @FXML
    private AnchorPane listingPane;
    
    @FXML
    private JFXTextField filterNameField;
    
    @FXML
    private Label totalEntriesLabel;
    
    private ListingContainer listingController;

    private String categoryConditionStatement;
    private String orderByStatement;
    private String filterNameStatement;
    private int numPages;
    private int numResults;
    
    @FXML
    void updateFilter(KeyEvent event) throws Exception {
        filterNameStatement = generateFilterNameStatement();
        //updateGigTable();
    }
    /*
    where LOWER(alias) like LOWER('%va%')
    OR LOWER(g.gig_name) like LOWER('%va%');
     */
    
    private String generateFilterNameStatement () {
        String filterText = filterNameField.getText();
        if (filterText.isEmpty())
            return "";
        String[] splitString = filterText.split("\\s+");
        StringBuilder statement = new StringBuilder("AND (");
        for (String word: splitString) {
            statement.append("LOWER(g.gig_name) LIKE LOWER('%").append(word);
            statement.append("%') OR LOWER(f.alias) LIKE LOWER('%").append(word).append("%')\n");
            statement.append("AND ");
        }
        statement.delete(statement.length() - 5, statement.length());
        statement.append(")");
        return statement.toString();
    }
    
    @FXML
    void changePage(ActionEvent event) throws Exception {
        try {
            pageNum = Integer.parseInt(pageNumField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (pageNum > numPages) pageNum = numPages;
        if (pageNum < 1) pageNum = 1;
        updateGigTable();
    }
    
    @FXML
    void checkValidity(KeyEvent event) {
        try {
            Integer.parseInt(event.getCharacter());
        } catch (Exception e) {
            pageNumField.setText(pageNumField.getText().replace(event.getCharacter(), ""));
        }
    }
    
    
    @FXML
    void reset(ActionEvent event) throws Exception {
        categoryComboBox.setValue("");
        sortComboBox.setValue("Most Popular");
        filterNameField.setText("");
        categoryConditionStatement = generateCategoryConditionStatement();
        orderByStatement = generateOrderByStatement();
        filterNameStatement = generateFilterNameStatement();
        pageNum = 1;
        fetchNumPages();
        updateGigTable();
    }
    
    private void updateGigTable() throws Exception {
        ArrayList<Listable> gigs = fetchGigs();
        listingController.updateListing(gigs);
    }
    
    @FXML
    void updateNext(ActionEvent event) throws Exception {
        pageNum += 1;
        if (pageNum > numPages) pageNum = numPages;
        updatePageCounter();
        updateGigTable();
    }
    
    @FXML
    void updatePrevious(ActionEvent event) throws Exception {
        pageNum -= 1;
        if (pageNum < 1) pageNum = 1;
        updatePageCounter();
        updateGigTable();
    }
    
    
    @FXML
    void filter (ActionEvent event) throws Exception {
        categoryConditionStatement = generateCategoryConditionStatement();
        orderByStatement = generateOrderByStatement();
        pageNum = 1;
        fetchNumPages();
        updateGigTable();
    }

    public void init(User currentUser) throws Exception {
        filterNameField.requestFocus();
        pageNum = 1;
        this.currentUser = currentUser;
        categoryComboBox.getItems().addAll(currentUser.getAllCategories());
        setupSortComboBox();
        categoryConditionStatement = generateCategoryConditionStatement();
        orderByStatement = generateOrderByStatement();
        filterNameStatement = "";
        fetchNumPages();
        ArrayList<Listable> gigs = fetchGigs();
        SceneManager sceneManager = new SceneManager();
        FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(listingPane, "listingContainer");
        ListingContainer controller = fxmlLoader.getController();
        controller.setActiveUser(currentUser);
        controller.init(gigs);
        listingController = controller;
    }
    
    private void setupSortComboBox() {
        sortComboBox.getItems().add("Most Popular");
        sortComboBox.getItems().add("Least Popular");
        sortComboBox.getItems().add("Best Rating");
        sortComboBox.getItems().add("Worst Rating");
        sortComboBox.getItems().add("Most Sold");
        sortComboBox.getItems().add("Least Sold");
        sortComboBox.setValue("Most Popular");
    }
    
    //    "ORDER BY ratio DESC\n" +
    private String generateOrderByStatement () {
        String orderBy = sortComboBox.getValue();
        // System.out.println(orderBy);
        if (orderBy.equals("Most Popular"))
            return "ORDER BY ratio DESC NULLS LAST";
        if (orderBy.equals("Least Popular"))
            return "ORDER BY ratio NULLS FIRST";
        if (orderBy.equals("Best Rating"))
            return "ORDER BY avg_rating DESC NULLS LAST";
        if (orderBy.equals("Worst Rating"))
            return "ORDER BY avg_rating NULLS FIRST";
        if (orderBy.equals("Most Sold"))
            return "ORDER BY num_sold DESC NULLS LAST";
        if (orderBy.equals("Least Sold"))
            return "ORDER BY num_sold NULLS FIRST";
        return "ORDER BY ratio DESC NULLS LAST";
    }
    
    private String generateCategoryConditionStatement () {
        if ( categoryComboBox.getSelectionModel().isEmpty())
            return "";
        String category = categoryComboBox.getValue();
        return "AND c.category_name = '" + category + "'";
    }
    
    private ArrayList<Listable> fetchGigs () {
        String query = "SELECT g.id, f.freelance_id, c.category_name, g.gig_name, f.alias, COUNT(o.id) AS num_sold,  avg_r, avg_r * COUNT(o.id) AS ratio FROM gigs AS g\n" +
                "INNER JOIN categories c ON g.category_id = c.id\n" +
                "INNER JOIN freelancers f ON g.freelancer_id = f.freelance_id\n" +
                "LEFT JOIN (SELECT gig_id, avg(rating) avg_r from reviews\n" +
                "    group by gig_id) r ON g.id = r.gig_id\n" +
                "LEFT JOIN services s ON g.id = s.gig_id\n" +
                "JOIN orders o on s.order_id = o.id\n" +
                "WHERE true \n" +
                filterNameStatement +
                categoryConditionStatement + "\n" +
                "GROUP BY g.id, g.gig_name, c.category_name, f.freelance_id, f.alias, r.avg_r\n" +
                orderByStatement + "\n"+
                "LIMIT 10 OFFSET " + (pageNum - 1) * 10;
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery(query);
        ArrayList<Listable> gigs = new ArrayList<>();
        for (ArrayList<String> row: Objects.requireNonNull(result)) {
            Double avgRating = 0.0;
            try {
                avgRating = Double.parseDouble(row.get(6));
            } catch (Exception ignored){ }
            gigs.add(new Gig(
                    Integer.parseInt(row.get(0)),
                    Integer.parseInt(row.get(1)),
                    row.get(2),
                    row.get(3),
                    row.get(4),
                    Integer.parseInt(row.get(5)),
                    avgRating
                    ));
        }
        return gigs;
    }
    
    private void updatePageCounter() {
        pageNumField.setText(String.valueOf(pageNum));
       totalEntriesLabel.setText("Pages: " + numPages + " | Results: " + numResults);
    }
    
    private void fetchNumPages () {
        String statement = "SELECT count(g.id) from gigs g \n" +
                "INNER JOIN freelancers f on f.freelance_id = g.freelancer_id\n" +
                "INNER JOIN categories c on c.id = g.category_id\n" +
                "WHERE true \n" +
                filterNameStatement + "\n" +
                categoryConditionStatement;
        String stringNumEntries = Objects.requireNonNull(DatabaseDriver.executeQuery(statement)).get(0).get(0);
    
        numResults = Integer.parseInt(stringNumEntries);
        numPages = (int) Math.ceil(numResults / 10.0);
        System.out.println("Total entries: " + numResults + "Num pages: " + numPages);
        updatePageCounter();
    }
}
