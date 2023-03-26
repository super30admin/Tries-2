class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int res[]=new int[k];

        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        PriorityQueue<Integer> q = new PriorityQueue<>((v1,v2)->map.get(v2)-map.get(v1));
        ;
        for(Integer key : map.keySet()){
             q.add(key);
        }
        for(int i=0; i<k; i++){
            res[i] = q.poll();
        }
        return res;
    }
}