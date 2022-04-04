// Time Complexity : O(m+n)*k ; m & n are lengths of query and pattern and k is no. of queries
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes

import java.util.ArrayList;
import java.util.List;

public class CamelCaseMatching {

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for(String query: queries){
            boolean flag = false;
            int i = 0; //pattern ptr
            for(int j = 0; j < query.length(); j++){
                //check if pattern is completly matched or matches char
                if(i < pattern.length() && pattern.charAt(i) == query.charAt(j)){
                    i++;
                    if(i == pattern.length()){
                        flag = true;
                    }
                }else if(Character.isUpperCase(query.charAt(j))){
                    //undo flag coz we found extra upper case letter in string
                    //even after pattern was matched completely
                    flag = false;
                    break;
                }
            }
            result.add(flag);
        }
        return result;
    }
}
