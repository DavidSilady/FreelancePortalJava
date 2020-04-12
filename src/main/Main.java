package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //User jeff = new User();
        //jeff.findGigByCategory("Tax Accountant");
        Parent root = FXMLLoader.load(getClass().getResource("/template/startScreen.fxml"));
        primaryStage.setTitle("Freelance Portal");
        primaryStage.setScene(new Scene(root, 720, 540));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
