// n Log n-k solution
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        int max = Integer.MIN_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) +1);
            max = Math.max(max, map.get(num));
        }
        List [] buckets = new List[max+1];
        for(int key: map.keySet()){
            int count = map.get(key);
            if(buckets[count] == null){
                buckets[count] = new ArrayList<>();
            }
            buckets[count].add(key);
        }
        for(int i=max; i >= 0 && k>=0; i--){
            List <Integer> li = buckets[i];
            if(li != null){
                for(int j=0; j<li.size() && k>0; j++){
                    result[result.length - k] = li.get(j);
                    k--;
                }
            }
        }
        
        return result;
    }
}
