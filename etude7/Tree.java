package etude7;

import java.util.*;

/**
 * Skeleton of the recursive implementation of a general tree.
 * 
 * @author Michael Albert
 * @param <T> The type of values stored in the tree.
 */
public class Tree<T> {

    /** Used to build indentation for string indenter. */
    private static int count = 0;

    /** node value. */
    protected T rootValue;

    /** parent node */
    protected List<String> parentList;

    /** Children of a node. */
    protected List<Tree<T>> children;

    /** Stringbuilder used for indentng string form of tree. */
    private StringBuilder indent = new StringBuilder();

    /**
     * Constructor for tree.
     * @param rootValue starting node.
     * @param children nodes below parent node.
     */
    public Tree(T rootValue, List<Tree<T>> children, List<String> parentList) {
        this.rootValue = rootValue;
        this.children = children;
        this.parentList = parentList;
    }
    /**
     * Another constructor.
     * @param rootValue parent node.
     */
    public Tree(T rootValue) {
        this(rootValue, new ArrayList<Tree<T>>(), new ArrayList<String>());
    }

    public Tree(T rootValue, List<String> parentList){
        this(rootValue, new ArrayList<Tree<T>>(), parentList);
    }
    /**
     * Returns size of the tree.
     * @return size, size of the tree.
     */
    public int size() {
        int size = 0;
        if (children.isEmpty()){
            return 1;
        }
        size++;
        for (Tree<T> element : children){
            size += element.size();
        } 
        return size;

    }
    /**
     * Adds child tree to parent node.
     * @param child child tree to add.
     */
    public void add(Tree<T> child) {
        children.add(child);
    }

    /**
     * Find sought after value and returns if found.
     * @param value being lookd for in tree.
     * @return match returns found value or null.
     */
    public Tree<T> find(T value) {
        if (rootValue.equals(value)) {
            return this;
        }
        for (Tree<T> child : children) {
            Tree<T> match = child.find(value);
            if (match != null) {
                return match;
            }
        }
        return null;
    }
    /**
     * Returns a String representation of tree.
     * @return string representation.
     */
    public String toString() {
        if (children.isEmpty()) {
            return rootValue.toString();
        }
        return rootValue.toString() + ' ' + children.toString();
    }
    /**
     * builds an indentation and adds to string builder.
     * @return string return.
     */
    public String toIndentedString() {
        indent.append(this.rootValue.toString() + "\n");
        String s = "  ";
        for (int i = 0; i < count; i++){
            s += "  ";
        }
        for(Tree<T> child : this.children){
            count++;
            indent.append(s + child.toIndentedString());
        }
        return indent.toString();  
    }
    /**
    private static void addChildren(String target, String children) {
        Tree<String> parent = tree.find(target);
        if (parent != null) {
            for (String child : children.split(" ")) {
                parent.add(new Tree<>(child));
            }
        }
    }
    private static Tree<String> tree;

    public static void main(String[] args) {
        System.out.println("Creating tree\n-------------");
        tree = new Tree<>("food");
        System.out.print(tree + "\nsize: " + tree.size());
        System.out.println("\nAdding children\n----------------");
        addChildren("food", "meat fruit vegetable");
        System.out.print(tree + "\nsize: " + tree.size());
        System.out.println("\nAdding deeper children\n----------------------");
        addChildren("meat", "chicken beef fish");
        addChildren("fish", "salmon cod tuna shark");
        addChildren("vegetable", "cabbage");
        System.out.print(tree + "\nsize: " + tree.size());
        System.out.println("\nIndented string\n---------------");
        System.out.print(tree.toIndentedString());
        
    }
    */
}
