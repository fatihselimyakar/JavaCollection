import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class CustomMaxHeap<E> {
    private E[] array;
    private int size;


    @SuppressWarnings("unchecked")
    public CustomMaxHeap(){
        array=(E[])new Object[10];
        size=0;
    }

    @SuppressWarnings("unchecked")
    public CustomMaxHeap(Comparator<E> cmp){
        array=(E[])new Object[10];
        size=0;
    }

    private void reallocate(){
        array=Arrays.copyOf(array, array.length*2);
    }

    private int leftChild(int index){
        return 2*index+1;
    }

    private int rightChild(int index){
        return 2*index+2;
    }

    private int parentIndex(int n){
        return (n-1)/2;
    }

    private void swap(int child, int parent) {
        E temp = array[parent];
        array[parent]=array[child];
        array[child]=temp;
    }

    @SuppressWarnings("unchecked")
    public boolean offer(E data){
        if(size==array.length){
            reallocate();
        }
        array[size]=data;
        ++size;
        //System.out.println("size: " + arrayList.size());
        int indexParent=parentIndex(size-1);
        int indexChild=size-1;
        while(indexParent>=0&&((Comparable<E>)array[indexChild]).compareTo(array[indexParent])>0){
            swap(indexChild, indexParent);
            indexChild=indexParent;
            indexParent=parentIndex(indexChild);
        }
        return true;
    }


    private E remove(int index){
        E temp=array[index];
        for(int i=index;i<size-1;++i){
            array[index]=array[index+1];
        }
        --size;
        return temp;
    }

    @SuppressWarnings("unchecked")
    public E poll(){
        if (size==0) {
            return null;
        }
        E result = array[0];
        if (size == 1) {
            remove(0);
            return result;
        }
        array[0]=remove(size-1);
        int parent = 0;
        while (true) {
            int leftChild = leftChild(parent);
            if (leftChild >= size) {
                break;
            }
            int rightChild = rightChild(parent);
            int maxChild = leftChild;
            if (rightChild < size && ((Comparable<E>)array[leftChild]).compareTo( array[rightChild]) < 0){
                maxChild = rightChild;
            }
            if (((Comparable<E>)array[parent]).compareTo(array[maxChild]) < 0){
                swap(parent, maxChild);
                parent = maxChild;
            }
            else{
                break;
            }
        }
        return result;
    }

    public E remove()throws NoSuchElementException {
        if (size==0) {
            throw new NoSuchElementException("There is no element in this stuff.");
        }
        return poll();

    }

    public E element()throws NoSuchElementException{
        if (size==0) {
            throw new NoSuchElementException("There is no element in this stuff.");
        }
        return array[0];
    }

    public boolean isEmpty(){
        return size==0;
    }

    int size(){
        return size;
    }

    /*@SuppressWarnings("unchecked")
    private int compare(E left, E right) {
        if (cmp != null) {
            return cmp.compare(left, right);
        }
        else {
            return ((Comparable<E>) left).compareTo(right);
        }
    }*/

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<size;++i){
            builder.append(array[i]+",");
        }
        return builder.toString();
    }
}

