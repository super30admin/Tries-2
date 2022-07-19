/*
Problem: https://leetcode.com/problems/top-k-frequent-elements/
TC: O(n log k)
*/

// Approach 1: O(n log k)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return new int[0];
        
        HashMap<Integer, Integer> numberFreqMap = new HashMap<>();
        for (int num : nums) {
            if (!numberFreqMap.containsKey(num)) {
                numberFreqMap.put(num, 0);
            }
            numberFreqMap.put(num, numberFreqMap.get(num) + 1);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> {
            if (i1[1] == i2[1]) {
                return i2[0] - i1[0];
            } else {
                return i1[1] - i2[1];
            }
        });
        
        for (int num : numberFreqMap.keySet()) {
            int freq = numberFreqMap.get(num);
            pq.offer(new int[]{num, freq});
            
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        int result[] = new int[k];
        int index = 0;
        
        while (!pq.isEmpty()) {
            result[index++] = pq.poll()[0];
        }
        
        return result;
    }
}

// Approach 2: O(n)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> numFreq = new HashMap<>();
        int max = 0;
        
        
        for (int i = 0; i < nums.length; ++i) {
            if (!numFreq.containsKey(nums[i])) {
                numFreq.put(nums[i], 0);
            }
            numFreq.put(nums[i], numFreq.get(nums[i]) + 1);
            max = Math.max(max, numFreq.get(nums[i]));
        }
        
        List<Integer> buckets[] = new List[max + 1];
        
        for (int num : numFreq.keySet()) {
            int freq = numFreq.get(num);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }
        
        List<Integer> result = new ArrayList<>();
        int count = k;
        for (int i = max; i >= 0 && count > 0; --i) {
            if (buckets[i] != null) {
                result.addAll(buckets[i]);
                count -= buckets[i].size();
            }
        }
        int res[] = new int[k];
        
        for (int i = 0; i < k; ++i) {
            res[i] = result.get(i);
        }
        
        return res;
    }
}