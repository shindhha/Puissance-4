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
            throw new IllegalArgumentException("colonne pleine");
        }
            
        int ligne = 0;
        for (; getTableau()[colonne][ligne] != null ; ligne++);
        getTableau()[colonne][ligne] = new Pion(equipe);
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
    
    
    /** 
     * @return le tableau de la partie avec les pions qui on dÈj‡ ÈtÈ rajoutÈ
     */ 
    public Pion[][] getTableau() {
        return this.tableau;
    }
}