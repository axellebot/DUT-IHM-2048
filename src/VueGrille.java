import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;

/**
 *
 * @author axell
 */
public class VueGrille extends JComponent implements KeyListener {

    private Grille g;
    private Fenetre owner;
    private int tailleCasePixels;
    private final int epaisseur;

    public VueGrille(Fenetre owner) {
        this.owner = owner;
        this.g = new Grille(4);
        tailleCasePixels = 200;
        epaisseur = 5;

        initialisation();

    }

    public void initialisation() {
        this.g.addRandom();
        this.g.addRandom();
        this.repaint();
        this.owner.pack();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int j = 0; j <= this.g.getTaille(); j++) {
            g2.setStroke(new BasicStroke(epaisseur));
            g2.drawLine(tailleCasePixels * j + epaisseur / 2, 0, tailleCasePixels * j + epaisseur / 2, tailleCasePixels * this.g.getTaille());
            g2.drawLine(0, tailleCasePixels * j + epaisseur / 2, tailleCasePixels * this.g.getTaille(), tailleCasePixels * j + epaisseur / 2);
        }

        for (int i = 0; i < this.g.getTaille(); i++) {
            for (int j = 0; j < this.g.getTaille(); j++) {
                g2.setColor(setColor(this.g.getGrille()[i][j].getValeur()));
                g2.fillRect(tailleCasePixels * j + epaisseur, tailleCasePixels * i + epaisseur, tailleCasePixels - epaisseur, tailleCasePixels - epaisseur);

                g2.setColor(Color.black);
                g2.setFont(new Font("Calibri", Font.BOLD, 75));
                g2.drawString(this.g.getGrille()[i][j].toString(), tailleCasePixels * j + epaisseur / 2 + tailleCasePixels / 2, tailleCasePixels * i + epaisseur / 2 + tailleCasePixels / 2);
            }
        }

    }

    public Color setColor(int a) {
        Color c = new Color(0);
        switch (a) {
            case 0:
                c = new Color(125, 125, 125);
                return c;
            case 2:
                c = new Color(238, 228, 218);
                return c;
            case 4:
                c = new Color(237, 224, 200);
                return c;
            case 8:
                c = new Color(245, 177, 121);
                return c;
            case 16:
                c = new Color(245, 149, 99);
                return c;
            case 32:
                c = new Color(246, 124, 95);
                break;
            case 64:
                c = new Color(246, 94, 59);
                return c;
            case 128:
                c = new Color(237, 204, 114);
                return c;
            case 256:
                c = new Color(233, 209, 87);
                return c;
            case 512:
                c = new Color(237, 200, 80);
                return c;
            case 1024:
                c = new Color(239, 197, 63);
                return c;
            case 2048:
                c = new Color(236, 196, 0);
                return c;

        }
        return c;
    }

    @Override

    public Dimension getPreferredSize() {
        return new Dimension((tailleCasePixels + epaisseur / 2) * this.g.getTaille(), (tailleCasePixels + epaisseur / 2) * this.g.getTaille());
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {

        //redimensionne
        if (ke.getKeyChar() == '+') {
            this.g = new Grille(this.g.getTaille() + 1);
            initialisation();
            owner.setLocationRelativeTo(null);

        }
        if (ke.getKeyChar() == '-') {
            if (g.getTaille() > 2) {
                this.g = new Grille(this.g.getTaille() - 1);
                initialisation();
                owner.setLocationRelativeTo(null);
            }
        }
        if (ke.getKeyChar() == '0' && this.g.getTaille() != 4) {

            this.g = new Grille(4);
            initialisation();
            owner.setLocationRelativeTo(null);
        }

        Grille g_tmp = new Grille(this.g);
        if (ke.getKeyCode() == ke.VK_UP || ke.getKeyCode() == ke.VK_DOWN || ke.getKeyCode() == ke.VK_LEFT || ke.getKeyCode() == ke.VK_RIGHT) {
            if (ke.getKeyCode() == ke.VK_UP) {

                this.g.pousser_haut();
            }

            if (ke.getKeyCode() == ke.VK_DOWN) {

                this.g.pousser_bas();
            }
            if (ke.getKeyCode() == ke.VK_LEFT) {

                this.g.pousser_gauche();
            }

            if (ke.getKeyCode() == ke.VK_RIGHT) {

                this.g.pousser_droite();
            }

            //Grille modifié ?
            if (Grille.equalsGrille(g, g_tmp) == false) {
                this.g.addRandom();
            }

            repaint();

            //Partie perdu ?
            if (this.g.grillePerdu() == true) {
                BoitePartiePerdu boite = new BoitePartiePerdu(this.owner);
                boite.setLocationRelativeTo(this);//position de la boite de dialogue par rapport à this
                if (boite.showDialog() == true) {
                    this.g = new Grille(this.g.getTaille());
                    initialisation();
                } else {
                    this.owner.dispose();
                }

            }

            //2048 réussi
            if (this.g.grilleGagne() == true) {
                BoitePartieGagne boite = new BoitePartieGagne(this.owner);
                boite.setLocationRelativeTo(this);//position de la boite de dialogue par rapport à this
                if (boite.showDialog() == true) {
                    this.g = new Grille(this.g.getTaille());
                    initialisation();
                } else {
                    this.owner.dispose();
                }
            }

        }

        //recommencer un nouveau jeu
        if (ke.getKeyCode() == ke.VK_F5 || ke.getKeyCode() == ke.VK_R) {
            this.g = new Grille(this.g.getTaille());
            initialisation();
        }
    }

}
