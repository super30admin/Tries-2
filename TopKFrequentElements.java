import java.util.HashMap;
import java.util.PriorityQueue;

// TC - O(nlogk)
// SC - O(n)

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums){
            map.put(num,map.getOrDefault(num,0) +1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) ->(map.get(a) - map.get(b)));
        for(Integer key : map.keySet()){
            pq.add(key);
            if(pq.size() > k ) pq.poll();
        }
        int[] result = new int[k];
        int i=0;
        while(!pq.isEmpty()){
            result[i] = pq.poll();
            i++;
        }
        return result;
    }
}