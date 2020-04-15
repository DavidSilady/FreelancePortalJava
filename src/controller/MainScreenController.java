package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DatabaseDriver;
import model.Freelancer;
import model.User;
import view.SceneManager;

import java.util.ArrayList;

public class MainScreenController {
	
	private User currentUser;
	
	@FXML
	private AnchorPane mainMenuPane;
	
	@FXML
	private AnchorPane dynamicPane;
	
	public void init(User user) throws Exception {
		if (isFreelancer(user)) {
			user = new Freelancer(user);
		}
		SceneManager sceneManager = new SceneManager();
		FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(mainMenuPane, user.getMenuSceneName());
		UserMenuController userHomeController = fxmlLoader.getController();
		userHomeController.init(user, dynamicPane);
		fxmlLoader = sceneManager.switchDynamicPane(dynamicPane, "browseGigs");
		((BrowseGigsController) fxmlLoader.getController()).init(user);
	}
	
	private boolean isFreelancer(User user) throws Exception {
		ArrayList<String> colNames = new ArrayList<String>();
		colNames.add("user_id");
		ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "freelancers",
				new ArrayList<String>(),
				" WHERE user_id = '" + user.getId() + "'");
		return ! result.isEmpty();
	}
	
}
