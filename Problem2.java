import java.util.ArrayList;
import java.util.List;
/*
Match Camelcases
approach: use 2 pointers and check for upper case letters
time: n*max(pattern length, avg query len)
space: O(1)
 */
public class Problem2 {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>();
        for (String query:queries) {
            boolean flag = false;
            int p1 = 0, p2 = 0;
            while (p1<query.length()) {

                if (p2<pattern.length() && pattern.charAt(p2)==query.charAt(p1)) {
                    p2++;
                    if (p2==pattern.length()) flag = true;
                }
                else if (Character.isUpperCase(query.charAt(p1))) {
                    flag = false;
                    break;
                }
                p1++;
            }
            res.add(flag);
        }
        return res;
    }
}
