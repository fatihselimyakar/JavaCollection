public class CustomDLL<E>{

    public static class Node<E>{
        private E data;
        private Node<E> next;
        private Node<E> prev;
        private Node(E dataItem) {
            data = dataItem;
        }
    }

    private Node<E>head=null;
    private Node<E>tail=null;
    private int size=0;

    private void addFirst(E item){
        head=new Node<E>(item);
        tail=head;
        ++size;
    }

    private void addAfter(Node<E> after,E item){
        if(after.next==null){
            after.next=new Node<E>(item,after.next);
            after.next.prev=after;
            tail=after.next;
        }
        after.next=new Node<E>(item,after.next);
        after.next.prev=after;
        ++size;
    }

    public E removeAfter(Node<E> after){
        Node<E>temp=after.next;
        if(temp!=null&&temp.next==null){
            after.next=temp.next;
            temp.next.prev=after;
            --size;
            tail=after;
            return temp.data;
        }
        else if(temp!=null){
            after.next=temp.next;
            temp.next.prev=after;
            --size;
            return temp.data;
        }
        else
            return null;
    }
//burda kaldımmm
    public E removeFirst(){
        Node<E> temp = head;
        if (head != null){
            head = head.next;
            head.next.prev=null;
        }
        if (temp != null) {
            --size;
            return temp.data;
        }
        else
            return null;
    }

    private Node<E> getNode(int index){
        Node<E>temp=head;
        for(int i=0;i<index && temp!=null;++i){
            temp=temp.next;
        }
        return temp;
    }

    public E get(int index)throws IndexOutOfBoundsException{
        if(index<0||index>size)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        else
            return getNode(index).data;
    }

    public E set (int index, E value)throws IndexOutOfBoundsException{
         if(index<0||index >= size)
             throw new IndexOutOfBoundsException(Integer.toString(index));
         Node<E> node=getNode(index);
         E result=node.data;
         node.data=value;
         return result;
    }

    public void add(int index,E value)throws IndexOutOfBoundsException{
        if(index<0||index>size)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        else if(index==0){
            addFirst(value);
        }
        else{
            addAfter(getNode(index-1),value);
        }
    }

    public boolean add(E item){
        add(size,item);
        return true;
    }

    public String toString() {
         Node<E> nodeRef = tail;
         StringBuilder result = new StringBuilder();
         while (nodeRef != null){
             result.append(nodeRef.data);
             if (nodeRef.prev != null)
                    result.append(" ==> ");
             nodeRef = nodeRef.prev;
         }
         return result.toString();
     }
}
