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

    private boolean termine;

    /**
     * TODO commenter l'Ètat initial atteint
     */
    public Grille() {
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
     * Ajoute un pion de l'Èquipe voulut dans la premiere case libre en partant
     * de la colonne choisit en partant du bas
     * @param x les coordonnÈe sur l'axe des abscisse == Les colonnes
     * @param equipe l'Èquipe en question
     * @throws IllegalArgumentException si la colonne choisit n'existe pas
     */
    public void ajouter(int x, boolean equipe) {
        if (x < 0 || x > getTableau().length ) {
            throw new IllegalArgumentException();
        }
        int y = 0;
        while (getTableau()[x][y] != null 
               && y > getTableau()[x].length) {
            y++;
        }
        getTableau()[x][y] = new Pion(equipe);
    }
    @Override
    public String toString() {
        Pion[][] t = getTableau();
        StringBuilder message = new StringBuilder();
        for (int y = t[0].length - 1; y >= 0; y--) {
            message.append("\n=============================================\n");
            for (int x = 0 ; x < t.length; x++) {
                if (t[x][y] != null) {
                    message.append("|" + t[x][y].getTeam());
                } else {
                    message.append("|noTeam");
                }
                
            }
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