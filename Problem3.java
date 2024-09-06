/*
    LC - 347. Top K Frequent Elements
    TC - O(n logk)
    SC - O(n + k)
*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return new int[]{};
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        for(int key: map.keySet()) {
            int[] temp = new int[] {key, map.get(key)};
            pq.add(temp);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        int[] result = new int[k];
        while(!pq.isEmpty()) {
            result[k - 1] = pq.poll()[0];
            k--;
        }
        return result;
    }
}

/*
    TC - O(n)
    SC - O(n)
*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        List<Integer> bucket[] = new ArrayList[nums.length + 1];
        for(int i=0; i< nums.length; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        for(int key : freq.keySet()) {
            int count = freq.get(key);
            if(bucket[count]==null) {
                bucket[count] = new ArrayList<>();
            }
            bucket[count].add(key);
        }
        int[] result = new int[k];
        int index = 0;
        for(int i=nums.length; i>=0; i--) {
            if(bucket[i]!=null) {
                for(int data: bucket[i]) {
                    result[index++] = data;
                    if(index == k){
                        return result;
                    }
                }
            }
        }
        return result;
    }
}