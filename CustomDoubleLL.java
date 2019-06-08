public class CustomDoubleLL<E> {
    private static class Node<E>{
        private Node<E>next;
        private Node<E>prev;
        private E data;
        public Node(Node<E>nextRef,Node<E>prevRef,E newData){
            next=nextRef;
            prev=prevRef;
            data=newData;
        }

        public Node(E newData){
            this(null,null,newData);
        }
    }
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private Node<E> getNode(int index){
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException("IN GET NODE");
        }
        if(index==size-1){
            return tail;
        }
        Node<E>temp=head;
        for(int i=0;i<index;++i){
            temp=temp.next;
        }
        return temp;
    }

    public CustomDoubleLL(){
        head=null;
        tail=null;
        size=0;
    }

    public boolean add(E entry){
        if(size==0){
            tail=head=new Node<>(entry);
        }
        else{
            Node<E>temp=new Node<>(head,null,entry);
            head.prev=temp;
            head=temp;
        }
        ++size;
        return true;
    }

    public void add(int index,E entry){
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException("IN ADD FUNCTION");
        }
        if(size==0){
            add(entry);
        }
        else{
            Node<E>temp=getNode(index-1);
            Node<E>add=new Node<>(temp.next,temp,entry);
            temp.next.prev=add;
            temp.next=add;
            ++size;
            //System.out.println(tail.data);
            /*System.out.println("next:"+temp.next.next.data);
            System.out.println("kendi:"+temp.next.next.prev.data);
            System.out.println("öncesi:"+temp.next.prev.data);
            System.out.println("kendi:"+temp.next.data);*/
        }


    }

    public E remove(int index){
        if(index==size-1){
            Node<E>temp=getNode(index);
            temp.prev.next=temp.next;
            temp.next=null;
            --size;
            return temp.data;
        }
        else {
            Node<E>temp=getNode(index);
            temp.prev.next=temp.next;
            temp.next.prev=temp.prev;
            --size;
            return temp.data;
        }

    }

    public int size(){
        return size;
    }

    public E get(int index){
        return getNode(index).data;
    }

    public E set(int index,E entry){
        Node<E>temp=getNode(index);
        E tempData=temp.data;
        temp.data=entry;
        return tempData;
    }

    int indexOf(E target){
        Node<E>temp=head;
        for(int i=0;i<size;++i,temp=temp.next){
            if(temp.data.equals(target)){//EQUALS KULLANILMAZSA == ILE SET EDILMIS VALUE VARSA PATLIYOR.
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty(){
        return size==0;
    }


    @Override
    public String toString() {
        Node<E>temp=head;
        StringBuilder build=new StringBuilder();
        for(int i=0;i<size;++i,temp=temp.next){
            build.append(temp.data);
            build.append(", ");
        }

        return build.toString();

        /*
            TERSTEN BASMAK ICIN(PREVLERI DENEMEK ICIN)
        Node<E>temp=tail;
        StringBuilder build=new StringBuilder();
        for(int i=0;i<size;++i,temp=temp.prev){
            build.append(temp.data);
            build.append(", ");
        }

        return build.toString();*/
    }
}
