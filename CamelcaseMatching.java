package tries2;

import java.util.ArrayList;
import java.util.List;

public class CamelcaseMatching {
	//Time Complexity : O(m + n)k, where m is length of string pattern, n of query and k are the number or words in query
	//Space Complexity : O(1)
	//Did this code successfully run on Leetcode : Yes
	//Any problem you faced while coding this : No
	public List<Boolean> camelMatch(String[] queries, String pattern) {
		List<Boolean> result = new ArrayList<>();

		for(String query: queries) {
			boolean flag = false;
			int i=0;
			for(int j=0; j<query.length(); j++) {
				if(i < pattern.length() && query.charAt(j) == pattern.charAt(i)) {
					i++;
					if(i == pattern.length())
						flag = true;
				} else if(Character.isUpperCase(query.charAt(j))) {
					flag = false;
					break;
				}
			}
			result.add(flag);
		}
		return result;
	}
}
