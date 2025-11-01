class Solution {
    List<Boolean> result;
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        this.result = new ArrayList<>();
        for(String query:queries){
            result.add(isMatch(query,pattern));
        }
        return result;
    }
    private Boolean isMatch(String query,String pattern){
        int i=0;//query
        int j=0;//pattern
        while(i<query.length()){
            if(j<pattern.length() && query.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }else if(Character.isLowerCase(query.charAt(i))){
                i++;
            }else{
                return false;
            }
        }
        return j == pattern.length();
    }
}


//TC--->O(N*(m+n))
//SC--->O(1)