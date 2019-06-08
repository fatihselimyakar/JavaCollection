
public class CustomLinkedStack<E> implements  StackInt<E>{

    private static class Node<E>{
        private Node<E>next;
        private E data;

        private Node(){
            next=null;

        }

        private Node(Node<E>nextNode,E data){
            next=nextNode;
            this.data=data;
        }

    }

    private Node<E>head;
    private int size=0;

    @Override
    public E push(E object){
        head= new Node<E>(head,object);
        ++size;
        return head.data;
    }

    @Override
    public E pop()throws NullPointerException{
        E temp=head.data;
        head=head.next;
        --size;
        return temp;
    }

    @Override
    public E peek(){
        return head.data;
    }

    @Override
    public boolean isEmpty(){
        return (size==0);
    }

    @Override
    public String toString() {
        Node<E> node = head;
        StringBuilder str = new StringBuilder();
        while (node != null){
            str.append(" "+node.data);
            node = node.next;
        }
        return str.toString();

    }
}
