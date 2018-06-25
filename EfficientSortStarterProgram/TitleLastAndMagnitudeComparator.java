
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] wordArray1 = q1.getInfo().split(" ");
        String[] wordArray2 = q2.getInfo().split(" ");
        
        String lastWord1 = wordArray1[wordArray1.length -1];
        String lastWord2 = wordArray2[wordArray2.length -1];
        
        Double mag1 = q1.getMagnitude();
        Double mag2 = q2.getMagnitude();
        
        if (lastWord1.compareTo(lastWord2) == 0) {
            return Double.compare(mag1, mag2);
            //return Double.compare(dist1, dist2);
        } else {
            return lastWord1.compareTo(lastWord2);
        }
        

    }
    
}
