//Time- O(n)
//Space - O(n)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[k];
        int max =0;
        for(int i=0; i<nums.length; i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
            int temp = map.getOrDefault(nums[i],0);
            max = Math.max(max,temp);
        }
        List<Integer>[] count = new List[max+1];

        for(int num : map.keySet()){
           int freq = map.get(num);
           if(count[freq] == null){
               count[freq] = new ArrayList<>();
           }
           count[freq].add(num);  
        }

        for(int i=max; i>=0 && k>=0; i--){
           if(count[i] == null){
               continue;
           }
           List<Integer> li = count[i];
          
            for(int j=0; j<li.size() && k>0; j++){
                result[k-1] = li.get(j);
                k--;
            }
           } 
           
         return result;

        }
        
    }