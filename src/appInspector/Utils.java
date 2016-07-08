package appInspector;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
	
	static public String getDepth(int value) {
		String label = "";
		if (value == Main.NODE_TYPE_ROOT) label = "Root";
		if (value == Main.NODE_TYPE_PROJECT) label = "Project";
		if (value == Main.NODE_TYPE_ENV) label = "Env";
		if (value == Main.NODE_TYPE_DOCBASE) label = "Docbase";
		return label;
	}

	static public void savePreferences(String filename) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("CONFIGURATION");
		    doc.appendChild(rootElement);
		    Element browser = doc.createElement("BROWSER");
		    browser.appendChild(doc.createTextNode("chrome"));
		    rootElement.appendChild(browser);
		    Element base = doc.createElement("BASE");
		    base.appendChild(doc.createTextNode("http:fut"));
		    rootElement.appendChild(base);
		    Element employee = doc.createElement("EMPLOYEE");
		    rootElement.appendChild(employee);
		    Element empName = doc.createElement("EMP_NAME");
		    empName.appendChild(doc.createTextNode("Anhorn, Irene"));
		    employee.appendChild(empName);
		    Element actDate = doc.createElement("ACT_DATE");
		    actDate.appendChild(doc.createTextNode("20131201"));
		    employee.appendChild(actDate);
		    
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    DOMSource source = new DOMSource(doc);
		    StreamResult result = new StreamResult(new File(filename));
		    transformer.transform(source, result);
		    System.out.println("File saved!");
		    
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}