import javax.swing.*;

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
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
        DrawGraph applet = new DrawGraph(locations.graph);
        frame.add(applet);
        applet.init();
        applet.start();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }
}
