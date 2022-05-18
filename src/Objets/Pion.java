/* Pion.java												DATE : 08/04/2022
 * IUT - RODEZ SAE 2.02 
 * Pas de droit d'auteur ni de copyright
 */


package Objets;

/**
 * Objet reprÃ©sentant un Pions/Jeton dans une grille de puissance 4
 * l'objet ne possÃ¨de qu'une caractÃ©ristique qui un boolean 
 * permettant d'identifier et de diffÃ©rencier le joueur qui la utiliser
 * dans le but de dÃ©terminer des enssembles de jeton de meme couleur
 * pour dÃ©terminer par la suite une victoire.
 * @author Diego Iglesias , Guillaume Medard , Antoine LALA
 */
public class Pion  {

	private String nom;
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
	/**
	 * Permet de récuperer un supposer pion relativement a celui
	 * qui a appeler la fonction un nombre négatif signifie que l'on
	 * reculle sur la grille a l'inver un nombre positif signifie que 
	 * l'on avance 
	 * @param nbVertical le nombre de case a parcourir sur l'axe des
	 *                   abssice par rapport au pion courant
	 * @param nbHorizontal le nombre de case a parcourir sur l'axe des
	 *                     ordonnée par rapport au pion courant
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
	/** @return l'equipe true si pions d'equipe bleu et false si jaune */
	public boolean getTeam() {
	    return this.equipe;
	}
	/** @return les coordonées du point sous la forme (x,y) */
	public int[] getCoord() {
		return this.Coord;
	}
}