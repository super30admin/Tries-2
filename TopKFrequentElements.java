// Time Complexity : O(N)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

//We make use of heaps, sorted based on the frequency to find the Top K Frequent Elements`

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        Map<Integer,Integer> map = new HashMap<>();
        
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0)+1);
 
        PriorityQueue<Integer> pq = new PriorityQueue((a,b) -> map.get(b)-map.get(a));
    
        int i=0;
        int[] result = new int[k];
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            pq.add(entry.getKey());
            i++;
            if (i>map.size()-k)
                result[i-(map.size()-k)-1] = pq.poll();
        }
        
        return result;
    }
}