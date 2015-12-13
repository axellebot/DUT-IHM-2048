
import javax.swing.UIManager;

/**
 *
 * @author axell
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //UserInterface
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        //main
        Fenetre fen = new Fenetre();
        fen.setLocationRelativeTo(null);
        fen.setVisible(true);
    }

}
