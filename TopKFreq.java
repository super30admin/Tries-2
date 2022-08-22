//Time complexity: O(n+logk)
//Space complexity: O(n)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int[] result=new int[k];
        for(int i=0;i<nums.length;i++)
        {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        PriorityQueue<Integer> q=new PriorityQueue<>((a,b)->map.get(a)-map.get(b));
        for(int key:map.keySet())
        {
            q.add(key);
            if(q.size()>k)
            {
                q.poll();
            }
        }
        for(int i=k-1;i>=0;i--)
        {
            result[i]=q.poll();
        }
     return result;   
    }
}