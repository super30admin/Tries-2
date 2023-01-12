import java.util.ArrayList;
import java.util.List;

// TC - O(n * max(k,l)) where n is length of queries string array, k is average length of each word in queries and l is the length of pattern string
// SC - O(1)
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for(String query: queries){
            boolean flag = false;
            int p1=0; // pointer on pattern string
            for(int i=0; i<query.length();i++){
                if(p1<pattern.length() && query.charAt(i) == pattern.charAt(p1)){
                    p1++;
                    if(p1 == pattern.length()){
                        flag = true;
                    }
                } else if(Character.isUpperCase(query.charAt(i))){
                    flag = false;
                    break;
                }
            }
            result.add(flag);
        }
        return result;
    }
}