package appInspector;


import java.awt.MenuBar;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.Event;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerInspector {

    // Reference to the main application
    private Main mainApp;
    private Utils utilsTools;
    private Nodes nodeTree;
    
 // Value injected by FXMLLoader
//    public MenuBar mnuBar;
    public MenuItem mnuFileExit;
    public MenuItem mnuToolDiscovery;
    public MenuItem mnuPopupAdd;
    public TreeView<String> treeViewProject;
    public MenuItem treeMenuCxtAdd;
    public MenuItem treeMenuCxtDel;
    public TextArea txtComment;
    
	public int depth;
	public String label = "";
	
       public void initialize()
    {
    	Nodes root= new Nodes("Root", mainApp.NODE_TYPE_ROOT);
    	root.setExpanded(true);

       	Nodes rootProject= new Nodes("Projects",mainApp.NODE_TYPE_PROJECT);
       	Nodes rootEnv= new Nodes("Env", mainApp.NODE_TYPE_ENV);

       	root.getChildren().add(rootProject);
    	rootProject.getChildren().add(rootEnv);

       	treeViewProject.setRoot(root);
       	treeViewProject.setEditable(true);
       	/*
        treeViewProject.setCellFactory(new Callback<TreeView<String>,TreeCell<String>>(){
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return new TextFieldTreeCellImpl();
            }
        });
       	*/
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
    
	@FXML public void handleShowTree() {
    	utilsTools.printDebug("handleShowTree");
    	printTree((Nodes)treeViewProject.getRoot());

    }
	
	private void printTree(Nodes sub) {
		ObservableList<Nodes> list = sub.getChildren();
		String tabs ="";
		
		for(int i=0; i<sub.getDepth(); i++)
			tabs = tabs+"- ";
		
		System.out.println(sub.getDepth()+" "+sub.getValue());
		
		for(Nodes child: list){
            if(child.getChildren().isEmpty()){
                System.out.println(sub.getDepth()+" "+child.getValue());
            } else {
                printTree(child);
            }
		}
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

//		System.out.println("treeViewMouseClick:"+mouseEvent.toString());

		TreeView jtree = (TreeView)mouseEvent.getSource();
		nodeTree = (Nodes)jtree.getSelectionModel().getSelectedItem();

//		System.out.println("nodeTree:"+nodeTree.getDepth());
		depth = nodeTree.getDepth();
		label = utilsTools.getDepth(depth);
		
   		utilsTools.printDebug("Tree->treeViewMouseClick Depth:"+depth+" Label:"+label);
   		
   		if (depth == mainApp.NODE_TYPE_DOCBASE)
   			treeMenuCxtAdd.setDisable(true);
   		else
   			treeMenuCxtAdd.setDisable(false);
   			
   		if (!nodeTree.isLeaf())
   			treeMenuCxtDel.setDisable(true);
   		else
   			treeMenuCxtDel.setDisable(false);
   			
	}


    @FXML void onMouseMove(MouseEvent event) {
//    	System.out.println("Mouse move X:"+event.getX()+" Y:"+event.getY());
    }

    @FXML void mnuPopupAddProjectAction()
    {
   		utilsTools.printDebug("Tree->Add Project Action");
   		System.out.println(nodeTree.toString());
//		boolean okClicked = mainApp.showDiscoveryBroker();
    }

    @FXML void handleMenuDocbroker() throws Throwable {
		utilsTools.printDebug("Menu->Show Docbroker");
		showWindow("Hello");
		
    }
    
    @FXML void handleTreeEditStart()
    {
   		utilsTools.printDebug("Tree->Edit Start | Depth:"+depth+" Label:"+label);
   		int level = nodeTree.getDepth();
   		if (level < mainApp.NODE_TYPE_DOCBASE)
   		{
	   		String label = utilsTools.getDepth(depth+1);
	   		Nodes newItem = new Nodes(label, depth+1);
	   		nodeTree.getChildren().add(newItem);
	   		if (!nodeTree.isExpanded())
	   			nodeTree.setExpanded(true);
   		}
    }

    @FXML void handleTreeEditCommit()
    {
   		utilsTools.printDebug("Tree->Edit Commit");
    }
    
    @FXML void handleTreeEditCancel()
    {
   		utilsTools.printDebug("Tree->Edit Cancel");
   		if (nodeTree.isLeaf())
   			System.out.println("Ultimo");
   		else
   			System.out.println("Sottonodi");
    }
    
// -----------------------------------------------------------------------------
    private final class TextFieldTreeCellImpl extends TreeCell<String> {
    	 
        private TextField textField;
        private ContextMenu addMenu = new ContextMenu();

        public TextFieldTreeCellImpl() {
        	MenuItem addMenuItem = new MenuItem("Add");       	
            addMenu.getItems().add(addMenuItem);
            MenuItem delMenuItem = new MenuItem("Remove");
            addMenu.getItems().add(delMenuItem);
            
            addMenuItem.setOnAction(new EventHandler() {
                public void handle(Event t) {
                	System.out.println(t);
                	
                	nodeTree = (Nodes)getTreeItem();
                	depth = nodeTree.getDepth()+1;

            		if (depth == Main.NODE_TYPE_ROOT) label = "Root";
            		if (depth == Main.NODE_TYPE_PROJECT) label = "Project";
            		if (depth == Main.NODE_TYPE_ENV) label = "Env";
            		if (depth == Main.NODE_TYPE_DOCBASE) label = "Docbase";
            		
                    Nodes newItem = new Nodes(label, depth);
                    nodeTree.getChildren().add(newItem);
                }
            });
        }

        
        @Override
        public void startEdit() {
            super.startEdit();
 
            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
            utilsTools.printDebug("TextFieldTreeCellImpl->startEdit:"+this.getId());
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
            utilsTools.printDebug("TextFieldTreeCellImpl->cancelEdit");
        }
 
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                    setContextMenu(addMenu);
                    /*
                    if ( getTreeItem().getParent()!= null)
                    {
                    	setContextMenu(addMenu);
                    }
                    */
                }
            }
//            utilsTools.printDebug("TextFieldTreeCellImpl->updateItem");
        }
 
        private void createTextField() {
            textField = new TextField(getString());

            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
 
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
            utilsTools.printDebug("TextFieldTreeCellImpl->createTextField");
        }
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
 // -----------------------------------------------------------------------------    

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
