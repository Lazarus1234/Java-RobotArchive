package Trees;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryTree {

    public static void main (String[] args){


//        tree.insert( new Node(69,"!"));
//        tree.insert( new Node(35,"!!"));
//        tree.insert( new Node(22,"Hello"));
//        tree.insert( new Node(43,"General"));
//        tree.insert( new Node(73,"It"));
//        tree.insert( new Node(99,"pleasure!"));
//        tree.insert( new Node(34,"There"));
//        tree.insert( new Node(62,"Kenobi"));
//        tree.insert( new Node(95,"is"));
//        tree.insert( new Node(1,"Well"));

        Node[] nodes = new Node[] {
                new Node(69,"!"),
                new Node(35,"!!"),
                new Node(22,"Hello"),
                new Node(43,"General"),
                new Node(73,"It"),
                new Node(99,"pleasure!"),
                new Node(34,"There"),
                new Node(62,"Kenobi"),
                new Node(95,"is"),
                new Node(1,"Well")
        };

        BinaryTree tree = new BinaryTree(Arrays.asList(nodes));

        

        System.out.println("Pre order" + "->" + tree.traversePreOrder());
        System.out.println("In order" + "->" + tree.traverseInOrder());
        System.out.println("Post Order" + "->" + tree.traversePostOrder());


        tree.find(34).setData("Test");

        System.out.println(tree.traverseInOrder());

    }




    public static class Node {
        int key;
        Node left, right;
        Object data;

        public Node(int key, Object data) {
            this.key = key;
            this.data = data;

        }

        public int getKey() {
            return key;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Object getData() {
            return data;
        }

        public String toString() {
            return Integer.toString(this.key) + " = " + data.toString();
        }
    }

    Node root;

    public BinaryTree(List<Node> nodes) {
        for (Node node : nodes)
            this.insert(node);
    }

    public BinaryTree() {}

    //constructor from list


    public Node find(int key){
        return find(this.root, key);
    }

    private Node find(Node focusNode, int Key){
        if (focusNode.key == Key) {
            return focusNode;
        } else if (Key < focusNode.key){
            return find(focusNode.left, Key);
        } else if (Key > focusNode.key){
            return  find(focusNode.right,Key);
        } else {
            return null;
        }
    }

    public void insert(Node insertNode){
        if (this.root == null){
            this.root = insertNode;
        } else {
            insert(this.root, insertNode);
        }
    }

    private void insert(Node focusNode, Node insertNode) {
        if (insertNode.key < focusNode.key) {
            if (focusNode.left == null) {
                focusNode.left = insertNode;
            } else {
                insert(focusNode.left, insertNode);
            }
        } else if (insertNode.key > focusNode.key) {
            if (focusNode.right == null) {
                focusNode.right = insertNode;
            } else {
                insert(focusNode.right, insertNode);
            }
        }
    }

    public ArrayList<Node> traversePreOrder(){
        return traversePreOrder(this.root);
    }

    private ArrayList<Node> traversePreOrder(Node focusNode) {
        ArrayList<Node> nodes = new ArrayList<>();
        if (focusNode != null){
            nodes.add(focusNode);
            nodes.addAll(traversePreOrder(focusNode.left));
            nodes.addAll(traversePreOrder(focusNode.right));
        }
        return nodes;
    }
    public ArrayList<Node> traverseInOrder(){
        return traverseInOrder(this.root);
    }

    private ArrayList<Node> traverseInOrder(Node focusNode) {
        ArrayList<Node> nodes = new ArrayList<>();
        if (focusNode != null){
            nodes.addAll(traverseInOrder(focusNode.left));
            nodes.add(focusNode);
            nodes.addAll(traverseInOrder(focusNode.right));
        }
        return nodes;
    }
    public ArrayList<Node> traversePostOrder(){
        return traversePostOrder(this.root);
    }
    private ArrayList<Node> traversePostOrder(Node focusNode) {
        ArrayList<Node> nodes = new ArrayList<>();
        if (focusNode != null){
            nodes.addAll(traversePostOrder(focusNode.left));
            nodes.addAll(traversePostOrder(focusNode.right));
            nodes.add(focusNode);
        }
        return nodes;
    }


}





