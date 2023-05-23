import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
Top K frequent Elements
approach: use a min max technique when we have to use a map and a heap
time: O(n+n+k)
space: O(n)
 */
public class Problem3 {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> buckets = new HashMap<>();

        for (int num:nums) {
            freq.put(num, freq.getOrDefault(num, 0)+1);
        }
        int min = 100, max = 0;

        for (int key:freq.keySet()) {
            min = Math.min(min, key);
            max = Math.max(max, key);
            if (!buckets.containsKey(freq.get(key))) {
                buckets.put(freq.get(key), new ArrayList<>());
            }
            buckets.get(freq.get(key)).add(key);
        }
        int[] res = new int[k];
        int j = 0;
        for (int i=max;i>=min && j<k;i--){
            List<Integer> list = buckets.get(i);
            if (list!=null) {
                for (int l = 0;l<list.size() && j<k;l++) {
                    res[j++] = list.get(l);
                }
            }
        }
        return res;
    }
}
