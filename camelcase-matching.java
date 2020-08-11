//let m is the average length of word and n is the no. of words
//k is the length of the pattern
//time complexity O(m x n) since k is the constant and negligible
//space complexity O(1)

class Solution {
    List<Boolean> result;
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        result = new ArrayList<>();
        if(queries == null || queries.length == 0) return result;
        for(String query: queries){
            int i = 0; boolean flag = false;
            for(int j = 0; j < query.length(); j++){
                if(i < pattern.length() && query.charAt(j) == pattern.charAt(i)){
                    i++;
                    if(i == pattern.length()) flag = true;
                } else if(Character.isUpperCase(query.charAt(j))){
                    flag = false;
                    break;
                }
            }
            result.add(flag);
        }
        return result;
    }
}
