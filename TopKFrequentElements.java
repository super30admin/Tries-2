// Time Complexity: O(n)
// Space Complexity: O(n)

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, List<Integer>> fmap = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : map.keySet()) {
            int freq = map.get(num);
            if (!fmap.containsKey(freq))
                fmap.put(freq, new ArrayList<>());
            fmap.get(freq).add(num);
            max = Math.max(max, freq);
        }

        int[] result = new int[k];
        int count = 0;
        int l = 0;
        for (int i = max; count < k; i--) {
            List<Integer> li = fmap.get(i);
            if (li == null)
                continue;
            for (int j = 0; j < li.size() && count < k; j++) {
                result[l++] = li.get(j);
                count++;
            }
        }

        return result;
    }
}