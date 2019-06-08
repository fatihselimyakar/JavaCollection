
public class CustomStack<E> implements  StackInt<E>{
    private E[]array;
    private int size=0;

    @SuppressWarnings("unchecked")
    CustomStack(){
        array=(E[])new Object[10];
    }

    @SuppressWarnings("unchecked")
    private void reallocate(){
        if(array.length==size){
            E[]temp2=(E[])new Object[2*array.length];
            System.arraycopy(array, 0, temp2, 0, array.length);
            array=temp2;
        }
    }
    @Override
    public E push(E object){
        reallocate();
        array[size]=object;
        ++size;
        return object;
    }

    @Override
    public E pop()throws NullPointerException{
        E temp=array[size-1];
        array[size-1]=null;
        --size;
        return temp;
    }

    @Override
    public E peek(){
        return array[size-1];
    }

    @Override
    public boolean isEmpty(){
        return (size==0);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i=0;i<size;++i){
            str.append(array[i]);
            str.append(" ");
        }
        return str.toString();

    }
}

