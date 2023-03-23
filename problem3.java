//Time Complexity: O(n log k), where n is the size of the input array nums.
//Space Complexity: O(n), where n is the size of the input array nums.
//ran on leetcode: Yes
// use a priority queue and a hash map to find the k most frequent elements in an array
class Element{
    int val, freq;
    Element(int val, int freq)
    {
        this.val = val;
        this.freq = freq;
    }
}
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Element> pq = new PriorityQueue<>((a,b)->a.freq-b.freq);
        HashMap<Integer,Integer> hm = new HashMap<>();
        
        for(int i=0;i<nums.length;i++)
        {
            hm.put(nums[i], hm.getOrDefault(nums[i],0)+1);
        }
        
        for(Integer key : hm.keySet())
        {
            pq.add(new Element((int)key, hm.get(key)));
            if(pq.size()>k)
                pq.remove();
        }
        
        int[] result = new int[k];
        for(int i=0;i<k;i++)
        {
            result[i] = pq.remove().val;
        }
        return result;
    }
}
