/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-Final
 * Date: 4/30/13
 * Time: 12:41 AM
 * Java Class: PACKAGE_NAME
 */
public class Distance {
    private String origin;
    private String destination;
    private double distance;

    public Distance(String origin, String destination, double distance){
         this.origin = origin;
         this.destination = destination;
         this.distance = distance;
    }

    public String getOrigin(){
        return origin;
    }

    public String getDestination(){
        return destination;
    }

    public double getDistance(){
        return distance;
    }
}
