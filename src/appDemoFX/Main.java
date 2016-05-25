package appDemoFX;
	
import java.awt.Label;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;


public class Main extends Application {
	Stage mainStage;
	
	static public boolean flag = true;
	
    public String codeName = "DemoFX";
	public String codeVersion = "0.01a";
    public Label lblMsg;
    
	public void handle(final MouseEvent event) {

		// do nothing for a right-click
		if (event.isSecondaryButtonDown()) {
			return;
		}

		System.out.println("Ciao");
		// store position of initial click
//		selectionRectangleStart = computeRectanglePoint(event.getX(), event.getY());
		event.consume();
	}

	@Override public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("DemoFX.fxml"));
			Scene scene = new Scene(root);
			mainStage = primaryStage;
			
//			scene.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public boolean showDiscoveryBroker() {
		try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("DiscoveryBroker.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Person");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(mainStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        ControllerDiscoveryBroker controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setPerson();

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
		
	}
}
