package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import model.Freelancer;
import model.User;

public class MyGigsController {

    @FXML
    private TableView<?> GigTableView;

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
        String gig_name = GigNameTextField.getText();
        if (gig_name.isEmpty()) return;
        String category = GigCategoryChoiceBox.getValue();
        currentFreelancer.add_gig(gig_name,category);
    }

    @FXML
    void removeGig(ActionEvent event) {

    }
    
    public void init (Freelancer currentUser) {
        currentFreelancer = currentUser;
        GigCategoryChoiceBox.getItems().addAll(currentUser.get_all_categories());
        //for (String category : this.currentFreelancer.get_all_categories()){
        //    GigCategoryChoiceBox.getItems().add("boi");
       // }
    }
}
