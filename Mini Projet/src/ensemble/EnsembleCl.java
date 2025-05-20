package ensemble;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * The EnsembleCl class represents a generic set of elements of type T using a Set to store elements.
 *
 * @author C.BOURRE
 * @version 1.0
 */
public class EnsembleCl<T> implements Ensemble<T>{
	
	/**
	 * Stores elements of the set without duplicates
	 */
	private Set<T> ensemble;
	
	/**
	 * Constructor of ‘EnsembleCl' for creating a set with a given set of elements.
	 * @param ensemble set of elements 
	 */
	public EnsembleCl(Set<T> ensemble) {
		
		//s'assurer que l'objet ensemble n'est pas null
		//Sinon une exception est levée
		if (ensemble == null) {
            throw new IllegalArgumentException("Erreur : la collection d'éléments doit être non nulle");
        }

        this.ensemble = new HashSet<>();

		//Ajoute les éléments de la collection d'éléments dans l'ensemble interne
        for (T element : ensemble) {
            if (element == null) {
                throw new IllegalArgumentException("Erreur : les éléments de l'ensemble ne peuvent pas être nuls");
            }
            this.ensemble.add(element);
        }
			
	}
	
	/**
	 * Used to find out the size of the main set.
	 * @return a number indicating the size of the set 
	 */
	public int cardinal(){
		if (this.ensemble == null) {
			throw new IllegalArgumentException("Erreur : la collection d'éléments doit être non nulle");
		}

		return this.ensemble.size();
	}

	/**
	 * Used to determine whether the element is present in the main set.
	 * @param element element of the set to check
	 * @return true or false to check whether the element is present
	 */
	public boolean contient(T element){
		if (element == null) {
			throw new IllegalArgumentException("Erreur : l'élément ne doit pas être null");
		}

		return this.ensemble.contains(element);
	}
	
	/**
	 * Used to create a union between two sets.
	 * @param autre given set of type T
	 * @return a new set containing all the elements between the main set and the set passed as a parameter.
	 */
	public Ensemble<T> union(Ensemble<T> autre){
		
		if (autre == null) {
			throw new IllegalArgumentException("Erreur : le paramètre doit etre non null");
		}
		
		//Creation d'une nouvelle collection sans doublons "ensembleUnion" copiant la collection "ensemble"
		Set<T> ensembleUnion = new HashSet<>(this.ensemble);
		
		//Ajoute les elements de "autre" dans la collection "ensembleUnion"
		ensembleUnion.addAll(autre.elements()); 
		
		return new EnsembleCl<>(ensembleUnion);
	}
	
	
	/**
	 * Intersects two sets.
	 * @param autre given set of type T
	 * @return a new set containing the common values between the main set and ‘autre’.
	 */
	public Ensemble<T> intersection(Ensemble<T> autre){
		
		if (autre == null) {
			throw new IllegalArgumentException("Erreur : le paramètre doit etre non null");
		}
		
		Set<T> ensembleInter = new HashSet<>(this.ensemble);
		
		ensembleInter.retainAll(autre.elements()); 
		
		return new EnsembleCl<>(ensembleInter);
	}
	
	
	/**
	 * Used to apply the difference between two sets.
	 * @param autre given set of type T
	 * @return a new set containing the elements present in the main set but not in ‘autre’.
	 */
	public Ensemble<T> difference(Ensemble<T> autre){
		
		if (autre == null) {
			throw new IllegalArgumentException("Erreur : le paramètre doit etre non null");
		}
		
		Set<T> ensembleDiff = new HashSet<>(this.ensemble);
		
		//Supprime les elements de "autre" dans la collection "ensembleDiff"
		ensembleDiff.removeAll(autre.elements()); 
		
		//Retourne une nouvelle instance de "EnsembleCl" contenant la difference des valeurs 
		//entre la collection "ensembleDiff" et le parametre "autre"
		return new EnsembleCl<>(ensembleDiff);
	}
	
	
	/**
	 * Used to make a symmetrical difference between two sets.
	 * @param autre given set of type T
	 * @return a new set containing the elements present in only one of the two sets
	 */
	public Ensemble<T> diffSymetrique(Ensemble<T> autre){
		
		if (autre == null) {
			throw new IllegalArgumentException("Erreur : le paramètre doit etre non null");
		}
		
		//Creation d'un nouvel ensemble de "this" et "autre" contenant l'union des deux
		Ensemble<T> ensembleUnion = this.union(autre);
		
		//Creation d'un nouvel ensemble de "this" et "autre" contenant l'intersection des deux
		Ensemble<T> ensembleInter = this.intersection(autre);
		
		//Garde les éléments présents dans l'un ou dans l'autre ensemble, mais pas dans les deux à la fois
		return ensembleUnion.difference(ensembleInter);
		
	}
	
	
	/**
	 * Used to produce the cartesian product of two sets.
	 * @param autre given set of type T
	 * @return a new SetCl containing the Cartesian product between the main set and ‘autre’.
	 */
	public Ensemble<Couple<T, T>> produitCartesien(Ensemble<T> autre){

		if (autre == null) {
			throw new IllegalArgumentException("Erreur : le paramètre doit etre non null");
		}
		
		//Creation d'une nouvelle collection sans doublons d'un couple "resProdCarte"
		Set<Couple<T, T>> resProdCarte = new HashSet<>();
		
		//Creation d'une nouvelle collection avec doublon de "ensembleParam" 
		//copiant le Set d'élément du parametre "autre"
		List<T> ensembleParam = new ArrayList<>(autre.elements());
		List<T> ensemblePrincipal = new ArrayList<>(this.ensemble);


		//Realisation du produit cartesien pour chaque valeur de "ensemblePrincipal"
		for (int i = 0; i < ensemblePrincipal.size(); i++) {
			T elemDePrincipal = ensemblePrincipal.get(i);
			
			for (int j = 0; j < ensembleParam.size(); j++) {
				T elemDeParam = ensembleParam.get(j);
				
				//Ajout des couples qui ont été créé, dans la collection de couple "resProdCarte"
				resProdCarte.add(new CoupleCl<>(elemDePrincipal, elemDeParam));
			}
		}
		
		//Retourne une nouvelle instance de "EnsembleCl" contenant 
		//le produit cartesien de deux ensembles
		return new EnsembleCl<>(resProdCarte);

	}
	
	
	/**
	 * Returns a copy of the set of elements contained in this set.
	 * @return a new HashSet of the main set
	 */	
	public Set<T> elements(){
		return new HashSet<>(this.ensemble);
	}
	
	
	@Override
	/**
	 * Used to test whether two sets are equal.
	 * @param o given set of type Object
	 * @return true or false to check if they are equal 
	 */
	public boolean equals(Object o) {
		return (this == o) || (o instanceof EnsembleCl<?> autre 
		&& this.ensemble.equals(autre.ensemble));
	}
	

	@Override
	/**
	 * Calculates the hashCode based on the elements in the set.
	 * @return the hashCode of the set as a number
	 */
	public int hashCode() {
		return ensemble.hashCode();
	}
	
	
	/**
	 * Returns a string representation of the set.
	 * @return the elements of the set as a string
	 */
	@Override
	public String toString() {
		return "(" + ensemble + ")";
	}
}
