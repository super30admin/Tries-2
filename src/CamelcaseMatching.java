import java.util.*;

/*
 * Time Complexity of the algorithm is O(n * k) where n average length of each string in queries and K is length of queries array.
 * space Complexity is constant.
 */

public class CamelcaseMatching {

	public List<Boolean> camelMatch(String[] queries, String pattern) {

		if (queries == null || queries.length == 0)
			return new ArrayList<>();

		List<Boolean> result = new ArrayList();

		for (String query : queries) {

			int i = 0;
			boolean flag = false;
			for (int j = 0; j < query.length(); j++) {

				char c = query.charAt(j);

				if (i < pattern.length() && c == pattern.charAt(i)) {
					i++;

					if (i == pattern.length()) {
						flag = true;

					}

				} else if (Character.isUpperCase(c)) {
					flag = false;
					break;

				}

			}

			result.add(flag);

		}

		return result;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
