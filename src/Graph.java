import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA4
 * Date: 3/21/13
 * Time: 12:28 AM
 * Java Class: PACKAGE_NAME
 */
class GraphException extends RuntimeException{

    public GraphException (String word){
         super(word);
    }
   }

class Vertex{
    public String id;
    public Library location;             //Word at this Node
    public List<Edge> adj;             //List of  Adjacent vertices
    public Vertex prev;            //Previous vertex on shortest path
    public int              scratch;        //Extra variable
    public double           dist;


    public Vertex (Library location){
        id = location.getId();
        this.location = location;
        adj = new LinkedList<Edge>();
        reset();

    }

    public String getId(){
        return id;
    }
    public Library getVertexLocation(){
        return location;
    }
    public void reset (){
        prev = null;
        scratch = 0;
        dist = Graph.INFINITY;
    }
}

class Edge {
    public Vertex dest; //Second vertex in Edge
    public double cost;


    public Edge (Vertex d, double c){
        dest = d;
        cost = c;
    }

}

public class Graph {
    private String graphError = null;
    public static final double INFINITY = Double.MAX_VALUE;
    private Map<Library, Vertex> vertexMap = new HashMap<Library, Vertex>( );
    //private ArrayList<String> results = new ArrayList<String>();
    double cost = 0;

    public int getVertexMapSize(){
        return vertexMap.size();
    }

    //Add an edge to the graph

    public void addEdge (Library sourceLocation, Library destinationLocation, double cost){
        Vertex v = getVertex(sourceLocation);
        Vertex w = getVertex(destinationLocation);
        v.adj.add(new Edge(w, cost));
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


    public void unweighted (Library location){
        clearAll();

        Vertex start = vertexMap.get(location);

        if (start == null){
            throw new NoSuchElementException("Start vertex not found: Does not fit into Graph");
        }

        Queue <Vertex> q = new LinkedList<Vertex>();
        q.add(start);
        start.dist = 0;

        while ( !q.isEmpty()){
            Vertex v = q.remove();
            for (Edge e : v.adj){
                Vertex w = e.dest;
                if (w.dist == INFINITY){
                    w.dist = v.dist +1;
                    w.prev = v;
                    q.add(w);
                }
            }
        }
    }

}
