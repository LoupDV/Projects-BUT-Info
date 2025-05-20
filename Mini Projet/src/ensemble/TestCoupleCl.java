package ensemble;

/**
 * Test class for the Couple class.
 * This class contains unit tests for the methods of the Couple class.
 * @author De Volder Loup
 */
public class TestCoupleCl {

    // Variables pour suivre le nombre de tests réussis et échoués
    private static int testsReussis = 0;
    private static int testsEchoues = 0;
    // Constante pour décider si on affiche les messages de succès pour chaque petit test
    private static final boolean AFFICHER_SUCCES_INDIVIDUELS = true;

    /**
     * Main method to run the unit tests.
     * This method will execute all the test groups and display the results.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Affiche un message de démarrage
        System.out.println("=====================================");
        System.out.println("    DÉMARRAGE DES TESTS UNITAIRES    ");
        System.out.println("             POUR COUPLE             ");
        System.out.println("=====================================");
        System.out.println();

        // Exécute chaque groupe de tests
        // 'TestCoupleCl::testerCreerCouple' est une référence à la méthode 'testerCreerCouple'
        executerGroupeDeTests("Création de Couple", TestCoupleCl::testerCreerCouple);
        executerGroupeDeTests("Accesseur getLeft()", TestCoupleCl::testerGetLeft);
        executerGroupeDeTests("Accesseur getRight()", TestCoupleCl::testerGetRight);
        executerGroupeDeTests("Méthode equals()", TestCoupleCl::testerEquals);
        executerGroupeDeTests("Méthode hashCode()", TestCoupleCl::testerHashCode);
        // executerGroupeDeTests("Méthode toString() pour Couple", TestCoupleCl::testerToStringCouple); // Test pour toString, actuellement commenté

        // Affiche le bilan final des tests
        System.out.println("\n=====================================");
        System.out.println("           BILAN DES TESTS           ");
        System.out.println("=====================================");
        System.out.println("Tests Réussis : " + testsReussis);
        System.out.println("Tests Échoués : " + testsEchoues);
        System.out.println("Total des Tests : " + (testsReussis + testsEchoues));
        System.out.println("=====================================");

        // Affiche un message en fonction du résultat global
        if (testsEchoues > 0) {
            System.err.println("\nATTENTION: AU MOINS UN TEST A ÉCHOUÉ !"); // System.err pour afficher en rouge (souvent)
        } else {
            System.out.println("\nFÉLICITATIONS: TOUS LES TESTS POUR COUPLE SONT PASSÉS !");
        }
    }

    /**
     * Execute a group of tests.
     * @param nomGroupe name of the test group.
     * @param testsARealiser the method to execute for the tests.
     */
    private static void executerGroupeDeTests(String nomGroupe, Runnable testsARealiser) {
        System.out.println("\n--- Test du groupe : " + nomGroupe + " ---");
        testsARealiser.run(); // Exécute la méthode de test passée en argument
        pause(100); // Petite pause pour que l'affichage soit plus lisible
    }

    /**
     * Called when an individual test passes.
     * @param nomSousTest The name or description of the sub-test.
     */
    private static void passerTest(String nomSousTest) {
        testsReussis++; // Incrémente le compteur des tests réussis
        if (AFFICHER_SUCCES_INDIVIDUELS) { // Si on a choisi d'afficher les succès
            System.out.println("  [OK] " + nomSousTest);
        }
    }

    /**
     * Called when an individual test fails.
     * @param nomSousTest The name or description of the sub-test.
     * @param messageErreur The reason for the failure.
     */
    private static void echouerTest(String nomSousTest, String messageErreur) {
        testsEchoues++; // Incrémente le compteur des tests échoués
        System.err.println("  [ÉCHEC] " + nomSousTest + " : " + messageErreur); // Affiche l'échec sur la sortie d'erreur
    }

    /**
     * Called when an individual test fails with an exception.
     * @param nomSousTest The name or description of the sub-test.
     * @param messageErreur The reason for the failure.
     * @param e The exception that was thrown.
     */
    private static void echouerTestAvecException(String nomSousTest, String messageErreur, Throwable e) {
        testsEchoues++;
        System.err.println("  [ÉCHEC] " + nomSousTest + " : " + messageErreur);
        System.err.println("          Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage()); // Affiche les détails de l'exception
    }

    /**
     * Pause the execution of the current thread for a specified number of milliseconds.
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
     * Tests the creation of a Couple.
     * Verifies the correct creation of a couple with valid elements,
     */
    public static void testerCreerCouple() {
        String nomTest = "Création de Couple";
        try {
            // Test 1: Création standard d'un couple
            Couple<Integer, String> c1 = Factory.creerCouple(1, "Alice");
            if (c1 == null) {
                echouerTest(nomTest + " - standard (null check)", "c1 ne devrait pas être null après création.");
                return; // Si c1 est null, les tests suivants vont échouer
            }
            // Vérification de l'élément gauche
            if (!Integer.valueOf(1).equals(c1.getLeft())) { // Utiliser .equals() pour les objets, et Integer.valueOf pour être sûr
                echouerTest(nomTest + " - standard (getLeft)", "Premier élément incorrect. Attendu: 1, Obtenu: " + c1.getLeft());
            }
            // Vérification de l'élément droit
            if (!"Alice".equals(c1.getRight())) {
                echouerTest(nomTest + " - standard (getRight)", "Deuxième élément incorrect. Attendu: Alice, Obtenu: " + c1.getRight());
            }
            // Si toutes les vérifications ci-dessus passent pour la création standard
            if (c1 != null && Integer.valueOf(1).equals(c1.getLeft()) && "Alice".equals(c1.getRight())){
                passerTest(nomTest + " - standard");
            }


            // Test 2: Création avec le premier élément null (devrait lever une IllegalArgumentException)
            try {
                Factory.creerCouple(null, "Test");
                echouerTest(nomTest + " - premier null", "IllegalArgumentException attendue, mais n'a pas été levée."); // Si on arrive ici, l'exception n'a pas été levée
            } catch (IllegalArgumentException e) {
                passerTest(nomTest + " - premier null (exception OK)"); // L'exception attendue a été capturée
            }

            // Test 3: Création avec le deuxième élément null (devrait lever une IllegalArgumentException)
            try {
                Factory.creerCouple(1, null);
                echouerTest(nomTest + " - deuxième null", "IllegalArgumentException attendue, mais n'a pas été levée.");
            } catch (IllegalArgumentException e) {
                passerTest(nomTest + " - deuxième null (exception OK)");
            }

        } catch (Throwable e) { // Attrape toute autre exception inattendue pendant ce groupe de test
            echouerTestAvecException(nomTest + " - global", "Une exception inattendue est survenue.", e);
        }
    }

    /**
     * Tests the method 'getLeft()' of the Couple class.
     * Verifies if it correctly returns the left element of the couple.
     */
    public static void testerGetLeft() {
        String nomTest = "Accesseur getLeft()";
        try {
            Couple<Integer, String> c = Factory.creerCouple(10, "Dix");
            if (!Integer.valueOf(10).equals(c.getLeft())) { // Utiliser .equals pour la comparaison d'objets
                echouerTest(nomTest, "Attendu: 10, Obtenu: " + c.getLeft());
            } else {
                passerTest(nomTest);
            }
        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Une exception inattendue est survenue.", e);
        }
    }

    /**
     * Tests the method 'getRight()' of the Couple class.
     * Verifies if it correctly returns the right element of the couple.
     */
    public static void testerGetRight() {
        String nomTest = "Accesseur getRight()";
        try {
            Couple<Integer, String> c = Factory.creerCouple(10, "Dix");
            if (!"Dix".equals(c.getRight())) { // Utiliser .equals pour la comparaison de chaînes
                echouerTest(nomTest, "Attendu: Dix, Obtenu: " + c.getRight());
            } else {
                passerTest(nomTest);
            }
        } catch (Throwable e) {
            echouerTestAvecException(nomTest, "Une exception inattendue est survenue.", e);
        }
    }

    /**
     * Tests the method 'equals()' of the Couple class.
     * Verifies the equality of two couples based on their elements.
     */
    public static void testerEquals() {
        String nomTest = "Méthode equals()";
        try {
            // Préparation des couples de test
            Couple<Integer, String> c1 = Factory.creerCouple(1, "A");
            Couple<Integer, String> c1Bis = Factory.creerCouple(1, "A"); // Identique à c1
            Couple<Integer, String> c2 = Factory.creerCouple(2, "A");    // Élément gauche différent
            Couple<Integer, String> c3 = Factory.creerCouple(1, "B");    // Élément droit différent
            Object o = new Object(); // Un objet d'un type différent

            // Test 1: Réflexivité (un objet est égal à lui-même)
            if (!c1.equals(c1)) {
                echouerTest(nomTest + " - réflexivité", "c1.equals(c1) devrait être vrai.");
            } else {
                passerTest(nomTest + " - réflexivité");
            }

            // Test 2: Égalité de contenu (deux objets avec le même contenu doivent être égaux)
            if (!c1.equals(c1Bis)) {
                echouerTest(nomTest + " - égalité contenu", "c1.equals(c1Bis) devrait être vrai.");
            } else {
                passerTest(nomTest + " - égalité contenu");
            }

            // Test 3: Symétrie (si a.equals(b) est vrai, alors b.equals(a) doit être vrai)
            if (!c1Bis.equals(c1)) {
                echouerTest(nomTest + " - symétrie", "c1Bis.equals(c1) devrait être vrai.");
            } else {
                passerTest(nomTest + " - symétrie");
            }

            // Test 4: Non-égalité si l'élément gauche est différent
            if (c1.equals(c2)) {
                echouerTest(nomTest + " - premier différent", "c1.equals(c2) devrait être faux.");
            } else {
                passerTest(nomTest + " - premier différent (attendu faux)");
            }

            // Test 5: Non-égalité si l'élément droit est différent
            if (c1.equals(c3)) {
                echouerTest(nomTest + " - deuxième différent", "c1.equals(c3) devrait être faux.");
            } else {
                passerTest(nomTest + " - deuxième différent (attendu faux)");
            }

            // Test 6: 'equals(null)'
            // Le comportement standard est de retourner 'false'.
            try {
                if (c1.equals(null)) { // 'objet.equals(null)' doit toujours retourner 'false'
                    echouerTest(nomTest + " - avec null", "c1.equals(null) a retourné true, attendu false.");
                } else {
                    passerTest(nomTest + " - avec null (retourne false OK)");
                }
            } catch (IllegalArgumentException iae) {
                passerTest(nomTest + " - avec null (IllegalArgumentException OK - si c'est le comportement attendu)");
            }

            // Test 7: 'equals' avec un objet d'un type différent
            if (c1.equals(o)) { // Doit retourner 'false'
                echouerTest(nomTest + " - autre type objet", "c1.equals(Object) devrait être faux.");
            } else {
                passerTest(nomTest + " - autre type objet (attendu faux)");
            }

        } catch (Throwable e) {
            echouerTestAvecException(nomTest + " - global", "Une exception inattendue est survenue.", e);
        }
    }

    /**
     * Tests the method 'hashCode()' of the Couple class.
     * Verifies the consistency of the hash code and its relation to the equals method.
     */
    public static void testerHashCode() {
        String nomTest = "Méthode hashCode()";
        try {
            Couple<Integer, String> c1 = Factory.creerCouple(1, "A");
            Couple<Integer, String> c1Bis = Factory.creerCouple(1, "A"); // Égal à c1

            // Test 1: Consistance (appeler hashCode() plusieurs fois sur le même objet doit donner le même résultat)
            if (c1.hashCode() != c1.hashCode()) {
                echouerTest(nomTest + " - consistance", "c1.hashCode() devrait être consistant (retourner la même valeur).");
            } else {
                passerTest(nomTest + " - consistance");
            }

            // Test 2: Si deux objets sont égaux selon equals(), leurs hashCodes doivent être égaux.
            if (c1.hashCode() != c1Bis.hashCode()) {
                echouerTest(nomTest + " - objets égaux", "Hashcodes de c1 (" + c1.hashCode() + ") et c1Bis (" + c1Bis.hashCode() + ") devraient être égaux car les objets le sont.");
            } else {
                passerTest(nomTest + " - objets égaux");
            }

        } catch (Throwable e) {
            echouerTestAvecException(nomTest + " - global", "Une exception inattendue est survenue.", e);
        }
    }
}