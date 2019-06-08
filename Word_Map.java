import java.util.*;

/**
 * Keep the String(key) and File_map(value).
 */
public class Word_Map implements Map, Iterable
{

    private final static int INITCAP=10;  //initial capacity
    private int CURRCAP = INITCAP;   //current capacity
    private final static float LOADFACT = 0.75f;
    private Node[] table;

    private int firstElementIndex;
    private Node lastElement;
    private int size;

    /**
     * No parameter constructor
     */
    public Word_Map() {
        this.table = new Node[INITCAP];
        firstElementIndex=-99;
        lastElement=null;
        size=0;
    }

    /**
     * The fundamental structure of map.
     */
    static class Node {
        private String key;
        private File_Map value;
        private Node next;

        /**
         * Node constructor.
         * @param nextNode pointing Node.
         * @param k key.
         * @param val value.
         */
        public Node(Node nextNode,String k,File_Map val){
            key=k;
            value=val;
            next=nextNode;
        }

        /**
         * Gets the key.
         * @return String(key).
         */
        public String getKey() {
            return key;
        }

        /**
         * Gets the value.
         * @return File_map(value)
         */
        public File_Map getValue() {
            return value;
        }

        /**
         * sets the value and returns old value.
         * @param val new value.
         * @return old value.
         */
        public File_Map setValue(File_Map val) {
            File_Map temp=value;
            this.value = val;
            return temp;
        }

        /**
         * toString.
         * @return String.
         */
        @Override
        public String toString() {
            return "\n"+key+"\n"+value;
        }
    }

    /**
     * Map's iterator.
     */
    private class MapIterator implements Iterator<Node>{
        private int index;
        private Node iterNode;

        /**
         * No parameter constructor.
         */
        MapIterator(){
            index=0;
            iterNode=table[firstElementIndex];
        }

        /**
         * controls the Is there next element.
         * @return true or false.
         */
        @Override
        public boolean hasNext() {
            return iterNode!=null;
        }

        /**
         * Increment the index and returns the value.
         * @return value.
         */
        @Override
        public Node next() {
            Node temp=iterNode;
            if(hasNext()){
                iterNode=iterNode.next;
                ++index;
            }
            return temp;
        }
    }

    /**
     * Finds the available index for the add.
     * @param key will use for the find index.
     * @return available index.
     */
    private int linearProbing(String key){
        int hash=key.hashCode()%CURRCAP;
        if (hash<0){
            hash += CURRCAP;
        }
        while ((table[hash]!=null)&&(!key.equals(table[hash].getKey()))){
            ++hash;
            if(hash>=CURRCAP){
                hash=0;
            }
        }
        return hash;
    }

    /**
     * provides the table size and indexes update if size/CURRCAP > LOADFACT.
     */
    private void rehash(){
        Node[]tableBackup=table;
        CURRCAP*=2;
        table=new Node[CURRCAP];
        Node temp=tableBackup[firstElementIndex];
        int i=0;
        while (temp!=null){
            int probeIndex=linearProbing(temp.getKey());
            if(i==0){
                firstElementIndex=probeIndex;
            }
            table[probeIndex]=temp;
            temp=temp.next;
            ++i;
        }
    }


    /**
     * returns the map's iterator.
     * @return Iterator.
     */
    @Override
    public Iterator iterator() {
        return new MapIterator();
    }

    /**
     * returns the key size.
     * @return integer size.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Controls Is there any element in the map.
     * @return true or false.
     */
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * To check whether the specified key.
     * @param key controlled key.
     * @return if there is return true else return false.
     */
    @Override
    public boolean containsKey(Object key) {
        return table[linearProbing((String)key)]!=null;
    }

    /**
     * To check whether the specified value.
     * @param value controlled key.
     * @return if there is return true else return false.
     */
    @Override
    public boolean containsValue(Object value) {
        Node temp=table[firstElementIndex];
        while (temp!=null){
            if((File_Map)temp.getValue()==(File_Map)value)
                return true;
            temp=temp.next;
        }
        return false;
    }

    /**
     * Bring the stated key's value.
     * @param key File word(String).
     * @return If there is then return the key's value else returns null.
     */
    @Override
    public Object get(Object key) {
        int index = linearProbing((String)key);
        if (table[index]== null)
            return null;
        else
            return (File_Map)table[index].getValue();

    }

    /**
     * Adds the new key,value pair if there is same key in table changes the value and returns old value else adds and returns null.
     * @param key String(Word).
     * @param value File_map holds the file name and indexes.
     * @return old value or null.
     */
    @Override
    public Object put(Object key, Object value) {
        int index=linearProbing((String)key);
        //System.out.println(index);

        if(table[index]!=null){//changes the key's value
            File_Map temp=table[index].getValue();
            table[index].setValue((File_Map)value);
            return temp;
        }
        else{//new node add
            table[index]=new Node(null, (String)key, (File_Map)value);
            if(size==0){
                firstElementIndex=index;
                lastElement=table[index];
            }
            else{
                lastElement.next=table[index];
                lastElement=table[index];
            }
            ++size;

            if(((float)size/(float)CURRCAP)>LOADFACT){
                rehash();
            }

            return null;
        }
    }

    /**
     * UNIMPLEMENTED METHOD.
     */
    @Override
    public Object remove(Object key) {
        return null;
    }

    /**
     * Adds the m's whole elements.
     * @param m source map.
     */
    @Override
    public void putAll(Map m) {
        Object[]strings = m.keySet().toArray();
        Object[]lists= m.values().toArray();
        for(int i=0;i<strings.length;++i){
            put(strings[i],lists[i]);
        }
    }

    /**
     * Removes whole elements in the map.
     */
    @Override
    public void clear() {
        for(int i=0;i<CURRCAP;++i){
            table[i]=null;
        }
        size=0;
        firstElementIndex=-99;
        lastElement=null;
    }

    /**
     * Returns key's set.
     * @return Set of key(String).
     */
    @Override
    public Set keySet() {
        Set<String>keys=new HashSet<>();
        Node temp=table[firstElementIndex];
        while (temp!=null){
            keys.add((String)temp.getKey());
            temp=temp.next;
        }
        return keys;
    }

    /**
     * Returns values'es collection.
     * @return Collection of value(File_Map).
     */
    @Override
    public Collection values() {
        ArrayList<File_Map>values=new ArrayList<>();
        Node temp=table[firstElementIndex];
        while (temp!=null){
            values.add((File_Map) temp.getValue());
            temp=temp.next;
        }
        return values;
    }

    /**
     * UNIMPLEMENTED METHOD.
     * @return null.
     */
    @Override
    public Set<Entry> entrySet() {
        return null;
    }

    /**
     * toString.
     * @return String.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node temp=table[firstElementIndex];
        while (temp!=null){
            builder.append("\nKEY:");
            builder.append(temp.getKey());
            builder.append("\nVALUE:\n");
            builder.append(temp.getValue());
            builder.append("\n");
            temp=temp.next;
        }

        return builder.toString();
    }
}
