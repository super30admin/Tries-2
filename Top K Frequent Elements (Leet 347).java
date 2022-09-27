// Time: O(nlogk)
// Space: O(n)

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null) return new int[0];
        
        HashMap <Integer, Integer> hm = new HashMap<>();
        
        for (int num: nums){
            hm.put(num, hm.getOrDefault(num, 0)+1);
        }
        
        PriorityQueue <Integer> pq = new PriorityQueue<>((a,b)->{
            return hm.get(a) - hm.get(b);
        });
        
        for (int key: hm.keySet()){
            pq.add(key);
            if (pq.size() > k){
                pq.poll();
            }
        }
        
        int [] res = new int [pq.size()];
        
        for(int i = 0; i < res.length; i++){
            res[i] = pq.poll();
        }
        
        return res;
        
    }
}





///////////////////////////////////
// Using Bucket Sort
// Time: O(n)
// Space: O(n)

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null) return new int[0];
        
        HashMap <Integer, Integer> hm = new HashMap<>();
        int max = 0;
        for (int num: nums){
            hm.put(num, hm.getOrDefault(num, 0)+1);
            max = Math.max(max, hm.get(num));
        }
        
        List [] arr = new List [max+1];
        
        for (int key: hm.keySet()){
            if (arr[hm.get(key)] == null){
                arr[hm.get(key)] = new ArrayList<>();
            }
            arr[hm.get(key)].add(key);
        }
        
        int [] res = new int [k];
        
        for(int i = arr.length-1; i>=0; i--){
            List<Integer> bucket = arr[i];
            if (arr[i] != null){
                for(int j = 0; j < arr[i].size(); j++){
                    res[k-1] = bucket.get(j);
                    k--;
                    if (k == 0){
                        return res;
                    }
                }
            }
        }
        
        return new int[0];
        
        
    }
}
