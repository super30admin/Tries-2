// Time Complexity: O(n)
// Space Complexity: O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

import java.util.*;
;class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            max = Math.max(max, map.get(num));
        }
        List[] buckets = new List[max];
        for(int key: map.keySet()) {
            int count = map.get(key);
            if(buckets[count - 1] == null) {
                buckets[count - 1] = new ArrayList<>();
            }
            buckets[count - 1].add(key);
        }
        for(int i = max - 1; i >= 0 && k >= 0; i--) {
            List<Integer> li = buckets[i];
            if(li != null) {
                for(int j = 0; j < li.size() && k > 0; j++) {
                    result[result.length - k] = li.get(j);
                    k--;
                }
            }
        }
        
        return result;
    }
}