/*
The questions asks us to return top k most frequent elements. There could be more than one elment that has the same frequency , we can return the elements in any order.

We will first create a hashmap to go over the array and record the frequencies of each element of the array, we will record the maximum frequency till now as well

We will then create a frequency to list of Intgers hashmap , as many integers can have the same number of frequencies

Now ,we have the max, we can apply the principles of bucket sort, 
String from the max frquency, we will gget the list of integers and keep adding them to result array until we get k elements

Time: O (3n)
Space : O ( 2n)


*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {

       // return heapSolution(nums,k);

        int[] result = new int[k];
        HashMap<Integer, Integer> frequMap = new HashMap<Integer,Integer>();
        int maxFrequency =0;
        for(int i=0; i<nums.length ; i++){
            if(!frequMap.containsKey(nums[i])){
                frequMap.put(nums[i], 0);
            }
            int freq = frequMap.get(nums[i]);
            freq++;
            maxFrequency = Math.max(maxFrequency, freq); // recoring the max frequency till now
            frequMap.put(nums[i],freq); // increasing the count 
        }

        // creating a Frequency to Integer list map, there may be a lot of elements that has same frequency, so we will be storing it in a list

        HashMap<Integer, List<Integer>> frequToInt = new HashMap<Integer,List<Integer>>();

        for(int element : frequMap.keySet()){
            if(!frequToInt.containsKey(frequMap.get(element))){
                 frequToInt.put( frequMap.get(element), new ArrayList<Integer>());
            }
            frequToInt.get( frequMap.get(element)).add(element);
        }
        // Now we have the Freq to lsit integers, and highest frequency map, we can use the concept of bucket sort  to get the elements
        // we will start with the highest the frequency elements, get the list, and add them to the result

        while(k>0 && maxFrequency >0){
            List<Integer> list = frequToInt.get(maxFrequency--);
            if(list!=null)
            for(int i=0;i<list.size();i++){
                result[k-1] = list.get(i);
                k--;
            }
        }

        return result;

        
        
    }

    /*
    Time: O (2n + nlogn)
    Space : O ( 3n  )
    */

    private int[] heapSolution(int[] nums, int k){
        
        int[] result = new int[k];
        HashMap<Integer, Integer> frequMap = new HashMap<Integer,Integer>();
        int maxFrequency =0;
        for(int i=0; i<nums.length ; i++){
            if(!frequMap.containsKey(nums[i])){
                frequMap.put(nums[i], 0);
            }
            int freq = frequMap.get(nums[i]);
            freq++;
            maxFrequency = Math.max(maxFrequency, freq); // recoring the max frequency till now
            frequMap.put(nums[i],freq); // increasing the count 
        }


        
        // creating a Frequency to Integer list map, there may be a lot of elements that has same frequency, so we will be storing it in a list
        HashMap<Integer, List<Integer>> frequToInt = new HashMap<Integer,List<Integer>>();

        // creating a max heap
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(
            (a,b) -> b-a // max heap
        );

        for(int element : frequMap.keySet()){
            if(!frequToInt.containsKey(frequMap.get(element))){
                 frequToInt.put( frequMap.get(element), new ArrayList<Integer>());
                 heap.add(frequMap.get(element));  // adding freq only once
            }
            frequToInt.get( frequMap.get(element)).add(element);
        }
    
        int[] found = new int[k];

        while(k>0 && !heap.isEmpty()){
            int key = heap.poll();
            for(int val : frequToInt.get(key)){
                found[--k] = val;
            }
        }

        return found;



    }
}