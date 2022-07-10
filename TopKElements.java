class Solution {
  // Using priority queue  
  // TC : O(n * log k) n - nums.length
  // SC : O(k)
  public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0) return new int[]{};
        
        int[] result = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        
        for(int num : nums) {
            map.put(num, map.getOrDefault(num,0) + 1);
        }
        
        map.forEach((key,value) -> {
            int[] tuple = new int[]{key, value};
            
            q.add(tuple);
            
            if(q.size() > k) q.poll();
        });
        
        int i=0;
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            result[i++] = temp[0];
        }
        
        return result;
    }
}
