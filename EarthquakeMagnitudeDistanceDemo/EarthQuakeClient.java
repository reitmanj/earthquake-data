
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //return an ArrayList of type QuakeEntry of all the earthquakes from quakeData that have a magnitude larger than magMin
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) { 
        //return an ArrayList of type QuakeEntry of all the earthquakes from quakeData that are less than distMax from the location from.
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
            
    public void dumpCSV(ArrayList<QuakeEntry> list){
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
	
    public void bigQuakes() {
	//print earthquakes above a certain magnitude, and also print the number of such earthquakes
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println(qe);
            }
        }
        
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
    }
	
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }

    }
    
    /*
     * Write the method filterByDepth that has three parameters, an ArrayList of type QuakeEntry named quakeData, a double named minDepth and a
     * double named maxDepth. This method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData whose depth is 
     * between minDepth and maxDepth, exclusive. (Do not include quakes with depth exactly minDepth or maxDepth.)
     */
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for ( QuakeEntry qe : quakeData) {
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);   
            }
        }
        
        return answer;   
    }
    
    /*Write the void method quakesOfDepth that has no parameters to use filterByDepth and print all the earthquakes from a data source whose
     *depth is between a given minimum and maximum value. You should also print out the number of earthquakes found. After writing this method,
     *when you run your program on the file nov20quakedatasmall.atom for quakes with depth between -10000.0 and -5000.0 you should find five
     *such quakes on the output.
    */
    public void quakesOfDepth() {
       ArrayList<QuakeEntry> printOut = new ArrayList<QuakeEntry>();
       EarthQuakeParser parser = new EarthQuakeParser();
       String source = "data/nov20quakedata.atom";
       //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
       ArrayList<QuakeEntry> list = parser.read(source);
       
       printOut = filterByDepth(list, -8000.0, -5000.0);
       
       System.out.println("Read data for " + list.size() + " quakes.");
       System.out.println("Find quakes with depth between -8000.0 and -5000.0");
       // for (QuakeEntry qe : printOut) {
           // System.out.println(qe.toString());
       // }
       System.out.println("Found " + printOut.size() + " quakes that match that criteria");
    }
    
    /*Write the method filterByPhrase that has three parameters, an ArrayList of type QuakeEntry named quakeData, a String named where that 
     *indicates where to search in the title and has one of three values: (“start”, ”end”, or “any”), and a String named phrase, indicating 
     *the phrase to search for in the title of the earthquake. The title of the earthquake can be obtained through the getInfo() method. The 
     *filterByPhrase method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData whose titles have the given 
     *phrase found at location where (“start” means the phrase must start the title, “end” means the phrase must end the title and “any” means 
     *the phrase is a substring anywhere in the title.)
     */
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String search, String phrase) {
        ArrayList<QuakeEntry> byPhrase = new ArrayList<QuakeEntry>();
        phrase = phrase.toLowerCase();
        
        for(QuakeEntry qe : quakeData) {
            String qePhrase = qe.getInfo().toLowerCase();
            
            boolean condition;
            final String start = "start";
            final String end = "end";
            final String any = "any";
            
            switch (search) {
                case start:
                  condition = qePhrase.startsWith(phrase);
                break;
                case end:
                  condition = qePhrase.endsWith(phrase);
                break;
                default:
                  condition = qePhrase.contains(phrase);
            }
               if (condition) {
                  byPhrase.add(qe);
            }
        }
        
        return byPhrase;
    }
    
    public void quakesByPhrase() {
       ArrayList<QuakeEntry> printOut = new ArrayList<QuakeEntry>();
       EarthQuakeParser parser = new EarthQuakeParser();
       String source = "data/nov20quakedata.atom";
       //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
       ArrayList<QuakeEntry> list = parser.read(source);
       
       String search = "any";
       String filter = "Creek";
       printOut = filterByPhrase(list, search, filter);
       
       System.out.println("Read data for " + list.size() + " quakes.");
       System.out.println("Find " + search + " with " + filter + " in title");
       // for (QuakeEntry qe : printOut) {
           // System.out.println(qe.toString());
       // }
       System.out.println("Found " + printOut.size() + " quakes that match that criteria");
    }
}
