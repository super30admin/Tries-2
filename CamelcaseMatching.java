class CamelcaseMatching {
    
    // Time Complexity: O(n*k)
    // Space Complexity: O(1)
    
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        if(queries == null || queries.length == 0 || pattern == null || pattern.length() == 0)
            return result;

        for(String s : queries)
            result.add(helper(s, pattern));
        
        return result;
    }
    
    private boolean helper(String s, String pattern){
        int i = 0;
        int j = 0;
        int n = s.length();
        int m = pattern.length();
        
        while(i < n){
            if(j < m && s.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }else if(s.charAt(i) > 90)
                i++;
            else
                return false;
        }
        return j == m;
    }
}