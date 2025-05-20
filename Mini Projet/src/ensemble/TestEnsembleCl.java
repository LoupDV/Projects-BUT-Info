package ensemble;

import java.util.Set;

import ensemble.Couple;
import ensemble.Ensemble;
import ensemble.Factory;

import java.util.HashSet; 
import java.util.Arrays;
import java.util.Collections;

/**
 * TestEnsembleCl is a class that contains unit tests for the Ensemble class.
 * It tests various methods of the Ensemble class to ensure they work as expected.
 *
 * @author De Volder Loup
 */
public class TestEnsembleCl {

    // Variables pour compter les tests réussis et échoués
    private static int testsReussis = 0;
    private static int testsEchoues = 0;
    // Constante pour activer/désactiver l'affichage des succès individuels
    private static final boolean AFFICHER_SUCCES_INDIVIDUELS = true;

    public static void main(String[] args) {
        // Affiche un message de démarrage des tests
        System.out.println("=====================================");
        System.out.println("    DÉMARRAGE DES TESTS UNITAIRES    ");
        System.out.println("            POUR ENSEMBLE            ");
        System.out.println("=====================================");
        System.out.println();

        // Exécute chaque groupe de tests en appelant la méthode correspondante
        // 'TestEnsembleCl::testerCreationEnsemble' est une référence à la méthode 'testerCreationEnsemble'
        executerGroupeDeTests("Création d'Ensemble", TestEnsembleCl::testerCreationEnsemble);
        executerGroupeDeTests("Méthode cardinal()", TestEnsembleCl::testerCardinal);
        executerGroupeDeTests("Méthode contient()", TestEnsembleCl::testerContient);
        executerGroupeDeTests("Méthode union()", TestEnsembleCl::testerUnion);
        executerGroupeDeTests("Méthode intersection()", TestEnsembleCl::testerIntersection);
        executerGroupeDeTests("Méthode difference()", TestEnsembleCl::testerDifference);
        executerGroupeDeTests("Méthode diffSymetrique()", TestEnsembleCl::testerDiffSymetrique);
        executerGroupeDeTests("Méthode produitCartesien()", TestEnsembleCl::testerProduitCartesien);
        executerGroupeDeTests("Méthode elements()", TestEnsembleCl::testerElements);
        executerGroupeDeTests("Méthode equals() pour Ensemble", TestEnsembleCl::testerEqualsEnsemble);
        executerGroupeDeTests("Méthode hashCode() pour Ensemble", TestEnsembleCl::testerHashCodeEnsemble);

        // Affiche le bilan final des tests
        System.out.println("\n=====================================");
        System.out.println("           BILAN DES TESTS           ");
        System.out.println("=====================================");
        System.out.println("Tests Réussis : " + testsReussis);
        System.out.println("Tests Échoués : " + testsEchoues);
        System.out.println("Total des Tests : " + (testsReussis + testsEchoues));
        System.out.println("=====================================");

        // Affiche un message final en fonction du résultat global
        if (testsEchoues > 0) {
            System.err.println("\nATTENTION: AU MOINS UN TEST A ÉCHOUÉ !");
        } else {
            System.out.println("\nFÉLICITATIONS: TOUS LES TESTS POUR ENSEMBLE SONT PASSÉS !");
        }
    }

    /**
     * Execute a group of tests and prints the result.
     * @param nomGroupe The name of the test group.
     * @param testsARealiser The method to execute for the tests.
     */
    private static void executerGroupeDeTests(String nomGroupe, Runnable testsARealiser) {
        System.out.println("\n--- Test du groupe : " + nomGroupe + " ---");
        testsARealiser.run(); // Exécute la méthode de test passée en argument
        pause(100); // Petite pause pour rendre l'affichage plus lisible
    }

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

    /**
     * Saves a test as successful and prints a success message.
     * @param nomSousTest The name or description of the sub-test.
     */
    private static void passerTest(String nomSousTest) {
        testsReussis++;
        if (AFFICHER_SUCCES_INDIVIDUELS) { // Si l'affichage des succès individuels est activé
            System.out.println("  [OK] " + nomSousTest);
        }
    }

    /**
     * Saves a test as failed and prints an error message.
     * @param nomSousTest The name or description of the sub-test.
     * @param messageErreur The reason for the failure.
     */
    private static void echouerTest(String nomSousTest, String messageErreur) {
        testsEchoues++;
        System.err.println("  [ÉCHEC] " + nomSousTest + " : " + messageErreur); // Affiche l'échec sur la sortie d'erreur
    }

    /**
     * Saves a test as failed with an exception and prints the error message.
     * @param nomSousTest The name or description of the sub-test.
     * @param messageErreur The reason for the failure.
     * @param e The exception that caused the failure.
     */
    private static void echouerTestAvecException(String nomSousTest, String messageErreur, Throwable e) {
        testsEchoues++;
        System.err.println("  [ÉCHEC] " + nomSousTest + " : " + messageErreur);
        System.err.println("          Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage()); // Affiche le type et le message de l'exception
    }

    // --- Méthodes de test pour Ensemble ---

    /**
     * Tests the creation of an Ensemble.
     */
    public static void testerCreationEnsemble() {
        String nomTest = "Création d'Ensemble";
        try {
            // Test 1: Création d'un ensemble vide
            Ensemble<Integer> ensVide = Factory.creerEnsemble(new HashSet<Integer>());
            // Vérifie que l'ensemble n'est pas null, que son cardinal est 0, et que sa collection d'éléments est vide
            if (ensVide == null || ensVide.cardinal() != 0 || !ensVide.elements().isEmpty()) {
                echouerTest(nomTest + " - vide", "Échec création ensemble vide");
            } else { // Ajout du else pour que passerTest ne soit appelé qu'en cas de succès de la condition
                passerTest(nomTest + " - vide");
            }

            // Test 2: Création d'un ensemble avec des éléments
            Set<String> setInitial = new HashSet<>(Arrays.asList("a", "b", "c"));
            Ensemble<String> ensPlein = Factory.creerEnsemble(setInitial);
            // Vérifie que le cardinal est correct et que tous les éléments initiaux sont présents
            if (ensPlein.cardinal() == 3 || ensPlein.contient("a") || ensPlein.contient("b") || ensPlein.contient("c")) {
                passerTest(nomTest + " - avec éléments");
            } else {
                echouerTest(nomTest + " - avec éléments", "Échec création ensemble plein");
            }

            // Test 3: Création avec un Set 'null' (devrait lever une IllegalArgumentException)
            try {
                Factory.creerEnsemble((Set<Object>) null); // Tente de créer un ensemble à partir d'un Set null
                echouerTest(nomTest + " - Set null", "IllegalArgumentException attendue non levée");
            } catch (IllegalArgumentException e) {
                passerTest(nomTest + " - Set null (exception OK)"); // L'exception attendue a été capturée
            }

            // Test 4: Création avec un élément 'null' dans le Set initial (devrait lever une IllegalArgumentException)
            try {
                Set<Integer> setAvecNull = new HashSet<>(Arrays.asList(1, null)); // Un Set contenant null
                Factory.creerEnsemble(setAvecNull);
                echouerTest(nomTest + " - élément null dans Set", "IllegalArgumentException attendue non levée");
            } catch (IllegalArgumentException e) {
                passerTest(nomTest + " - élément null dans Set (exception OK)");
            }

        } catch (Throwable e) { // Attrape toute autre exception inattendue pendant ce groupe de test
            echouerTestAvecException(nomTest, "Exception inattendue globale.", e);
        }
    }

    /**
     * Test the method 'cardinal()' of the Ensemble class.
     */
    public static void testerCardinal() {
        String nomTest = "Méthode cardinal()";
        try {
            // Test 1: Cardinal d'un ensemble vide
            Ensemble<Double> ensVide = Factory.creerEnsemble(new HashSet<Double>());
            if (ensVide.cardinal() != 0) {
                echouerTest(nomTest + " - vide", "Cardinal attendu 0, Obtenu " + ensVide.cardinal());
            } else {
                passerTest(nomTest + " - vide");
            }

            // Test 2: Cardinal d'un ensemble non vide
            Set<Integer> setInitial = new HashSet<>(Arrays.asList(10, 20, 30));
            Ensemble<Integer> ensPlein = Factory.creerEnsemble(setInitial);
            if (ensPlein.cardinal() != 3) {
                echouerTest(nomTest + " - non vide", "Cardinal attendu 3, Obtenu " + ensPlein.cardinal());
            } else {
                passerTest(nomTest + " - non vide");
            }
        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Tests the method 'contient()' of the Ensemble class.
     * The 'contient' method checks if an element is present in the ensemble.
     */
    public static void testerContient() {
        String nomTest = "Méthode contient()";
        try {
            Ensemble<String> ens = Factory.creerEnsemble(new HashSet<>(Arrays.asList("pomme", "banane")));

            // Test 1: Élément présent
            if (!ens.contient("pomme")) {
                echouerTest(nomTest + " - élément présent", "'pomme' non trouvé");
            } else {
                passerTest(nomTest + " - élément présent");
            }

            // Test 2: Élément absent
            if (ens.contient("orange")) {
                echouerTest(nomTest + " - élément absent", "'orange' trouvé à tort");
            } else {
                passerTest(nomTest + " - élément absent");
            }

            // Test 3: 'contient(null)'
            try {
                boolean resultatContientNull = ens.contient(null); // EnsembleCl.contient(null) est censé lever une exception
                if (resultatContientNull) {
                    echouerTest(nomTest + " - contient(null)", "contient(null) devrait retourner false si null n'est pas un élément valide/permis");
                } else {
                    echouerTest(nomTest + " - contient(null)", "contient(null) a retourné false alors qu'il devrait lever une exception");
                }
            } catch (IllegalArgumentException e) {
                passerTest(nomTest + " - contient(null) (exception OK - si c'est le comportement voulu)");
            }

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Tests the method 'union()' of the Ensemble class.
     * The union of two sets A and B contains all elements that are in A or in B.
     */
    public static void testerUnion() {
        String nomTest = "Méthode union()";
        try {
            // Préparation des ensembles de test
            Ensemble<Integer> e1 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 2, 3)));
            Ensemble<Integer> e2 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(3, 4, 5)));
            Ensemble<Integer> eVide = Factory.creerEnsemble(new HashSet<Integer>());
            Ensemble<Integer> e1Original = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 2, 3))); // Pour vérifier l'immutabilité

            // Test 1: Union de deux ensembles non vides
            Ensemble<Integer> union12 = e1.union(e2);
            Ensemble<Integer> attendu12 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5)));
            if (!union12.equals(attendu12)) {
                echouerTest(nomTest + " - e1 U e2", "Union incorrecte. Attendu: "+attendu12+", Obtenu: "+union12);
            } else {
                passerTest(nomTest + " - e1 U e2");
            }

            // Test 2: Union d'un ensemble avec un ensemble vide
            Ensemble<Integer> union1Vide = e1.union(eVide);
            if (!union1Vide.equals(e1)) { // A ∪ ∅ = A
                echouerTest(nomTest + " - e1 U eVide", "Union avec vide incorrecte.");
            } else {
                passerTest(nomTest + " - e1 U eVide");
            }

            // Test 3: Vérification de l'immutabilité (l'ensemble original ne doit pas être modifié)
            if (!e1.equals(e1Original)) {
                echouerTest(nomTest + " - immutabilité e1", "e1 modifié par union.");
            } else {
                passerTest(nomTest + " - immutabilité e1");
            }

            // Test 4: Union avec 'null' (devrait lever une IllegalArgumentException)
            try {
                e1.union(null);
                echouerTest(nomTest + " - avec null", "IllegalArgumentException attendue non levée.");
            } catch (IllegalArgumentException e) {
                passerTest(nomTest + " - avec null (exception OK)");
            }

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Tests the method 'intersection()' of the Ensemble class.
     * The intersection of two sets A and B contains all elements that are in both A and B.
     */
    public static void testerIntersection() {
        String nomTest = "Méthode intersection()";
        try {
            // Préparation des ensembles
            Ensemble<Integer> e1 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 2, 3)));
            Ensemble<Integer> e2 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(3, 4, 5)));
            Ensemble<Integer> eVide = Factory.creerEnsemble(new HashSet<Integer>());
            Ensemble<Integer> e1Original = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 2, 3))); // Pour l'immutabilité

            // Test 1: Intersection de deux ensembles non vides
            Ensemble<Integer> inter12 = e1.intersection(e2);
            Ensemble<Integer> attendu12 = Factory.creerEnsemble(new HashSet<>(Collections.singletonList(3))); // {3}
            if (!inter12.equals(attendu12)) {
                echouerTest(nomTest + " - e1 I e2", "Intersection incorrecte. Attendu: " + attendu12 + ", Obtenu: " + inter12);
            } else {
                passerTest(nomTest + " - e1 I e2");
            }

            // Test 2: Intersection d'un ensemble avec un ensemble vide
            Ensemble<Integer> inter1Vide = e1.intersection(eVide);
            if (!inter1Vide.equals(eVide) || inter1Vide.cardinal() != 0) { // A ∩ ∅ = ∅
                echouerTest(nomTest + " - e1 I eVide", "Intersection avec vide incorrecte.");
            } else {
                passerTest(nomTest + " - e1 I eVide");
            }

            // Test 3: Vérification de l'immutabilité
            if (!e1.equals(e1Original)) {
                echouerTest(nomTest + " - immutabilité e1", "e1 modifié par intersection.");
            } else {
                passerTest(nomTest + " - immutabilité e1");
            }

            // Test 4: Intersection avec 'null'
            try {
                e1.intersection(null);
                echouerTest(nomTest + " - avec null", "IllegalArgumentException attendue non levée.");
            } catch (IllegalArgumentException e) {
                passerTest(nomTest + " - avec null (exception OK)");
            }

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Tests the method 'difference()' of the Ensemble class.
     * The difference of two sets A and B contains all elements that are in A but not in B.
     */
    public static void testerDifference() {
        String nomTest = "Méthode difference()";
        try {
            Ensemble<Integer> e1 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 2, 3)));
            Ensemble<Integer> e2 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(3, 4, 5)));
            Ensemble<Integer> attendu12 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 2))); // {1, 2}
            Ensemble<Integer> diff12 = e1.difference(e2);

            if (!diff12.equals(attendu12)) {
                echouerTest(nomTest + " - e1 \\ e2", "Différence incorrecte. Attendu: " + attendu12 + ", Obtenu: " + diff12);
            } else {
                passerTest(nomTest + " - e1 \\ e2");
            }
        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Tests the method 'diffSymetrique()' of the Ensemble class.
     * The symmetric difference of two sets A and B contains all elements that are in A or in B but not in both.
     */
    public static void testerDiffSymetrique() {
        String nomTest = "Méthode diffSymetrique()";
        try {
            Ensemble<Integer> e1 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 2, 3)));
            Ensemble<Integer> e2 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(3, 4, 5)));
            Ensemble<Integer> attendu = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 2, 4, 5))); // {1, 2, 4, 5}
            Ensemble<Integer> diffS = e1.diffSymetrique(e2);

            // Vérifie que la différence symétrique est correcte 
            if (!diffS.equals(attendu)) {
                echouerTest(nomTest + " - e1 Δ e2", "Différence symétrique incorrecte. Attendu: " + attendu + ", Obtenu: " + diffS);
            } else {
                passerTest(nomTest + " - e1 Δ e2");
            }
        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Tests the method 'produitCartesien()' of the Ensemble class.
     * The Cartesian product of two sets A and B contains all ordered pairs (a, b) where a is in A and b is in B.
     */
    public static void testerProduitCartesien() {
        String nomTest = "Méthode produitCartesien()";
        try {
            Ensemble<Integer> eA = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 2))); // A = {1, 2}
            Ensemble<Integer> eB = Factory.creerEnsemble(new HashSet<>(Arrays.asList(3)));    // B = {3}
            // A x B = {(1,3), (2,3)}
            Set<Couple<Integer,Integer>> setCouplesAttendus = new HashSet<>();
            setCouplesAttendus.add(Factory.creerCouple(1,3));
            setCouplesAttendus.add(Factory.creerCouple(2,3));
            Ensemble<Couple<Integer,Integer>> attendu = Factory.creerEnsemble(setCouplesAttendus);

            Ensemble<Couple<Integer,Integer>> produit = eA.produitCartesien(eB);
            if(!produit.equals(attendu)){
                echouerTest(nomTest + " - eA x eB", "Produit cartésien incorrect. Attendu: "+attendu+" Obtenu: "+produit);
            } else {
                passerTest(nomTest + " - eA x eB");
            }
        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Tests the method 'elements()' of the Ensemble class.
     * The 'elements()' method returns a Set of all elements in the ensemble.
     */
    public static void testerElements() {
        String nomTest = "Méthode elements()";
        Ensemble<String> ensVide = null;
        Set<String> elementsVideRetournes = null;
        Ensemble<Integer> ensPlein = null;
        Set<Integer> elementsPleinRetournes = null;
        Set<Integer> setInitialPlein = null;

        try {
            // Test 1: 'elements()' sur un ensemble vide
            ensVide = Factory.creerEnsemble(new HashSet<String>());
            elementsVideRetournes = ensVide.elements();
            if (elementsVideRetournes == null || !elementsVideRetournes.isEmpty()) {
                echouerTest(nomTest + " - vide", "elements() sur ensemble vide incorrect.");
            } else {
                passerTest(nomTest + " - vide");
            }

            // Test 2: 'elements()' sur un ensemble non vide
            setInitialPlein = new HashSet<>(Arrays.asList(5, 10));
            ensPlein = Factory.creerEnsemble(setInitialPlein);
            elementsPleinRetournes = ensPlein.elements();
            // Vérifie que le Set retourné n'est pas null et qu'il est égal au Set initial
            if (elementsPleinRetournes == null || !elementsPleinRetournes.equals(setInitialPlein)) {
                echouerTest(nomTest + " - non vide", "Contenu de elements() incorrect. Attendu: " + setInitialPlein + ", Obtenu: " + elementsPleinRetournes);
            } else {
                passerTest(nomTest + " - non vide");
            }

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Tests the method 'equals()' of the Ensemble class.
     * The 'equals()' method checks if two ensembles are equal based on their elements.
     * Two ensembles are equal if they contain the same elements, regardless of the order of creation.
     */
    public static void testerEqualsEnsemble() {
        String nomTest = "Méthode equals() pour Ensemble";
        try {
            Ensemble<Integer> e1 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 2)));
            Ensemble<Integer> e1bis = Factory.creerEnsemble(new HashSet<>(Arrays.asList(2, 1))); // Mêmes éléments, ordre différent
            Ensemble<Integer> e2 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 3)));    // Contenu différent

            // Test 1: Réflexivité (un objet est égal à lui-même)
            if (!e1.equals(e1)) {
                echouerTest(nomTest + " - réflexivité", "e1 non égal à e1");
            } else {
                passerTest(nomTest + " - réflexivité");
            }

            // Test 2: Égalité avec même contenu mais ordre de création différent
            if (!e1.equals(e1bis)) {
                echouerTest(nomTest + " - contenu (ordre diff)", "e1 non égal à e1bis (devraient être égaux)");
            } else {
                passerTest(nomTest + " - contenu (ordre diff)");
            }

            // Test 3: Non-égalité avec contenu différent
            if (e1.equals(e2)) {
                echouerTest(nomTest + " - contenu différent", "e1 égal à e2 à tort (ne devraient pas être égaux)");
            } else {
                passerTest(nomTest + " - contenu différent");
            }

            // Test 4: 'equals(null)'
            try {
                if (e1.equals(null)) { // 'objet.equals(null)' doit toujours retourner 'false'
                    echouerTest(nomTest + " - avec null", "e1.equals(null) a retourné true, attendu false ou IAE.");
                } else {
                    passerTest(nomTest + " - avec null (retourne false OK)");
                }
            } catch (IllegalArgumentException iae) {
                echouerTest(nomTest + " - avec null", "e1.equals(null) a levé IllegalArgumentException, attendu false.");
            }

            // Test 5: 'equals' avec un objet d'un type différent
            if (e1.equals(new Object())) { // Doit retourner 'false'
                echouerTest(nomTest + " - autre type", "e1 égal à un Object générique à tort");
            } else {
                passerTest(nomTest + " - autre type");
            }

        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }

    /**
     * Tests the method 'hashCode()' of the Ensemble class.
     * The 'hashCode()' method returns a hash code value for the ensemble.
     * Two equal ensembles must have the same hash code.
     */
    public static void testerHashCodeEnsemble() {
        String nomTest = "Méthode hashCode() pour Ensemble";
        try {
            Ensemble<Integer> e1 = Factory.creerEnsemble(new HashSet<>(Arrays.asList(1, 2)));
            Ensemble<Integer> e1bis = Factory.creerEnsemble(new HashSet<>(Arrays.asList(2, 1))); // Égal à e1

            // Cas 1: Consistance (appeler hashCode() plusieurs fois sur le même objet doit donner le même résultat)
            if (e1.hashCode() != e1.hashCode()) {
                echouerTest(nomTest + " - consistance", "hashCode non consistant");
            }
            passerTest(nomTest + " - consistance");

            // Cas 2: Si e1 et e1bis sont égaux (selon e1.equals(e1bis)), leurs hashCodes doivent être identiques.
            if (e1.hashCode() != e1bis.hashCode()) {
                echouerTest(nomTest + " - contenu identique", "HashCodes différents pour ensembles égaux. e1.hc=" + e1.hashCode() + ", e1bis.hc=" + e1bis.hashCode());
            } else {
                passerTest(nomTest + " - contenu identique");
            }
        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Exception inattendue.", e);
        }
    }
}