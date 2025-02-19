import java.util.HashMap;
import java.util.PriorityQueue;

public class problem3 {
    // Time Complexity: O(nlog(k))
    // Space Complexity : O(n)

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0) + 1);
        }
        PriorityQueue<Integer> pq= new PriorityQueue<>((a,b)-> map.get(a)-map.get(b));
        for (int num: map.keySet())
        {
            pq.add(num);
            if (pq.size() > k){pq.poll();}
        }
        int[] res = new int[k];
        int idx=0;
        while (!pq.isEmpty()){
            res[idx++]=pq.poll();
            idx++;
        }
        return res;
    }

}
