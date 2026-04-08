// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// Find the freqency of each elem in a hashmap
// create buckets of size n+1 and add elements of freq i in bucket i
// iterate over the buckets in descending order and add first k elements in result

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : map.keySet()) {
            if (buckets[map.get(key)] == null)
                buckets[map.get(key)] = new ArrayList();
            buckets[map.get(key)].add(key);
        }

        int[] result = new int[k];
        int idx = 0;
        for (int i = nums.length; i >= 0; i--) {
            if (buckets[i] != null)
                for (int j = 0; j < buckets[i].size() && idx < k; j++)
                    result[idx++] = buckets[i].get(j);
        }

        return result;
    }
}