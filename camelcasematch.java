//Time Complexity:O(N * (m + n)) where N is the number of queries, m is the length of the query and n is the length of the pattern.
//Space Complexity: O(1)

// Using Two Pointers: for each query, keep i on query and j on pattern; when chars match, i++ and j++.
// If chars don’t match and query[i] is uppercase, return false for this query; otherwise (lowercase mismatch) i++ to skip it.
// After finishing the query, it’s a match iff j == pattern.length(); append that boolean to the result list.

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();

        for(String query : queries){
            result .add(isMatch(query, pattern));
        }

        return result;
    }

    private boolean isMatch(String query, String pattern){

        int i = 0; 
        int j = 0; 

        while(i < query.length()){
            if(j < pattern.length() && query.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }else if(Character.isUpperCase(query.charAt(i))){
                return false;
            }else{
                i++;
            }
        }

        return j == pattern.length(); 
    }
}
