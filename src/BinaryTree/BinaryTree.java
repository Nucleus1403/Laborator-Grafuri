package BinaryTree;

import groovyjarjarpicocli.CommandLine;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree
{
    public Node TreeRoot;
    public int Count=0;

    private Node AddRecursive(Node current, int key) {
        if (current == null)
        {
            Node newNode = new Node(key);
            newNode.value= Count++;
            return newNode;
        }
        if (key < current.value) {
            current.Left = AddRecursive(current.Left, key);
        } else if (key > current.value) {
            current.Right = AddRecursive(current.Right, key);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    public void Add(int value) {
        TreeRoot = AddRecursive(TreeRoot, value);
    }

    private boolean ContainsNodeRecursive(Node current, int key) {
        if (current == null) {
            return false;
        }
        if (key == current.key) {
            return true;
        }
        return key < current.key
                ? ContainsNodeRecursive(current.Left, key)
                : ContainsNodeRecursive(current.Right, key);
    }

    private boolean ContainsValueRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? ContainsNodeRecursive(current.Left, value)
                : ContainsNodeRecursive(current.Right, value);
    }

    public boolean ContainsNode(int key) {
        return ContainsNodeRecursive(TreeRoot, key);
    }

    public boolean ContainsValue(int value) {
        return ContainsNodeRecursive(TreeRoot, value);
    }

    public void Delete(int value) {
        TreeRoot = DeleteRecursive(TreeRoot, value);
    }

    private Node DeleteRecursive(Node current, int key) {
        if (current == null) {
            return null;
        }

        if (key == current.key) {
            if (current.Left == null && current.Right == null) {
                return null;
            }
            if (current.Right == null) {
                return current.Left;
            }

            if (current.Left == null) {
                return current.Right;
            }

            int smallestValue = FindSmallestValue(current.Right);
            current.key = smallestValue;
            current.Right = DeleteRecursive(current.Right, smallestValue);
            return current;
        }
        if (key < current.key) {
            current.Left = DeleteRecursive(current.Left, key);
            return current;
        }
        current.Right = DeleteRecursive(current.Right, key);
        return current;
    }

    private int FindSmallestValue(Node root) {
        return root.Left == null ? root.value : FindSmallestValue(root.Left);
    }

    public void TraverseInOrder(Node node) {
        if (node != null) {
            TraverseInOrder(node.Left);
            System.out.print(" " + node.key);
            TraverseInOrder(node.Right);
        }
    }

    public void TraversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.key);
            TraversePreOrder(node.Left);
            TraversePreOrder(node.Right);
        }
    }

    private Queue<Integer> queue = new LinkedList<>();

    public void StartTraversePreOrderWithQueue(Node node)
    {
        queue.add(node.key);
        TraversePreOrderWithQueue(node);
    }
    public void TraversePreOrderWithQueue(Node node) {
        if (node != null)
        {
            if (node.Left != null) {
                queue.add(node.Left.key);
            }
            if (node.Right != null) {
                queue.add(node.Right.key);
            }
            System.out.print(" " + queue.peek());
            queue.remove();
            TraversePreOrder(node.Left);
            TraversePreOrder(node.Right);
        }
    }

    private Stack<Node> stack = new Stack();

    public void PreorderWithStack(Node node) {
        if (node == null)
            return;

        while (node != null || stack.size() > 0) {

            while (node != null) {

                stack.push(node);
                node = node.Left;
            }
            node = stack.pop();

            System.out.print(node.key + " ");
            node = node.Right;
        }
    }

    public void TraversePostOrder(Node node) {
        if (node != null) {
            TraversePostOrder(node.Left);
            TraversePostOrder(node.Right);
            System.out.print(" " + node.key);
        }
    }
}