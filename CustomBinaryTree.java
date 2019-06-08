import java.io.Serializable;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class CustomBinaryTree<E> {
    protected static class Node<E> implements Serializable {
        protected E data;
        protected Node<E> right;
        protected Node<E> left;

        public Node(){
            this.data=null;
            this.right=null;
            this.left=null;
        }

        public Node(Node<E> right,Node<E> left,E data){
            this.data=data;
            this.right=right;
            this.left=left;
        }

        public Node(E data){
            this.data=data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    protected Node<E> root;

    public CustomBinaryTree(){
        this.root=null;
    }

    protected CustomBinaryTree(Node<E> root){
        this.root=root;
    }

    public CustomBinaryTree(E data,CustomBinaryTree<E>lefttree, CustomBinaryTree<E>righttree){
        this.root=new Node<>(data);
        this.root.right=righttree.root;
        this.root.left=lefttree.root;
    }

    @SuppressWarnings("unchecked")
    public CustomBinaryTree<E> getLeftSubtree(){
        return new CustomBinaryTree(root.left);
    }

    @SuppressWarnings("unchecked")
    public CustomBinaryTree<E> getRightSubtree(){
        return new CustomBinaryTree(root.right);
    }

    public E getData(){
        return root.data;
    }

    public boolean isLeaf(){
        return root.right==null&&root.left==null;
    }

    public void preOrderTraverse (BiConsumer<E, Integer> consumer){
        preOrderTraverse(root, 1, consumer);
    }

    private void preOrderTraverse(Node<E> node, int depth, BiConsumer<E, Integer> consumer) {
        if (node == null) {
            consumer.accept(null, depth);
        }
        else {
            consumer.accept(node.data, depth);
            preOrderTraverse(node.left, depth + 1, consumer);
            preOrderTraverse(node.right, depth + 1, consumer);
        }
    }


    public String toString() {
        final StringBuilder sb = new StringBuilder();
        preOrderTraverse((e, d) -> {
            for (int i = 1; i < d; i++) {
                sb.append(" ");
            }
            sb.append(e);
            sb.append("\n");
        });
        return sb.toString();
    }


    /*private void toString(Node<E> node,int depth,StringBuilder sb) {//OKU BİDAHA
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null){
            sb.append("null\n");
        }
        else{
            sb.append(node.toString());
            sb.append("\n");
            toString(node.left, depth + 1, sb);
            toString(node.right, depth + 1, sb);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, 1, sb);
        return sb.toString();
    }*/

}
