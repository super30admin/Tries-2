// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We form a map to understand the frequencies of the elements first. Next we create another map with frequency
to list of elements and also keep track of min and max frequencies. Now, we start accumulating the elements
from the maximum frequency into out result and iterate till minimum frequency until our k elements are
identified.
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Map<Integer, List<Integer>> frqMap = new HashMap<>();

        //compute the map of frequency to list of numbers
        for(int num : map.keySet()) {
            int frq = map.get(num);
            frqMap.putIfAbsent(frq, new ArrayList<>());
            frqMap.get(frq).add(num);

            min = Math.min(min, frq);
            max = Math.max(max, frq);
        }
        int[] result = new int[k];
        int idx = 0;

        for(int i = max ; i >= min && idx < k ; i--) {
            if(!frqMap.containsKey(i))
                continue;
            List<Integer> list = frqMap.get(i);
            for(int num : list) {
                result[idx++] = num;
            }
        }
        return result;
    }
}