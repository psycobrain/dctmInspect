package appInspector;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Nodes extends TreeItem {

	private int depth;
	
    Image iconProject = new Image (getClass().getResourceAsStream("images/logo_16.gif"));
    Image iconEnv = new Image (getClass().getResourceAsStream("images/type/t_dm_folder_open_16.gif"));
    Image iconDocbase = new Image (getClass().getResourceAsStream("images/type/t_dm_docbase_config_16.gif"));
    
	public Nodes(String string) {
		// TODO Auto-generated constructor stub
		super.setValue(string);
		this.depth = Main.NODE_TYPE_ROOT;
	}
	
	public Nodes(String string, int type) {
		// TODO Auto-generated constructor stub
		Image imageView = null;

		if (type == Main.NODE_TYPE_PROJECT)
			imageView = iconProject;
		if (type == Main.NODE_TYPE_ENV)
			imageView = iconEnv;
		if (type == Main.NODE_TYPE_DOCBASE)
			imageView = iconDocbase;

		this.depth = type;
		
		super.setValue(string);
		super.setGraphic(new ImageView(imageView));
		
//		System.out.println("Inserisco:"+string+" livello:"+type);
	}

	public int getDepth() {
		return this.depth;
	}
	
	public String printTree() {
//		System.out.println(sub.getDepth()+" "+sub.getValue());
		
		return "^---^";
	}

}
