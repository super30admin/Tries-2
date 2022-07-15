// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

public class Solution {

    public int[] topKFrequent(int[] nums, int k) {
     
	int[] result = new int[k];
	Map<Integer, Integer> map = new HashMap<>();
	int max = Integer.MIN_VALUE;


	for(int n: nums) {
		map.put(n, map.getOrDefault(n, 0) + 1);
        
        max = Math.max(max, map.get(n));
	}

	List[] buckets = new List[max + 1];

	for(int key: map.keySet()) {
		
		if(buckets[map.get(key)] == null) {
			buckets[map.get(key)] = new ArrayList<>();		
		}

		buckets[map.get(key)].add(key);
        
	}

	for(int i = max; i >= 0 && k > 0; i--) {
	
		if(buckets[i] != null) {

			List<Integer> list = buckets[i];

			for(int j = 0; j < list.size() && k >= 0; j++) {
				result[--k] = list.get(j);
			}
		}
	}
		return result;
	}
}
