import java.util.*;

import static java.util.Collections.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA4
 * Date: 3/21/13
 * Time: 12:28 AM
 * Java Class: PACKAGE_NAME
 *
 * Graph class builds the graph for display.
 * Uses hardcoded XY coordinates
 */
class GraphException extends RuntimeException{

    public GraphException (String word){
         super(word);
    }
   }

class Vertex{
    public String id;
    public Library location;             //Word at this Node
    public List<GEdge> adj;             //List of  Adjacent vertices
    public Vertex prev;            //Previous vertex on shortest path
    private int dx;
    private int dy;
    public double           dist;


    public Vertex (Library location){
        dy = 0;
        dx = 0;
        id = location.getId();
        this.location = location;
        adj = new LinkedList<GEdge>();
        reset();

    }


    public int getDx(){
        return dx;
    }

    public int getDy(){
        return dy;
    }

    public void setDx(int x){
        dx = x;
    }

    public void setDy (int y){
        dy = y;
    }
    public List<GEdge> getAdj(){
        return adj;
    }
    public String getId(){
        return id;
    }
    public Library getVertexLocation(){
        return location;
    }
    public void reset (){
        prev = null;

        dist = Graph.INFINITY;
    }

    public int getX(){


        return Integer.parseInt(location.getX());
    }

    public int getY(){
        return Integer.parseInt(location.getY());
    }

}

class GEdge implements Comparable<GEdge>{
    public Vertex origin;
    public Vertex dest; //Second vertex in GEdge
    public double cost;


    public GEdge (Vertex b, Vertex d, double c){
        origin = b;  //added origin
        dest = d;
        cost = c;
    }



    public double getDistance(){
        return cost;
    }



    @Override
    public String toString(){
        return "(" + origin.getId() + ", " + dest.getId() + ") : Weight = " + cost;
    }


    @Override
    public int compareTo(GEdge edge) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}


public class Graph {
    private String graphError = null;
    public static final double INFINITY = Double.MAX_VALUE;
    private Map<Library, Vertex> vertexMap = new HashMap<Library, Vertex>( );

    private ArrayList<GEdge> edges = new ArrayList<GEdge>();
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    double cost = 0;

    public int getVertexMapSize(){
        return vertexMap.size();
    }

    //Add an edge to the graph

    public void addGEdge (Library sourceLocation, Library destinationLocation, double cost){
        Vertex v = getVertex(sourceLocation);
        Vertex w = getVertex(destinationLocation);
        //v.adj.add(new GEdge(v, w, cost));
        GEdge e = new GEdge(v, w, cost);
        v.adj.add(e);
        edges.add(e);
    }

    public ArrayList<Vertex> getVertices(){
        return vertices;
    }

    public ArrayList<GEdge> getEdges(){
        return edges;
    }
    public void resetGraph(){
        clearAll();
    }

    public double getCost(){

        return cost;
    }
    public void printPath (Library location){
        Vertex w = vertexMap.get(location);
        if ( w == null){
            throw new NoSuchElementException("Destination vertex not found: Does not fit into Graph");
        } else if (w.dist == INFINITY){
            System.out.println(location.getId() + " is unreachable.");
            graphError = location.getId() + " is unreachable.";
        }

        else {
            cost = w.dist;
            System.out.print( "( Distance is: " + w.dist + ") ");
            printPath( w );
            System.out.println();
        }
    }

    /*
        If vertexWord is not present, add it to the vertexMap.
        In either case, return the vertex
     */
      private Vertex getVertex (Library location){
          Vertex v = vertexMap.get(location);
          if ( v == null){

              v = new Vertex(location);
              vertices.add(v);
              vertexMap.put(location, v);
          }
          return v;
      }
        /*
        Recursive routine to rpint the shortest path to dest after running shortest path algorithm.
        The path is known to exist
         */
    private void printPath (Vertex dest){
        //results.add(dest.getVertexLocation());
        if (dest.prev != null){
            //results.add(dest.prev.getVertexWord());
            printPath(dest.prev);
            System.out.print(" to ");

        }

        System.out.print(dest.getVertexLocation().getName());

    }

    public String getGraphError(){
        return graphError;
    }

    private void clearAll(){
        for (Vertex v : vertexMap.values()){
            v.reset();
        }
    }

    /*
    Single source unweighted shortest path algorithm

     */

    public Map<Library, Vertex> getVertexMap(){
        return vertexMap;
    }






}
