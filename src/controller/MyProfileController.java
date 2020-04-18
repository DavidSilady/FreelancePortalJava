package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Freelancer;
import view.SceneManager;

public class MyProfileController {

    @FXML
    private JFXButton AddLanguageButton;

    @FXML
    private ListView<String> LanguagesListView;

    @FXML
    private JFXTextField AddLanguageTextField;

    @FXML
    private Label Label1;

    @FXML
    private JFXTextArea DescriptionTextArea;

    @FXML
    private Label Label2;

    @FXML
    private JFXButton SaveChangesButton;

    @FXML
    private JFXButton RemoveLanguageButton;

    private Freelancer currentUser;

    public void init(Freelancer freelancer){
        this.currentUser = freelancer;
        DescriptionTextArea.setText(currentUser.getDescription());
        currentUser.load_my_languages();
        ObservableList<String> items = FXCollections.observableArrayList(currentUser.getLanguages());
        LanguagesListView.setItems(items);
    }

    @FXML
    void addLanguage(ActionEvent event) {
        if (AddLanguageTextField.getText().isEmpty()){
            return;
        }
        currentUser.addMyLanguage(AddLanguageTextField.getText());
        AddLanguageTextField.setText("");
        ObservableList<String> items = FXCollections.observableArrayList(currentUser.getLanguages());
        LanguagesListView.setItems(items);
    }

    @FXML
    void removeSelectedLanguage(ActionEvent event) {
        try {
            String to_remove = LanguagesListView.getSelectionModel().getSelectedItem().toString();
            //System.out.println(to_remove);
            currentUser.deleteMyLanguage(to_remove);
            ObservableList<String> items = FXCollections.observableArrayList(currentUser.getLanguages());
            LanguagesListView.setItems(items);
        }
        catch (Exception ex){
            ;
        }
    }

    @FXML
    void saveChangesInDescription(ActionEvent event) {
        currentUser.saveDescription(DescriptionTextArea.getText());
    }

}
