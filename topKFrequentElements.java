//O(n * log n) 
//SC- O(n) 
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0)    return new int[0];
        
        ArrayList<Integer> result = new ArrayList<>();
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = Integer.MAX_VALUE;
       
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
            max = Math.max(max, map.get(i));
        }
        
       PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> b[1] - a[1] );
        
        for(Integer i : map.keySet()){
            int[] temp = new int[2];
            temp[0] = i;
            temp[1] = map.get(i);
            pq.add(temp);
        }
        
      
        while(k-- > 0){
            int[] pair = pq.poll();
            result.add(pair[0]);
        }
      
        int[] arr = new int[result.size()];
        for(int i = 0; i< arr.length; i++){
            arr[i] = result.get(i);
        }
        return arr;

    }
}
