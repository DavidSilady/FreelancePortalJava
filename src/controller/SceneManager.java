package controller;

import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneManager {
	public void setScene (javafx.event.ActionEvent actionEvent, String sceneName) throws Exception{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/template/" + sceneName + ".fxml"));
		Parent signUpParent = loader.load();
		Scene signUpScene = new Scene(signUpParent);
		Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
		window.setScene(signUpScene);
		window.show();
	}
	
	public void changeDynamicPane(Pane dynamicPane, String name) throws Exception{
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
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		//stage.initStyle(StageStyle.UNDECORATED);
		stage.setTitle("SEKS | " + sceneName);
		stage.setScene(new Scene(root1, 1280, 720));
		stage.show();
	}
}
