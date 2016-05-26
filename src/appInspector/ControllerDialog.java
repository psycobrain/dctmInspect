package appInspector;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ControllerDialog {
    
    private final String message ;
    
    @FXML
    private Label messageLabel ;
    
    public ControllerDialog(String message) {
        this.message = message ;
    }
    
    public void initialize() {
        messageLabel.setText(message);
    }
    
    public void close() {
        messageLabel.getScene().getWindow().hide();
    }
    
    
}