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
     JButton displayButton;
     GraphView displayPanel;
     JScrollPane jScrollPane;
     JPanel solvePanel;
     MNKruskal locations;
     GraphView graphView;
    GraphView solve;
    int counter = 0;
    public DrawGraph(MNKruskal locations) {
        this.locations = locations;
         getContentPane().setLayout(null);
         setupGUI();
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add(new GraphView(graph));
    }

    private void setupGUI() {

        displayButton = new JButton();
        displayButton.setLocation(400, 10);
        displayButton.setSize(100,30);
        displayButton.setText("Display");
        getContentPane().add(displayButton);


        solveButton = new JButton();
        solveButton.setLocation(500,10);
        solveButton.setSize(100,30);
        solveButton.setText("Solve");
        getContentPane().add(solveButton);
/*

        //displayPanel = new GraphView();
        //displayPanel.setLocation(0,30);
        displayPanel.setBackground(Color.white);
        displayPanel.setSize(500,800);
        //displayPanel.setForeground(Color.green);
        jScrollPane = new JScrollPane();
        //jScrollPane.add(displayPanel);
        jScrollPane.setViewportView(displayPanel);
        jScrollPane.setLocation(0,30);
        getContentPane().add(jScrollPane);
*/

        displayButton.addActionListener(this);
        //displayPanel = new GraphView();
        /*
        graphView =  new GraphView(locations.graph);
        graphView.setLocation(0,30);
        graphView.setSize(750,600 );
        getContentPane().add(graphView);

        solveButton.addActionListener(this);

        setTitle("Library Map");
        setSize(750,700);
        setVisible(true);
        setResizable(false);
      */

        solveButton.addActionListener(this);

        setTitle("Library Map");
        setSize(1800, 800);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        System.out.println(obj);
        if (obj == solveButton){
             //if (solve != null){
                // getContentPane().remove(solve);
             //}

            /*System.out.println("Solve Button Pressed");
            JFrame frame = new JFrame("MST Solution Map");
            GraphView applet = new GraphView(locations.graphMST);
            applet.setSize(750, 500);
            frame.add(applet);


            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(750, 700);
            frame.setVisible(true);*/

            drawingStep(locations.graphMST);
             solve.setLocation(750, 30);
             solve.setSize(900, 800);
            JScrollBar jScrollBar = new JScrollBar();
            jScrollBar.

             //getContentPane().add(solve);
            getContentPane().validate();
            getContentPane().repaint();
        }

        if (obj == displayButton){
            System.out.println("Display Button Pressed");
            //displayPanel =  new GraphView(locations.graph);

            graphView = new GraphView(locations.graph);
            //jScrollPane = new JScrollPane();
            graphView.setSize(800,800);

            //jScrollPane.setLocation(0,30);
            //jScrollPane.setSize(500, 800);
            //jScrollPane.add(displayPanel);
            //getContentPane().add(jScrollPane);
            graphView.setLocation(0,30);

            getContentPane().add(graphView);
            getContentPane().validate();
            getContentPane().repaint();
        }


    }

    public void drawingStep (Graph graph){
        if (counter > graph.getEdges().size()) {
            counter = 0;
        }
        solve = new GraphView(graph, counter);
        counter++;

    }
}
