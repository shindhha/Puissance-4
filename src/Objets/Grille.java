/* Grille.java                                              DATE : 08/04/2022
 * IUT - RODEZ SAE 2.02 
 * Pas de droit d'auteur ni de copyright
 */

package Objets;



/**
 * Objet repr√©santent un Tableau/Grille de Puissance 4
 * d'une dimenssions de 7 colonne pour 6 ligne;
 * 
 * @author Diego Iglesias , Guillaume Medard , Antoine LALA
 */

public class Grille {

    private Pion[][] tableau = new Pion[7][6];

    private int id;
    private Pion lastPlaced;

    /**
     * constructor de grilles
     * @param id identifiant de la grille
     */
    public Grille(int id) {
        this.id = id;
    }
    /** 
     * Vide le tableau des pion existant
     */
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
    public boolean ValidCoord(int colonne,int ligne) {
    	return colonne >= 0 && colonne < getTableau().length
    		   && ligne >= 0 && ligne < getTableau()[colonne].length;
    }
    
    /** 
     * Ajoute un pion de l'Èquipe voulut dans la premiere case libre en partant
     * de la colonne choisit en partant du bas
     * @param colonne les coordonnÈe sur l'axe des abscisse == Les colonnes
     * @param equipe l'Èquipe en question
     * @throws IllegalArgumentException si la colonne choisit n'existe pas
     */
    public void ajouter(int colonne, boolean equipe) {
        if (colonne < 0 || colonne > getTableau().length ) {
            throw new IllegalArgumentException("colonne inexistante");
        }
        if (colonnePleine(colonne)) {
            throw new ArrayIndexOutOfBoundsException("colonne pleine");
        }
            
        int ligne = 0;
        for (; getTableau()[colonne][ligne] != null ; ligne++);
        lastPlaced = new Pion(equipe,colonne,ligne);
        getTableau()[colonne][ligne] = lastPlaced;
    }
    public Pion getPionFrom(int colonne,int ligne) {
    	if (getTableau()[colonne][ligne] == null) {
    		return null;
    	}
    	if (!ValidCoord(colonne, ligne)) {
    		throw new ArrayIndexOutOfBoundsException();
    	}
    	return getTableau()[colonne][ligne];
    }
    public int getNbPion(int bas , int droite) {
    	
		int nbPion = 0;
		Pion toCompare = getLastPlaced();
		boolean sameColor = true;
		for(int nbIt = 0; nbIt < 3 && sameColor; nbIt++) {
			try {
				toCompare = toCompare.lookNext(bas, droite, this);
				sameColor = toCompare.equals(this.lastPlaced);
				if (sameColor) nbPion++;
			} catch (Exception e) {
			} 
		}
		if (nbPion == 3) return 3;
		toCompare = getLastPlaced();
		for(int nbIt = 0; nbIt < 3 && sameColor; nbIt++) {
			try {
				toCompare = toCompare.lookNext(bas * -1, droite * -1, this);
				sameColor = toCompare.equals(this.lastPlaced);
				if (sameColor) nbPion++;
			} catch (Exception e) {
			} 
		}
		
		return nbPion;
    }
    public boolean IdentVictory() {
    	
    	int dBasGauche = getNbPion(1, 1);
    	System.out.println("dbg : " + dBasGauche);
    	int dHautGauche = getNbPion(-1, 1);
    	System.out.println("dhg : " + dHautGauche);
    	int horizontal = getNbPion(1,0);
    	System.out.println("hor : " + horizontal);
    	int vertical = getNbPion(0, -1);
    	System.out.println("ver : " + vertical);
    	return dBasGauche == 3 || dHautGauche == 3 || horizontal == 3 || vertical == 3;
    }

    
    public boolean grillePlein() {
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
    
    public Pion getLastPlaced() {
    	return this.lastPlaced;
    }
    /** 
     * @return le tableau de la partie avec les pions qui on dÈj‡ ÈtÈ rajoutÈ
     */ 
    public Pion[][] getTableau() {
        return this.tableau;
    }
}