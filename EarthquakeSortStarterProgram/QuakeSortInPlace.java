
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        int count = 1;
        
        
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                
                minIdx = i;
                
            }
            
        }
        
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int numChecks = 0;
        
        for (int i=0; i < in.size(); i++) {
            
            if (checkInSortedOrder(in)) {
                break;   
            }
            numChecks += 1;
            int minIdx = getSmallestMagnitude(in,i);
            
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            
            
        } 
        System.out.println("Number of checks = " + numChecks);
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        
        return maxIdx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < 70; i++) {
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i, qmax);
            in.set(maxIdx, qi);
            
        }
    }
    
    public int onePassBubbleSort(ArrayList<QuakeEntry> quakeData) {
        
        int numSorted = 0;
        for (int idx = 0; idx < quakeData.size() - 1; idx++) {
            if(quakeData.get(idx).getMagnitude() > quakeData.get(idx + 1).getMagnitude()) {
                QuakeEntry qLow = quakeData.get(idx);
                QuakeEntry qHigh = quakeData.get(idx + 1);
                quakeData.set(idx, qHigh);
                quakeData.set(idx + 1, qLow);
                numSorted += 1;
            }
        }
        
        return numSorted;
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        
        int numSorts = in.size();
        int numberOfPasses = 0;
        for (int i = 0; i < numSorts - 1; i++) {
            
            int numSorted = onePassBubbleSort(in);
            
            if (numSorted == 0) {
                break;
            }
            numberOfPasses += 1;
            
        }
        
        System.out.println("Number of passes: " + numberOfPasses);
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
     
        for (int idx = 0; idx < quakes.size() - 1; idx++) {
            if(quakes.get(idx).getMagnitude() > quakes.get(idx + 1).getMagnitude()) {
                return false;
            }
        }
        
        return true;
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");
        
        //sortByMagnitudeWithCheck(list);
        //sortByLargestDepth(list);
        // if (checkInSortedOrder(list)) {
            // System.out.println("Already sorted by magnitude");
        // } else {
        sortByMagnitudeWithBubbleSort(list);
        // }
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
}
