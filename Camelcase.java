// Time Complexity : O(n(k+m))   
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// we can solve this using two pointers
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if(queries== null  || queries.length == 0 )return new ArrayList<>();
        List<Boolean> result = new ArrayList<>();
        
        for(String word : queries){
            int i =0;
            boolean matches = false;
            for(int j =0; j<word.length(); j++){
                if(i<pattern.length() && pattern.charAt(i) == word.charAt(j)){
                    i++;
                    if(i==pattern.length()){
                        matches = true;
                    }
                }
                else if(Character.isUpperCase(word.charAt(j))){
                    matches = false;
                    break;
                }
            }
            result.add(matches);
        }
        return result;
    }
}