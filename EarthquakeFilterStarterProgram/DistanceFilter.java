

public class DistanceFilter implements Filter{
    private float maxD;
    private String inLocation;
    
    public DistanceFilter(float maxDistance, String location) {
        maxD = maxDistance;
        inLocation = location;
    }
    
    public  boolean satisfies(QuakeEntry qe) {
        //get location of japan(35.42, 139.43)
        Location from = new Location(35.42, 139.43);
        //find distance to quake from Japan
        float distanceTo = from.distanceTo(qe.getLocation());
        
        String str = qe.getInfo().toString();
        //System.out.println( distanceTo + " < " + maxD + " " + qe.getInfo());
        
        return distanceTo < maxD && str.endsWith(inLocation) ? true : false;
        
    }
}
