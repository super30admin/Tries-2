// Time Complexity : O(n(l + k)), n -> Number of queries, l -> Length of each query, k -> Length of pattern
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem2;

import java.util.ArrayList;
import java.util.List;

public class CamelcaseMatching {
	public List<Boolean> camelMatch(String[] queries, String pattern) {
		if (queries == null || queries.length == 0) {
			return new ArrayList<>();
		}

		List<Boolean> res = new ArrayList<>();

		int pLen = pattern.length();

		for (String query : queries) {
			int i = 0;
			boolean match = false;

			for (int j = 0; j < query.length(); j++) {
				char qCh = query.charAt(j);

				if (i < pLen && qCh == pattern.charAt(i)) {
					i++;
					if (i == pLen) {
						match = true;
					}
				} else if (Character.isUpperCase(qCh)) {
					match = false;
					break;
				}
			}

			res.add(match);
		}

		return res;
	}

	private void print(String[] queries, List<Boolean> list, String pattern) {
		for (int i = 0; i < queries.length; i++) {
			System.out.println(queries[i] + " -> " + (list.get(i) ? "Matches pattern " : "Does not match pattern ")
					+ "\'" + pattern + "\'");
		}
	}

	public static void main(String[] args) {
		CamelcaseMatching obj = new CamelcaseMatching();
		String[] queries = { "FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack" };
		String pattern = "FB";

		List<Boolean> doesQueryMatch = obj.camelMatch(queries, pattern);
		obj.print(queries, doesQueryMatch, pattern);
	}
}
