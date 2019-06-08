public class CustomCircularLL<E>{
    public static class Node<E>{
        private E data;
        private Node<E>next;
        private Node<E>prev;

        public Node(Node<E>nextRef,Node<E>prevRef,E newData){
            next=nextRef;
            prev=prevRef;
            data=newData;
        }

        public Node(Node<E>nextRef,E newData){
            this(nextRef,null,newData);
        }

    }
    private Node<E>tail;
    private Node<E>head;
    private int size;

    public CustomCircularLL(){
        tail=head=null;
        size=0;
    }

    public E addFirst(E entry){
        if(size==0){
            tail=head=new Node<>(head,entry);
            tail.next=head;
        }
        else{
            head=new Node<>(head, entry);
            tail.next=head;
        }
        ++size;

        return entry;
    }

    public E addAfter(int index,E entry){
        tail=getNode(index).next=new Node<E>(getNode(index).next,entry);
        ++size;
        return entry;
    }

    public E removeFirst(){
        E temp=head.data;
        if(size!=0){
            head=head.next;
            tail.next=head;
        }
        else{
            throw new NullPointerException("In removeFirst function");
        }
        --size;
        return temp;
    }

    public E removeAfter(int index){
        if(index==size-2){
           tail=getNode(index);
           tail.next=head;
        }
        Node<E>temp=getNode(index);
        E temp2=temp.next.data;
        temp.next=temp.next.next;
        --size;
        System.out.println(tail.next.data);
        System.out.println(head.data);
        return temp2;
    }

    private Node<E> getNode(int index){
        if(index>=size||index<0){
            throw new IndexOutOfBoundsException("Exception in getNode");
        }
        else{
            Node<E>temp=head;
            for(int i=0;i<index;++i){
                temp=temp.next;
            }
            return temp;
        }

    }

    public String toString() {
        Node<E> nodeRef = head;
        StringBuilder result = new StringBuilder();
        for(int i=0;i<size;++i,nodeRef=nodeRef.next){
            result.append(nodeRef.data);
            result.append(" ");
        }
        return result.toString();
    }
}
