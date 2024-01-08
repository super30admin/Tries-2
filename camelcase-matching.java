// Time Complexity : P(w*q), where q is the number of queries and w is the maximum length of a word in the queries array 
// Space Complexity : O(q), where q is the number of queries
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : -


// Your code here along with comments explaining your approach

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> retList = new ArrayList<>();      
	    for(String word : queries){
            int i = 0;
            boolean flag = false;           
            for(int j = 0; j < word.length(); j++){
                char ch = word.charAt(j);               
                if(i < pattern.length() && pattern.charAt(i) == ch){
                    i++;
                    if(i >= pattern.length()){
                        flag = true;
                    }
                }else if(Character.isUpperCase(ch)){
                    flag = false;
                    break;
                }
            }            
            retList.add(flag);
        }        
        return retList;
    }
}