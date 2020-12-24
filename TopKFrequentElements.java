// Time Complexity : O(N log k), where N is #nums
// Space Complexity : O(N), map to keep element frequency count  
// Did this code successfully run on Leetcode : yes

// Your code here along with comments explaining your approach
// build element frequncy count, maintain heap of top k elements, min heap, extract all elements in heap to form result

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();
        
        for(int n : nums){
            count.put(n, count.getOrDefault(n, 0)+1);
        }
        
        Queue<Integer> pq = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));
        
        for(int n : count.keySet()){
            pq.add(n);
            
            if(pq.size()>k)
                pq.poll();
        }
        
        int[] res = new int[k];
        int i=k-1;
        while(!pq.isEmpty()){
            res[i] = pq.poll();
            i--;
        }
        
        return res;
    }
}