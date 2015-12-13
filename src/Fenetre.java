import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Axel
 */
public class Fenetre extends JFrame {

    private VueGrille vgrille;

    public Fenetre() {
        this.vgrille = new VueGrille(this);
        this.setTitle("2048");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(this.getClass().getResource("mainicon.png")).getImage());

        initialisation();

        //ajout des ecouteurs
        addKeyListener(vgrille);
    }

    public void initialisation() {

        //création d'un panneau
        JPanel pano = new JPanel();
        //ajout du gestionnaire de placement au panneau
        pano.setLayout(new GridBagLayout());

        GridBagConstraints cont = new GridBagConstraints();

        /*placement des composants*/
        //remplir
        cont.fill = GridBagConstraints.BOTH;

        //redimensionne en fonction de la fenetre
        cont.weightx = 1;
        cont.weighty = 1;

        //padding
        cont.ipadx = 0;
        cont.ipadx = 0;

        cont.gridx = 0;
        cont.gridy = 0;

        //placer le content dans le pano
        pano.add(this.vgrille, cont);

        //changer la couleur
        pano.setBackground(Color.lightGray);
        //placer le pano dans la fenetre
        this.setContentPane(pano);
        this.pack();
    }
}
