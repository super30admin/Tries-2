// Approach: Maintain a PriorityQueue (Heap) of size k sorted by frequency of elements
// Time: O(nlogk)
// Space: O(n + k)

import java.util.*;

class TopKFrequentlyRepeatingElements_Heap {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a)-map.get(b));
        for (int key : map.keySet()) {
            pq.add(key);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] result = new int[k];
        for (int i = 0; i<k; i++) {
            result[i] = pq.poll();
        }
        return result;
    }
}