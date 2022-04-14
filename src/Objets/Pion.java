/* Pion.java												DATE : 08/04/2022
 * IUT - RODEZ SAE 2.02 
 * Pas de droit d'auteur ni de copyright
 */


package Objets;

/**
 * Objet représentant un Pions/Jeton dans une grille de puissance 4
 * l'objet ne possède qu'une caractéristique qui un boolean 
 * permettant d'identifier et de différencier le joueur qui la utiliser
 * dans le but de déterminer des enssembles de jeton de meme couleur
 * pour déterminer par la suite une victoire.
 * @author Diego Iglesias , Guillaume Medard , Antoine LALA
 */
public class Pion  {


	private boolean equipe;
	private int[] Coord = new int[2];

	/**
	 * Methode constructeur des pions
	 * @param equipe true pour pions bleu et false pour jaune
	 */
	public Pion(boolean equipe,int colonne,int ligne) {
		this.equipe = equipe;
		this.Coord[0] = colonne;
		this.Coord[1] = ligne;
	}
	
	public Pion lookNext (int colonne , int ligne,Grille laGrille) {
    	int targetColumn = colonne + getCoord()[0];
    	int targetRow = ligne + getCoord()[1];
		return laGrille.getPionFrom(targetColumn,targetRow);
    }
	public boolean equals (Pion toCompare) {
		
		return this.getTeam() == toCompare.getTeam();
		
	}
	@Override
	public String toString() {
		return " a �t� placer dans la colonne : "
			   + (getCoord()[0] + 1) + " et dans la ligne : " + (getCoord()[1] + 1);
	}
	/** 
	 * 
	 * @return l'equipe true si pions d'equipe bleu et false si jaune
	 */
	public boolean getTeam() {
	    return this.equipe;
	}
	public int[] getCoord() {
		return this.Coord;
	}
}