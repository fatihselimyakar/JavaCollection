import java.util.NoSuchElementException;

public class CustomQueue<E>{
    private static class Node<E>{
        private Node<E>next;
        private E data;

        public Node(Node<E>nextRef,E newData){
            next=nextRef;
            data=newData;
        }

    }

    private Node<E>head;
    private Node<E>tail;
    private int size;

    public CustomQueue(){
        size=0;
        head=null;
    }

    public boolean offer(E item){
        if(size==0){
            head=new Node<E>(head,item);
            tail=head;
        }
        else{
            head=new Node<E>(head,item);

        }
        ++size;
        return true;
    }

    public E remove(){
        if(size==0){
            throw new NoSuchElementException();
        }
        else{
            Node<E>temp=head;
            for(int i=0;i<size-2;++i){
                temp=temp.next;
            }
            E data=temp.next.data;
            tail= temp;
            temp.next=temp.next.next;
            --size;
            return data;

        }
    }

    public E poll(){
        if(size==0){
            return null;
        }
        else
            return remove();
    }

    public E peek(){
        if(size==0){
            return null;
        }
        else{
            return tail.data;
        }
    }

    public E element(){
        if(size==0){
            throw new NoSuchElementException();
        }
        else {
            return tail.data;
        }
    }

    public String toString(){
        StringBuilder temp=new StringBuilder();
        Node<E>tempHead=head;
        for(int i=0;i<size;++i,tempHead=tempHead.next){
            temp.append(tempHead.data+" ");
        }
        return temp.toString();
    }




}
