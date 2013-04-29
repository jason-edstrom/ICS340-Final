import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-Final
 * Date: 4/29/13
 * Time: 12:26 AM
 * Java Class: PACKAGE_NAME
 */
public class Locations {

         private ArrayList<Library> locations;


    public Locations (){
         locations = new ArrayList<Library>();



    }

    public void addLibrary(Library singleLocation){
        locations.add(singleLocation);
    }
}
