
import java.util.*;

public class LargestQuakes {
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> largest = getLargest(list, 5);
        
        //System.out.println("Index of greatest mag quake = " + indexOfLargest(list));
        System.out.println("Largest 5 quakes in list");
        for (QuakeEntry qe : largest) {
            System.out.println(qe);   
        }
    
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> list) {
        int index = 0;
        double holdMagnitude = 0.0;
        
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > holdMagnitude) {
                holdMagnitude = qe.getMagnitude();
                index = list.indexOf(qe);
            }
        }
        return index;
    }
    //getLargest that has two parameters, an ArrayList of type QuakeEntry named quakeData and an integer named howMany.
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> list, int howMany) {
       ArrayList<QuakeEntry> largest = new ArrayList<QuakeEntry>();
       
       for (int i = 0; i < howMany; i++) {
           int index = 0;
           index = indexOfLargest(list);
           largest.add(list.get(index));
           list.remove(index);
       }
       
       return largest;
    }
}
