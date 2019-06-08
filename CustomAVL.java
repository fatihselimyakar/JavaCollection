

public class CustomAVL<E extends Comparable<E>> {
    private static class Node<E extends Comparable<E>> {
        int height;
        E data;
        Node<E> left, right;

        Node(E d) {
            data = d;
            height = 1;
        }
    }

    private Node<E> root;

    // A utility function to get the height of the tree
    private int height(Node<E> N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // A utility function to get maximum of two integers
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    private Node<E> rightRotate(Node<E> y) {
        Node<E> x = y.left;
        Node<E> T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    private Node<E> leftRotate(Node<E> x) {
        Node<E> y = x.right;
        Node<E> T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    private int getBalance(Node<E> N) {
        if (N == null)
            return 0;

        return height(N.right) - height(N.left);
    }

    public boolean add(E item){
        root=insert(root, item);
        return true;
    }

    @SuppressWarnings("unchecked")
    private Node<E> insert(Node<E> node, E item) {

        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return new Node(item);

        if (item.compareTo(node.data)<0)
            node.left = insert(node.left, item);
        else if (item.compareTo(node.data)>0)
            node.right = insert(node.right, item);
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),height(node.right));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && getBalance(node.right)==1 ) {
            return leftRotate(node);
        }

        // left-left Case
        if (balance < -1 &&getBalance(node.left)==-1 ) {
            // item.compareTo(node.right.data) >0)//
            return rightRotate(node);
        }

        //  Right -left Case
        if (balance > 1 && getBalance(node.right)==-1) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        //  Left-right Case
        if (balance < -1 && getBalance(node.left)==1) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    private void preOrder(Node<E> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    private void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    public void print(){
        System.out.println("PREORDER");
        preOrder(root);
        System.out.println("\nINORDER");
        inOrder(root);
        System.out.println("\nPOSTORDER");
        postOrder(root);


    }

}