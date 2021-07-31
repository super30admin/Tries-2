class Solution {
    //Time O(N)
    //Space O(N)
    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0)
        {
            return nums;
        }
        HashMap<Integer , Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++)
        {
            map.put(nums[i] , map.getOrDefault(nums[i] , 0) + 1);
        }
        List bucket[] = new List[nums.length+1];
        for(Integer num : map.keySet())
        {
            int index = map.get(num);
            if(bucket[index] == null)
            {
                bucket[index] = new ArrayList<>();
            }
            bucket[index].add(num);
        }
        int ans[] = new int[k];
        int j = 0;
        for(int i = bucket.length-1 ; i >= 0 && k > 0 ; i--)
        {
            if(bucket[i] != null)
            {
                List<Integer> li = bucket[i];
                for(Integer num : li)
                {
                    ans[j++] = num;
                    k--;
                }
            }
        }
        return ans;
    }
}
