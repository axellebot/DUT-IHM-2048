

import java.util.ArrayList;

/**
 *
 * @author axell
 */
public class Grille {

    private int taille;
    private Case grille[][];

    public Grille(int taille) {
        this.taille = taille;
        this.grille = new Case[this.taille][this.taille];

        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                grille[i][j] = new Case(0, j, i);
            }
        }
    }

    public Grille(Grille g) {
        this.taille = g.taille;
        this.grille = new Case[this.taille][this.taille];
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                this.grille[i][j] = new Case(g.getGrille()[i][j]);
            }
        }
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public Case[][] getGrille() {
        return grille;
    }

    public void setGrille(Case[][] grille) {
        this.grille = grille;
    }

    public void reinitialiseGrille() {
        this.grille = null;
    }

    @Override
    public String toString() {
        String _tmp = new String();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                _tmp += grille[i][j].toString() + "\t";
            }
            _tmp += "\n";
        }
        return _tmp;
    }

    public void addRandom() {

        ArrayList<Case> listeCaseVide = new ArrayList();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j].getValeur() == 0) {
                    listeCaseVide.add(grille[i][j]);
                }
            }
        }
        if (listeCaseVide.size() > 0) {

            int indice = (int) (Math.random() * (listeCaseVide.size() - 1));

            double rand = Math.random();
            if (rand <= 0.5) {
                listeCaseVide.get(indice).setValeur(2);

            } else {
                if (rand > 0.5) {
                    listeCaseVide.get(indice).setValeur(4);

                }
            }
        }
    }

    public int[] recup_colonne(int j) {
        int tab[] = new int[taille];

        //initaliser les cases de tab à 0
        for (int i = 0; i < taille; i++) {
            tab[i] = 0;
        }

        int k = 0;

        for (int i = 0; i < taille; i++) {
            if (grille[i][j].getValeur() > 0) {
                tab[k] = grille[i][j].getValeur();
                k++;
            }
        }
        return tab;
    }

    public int[] recup_ligne(int i) {
        int tab[] = new int[taille];

        //initaliser les cases de tab à 0
        for (int j = 0; j < taille; j++) {
            tab[j] = 0;
        }

        int k = 0;

        for (int j = 0; j < taille; j++) {
            if (grille[i][j].getValeur() > 0) {
                tab[k] = grille[i][j].getValeur();
                k++;
            }
        }
        return tab;
    }

    public void pousser_haut() {

        //appliquer la fusion sur la grille
        for (int j = 0; j < taille; j++) { // pour chaque colonne
            int tab[] = recup_colonne(j); //recupérer la colonne condensé

            int i = 0, k = 0;

            while (k < taille) {  //chaque ligne de la colonne
                if (k + 1 < taille && tab[k] == tab[k + 1]) {
                    this.grille[i][j].setValeur(tab[k] + tab[k + 1]);
                    k += 2;
                } else {
                    this.grille[i][j].setValeur(tab[k]);
                    k++;
                }
                i++;
            }
            for (; i < taille; i++) {
                this.grille[i][j].setValeur(0);

            }

        }

    }

    public void pousser_bas() {
        for (int j = 0; j < taille; j++) { // pour chaque colonne
            int tab[] = recup_colonne(j); //recupérer la colonne condensé

            int i = this.taille - 1, k = this.taille - 1;

            while (k >= 0) {  //chaque ligne de la colonne
                if (tab[k] != 0) {
                    if (k - 1 >= 0 && tab[k] == tab[k - 1]) {
                        this.grille[i][j].setValeur(tab[k] + tab[k - 1]);
                        k -= 2;
                    } else {
                        this.grille[i][j].setValeur(tab[k]);
                        k--;
                    }
                    i--;
                } else {
                    k--;
                }
            }
            for (; i >= 0; i--) {
                this.grille[i][j].setValeur(0);

            }
        }

    }

    public void pousser_gauche() {

        //appliquer la fusion sur la grille
        for (int i = 0; i < taille; i++) { // pour chaque ligne
            int tab[] = recup_ligne(i); //recupérer la ligne condensé

            int j = 0, k = 0;

            while (k < taille) {  //chaque colonne de la ligne
                if (k + 1 < taille && tab[k] == tab[k + 1]) {
                    this.grille[i][j].setValeur(tab[k] + tab[k + 1]);
                    k += 2;
                } else {
                    this.grille[i][j].setValeur(tab[k]);
                    k++;
                }
                j++;
            }
            for (; j < taille; j++) {
                this.grille[i][j].setValeur(0);

            }

        }

    }

    public void pousser_droite() {
        for (int i = 0; i < taille; i++) { // pour chaque ligne
            int tab[] = recup_ligne(i); //recupérer la ligne condensé

            int j = this.taille - 1, k = this.taille - 1;

            while (k >= 0) {  //chaque ligne de la colonne
                if (tab[k] != 0) {
                    if (k - 1 >= 0 && tab[k] == tab[k - 1]) {
                        this.grille[i][j].setValeur(tab[k] + tab[k - 1]);
                        k -= 2;
                    } else {
                        this.grille[i][j].setValeur(tab[k]);
                        k--;
                    }
                    j--;
                } else {
                    k--;
                }
            }
            for (; j >= 0; j--) {
                this.grille[i][j].setValeur(0);

            }
        }

    }

    /**
     * Comparer les grilles
     *
     * @param g1 Grille 1
     * @param g2 Grille 2
     * @return true si les grilles sont les mêmes sinon false
     */
    static boolean equalsGrille(Grille g1, Grille g2) {

        if (g1.getTaille() == g2.getTaille()) {
            for (int i = 0; i < g1.getTaille(); i++) {
                for (int j = 0; j < g1.getTaille(); j++) {
                    if (g1.getGrille()[i][j].getValeur() != g2.getGrille()[i][j].getValeur()) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean grillePerdu() {
        int k = 0;

        Grille g_tmp = new Grille(this);

        boolean grilleRempli = false;
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                if (this.grille[i][j].getValeur() != 0) {
                    k++;
                }
            }
        }
        if (k == this.getTaille() * this.getTaille()) {
            grilleRempli = true;
        }
        if (grilleRempli) {
            g_tmp.pousser_bas();
            g_tmp.pousser_haut();
            g_tmp.pousser_gauche();
            g_tmp.pousser_droite();
            if(Grille.equalsGrille(this,g_tmp)==true){
                return true;
            }
        }

        return false;
    }

    public boolean grilleGagne() {
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                if (this.grille[i][j].getValeur() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }
}
