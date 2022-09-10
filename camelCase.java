//O(L * L)
//O(1) 
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        
        if(queries == null || queries.length == 0)  return result;
        
        for(String s : queries){
            int q = 0, p = 0; 
            boolean valid = false;
     
            while(q < s.length()){
                if(p < pattern .length() && s.charAt(q) ==  pattern.charAt(p)){
                 
                    p++;
                    if(p == pattern .length()){
                        valid = true;
                    }
                } else if(Character.isUpperCase( s.charAt(q))){
                    valid = false;
                    break;
                }
                q++;
            }
            
            result.add(valid);
        }   
        
        return result;
    }
}
