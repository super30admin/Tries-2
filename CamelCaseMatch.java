/**

 * CamelCase pattern matching using two pointers to compare query with pattern.

 * We greedily match pattern characters in order while allowing lowercase skips in query.

 * Returns true only if full pattern is matched and no invalid uppercase characters remain.

 *

 * Time Complexity: O(Q * N), where Q = number of queries and N = average query length

 * Space Complexity: O(1) excluding output list

 */
public class CamelCaseMatch {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();

        for(String query : queries){
            result .add(isMatch(query, pattern));
        }

        return result;
    }

    private boolean isMatch(String query, String pattern){

        int i = 0; // query;
        int j = 0; // pattern

        while(i < query.length()){
            if(j < pattern.length() && query.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }else if(Character.isUpperCase(query.charAt(i))){
                return false;
            }else{
                i++;
            }
        }

        return j == pattern.length(); 
    }
}
