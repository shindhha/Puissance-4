package tests;

import Objets.Grille;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test de la classe Grille
 * @author Diego Iglesias , Guillaume Medard , Antoine LALA
 */
class GrilleTest {

    /**
     * Test du constructeur de la classe Grille
     */
    @Test
    void TestGrille() {
        Grille grille = new Grille();
        assertNotNull(grille);
    }

    /**
     * Test de la méthode vider()
     */
    @Test
    void TestVider() {
        // Déclaration d'une nouvelle grille
        Grille grille1 = new Grille();

        // Population de cette grille
        grille1.ajouter(grille1.creer(0, true));
        grille1.ajouter(grille1.creer(0, false));
        grille1.ajouter(grille1.creer(3, true));
        grille1.ajouter(grille1.creer(3, true));
        grille1.ajouter(grille1.creer(1, false));
        grille1.ajouter(grille1.creer(2, false));
        grille1.ajouter(grille1.creer(6, true));
        grille1.ajouter(grille1.creer(6, true));
        grille1.ajouter(grille1.creer(6, true));
        grille1.ajouter(grille1.creer(6, true));
        grille1.ajouter(grille1.creer(6, true));
        grille1.ajouter(grille1.creer(6, true));

        grille1.vider();
        for (int i = 0; i < grille1.getTableau().length; i++) {
            for (int j = 0; j < grille1.getTableau()[i].length; j++) {
                assertNull(grille1.getTableau()[i][j]);
            }
        }
    }

    /**
     * Test de la méthode colonnePleine()
     */
    @Test
    void colonnePleineTest() {
        Grille grille2 = new Grille();
        assertFalse(grille2.colonnePleine(0));
        assertFalse(grille2.colonnePleine(6));
        for(int i = 0; i < 6; i++) {
            grille2.ajouter(grille2.creer(0, true));
        }
        assertTrue(grille2.colonnePleine(0));
        assertFalse(grille2.colonnePleine(6));
    }

    /**
     * Test de la méthode ValidCoord()
     */
    @Test
    void ValidCoordTest() {
        Grille grille3 = new Grille();
        assertFalse(grille3.ValidCoord(-1, 0));
        assertFalse(grille3.ValidCoord(0, -1));
        assertFalse(grille3.ValidCoord(7, 0));
        assertFalse(grille3.ValidCoord(0, 7));
        assertTrue(grille3.ValidCoord(0, 0));
        assertTrue(grille3.ValidCoord(6, 6));
    }



}