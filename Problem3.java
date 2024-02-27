// Time Complexity : O(nlogk)
// Space Complexity : O(k)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
       Map<Integer,Integer> map = new HashMap<>();
       Queue<Integer> pq = new PriorityQueue<>((a,b)->map.get(a)-map.get(b));
       for(int n : nums){
           map.put(n,map.getOrDefault(n,0)+1);
       }
       for(int x: map.keySet()){
           pq.add(x);
           if(pq.size() > k)
            pq.poll();
       }
       int[] ans = new int[k];
       for(int i = k-1; i >=0 ;i--)
            ans[i]=pq.poll();
        return ans; 
    }
}