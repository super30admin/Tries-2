// Using PriorityQueue
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int [] result = new int[k];
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i< nums.length; i++){
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> countMap.get(a) - countMap.get(b));
        for (int key: countMap.keySet()){
            pq.add(key);
            if (pq.size() > k){
                pq.poll();
            }
        }
        int i = 0;
        while(!pq.isEmpty()){
            result[i] = pq.poll();
            i++;
        }
        return result;       
    }
}

// Time Complexity: O(n* log(k))
// Space Complexity: O(n)