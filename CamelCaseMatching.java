package S30.Tries_2;

import java.util.ArrayList;
import java.util.List;

/*
Time Complexity : O((m+k)n), m is length of pattern, k is average query length, n is number of queries
Space Complexity : O(1)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/

public class CamelCaseMatching {

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for(String query : queries){
            boolean flag = false;
            int i = 0;
            for(int j = 0; j < query.length(); j++){
                char ch = query.charAt(j);
                if(i < pattern.length() && ch == pattern.charAt(i)){
                    i++;
                    if(i == pattern.length()){
                        flag = true;
                    }
                }else if(Character.isUpperCase(ch)){
                    flag = false;
                    break;
                }

            }
            result.add(flag);
        }

        return result;
    }
}
