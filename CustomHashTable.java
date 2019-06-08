import javax.swing.*;
import java.sql.Array;
import java.util.*;

public class CustomHashTable {
    private static class Entry{
        private String key;
        private Integer value;

        public Entry(String key,Integer value){
            this.key=key;
            this.value=value;
        }

        public int hashCode(){
            return key.hashCode()+value.hashCode();
        }

        @Override
        public String toString() {
            return "Key:"+key+" Value:"+value;
        }
    }

    private Entry[] table;
    private int size;
    private int delete;
    private final Entry DELETED=new Entry(null, null);
    private final double LOADFACT=0.75;


    public CustomHashTable(){
        table= new Entry[100];
        size=0;
        delete=0;
    }

    public int hashCode(){
        int total=0;
        for(int i=0;i<size;++i){
            total+=table[i].hashCode();
        }
        return total;
    }

    public boolean equals(Object o){
        if(o==this){
            return true;
        }
        if(!(o instanceof CustomHashTable)){
            return false;
        }
        CustomHashTable htb = (CustomHashTable)o;

        return htb.hashCode()==this.hashCode();
    }

    private int linearProbing(String key){
        int index=key.hashCode()%table.length;
        if (index<0){
            index += table.length;
        }
        while(table[index]!=null&&!key.equals(table[index].key)){
            ++index;
            if(index>=table.length){
                index=0;
            }
        }
        return index;
    }

    private int quadraticProbing(String key){
        int index=key.hashCode()%table.length;
        if (index<0){
            index += table.length;
        }
        int k=1;
        while(table[index]!=null&&!key.equals(table[index].key)){
            index = (index + k) % table.length;
            if (index<0){
                index += table.length;
            }
            k += 2;
        }
        return index;
    }

    private void rehash(){
        Entry[]array=table;
        table=new Entry[table.length*2+1];
        delete=0;
        size=0;
        for(int i=0;i<array.length;++i){
            if(array[i]!=null&&array[i]!=DELETED){
                put(array[i].key,array[i].value);
            }

        }
    }

    public boolean isEmpty(){
        return size==0;
    }


    public Integer get(String key){
        int index=quadraticProbing(key);
        if (table[index] != null)
            return table[index].value;
        else
            return null;
    }

    public boolean containsKey(String key) {
        return table[quadraticProbing(key)]!=null&&table[quadraticProbing(key)]!=DELETED;
    }

    public boolean containsValue(Integer value) {
        if(value==null)
            return false;
        for(int i=0;i<table.length;++i){
            if(table[i]!=null&&value.equals(table[i].value))
                return true;
        }
        return false;
    }

    public Integer put(String  key, Integer value) {
        int index=quadraticProbing((String)key);
        if(table[index]!=null){//changes the key's value
            Integer temp=table[index].value;
            table[index].value=value;
            return temp;
        }
        else{//new node add
            table[index]=new Entry(key, value);
            ++size;
            if(((double)(size+delete)/(double)table.length)>LOADFACT){
                rehash();
            }
            return null;
        }
    }

    public Integer remove(String key){
        int index=quadraticProbing(key);
        if(table[index]==null||table[index]==DELETED){
            return null;
        }
        Integer temp=table[index].value;
        table[index]=DELETED;
        --size;
        ++delete;
        return temp;
    }

    public void putAll(Map m) {
        String[]strings = (String[]) m.keySet().toArray();
        Integer[]lists= (Integer[]) m.values().toArray();
        for(int i=0;i<strings.length;++i){
            put(strings[i],lists[i]);
        }
    }

    public void clear() {
        for(int i=0;i<table.length;++i){
            table[i]=null;
        }
        size=0;
        delete=0;
    }

    public Set keySet() {
        Set<String>keys=new HashSet<>();
        for(int i=0;i<table.length;++i){
            if(table[i]!=null&&table[i]!=DELETED){
                keys.add(table[i].key);
            }
        }
        return keys;
    }

    public Collection values() {
        ArrayList<Integer>values=new ArrayList<>();
        for(int i=0;i<table.length;++i){
            if(table[i]!=null&&table[i]!=DELETED){
                values.add(table[i].value);
            }
        }
        return values;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString() {
        StringBuilder deneme=new StringBuilder();
        for(int i=0;i<table.length;++i){
            deneme.append(table[i]);
            deneme.append("\n");
        }
        return deneme.toString();
    }

}
