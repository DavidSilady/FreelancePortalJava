package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import view.SceneManager;

public class MainScreenController {
	
	private User currentUser;
	
	@FXML
	private AnchorPane mainMenuPane;
	
	@FXML
	private AnchorPane dynamicPane;
	
	public void init(User user) throws Exception {
		this.currentUser = user;
		SceneManager sceneManager = new SceneManager();
		FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(mainMenuPane, "userMenu");
		UserMenuController userHomeController = fxmlLoader.getController();
		userHomeController.init(user, dynamicPane);
		fxmlLoader = sceneManager.switchDynamicPane(dynamicPane, "browseGigs");
	}
	
}
