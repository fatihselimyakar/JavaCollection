import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class CustomSet<E> implements Set<E> {
    private CustomArrayList<E> arrayList;

    public CustomSet(){
        arrayList=new CustomArrayList<>();
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return arrayList.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return arrayList.iterator();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray() {
        E[]array=(E[])new Object[arrayList.size()];
        for(int i=0;i<arrayList.size();++i){
            array[i]=arrayList.get(i);
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if(!contains(e))
            return arrayList.add(e);
        else
            return false;
    }

    @Override
    public boolean remove(Object o) {
        arrayList.remove(arrayList.indexOf(o));
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(int i=0;i<arrayList.size();++i){
            if(!c.contains(arrayList.get(i)))
                return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends E> c) {
        E[]array=(E[])c.toArray();
        for(int i=0;i<c.size();++i){
            arrayList.add(array[i]);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        CustomArrayList<E> temp=new CustomArrayList<E>();
        int flag=0;
        for(int i=0;i<arrayList.size();++i){
            if(c.contains(arrayList.get(i))){
                temp.add(arrayList.get(i));
                ++flag;
            }
        }
        arrayList=temp;
        return flag!=0;// bu yanlış olabilir.

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int flag=0;
        for(int i=0;i<arrayList.size();++i){
            if(c.contains(arrayList.get(i))){
                arrayList.remove(i);
                ++flag;
            }
        }
        return flag!=0;
    }

    @Override
    public void clear() {
        arrayList.clear();
    }

    @Override
    public String toString() {
        return arrayList.toString();
    }
}
