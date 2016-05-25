package appDemoFX;


import java.awt.MenuBar;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DemoFXController {

    // Reference to the main application
    private Main mainApp;
    private Utils utilsTools;
    
 // Value injected by FXMLLoader
//    public MenuBar mnuBar;
    public MenuItem mnuFileExit;
    public MenuItem mnuToolDiscovery;
    public TreeView<String> treeViewProject;
    public ContextMenu treeMenuPopup;

    Image iconProject = new Image (getClass().getResourceAsStream("images/logo_16.gif"));
    Image iconEnv = new Image (getClass().getResourceAsStream("images/type/t_dm_folder_open_16.gif"));

    
    public void initialize()
    {
    	loadTreeItems("node 1", "node 2", "node 3");
    }

    public void loadTreeItems(String... rootItems)
    {

    	TreeItem<String> root= new TreeItem<String>("Root");
       	TreeItem<String> rootProject= new TreeItem<String>("Projects",new ImageView(iconProject));
       	TreeItem<String> rootEnv= new TreeItem<String>("Env",new ImageView(iconEnv));
       	
    	root.setExpanded(true);
    	
    	root.getChildren().add(rootProject);
    	rootProject.getChildren().add(rootEnv);
    	
/*
    	for (String itemString: rootItems) {
    		root.getChildren().add(new TreeItem<String>(itemString, new ImageView(iconProject)));
    	}
*/
       	treeViewProject.setRoot(root);	
}
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }


    /**
     * Menu Actions.
     */
	@FXML public void handleExit() {
    	utilsTools.printDebug("Exit");
        System.exit(0);
    }
    
	@FXML public void handleAbout() {
    	utilsTools.printDebug("About Dialog");
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog");
    	alert.setHeaderText(null);
    	alert.setHeaderText(mainApp.codeName+" "+mainApp.codeVersion);
    	alert.setContentText("Inspector for ECM Documentum");

    	alert.showAndWait();
    }
    
	@FXML public void handleDiscovery() {
		utilsTools.printDebug("Tools->Discovery Docbroker");
	}
	
	@FXML public void treeViewMouseClick(MouseEvent mouseEvent)
	{
		  if (mouseEvent.getClickCount() == 2) {
			  TreeItem<String> item = treeViewProject.getSelectionModel().getSelectedItem();
			  utilsTools.printDebug("treeViewMouseClick:"+item.getValue());
		  }
	}
	
    @FXML void onMouseMove(MouseEvent event) {
//    	System.out.println("Mouse move X:"+event.getX()+" Y:"+event.getY());
    }
    
    @FXML void mnuPopupAddProject()
    {
		utilsTools.printDebug("Tree->Add Project");
		boolean okClicked = mainApp.showDiscoveryBroker();
    }
   
}
