//Time: O(NLogK) | Space: O(N)

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> map.get(a) - map.get(b));
        for(int ele: map.keySet()) {
            pq.add(ele);
            if(pq.size() > k)
                pq.poll();
        }

        int[] result = new int[k];
        for(int i=0;i<k;i++) {
            result[i] = pq.poll();
        }
        return result;
    }
}