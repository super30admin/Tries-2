// Time Complexity : O(n * k) where n is the no.length of array queries and k is the avg length of each string in array
// Space Complexity : O(1) // creating the result list which won't count
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// We will use 2 pointers to solve this, iterate over all the words in queries
// We will match the characters to with the characters in pattern
// If we find an Upcase letter that is not matching with pattern we mark it false
// Also query reach end of the length and still there are charaters left in pattern mark it false
// Finally return the list of boolean stored.
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for(String query: queries){
            int pi = 0; //patternIndex
            boolean flag = true;
            for(int i = 0; i < query.length(); i++){
                if(pi < pattern.length() && pattern.charAt(pi) == query.charAt(i)){
                    pi++;
                }
                else if(Character.isUpperCase(query.charAt(i))){
                    flag = false;
                    break;
                }
            }
            if(pi == pattern.length() && flag)
                result.add(true);
            else
                result.add(false);
        }
        return result;
    }
}