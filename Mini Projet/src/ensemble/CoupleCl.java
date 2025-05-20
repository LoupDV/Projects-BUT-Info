package ensemble;

import java.util.Objects;

/**
 * Class CoupleCl implements the interface Couple and represents a couple
 * @param <A> 
 * @param <B>
 * @author Loup De Volder
 */
class CoupleCl<A, B> implements Couple<A, B> {
    
    private final A left;
    private final B right;


    /**
     * Constructor for CoupleCl
     * @param left the first element of the couple
     * @param right the second element of the couple
     */
    public CoupleCl(A leftCl, B rightCl) {
        if (leftCl == null || rightCl == null) {
            throw new IllegalArgumentException("Elements of the couple cannot be null");
        }

        this.left = leftCl;
        this.right = rightCl;
    }


    /**
     * Get the first element of the couple
     * @return the first element of the couple
     */
    @Override
    public A getLeft() {
        return left;
    }


    /**
     * Get the second element of the couple
     * @return the second element of the couple
     */
    @Override
    public B getRight() {
        return right;
    }


    /**
     * Check if two couples are equal
     * @param o the object to compare with
     * @return true if the two couples are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        return (this == o) || ((o instanceof CoupleCl<?, ?> couple) && this.left.equals(couple.getLeft()) && this.right.equals(couple.getRight()));
    }


    /**
     * Get the hash code of the couple
     * @return the hash code of the couple
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.left, this.right);
    }


    /**
     * Get the string representation of the couple
     * @return the string representation of the couple
     */
    @Override
    public String toString() {
        return "(" + left + ", " + right + ")";
    }
}