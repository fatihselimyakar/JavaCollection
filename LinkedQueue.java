import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class LinkedQueue<E> {
    private CustomDoubleLL<E>list;

    public LinkedQueue(){
        list=new CustomDoubleLL<>();
    }

    public boolean offer(E e) {
        list.add(e);
        return true;
    }


    public E poll() {
        if(isEmpty()){
            return null;
        }
        else {
            E temp=list.get(size()-1);
            list.remove(size()-1);
            return temp;
        }
    }


    public E peek() {
        if(isEmpty()){
            return null;
        }
        else{
            return list.get(size()-1);
        }
    }


    public E element() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        else
            return peek();
    }


    public E remove() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        else
            return poll();
    }


    public int size() {
        return list.size();
    }


    public boolean isEmpty() {
        return list.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        if(list.indexOf((E)o)==-1)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
