import java.util.ArrayList;
import java.util.List;
/*
Time Complexity: O(n*m), N is the length of queries array and pattern is the length of string pattern
Space Complexity: O(1)
Run on leetcode: Yes
Any difficulties: no

Approach:
1. If character query[i] matches character in the pattern[j] then incrementing pattern pointer by one,
2. if does not match keep continue
3. If characters does not match and query is not capitalized return false
 */
public class MatchCamelCase {
    public static List<Boolean> matchCamelCase(String[] queries, String pattern){
        List<Boolean> result = new ArrayList<>();

        char[] patternArr = pattern.toCharArray();

        for(String query: queries){
            boolean isMatch = match(query.toCharArray(), patternArr);
            result.add(isMatch);
        }
        return result;
    }

    public static boolean match(char[] queryArr, char[] patternArr){
        int j = 0;

        for(int i = 0; i<queryArr.length; i++){
            if(j<patternArr.length && patternArr[j] == queryArr[i]){
                j++;
            }else if(queryArr[i]>= 'A' && queryArr[i]<='Z'){
                return false;
            }
        }
        return j == patternArr.length;
    }

    public static void main(String[] args){
        System.out.println("Camel Case Matching: "+
                matchCamelCase(new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"}, "FB"));
        System.out.println("Camel Case Matching: "+
                matchCamelCase(new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"}, "FoBa"));
        System.out.println("Camel Case Matching: "+
                matchCamelCase(new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"}, "FoBaT"));
    }
}
