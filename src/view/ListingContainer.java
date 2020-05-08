package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Listable;
import model.User;

import java.text.ParsePosition;
import java.util.ArrayList;

public class ListingContainer {
	
	@FXML
	private HBox scrollHBox;
	
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private AnchorPane scrollPaneChild;
	
	@FXML
	private ArrayList<AnchorPane> listingPanes = new ArrayList<>();
	
	private ArrayList<Listable> listableArray = new ArrayList<>();
	
	private User activeUser;
	
	public void setActiveUser(User user) {
		this.activeUser = user;
	}
	
	public void init(ArrayList<Listable> listableArray) throws Exception {
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		updateListing(listableArray);
	}
	
	public ArrayList<Listable> getListableArray() {
		return listableArray;
	}
	
	public void updateListing(ArrayList<Listable> listableArray) throws Exception {
		this.listableArray = listableArray;
		// scrollPaneChild.getChildren().clear();
		scrollHBox.getChildren().clear();
		listingPanes.clear();
		int index = 0;
		for (Listable listing : listableArray) {
			//System.out.print(index++ + " ");
			AnchorPane listingPane = new AnchorPane();
			SceneManager sceneManager = new SceneManager();
			FXMLLoader fxmlLoader = sceneManager.switchDynamicPane(listingPane, listing.getListablePaneName());
			ListablePane controller = fxmlLoader.getController();
			controller.init(listing, this);
			listingPanes.add(listingPane);
		}
		
		VBox listingBox = new VBox(10);
		
		listingBox.maxHeight(2160);
		listingBox.getChildren().addAll(listingPanes);
		//System.out.print("\n" + listingPanes.size());
		//System.out.print("\n" + listingBox.getChildren().size());
		// scrollPaneChild.getChildren().add(listingBox);
		scrollHBox.getChildren().addAll(listingBox);
		listingBox.setAlignment(Pos.CENTER);
		scrollHBox.setAlignment(Pos.CENTER);
		scrollPane.setVvalue(0);
		//anchorPane.getChildren().add(listingBox);
	}
	
	public User getActiveUser () {
		return this.activeUser;
	}
}

