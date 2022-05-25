 /* Grille.java                                              DATE : 08/04/2022
 * IUT - RODEZ SAE 2.02 
 * Pas de droit d'auteur ni de copyright
 */

package Objets;


import java.util.Random;




/**
 * Objet reprÃ©santent un Tableau/Grille de Puissance 4
 * d'une dimenssions de 7 colonne pour 6 ligne;
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
     * @return true si les coordonnï¿½es existe dans la grille , false sinon 
     */
    public boolean ValidCoord(int colonne,int ligne) {
    	return colonne >= 0 && colonne < getTableau().length
    		   && ligne >= 0 && ligne < getTableau()[colonne].length;
    }
    public void ajouter(Pion aAjouter) {
    	getTableau()[aAjouter.getCoord()[0]][aAjouter.getCoord()[1]] = aAjouter;
    }
    /** 
     * Ajoute un pion de l'ï¿½quipe voulut dans la premiere case libre en partant
     * de la colonne choisit en partant du bas
     * @param colonne les coordonnï¿½e sur l'axe des abscisse == Les colonnes
     * @param equipe l'ï¿½quipe en question
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
    public int getFirstPlaceFree(int colonne) {
    	int ligne = 0;
        for (; getTableau()[colonne][ligne] != null ; ligne++);
        return ligne;
    }

    /**
     * @param colonne la colonne cible
     * @param ligne la ligne cible
     * @throw ArraArrayIndexOutOfBoundsException si les coordonï¿½es n'existe pas
     *        une future exception si la case cible est vide 
     * @return le supposer pion au coordonï¿½es choisie
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
     * @param horizontalDirection la direction a suivre : si nï¿½gatif a gauche sinon a droite
     * @param verticalDirection la direction a suibre : si nï¿½gatif en bas sinon en haut
     * @param toCompare le point de dï¿½part de la 'Translation'
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
    public boolean IdentVictory(int nbPionAlignToWin , Pion FromWho) {
    	
    	Pion last = FromWho;
    	
    	int dBasGauche = getNbAlignPion(1, 1, last) + getNbAlignPion(-1, -1, last);
    	int dHautGauche = getNbAlignPion(-1, 1, last) + getNbAlignPion(1, -1, last);  
    	int horizontal = getNbAlignPion(1, 0, last) + getNbAlignPion(-1, 0, last);    
    	int vertical = getNbAlignPion(0, -1, last);

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
     * @return le tableau de la partie avec les pions qui on dï¿½jï¿½ ï¿½tï¿½ rajoutï¿½
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
     * test le nombre de pion qui s'aligne 
     * si l'ont pose un pion dans une case données en argument
     * @param equipe equipe d'ont ont test les pions
     * @param colonne du point temporaire
     * @param ligne  du point temporaire
     * @return nombre de pion alligner si l'ont 
     *         pose un pion sur les coordonées du pion courrant
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