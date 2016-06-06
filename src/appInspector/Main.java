package appInspector;
	
import java.awt.Label;
import java.io.IOException;

import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;


public class Main extends Application {
	static Stage mainStage;
	
	static public boolean flag = true;
	
    public String codeName = "DemoFX";
	public String codeVersion = "0.01a";
    public Label lblMsg;
    
    public static int	NODE_TYPE_ROOT = 1;
    public static int	NODE_TYPE_PROJECT = 2;
    public static  int	NODE_TYPE_ENV = 3;
    public static int	NODE_TYPE_DOCBASE = 4;

    
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
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Inspector.fxml"));
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

	

}
