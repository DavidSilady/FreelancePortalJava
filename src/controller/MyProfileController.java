package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MyProfileController {

    @FXML
    private JFXButton AddLanguageButton;

    @FXML
    private ListView<?> LanguagesListView;

    @FXML
    private JFXTextField AddLanguageTextField;

    @FXML
    private Label Label1;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXTextArea DescriptionTextArea;

    @FXML
    private Label Label2;

    @FXML
    private JFXButton SaveChangesButton;

    @FXML
    private JFXButton RemoveLanguageButton;

    @FXML
    void addLanguage(ActionEvent event) {

    }

    @FXML
    void goBackHome(ActionEvent event) {

    }

    @FXML
    void removeSelectedLanguage(ActionEvent event) {

    }

    @FXML
    void saveChangesInDescription(ActionEvent event) {

    }

}
