import java.util.NoSuchElementException;
import java.util.Iterator;

public class CustomDeque<E> implements Iterable<E>{
    private E[] array;
    private int size;

    private class dequeIterator<E> implements Iterator<E>{
        private int index;

        public dequeIterator(){
            index=0;
        }

        public dequeIterator(int a){
            index=size-1;
        }

        public boolean hasNext(){
            if(index<size)
                return true;
            else
                return false;
        }

        public boolean hasPrev(){
            if(index>0)
                return true;
            else
                return false;
        }

        @SuppressWarnings("unchecked")
        public E prev(){
            --index;
            return (E) array[index+1];
        }

        @SuppressWarnings("unchecked")
        public E next(){
            ++index;
            return (E) array[index-1];
        }

        public void remove(){
            for(int i=index;i<size-1;++i){
                array[i]=array[i+1];
            }
            --size;
            --index;
        }
    }

    @SuppressWarnings("unchecked")
    private void reallocate(){
        if(size>=array.length){
            E[]temp=(E[])new Object[array.length*2];
            System.arraycopy(array, 0,temp , 0, array.length);
            array=temp;
        }
    }

    @SuppressWarnings("unchecked")
    public CustomDeque(){
        array=(E[])new Object[10];
        size=0;
    }

    public boolean offerFirst(E item){
        reallocate();
        for(int i=size;i>0;--i){
            array[i]=array[i-1];
        }
        array[0]=item;
        ++size;
        return true;
    }

    public boolean offerLast(E item){
        reallocate();
        array[size]=item;
        ++size;
        return true;
    }

    public void addFirst(E item)throws Exception{
        offerFirst(item);
    }

    public void addLast(E item)throws Exception{
        offerLast(item);
    }

    public E pollFirst(){
        if(size==0){
            return null;
        }
        E temp=array[0];
        for(int i=0;i<size-1;++i){
            array[i]=array[i+1];
        }
        --size;
        return temp;
    }

    public E pollLast(){
        if(size==0){
            return null;
        }
        E temp=array[size-1];
        array[size-1]=null;
        --size;
        return temp;
    }

    public E removeFirst(){
        if(size==0){
            throw new NoSuchElementException();
        }
        return pollFirst();
    }

    public E removeLast(){
        if(size==0){
            throw new NoSuchElementException();
        }
        return pollLast();
    }

    public E peekFirst(){
        if(size==0){
            return null;
        }
        return array[0];
    }

    public E peekLast(){
        if(size==0){
            return null;
        }
        return array[size-1];
    }

    public E getFirst(){
        if(size==0){
            throw new NoSuchElementException();
        }
        return peekFirst();
    }

    public E getLast(){
        if(size==0){
            throw new NoSuchElementException();
        }
        return peekLast();
    }

    public int indexOf(E item){
        for(int i=0;i<size;++i){
            if(item.equals(array[i]))
                return i;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public boolean removeFirstOccurrence(Object item){
        int index=indexOf((E)item);
        if(index==-1){
            return false;
        }
        else {
            for(int i=index;i<size-1;++i){
                array[i]=array[i+1];
            }
            --size;
            return true;
        }

    }

    @SuppressWarnings("unchecked")
    boolean removeLastOccurrence(Object item){
        E newItem=(E)item;
        int index=-1;
        for(int i=size-1;i>0;--i){
            if(array[i].equals(newItem)&&index==-1){
                index=i;
            }
        }
        if(index==-1){
            return false;
        }
        else {
            for(int i=index;i<size-1;++i){
                array[i]=array[i+1];
            }
            --size;
            return true;
        }
    }

    public Iterator<E> iterator(){
        return new dequeIterator<E>();
    }

    public Iterator<E> descendingIterator(){
        return new dequeIterator<>(1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<size;++i){
            builder.append(array[i]);
            builder.append(" ");
        }

        return builder.toString();
    }
}
