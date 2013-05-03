import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphView extends javax.swing.JPanel {
  private Graph  graph;
  private int height = getHeight();
  private int width = getWidth();

  public GraphView(Graph graph) {
    this.graph = graph;
  }
  
  protected void paintComponent(java.awt.Graphics g) {
    super.paintComponent(g);
      //g.translate(400,250);
      //g.drawLine(400,250,10,10);

    // Draw vertices
    ArrayList<Vertex> vertices = graph.getVertices();
     for (Vertex v : vertices){
        Random random = new Random();

         //v.setDx(random.nextInt() * 100);
        // v.setDy(random.nextInt() * 100);

        v.setGPSPixel(getHeight(), getWidth() );
         int index = vertices.indexOf(v);
         v.setDx((v.getDx() + (index * 10)));
         v.setDy((v.getDy() + (index * 10)));

     }

    for (int i = 0; i < graph.getVertexMapSize(); i++) {
      int x = vertices.get(i).getDx();
      int y = vertices.get(i).getDy();
      String name = vertices.get(i).getVertexLocation().getName() + " : " + vertices.get(i).getId();
      
      g.fillOval(x - 8, y - 8, 16, 16); // Display a vertex
      g.drawString(name, x - 12, y - 12); // Display the name
    }
    
    // Draw edges for pair of vertices
      ArrayList<GEdge> edges = graph.getEdges();
    for (int i = 0; i < graph.getVertexMapSize(); i++) {
      //java.util.List<Integer> neighbors = graph.getNeighbors(i);
      for (int j = 0; j < edges.size(); j++) {
        //int v = neighbors.get(j);
        Vertex v1 = vertices.get(vertices.indexOf(edges.get(j).origin));
        Vertex v2 = vertices.get(vertices.indexOf(edges.get(j).dest));
        int x1 = v1.getX();
        int y1 = v1.getY();
        int x2 = v2.getX();
        int y2 = v2.getY();


          //int x1 = edges.get(j).ge
        g.drawLine(x1, y1, x2, y2); // Draw an edge for (i, v)
      }
    }
  }
}
