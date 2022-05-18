 /* Grille.java                                              DATE : 08/04/2022
 * IUT - RODEZ SAE 2.02 
 * Pas de droit d'auteur ni de copyright
 */

package Objets;

/**
 * Objet représantent un Tableau/Grille de Puissance 4
 * d'une dimenssions de 7 colonne pour 6 ligne;
 * 
 * @author Diego Iglesias , Guillaume Medard , Antoine LALA
 */

public class Grille {

    private Pion[][] tableau = new Pion[7][6];
    private boolean tourJoueur = true;
    private Pion lastPlaced;
    /**
     * constructor de grilles
     * @param id identifiant de la grille
     */
    public Grille() {

    }
     
    /** Vide le tableau des pion existant */
    public void vider() {

        for (int x = 0; x < getTableau().length; x++) {
            for (int y = 0; y < getTableau()[x].length; y++) {
                getTableau()[x][y] = null;
            }
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
     * @return true si les coordonn�es existe dans la grille , false sinon 
     */
    public boolean ValidCoord(int colonne,int ligne) {
    	return colonne >= 0 && colonne < getTableau().length
    		   && ligne >= 0 && ligne < getTableau()[colonne].length;
    }
    
    /** 
     * Ajoute un pion de l'�quipe voulut dans la premiere case libre en partant
     * de la colonne choisit en partant du bas
     * @param colonne les coordonn�e sur l'axe des abscisse == Les colonnes
     * @param equipe l'�quipe en question
     * @throws IllegalArgumentException si la colonne choisit n'existe pas
     */
    public void ajouter(int colonne, boolean equipe) {
        if (colonne < 0 || colonne > getTableau().length ) {
            throw new IllegalArgumentException("colonne inexistante");
        }
        if (colonnePleine(colonne)) {
            throw new ArrayIndexOutOfBoundsException("colonne pleine");
        }
            
        int ligne = getFirstPlaceFree(colonne);
        lastPlaced = new Pion(equipe,colonne,ligne);
        getTableau()[colonne][ligne] = lastPlaced;
        
    }
    public int getFirstPlaceFree(int colonne) {
    	int ligne = 0;
        for (; getTableau()[colonne][ligne] != null ; ligne++);
        return ligne;
    }
    /**
     * @param colonne la colonne cible
     * @param ligne la ligne cible
     * @throw ArraArrayIndexOutOfBoundsException si les coordon�es n'existe pas
     *        une future exception si la case cible est vide 
     * @return le supposer pion au coordon�es choisie
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
     * @param horizontalDirection la direction a suivre : si n�gatif a gauche sinon a droite
     * @param verticalDirection la direction a suibre : si n�gatif en bas sinon en haut
     * @param toCompare le point de d�part de la 'Translation'
     * @return le nombre de pion aligner avec 'toCompare' en l'excluant
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
    public boolean IdentVictory() {
    	
    	Pion last = getLastPlaced();
    	
    	int dBasGauche = getNbAlignPion(1, 1, last) + getNbAlignPion(-1, -1, last);
    	if (dBasGauche == 3) {
            System.out.println("Victoire diagonale Nord Est !");
        }
        
    	int dHautGauche = getNbAlignPion(-1, 1, last) + getNbAlignPion(1, -1, last);
    	if (dHautGauche == 3) {
            System.out.println("Victoire diagonale Sud Est !");
        }
        
    	int horizontal = getNbAlignPion(1, 0, last) + getNbAlignPion(-1, 0, last);
        if (horizontal == 3) {
            System.out.println("Victoire Ouest Est !");
        }
       
    	int vertical = getNbAlignPion(0, -1, last);
    	if (vertical == 3) {
            System.out.println("Victoire Nord Sud !");
        }
    	return dBasGauche >= 3 || dHautGauche >= 3 || horizontal >= 3 || vertical >= 3;
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
    /** @return le dernier pion placer dans la grille */
    public Pion getLastPlaced() {
    	return this.lastPlaced;
    }
    /** 
     * @return le tableau de la partie avec les pions qui on d�j� �t� rajout�
     */ 
    public Pion[][] getTableau() {
        return this.tableau;
    }
}