package appInspector;


import java.awt.MenuBar;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerInspector {

    // Reference to the main application
    private Main mainApp;
    private Utils utilsTools;
    
 // Value injected by FXMLLoader
//    public MenuBar mnuBar;
    public MenuItem mnuFileExit;
    public MenuItem mnuToolDiscovery;
    public TreeView<String> treeViewProject;
    public ContextMenu treeMenuPopup;
    public TextArea txtComment;

    enum idTree { PROJECT, ENV, REPO };
    
    Image iconProject = new Image (getClass().getResourceAsStream("images/logo_16.gif"));
    Image iconEnv = new Image (getClass().getResourceAsStream("images/type/t_dm_folder_open_16.gif"));
    Image iconDocbase = new Image (getClass().getResourceAsStream("images/type/t_dm_folder_open_16.gif"));

    
    public void initialize()
    {
//    	loadTreeItems("node 1", "node 2", "node 3");
        Tree tree = new Tree();
        /*
         * The second parameter for the addNode method is the identifier
         * for the node's parent. In the case of the root node, either
         * null is provided or no second parameter is provided.
         */
//        tree.addRootTree(root);
        tree.addNode("Root", 0);
        tree.addNode("ISP", "Root", 1);
        tree.addNode("Sviluppo", "ISP", 2);
        tree.addNode("System", "ISP", 2);
        tree.addNode("Produzione",  "ISP", 2);
        tree.addNode("sal", "Sviluppo", 3);
        
    	TreeItem<String> root= new TreeItem<String>("Root");
    	root.setExpanded(true);

/*
       	TreeItem<String> rootProject= new TreeItem<String>("Projects",new ImageView(iconProject));
       	TreeItem<String> rootEnv= new TreeItem<String>("Env",new ImageView(iconEnv));
    	root.getChildren().add(rootProject);
    	rootProject.getChildren().add(rootEnv);
*/    	
       tree.toTree("Root", root);
        
       	treeViewProject.setRoot(root);	
       	



 
        tree.display("Root", txtComment);

        

    	
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
    
	@FXML public void treeViewMouseClick(MouseEvent mouseEvent)
	{
		System.out.println(mouseEvent.toString());
		System.out.println(mouseEvent);

	}


    @FXML void onMouseMove(MouseEvent event) {
//    	System.out.println("Mouse move X:"+event.getX()+" Y:"+event.getY());
    }
    
    @FXML void mnuPopupAddProject(MouseEvent event)
    {
    	if (event.isSecondaryButtonDown())
    		utilsTools.printDebug("Tree->Add Project");
//		boolean okClicked = mainApp.showDiscoveryBroker();
    }

    @FXML void handleMenuDocbroker() throws Throwable {
		utilsTools.printDebug("Menu->Show Docbroker");
		showWindow("Hello");
		
    }
    
    private void showWindow(String message) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/Dialog.fxml"));
        loader.setController(new ControllerDialog(message));
        final Parent root = loader.load();
        final Scene scene = new Scene(root, 250, 150);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.show();
    }
}
