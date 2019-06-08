import java.util.LinkedList;

public class CustomHashTableChain<K,V> {
    private static class Entry<K,V>{
        private K key;
        private V value;

        public Entry(K key,V value){
            this.key=key;
            this.value=value;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Entry)){
                return false;
            }
            return ((Entry)obj).key==key&&((Entry)obj).value==value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Key:"+key+" Value:"+value;
        }

    }

    private LinkedList<Entry<K, V>>[] table;
    private int size=0;
    private static final int CAPACITY=10;
    private static final double LOAD_THRESHOLD=0.1;

    @SuppressWarnings("unchecked")
    public CustomHashTableChain(){
        table=new LinkedList[CAPACITY];
    }

    public V get(K key){
        int index=key.hashCode()%table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null;
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.key.equals(key))
                return nextItem.value;
        }
        return null;
    }

    public V put(K key,V value){
        int index=key.hashCode()%table.length;
        if (index < 0)
            index += table.length;
        //System.out.println("KEY:"+key);
        //System.out.println("INDEX:"+index);
        V willReturn=null;
        if(table[index]==null){
            table[index]=new LinkedList<>();
            table[index].add(new Entry<>(key,value));
            ++size;
            if(size>LOAD_THRESHOLD*table.length){
                rehash();
            }
        }
        else {
            for(Entry<K,V> item:table[index]){
                if(key.equals(item.key)){
                    V temp=item.value;
                    item.value=value;
                    willReturn= temp;
                }
            }
            table[index].add(new Entry<>(key, value));
        }
        return willReturn;
    }

    public int size(){
        return size;
    }

    @SuppressWarnings("unchecked")
    private void rehash(){
        LinkedList<Entry<K,V>>[]array=table;
        table=new LinkedList[table.length*2+1];
        size=0;
        for(int i=0;i<array.length;++i){
            if(array[i]!=null){
                /*int index=array[i].getFirst().key.hashCode()%table.length;
                if(index<0){
                    index+=table.length;
                }
                table[index]=array[i];*/
                for (Entry<K,V> item:array[i]) {
                    put(item.key, item.value);
                }
            }
        }
    }

    public V remove(K key){
        int index=key.hashCode()%table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null;

        V temp=get(key);

        table[index].remove(new Entry<K,V>(key,get(key)));

        --size;

        return temp;

    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<table.length;++i){
            builder.append(table[i]);
            builder.append("\n");
        }

        builder.append("table length:"+table.length);

        return builder.toString();
    }
}
