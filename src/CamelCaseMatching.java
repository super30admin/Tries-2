import java.util.ArrayList;
import java.util.List;

//Time Complexity : O(N*length of longest word)
//Space Complexity : O(1)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/**
 * Iterate over each query and compare with the letters of the pattern. If extra
 * capitals are found, then return false for that query else true.
 *
 */
class Solution {
	public List<Boolean> camelMatch(String[] queries, String pattern) {
		List<Boolean> list = new ArrayList<>();

		for (var q : queries) {
			int index = 0;
			boolean flag = true;
			for (var c : q.toCharArray()) {
				if (index < pattern.length() && c == pattern.charAt(index)) {
					index++;
					continue;
				}
				if (c >= 'A' && c <= 'Z') {
					if (index >= pattern.length() || c != pattern.charAt(index)) {
						flag = false;
						break;
					}
				}
			}
			flag = flag && index == pattern.length();
			list.add(flag);
		}
		return list;
	}
}