package BinaryTree;

public class Main {

    public static void main(String[] args) {

        BinaryTree tree = CreateBinaryTree();
        tree.TraverseInOrder(tree.TreeRoot);

        System.out.print("\n");
        System.out.println(tree.ContainsNode(9));

        //tree.Delete(9);
        tree.TraverseInOrder(tree.TreeRoot);

        System.out.print("\n");
        System.out.println(tree.ContainsNode(9));

        tree.TraversePreOrder(tree.TreeRoot);

        System.out.print("\n");
        tree.TraversePostOrder(tree.TreeRoot);

        System.out.print("\nQueue\n");
        tree.StartTraversePreOrderWithQueue(tree.TreeRoot);

        System.out.print("\nStack\n");
        tree.PreorderWithStack(tree.TreeRoot);
    }
    private static BinaryTree CreateBinaryTree() {
        BinaryTree bt = new BinaryTree();

        bt.Add(6);
        bt.Add(4);
        bt.Add(8);
        bt.Add(3);
        bt.Add(5);
        bt.Add(7);
        bt.Add(9);

        return bt;
    }
}