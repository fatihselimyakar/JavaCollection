import java.util.Map;

public class CustomEntry<K,V> implements Map.Entry {
    private K key;
    private V value;

    public CustomEntry(K k,V v){
        key=k;
        value=v;
    }
    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object setValue(Object val) {
        V temp=value;
        value=(V)val;
        return temp;
    }

    @Override
    public String toString() {
        return "("+key+", "+value+")";
    }
}
