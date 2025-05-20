package ensemble;

import java.util.Collection;    
import java.util.Set;

/**
 * Interface Ensemble represents a set of elements of type T
 * @param <T> the type of the elements in the set
 * @author De Volder Loup
 */
public interface Ensemble<T> {

    /**
     * Give the cardinal of the set
     * @return the number of elements in the set
     */
    public int cardinal();


    /**
     * verify if the element is in the set
     * @param element the element to be checked
     * @return true if the element is in the set, false otherwise
     */
    public boolean contient(T element);


    /**
     * the union of two sets
     * @param autre the other set to be checked
     * @return a new set which is the union of the two sets
`    */
    public Ensemble <T> union(Ensemble<T> autre);


    /**
     * the intersection of two sets
     * @param autre the other set to be checked
     * @return a new set which is the intersection of the two sets
     */
    public Ensemble<T> intersection(Ensemble<T> autre);


    /**
     * the difference of two sets
     * @param autre the other set to be checked
     * @return a new set which is the difference of the two sets
     */
    Ensemble<T> difference(Ensemble<T> autre);


    /**
     * the symmetric difference of two sets
     * @param autre the other set to be checked
     * @return a new set which is the symmetric difference of the two sets
     */
    Ensemble<T> diffSymetrique(Ensemble<T> autre);


    /**
     * the cartesian product of two sets
     * @param autre the element to be use for the cartesian product
     * @return a new set which is the cartesian product of the two sets
     */
    Ensemble<Couple<T, T>> produitCartesien(Ensemble<T> autre);


    /**
     * the set of elements
     * @return a set of elements
     */
    Set<T> elements(); 
}
