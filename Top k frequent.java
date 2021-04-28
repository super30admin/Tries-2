//time complexity-O(3*n)
//Space complexity-O(n)
//Ran on leetcode-Yes
//Solution with comments-
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> freq= new HashMap<>();//count the freq of each number 
        for(int i=0;i<nums.length;i++){
            freq.put(nums[i],freq.getOrDefault(nums[i],0)+1);
        }
        int max=0;
        HashMap<Integer,List<Integer>> revfreq= new HashMap<>();// create a reverse freq hashmap of previous hashmap
        for(int n: freq.keySet()){
            int getfreq= freq.get(n);
            max=Math.max(max,getfreq);
            revfreq.putIfAbsent(getfreq, new ArrayList<>());
            revfreq.get(getfreq).add(n);
        }
        
        int[] out= new int[k];
        int index=0;
        while(index<k){
            if(revfreq.containsKey(max)){//starting from max freq we add all elements with max frequency
                for(int n: revfreq.get(max)){
                    out[index]=n;
                    index++;
                    if(index>=k) break;
                }
                
            }
            max--;
        }
        return out;
    }
}