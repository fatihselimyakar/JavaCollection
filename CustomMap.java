import java.util.Arrays;

public class CustomMap<K,V> {
    private K[] array1;
    private V[] array2;
    private int size=0;

    @SuppressWarnings("unchecked")
    public CustomMap(){
        array1=(K[])new Object[10];
        array2=(V[])new Object[10];
    }

    @SuppressWarnings("unchecked")
    private void reallocate(){
        if(array1.length==size&&array2.length==size){
            array1=Arrays.copyOf(array1,array1.length*2);
            array2=Arrays.copyOf(array2,array2.length*2);
        }
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    private int indexOf(K key){
        for(int i=0;i<size;++i){
            if(key.equals(array1[i]))
                return i;
        }
        return -1;
    }

    public V put(K obj1,V obj2){
        reallocate();
        if(!containsKey(obj1)){
            array1[size]=obj1;
            array2[size]=obj2;
            ++size;
            return null;
        }
        else{
            int index=indexOf(obj1);
            V temp=array2[index];
            array2[index]=obj2;
            return temp;
        }
    }

    @SuppressWarnings("unchecked")
    public V remove(Object key){
        if(!containsKey((K)key)){
            return null;
        }
        else{
            reallocate();
            V temp;
            int index=indexOf((K)key);
            temp=array2[index];
            for(int i=index;i<size;++i){
                array1[i]=array1[i+1];
                array2[i]=array2[i+1];
                --size;
            }
            return temp;
        }
    }

    public boolean containsKey(K obj){
        for(int i=0;i<size;++i){
            if(array1[i].equals(obj))
                return true;
        }
        return false;
    }

    public V get(K obj){
        for(int i=0;i<array1.length;++i){
            if(array1[i].equals(obj))
                return array2[i];
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder= new StringBuilder();
        for(int i=0;i<size;++i){
            builder.append("Key:");
            builder.append(array1[i]);
            builder.append(" Value:");
            builder.append(array2[i]);
            builder.append("\n");
        }
        return builder.toString();
    }
}
