class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int [] result = new int [k];
        int l = k;
        
        if(nums == null || nums.length == 0) return result;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num:nums){
            map.put(num, map.getOrDefault(num, 0)+1); 
        }
        
        List<Integer> [] buckets = new List[nums.length+1];
        
        for(int key: map.keySet()){
            int freq = map.get(key);
            if(buckets[freq] == null){
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }
        
        for(int i = nums.length; i>=0 && k > 0; i--){
            if(buckets[i] != null){
                List<Integer> li = buckets[i];
                for(int j = 0; j < li.size() && k > 0; j++){
                    result[l-k] = li.get(j);
                    k--;
                }
            }
        }
        return result;
    }
}

//TC:O(n)
//SC: O(n)
