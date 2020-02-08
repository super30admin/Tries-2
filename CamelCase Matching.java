TC: O(m*n)
SC: O(n)

Runtime: 0 ms, faster than 100.00% of Java online submissions for Camelcase Matching.
Memory Usage: 38 MB, less than 9.52% of Java online submissions for Camelcase Matching.

Approach: Two pointer for pattern and the query. If character matches increment pattern pointer as long as 
it is less than size of pattern. Whenever a query char doesnot match with current pattern char and it is
uppercase, the flag becomes false.


class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {

        List<Boolean> retList = new ArrayList<>();
        
        for(String query : queries){
            int i=0;
            boolean flag= false;
            
            for(int j =0; j< query.length(); j++){
                char ch = query.charAt(j);
                
                if(i < pattern.length() && ch == pattern.charAt(i)){
                    i++;
                    if(i>= pattern.length()) flag = true;
                }
                else if(Character.isUpperCase(ch)){
                    flag = false;
                    break;
                }
            }
            retList.add(flag);
        }
    
        return retList;
        
    }
}
