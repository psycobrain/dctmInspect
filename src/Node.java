

import java.util.ArrayList;

public class Node {

    private String identifier;
    private int	typo;
    private ArrayList<String> children;


    // Constructor
    public Node(String identifier, int typology) {
        this.identifier = identifier;
        this.typo = typology;
        children = new ArrayList<String>();
    }

    // Properties
    public String getIdentifier() {
        return identifier;
    }

    public int getTipology() {
    	return typo;
    }
    public ArrayList<String> getChildren() {
        return children;
    }

    // Public interface
    public void addChild(String identifier) {
        children.add(identifier);
    }
}
