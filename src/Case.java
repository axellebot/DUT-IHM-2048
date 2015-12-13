
/**
 * @author axell
 */
public class Case {
    private int valeur;
    private int x, y;


    public Case(int valeur, int x, int y) {
        this.valeur = valeur;
        this.x = x;
        this.y = y;
    }

    public Case(Case c) {
        this.valeur = c.getValeur();
        this.x = c.getX();
        this.y = c.getY();
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return this.valeur + "";
    }


}
