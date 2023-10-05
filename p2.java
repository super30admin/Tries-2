// Time Complexity :O(nl)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        ////go through every word
        for(String word: queries){
            result.add(check(word, pattern));
        }
        return result;
    }
//functon to check if the two strings match using two pointers
    private Boolean check(String word, String pattern){
        int wp = 0;
        int pp = 0;
        while(pp < pattern.length() && wp < word.length()){
            char p = pattern.charAt(pp);
            char w = word.charAt(wp);
            if(p == w){
                pp++;
                wp++;
            }else if(Character.isLowerCase(w)){
                wp++;
            }else if(Character.isUpperCase(p) || Character.isUpperCase(w)){
               return false;
            }
        }

        if(pp != pattern.length()) return false;

        while(wp < word.length()){
            char w = word.charAt(wp++);
            if(Character.isUpperCase(w)) return false;
        }

        return true;
    }
}