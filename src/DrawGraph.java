import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-Final
 * Date: 5/3/13
 * Time: 12:08 AM
 * Java Class: PACKAGE_NAME
 */
public class DrawGraph extends JFrame implements ActionListener {
     JButton solveButton;
     MNKruskal locations;
     GraphView graphView;
    public DrawGraph(MNKruskal locations) {
        this.locations = locations;
         getContentPane().setLayout(null);
         setupGUI();
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add(new GraphView(graph));
    }

    private void setupGUI() {
        solveButton = new JButton();
        solveButton.setLocation(250,10);
        solveButton.setSize(200,30);
        solveButton.setText("Solve");
        getContentPane().add(solveButton);

        graphView =  new GraphView(locations.graph);
        graphView.setLocation(0,30);
        graphView.setSize(700,600 );
        getContentPane().add(graphView);

        solveButton.addActionListener(this);

        setTitle("Library Map");
        setSize(700,700);
        setVisible(true);
        setResizable(false);

    }


    public static void main(String[] args) {
       /* MNKruskal locations = new MNKruskal();


        JFrame frame = new JFrame("Library Map");
        DrawGraph applet = new DrawGraph(locations.graph);
        applet.setSize(700, 500);
        frame.add(applet);
        applet.init();
        applet.start();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setVisible(true);
        */

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        System.out.println(obj);
        if (obj == solveButton){
            System.out.println("Solve Button Pressed");
            JFrame frame = new JFrame("MST Solution Map");
            GraphView applet = new GraphView(locations.graphMST);
            applet.setSize(700, 500);
            frame.add(applet);


            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 700);
            frame.setVisible(true);
        }
    }
}
