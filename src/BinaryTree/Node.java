package BinaryTree;

public class Node {

    public int key;
    public int value;
    public int checked;

    public Node Left;
    public Node Right;

    public Node(int Key)
    {
        this.key = Key;
        this.Left=null;
        this.Right=null;
        this.checked=0;
    }
}
