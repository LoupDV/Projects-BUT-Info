package ensemble;

import java.util.Set;

/**
 * Factory class for creating instances of Couple, Ensemble, and Relation
 * @author De Volder Loup
 */
public class Factory {

    /**
     * Creates a new instance of Couple
     * @param <A> the type of the first element.
     * @param <B> the type of the second element.
     * @param leftCl the first element of the couple.   // Nom original gardé
     * @param rightCl the second element of the couple. // Nom original gardé
     * @return a new instance of Couple
     */
    public static <A, B> Couple<A, B> creerCouple(A leftCl, B rightCl) {
        if (leftCl == null || rightCl == null) {
            throw new IllegalArgumentException("Elements of the couple cannot be null");
        }
        return new CoupleCl<>(leftCl, rightCl);
    }

    /**
     * Creates a new instance of Ensemble with a given set of elements
     * @param <T> the type of the elements in the ensemble.
     * @param ensemble set of elements.               // Nom original gardé
     * @return a new instance of Ensemble
     */
    public static <T> Ensemble<T> creerEnsemble(Set<T> ensemble) {
        if (ensemble == null) {
            throw new IllegalArgumentException("Erreur : la collection d'éléments doit être non nulle");
        }
        return new EnsembleCl<>(ensemble);
    }

    /**
     * Creates a new instance of Relation
     * @param <T> the type of the elements in the relation.
     * @param ensemble the base set of the relation.        // Nom original gardé
     * @param couple the set of couples forming the relation. // Nom original gardé
     * @return a new instance of Relation
     */
    public static <T> Relation<T> creerRelation(Set<T> ensemble, Set<Couple<T, T>> couple) {
        if (ensemble == null || couple == null) { // Vérification ajoutée pour les deux params
            throw new IllegalArgumentException("Base set and couples set cannot be null for relation");
        }
        return new RelationCl<>(ensemble, couple);
    }
}