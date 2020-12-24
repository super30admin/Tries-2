// Time Complexity : O(C), where C is total no of chars across all query strings
// Space Complexity : O(1), no extra space apart from output  
// Did this code successfully run on Leetcode : yes

// Your code here along with comments explaining your approach
// Use 2 pointers to compare chars for each query string vs pattern 
// i=0, j=0, if query[i]==pattern[j], i++, j++
// else if isLowerCase(query[i]) i++
// else if isUpperCase(query[i]) return false as not a match
// if both pointers reach end return isMatch = true 

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> output = new ArrayList<>(queries.length);
        
        for(String query : queries){
            output.add(isMatch(query, pattern));
        }
        
        return output;
    }
    
    private boolean isMatch(String query, String pattern){
        int j=0;
        
        for(int i=0; i<query.length(); i++){
            if(j<pattern.length() && query.charAt(i)==pattern.charAt(j)){
                j++;
            }
            else if(Character.isUpperCase(query.charAt(i))){
                return false;
            }
        }
        
        return j==pattern.length();
    }
}