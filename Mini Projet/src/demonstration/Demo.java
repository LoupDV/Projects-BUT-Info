package demonstration;

import ensemble.Factory;
import ensemble.Couple;
import ensemble.Ensemble;
import ensemble.Relation;
import java.util.HashSet;
import java.util.Set;

public class Demo {

    /**
     * put the thread to sleep for a given number of milliseconds.
     * @param millisecondes The number of milliseconds to sleep.
     */
    private static void pause(int millisecondes) {
        try {
            Thread.sleep(millisecondes);
        } catch (InterruptedException e) { // Si la pause est interrompue
            Thread.currentThread().interrupt(); // Rétablit le statut d'interruption du thread
        }
    }
    
    public static void main(String[] args) {

        System.out.println();

        Couple<String, Integer> bonCouple = Factory.creerCouple("cle", 123);
        System.out.println("--- Creation Bon Couple (via Factory) ---");
        System.out.println(bonCouple);

        pause(1500);

        System.out.println("bonCouple.getLeft : " + bonCouple.getLeft());
        pause(1500);

        System.out.println("bonCouple.getRight : " + bonCouple.getRight());
        pause(1500);

        Couple<String, Integer> autreBonCouple = Factory.creerCouple("cle", 123);
        System.out.println("bonCouple.equals(autreBonCouple) : " + bonCouple.equals(autreBonCouple));
        pause(1500);
        System.out.println("bonCouple.hashCode() : " + bonCouple.hashCode());
        pause(1500);
        System.out.println("bonCouple : " + bonCouple);
        pause(1500);


        System.out.println();


        System.out.println("--- Creation Mauvais Couple (via Factory) ---");
        try {
            Factory.creerCouple(null, 456);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        pause(1500);

        try {
            Factory.creerCouple("valeur", null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        pause(1500);


        System.out.println();


        Object mauvaisType = "ceci n'est pas un couple";
        System.out.println("--- Mauvaise Utilisation (equals -> type : string ) ---");
        System.out.println("bonCouple.equals(mauvaisType) : " + bonCouple.equals(mauvaisType));
        pause(1500);

        System.out.println();

        Couple<String, Integer> coupleNull = null;
        System.out.println("--- Mauvaise Utilisation (equals avec null) ---");
        System.out.println("bonCouple.equals(coupleNull) : " + bonCouple.equals(coupleNull));
        pause(1500);


        System.out.println();
        System.out.println();
        System.out.println();


        Set<String> elementsBonEnsemble = new HashSet<>();
        elementsBonEnsemble.add("a");
        elementsBonEnsemble.add("b");
        Ensemble<String> bonEnsemble = Factory.creerEnsemble(elementsBonEnsemble);
        System.out.println("--- Creation Bon Ensemble (via Factory) ---");
        System.out.println("bonEnsemble : " + bonEnsemble); 
        pause(1500);


        System.out.println("bonEnsemble.cardinal() : " + bonEnsemble.cardinal());
        pause(1500);
        System.out.println("bonEnsemble.contient(\"a\") : " + bonEnsemble.contient("a"));
        pause(1500);
        System.out.println("bonEnsemble.contient(\"c\") : " + bonEnsemble.contient("c"));
        pause(1500);

        Set<String> autreElements = new HashSet<>();
        autreElements.add("b");
        autreElements.add("c");
        Ensemble<String> autreEnsemble = Factory.creerEnsemble(autreElements);
        pause(1500);

        Ensemble<String> unionEnsemble = bonEnsemble.union(autreEnsemble);
        System.out.println("bonEnsemble.union(autreEnsemble) : " + unionEnsemble);
        pause(1500);

        Ensemble<String> intersectionEnsemble = bonEnsemble.intersection(autreEnsemble);
        System.out.println("bonEnsemble.intersection(autreEnsemble) : " + intersectionEnsemble);
        pause(1500);

        Ensemble<String> differenceEnsemble = bonEnsemble.difference(autreEnsemble);
        System.out.println("bonEnsemble.difference(autreEnsemble) : " + differenceEnsemble);
        pause(1500);

        Ensemble<String> diffSymEnsemble = bonEnsemble.diffSymetrique(autreEnsemble);
        System.out.println("bonEnsemble.diffSymetrique(autreEnsemble) : " + diffSymEnsemble);
        pause(1500);

        Ensemble<Couple<String, String>> produitCartesienEnsemble = bonEnsemble.produitCartesien(autreEnsemble);
        System.out.println("bonEnsemble.produitCartesien(autreEnsemble) : " + produitCartesienEnsemble);
        pause(1500);

        System.out.println("bonEnsemble.elements() : " + bonEnsemble.elements());
        pause(1500);

        Ensemble<String> encoreUnBonEnsemble = Factory.creerEnsemble(elementsBonEnsemble);
        System.out.println("bonEnsemble.equals(encoreUnBonEnsemble) : " + bonEnsemble.equals(encoreUnBonEnsemble));
        System.out.println("bonEnsemble.hashCode() : " + bonEnsemble.hashCode());
        pause(1500);


        System.out.println();


        System.out.println("--- Creation Mauvais Ensemble (via Factory) ---");
        try {
            Factory.creerEnsemble(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        pause(1500);

        System.out.println();


        System.out.println("--- Mauvaise Utilisation (paramètre null) ---");
        try {
            bonEnsemble.union(null);
        } catch (IllegalArgumentException e) {
            System.out.println("bonEnsemble.union(null) : " + e.getMessage());
        }
        pause(1500);

        try {
            bonEnsemble.intersection(null);
        } catch (IllegalArgumentException e) {
            System.out.println("bonEnsemble.intersection(null) : " + e.getMessage());
        }
        pause(1500);

        try {
            bonEnsemble.difference(null);
        } catch (IllegalArgumentException e) {
            System.out.println("bonEnsemble.difference(null) : " + e.getMessage());
        }
        pause(1500);

        try {
            bonEnsemble.diffSymetrique(null);
        } catch (IllegalArgumentException e) {
            System.out.println("bonEnsemble.diffSymetrique(null) : " + e.getMessage());
        }
        pause(1500);

        try {
            bonEnsemble.produitCartesien(null);
        } catch (IllegalArgumentException e) {
            System.out.println("bonEnsemble.produitCartesien(null) : " + e.getMessage());
        }
        pause(1500);


        System.out.println();
        System.out.println();
        System.out.println();


        Set<String> baseRelation = new HashSet<>();
        baseRelation.add("a");
        baseRelation.add("b");

        Set<Couple<String, String>> couplesRelation = new HashSet<>();
        couplesRelation.add(Factory.creerCouple("a", "a"));
        couplesRelation.add(Factory.creerCouple("a", "b"));
        pause(1500);

        Relation<String> bonneRelation = Factory.creerRelation(baseRelation, couplesRelation);
        System.out.println("--- Creation Bonne Relation (Factory) ---");
        System.out.println("bonneRelation : " + bonneRelation);
        pause(1500);

        System.out.println("bonneRelation.estReflexive() : " + bonneRelation.estReflexive());
        System.out.println("bonneRelation.estSymetrique() : " + bonneRelation.estSymetrique());
        System.out.println("bonneRelation.estAntisymetrique() : " + bonneRelation.estAntisymetrique());
        System.out.println("bonneRelation.estTransitive() : " + bonneRelation.estTransitive());
        System.out.println("bonneRelation.contient(Factory.creerCouple(\"a\", \"a\")) : " + bonneRelation.contient(Factory.creerCouple("a", "a")));
        System.out.println("bonneRelation.contient(Factory.creerCouple(\"b\", \"a\")) : " + bonneRelation.contient(Factory.creerCouple("b", "a")));
        System.out.println("bonneRelation.elements() : " + bonneRelation.elements());
        System.out.println("bonneRelation.equals(bonneRelation) : " + bonneRelation.equals(bonneRelation));
        System.out.println("bonneRelation.hashCode() : " + bonneRelation.hashCode());
        pause(1500);

        System.out.println();

        System.out.println("--- Creation Mauvaise Relation (Factory - ensemble null) ---");
        try {
            Factory.creerRelation(null, couplesRelation);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        pause(1500);

        System.out.println("\n--- Creation Mauvaise Relation (Factory - couples null) ---");
        try {
            Factory.creerRelation(baseRelation, null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        pause(1500);

        System.out.println();
    }
}