/* Tests.java												DATE : 08/04/2022
 * IUT - RODEZ SAE 2.02 
 * Pas de droit d'auteur ni de copyright
 */

package tests;

import java.util.Random;

import Objets.Grille;
import Objets.Pion;

/** TODO commenter la responsabilité de cette classe
 * @author guillaume.medard
 *
 */
public class Tests {

    /** TODO commenter le rôle de cette méthode (SRP)
     * @param args
     */
    public static void main(String[] args) {
        Grille test = new Grille(1);
        
        test.ajouter(0, false);
        test.ajouter(1, true);
        test.ajouter(1, false);
        test.ajouter(2, true);
        test.ajouter(2, true);
        test.ajouter(2, false);
        Pion k = test.getPionFrom(0, 0);
        Pion kk = k.lookNext(1, 1, test);
        Pion kkk = k.lookNext(2, 2, test);
        Pion kkkk = kk.lookNext(1, 1, test);
        System.out.println(k.toString());
        System.out.println(kk.toString());
        System.out.println(kkk);
        System.out.println(kkkk);
        if (TestsVictoire()) {
        	System.out.println("Quelqu'un a gagne ! ");
        } else {
        	System.out.println("Personne n'a gagne !");
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
    //TODO tests d'ajout , empilement , vÃ©rification pour ne pas dÃ©passer le tableau
    //TODO tests toString meme si normalement sa marche;
    private static boolean TestsVider() {
        return true; //bouchon
    }
    private static boolean TestsVictoire() {
    	/* Test pour la victoire en diagonale en partant d'en bas a gauche jusqu'en haut a droite */
    	Grille grilleTest1 = new Grille(3);
    	/**/
    	grilleTest1.ajouter(0, false);
    	grilleTest1.ajouter(1, true);
    	grilleTest1.ajouter(2, false);
    	grilleTest1.ajouter(3, true);
    	grilleTest1.ajouter(1, false);
    	grilleTest1.ajouter(2, true);
    	grilleTest1.ajouter(3, false);
    	grilleTest1.ajouter(3, true);
    	grilleTest1.ajouter(3, false);
    	grilleTest1.ajouter(2, false);
    	System.out.println(grilleTest1);
    	System.out.println(grilleTest1.getLastPlaced());
    	
    	/* Test pour la victoire en diagonale en partant d'en haut a gauche jusqu'en bas a droite */
    	
    	Grille grilleTest2 = new Grille(3);
    	grilleTest2.ajouter(6, false);
    	grilleTest2.ajouter(5, true);
    	grilleTest2.ajouter(5, false);
    	grilleTest2.ajouter(4, true);
    	grilleTest2.ajouter(4, false);
    	grilleTest2.ajouter(4, false);
    	grilleTest2.ajouter(3, true);
    	grilleTest2.ajouter(3, false);
    	grilleTest2.ajouter(3, true);
    	grilleTest2.ajouter(3, false);
    	System.out.println(grilleTest2);
    	System.out.println(grilleTest2.getLastPlaced());
        return grilleTest1.IdentVictory() && grilleTest2.IdentVictory();
    }


}