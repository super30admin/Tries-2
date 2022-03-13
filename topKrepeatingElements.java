// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// There are 2 approaches:
// approach 1: Map+Priority Queue
// approach 2: 2 Maps

// --------------------------------------------------------------------------------------------------------
// approach 1: Map+Priority Queue
// Time Complexity = O(n), where n=no. of numbers in nums array
// Space Complexity = O(m), m for map of unique elements
// approach 1: Map+Priority Queue
// Time Complexity = O(n), where n=no. of numbers in nums array
// Space Complexity = O(n), n for map
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};

        // map will contain each element and its count
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        // add the frequencies to a min heap/priority queue of size k
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a)-map.get(b));
        for (int key: map.keySet()) {
            pq.add(key);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // add the values with max frequencies from the end to the result
        int[] result = new int[k];
        int index=k-1;
        while (!pq.isEmpty()) {
            int element = pq.poll();
            result[index--] = element;
            if (index < 0) break;
        }

        return result;
    }
}

// --------------------------------------------------------------------------------------------------------
// approach 2: 2 Maps
// Time Complexity = O(n), where n=no. of numbers in nums array
// Space Complexity = O(m), m for map of unique elements
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};

        // map will contain each element and its count
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        // form a frequency map, by swapping key and value in map
        Map<Integer, List<Integer>> freqmap = new HashMap<>();
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        for (int key: map.keySet()) {
            int freq = map.get(key);
            if (!freqmap.containsKey(freq)) {
                freqmap.put(freq, new ArrayList<>());
            }
            freqmap.get(freq).add(key);
            min = Math.min(min, freq);
            max = Math.max(max, freq);
        }

        // traverse the frequency map from max value to min value and break when we get all k values
        int[] result = new int[k];
        int index=0;
        for (int i=max; i>=min; i--) {
            if (freqmap.containsKey(i)) {
                List<Integer> elements = freqmap.get(i);
                for (Integer el: elements) {
                    result[index++] = el;
                    if (index==k) {
                        return result;
                    }
                }
            }
        }

        return result;
    }
}