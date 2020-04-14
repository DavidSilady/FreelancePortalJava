package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Freelancer;

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

    private Freelancer currentFreelancer;

    public void init(Freelancer freelancer){
        this.currentFreelancer = freelancer;
        DescriptionTextArea.setText(currentFreelancer.getDescription());
    }

    @FXML
    void addLanguage(ActionEvent event) {

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

    }

    @FXML
    void saveChangesInDescription(ActionEvent event) {
        currentFreelancer.saveDescription(DescriptionTextArea.getText());
    }

}
