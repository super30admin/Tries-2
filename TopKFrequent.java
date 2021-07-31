class TopKFrequent {
    //time:O(n)
    //space:O(2n)-> O(n)
    //did it run successfully on leetcode: yes
    
    public int[] topKFrequent(int[] nums, int k) {
        
        //map to store number and its frequency
        HashMap<Integer, Integer> map = new HashMap<>();//n
        //calculating frequency
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        
        //list to store frequency and numbers with that frequency
        List<Integer>[] bucket = new List[nums.length+1];//n
        
        for(int key: map.keySet()){
            //getting frequency for key
            int freq = map.get(key);
            //add number in the list with frequency freq
            if(bucket[freq]==null){
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }
        //result array
        int[] res = new int[k];
        int j=k-1;
        
        //looping through end of bucket
        for(int i = nums.length; i>=0; i--){
            //if freuency exist
            if(bucket[i] !=null){
                //get the list of numbers with that frequency
                List<Integer> list = bucket[i];
                //looping through the list
                for(int val: list){
                    //if result pointer is within bounds 
                    if(j>=0){
                        //add to result
                        res[j] = val;
                        //decrement result pointer
                        j--;    
                    }
                    //if results found
                    else{
                        break;
                    }
                    
                }
            }
            //if results found
            if(j<0) break;
        }
       
        
        return res;
    }
    
}