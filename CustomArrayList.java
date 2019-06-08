import java.util.Iterator;
import java.util.List;

public class CustomArrayList<E>{
    private E array[];
    private int size;

    @SuppressWarnings("unchecked")
    public CustomArrayList(){
        array=(E[])new Object[10];
        size=0;
    }

    @SuppressWarnings("unchecked")
    private void reallocate(){
        if(size==array.length){
            E[] temp=(E[])new Object[array.length*2];
            System.arraycopy(array, 0,temp , 0, array.length);
            array=temp;
        }
    }

    public E get(int index)throws IndexOutOfBoundsException{
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException("Arraylist in get function.");
        }
        else{
            return array[index];
        }
    }

    public int size(){
        return size;
    }

    public E set(int index,E entry)throws IndexOutOfBoundsException{
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException();
        }
        else if(entry!=array[index]){
            E temp=array[index];
            array[index]=entry;
            return temp;
        }
        return array[index];
    }

    public boolean add(E entry){
        reallocate();
        array[size]=entry;
        ++size;
        return true;
    }

    public void add(int index,E entry){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException();
        }
        else{
            reallocate();
            for(int i=size;i>index-1;--i){
                array[i+1]=array[i];
            }
            array[index]=entry;
            ++size;
        }
    }

    @SuppressWarnings("unchecked")
    public int indexOf(Object target){
        E temp=(E)target;
        for(int i=0;i<size;++i){
            if(array[i].equals(temp)){
                return i;
            }
        }
        return -1;
    }

    public E remove(int index){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException();
        }
        E temp=array[index];
        for(int i=index;i<size-1;++i){
            array[i]=array[i+1];
        }
        --size;
        return temp;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public boolean contains(Object o) {
        if(indexOf(o)==-1)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        StringBuilder build=new StringBuilder();
        for(int i=0;i<size;++i){
            build.append(array[i]);
            build.append(" ");
        }
        return build.toString();
    }
}
