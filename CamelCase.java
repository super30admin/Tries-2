//Time complexity: O(n)
//Space complexity: O(1)
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result=new ArrayList<>(); 
        for(int i=0;i<queries.length;i++)
        {
            int patternP=0;
            Boolean flag=true;
            for(int j=0;j<queries[i].length();j++){
                //System.out.println(queries[i][j]+" "+pattern[patternP]);
                if(patternP<pattern.length() && queries[i].charAt(j)==pattern.charAt(patternP)){
                        patternP+=1;
                    }
            
                else if(Character.isUpperCase(queries[i].charAt(j)))
                {
                    flag=false;
                    break;
                }
                
            }
            if(flag==false)
                result.add(false);
            else if(patternP==pattern.length())
                result.add(true);
            else
            result.add(false);
            
            
        }
        return result;
        
    }
}