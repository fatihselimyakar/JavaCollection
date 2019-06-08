import java.util.ArrayList;

public class StackAL<E> implements StackInt<E> {
    private ArrayList<E> stack;

    public StackAL(){
        stack=new ArrayList<E>();
    }

    @Override
    public E push(E object){
        stack.add(object);
        return object;
    }

    @Override
    public E pop()throws NullPointerException{
        if(stack.size()==0)
            throw new NullPointerException();
        else{
            E temp=stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            return temp;
        }

    }

    @Override
    public E peek(){
        if(stack.size()==0)
            return null;
        else{
            return stack.get(stack.size()-1);
        }
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder temp=new StringBuilder();
        for(int i=0;i<stack.size();++i){
            temp.append(" "+stack.get(i));
        }
        return temp.toString();
    }
}
