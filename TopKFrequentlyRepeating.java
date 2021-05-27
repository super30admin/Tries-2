class TopKFrequentlyRepeating {
    
    // Time Complexity: O(n) -- O(3n)
    // Space Complexity: O(n)
    
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        if(nums == null || nums.length == 0 || k <= 0)
            return result;
        
        Map<Integer,Integer> freqMap = new HashMap<>();
        int maxVal = Integer.MIN_VALUE;
        
        for(int i : nums){
            freqMap.put(i, freqMap.getOrDefault(i, 0)+1);
            maxVal = Math.max(maxVal, freqMap.get(i));
        }
        
        Map<Integer, List<Integer>> freqToElements = new HashMap<>();
        for(int i : freqMap.keySet()){
            freqToElements.putIfAbsent(freqMap.get(i), new ArrayList());
            freqToElements.get(freqMap.get(i)).add(i);
        }
        
        int index = 0;
        while(index < k){
            if(freqToElements.containsKey(maxVal)){
                for(int i : freqToElements.get(maxVal)){
                    result[index++] = i;
                    if(index >= k)
                        break;
                }
            }
            maxVal--;
        }
        
        return result;
    }
}