/* Pion.java												DATE : 08/04/2022
 * IUT - RODEZ SAE 2.02 
 * Pas de droit d'auteur ni de copyright
 */


package Objets;

import static Objets.Grille.*;

/**
 * Objet représentant un pion/jeton dans une grille de puissance 4
 * l'objet ne possède qu'une caractéristique qui est un booléen
 * permettant d'identifier et de différencier le joueur qui l'a utilisé
 * dans le but de déterminer des ensembles de jeton de même couleur
 * pour déterminer par la suite une victoire.
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
	 * Permet de récupérer un supposé pion relativement a celui
	 * qui a appelé la fonction. Un nombre négatif signifie que l'on
	 * recule sur la grille, à l'inverse un nombre positif signifie que
	 * l'on avance.
	 * @param nbVertical le nombre de cases à parcourir sur l'axe des
	 *                   abscisses par rapport au pion courant.
	 * @param nbHorizontal le nombre de cases à parcourir sur l'axe des
	 *                     ordonnées par rapport au pion courant
	 * @param choisie La grille sur laquelle on doit récupérer le pion
	 * @return le pion éloigner horizontalement de 'colonne' case 
	 *         et éloigner verticalement de 'ligne' case
	 */
	public Pion lookAside (int nbVertical, int nbHorizontal, Grille choisie) {
    	int targetColumn = nbVertical + getCoord()[0];
    	int targetRow = nbHorizontal + getCoord()[1];
		return choisie.getPionFrom(targetColumn,targetRow);
    }
    /** @return true si les pions sont de la même équipe false sinon */
	public boolean equals (Pion toCompare) {
		return this.getTeam() == toCompare.getTeam();
		
	}
	@Override
	public String toString() {
		return "a été placer dans la colonne : " + (getCoord()[0] + 1) 
			   + " et dans la ligne : " + (getCoord()[1] + 1);
	}
	/** @return true si l'équipe est bleue, false si l'équipe est jaune */
	public boolean getTeam() {
	    return this.equipe;
	}
	/** @return les coordonnées du point sous la forme (x, y) */
	public int[] getCoord() {
		return this.Coord;
	}
}