//TC: O(nk), or (O (n(k+l)), where l is the length of the pattern, but outer loop runs mostly for the queries array. n is length of the queries array and k is average length of a string
//SC: O(1) 
//Running on Leetcode: yes
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        if(queries == null || queries.length == 0) return result;
        for(String query : queries) {
            int i = 0; // pointer in pattern
            boolean flag = false;
            for(int j = 0; j < query.length(); j++) {
                char qChar = query.charAt(j);
                if(i < pattern.length() && qChar == pattern.charAt(i)) {
                    i++;
                    if(i == pattern.length()) {
                        flag = true;
                    }
                }
                else if(Character.isUpperCase(qChar)) {
                    flag = false;
                    break;
                }
            }
            result.add(flag);
        }
        return result;
    }
}
