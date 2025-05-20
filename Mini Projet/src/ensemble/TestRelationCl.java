package ensemble;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;

/**
 * TestRelationCl is a class that tests the Relation class.
 * It includes unit tests for all the methods of the RelationCl class.
 * @author De Volder Loup
 */
public class TestRelationCl {

    // Variables pour suivre le nombre de tests réussis et échoués
    private static int testsReussis = 0;
    private static int testsEchoues = 0;
    // Constante pour décider si on affiche les messages de succès pour chaque petit test
    private static final boolean AFFICHER_SUCCES_INDIVIDUELS = true;

    // Données de test partagées par plusieurs tests
    private static Set<Integer> set123; 
    private static Couple<Integer, Integer> c11, c12, c13, c21, c22, c23, c31, c32, c33;

    /**
     * Initialization of test data.
     */
    private static void DonneesDeTest() {
        System.out.println("Initialisation des données de test pour Relation...");
        // Crée l'ensemble {1, 2, 3}
        set123 = new HashSet<>(Arrays.asList(1, 2, 3));

        // Crée des objets Couple. Par exemple, c11 représente le couple (1,1)
        c11 = Factory.creerCouple(1, 1);
        c12 = Factory.creerCouple(1, 2);
        c13 = Factory.creerCouple(1, 3);
        c21 = Factory.creerCouple(2, 1);
        c22 = Factory.creerCouple(2, 2);
        c23 = Factory.creerCouple(2, 3);
        c31 = Factory.creerCouple(3, 1);
        c32 = Factory.creerCouple(3, 2);
        c33 = Factory.creerCouple(3, 3);
        System.out.println("Données de test initialisées.");
    }

    /**
     * Main method to run all the unit tests for the Relation class.
     * This method initializes the test data, runs each group of tests,
     * and displays the final results.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // 1. Prépare les données de test
        DonneesDeTest();

        // Affiche un message de début
        System.out.println("=====================================");
        System.out.println("    DÉMARRAGE DES TESTS UNITAIRES    ");
        System.out.println("           POUR RELATION             ");
        System.out.println("=====================================");
        System.out.println();

        // 2. Exécute chaque groupe de tests
        executerGroupeTests("Création de Relation", TestRelationCl::testerCreationRelation);
        executerGroupeTests("Méthode contient() pour Relation", TestRelationCl::testerContient);
        executerGroupeTests("Méthode elements() pour Relation", TestRelationCl::testerElements);
        executerGroupeTests("Propriété estReflexive()", TestRelationCl::testerEstReflexive);
        executerGroupeTests("Propriété estSymetrique()", TestRelationCl::testerEstSymetrique);
        executerGroupeTests("Propriété estAntisymetrique()", TestRelationCl::testerEstAntisymetrique);
        executerGroupeTests("Propriété estTransitive()", TestRelationCl::testerEstTransitive);
        executerGroupeTests("Méthode equals() pour Relation", TestRelationCl::testerEquals);
        executerGroupeTests("Méthode hashCode() pour Relation", TestRelationCl::testerHashCode);

        // 3. Affiche le bilan final des tests
        System.out.println("\n=====================================");
        System.out.println("           BILAN DES TESTS           ");
        System.out.println("=====================================");
        System.out.println("Tests Réussis : " + testsReussis);
        System.out.println("Tests Échoués : " + testsEchoues);
        System.out.println("Total des Tests : " + (testsReussis + testsEchoues));
        System.out.println("=====================================");

        // Affiche un message en fonction du résultat global
        if (testsEchoues > 0) {
            System.err.println("\nATTENTION: AU MOINS UN TEST A ÉCHOUÉ !");
        } else {
            System.out.println("\nFÉLICITATIONS: TOUS LES TESTS POUR RELATION SONT PASSÉS !");
        }
    }

    /**
     * Execute a group of tests and display the results.
     * @param nomGroupe The name of the test group.
     * @param testsARealiser The method to execute for the tests.
     */
    private static void executerGroupeTests(String nomGroupe, Runnable testsARealiser) {
        System.out.println("\n--- Test du groupe : " + nomGroupe + " ---");
        testsARealiser.run(); // Exécute la méthode de test passée en argument
        pause(100); // Petite pause pour que l'affichage soit plus lisible
    }

    /**
     * Stop the current thread for a specified number of milliseconds.
     * @param millisecondes The number of milliseconds to pause.
     */
    private static void pause(int millisecondes) {
        try {
            Thread.sleep(millisecondes); // Met le thread actuel en sommeil
        } catch (InterruptedException e) { // Si la pause est interrompue
            Thread.currentThread().interrupt(); // Rétablir le statut d'interruption
        }
    }

    /**
     * Called when a sub-test passes. 
     * This method increments the success counter and optionally displays a success message.
     * @param nomSousTest The name or description of the sub-test.
     */
    private static void passerTest(String nomSousTest) {
        testsReussis++;
        if (AFFICHER_SUCCES_INDIVIDUELS) { // Si on a choisi d'afficher les succès
            System.out.println("  [OK] " + nomSousTest);
        }
    }

    /**
     * Called when a sub-test fails.
     * This method increments the failure counter and displays an error message.
     * @param nomSousTest The name or description of the sub-test.
     * @param messageErreur The reason for the failure.
     */
    private static void echouerTest(String nomSousTest, String messageErreur) {
        testsEchoues++;
        System.err.println("  [ÉCHEC] " + nomSousTest + " : " + messageErreur); // Affiche l'échec sur la sortie d'erreur
    }

    /**
     * Called when a sub-test fails with an exception.
     * This method increments the failure counter and displays the exception details.
     * @param nomSousTest The name or description of the sub-test.
     * @param messageErreur The reason for the failure.
     * @param e The exception that was thrown.
     */
    private static void echouerTestAvecException(String nomSousTest, String messageErreur, Throwable e) {
        testsEchoues++;
        System.err.println("  [ÉCHEC] " + nomSousTest + " : " + messageErreur);
        System.err.println("          Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage()); // Affiche les détails de l'exception
    }

    // --- Méthodes de test pour Relation ---
    // Chaque méthode ci-dessous teste un aspect spécifique de la classe Relation.

    /**
     * Test the creation of a RelationCl object.
     */
    public static void testerCreationRelation() {
        String nomTest = "Création de Relation";
        try {
            // Cas 1: Création valide
            Set<Couple<Integer, Integer>> couplesValides = new HashSet<>(Arrays.asList(c11, c12));
            Relation<Integer> r1 = Factory.creerRelation(set123, couplesValides);
            if (r1 == null || r1.elements().size() != 2) { // Vérifie que la relation n'est pas nulle et a le bon nombre d'éléments
                echouerTest(nomTest + " - valide", "Échec création valide.");
            }
            passerTest(nomTest + " - valide");

            // Cas 2: Test avec un ensemble de base null
            try {
                Factory.creerRelation(null, couplesValides); // Tente de créer avec un ensemble de base null
                echouerTest(nomTest + " - ensemble null", "IllegalArgumentException attendue."); // Si aucune exception n'est levée, c'est un échec
            } catch (IllegalArgumentException e) {
                passerTest(nomTest + " - ensemble null (exception OK)"); // L'exception attendue a été levée
            }

            // Cas 3: Test avec un ensemble de couples null
            try {
                Factory.creerRelation(set123, null); // Tente de créer avec des couples null
                echouerTest(nomTest + " - couples null", "IllegalArgumentException attendue.");
            } catch (IllegalArgumentException e) {
                passerTest(nomTest + " - couples null (exception OK)");
            }

        } catch (Throwable e) { // Attrape une exception globale inattendue pour tout le test
            echouerTestAvecException(nomTest, "Exception inattendue globale.", e);
        }
    }

    /**
     * Test the `contient()` method of the RelationCl class.
     */
    public static void testerContient() {
        String nomTest = "Méthode contient() pour Relation";
        try {
            // Prépare une relation de test
            Set<Couple<Integer, Integer>> couples = new HashSet<>(Arrays.asList(c11, c12, c23)); // Contient (1,1), (1,2), (2,3)
            Relation<Integer> r = Factory.creerRelation(set123, couples);

            // Cas 1: Vérifie si un couple présent est bien détecté
            if (!r.contient(c11)) { // c11 est (1,1)
                echouerTest(nomTest + " - c11", "Devrait contenir (1,1)");
            }
            passerTest(nomTest + " - c11");

            // Cas 2: Vérifie avec un couple équivalent (même contenu, mais objet différent)
            if (!r.contient(Factory.creerCouple(1,2))) { // (1,2) est dans la relation via c12
                echouerTest(nomTest + " - c12 équivalent", "Devrait contenir (1,2)");
            }
            passerTest(nomTest + " - c12 équivalent");

            // Cas 3: Vérifie si un couple absent n'est pas détecté
            if (r.contient(c13)) { // c13 (1,3) n'est pas dans la relation
                echouerTest(nomTest + " - c13", "Ne devrait pas contenir (1,3)");
            }
            passerTest(nomTest + " - c13 (absent OK)");

            // Cas 4: Vérifie le comportement avec `null`
            if (r.contient(null)) { // retourne false
                echouerTest(nomTest + " - contient(null)", "devrait retourner false");
            } else {
                passerTest(nomTest + " - contient(null) (retourne false OK)");
            }

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Test the `elements()` method of the RelationCl class.
     * This method should return a set of couples representing the relation.
     */
    public static void testerElements() {
        String nomTest = "Méthode elements() pour Relation";
        try {
            // Prépare une relation de test
            Set<Couple<Integer, Integer>> couplesInitiaux = new HashSet<>(Arrays.asList(c11, c22)); // (1,1), (2,2)
            Relation<Integer> r = Factory.creerRelation(set123, couplesInitiaux);

            // Cas 1: Vérifie si l'ensemble retourné contient les bons éléments
            Set<Couple<Integer, Integer>> elementsRetournes = r.elements();
            if (!elementsRetournes.equals(couplesInitiaux)) { // `equals` sur les Set compare leur contenu
                echouerTest(nomTest + " - contenu", "Set d'éléments incorrect.");
            }
            passerTest(nomTest + " - contenu");

            // Cas 2: Test de "copie défensive" ou d'immutabilité
            // On essaie de modifier l'ensemble retourné pour voir si cela affecte la relation originale.
            try {
                elementsRetournes.add(c33); // Ajoute (3,3) à l'ensemble retourné
                // Si la relation originale est modifiée, c'est un problème (manque de copie défensive).
                if (r.elements().size() != couplesInitiaux.size() || r.contient(c33)) {
                    echouerTest(nomTest + " - copie défensive", "Relation modifiée.");

                }
                passerTest(nomTest + " - copie défensive"); // La relation n'a pas été modifiée (OK)
            } catch (UnsupportedOperationException uoe) {
                // Si une UnsupportedOperationException est levée, cela signifie que l'ensemble retourné
                // est immuable (par exemple, via Collections.unmodifiableSet).
                passerTest(nomTest + " - copie défensive (vue immuable OK)");
            }

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Test the `estReflexive()` method of the RelationCl class.
     * A relation R is reflexive if for every element x in the base set,
     */
    public static void testerEstReflexive() {
        String nomTest = "Propriété estReflexive()";
        try {
            // Cas 1: Relation réflexive
            // Contient (1,1), (2,2), (3,3) pour l'ensemble de base {1,2,3}
            Set<Couple<Integer, Integer>> couplesR = new HashSet<>(Arrays.asList(c11, c22, c33, c12));
            Relation<Integer> rReflexive = Factory.creerRelation(set123, couplesR);
            if (!rReflexive.estReflexive()) {
                echouerTest(nomTest + " - cas réflexive", "Devrait être réflexive.");
            }
            passerTest(nomTest + " - cas réflexive");

            // Cas 2: Relation non réflexive (manque (3,3))
            Set<Couple<Integer, Integer>> couplesNR = new HashSet<>(Arrays.asList(c11, c22)); // Manque (3,3)
            Relation<Integer> rNonReflexive = Factory.creerRelation(set123, couplesNR);
            if (rNonReflexive.estReflexive()) {
                echouerTest(nomTest + " - cas non réflexive", "Ne devrait pas être réflexive.");
            }
            passerTest(nomTest + " - cas non réflexive");

            // Cas 3: Relation sur un ensemble de base vide (devrait être réflexive par vacuité)
            Relation<Integer> rVide = Factory.creerRelation(Collections.emptySet(), Collections.emptySet());
            if (!rVide.estReflexive()) {
                echouerTest(nomTest + " - ensemble vide", "Devrait être réflexive.");
            }
            passerTest(nomTest + " - ensemble vide");

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Test the `estSymetrique()` method of the RelationCl class.
     * A relation R is symmetric if for every (x,y) in R, (y,x) is also in R.
     */
    public static void testerEstSymetrique() {
        String nomTest = "Propriété estSymetrique()";
        try {
            // Cas 1: Relation symétrique
            // Si (1,2) y est, (2,1) y est aussi. Si (2,3) y est, (3,2) y est aussi. (1,1) est son propre symétrique.
            Set<Couple<Integer,Integer>> couplesS = new HashSet<>(Arrays.asList(c11, c12, c21, c23, c32));
            Relation<Integer> rSym = Factory.creerRelation(set123, couplesS);
            if (!rSym.estSymetrique()) {
                echouerTest(nomTest + " - cas symétrique", "Devrait être symétrique.");
            }
            passerTest(nomTest + " - cas symétrique");

            // Cas 2: Relation non symétrique
            // Contient (1,2) mais pas (2,1).
            Set<Couple<Integer,Integer>> couplesNS = new HashSet<>(Arrays.asList(c12, c22));
            Relation<Integer> rNonSym = Factory.creerRelation(set123, couplesNS);
            if (rNonSym.estSymetrique()) {
                echouerTest(nomTest + " - cas non symétrique", "Ne devrait pas être symétrique.");
            }
            passerTest(nomTest + " - cas non symétrique");

            // Cas 3: Relation vide (devrait être symétrique par vacuité)
            // L'ensemble de base est set123, mais la relation elle-même ne contient aucun couple.
            Relation<Integer> rVide = Factory.creerRelation(set123, Collections.emptySet());
            if (!rVide.estSymetrique()) {
                echouerTest(nomTest + " - relation vide", "Devrait être symétrique.");
            }
            passerTest(nomTest + " - relation vide");

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Test the `estAntisymetrique()` method of the RelationCl class.
     * A relation R is antisymmetric if for every (x,y) in R and (y,x) in R,
     * if x ≠ y, then (x,y) and (y,x) cannot both be in R.
     */
    public static void testerEstAntisymetrique() {
        String nomTest = "Propriété estAntisymetrique()";
        try {
            // Cas 1: Relation antisymétrique
            Set<Integer> base12 = new HashSet<>(Arrays.asList(1,2)); // Ensemble de base {1,2}
            // Contient (1,1), (1,2), (2,2). (1,2) est là, mais (2,1) ne l'est pas.
            Set<Couple<Integer,Integer>> couplesAS = new HashSet<>(Arrays.asList(Factory.creerCouple(1,1), Factory.creerCouple(1,2), Factory.creerCouple(2,2)));
            Relation<Integer> rAS = Factory.creerRelation(base12, couplesAS);
            if (!rAS.estAntisymetrique()) {
                echouerTest(nomTest + " - cas antisymétrique", "Devrait être antisymétrique.");
            }
            passerTest(nomTest + " - cas antisymétrique");

            // Cas 2: Relation non antisymétrique
            // Contient (1,2) et (2,1), et 1 ≠ 2.
            Set<Couple<Integer,Integer>> couplesNAS = new HashSet<>(Arrays.asList(c12, c21));
            Relation<Integer> rNonAS = Factory.creerRelation(set123, couplesNAS);
            if (rNonAS.estAntisymetrique()) {
                echouerTest(nomTest + " - cas non antisymétrique", "Ne devrait pas être antisymétrique.");
            }
            passerTest(nomTest + " - cas non antisymétrique");

            // Cas 3: Relation vide (devrait être antisymétrique par vacuité)
            Relation<Integer> rVide = Factory.creerRelation(set123, Collections.emptySet());
            if (!rVide.estAntisymetrique()) {
                echouerTest(nomTest + " - relation vide", "Devrait être antisymétrique.");
            }
            passerTest(nomTest + " - relation vide");

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Test the `estTransitive()` method of the RelationCl class.
     * A relation R is transitive if for every (x,y) in R and (y,z) in R,
     * then (x,z) must also be in R.
     */
    public static void testerEstTransitive() {
        String nomTest = "Propriété estTransitive()";
        try {
            // Cas 1: Relation transitive
            // Exemple: (1,2) et (2,3) sont là, et (1,3) est aussi là (c13).
            Set<Couple<Integer,Integer>> couplesT = new HashSet<>(Arrays.asList(c11,c12,c13, c22,c23, c33));
            Relation<Integer> rT = Factory.creerRelation(set123, couplesT);
            if (!rT.estTransitive()) {
                echouerTest(nomTest + " - cas transitive", "Devrait être transitive.");
            }
            passerTest(nomTest + " - cas transitive");

            // Cas 2: Relation non transitive
            // Contient (1,2) (c12) et (2,3) (c23), mais manque (1,3) (c13).
            Set<Couple<Integer,Integer>> couplesNT = new HashSet<>(Arrays.asList(c12, c23));
            Relation<Integer> rNonT = Factory.creerRelation(set123, couplesNT);
            if (rNonT.estTransitive()) {
                echouerTest(nomTest + " - cas non transitive", "Ne devrait pas être transitive.");
            }
            passerTest(nomTest + " - cas non transitive");

            // Cas 3: Relation vide (devrait être transitive par vacuité)
            Relation<Integer> rVide = Factory.creerRelation(set123, Collections.emptySet());
            if (!rVide.estTransitive()) {
                echouerTest(nomTest + " - relation vide", "Devrait être transitive.");
            }
            passerTest(nomTest + " - relation vide");

            // Cas 4: Autre relation non transitive
            // Contient (3,1) (c31) et (1,2) (c12), mais manque (3,2) (c32).
            Set<Couple<Integer,Integer>> couplesNonTransitif2 = new HashSet<>(Arrays.asList(c31, c12));
            Relation<Integer> rNonTransitif2 = Factory.creerRelation(set123, couplesNonTransitif2);
            if (rNonTransitif2.estTransitive()) {
                echouerTest(nomTest + " - cas non-transitif R={(3,1),(1,2)}", "Ne devrait PAS être transitive.");
            }
            passerTest(nomTest + " - cas non-transitif R={(3,1),(1,2)}");

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Test the `equals()` method of the RelationCl class.
     * This method should check if two Relation objects are equal based on their content.
     */
    public static void testerEquals() {
        String nomTest = "Méthode equals() pour Relation";
        try {
            // Prépare des relations pour les tests
            Set<Couple<Integer,Integer>> couplesCommuns = new HashSet<>(Arrays.asList(c12, c22)); // (1,2), (2,2)
            Relation<Integer> r1 = Factory.creerRelation(set123, couplesCommuns);
            // r1bis a le même contenu que r1, mais est un objet différent
            Relation<Integer> r1bis = Factory.creerRelation(new HashSet<>(set123), new HashSet<>(couplesCommuns));

            // Cas 1: Réflexivité (un objet est égal à lui-même)
            if (!r1.equals(r1)) {
                echouerTest(nomTest + " - réflexivité", "r1.equals(r1) KO");
            }
            passerTest(nomTest + " - réflexivité");

            // Cas 2: Égalité de contenu (deux objets avec le même contenu doivent être égaux)
            if (!r1.equals(r1bis)) {
                echouerTest(nomTest + " - égalité contenu", "r1.equals(r1bis) KO");
            }
            passerTest(nomTest + " - égalité contenu");

            // Cas 3: Non-égalité si les couples sont différents
            Set<Couple<Integer,Integer>> couplesDifferents = new HashSet<>(Arrays.asList(c11, c13)); // (1,1), (1,3)
            Relation<Integer> r2 = Factory.creerRelation(set123, couplesDifferents);
            if (r1.equals(r2)) { // r1 et r2 ne devraient pas être égaux
                echouerTest(nomTest + " - couples différents", "r1.equals(r2) OK à tort");
            }
            passerTest(nomTest + " - couples différents");

            // Cas 4: Non-égalité si l'ensemble de base est différent (si la classe Relation le prend en compte)
            Set<Integer> baseDifferente = new HashSet<>(Arrays.asList(1,2,3,4)); // Ensemble de base {1,2,3,4}
            Relation<Integer> r3 = Factory.creerRelation(baseDifferente, couplesCommuns);
            if (r1.equals(r3)) { // r1 (base {1,2,3}) et r3 (base {1,2,3,4}) ne devraient pas être égaux
                echouerTest(nomTest + " - base différente", "r1.equals(r3) OK à tort (si la base compte)");
            }
            passerTest(nomTest + " - base différente (OK si la base compte)");

            // Cas 5: Test avec `null`
            try {
                if (r1.equals(null)) { // `objet.equals(null)` doit toujours retourner `false`
                    echouerTest(nomTest + " - avec null", "r1.equals(null) a retourné true, attendu false ou IAE.");
                } else {
                    echouerTest(nomTest + " - avec null", "r1.equals(null) a retourné false, attendu IAE.");
                }
            } catch (IllegalArgumentException iae) {
                passerTest(nomTest + " - avec null (IllegalArgumentException OK - si c'est le comportement attendu de RelationCl)");
            }

            // Cas 6: Test avec un objet d'un type différent
            if (r1.equals(new Object())) { // Doit retourner `false`
                echouerTest(nomTest + " - autre type", "r1.equals(Object) OK à tort");
            }
            passerTest(nomTest + " - autre type");

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue globale.", e);
        }
    }

    /**
     * Test the `hashCode()` method of the RelationCl class.
     * This method should return a hash code based on the content of the Relation.
     */
    public static void testerHashCode() {
        String nomTest = "Méthode hashCode() pour Relation";
        try {
            // Prépare des relations
            Set<Couple<Integer,Integer>> couplesCommuns = new HashSet<>(Arrays.asList(c12, c22));
            Relation<Integer> r1 = Factory.creerRelation(set123, couplesCommuns);
            Relation<Integer> r1bis = Factory.creerRelation(new HashSet<>(set123), new HashSet<>(couplesCommuns)); // r1.equals(r1bis) devrait être true

            // Cas 1: Consistance (appeler hashCode() plusieurs fois sur le même objet doit donner le même résultat)
            if (r1.hashCode() != r1.hashCode()) {
                echouerTest(nomTest + " - consistance", "hashCode non consistant");
            }
            passerTest(nomTest + " - consistance");

            // Cas 2: Si deux objets sont égaux selon equals(), leurs hashCodes doivent être égaux.
            if (r1.equals(r1bis) && r1.hashCode() != r1bis.hashCode()) {
                echouerTest(nomTest + " - objets égaux", "HashCodes différents pour relations égales");
            }
            passerTest(nomTest + " - objets égaux");

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue globale.", e);
        }
    }
}