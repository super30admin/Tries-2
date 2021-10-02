// Time Complexity : O(n), n -> Length of nums
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
	/********************* Hashmap + PriorityQueue *********************/
	// Time Complexity : O(nlog k), n -> Length of nums, k -> Given k
	// Space Complexity : O(n)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	public int[] topKFrequentPQueue(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return new int[0];
		}

		int[] res = new int[k];
		Map<Integer, Integer> freqMap = new HashMap<>();

		for (int num : nums) {
			freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
		}

		PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> freqMap.get(a) - freqMap.get(b));

		for (int key : freqMap.keySet()) {
			queue.add(key);

			if (queue.size() > k) {
				queue.poll();
			}
		}

		int i = 0;
		while (!queue.isEmpty()) {
			res[i++] = queue.poll();
		}

		return res;
	}

	/********************* Hashmap + Bucket Sort *********************/
	// Time Complexity : O(n), n -> Length of nums
	// Space Complexity : O(n)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	public int[] topKFrequent(int[] nums, int k) {
		if (nums == null || nums.length < k) {
			return nums;
		}

		int[] res = new int[k];
		int max = 0;
		Map<Integer, Integer> freqMap = new HashMap<>();

		for (int num : nums) {
			freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
			max = Math.max(max, freqMap.get(num));
		}

		List<Integer>[] buckets = new List[max + 1];

		for (int key : freqMap.keySet()) {
			int freq = freqMap.get(key);
			if (buckets[freq] == null) {
				buckets[freq] = new ArrayList<>();
			}

			buckets[freq].add(key);
		}

		for (int i = buckets.length - 1; i >= 0 && k > 0; i--) {
			List<Integer> bucket = buckets[i];
			if (buckets[i] == null) {
				continue;
			}

			for (int j = bucket.size() - 1; j >= 0 && k > 0; j--) {
				res[k - 1] = bucket.get(j);
				k--;
			}
		}

		return res;
	}

	private void print(int[] nums) {
		for (int num : nums) {
			System.out.print(num + " ");
		}
	}

	public static void main(String[] args) {
		TopKFrequentElements obj = new TopKFrequentElements();
		int[] nums = { 1, 1, 1, 2, 2, 3 };
		int k = 2;

		int[] freqNums = obj.topKFrequent(nums, k);

		System.out.println("Top \'" + k + "\' frequent elements: ");
		obj.print(freqNums);
	}

}
