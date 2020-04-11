package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

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
    private ChoiceBox<?> GigCategoryChoiceBox;

    @FXML
    private JFXButton AddGigButtton;

    @FXML
    private JFXButton BackButton;

    @FXML
    void addGig(ActionEvent event) {

    }

    @FXML
    void goBackHome(ActionEvent event) {

    }

    @FXML
    void removeGig(ActionEvent event) {

    }

}
