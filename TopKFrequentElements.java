// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Create a map of frequencies
// Next create a map of frequencies to list of numbers
// Now start from highest freq and add k numbers with highest freq to result array
// Finally return the result array
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<>();
        for(int x: nums){
            hm.put(x, hm.getOrDefault(x, 0) + 1);
        }
        int n = nums.length;
        Map<Integer, List<Integer>> freqToNo = new HashMap<>();
        for(Map.Entry<Integer, Integer> entry: hm.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            if(freqToNo.containsKey(value)){
                freqToNo.get(value).add(key);
            }
            else{
                List<Integer> xyz = new ArrayList<>();
                xyz.add(key);
                freqToNo.put(value, xyz);
            }
        }
        int[] result = new int[k];
        int j = 0;
        for(int i = n; i >= 0; i--){
            if(freqToNo.containsKey(i)){
                List<Integer> list = freqToNo.get(i);
                for(int xx: list)
                    result[j++] = xx;
            }
            if(j >= k)
                break;
        }
        return result;
    }
}