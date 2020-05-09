package main;

import classesORM.CategoryORM;
import classesORM.FreelancerORM;
import classesORM.UserORM;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ORMDatabaseDriver;
import model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import java.util.Iterator;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/template/startScreen.fxml"));
        primaryStage.setTitle("Freelance Portal");
        primaryStage.setScene(new Scene(root, 720, 540));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
