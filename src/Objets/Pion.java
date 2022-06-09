/* Pion.java												DATE : 08/04/2022
 * IUT - RODEZ SAE 2.02 
 * Pas de droit d'auteur ni de copyright
 */


package Objets;

import static Objets.Grille.*;

/**
 * Objet repr�sentant un pion/jeton dans une grille de puissance 4
 * l'objet ne poss�de qu'une caract�ristique qui est un bool�en
 * permettant d'identifier et de diff�rencier le joueur qui l'a utilis�
 * dans le but de d�terminer des ensembles de jeton de m�me couleur
 * pour d�terminer par la suite une victoire.
 * @author Diego Iglesias , Guillaume Medard , Antoine LALA
 */
public class Pion  {

	private String nom;
	private boolean equipe;
	private int[] Coord = new int[2];

	/**
	 * Methode constructeur des pions
	 * @param equipe true pour un pion bleu et false pour un pion jaune
	 */
	public Pion(boolean equipe,int colonne,int ligne) {
		this.equipe = equipe;
		this.Coord[0] = colonne;
		this.Coord[1] = ligne;
	}
	/**
	 * Permet de r�cup�rer un suppos� pion relativement a celui
	 * qui a appel� la fonction. Un nombre n�gatif signifie que l'on
	 * recule sur la grille, � l'inverse un nombre positif signifie que
	 * l'on avance.
	 * @param nbVertical le nombre de cases � parcourir sur l'axe des
	 *                   abscisses par rapport au pion courant.
	 * @param nbHorizontal le nombre de cases � parcourir sur l'axe des
	 *                     ordonn�es par rapport au pion courant
	 * @param choisie La grille sur laquelle on doit r�cup�rer le pion
	 * @return le pion �loigner horizontalement de 'colonne' case 
	 *         et �loigner verticalement de 'ligne' case
	 */
	public Pion lookAside (int nbVertical, int nbHorizontal, Grille choisie) {
    	int targetColumn = nbVertical + getCoord()[0];
    	int targetRow = nbHorizontal + getCoord()[1];
		return choisie.getPionFrom(targetColumn,targetRow);
    }
    /** @return true si les pions sont de la m�me �quipe false sinon */
	public boolean equals (Pion toCompare) {
		return this.getTeam() == toCompare.getTeam();
		
	}
	@Override
	public String toString() {
		return "a �t� placer dans la colonne : " + (getCoord()[0] + 1) 
			   + " et dans la ligne : " + (getCoord()[1] + 1);
	}
	/** @return true si l'�quipe est bleue, false si l'�quipe est jaune */
	public boolean getTeam() {
	    return this.equipe;
	}
	/** @return les coordonn�es du point sous la forme (x, y) */
	public int[] getCoord() {
		return this.Coord;
	}
}