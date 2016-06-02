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
    private Object nodeTree;
    
 // Value injected by FXMLLoader
//    public MenuBar mnuBar;
    public MenuItem mnuFileExit;
    public MenuItem mnuToolDiscovery;
    public TreeView<String> treeViewProject;
    public ContextMenu treeMenuPopup;
    public TextArea txtComment;

  
    Image iconProject = new Image (getClass().getResourceAsStream("images/logo_16.gif"));
    Image iconEnv = new Image (getClass().getResourceAsStream("images/type/t_dm_folder_open_16.gif"));
    Image iconDocbase = new Image (getClass().getResourceAsStream("images/type/t_dm_folder_open_16.gif"));

    
    public void initialize()
    {
    	Nodes root= new Nodes("Root");
    	root.setExpanded(true);

       	Nodes rootProject= new Nodes("Projects",new ImageView(iconProject));
       	Nodes rootEnv= new Nodes("Env",new ImageView(iconEnv));
       	
       	System.out.println(rootProject.getDepth());
       	System.out.println(rootEnv.getDepth());

/*
    	TreeItem root= new TreeItem("Root");
    	root.setExpanded(true);

       	TreeItem rootProject= new TreeItem("Projects",new ImageView(iconProject));
       	TreeItem rootEnv= new TreeItem("Env",new ImageView(iconEnv));
*/
       	root.getChildren().add(rootProject);
    	rootProject.getChildren().add(rootEnv);

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
 
    	alert.setHeaderText(mainApp.codeName+" "+mainApp.codeVersion);
    	alert.setContentText("Inspector for ECM Documentum");

    	alert.showAndWait();
    }
    
	@FXML public void treeViewMouseClick(MouseEvent mouseEvent)
	{
		System.out.println("treeViewMouseClick:"+mouseEvent.toString());

		TreeView jtree = (TreeView)mouseEvent.getSource();
		nodeTree = jtree.getSelectionModel().getSelectedItem();
		System.out.println(nodeTree.toString());
	}


    @FXML void onMouseMove(MouseEvent event) {
//    	System.out.println("Mouse move X:"+event.getX()+" Y:"+event.getY());
    }
    
    @FXML void mnuPopupAddProject()
    {
   		utilsTools.printDebug("Tree->Add Project");
   		System.out.println(nodeTree.toString());
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
