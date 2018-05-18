

public class PhraseFilter implements Filter {
    private String searchType;
    private String phrase;
    
    public PhraseFilter(String location, String phrase) {
        searchType = location;
        phrase = phrase;
    }
    
    
    
    public  boolean satisfies(QuakeEntry qe) {
        boolean condition;
        final String start = "start";
        final String end = "end";
            
        switch (searchType) {
            case start:
              condition = qe.getInfo().startsWith(phrase);
              break;
            case end:
              condition = qe.getInfo().endsWith(phrase);
              break;
            default:
              condition = qe.getInfo().contains(phrase);
        }
        return condition;
    }
}
