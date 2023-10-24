import java.util.HashMap;
import java.util.PriorityQueue;

// Time Complexity : O(n log k)
// Space Complexity : O(k)
// Did this code successfully run on Leetcode : Yes

public class TopKFreqElements {
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));

        for (int key : map.keySet()) {
            pq.add(key);
            if (pq.size() > k) pq.poll();
        }

        int[] result = new int[pq.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = pq.poll();
        }

        return result;
    }
}