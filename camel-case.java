// Time, Space - O(NK), O(1)

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if(queries == null || queries.length == 0) {
            return new ArrayList<>();
        }
        List<Boolean> result = new ArrayList<>();
        for(String query : queries) {
            int i=0;
            boolean flag = false;
            for(int j=0;j<query.length();j++) {
                char c = query.charAt(j);
                if(i < pattern.length() && pattern.charAt(i) == c) {
                    i++;
                    if(i==pattern.length()) {
                        flag = true;
                    }                    
                }
                else if(Character.isUpperCase(c)){
                    flag = false;
                    break;
                }
            }
            result.add(flag);
        }
        
        return result;
        
    }
}
