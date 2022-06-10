/* Grille.java                                              DATE : 08/04/2022
 * IUT - RODEZ SAE 2.02 
 * Pas de droit d'auteur ni de copyright
 */

package Objets;

import java.util.Arrays;
import java.util.Random;

/**
 * Objet représentant un Tableau/Grille de Puissance 4
 * d'une dimension de 7 colonnes pour 6 lignes.
 * 
 * @author Diego Iglesias , Guillaume Medard , Antoine LALA
 */

public class Grille {
	private static final int LARGEUR = 7;
	private static final int HAUTEUR = 6;
    private Pion[][] tableau = new Pion[LARGEUR][HAUTEUR];
    private boolean tourJoueur = true;
    private Pion lastPlaced;
    /**
     * constructor de grilles
     */
    public Grille() {

    }
     
    /** Vide le tableau des pions existant */
    public void vider() {

        for (int x = 0; x < getTableau().length; x++) {
            Arrays.fill(getTableau()[x], null);
        }
    }
    
    /** 
     * @param colonne colonne a verifier
     * @return true si la colonne est pleine, sinon false
     */
    public  boolean colonnePleine(int colonne) {
        return getTableau()[colonne][5] != null;
    }

    /** 
     * @param colonne , la colonne cible
     * @param ligne , la ligne cible
     * @return true si les coordonnées existe dans la grille, false sinon
     */
    public boolean ValidCoord(int colonne, int ligne) {
    	return colonne >= 0 && colonne < getTableau().length
    		   && ligne >= 0 && ligne < getTableau()[colonne].length;
    }

    /**
     * Ajoute un pion dans la grille aux coordonnées stockées dans le pion
     * @param aAjouter
     */
    public void ajouter(Pion aAjouter) {
    	getTableau()[aAjouter.getCoord()[0]][aAjouter.getCoord()[1]] = aAjouter;
    }


    /** 
     * Ajoute un pion de l'équipe voulut dans la premiere case libre en partant
     * de la colonne choisit en partant du bas
     * @param colonne les coordonnées sur l'axe des abscisses == Les colonnes
     * @param equipe l'équipe en question
     * @throws IllegalArgumentException si la colonne choisit n'existe pas
     */
    public Pion creer(int colonne, boolean equipe) {
    	
        if (colonne < 0 || colonne > getTableau().length ) {
            throw new IllegalArgumentException("colonne inexistante");
        }
        if (colonnePleine(colonne)) {
            throw new ArrayIndexOutOfBoundsException("colonne pleine");
        }
            
        int ligne = getFirstPlaceFree(colonne);

        lastPlaced = new Pion(equipe,colonne,ligne);
        
        return lastPlaced;
    }

    /**
     * Trouve la première place libre dans une colonne de la grille
     * @param colonne la colonne cible
     * @return la ligne de la première place libre
     */
    public int getFirstPlaceFree(int colonne) {
    	int ligne = 0;
        for (; getTableau()[colonne][ligne] != null ; ligne++);
        return ligne;
    }

    /**
     * @param colonne la colonne cible
     * @param ligne la ligne cible
     * @throws ArrayIndexOutOfBoundsException si les coordonnées n'existent pas
     *        une future exception si la case cible est vide 
     * @return le supposer pion aux coordonnées choisies
     */
    public Pion getPionFrom(int colonne,int ligne) {
    	if (getTableau()[colonne][ligne] == null) {
    		return null;
    	}
    	if (!ValidCoord(colonne, ligne)) {
    		throw new ArrayIndexOutOfBoundsException();
    	}
    	return getTableau()[colonne][ligne];
    }

    /**
     * @param horizontalDirection la direction à suivre : si négatif a gauche sinon a droite
     * @param verticalDirection la direction à suivre : si négatif en bas sinon en haut
     * @param toCompare le point de départ de la 'Translation'
     * @return le nombre de pions alignés avec 'toCompare' en l'excluant
     */
    public int getNbAlignPion(int horizontalDirection , int verticalDirection, Pion toCompare) {
    	
		int nbPion = 0;
		Pion toCompareBis = toCompare;
		boolean sameColor = true;
		for(int nbIt = 0; nbIt < 3 && sameColor; nbIt++) {
			try {
				toCompareBis = toCompareBis.lookAside(horizontalDirection, verticalDirection, this);
				sameColor = toCompareBis.equals(toCompare);
				if (sameColor) nbPion++;
			} catch (Exception e) {
			}
		}
		return nbPion;
    }

    /**
     * Détermine si il y a un alignement de pions dans le tableau menant à une victoire
     * @param nbPionAlignToWin le nombre de pions à aligner pour gagner
     * @param FromWho le pion à partir duquel on fait la vérification
     * @return
     */
    public boolean IdentVictory(int nbPionAlignToWin , Pion FromWho) {

        int dBasGauche = getNbAlignPion(1, 1, FromWho) + getNbAlignPion(-1, -1, FromWho);
    	int dHautGauche = getNbAlignPion(-1, 1, FromWho) + getNbAlignPion(1, -1, FromWho);
    	int horizontal = getNbAlignPion(1, 0, FromWho) + getNbAlignPion(-1, 0, FromWho);
    	int vertical = getNbAlignPion(0, -1, FromWho);

    	return dBasGauche >= nbPionAlignToWin || dHautGauche >= nbPionAlignToWin || horizontal >= nbPionAlignToWin || vertical >= nbPionAlignToWin;
    }

    /** @return true si la grille est pleine false sinon */
    public boolean grillePleine() {
    	boolean colonnePlein = true;
    	for (int colonne = 0; colonne < getTableau().length; colonne++) {
    		colonnePlein &= colonnePleine(colonne);
    	}
    	return colonnePlein;
    }


    @Override
    public String toString() {
        Pion[][] table = getTableau();
        StringBuilder message = new StringBuilder();
        for (int y = table[0].length - 1; y >= 0; y--) {
            message.append("\n");
            for (int x = 0 ; x < table.length; x++) {
                if (table[x][y] != null) {
                    char team =  table[x][y].getTeam() ? 'J' : 'B';
                    message.append("|" + team);
                } else {
                    message.append("|_");
                }
                
            }
            message.append("|");
        }
        
        return message.toString();
    }


    public void randomGrille(int nbPionForOneTeam) {
    	vider();
    	boolean team = true;
    	
    	for (int nbPionPlaced = 0; nbPionPlaced < nbPionForOneTeam * 2;) {
    		int place = new Random().nextInt(7);
    		Pion aAjouter = creer(place,team);
    		try {
    			if (!IdentVictory(2,aAjouter)) {
    				ajouter(aAjouter);
    				nbPionPlaced++;
    				team = !team;
    			}
				
			} catch (Exception e) {
			}
    	}
    }
    /** @return le dernier pion placer dans la grille */
    public Pion getLastPlaced() {
    	return this.lastPlaced;
    }
    /** 
     * @return le tableau de la partie avec les pions qui ont déjà été rajoutés
     */ 
    public Pion[][] getTableau() {
        return this.tableau;
    }
    
    /** 
     * methode de reflexion de l'IA
     * @param equipe equipe de l'ordinateur
     */
    public void ordinateur(boolean equipe) {
        
        int colonneAJouer = 0;
        int[] meilleurScore = new int[getTableau().length]; 
        int[] pointAdversaire = pointParCoup(lastPlaced.getTeam(),lastPlaced.getCoord()[0], lastPlaced.getCoord()[1]);
        System.out.println(pointAdversaire[0]);
                
        if (pointAdversaire[0] == 2) {
            colonneAJouer = pointAdversaire[1] + pointAdversaire[2] * -1; // colone a l'opposer d'ou l'adversaire a aligner 3 pions
        }else {
            for (int colonne = 0; colonne < getTableau().length; colonne++) {
                int ligne = getFirstPlaceFree(colonne);
                meilleurScore[colonne] = pointParCoup(equipe,colonne, ligne)[0];
                for (int indice = 0; indice < getTableau().length; indice++) {
                    if (meilleurScore[colonneAJouer] < meilleurScore[indice]) {
                        colonneAJouer = indice;
                    }
                }
            }
        }
        ajouter(creer(colonneAJouer, equipe));
    }
    

    /** 
     * test le nombre de pions qui s'aligne
     * si l'on pose un pion dans une case donnée en argument
     * @param equipe équipe dont on teste les pions
     * @param colonne du point temporaire
     * @param ligne  du point temporaire
     * @return nombre de pions alignés si l'on
     *         pose un pion sur les coordonnées du pion courant
     */
    public int[] pointParCoup (boolean equipe, int colonne, int ligne) {
        int colonnePointMax = 0;
        int directionPointMax = 0;
        int point = 0;
        int plusPoints = 0; 
        int[] info = new int[3];

        for (int horizontal = -1; horizontal < 2; horizontal++ ) {
            for (int vertical = -1; vertical < 2; vertical++) {
                if (horizontal != 0 || vertical !=0) {
                    Pion temporaire = new Pion (equipe,colonne,ligne);
                    point = getNbAlignPion(horizontal, vertical, temporaire);

                    if (point > plusPoints) {
                        plusPoints = point;
                        colonnePointMax = colonne;
                        directionPointMax = horizontal;
                    }
                }
            }
        }
        info[0] = plusPoints;
        info[1] = colonnePointMax;
        info[2] = directionPointMax;
        return info;

    }
}