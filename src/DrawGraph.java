import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-Final
 * Date: 5/3/13
 * Time: 12:08 AM
 * Java Class: PACKAGE_NAME
 */
public class DrawGraph extends JApplet {

    public DrawGraph(Graph graph) {

        add(new GraphView(graph));
    }



    public static void main(String[] args) {
        MNKruskal locations = new MNKruskal();

        JFrame frame = new JFrame("Library Map");
        //frame.setLayout(new BorderLayout());
        DrawGraph applet = new DrawGraph(locations.graph);
        applet.setSize(700, 500);
        JButton solveButton = new JButton("Solve");
        solveButton.setLocation(50, 550);
        frame.add(solveButton);
        //JPanel button = new JPanel();
        //button.setSize(200,700);
        //button.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        frame.add(applet);
        //frame.add(button, BorderLayout.SOUTH);
        applet.init();
        applet.start();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setVisible(true);


    }
}
