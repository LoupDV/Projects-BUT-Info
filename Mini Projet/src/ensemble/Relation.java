package ensemble;
import java.util.*;

/**
 * Interface for a relation in set theory
 * @param <T> the type of the elements in the relation
 * @author Matheo Biet
 */
public interface Relation<T> {

    /**
     * Determine if the relation is reflexive in the sense of set theory
     * @return true if reflexive, otherwise false
     */
    public boolean estReflexive();

    /**
     * Determine if the relation is symmetric in the sense of set theory
     * @return true if symmetric, otherwise false
     */
    public boolean estSymetrique();

    /**
     * Determine if the relation is antisymmetric in the sense of set theory
     * @return true if antisymmetric, otherwise false
     */
    public boolean estAntisymetrique();

    /**
     * Determine if the relation is transitive in the sense of set theory
     * @return true if transitive, otherwise false
     */
    public boolean estTransitive();

    /**
     * Determine if the relation is an equivalence relation in the sense of set theory
     * @return true if equivalence, otherwise false
     */
    public boolean contient(Couple<T, T> couple);

    /**
     * Determine if the relation is a partial order in the sense of set theory
     * @return true if partial order, otherwise false
     */
    public Set<Couple<T, T>> elements();
}
