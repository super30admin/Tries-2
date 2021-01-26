package S30.Tries_2;

/* Heap Solution
Time Complexity : O(NlogK)
Space Complexity : O(N) - map size
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/

/* Bucket Sort Solution
Time Complexity : O(N)
Space Complexity : O(N) - map size
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class TopKFrequentElements {

    public int[] topKFrequentHeapSort(int[] nums, int k) {

        int[] result = new int[k];
        if(nums == null || nums.length == 0) return result;
        HashMap<Integer,Integer> map = new HashMap<>();

        //build frequency map
        for(int num : nums){
            map.put(num, map.getOrDefault(num,0)+1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));

        for(Integer key : map.keySet()){
            queue.offer(key);
            if(queue.size() > k) queue.poll();
        }

        while(!queue.isEmpty()){
            result[k-1] = queue.poll();
            k--;
        }

        return result;
    }

    public int[] topKFrequentBucketSort(int[] nums, int k) {

        int[] result = new int[k];
        if(nums == null || nums.length == 0) return result;
        HashMap<Integer,Integer> map = new HashMap<>();

        //build frequency map
        for(int num : nums){
            map.put(num, map.getOrDefault(num,0)+1);
        }

        //Do bucket sort
        List[] buckets = new List[nums.length+1];

        for(Integer key : map.keySet()){
            int freq = map.get(key);
            if(buckets[freq] == null){
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }

        for(int i = buckets.length - 1; i >= 0 && k > 0; i--){
            List<Integer> temp = buckets[i];
            if(temp != null){
                for(int j = 0; j < temp.size() && k > 0; j++){
                    result[k-1] = temp.get(j);
                    k--;
                }
            }
        }

        return result;
    }
}
