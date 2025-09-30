// Time Complexity: O(n) - Count frequencies in one pass O(n); build freq→list over unique elems m≤n; sweep frequencies (range ≤n) and collect up to k → overall linear in input size.
// Space Complexity: O(n) - HashMap<num, freq> stores up to n keys.

//Build HashMap<num, freq> by scanning nums once.
//Build HashMap<freq, list of nums>, track min/max freq to know the range to scan.
//Iterate freq from max→min, append nums at each freq into result until k elements are filled.

class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashMap<Integer, List<Integer>> freToNumMap = new HashMap<>();

        for(int key: map.keySet()){

            int frq = map.get(key);

            freToNumMap.putIfAbsent(frq, new ArrayList<>());
            freToNumMap.get(frq).add(key);

            min = Math.min(min, frq);
            max = Math.max(max, frq);
        }

        int[] result = new int[k];
        int idx = 0;

        for(int i=max; i>=min && idx < k; i--){
            if(!freToNumMap.containsKey(i)) continue;

            List<Integer> list = freToNumMap.get(i);
            for(int num : list){
                result[idx] = num;
                idx++;
            }

        }
        
        return result;
    }
}
