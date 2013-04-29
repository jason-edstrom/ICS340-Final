/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-Final
 * Date: 4/29/13
 * Time: 12:26 AM
 * Java Class: PACKAGE_NAME
 */
public class Library {
      private String id;
      private String county;
      private String name;
      private String x;
      private String y;

    public Library(String id, String county, String name, String x, String y){
        this.id = id;
        this.county = county;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getId(){
        return id;
    }

    public String getCounty(){
        return county;
    }

    public String getName(){
        return name;
    }

    public String getX(){
        return x;
    }

    public String getY(){
        return y;
    }
}
