package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Listable;
import view.SceneManager;

import java.util.ArrayList;

public class ListingContainerController {
	
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private ArrayList<AnchorPane> listingPanes = new ArrayList<>();
	
	// private ArrayList<Listable> listableArray = new ArrayList<>();
	
	public void init(ArrayList<Listable> listableArray) throws Exception {
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		updateListing(listableArray);
	}
	
	private void updateListing(ArrayList<Listable> listableArray) throws Exception {
		anchorPane.getChildren().clear();
		listingPanes.clear();
		
		for (Listable listing : listableArray) {
			AnchorPane listingPane = new AnchorPane();
			SceneManager sceneManager = new SceneManager();
			FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(listingPane, listing.getListablePaneName());
			ListablePaneController controller = fxmlLoader.getController();
			controller.init(listing);
			listingPanes.add(listingPane);
		}
		
		VBox listingBox = new VBox();
		listingBox.getChildren().addAll(listingPanes);
		anchorPane.getChildren().add(listingBox);
	}
}

