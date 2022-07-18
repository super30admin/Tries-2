//TC : O(n log k)
//Sc : O(N)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums==null||nums.length==0) return nums;
        HashMap<Integer,Integer> hm = new HashMap();
        int n = nums.length;
        int[] result = new int[k];
        for(int i=0;i<n;i++)
        {
            hm.put(nums[i],hm.getOrDefault(nums[i],0)+1);
        }
        Queue<Integer> q = new PriorityQueue<>((n1,n2) -> hm.get(n1) - hm.get(n2));

        for(int i : hm.keySet()){
            q.add(i);

            if(q.size() > k)
                q.poll();
        }
        for(int i=0;i<k;i++)
        {
            result[i] = q.poll();
        }

        return result;
    }
}