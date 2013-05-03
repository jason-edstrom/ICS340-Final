import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-Final
 * Date: 4/29/13
 * Time: 12:26 AM
 * Java Class: PACKAGE_NAME
 */
public class MNKruskal {
         private String distanceFile = "/ics340/distances.txt";
         private String locationFile = "/ics340/libraries.txt";
         private ArrayList<Library> locations;
         private ArrayList<Distance> distances;
         private ArrayList<Kruskal.Edge> MSTEdges;
         public Graph graph = new Graph();
         public Graph graphMST = new Graph();
         private Kruskal kruskal;
    public MNKruskal (){
        locations = new ArrayList<Library>();
        distances = new ArrayList<Distance>();
          readLibraries(locationFile);
          readDistances(distanceFile);
         System.out.println("Files Parsed");
        buildGraph();
         kruskal = new Kruskal(locations, distances);
        buildMSTGraph();




    }





    public void addLibrary(Library singleLocation){
        locations.add(singleLocation);
    }

    public void readLibraries(String file_name){
        String tmpID = null;
        String tmpName = null;
        String tmpCounty = null;
        String tmpX = null;
        String tmpY = null;
        try {

            FileReader fileReader = new FileReader(file_name);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strLine;

            while ((strLine = bufferedReader.readLine()) != null){
                StringTokenizer st = new StringTokenizer(strLine, ",");
                int counter = 0;
                while (st.hasMoreTokens()){
                    if(counter == 5 ){
                        throw new IllegalArgumentException("Error in line input.  Too big for program");
                    }
                    switch(counter){
                        case 0:
                            tmpID = st.nextToken();
                            break;

                        case 1:
                            tmpCounty = st.nextToken();
                            break;
                        case 2:
                            tmpName = st.nextToken();
                            break;
                        case 3:
                            tmpX = st.nextToken();
                            break;
                        case 4:
                            tmpY = st.nextToken();
                            break;
                    }
                    counter++;



                }

                if ((tmpID == null) || (tmpCounty == null) || (tmpName == null) || (tmpX == null) || (tmpY == null)){
                       throw new IllegalArgumentException("Too few items in input line");
                }

                locations.add(new Library(tmpID, tmpCounty, tmpName, tmpX, tmpY));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void readDistances(String file_name){
        String tmpOrigin = null;
        String tmpDestination = null;
        double tmpDistance = 0;

        try {

            FileReader fileReader = new FileReader(file_name);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strLine;

            while ((strLine = bufferedReader.readLine()) != null){
                StringTokenizer st = new StringTokenizer(strLine, ",");
                int counter = 0;
                while (st.hasMoreTokens()){
                    if(counter == 3 ){
                        throw new IllegalArgumentException("Error in line input.  Too big for program");
                    }
                    switch(counter){
                        case 0:
                            tmpOrigin = st.nextToken();
                            break;

                        case 1:
                            tmpDestination = st.nextToken();
                            break;
                        case 2:
                            tmpDistance = Double.parseDouble(st.nextToken());
                            break;

                    }
                    counter++;



                }

                if ((tmpOrigin == null) || (tmpDestination == null)){
                    throw new IllegalArgumentException("Too few items in input line");
                } else if (tmpDistance == 0){
                    throw new IllegalArgumentException("Too few items in input line");
                }

                distances.add(new Distance(tmpOrigin, tmpDestination, tmpDistance));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void buildGraph(){
        Library origin = null;
        Library destination = null;
        double distance = 0.0;
        int index = 0;

        for (Distance d : distances){
             for (Library l : locations){
                 if (d.getOrigin().equals(l.getId())){
                    index = locations.indexOf(l);
                    origin = locations.get(index);
                     break;
                 }
             }
            for (Library l : locations){
                if (d.getDestination().equals(l.getId())){
                    index = locations.indexOf(l);
                    destination = locations.get(index);
                    break;
                }
            }
            //distance = d.getDistance();
            graph.addGEdge(origin, destination, d.getDistance());
        }


    }

    public void buildMSTGraph(){

        MSTEdges = kruskal.getTree();
        for (Kruskal.Edge e : MSTEdges){
            graphMST.addGEdge(e.getU().getVertexLocation(), e.getV().getVertexLocation(), e.getWeight() );
        }
          System.out.println("MST graph built");


    }

     public static void main (String args[]){
         MNKruskal locations = new MNKruskal();

     }

 }

