//Time Complexity O(M*N)query M pattern N
//Space Complexity O(1) constant array of size 26
//Leetcode tested

import java.util.ArrayList;
import java.util.List;

public class CamelCaseMatching {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        int[] upperCase = new int[26];
        int[] lowerCase = new int[26];
        for (int i = 0; i < pattern.length(); i++) {
            char c= pattern.charAt(i);
            if(Character.isUpperCase(c)){
                upperCase[c - 'A']++;
            }else{
                lowerCase[c - 'a']++;
            }
        }
        for (int i = 0; i < queries.length; i++) {
            if(match(queries[i],pattern,upperCase,lowerCase)){
                result.add(true);
            }else {
                result.add(false);
            }
        }
        return result;
    }
    public boolean match(String query,String pattern,int[] upperCase,int[] lowerCase){
        int[] qUpperCase = new int[26];
        int[] qLowerCase = new int[26];
        for (int i = 0; i < query.length(); i++) {
            char c= query.charAt(i);
            if(Character.isUpperCase(c)){
                qUpperCase[c - 'A']++;
            }else{
                qLowerCase[c - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if(qUpperCase[i]!=upperCase[i]) return false;
            if(lowerCase[i] > qLowerCase[i]) return false;
        }
        int queryIndex=0, patternIndex=0;
        while (queryIndex<query.length() && patternIndex<pattern.length()){
            if(query.charAt(queryIndex) == pattern.charAt(patternIndex)){
                queryIndex++;
                patternIndex++;
            }else{
                queryIndex++;
            }
        }
        if(patternIndex!=pattern.length()) return false;

        return true;
    }
}
