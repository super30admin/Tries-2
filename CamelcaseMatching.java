// Time Complexity : O(k(n+m))
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
// Two pointers
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        if(queries==null || queries.length==0)
            return result;
        
        for(String query : queries){
            int i =0;
            boolean flag = false;
            for(int j=0; j<query.length(); j++){
                char c = query.charAt(j);
                if(i< pattern.length() && c == pattern.charAt(i)){
                    i++;
                    if(i == pattern.length()){
                        flag = true;
                    }
                }else if(Character.isUpperCase(c)){
                    flag = false;
                    break;
                }
            }
            result.add(flag);
            
        }
        return result;
    }
}
