package appInspector;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Utils {
	
	static private Main mainApp;

	@SuppressWarnings("static-access")
	static public void printDebug(String msg)
	{
		if (mainApp.flag)
			System.out.println(msg);
	}
	


}
