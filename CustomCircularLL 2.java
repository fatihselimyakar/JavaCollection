import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomCircularLL<E> implements Iterable{
    private static class Node<E>{
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
    private class CircularIterator implements Iterator{
        private Node<E> nodeRef;
        private Node<E> lastReturned;
        private int index;

        public CircularIterator(){
            nodeRef=head;
            index=0;
            lastReturned=null;
        }
        public boolean hasNext(){
            return index!=size;
        }

        public E next(){
            if(!hasNext()){
                throw new IndexOutOfBoundsException();
            }
            else{
                E temp=nodeRef.data;
                lastReturned=nodeRef;
                nodeRef=nodeRef.next;
                ++index;
                return temp;
            }

        }

        public void remove(){
            if(index==0){
                throw new InvalidParameterException();
            }
            else if(lastReturned==head){
                lastReturned.next.prev=tail;
                head=head.next;
                --size;
            }
            else if(lastReturned==tail){
                lastReturned.prev.next=head;
                tail=lastReturned.prev;
                --size;
            }
            else{
                lastReturned.next.prev=lastReturned.prev;
                lastReturned.prev.next=lastReturned.next;
                --size;
            }
        }
    }

    public Iterator iterator(){
        return new CircularIterator();
    }

    private Node<E>tail;
    private Node<E>head;
    private int size;

    public CustomCircularLL(){
        tail=head=null;
        size=0;
    }

    private Node<E> getNode(int index){
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException();
        }
        else{
            Node<E>temp=head;
            for(int i=0;i<index;++i){
                temp=temp.next;
            }
            return temp;
        }

    }
    
    @SuppressWarnings("unchecked")
    public E getIt(int index){
        Iterator it=this.iterator();
        int i=0;
        while(it.hasNext()&&i<index){
            ++i;
            it.next();
        }
        return (E)it.next();
    }

    public boolean add(E entry){
        if(size==0){
            head=tail=new Node<>(null,null,entry);
        }
        else{
            tail.next=new Node<>(head,tail,entry);
            tail=tail.next;
        }
        ++size;
        return true;
    }

    public void add(int index,E entry){
        if(index==size-1){
            add(entry);
        }
        else if(index==0){
            Node<E>temp=new Node<>(head,tail,entry);
            head.prev=temp;
            tail.next=temp;
            head=temp;
        }
        else {
            Node<E>temp=getNode(index-1);
            Node<E>node = new Node<>(temp.next,temp,entry);
            temp.next.prev=node;
            temp.next=node;
        }
        ++size;

    }

    public E remove(int index){
        Node<E>temp=getNode(index);
        E rt=temp.data;
        temp.prev.next=temp.next;
        temp.next.prev=temp.prev;
        if(index==size-1){
            tail=temp.prev;
            tail.next=head;
        }
        else if(index==0){
            tail.next=head.next;
            head=head.next;
            head.prev=tail;
        }
        --size;
        return rt;
    }

    public int size(){
        return size;
    }

    public E get(int index){
        return getNode(index).data;
    }

    public E set(int index,E entry){
        Node<E> temp=getNode(index);
        E rt=temp.data;
        temp.data=entry;
        return rt;
    }

    public int indexOf(E target){
        Node<E>temp=head;
        for(int i=0;i<size;++i,temp=temp.next){
            if(target.equals(temp.data)){
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        Node<E> nodeRef = tail;
        StringBuilder result = new StringBuilder();
        for(int i=0;i<20;++i,nodeRef=nodeRef.prev){
            result.append(nodeRef.data);
            result.append(" ");
            System.out.println("tail"+tail.data);
            System.out.println("head"+head.data);
        }
        return result.toString();
    }
}
