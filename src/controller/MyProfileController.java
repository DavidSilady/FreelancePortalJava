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
import javafx.scene.control.cell.TextFieldListCell;
import model.Freelancer;

import java.util.ArrayList;

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
    private JFXButton BackButton;

    @FXML
    private JFXTextArea DescriptionTextArea;

    @FXML
    private Label Label2;

    @FXML
    private JFXButton SaveChangesButton;

    @FXML
    private JFXButton RemoveLanguageButton;

    private Freelancer currentFreelancer;

    public void init(Freelancer freelancer){
        this.currentFreelancer = freelancer;
        DescriptionTextArea.setText(currentFreelancer.getDescription());
        currentFreelancer.load_my_languages();
        ObservableList<String> items = FXCollections.observableArrayList(currentFreelancer.getLanguages());
        LanguagesListView.setItems(items);
    }

    @FXML
    void addLanguage(ActionEvent event) {
        if (AddLanguageTextField.getText().isEmpty()){
            return;
        }
        currentFreelancer.add_my_language(AddLanguageTextField.getText());
        AddLanguageTextField.setText("");
        ObservableList<String> items = FXCollections.observableArrayList(currentFreelancer.getLanguages());
        LanguagesListView.setItems(items);
    }

    @FXML
    void goBackHome(ActionEvent event) throws Exception {
        SceneManager sceneManager = new SceneManager();
        FXMLLoader tempLoader = sceneManager.switchSceneWithReturn(event, "freelancerHome");
        FreelancerHomeController controller = tempLoader.getController();
        controller.init(currentFreelancer);
    }

    @FXML
    void removeSelectedLanguage(ActionEvent event) {
        try {
            String to_remove = LanguagesListView.getSelectionModel().getSelectedItem().toString();
            System.out.println(to_remove);
            currentFreelancer.deleteMyLanguage(to_remove);
            ObservableList<String> items = FXCollections.observableArrayList(currentFreelancer.getLanguages());
            LanguagesListView.setItems(items);
        }
        catch (Exception ex){
            ;
        }
    }

    @FXML
    void saveChangesInDescription(ActionEvent event) {
        currentFreelancer.saveDescription(DescriptionTextArea.getText());
    }

}
