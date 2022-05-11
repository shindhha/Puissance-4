/* Duree.java                                                         17/04/2022
 * Diego Iglesias, pas de droits d'auteur
 */

package objets;

/**
 * Gestion de dureees.
 * Une duree est stockee sous la forme d'un entier positif representant un nombre d'heures.
 * Une duree est creee a  partir d'un nombre d'heures et/ou de jours
 * et/ou de semaines et/ou de mois.
 *
 * Dans le cas ou la duree est creee a  partir d'un ou plusieurs nombres autres
 * qu'un nombre unique d'heures,
 * la duree est convertie en heures et est arrondie pour donner un nombre entier.
 * Les conversions sont realisees selon le schema suivant :
 *  - 1 mois = 4 semaines
 *  - 1 semaine = 7 jours
 *  - 1 jour = 24 heures
 *
 * Exemple : creation d'une duree a  partir d'une duree
 * de 1 jour et 2 heures : duree = 24 + 2 = 26 heures.
 * Exemple 2 : creation d'une duree a  partir d'une duree
 * de 2.5 semaines, 1.3 jours et 1 heure : duree = 420 + 31 + 1 = 452 heures.
 *
 * Aucun nombre fourni pour la creation d'une duree ne peut etre negatif.
 * La duree peut etre affichee en heures, jour, semaine, mois, annee.
 */
public class Duree {
    private int hours;

    /**
     * Constructeur d'une duree a  partir d'un nombre d'heures.
     * @param heures nombre d'heures qu'on souhaite utiliser pour la duree.
     */
    public Duree(int hours) {
        if(hours < 0) {
            throw new IllegalArgumentException("Le nombre d'heures ne peut pas etre negatif.");
        }
        
        this.hours = hours;
    }

    /**
     * Constructeur d'une duree a  partir d'un nombre de jours et d'heures.
     * @param jours nombre de jours qu'on souhaite utiliser pour la duree.
     * @param heures nombre d'heures qu'on souhaite utiliser pour la duree.
     */
    public Duree(double jours, int hours) {
        if(jours < 0 || hours < 0) {
            throw new IllegalArgumentException("Le nombre de jours" +
                    " et d'heures ne peuvent pas etre negatifs.");
        }
        this.hours = (int) Math.round(jours * 24) + hours;
    }

    /**
     * Constructeur d'une duree a  partir d'un nombre de semaines,
     * de jours et d'heures.
     * @param semaines nombre de semaines qu'on souhaite utiliser pour la duree.
     * @param jours nombre de jours qu'on souhaite utiliser pour la duree.
     * @param heures nombre d'heures qu'on souhaite utiliser pour la duree.
     */
    public Duree(double semaines, double jours, int hours) {
        if(semaines < 0 || jours < 0 || hours < 0) {
            throw new IllegalArgumentException("Le nombre de semaines," +
                    " de jours et d'heures ne peuvent pas etre negatifs.");
        }
        this.hours = (int) Math.round(semaines * 7 * 24)
                     + (int) Math.round(jours * 24) + hours;
    }

    /**
     * Constructeur d'une duree a  partir d'un nombre de mois, de semaines,
     * de jours et d'heures.
     * @param mois nombre de mois qu'on souhaite utiliser pour la duree.
     * @param semaines nombre de semaines qu'on souhaite utiliser pour la duree.
     * @param jours nombre de jours qu'on souhaite utiliser pour la duree.
     * @param heures nombre d'heures qu'on souhaite utiliser pour la duree.
     */
    public Duree(double mois, double semaines, double jours, int hours) {
        if(mois < 0 || semaines < 0 || jours < 0 || hours < 0) {
            throw new IllegalArgumentException("Le nombre de mois, " +
                    "de semaines, de jours et d'heures ne peuvent pas etre" +
                    " negatifs.");
        }
        this.hours = (int) Math.round(mois * 4 * 7 * 24)
                     + (int) Math.round(semaines * 7 * 24)
                     + (int) Math.round(jours * 24) + hours;
    }

    

    /**
     * Getter de la duree selon l'unite souhaitee
     * (m = mois, s = semaine, j = jour, h = heure).
     * @param unite l'unite souhaitee.
     * @return la duree en fonction de l'unite souhaitee.
     */
    public double getDuree(char unite) {
        return switch (unite) {
            case 'm' -> (double) hours / (30 * 24);
            case 's' -> (double) hours / (7 * 24);
            case 'j' -> (double) hours / 24;
            case 'h' -> (double) hours;
            default -> throw new IllegalArgumentException("L'unite " +
                    "n'est pas reconnue.");
        };
    }

    /**
     * Renvoie une duree sous forme d'un tableau de 4 entiers.
     * L'indice 0 correspond au nombre de mois, l'indice 1 au nombre de semaines,
     * l'indice 2 au nombre de jours et l'indice 3 au nombre d'heures.
     * Exemple : si la duree est de 2 heures, le tableau renvoye sera [0, 0, 0, 2].
     * Exemple 2 : si la duree est de 342 heures, le tableau renvoye sera [9, 3, 2, 3].
     * @return un tableau de 4 entiers.
     */
    public int[] getDureeTableau() {
    	int dureeBis = hours;
        int[] tableau = new int[4];
        tableau[3] = dureeBis % 24;
        dureeBis /= 24;
        tableau[2] = dureeBis % 7;
        dureeBis /= 7;
        tableau[1] = dureeBis % 4;
        dureeBis /= 4;
        tableau[0] = dureeBis;
        return tableau;
    }

    /**
     * Methode toString()
     * @return la duree sous forme d'une chaine de caracteres.
     */
    @Override
    public String toString() {
        int[] tableau = getDureeTableau();
        String dureeString = "";
        if(tableau[0] != 0) {
            dureeString += tableau[0] + " mois, ";
        }
        if(tableau[1] != 0) {
            dureeString += tableau[1] + " semaines, ";
        }
        if(tableau[2] != 0) {
            dureeString += tableau[2] + " jours, ";
        }
        if(tableau[3] != 0) {
            dureeString += tableau[3] + " heures";
        }
        if (dureeString.equals("")) {
        	dureeString += "0 jours";
        }
        return dureeString;
    }
    /**
     * Getter de la duree.
     * @return la duree en heures.
     */
    public int getHours() {
        return hours;
    }
    public void addDuree(int toAdd) {
    	this.hours += toAdd;
    }
    public void setHours(int toAdd) {
    	this.hours = toAdd;
    }

}
