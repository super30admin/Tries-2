// Time Complexity : O(m  + k)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

public class Solution {

	public List<Boolean> camelMatch(String[] queries, String pattern) {
		
		if(queries == null || queries.length == 0) {
				return new ArrayList<>();
		}

		List<Boolean> result = new ArrayList<>();

		for(String query : queries) {
			
			int i = 0;
			boolean match = false;
			for(int j = 0; j < query.length(); j++) {
				
				if(i < pattern.length() && pattern.charAt(i) == query.charAt(j)) {
					
                    i++;

					if (i == pattern.length()) {
						match = true;
					}
                    
				} else if(Character.isUpperCase(query.charAt(j))) {
                    match = false;
                    break;
                }
			}

			result.add(match);
		}

		return result;
	}
}
