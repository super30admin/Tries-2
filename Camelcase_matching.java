// Time Complexity : O(max(m,k) *n) - n: no of strings in query, K- max size of the query, m :pattern length
// Space Complexity : O(1)

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        
        // Result
        List<Boolean> result = new ArrayList<>();
        
        // Iterate through each string 
        for (String query : queries){
            
            boolean flag = false;
            int p = 0; // pointer to map the character in the pattern
            
            // Iterate through each character
            for (int i = 0; i < query.length(); i++){
                
                // If char matches
                if (p < pattern.length() && pattern.charAt(p) == query.charAt(i)){
                    
                    // Check for next character
                    p++;
                    
                    // If p reaches end , then the pattern matched
                    if (p == pattern.length() ){
                        flag = true;
                    }
                        
                }
                
                // If not matched or if a different upper case exists in query
                else if (query.charAt(i) >= 65 && query.charAt(i) <= 90){
                    
                    flag = false;
                    break; //  no need to check other characters in the query.
                }
            }
            
            // add the flag value in the result for that query.
            result.add(flag);
        }
        
        return result;
    }
}