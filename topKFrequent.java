// approch 1 
// Time Complexity : O(nlogk)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// approch 2
// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;

class Main {
    // approch 1 Heap
    public static int[] topKFrequent1(int[] nums, int k) {
        // null case
        if (nums == null || nums.length == 0)
            return new int[] {};
        // result array
        int[] result = new int[k];
        // hashmap for element -> freq
        HashMap<Integer, Integer> map = new HashMap<>();

        // go through all elements
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // priority queue for getting top k frequent element
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int ca = map.get(a);
            int cb = map.get(b);
            if (ca == cb) {
                return -1;
            }
            return ca - cb;
        });
        for (int key : map.keySet()) {
            pq.add(key);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        // add element from pq to result
        int x = k - 1;
        while (!pq.isEmpty()) {
            int temp = pq.poll();
            result[x--] = temp;
        }
        return result;
    }

    // approch 2 using bucket sort
    public static int[] topKFrequent2(int[] nums, int k) {
        // null case
        if (nums == null || nums.length == 0)
            return new int[] {};

        int n = nums.length;
        // fist add it inside the map
        Map<Integer, Integer> map = new HashMap<>();
        // max varible for storing the max freq
        int max = 0;
        for (int i = 0; i < n; i++) {
            // freq table
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            // update the max
            max = Math.max(max, map.get(nums[i]));
        }
        // bucket sort freqs
        List<Integer>[] li = new ArrayList[max + 1];
        for (int key : map.keySet()) {
            // if list is null make new list
            // and append inside the list
            if (li[map.get(key)] == null) {
                li[map.get(key)] = new ArrayList<>();
            }
            li[map.get(key)].add(key);
        }
        // result array
        int[] result = new int[k];
        // result pointer
        int j = 0;
        // flag to break the loop
        boolean flag = true;
        for (int i = max; i >= 0 && flag; i--) {
            // check if list is null or not
            if (li[i] == null)
                continue;
            // add elements inside the result
            // if j == k break the both loop and return
            for (int l : li[i]) {
                result[j++] = l;
                if (j == k) {
                    flag = false;
                    break;
                }
            }
        }
        // return result
        return result;
    }
    public static void main(String[] args) {
        int[] nums = new int[] { 1,2,2,3,4,5,1,3,5,6,1,5,5,4,5};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent1(nums, k)));
        System.out.println(Arrays.toString(topKFrequent2(nums, k)));

    }
}