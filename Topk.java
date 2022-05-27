// Time Complexity : O(N+MlogK)   
// Space Complexity : O(k)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// We can solve this using map and priority queue
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0)return new int[]{};
        int[] result = new int[k];
        HashMap<Integer,Integer> map = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[1] - b[1]);
    
        for(int n : nums){
            map.put(n, map.getOrDefault(n ,0)+1);
        }
        for(int key : map.keySet()){
            pq.add(new int[]{key, map.get(key)});
            if(pq.size() >k){
                pq.poll();
            }
        }
        for(int j = k-1; j>=0; j--){
            int[] polled = pq.poll();
            result[j] = polled[0];
        }
        return result;
    }
}