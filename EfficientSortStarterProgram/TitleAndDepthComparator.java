
import java.util.*; 


public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();
        Double depth1 = q1.getDepth();
        Double depth2 = q2.getDepth();
        
        if (title1.compareTo(title2) == 0) {
            return Double.compare(depth1, depth2);
            //return Double.compare(dist1, dist2);
        } else {
            return title1.compareTo(title2);
        }
        

    }
    
}
