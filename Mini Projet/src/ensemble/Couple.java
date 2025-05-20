package ensemble;

/**
 * Interface Couple represents a pair of elements of types A and B
 * @param <A> the type of the first element in the couple
 * @param <B> the type of the second element in the couple
 * 
 * @author C.BOURRE
 * @version 1.0
 */
public interface Couple<A, B> {
    
    /**
     * Get the first element of the couple
     * @return the first element of the couple
     */
    public A getLeft();
    
    /**
     * Get the second element of the couple
     * @return the second element of the couple
     */
    public B getRight();
}