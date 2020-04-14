package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import model.Freelancer;

public class FreelancerHomeController {

    @FXML
    private JFXButton ExitButton;

    @FXML
    private JFXButton ReviewsButton;

    @FXML
    private JFXButton MyGigsButton;

    @FXML
    private JFXButton MyProfileButton;


    @FXML
    private Label label1;

    private Freelancer currentFreelancer;

    public void init(Freelancer freelancer){
        this.currentFreelancer = freelancer;
        label1.setText("Welcome, " + freelancer.getName() + " " + freelancer.getSurname());
    }

    @FXML
    void exitProgram(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void goToMyGigs(ActionEvent event) {

    }

    @FXML
    void goToMyProfile(ActionEvent event) throws Exception {
        SceneManager sceneManager = new SceneManager();
        FXMLLoader tempLoader = sceneManager.switchSceneWithReturn(event, "myProfile");
        MyProfileController controller = tempLoader.getController();
        controller.init(currentFreelancer);
    }

    @FXML
    void goToReviews(ActionEvent event) {

    }

}
