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
    public boolean visited;       //Extra variable
    public boolean root;
    public int numberVetices = 0;
    public double           dist;


    public Vertex (Library location){
        visited = false;
        id = location.getId();
        this.location = location;
        adj = new LinkedList<GEdge>();
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

        dist = Graph.INFINITY;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
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

    public void setNodesVisited() {
        origin.setVisited(true); //added origin
        origin.numberVetices++;
        dest.setVisited(true);
        dest.numberVetices++;
    }

    public double getDistance(){
        return cost;
    }

    public boolean areNodesVisited() {
        return (origin.isVisited() & dest.isVisited());   //added origin
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
    //private ArrayList<String> results = new ArrayList<String>();
    private ArrayList<GEdge> edges = new ArrayList<GEdge>();
    private ArrayList<GEdge> sortedGEdges = new ArrayList<GEdge>();
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

     public void sortGEdgesDistance(){
         Collections.sort(edges, new DistanceComparator());
         System.out.println("Sorted");
         //return edges;
     }

    public double computeMinimumSpanningTree() {
        HashSet<GEdge> mst = new HashSet<GEdge>();
        for (int i = 0; i < edges.size(); i++) {
            //boolean visitedcheck = edges.get(i).areNodesVisited();
            if (!edges.get(i).areNodesVisited()) {
                mst.add(edges.get(i));
                edges.get(i).setNodesVisited();
            }
        }

        System.out.println(mst);

        Object[] newGEdge = mst.toArray();
        double totalCost=0;
        for (int i = 0; i < newGEdge.length; i++) {
            totalCost+=((GEdge) newGEdge[i]).getDistance();
        }
        return totalCost;
    }

    public void findRoots(){

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
            for (GEdge e : v.adj){
                Vertex w = e.dest;
                if (w.dist == INFINITY){
                    w.dist = v.dist +1;
                    w.prev = v;
                    q.add(w);
                }
            }
        }
    }

    private static class DistanceComparator implements Comparator<GEdge>{

        @Override
        public int compare(GEdge e1, GEdge e2) {
            return (e1.cost < e2.cost ) ? -1: (e1.cost > e2.cost) ? 1:0 ;


        }

    }

}
