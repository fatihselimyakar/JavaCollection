import java.util.*;

public class File_Map implements Map
{
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    Your should provide and explain the complexities for each method in your report.
    * */
   CustomArrayList<String> fnames;
   CustomArrayList<List<Integer>> occurances;

   public File_Map(){
       fnames=new CustomArrayList<>();
       occurances=new CustomArrayList<>();
   }

    @Override
    public int size() {
        return fnames.size();
    }

    @Override
    public boolean isEmpty() { return fnames.isEmpty(); }

    @Override
    public boolean containsKey(Object key) {
        return fnames.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return occurances.contains(value);
    }

    @Override
    public Object get(Object key) {
       if(containsKey(key)){
            return occurances.get(fnames.indexOf(key));
       }
       else
           return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value) {
        if(containsKey(key)){
            Object temp=get(key);
            occurances.set(fnames.indexOf(key),(List<Integer>) value);
            return temp;
        }
        else{
            fnames.add((String)key);
            occurances.add((List<Integer>) value);
            return null;
        }

    }

    @Override
    public Object remove(Object key) {
        if(!containsKey(key)){
            return null;
        }
        else{
            int index=fnames.indexOf(key);
            fnames.remove(index);
            return occurances.remove(index);
        }
    }

    @Override
    public void putAll(Map m) {
        Object[]strings = m.keySet().toArray();
        Object[]lists= m.values().toArray();
        for(int i=0;i<strings.length;++i){
            put(strings[i],lists[i]);
        }
    }

    @Override
    public void clear() {
        occurances.clear();
        fnames.clear();
    }

    @Override
    public Set keySet() {
        Set<String> temp=new CustomSet<>();
        for(int i=0;i<fnames.size();++i){
            temp.add(fnames.get(i));
        }
        return temp;
    }

    @Override
    public Collection values() {
        ArrayList<List<Integer>> temp=new ArrayList<>();
        for(int i=0;i<occurances.size();++i){
            temp.add(occurances.get(i));
        }
        return temp;
    }

    @Override
    public Set<Entry> entrySet() {
        Set<Entry> temp=new CustomSet<>();
        for(int i=0;i<fnames.size();++i){
            temp.add(new CustomEntry<String,List<Integer>>(fnames.get(i),occurances.get(i)));
        }
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for(int i=0;i<fnames.size();++i){
            builder.append(fnames.get(i));
            builder.append("=");
            builder.append(occurances.get(i));
            if(i!=fnames.size()-1)
                builder.append(", ");
        }
        builder.append("}");

        return builder.toString();
    }
}
