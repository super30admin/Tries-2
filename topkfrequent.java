//Time complexity : O(Nlogk)
//Space Complexity: O(N+k)
class Frequency {
    int freq;
    int num;
    Frequency(int freq, int num) {
        this.freq = freq;
        this.num = num;
    }
}
class FrequencyComparator implements Comparator<Frequency> {
    public int compare(Frequency f1, Frequency f2) {
        if (f1.freq > f2.freq) return -1;
        else if (f1.freq == f2.freq) return 0;
        else return 1;
    }
}
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Frequency> pq = new PriorityQueue<Frequency>(new FrequencyComparator());
        HashMap<Integer, Frequency> hm = new HashMap<Integer, Frequency>();
        for(int i=0;i<nums.length;i++) {
            if (hm.containsKey(nums[i])) hm.get(nums[i]).freq++;
            else hm.put(nums[i], new Frequency(1, nums[i]));
        }
        for(Map.Entry<Integer, Frequency> map: hm.entrySet()) pq.add(map.getValue());
        int top[] = new int[k];
        for(int i=0;i<top.length;i++) {
            top[i] = pq.poll().num;
        }
        return top;
    }
}