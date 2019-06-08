public class CustomRotateBT<E extends Comparable<E>> extends CustomBST<E>  {
    protected Node<E> rotateRight(Node<E> localRoot){
        Node<E> temp=localRoot.left;
        localRoot.left=temp.right;
        temp.right=localRoot;
        return temp;
    }

    protected Node<E> rotateLeft(Node<E> localRoot){
        Node<E> temp=localRoot.right;
        localRoot.right=temp.left;///????
        temp.left=localRoot;
        return temp;
    }
}
