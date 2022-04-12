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
public class Pion {


	private boolean equipe;

	/**
	 * Methode constructeur des pions
	 * @param equipe true pour pions bleu et false pour jaune
	 */
	public Pion(boolean equipe) {
		this.equipe = equipe;
	}
	
	/** 
	 * 
	 * @return l'equipe true si pions d'equipe bleu et false si jaune
	 */
	public boolean getTeam() {
	    return this.equipe;
	}
}