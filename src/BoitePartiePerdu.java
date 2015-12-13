
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author axell
 */
public class BoitePartiePerdu extends JDialog implements ActionListener {

    //attribut graphique
    private JButton btnRecommencer, btnFermer;
    private JLabel lblPerdu;
    private boolean rejouer;

    public BoitePartiePerdu(Fenetre owner) {
        super(owner, true); //constructeur de la classe Mère : owner = propriétaire de la fenêtre (son parent), le second paramètre est true pour la rendre modale

        this.setTitle("Partie Terminer");

        this.btnFermer = new JButton("Fermer");
        btnFermer.setBackground(Color.RED);

        this.btnRecommencer = new JButton("Recommencer");
        btnRecommencer.setBackground(Color.GREEN);

        this.lblPerdu = new JLabel("Perdu");
        lblPerdu.setFont(new Font("Serif", Font.PLAIN, 36));

        initialisation();

        //ajout des ecouteurs
        btnFermer.addActionListener(this);
        btnRecommencer.addActionListener(this);
    }

    public void initialisation() {
        //création d'un panneau
        JPanel pano = new JPanel();
        //ajout du gestionnaire de placement au panneau
        pano.setLayout(new GridBagLayout());

        GridBagConstraints cont = new GridBagConstraints();

        /*placement des composants*/
        //remplir
        cont.fill = GridBagConstraints.CENTER;

        //redimensionne en fonction de la fenetre
        cont.weightx = 1;
        cont.weighty = 1;

        //padding
        cont.ipadx = 0;
        cont.ipadx = 0;

        cont.gridx = 0;
        cont.gridy = 0;
        cont.gridwidth = 2;
        pano.add(lblPerdu, cont);

        cont.fill = GridBagConstraints.BOTH;

        cont.gridwidth = 1;
        cont.gridy = 1;
        pano.add(btnRecommencer, cont);

        cont.gridx = 1;
        pano.add(btnFermer, cont);

        //changer la couleur
        pano.setBackground(Color.WHITE);
        //placer le panneau dans la JFrame
        this.setContentPane(pano);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnFermer) {
            this.rejouer = false;
            this.setVisible(false);//on ferme la fenêtre

        }
        if (ae.getSource() == btnRecommencer) {
            this.rejouer = true;
            this.setVisible(false);//on ferme la fenêtre
        }
    }

    public boolean showDialog() {
        this.setVisible(true);//on ouvre la fenêtre
        return rejouer;//on retourne Le résultat
    }

}
