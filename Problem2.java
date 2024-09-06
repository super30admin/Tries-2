/*
    LC - 1023. Camelcase Matching
    TC - O(nk)
    SC - O(1) // no extra space is used. bcs we are expect to return the boolean list.
*/

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if(queries == null || queries.length == 0) {
            return new ArrayList<>();
        }
        List<Boolean> result = new ArrayList<>();
        for(String word: queries) {
            int i = 0;
            boolean flag = false;
            for(int j=0; j< word.length(); j++) {
                if(i < pattern.length() && word.charAt(j) == pattern.charAt(i)) {
                    i++;
                }
                else if(Character.isUpperCase(word.charAt(j))) {
                    flag = false;
                    break;
                }
                if(i == pattern.length()) {
                    flag = true;
                }
            }
            result.add(flag);
        }
        return result;
    }
}