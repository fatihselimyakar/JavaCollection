/**
 * Keep the two integer
 */
public class CustomPair<T,E>{
    private T x;
    private E y;

    /**
     * Initialize the two keeping integer.
     * @param p1 x coordinate
     * @param p2 y coordinate
     */
    public CustomPair(T p1,E p2){
        x=p1;
        y=p2;
    }

    /**
     * X's getter.
     * @return x coordinate
     */
    public T getX() {
        return x;
    }

    /**
     * Y's getter.
     * @return y coordinate
     */
    public E getY() {
        return y;
    }

}
