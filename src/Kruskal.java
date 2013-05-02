import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-Final
 * Date: 5/1/13
 * Time: 9:26 PM
 * Java Class: PACKAGE_NAME
 */
public class Kruskal {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private ArrayList<Library> libraries;
    private ArrayList<Distance> distances;
    private ArrayList<Edge> tree;

    public Kruskal(ArrayList libraries, ArrayList distances){
        this.libraries = libraries;
        this.distances = distances;
        buildVertices();
        buildEdges();
        System.out.println("Edges and Vertices Built");

        DisjointSet disjointSet = new DisjointSet(vertices);
        System.out.println("DisjointSet Built");

        tree = new ArrayList<Edge>();

        Collections.sort(edges);
        System.out.println("Edges Sorted");

        /* Kruskal's algorithm */
        for (Edge e : edges) {
            Vertex u = e.getU();
            Vertex v = e.getV();
            if (disjointSet.find(u.getNode()) != disjointSet.find(v.getNode())) {
                      tree.add(e);
                disjointSet.union(u.getNode(), v.getNode());
            }
        }

        System.out.println(sum(tree));

        System.out.println("Done");
    }

    public ArrayList<Edge> getTree(){
        return tree;
    }
    public double sum(List<Edge> edges) {
        double sum = 0;

        for (Edge e : edges) {
            sum += e.getWeight();
        }

        return sum;
    }



    private void buildEdges() {
        edges = new ArrayList<Edge>();
        Vertex a = new Vertex();
        Vertex b = new Vertex();
        for ( Distance d : distances){
             for (Vertex u : vertices){
                if (u.getId().equals(d.getOrigin())){
                    a = u;
                    break;
                }
            }

            for (Vertex v : vertices){
                if (v.getId().equals(d.getDestination())){
                    b = v;
                    break;
                }
            }
            Edge e = new Edge(a, b, d.getDistance());
            edges.add(e);
        }
    }

    private void buildVertices(){
        vertices = new ArrayList<Vertex>();
         for(Library l : libraries){
             vertices.add(new Vertex(l));
         }
    }

    class Edge implements Comparable {
        private double weight;
        private Vertex u, v;

        public Edge(Vertex u, Vertex v) {
            this.u = u;
            this.v = v;
        }

        public Edge(Vertex u, Vertex v, double w) {
            this(u, v);
            this.weight = w;
        }

        public double getWeight() { return this.weight; }
        public void setWeight(double w) { this.weight = w; }
        public Vertex getU() { return this.u; }
        public Vertex getV() { return this.v; }

        public int compareTo(Object o) {
            Edge other = (Edge) o;

            if (this.getWeight() < other.getWeight())
                return -1;
            else if (this.getWeight() > other.getWeight())
                return 1;
            else
                return 0;
        }

        public double sum(List<Edge> edges) {
            double sum = 0;

            for (Edge e : edges) {
                sum += e.getWeight();
            }

            return sum;
        }
    }


    /*
     * Implementation of a node, to be used with the DisjointSet tree
     * structure.
     */
    class Node {
        int rank;      // the approximate count of nodes below this node
        int index;     // a unique index for each node in the tree
        Node parent;

        public Node(int r, int i, Node p) {
            this.rank = r;
            this.index = i;
            this.parent = p;
        }
    }

    class Vertex {
        public String id;
        public Library location;
        private Node n;

        public Vertex ( ){

        }

        public Vertex (Library location){

            id = location.getId();
            this.location = location;
        }

        public String getId(){
            return id;
        }
        public Library getVertexLocation(){
            return location;
        }

        public void setNode(Node n) { this.n = n; }
        public Node getNode() { return this.n; }
    }

    /*
 * A simple implementation of the disjoint-set data structure.
 * Elements of disjoint sets are represented in a tree, in
 * which each node references its parent.
 * A "union by rank" strategy is used to optimize the combination
 * of two trees, making sure to always attach a smaller tree to the
 * root of the larger tree.
 */
    class DisjointSet {
        private int nodeCount = 0;
        private int setCount = 0;

        ArrayList<Node> rootNodes;

        /*
         * Returns the index of the set that n is currently in.
         * The index of the root node of each set uniquely identifies the set.
         * This is used to determine whether two elements are in the
         * same set.
         */
        public int find(Node n) {
            Node current = n;

    /* Ride the pointer up to the root node */
            while (current.parent != null)
                current = current.parent;

            Node root = current;

    /*
     * Ride the pointer up to the root node again, but make each node below
     * a direct child of the root. This alters the tree such that future
     * calls will reach the root more quickly.
     */
            current = n;
            while (current != root) {
                Node temp = current.parent;
                current.parent = root;
                current = temp;
            }

            return root.index;
        }


        /*
         * Combines the sets containing nodes i and j.
         */
        public void union(Node i, Node j) {
            int indexI = find(i);
            int indexJ = find(j);

    /* Are these nodes already part of the same set? */
            if (indexI == indexJ) return;

    /* Get the root nodes of each set (this will run in constant time) */
            Node a = this.rootNodes.get(indexI);
            Node b = this.rootNodes.get(indexJ);

    /* Attach the smaller tree to the root of the larger tree */
            if (a.rank < b.rank) {
                a.parent = b;
            } else if (a.rank > b.rank) {
                b.parent = a;
            } else {
                b.parent = a;
                a.rank++;
            }

            this.setCount--;
        }


        /*
         * Takes a list of n vertices and creates n disjoint singleton sets.
         */
        public void makeSets(List<Vertex> vertices) {
            for (Vertex v : vertices)
                makeSet(v);
        }


        /*
         * Creates a singleton set containing one vertex.
         */
        public void makeSet(Vertex vertex) {
            Node n = new Node(0, rootNodes.size(), null);
            vertex.setNode(n);
            this.rootNodes.add(n);
            this.setCount++;
            this.nodeCount++;
        }


        public DisjointSet(List<Vertex> vertices) {
            this.rootNodes = new ArrayList<Node>(vertices.size());
            makeSets(vertices);
        }
    }
}
