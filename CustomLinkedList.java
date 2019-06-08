
public class CustomLinkedList<E> {
    private static class Node<E>{
        private Node<E>next;
        private E data;

        public Node(Node<E>nextRef,E newData){
            next=nextRef;
            data=newData;
        }

        public Node(E newData){
            this(null,newData);
        }

    }

    private Node<E>head;
    private int size=0;

    private E addFirst(E data){
        ++size;
        head=new Node<>(head,data);
        return data;
    }
    private E addAfter(int index,E data){
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException("Exception in addAfter");
        }
        else{
            Node<E>temp=getNode(index);
            Node<E>temp2=new Node<>(temp.next,data);
            temp.next=temp2;
            ++size;
            return data;
        }
    }
    private E removeFirst(){
        E temp=head.data;
        head=head.next;
        --size;
        return temp;
    }
    private E removeAfter(int index){
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException("Exception in removeAfter");
        }
        else{
            Node<E>temp=getNode(index);
            E temp2=temp.next.data;
            temp.next=temp.next.next;
            --size;
            return temp2;
        }
    }
    private Node<E>getNode(int index){
        if(index<=size-1&&index>=0){
            Node<E>temp=head;
            for(int i=0;i<index;++i){
                temp=temp.next;
            }
            return temp;
        }
        else{
            throw new IndexOutOfBoundsException("Exception in getNode");
        }
    }
    public E get(int index){
        return getNode(index).data;
    }

    public E set(int index,E data){
        E temp=getNode(index).data;
        getNode(index).data=data;
        return temp;
    }
    public void add(int index,E data){
        if(index==0)
            addFirst(data);
        else
            addAfter(index-1, data);
    }
    public boolean add(E data){
        addFirst(data);
        return true;
    }

    public E remove(int index){
        if(index==0)
            return removeFirst();
        else
            return removeAfter(index-1);
    }

    public String toString() {
        Node<E> nodeRef = head;
        StringBuilder result = new StringBuilder();
        while (nodeRef != null){
            result.append(nodeRef.data);
            if (nodeRef.next != null)
                result.append(" ==> ");
            nodeRef = nodeRef.next;
        }
        return result.toString();
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public int indexOf(E data){
        Node<E>temp=head;
        for(int i=0;i<size-1;++i,temp=temp.next){
            if(temp.data.equals(data)){
                return i;
            }
        }
        return -1;
    }

}
