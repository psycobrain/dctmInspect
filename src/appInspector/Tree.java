package appInspector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;

public class Tree {
    public enum TraversalStrategy {
        DEPTH_FIRST,
        BREADTH_FIRST
    }
    
    private final static int ROOT = 0;

    private HashMap<String, Node> nodes;
    private TraversalStrategy traversalStrategy;
    private TreeItem<String> rootTree;


    // Constructors
    public Tree() {
        this(TraversalStrategy.DEPTH_FIRST);
    }

    public Tree(TraversalStrategy traversalStrategy) {
        this.nodes = new HashMap<String, Node>();
        this.traversalStrategy = traversalStrategy;
    }

    // Properties
    public HashMap<String, Node> getNodes() {
        return nodes;
    }

    public TraversalStrategy getTraversalStrategy() {
        return traversalStrategy;
    }

    public void setTraversalStrategy(TraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }

    // Public interface
    public void addRootTree(TreeItem<String> root)
    {
    	this.rootTree = root;
    }
    
    public Node addNode(String identifier, int typo) {
        return this.addNode(identifier, null, typo);
    }

    public Node addNode(String identifier, String parent, int typo) {
        Node node = new Node(identifier, typo);
        nodes.put(identifier, node);

        if (parent != null) {
            nodes.get(parent).addChild(identifier);
        }

        return node;
    }

    public void display(String identifier, TextArea obj) {
        this.display(identifier, ROOT, obj);
    }

    public void display(String identifier, int depth, TextArea obj) {
        ArrayList<String> children = nodes.get(identifier).getChildren();

        if (depth == ROOT) {
        	obj.appendText(nodes.get(identifier).getIdentifier()+" ("+depth+") \n");
//            System.out.println(nodes.get(identifier).getIdentifier());
        } else {
            String tabs = String.format("%0" + depth + "d", 0).replace("0", "    "); // 4 spaces
            obj.appendText(tabs + nodes.get(identifier).getIdentifier()+" ("+depth+") \n");
//            System.out.println(tabs + nodes.get(identifier).getIdentifier());
        }
        depth++;
        for (String child : children) {

            // Recursive call
            this.display(child, depth, obj);
        }
    }
    
    public void toTree(String id, TreeItem<String> jtree)
    {
    	this.toTree(id, ROOT, jtree);
    }
    public void toTree(String id, int level, TreeItem<String> jtree)
    {
    	ArrayList<String> figlio = nodes.get(id).getChildren();

    	
    	if (level == ROOT)
    	{
        	TreeItem<String>item = new TreeItem<String>(nodes.get(id).getIdentifier());    		
    		System.out.println("root");
    	} else {
    		TreeItem<String>item = new TreeItem<String>(nodes.get(id).getIdentifier());
    		jtree.getChildren().add(item);
    		
    		System.out.println("sub:"+level);
    	}
    	level++;
    	for (String sub, figlio)

    }

/*
    public Iterator<Node> iterator(String identifier) {
        return this.iterator(identifier, traversalStrategy);
    }

    public Iterator<Node> iterator(String identifier, TraversalStrategy traversalStrategy) {
        return traversalStrategy == TraversalStrategy.BREADTH_FIRST ?
                new BreadthFirstTreeIterator(nodes, identifier) :
                new DepthFirstTreeIterator(nodes, identifier);
    }
*/
}
