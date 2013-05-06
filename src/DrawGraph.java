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
 *
 * DrawGraph Class creates the gui aspect and handles the button calls.
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
        solveButton.setLocation(300,10);
        solveButton.setSize(200,30);
        solveButton.setText("Solve");
        getContentPane().add(solveButton);

        graphView =  new GraphView(locations.graph);
        graphView.setLocation(0,30);
        graphView.setSize(750,600 );
        getContentPane().add(graphView);

        solveButton.addActionListener(this);

        setTitle("Library Map");
        setSize(750,700);
        setVisible(true);
        setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        System.out.println(obj);
        if (obj == solveButton){
            System.out.println("Solve Button Pressed");
            JFrame frame = new JFrame("MST Solution Map");
            GraphView applet = new GraphView(locations.graphMST);
            applet.setSize(750, 500);
            frame.add(applet);


            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(750, 700);
            frame.setVisible(true);
        }
    }
}
