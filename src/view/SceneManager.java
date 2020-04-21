package view;

import javafx.event.Event;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneManager {

	public FXMLLoader switchScene (javafx.event.ActionEvent actionEvent, String sceneName) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/view/template/" + sceneName + ".fxml"));
		Parent root = fxmlLoader.load();
		Scene fxmlScene = new Scene(root);
		Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
		window.setScene(fxmlScene);
		window.show();
		return fxmlLoader;
	}
	
	public FXMLLoader switchDynamicPane (Pane dynamicPane, String name) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/template/" + name + ".fxml"));
		Pane pane = (Pane) fxmlLoader.load();
		try {
			dynamicPane.getChildren().clear();
			dynamicPane.getChildren().add(pane);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fxmlLoader;
	}
	
	public FXMLLoader switchWindow (Event actionEvent, String sceneName) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/template/" + sceneName +".fxml"));
		((Node) actionEvent.getSource()).getScene().getWindow().hide();
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Freelance Portal | " + sceneName);
		stage.setScene(new Scene(root, 1280, 720));
		stage.show();
		return fxmlLoader;
	}
	
	public FXMLLoader showWindow (Event actionEvent, String sceneName, int width, int height, boolean undecorated) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/template/" + sceneName +".fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		
		stage.setTitle("Freelance Portal | " + sceneName);
		Scene scene = new Scene(root, width, height);
		if (undecorated) {
			stage.initStyle(StageStyle.UNDECORATED);
			scene.setFill(Color.TRANSPARENT); // Fill our scene with nothing
			stage.initStyle(StageStyle.TRANSPARENT); // Important one!
		}
		stage.setScene(scene);
		stage.show();
		return fxmlLoader;
	}
}
