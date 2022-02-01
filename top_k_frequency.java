// Bruteforce Approach : O(nlogk)
// Hashmap with key as num and Value as frequency 
// use min heap of size k to filter out the k top elements with high frequency


// Efficient Approach 
// Time complexity : O(n * max) where max = maximum frequency value in the nums array
// Space complexity : O(n) : 2 hashmaps
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        HashMap<Integer, Integer> elementTofreq = new HashMap<>();
        
        // The same frequency can be present for two nums , so list is needed
        HashMap<Integer, List<Integer>> freqToElement = new HashMap<>();
        
        int max = Integer.MIN_VALUE;
        
        // Result
        int[] result = new int[k];
        
        // Create hashmap with mapping as element ->  freq
        for (int i = 0; i < nums.length; i++){
            
            if (!elementTofreq.containsKey(nums[i])){
                elementTofreq.put(nums[i], 1);
            }
            
            // If exists, add 1 to the frequency
            elementTofreq.put(nums[i] , elementTofreq.get(nums[i])  + 1);
            
            // Most frequenctly occured element's frequency
            max = Math.max(max, elementTofreq.get(nums[i]));
        }
        
        
        // Create hashmap with mapping as freq ->  Element
        for (int key: elementTofreq.keySet()){
            
            // Get the frequency
            int freqKey = elementTofreq.get(key);
            
            // If freq is not present
            if (!freqToElement.containsKey(freqKey)){
                // Add new list
                freqToElement.put(freqKey, new ArrayList<>());
            }
            
            // If exists, add key to the frequency value list
            freqToElement.get(freqKey).add(key);
        }
        
        int j =0 ; //  result pointer
                
        // find the k top most elements
        while(k > 0){
            
            // Start with the max frequency and check if it is present in the frequencyTo element hashmap 
            if (freqToElement.containsKey(max)){
                
                // Get the elements with that max frequency 
                List<Integer> list = freqToElement.get(max);
                
                for (int i = 0; i < list.size() && k > 0; i++){
                    
                    result[j] = list.get(i);
                    
                    // Increment the j pointer
                    j ++ ;
                    
                    // Decrement the k 
                    k--;
                }
            }
            
            // reduce the max
            max--;
            
        }
       
                
    
        return result;
    
    }
}