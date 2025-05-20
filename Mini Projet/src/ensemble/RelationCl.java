package ensemble;
import java.util.*;

/**
 * RelationCl class represents a binary relation on a set of elements of type T
 * @param <T> The type of elements in the relation
 * 
 * @author M.BIET
 * @version 1.0
 */
class RelationCl<T> implements Relation<T> {

    /**
     * The set of elements on which the relation is defined
     */
    private final Set<T> ensemble;

    /**
     * The set of couples representing the binary relation
     */
    private final Set<Couple<T, T>> couple;

    /**
     * Constructor for RelationCl
     * @param ensemble The set of elements on which the relation is defined
     * @param couple   The set of couples representing the binary relation
     */
    public RelationCl(Set<T> ensemble, Set<Couple<T, T>> couple) {
        if (ensemble == null) {
            throw new IllegalArgumentException("L'ensemble ne peut pas être null");
        }
        if (couple == null) {
            throw new IllegalArgumentException("L'ensemble de couples ne peut pas être null");
        }
        this.ensemble = new HashSet<>(ensemble);
        this.couple = new HashSet<>(couple);
    }

    /**
     * Checks if the relation is reflexive
     * @return true if the relation is reflexive, false if not
     */
    @Override
    public boolean estReflexive() {
        boolean result = true;
        for (T element : ensemble) {
            if (!this.contient(new CoupleCl<>(element, element))) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Checks if the relation is symmetric
     * @return true if the relation is symmetric, false if not
     */
    @Override
    public boolean estSymetrique() {
        boolean result = true;
        for (Couple<T, T> laPaire : couple) {
            T premierElem = laPaire.getLeft();
            T deuxiemeElem = laPaire.getRight();
            if (!this.contient(new CoupleCl<>(deuxiemeElem, premierElem))) {
                return false;
            }
        }
        return result;
    }

    /**
     * Checks if the relation is antisymmetric
     * @return true if the relation is antisymmetric, false if not
     */
    @Override
    public boolean estAntisymetrique() {
        boolean result = true;
        for (Couple<T, T> laPaire : couple) {
            T premierElem = laPaire.getLeft();
            T deuxiemeElem = laPaire.getRight();
            Couple<T, T> coupleInverse = new CoupleCl<>(deuxiemeElem, premierElem);

            if (this.contient(coupleInverse) && !premierElem.equals(deuxiemeElem)) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Checks if the relation is transitive
     * @return true if the relation is transitive, false if not
     */
    @Override
    public boolean estTransitive() {
        boolean result = true;
        for (Couple<T, T> laPaire1 : couple) {
            for (Couple<T, T> laPaire2 : couple) {
                if (laPaire1.getRight().equals(laPaire2.getLeft())) {
                    Couple<T, T> laPaire3 = new CoupleCl<>(laPaire1.getLeft(), laPaire2.getRight());
                    if (!this.contient(laPaire3)) {
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Checks if the relation contains a given couple
     * @param couple The couple to check for
     * @return true if the relation contains the couple, false if not
     */
    @Override
    public boolean contient(Couple<T, T> couple) {   
        return this.couple.contains(couple);
    }
    
    /**
     * Returns the set of couples in this relation
     * @return A set of couples representing the relation
     */
    @Override
    public Set<Couple<T, T>> elements() {
        return new HashSet<>(this.couple);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("L'objet ne peut pas être null");
        }

        boolean ret = false; // Initialisation

        if (this == o) {
            ret = true;
        } else if (o instanceof RelationCl<?> autre) {
            ret = Objects.equals(ensemble, autre.ensemble) && Objects.equals(couple, autre.couple);
        }

        return ret;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ensemble, couple);
    }

    @Override
    public String toString() {
        return "ensemble =" + ensemble + ", couple=" + couple + '}';
    }
}
