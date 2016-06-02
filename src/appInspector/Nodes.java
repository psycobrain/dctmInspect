package appInspector;

import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

public class Nodes extends TreeItem {

	public Nodes(String string) {
		// TODO Auto-generated constructor stub
		super.setValue(string);
	}
	
	public Nodes(String string, ImageView imageView) {
		// TODO Auto-generated constructor stub
		super.setValue(string);
		super.setGraphic(imageView);
	}

	public int getDepth() {
		int depth = -1;
		TreeItem nodo;
		
		if (this.isLeaf())
			depth = 0;
		else
		{
			nodo = this;
			while (!nodo.isLeaf()) {
				depth++;
				nodo = nodo.getParent();
			}
		}
		return depth;
	}
}
