package controller;

import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneManager {
	public void setScene (javafx.event.ActionEvent actionEvent, String sceneName) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/template/" + sceneName + ".fxml"));
		Parent root = fxmlLoader.load();
		Scene fxmlScene = new Scene(root);
		Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
		window.setScene(fxmlScene);
		window.show();
	}
	
	public void switchDynamicPane (Pane dynamicPane, String name) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/template/" + name + ".fxml"));
		Pane pane = (Pane) fxmlLoader.load();
		try {
			dynamicPane.getChildren().clear();
			dynamicPane.getChildren().add(pane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void switchStage (Event actionEvent, String sceneName) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/template/" + sceneName +".fxml"));
		((Node) actionEvent.getSource()).getScene().getWindow().hide();
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		//stage.initStyle(StageStyle.UNDECORATED);
		stage.setTitle("Freelance Portal | " + sceneName);
		stage.setScene(new Scene(root, 1280, 720));
		stage.show();
	}
}
