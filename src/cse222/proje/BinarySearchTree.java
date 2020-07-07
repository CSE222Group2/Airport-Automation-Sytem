package cse222.proje;

public class BinarySearchTree<T extends Comparable<T>> {

    Node<T> root;

    public Node<T> search(Node<T> root, T key)
    {
        if (root==null || root.data.compareTo(key) == 0)
            return root;

        if (root.data.compareTo(key) < 0)
            return search(root.left, key);

        return search(root.right, key);
    }

    public void add(T data){ root = addRec(root,data); }

    private Node<T> addRec(Node<T> root, T data){

        if (root == null) {
            root = new Node<>(data);
            return root;
        }

        else {
            if (data.compareTo(root.data) < 0)
                root.left = addRec(root.left, data);

            else if (data.compareTo(root.data) > 0)
                root.right = addRec(root.right, data);
        }

        return root;
    }

    public void print(){

        printRec(root);
    }

    private void printRec(Node<T>root){

        if (root != null) {
            printRec(root.left);
            System.out.print("\n");
            System.out.print(root.data + "\n");
            printRec(root.right);
        }
    }


    public StringBuilder toStringBuilder(){

        StringBuilder sb = new StringBuilder();
        printRec(root,sb);
        return sb;
    }

    private void printRec(Node<T>root, StringBuilder sb){

        if (root != null) {
            printRec(root.left,sb);
            sb.append("\n");
            sb.append(root.data);
            sb.append("\n");
            printRec(root.right,sb);
        }
    }

    public void delete(T key)
    {
        root = deleteRec(root, key);
    }

    private Node<T> deleteRec(Node<T> root, T key)
    {
        if (root == null)
            return null;

        if (key.compareTo(root.data) < 0)
            root.left = deleteRec(root.left, key);

        else if (key.compareTo(root.data) > 0)
            root.right = deleteRec(root.right, key);

        else
        {
            if (root.left == null)
                return root.right;

            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    private T minValue(Node<T> root)
    {
        T minv = root.data;
        while (root.left != null)
        {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    protected static class Node<E>{

        protected  E data;
        protected Node<E> left;
        protected Node<E> right;

        public Node(E data){
            this.data=data;
            left=null;
            right=null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}