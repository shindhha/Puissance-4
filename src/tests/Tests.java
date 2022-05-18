/* Tests.java												DATE : 08/04/2022
 * IUT - RODEZ SAE 2.02 
 * Pas de droit d'auteur ni de copyright
 */

package tests;

import java.io.IOException;
import java.util.Random;

import Objets.Grille;
import Objets.Pion;
import Main.Interface;

/** TODO commenter la responsabilit? de cette classe
 * @author guillaume.medard
 *
 */
public class Tests {

    /** TODO commenter le r?e de cette m?hode (SRP)
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        
        if (TestsVictoire()) {
        	System.out.println("Tests reussissent");
        } else {
        	System.out.println("Tests echouent");
        }
        test();
        
    }
    private static boolean TestGrilleAleatoire ()throws ArrayIndexOutOfBoundsException {
    	
    	Grille grilleTest = new Grille();
    	int colonne;
    	boolean team = true;
    	for (int nIt = 0; nIt < 20 && !grilleTest.IdentVictory(3,grilleTest.getLastPlaced()); nIt++) {
    		colonne = new Random().nextInt(grilleTest.getTableau().length - 1); 
    		grilleTest.creer(colonne, team);
    		team = !team;
    	}
    	System.out.println(grilleTest.toString());
    	System.out.println("Le dernier pion " + grilleTest.getLastPlaced().toString());
		return grilleTest.IdentVictory(3,grilleTest.getLastPlaced());
    	
    	
    }
    private static boolean TestsVictoire() throws IOException {
    	boolean victoire = true;
    	Grille test3 = new Grille();
        test3.ajouter(test3.creer(0,false));
        test3.ajouter(test3.creer(0,false));
        test3.ajouter(test3.creer(0,false));
        test3.ajouter(test3.creer(0,false));
        System.out.println(test3);
        System.out.println("Le dernier pion " + test3.getLastPlaced());
        victoire &= test3.IdentVictory(3,test3.getLastPlaced());
        /* Test pour la victoire horizontale */
        Grille test4 = new Grille();

        test4.ajouter(test4.creer(0,false));
        test4.ajouter(test4.creer(1,false));
        test4.ajouter(test4.creer(3,false));
        test4.ajouter(test4.creer(2,false));
        System.out.println(test4);
        System.out.println("Le dernier pion " + test4.getLastPlaced());
        victoire &= test4.IdentVictory(3,test4.getLastPlaced());
        /* Test pour une multiple victoire */
        Grille test5 = new Grille();
        test5.ajouter(test5.creer(6, false));
        test5.ajouter(test5.creer(5, false));
        test5.ajouter(test5.creer(5, false));
        test5.ajouter(test5.creer(4, false));
        test5.ajouter(test5.creer(4, false));
        test5.ajouter(test5.creer(4, false));
        test5.ajouter(test5.creer(3, false));
        test5.ajouter(test5.creer(3, false));
        test5.ajouter(test5.creer(3, false));
        test5.ajouter(test5.creer(3, false));
        System.out.println(test5);
        System.out.println("Le dernier pion " + test5.getLastPlaced());
        victoire &= test5.IdentVictory(3,test5.getLastPlaced()); // identifie que 2/3 victoire , ne pas oublier que les victoires 
                                          // sont calculer uniquement par rapport au dernier pion placer.

        
        return  victoire;
    }
    public static void test() throws IOException {
    	Grille grille = new Grille();
    	
    	grille.randomGrille(8);
    	
    	System.out.println(grille);
    	
    }

}