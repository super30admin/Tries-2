import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Time Complexity : O(N)
//Space Complexity : O(N)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/**
 * Create a map of number to its frequency. Then put them in a bucket based on
 * their respective frequency. Iterate from the end of these buckets to get the
 * k frequent elements.
 *
 */
class Solution {
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		List<Integer>[] buckets = new ArrayList[nums.length + 1];
		for (int i = 0; i < buckets.length; i++)
			buckets[i] = new ArrayList<>();

		for (int key : map.keySet()) {
			buckets[map.get(key)].add(key);
		}

		List<Integer> flattened = new ArrayList<>();
		for (int i = buckets.length - 1; i >= 0; i--) {
			for (int n : buckets[i])
				flattened.add(n);
		}

		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = flattened.get(i);
		}

		return res;
	}
}