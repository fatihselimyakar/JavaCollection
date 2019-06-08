public class CustomBST<E extends Comparable<E>> extends CustomBinaryTree<E> {
    protected boolean addReturn;
    protected E deleteReturn;

    public CustomBST(){
        root=null;
    }

    protected CustomBST(Node<E> root){
        this.root=root;
    }

    public CustomBST(CustomBST<E>rightTree,CustomBST<E>leftTree,E data){
        root=new Node<>(data);
        root.left=leftTree.root;
        root.right=rightTree.root;
    }

    private E search(Node<E>localRoot,E target){
        if(localRoot==null)
            return null;
        int compRes=target.compareTo(localRoot.data);
        if(compRes==0){//.equals olsa ne olurdu?
            return localRoot.data;
        }
        else if(compRes<0){
            return search(localRoot.left, target);
        }
        else {
            return search(localRoot.right, target);
        }
    }

    public E find(E target){
        return search(root,target);
    }

    public boolean contains(E target){
        return find(target)!=null;
    }

    public boolean add(E data){
        root = add(root,data);
        return addReturn;
    }

    private Node<E> add(Node<E>localRoot,E data){
        if(localRoot==null){//TREE BOMBOŞ İSE DİYE
            addReturn=true;
            return new Node<>(data);
        }
        else if(data.compareTo(localRoot.data)==0){//ELEMAN ZATEN EKLİYSE
            addReturn=false;
            return localRoot;
        }
        else if(data.compareTo(localRoot.data)<0){//ELEMAN NODE'DAN KÜÇÜKSE
            localRoot.left = add(localRoot.left, data);
            return localRoot;
        }
        else{
            localRoot.right=add(localRoot.right,data);//ELEMAN NODE'DAN BÜYÜKSE
            return localRoot;
        }
    }

    public E delete(E target){
        remove(root, target);
        return deleteReturn;
    }
    private Node<E> remove(Node<E>localRoot,E target){
        if(localRoot==null){
            deleteReturn=null;
            return localRoot;
        }

        int compRes=target.compareTo(localRoot.data);
        if(compRes<0){
            localRoot.left=remove(localRoot.left, target);
            return localRoot;
        }
        else if(compRes>0){
            localRoot.right=remove(localRoot.right, target);
            return localRoot;
        }
        else {
            deleteReturn=localRoot.data;
            if(localRoot.left==null){
                return localRoot.right;
            }
            else if(localRoot.right==null){
                return localRoot.left;
            }
            else{
                // with inorder predecessor.
                if(localRoot.left.right==null){
                    localRoot.data = localRoot.left.data;
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                }
                else{
                    localRoot.data = findLargestChild(localRoot.left); return localRoot;
                }
            }
        }
    }


    private E findLargestChild(Node<E>parent){
        if (parent.right.right == null) {
            E returnValue = parent.right.data; parent.right = parent.right.left; return returnValue;
        }
        else {
            return findLargestChild(parent.right);
        }
    }

}
