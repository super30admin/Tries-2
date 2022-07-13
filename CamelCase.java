import java.util.ArrayList;
import java.util.List;

public class CamelCase {
    // Two pointer approach
    // TC : O(n * (m + k))
    //      n - number of strings in queries array
    //      m - length of the pattern string
    //      k - average length of each string
    // SC : O(1)
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if(queries == null || queries.length == 0) return new ArrayList<>();

       List<Boolean> result = new ArrayList<>();

       for(String query : queries) {
           int i=0;
           boolean isMatching = false;
           for(int j=0; j < query.length(); j++) {
                if(i < pattern.length() && pattern.charAt(i) == query.charAt(j)) {
                    i++;
                    if(i == pattern.length()) {
                        isMatching = true;
                    }
                }else if(Character.isUpperCase(query.charAt(j))) {
                    isMatching = false;
                    break;
                }
           }
           result.add(isMatching);
       }
       return result;
    }
}
