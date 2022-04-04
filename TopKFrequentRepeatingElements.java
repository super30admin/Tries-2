// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentRepeatingElements {

    public int[] topKFrequent(int[] nums, int k) {
        //null
        if (nums == null) return new int[0];

        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) { //O(n)
            map.put(num, map.getOrDefault(num, 0) + 1);
            max = Math.max(max, map.get(num));
        }

        List<Integer>[] buckets = new ArrayList[max + 1];

        for (int key : map.keySet()) { //O(n)
            int f = map.get(key);
            if (buckets[f] == null)
                buckets[f] = new ArrayList<>();
            buckets[f].add(key);
        }

        int[] result = new int[k];
        for (int i = max; i >= 0 && k > 0; i--) { //O(k)
            List<Integer> li = buckets[i];
            if (li != null) {
                for (int j = 0; j < li.size() && k > 0; j++) {
                    // result[k - 1] = li.get(j);
                    result[result.length - k] = li.get(j); // get result in sorted order
                    k--;
                }
            }
        }
        return result;
    }

}
