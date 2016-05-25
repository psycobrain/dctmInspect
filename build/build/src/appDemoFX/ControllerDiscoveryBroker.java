package appDemoFX;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerDiscoveryBroker {
    private Utils utilsTools;
    
	@FXML private TextField docbrokerServerField;
	@FXML private TextField docbrokerPortField;

	private Stage dialogStage;
	
	@FXML private void handleOk() {
		utilsTools.printDebug("Dialog->Discovery Docbroker->Ok");
	}
	
	@FXML private void handleCancel() {
		utilsTools.printDebug("Dialog->Discovery Docbroker->Cancel");
	}
	
	@FXML private void handleCheck() {
		utilsTools.printDebug("Dialog->Discovery Docbroker->Check");
	}
	
	@FXML private void handleAdd() {
		utilsTools.printDebug("Dialog->Discovery Docbroker->Add");
	}

	public void setDialogStage(Stage dialogStage) {
		// TODO Auto-generated method stub
		this.dialogStage = dialogStage;
	}

	public boolean isOkClicked() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setPerson() {
		// TODO Auto-generated method stub
		
	}
}