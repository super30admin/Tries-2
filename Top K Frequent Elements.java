

//using min heap - tc -O(nlogk) sc- O(k)
// class Solution {
//     public int[] topKFrequent(int[] nums, int k) {

//         HashMap<Integer, Integer> map = new HashMap<>();

//         for(int num : nums){
//             map.put(num, map.getOrDefault(num, 0)+1 );
//         }

//         PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) ->{
//             return map.get(a) - map.get(b);
//         });

//         for(int key : map.keySet()){
//             pq.add(key);
//             if(pq.size() > k) pq.poll();
//         }
            
//         int[] result = new int[k];
//         int idx = 0;

//         while(!pq.isEmpty()){
//             result[idx] =pq.poll();
//             idx++;
//         }
//         return result;
        
//     }
// }

// 2 hashmap solution tc- O(2n) sc-O(n)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1 );
        }
        
        HashMap<Integer, List<Integer>> freqToNumMap = new HashMap<>();
        int min = 0, max = 0;

        for(int key : map.keySet()){
           int frq = map.get(key);
           if(!freqToNumMap.containsKey(frq)){
               freqToNumMap.put(frq, new ArrayList<>());
           }
           freqToNumMap.get(frq).add(key);
           min = Math.min(min, frq);
           max = Math.max(max, frq);
        }
            
        int[] result = new int[k];
        int idx = 0;

       for(int i = max; i >= min && idx < k ; i--){
        if(!freqToNumMap.containsKey(i)) continue;
        List<Integer> lst = freqToNumMap.get(i);
        for(int j = 0; j< lst.size() && idx < k; j++){
            result[idx] = lst.get(j);
            idx++;
        }
       }
        return result;
        
    }
}