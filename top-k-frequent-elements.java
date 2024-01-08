// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : -


// Your code here along with comments explaining your approach

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for(int key: map.keySet()){
            pq.add(key);
            if(pq.size() > k){
                pq.poll();
            }
        }
        int[] result = new int[k];
        for(int i = 0; i < k; i++){
            result[i] = pq.poll();
        }
        return result;
    }
}