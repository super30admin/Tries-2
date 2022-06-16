//O(n * n) if we take length of string n
//O(1) SC
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();
        
        if(queries == null || queries.length == 0)  return ans;
        
        for(String s : queries){
            int q = 0, p = 0; //pointers on queries and pattern
            boolean valid = false; // for each query 
     
            while(q < s.length()){
                if(p < pattern .length() &&   s.charAt(q) ==  pattern.charAt(p)){
                   // q++;
                    p++;
                    if(p == pattern .length()){
                        valid = true; // when we reach end of pattern string, setting boolean variable to true
                    }
                } else if(Character.isUpperCase( s.charAt(q))){
                    valid = false;
                    break;
                    //pointer on pattern reach out of bound and we get uppercase letter in Query word
                }
                //Everytime pointer on query word will be incresing
                q++;
            }
            
            ans.add(valid);
        }   
        
        return ans;
    }
}