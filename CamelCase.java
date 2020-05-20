// Time Complexity : O(mxn)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Had some issues with pointers


// Your code here along with comments explaining your approach

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for(int i=0; i<queries.length;i++){
            result.add(compare(queries[i],pattern));
        }
        return result;
    }
    private boolean compare(String query, String pattern){
        int index = 0;
        for(Character ch : query.toCharArray()){
            if(index<pattern.length() && ch==pattern.charAt(index)){
                index++;
            }
            else if(Character.isUpperCase(ch)){
                return false;
            }
        }
        return index==pattern.length();
    }
}