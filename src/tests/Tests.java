/* Tests.java												DATE : 08/04/2022
 * IUT - RODEZ SAE 2.02 
 * Pas de droit d'auteur ni de copyright
 */

package tests;

import java.util.Random;

import Objets.Grille;
import Objets.Pion;

/** TODO commenter la responsabilit? de cette classe
 * @author guillaume.medard
 *
 */
public class Tests {

    /** TODO commenter le r?e de cette m?hode (SRP)
     * @param args
     */
    public static void main(String[] args) {
        
        if (testOrdinateur()) {
        	System.out.println("Tests reussissent");
        } else {
        	System.out.println("Tests echouent");
        }
        
        
    }
    private static boolean TestGrilleAleatoire ()throws ArrayIndexOutOfBoundsException {
    	
    	Grille grilleTest = new Grille(1);
    	int colonne;
    	boolean team = true;
    	for (int nIt = 0; nIt < 20 && !grilleTest.IdentVictory(); nIt++) {
    		colonne = new Random().nextInt(grilleTest.getTableau().length - 1); 
    		grilleTest.ajouter(colonne, team);
    		team = !team;
    	}
    	System.out.println(grilleTest.toString());
    	System.out.println("Le dernier pion " + grilleTest.getLastPlaced().toString());
		return grilleTest.IdentVictory();
    	
    	
    }
    private static boolean TestsVictoire() {
        boolean victoire = true;
    	/* Test pour la victoire en diagonale en partant d'en bas a gauche jusqu'en haut a droite */
    	Grille test1 = new Grille(3);
    	/**/
    	test1.ajouter(0, false);
    	test1.ajouter(1, true);
    	test1.ajouter(2, false);
    	test1.ajouter(3, true);
    	test1.ajouter(1, false);
    	test1.ajouter(2, true);
    	test1.ajouter(3, false);
    	test1.ajouter(3, true);
    	test1.ajouter(3, false);
    	test1.ajouter(2, false);
        
    	System.out.println(test1);
    	System.out.println("Le dernier pion " + test1.getLastPlaced());
    	victoire &= test1.IdentVictory();
    	/* Test pour la victoire en diagonale en partant d'en haut a gauche jusqu'en bas a droite */
    	
    	Grille test2 = new Grille(3);
    	test2.ajouter(6, false);
    	test2.ajouter(5, true);
    	test2.ajouter(5, false);
    	test2.ajouter(4, true);
    	test2.ajouter(4, false);
    	test2.ajouter(4, false);
    	test2.ajouter(3, true);
    	test2.ajouter(3, false);
    	test2.ajouter(3, true);
    	test2.ajouter(3, false);
    	System.out.println(test2);
    	System.out.println("Le dernier pion " + test2.getLastPlaced());
        victoire &= test2.IdentVictory();
        /* Test pour la victoire verticale */
        Grille test3 = new Grille(3);

        test3.ajouter(0,false);
        test3.ajouter(0,false);
        test3.ajouter(0,false);
        test3.ajouter(0,false);
        System.out.println(test3);
        System.out.println("Le dernier pion " + test3.getLastPlaced());
        victoire &= test3.IdentVictory();
        /* Test pour la victoire horizontale */
        Grille test4 = new Grille(3);

        test4.ajouter(0,false);
        test4.ajouter(1,false);
        test4.ajouter(3,false);
        test4.ajouter(2,false);
        System.out.println(test4);
        System.out.println("Le dernier pion " + test4.getLastPlaced());
        victoire &= test4.IdentVictory();
        /* Test pour une multiple victoire */
        Grille test5 = new Grille(3);
        test5.ajouter(6, false);
        test5.ajouter(5, false);
        test5.ajouter(5, false);
        test5.ajouter(4, false);
        test5.ajouter(4, false);
        test5.ajouter(4, false);
        test5.ajouter(3, false);
        test5.ajouter(3, false);
        test5.ajouter(3, false);
        test5.ajouter(3, false);
        System.out.println(test5);
        System.out.println("Le dernier pion " + test5.getLastPlaced());
        victoire &= test5.IdentVictory(); // identifie que 2/3 victoire , ne pas oublier que les victoires 
                                          // sont calculer uniquement par rapport au dernier pion placer.
        return  victoire;
    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @return true si test reussi, false sinon
     */
    public static boolean testOrdinateur() {
        
        Grille ordinateur = new Grille(4);
        
        ordinateur.ajouter(3, false);
        System.out.println(ordinateur);
        ordinateur.ordinateur(true);
        System.out.println(ordinateur);
        ordinateur.ajouter(3, false);
        System.out.println(ordinateur);
        ordinateur.ordinateur(true);
        System.out.println(ordinateur);
        ordinateur.ajouter(3, false);
        System.out.println(ordinateur);
        ordinateur.ordinateur(true);
        System.out.println(ordinateur);
        
        
        return true;
    }


}