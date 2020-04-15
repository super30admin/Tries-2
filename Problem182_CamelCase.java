//Time Complexity: O(n(m + L))

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        //fro each and every string in queries
        for(String query : queries){
            int i = 0;
            boolean flag = false;
            //for every character in the query string
            for(int j = 0; j < query.length(); j++){
                //get the char at 'j'
                char ch = query.charAt(j);
                //if not pattern's last char & matches to pattern_At(i)
                if(i < pattern.length() && ch == pattern.charAt(i)){
                    //increment & if the last character of pattern; return true
                    i++;
                    if(i == pattern.length()){
                        flag = true;
                    }
                    //if upper case when string char; false
                } else if(Character.isUpperCase(ch)){
                    flag = false;
                    break;
                }
            }//add all flag values to result
            result.add(flag);
        }
        return result;
    }
}