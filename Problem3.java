class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums.length == 1){
            return nums;
        }
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> pq = new PriorityQueue<>((a,b)-> map.get(a) - map.get(b));
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0)+ 1);
        }
        for(int num: map.keySet()){
            pq.add(num);
            if(pq.size() > k){
                pq.poll();
            }
        }
        int[] result = new int[k];
        for(int i = 0; i < k; i++){
            result[i] = pq.poll();
        }
        return result;
    }
}